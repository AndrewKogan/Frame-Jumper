package CodePuzzle;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CodePuzzle {
    private File hiddenFile;
    private String code = "";

    private JFrame window;

    public CodePuzzle() {
        String username = System.getProperty("user.name");
        try {
            hiddenFile = new File("C:\\Users\\" + username + "\\Desktop\\I SEE YOU.txt");
            if(hiddenFile.createNewFile()) {
                System.out.println("File created");
            } else {
                System.out.println("Error: File already exists");
            }

            for (int i = 0; i < 4; i++)
                code += (int) (Math.random() * 10);

            FileWriter writer = new FileWriter(hiddenFile);
            writer.write(code);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("");

        window.add(new CodePanel(this));
        window.pack();
        window.setLocationRelativeTo(null);

        window.setEnabled(true);
        window.setVisible(true);
    }

    public boolean checkPuzzleSolution(String attempt) {
        return code.equals(attempt);
    }

    public void onPuzzleSolved() {
        System.out.println("Solved");
        onQuit();
    }

    public void onQuit() {
        Desktop.getDesktop().moveToTrash(hiddenFile);
        window.dispose();
    }
}
