package manager;

import tilemap.Background;

import java.awt.*;

public class MenuState extends GameState{

    private Background bg;
    private int currentChoice = 0;
    private final String[] options = {"Iniciar", "Ajuda", "Sair"};
    private Color titleColor;
    private Font titleFont, font;

    public MenuState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    @Override
    public void init() {
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics2D g) {
    }

    @Override
    public void keyPressed(int key) {
    }

    @Override
    public void keyReleased(int key) {
    }
}
