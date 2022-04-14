package manager;

import tilemap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState{

    private Background bg;
    private int currentChoice = 0;
    private final String[] options = {"Iniciar", "Ajuda", "Sair"};
    private Color titleColor;
    private Font titleFont, font;

    public MenuState(GameStateManager gsm) {
        this.gsm = gsm;
        try {
            bg = new Background("/Backgrounds/menubg.gif", 1);
            bg.setVector(-0.1, 0);
            titleColor = new Color(128, 0, 0);
            titleFont = new Font("Century Gothic", Font.PLAIN, 28);
            font = new Font("Arial", Font.PLAIN, 12);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
    }

    @Override
    public void update() {
        bg.update();
    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);

        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("Dragão 2022", 80, 70);
        g.setFont(font);

        for (int i = 0; i < options.length; i++) {
            if(i == currentChoice) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.GRAY);
            }
            g.drawString(options[i], 145, 140 + i * 15);
        }
    }

    private void select() {
        if(currentChoice == 0) {
            // Iniciar game
            gsm.setState(GameStateManager.LEVEL_1_STATE);
        }
        if(currentChoice == 1) {
            // Menu de ajuda
        }
        if(currentChoice == 2) {
            System.exit(0);
        }
    }

    @Override
    public void keyPressed(int key) {
        if(key == KeyEvent.VK_ENTER) {
            select();
        }
        if(key == KeyEvent.VK_UP) {
            currentChoice--;
            if(currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }
        if(key == KeyEvent.VK_DOWN) {
            currentChoice++;
            if(currentChoice == options.length) {
                currentChoice = 0;
            }
        }
    }

    @Override
    public void keyReleased(int key) {
    }
}
