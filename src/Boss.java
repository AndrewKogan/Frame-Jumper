import Audio.AudioPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Boss implements ActionListener {
    private Player player;

    private int xSpeed = 2;
    private boolean facingRight;
    private int x;
    private int y;

    private int width;
    private int height;

    private int health;
    private int maxHealth;

    private Rectangle hitbox;

    private boolean attacking = false;
    private int attackDMG;
    private int iFrames;

    private Timer attackCooldown;
    private Timer comboTimer = new Timer(0, this);;
    private Timer endlagTimer = new Timer(0, this);;

    private int comboIndex;

    private boolean dashing;
    private int dashDirection;
    private int dashFrames = 20;

    private Timer dropTimer = new Timer(0, this);

    private Timer teleportTimer = new Timer(0, this);

    private Animation walk;
    private Animation teleport;
    private Animation flashKick;
    private Animation leap;
    private Animation dropkick;
    private Animation dash;
    private Animation combo1;
    private Animation combo2;
    private Animation combo3;
    private Animation currentAnimation;

    private BossState state = BossState.WALKING;

    public Boss(Player p) {
        player = p;

        x = 200;
        y = 700;

        width = 125;
        height = 200;

        maxHealth = 100;
        health = maxHealth;

        hitbox = new Rectangle(x, y, width, height);
        attackCooldown = new Timer(5000, this);

        walk = new Animation("src\\images\\Matthew\\Walk", 2, 4, true);
        teleport = new Animation("src\\images\\Matthew\\Teleport", 3, 3, false);
        flashKick = new Animation("src\\images\\Matthew\\FlashKick", 6, 12, false);
        leap = new Animation("src\\images\\Matthew\\Leap", 5, 7, false);
        dropkick = new Animation("src\\images\\Matthew\\ExplosiveDropkick", 1, 1, false);
        dash = new Animation("src\\images\\Matthew\\Dash", 3, 3, false);
        combo1 = new Animation("src\\images\\Matthew\\ThreeHitCombo\\HitOne", 2, 6, false);
        combo2 = new Animation("src\\images\\Matthew\\ThreeHitCombo\\HitTwo", 2, 6, false);
        combo3 = new Animation("src\\images\\Matthew\\ThreeHitCombo\\HitThree", 5, 8, false);
    }

    public void update() {
        if (iFrames > 0) iFrames--;

        switch (state) {
            case WALKING:
                if (player.x > x) {
                    xSpeed = 2;
                    facingRight = true;
                }
                else if (player.x < x) {
                    xSpeed = -2;
                    facingRight = false;
                }

                if (currentAnimation != walk) {
                    int delay = 1000 * ((int) (Math.random() * 4) + 3);
                    attackCooldown = new Timer(delay, this);
                    attackCooldown.start();

                    currentAnimation = walk;
                    currentAnimation.play();
                }
                break;
            case COMBOING:
                if (comboIndex == 0) {
                    if (currentAnimation != combo1) {
                        currentAnimation = combo1;
                        currentAnimation.play();
                        AudioPlayer.playSound("src\\Audio\\Matthew\\Combo1.wav");
                    }
                    else if (currentAnimation.finished && !comboTimer.isRunning()) {
                        attacking = true;
                        attackDMG = 2;
                        comboTimer = new Timer(500, this);
                        comboTimer.start();
                    }
                }
                else if (comboIndex == 1) {
                    if (currentAnimation != combo2) {
                        currentAnimation = combo2;
                        currentAnimation.play();
                        AudioPlayer.playSound("src\\Audio\\Matthew\\Combo1.wav");
                    }
                    else if (currentAnimation.finished && !comboTimer.isRunning()) {
                        attacking = true;
                        attackDMG = 2;
                        comboTimer = new Timer(500, this);
                        comboTimer.start();
                    }
                }
                else if (comboIndex == 2) {
                    if (currentAnimation != combo3) {
                        currentAnimation = combo3;
                        currentAnimation.play();
                        AudioPlayer.playSound("src\\Audio\\Matthew\\Combo2.wav");
                    }
                    else if (currentAnimation.currentFrame == 2) {
                        attacking = true;
                        attackDMG = 5;
                    }
                    else if (currentAnimation.finished && !endlagTimer.isRunning()) {
                        attacking = false;
                        endlagTimer = new Timer(1000, this);
                        endlagTimer.start();
                    }
                }
                break;
            case DASHING:
                if (comboIndex >= 3) {
                    comboIndex = 0;
                    endlagTimer = new Timer(400, this);
                    endlagTimer.start();
                }
                else if (!comboTimer.isRunning() && !endlagTimer.isRunning()) {
                    if (currentAnimation != dash) {
                        currentAnimation = dash;
                        currentAnimation.play();
                        AudioPlayer.playSound("src\\Audio\\Matthew\\Dash.wav");
                    }
                    else if (currentAnimation.finished && !dashing) {
                        attacking = true;
                        attackDMG = 10;
                        dashing = true;
                        dashDirection = (int) Math.signum(player.x - x);
                        facingRight = dashDirection > 0;
                    }
                    else if (currentAnimation.finished) {
                        x += 12 * dashDirection;
                        dashFrames--;
                        if (dashFrames <= 0) {
                            attacking = false;
                            dashing = false;
                            dashFrames = 20;
                            currentAnimation = walk;
                            comboTimer = new Timer(500, this);
                            comboTimer.start();
                        }
                    }
                }
                break;
            case LEAPING:
                if (currentAnimation != leap) {
                    currentAnimation = leap;
                    currentAnimation.play();
                    AudioPlayer.playSound("src\\Audio\\Matthew\\Leap.wav");
                }
                else if (currentAnimation.finished && y > -200) y -= 15 - player.yspeed;
                else if (y <= -200) {
                    x = player.x;
                    if (!dropTimer.isRunning()) {
                        dropTimer = new Timer(1000, this);
                        dropTimer.start();
                    }
                }
                break;
            case DROPPING:
                if (currentAnimation != dropkick) {
                    currentAnimation = dropkick;
                    currentAnimation.play();
                    AudioPlayer.playSound("src\\Audio\\Matthew\\Dropkick.wav");
                }
                else if (currentAnimation.finished && y < player.y && !endlagTimer.isRunning()) {
                    y += 25 - player.yspeed;
                    attacking = true;
                    attackDMG = 20;
                }
                else if (y >= player.y && !endlagTimer.isRunning()) {
                    y = 775;
                    attacking = false;
                    endlagTimer = new Timer(1500, this);
                    endlagTimer.start();
                }
                break;
            case TELEPORTING:
                if (currentAnimation != teleport) {
                    currentAnimation = teleport;
                    currentAnimation.play();
                    AudioPlayer.playSound("src\\Audio\\Matthew\\Teleport.wav");
                }
                else if (currentAnimation.finished && !teleportTimer.isRunning()) {
                    teleportTimer = new Timer(500, this);
                    teleportTimer.start();
                }
                break;
            case KICKING:
                if (currentAnimation != flashKick) {
                    currentAnimation = flashKick;
                    currentAnimation.play();
                    AudioPlayer.playSound("src\\Audio\\Matthew\\FlashKick.wav");
                }
                else if (currentAnimation.currentFrame == 3) {
                    attacking = true;
                    attackDMG = 20;
                }
                else if (currentAnimation.finished && !endlagTimer.isRunning()) {
                    attacking = false;
                    endlagTimer = new Timer(2000, this);
                    endlagTimer.start();
                }
                break;
        }

        x += xSpeed - player.xspeed;
        y -= player.yspeed;
        hitbox.x = x;
        hitbox.y = y;

        if (hitbox.intersects(player.hitBox) && player.iFrames <= 0) {
            if (attacking) {
                player.health -= attackDMG;
                player.iFrames = 40;
            }
            else if (player.attacking && iFrames <= 0) {
                health -= 4;
                iFrames = 40;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage activeFrame = currentAnimation.getActiveFrame();

        if (!facingRight) g2.drawImage(activeFrame, x, y, width, height, null);
        else g2.drawImage(activeFrame, x + width, y, -width, height, null);

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Old English Text MT", Font.PLAIN, 72));
        g2.drawString("Matthias", 650, 75);
        g2.fillRect(200, 100, 1100, 100);
        g2.setColor(Color.RED);
        g2.fillRect(225, 112, (int) (((double)health / maxHealth) * 1050), 75);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == attackCooldown) {
            double rng = Math.random();
            if (Math.abs(x - player.x) <= 150) {
                if (rng < 0.5) state = BossState.COMBOING;
                else if (rng < 0.75) state = BossState.DASHING;
                else if (rng < 0.9) state = BossState.LEAPING;
                else state = BossState.TELEPORTING;
            }
            else if (Math.abs(x - player.x) <= 400) {
                if (rng < 0.4) state = BossState.LEAPING;
                else if (rng < 0.8) state = BossState.DASHING;
                else state = BossState.TELEPORTING;
            }
            else {
                if (rng < 0.6) state = BossState.TELEPORTING;
                else if (rng < 0.9) state = BossState.LEAPING;
                else state = BossState.DASHING;
            }

            xSpeed = 0;
            attackCooldown.stop();
        }
        else if (source == comboTimer) {
            attacking = false;
            comboIndex++;
            comboTimer.stop();
        }
        else if (source == dropTimer) {
            state = BossState.DROPPING;
            dropTimer.stop();
        }
        else if (source == teleportTimer) {
            state = BossState.KICKING;
            if (player.facingLeft) {
                x = player.x + player.width - 10;
                facingRight = false;
            }
            else {
                x = player.x - width + 10;
                facingRight = true;
            }
            teleportTimer.stop();
        }
        else if (source == endlagTimer) {
            state = BossState.WALKING;
            attackDMG = 0;
            comboIndex = 0;
            y = 700;
            endlagTimer.stop();
        }
    }
}
