package game;

import javax.swing.*;

public class GameWindow {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dragão 2022");
        frame.setContentPane(new GamePanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
