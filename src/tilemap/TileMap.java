package tilemap;

import game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileMap {

    private double x;
    private double y;

    private int xMin;
    private int xMax;
    private int yMin;
    private int yMax;

    private double tween;

    private int[][] map;
    private int tileSize;
    private int numRows;
    private int numCols;
    private int width;
    private int height;

    private BufferedImage tileset;
    private int numTilesAcross;// tiles lado a lado
    private Tile[][] tiles;

    private int rowOffset;
    private int colOffset;
    private int numRowsToDraw;
    private int numColsToDraw;

    public TileMap(int tileSize) {
        this.tileSize = tileSize;
        numRowsToDraw = GamePanel.HEIGHT / tileSize + 2;
        numColsToDraw = GamePanel.WIDTH / tileSize + 2;
        tween = .07; // transição de tela. ou movimento de interpolação
    }

    //carregar tile
    public void loadTiles(String path) {
        try {
            tileset = ImageIO.read(getClass().getResourceAsStream(path));
            numTilesAcross = tileset.getWidth() / tileSize; // largura total do mapa tile dividido pelo tamanho de um tile 30px
            tiles = new Tile[2][numTilesAcross];

            BufferedImage subImage;//cada tile da imagem completa dos tiles
            for (int col = 0; col < numTilesAcross; col++) {
                subImage = tileset.getSubimage(col * tileSize, 0, tileSize, tileSize);
                tiles[0][col] = new Tile(subImage, Tile.NORMAL_TILE); // Desenhando a primeira linha
                subImage = tileset.getSubimage(col * tileSize, tileSize, tileSize, tileSize);
                tiles[1][col] = new Tile(subImage, Tile.BLOCKED_TILE); // Desenhando a segunda linha
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //carregar o mapa
    public void loadMap(String path) {
        try{
            InputStream in = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());
            map = new int[numRows][numCols]; // ler do arquivo e cria a matrix com o mapa, ponto muito importante
            width = numCols * tileSize; // calculo da largura nescessaria para compor a tela
            height = numRows * tileSize; // calcula da altura - linhas - para compor a tela

            String delimiter = "\\s+"; // Existe devido ao editor de criacao do map
            for(int row = 0; row < numRows; row++) {
                String line = br.readLine();
                String[] tokens = line.split(delimiter);
                for(int col = 0; col < numCols; col++) {
                    map[row][col] = Integer.parseInt(tokens[col]); // loop que carrega os indice do mapa
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public int getHeight() {
        return height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getWidth() {
        return width;
    }

    // retorna o tipo do tile
    public int getType(int row, int col) {
        int matrix = map[row][col];
        int r = matrix / numTilesAcross;
        int c = matrix % numTilesAcross;
        return tiles[r][c].getType();
    }

    // posicionando o tile
    public void setPosition(double x, double y) {
        this.x += (x - this.x) * tween;
        this.y += (y - this.y) * tween;

        fixBounds(); // ajustando limites

        colOffset = (int) -this.x / tileSize;
        rowOffset = (int) -this.y / tileSize;
    }

    private void fixBounds() {
        if (x < xMin)
            x = xMin;
        if (x > xMax)
            x = xMax;
        if (y < yMin)
            y = yMin;
        if (y > yMax)
            y = yMax;
    }

    //desenhando o cenario
    public void draw(Graphics2D g) {
        for (int row = rowOffset; row < rowOffset + numRowsToDraw; row++) {
            if (row >= numRows) break;
            for (int col = colOffset; col < colOffset + numColsToDraw; col++) {
                if (col >= numCols) break;
                if (map[row][col] == 0) continue;

                int matrix = map[row][col];
                int r = matrix / numTilesAcross;
                int c = matrix % numTilesAcross;

                g.drawImage(tiles[r][c].getImage(), (int) x + col * tileSize, (int) y + row * tileSize, null);
            }
        }
    }

}

