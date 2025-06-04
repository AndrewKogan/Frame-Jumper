public class Door extends Wall {
    private boolean opened;
    private boolean isOpening;
    private final int startY;

    public Door(int x, int y, int width, int height, Player player, boolean wallJumpable) {
        super(x, y, width, height, player, wallJumpable);
        startY = y;
    }

    @Override
    public void update() {
        super.update();
        if (isOpening) {
            y -= height / 60;
            if (y <= startY - height) {
                opened = true;
                isOpening = false;
            }
        }
    }

    public void open() {
        if (!opened)
            isOpening = true;
    }
}
