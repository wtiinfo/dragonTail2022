package manager;

import game.GamePanel;
import tilemap.Background;
import tilemap.TileMap;

import java.awt.*;

public class Level1State extends GameState{

    private TileMap tileMap;
    private Background bg;

    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    @Override
    public void init() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tilesets/grasstileset.gif");
        tileMap.loadMap("/Maps/level1-1.map");
        tileMap.setPosition(0, 0);

        //fundo do cenario do level 1
        bg = new Background("/Backgrounds/grassbg1.gif", .1);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {
        //limpar tela - fundo do cenario branco
        //g.setColor(Color.WHITE);
        //g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        //novo fundo para o cenario da fase 1
        bg.draw(g);

        //desenhando o mapa
        tileMap.draw(g);
    }

    @Override
    public void keyPressed(int key) {

    }

    @Override
    public void keyReleased(int key) {

    }
}
