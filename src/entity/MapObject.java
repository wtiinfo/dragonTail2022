package entity;

import game.GamePanel;
import tilemap.Tile;
import tilemap.TileMap;

import java.awt.*;

public abstract class MapObject {

    protected TileMap tileMap;
    protected int tileSize;
    protected double xMap;
    protected double yMap;

    //posição e vetor
    protected double x;
    protected double y;
    protected double dx;
    protected double dy;

    //dimensoes
    protected int width;
    protected int height;

    //box collision
    protected int col_width;
    protected int col_height;

    //collision
    protected int currentRow;
    protected int currentCol;
    protected double xdest;
    protected double ydest;
    protected double xtemp;
    protected double ytemp;
    protected boolean topLeft;
    protected boolean topRight;
    protected boolean bottomLeft;
    protected boolean bottomRight;

    //animation
    protected Animation animation;
    protected int currentAction;
    protected int previousAction;
    protected boolean facingRight;

    //movement
    protected boolean left;
    protected boolean right;
    protected boolean up;
    protected boolean down;
    protected boolean jumping;
    protected boolean falling;

    //movement attibutes
    protected double moveSpeed;
    protected double maxSpeed;
    protected double stopSpeed;
    protected double fallSpeed;
    protected double maxFallSpeed;
    protected double jumpStart;
    protected double stopJumpSpeed;

    //constructor
    public MapObject(TileMap tileMap) {
        this.tileMap = tileMap;
        this.tileSize = tileMap.getTileSize();
    }


    public boolean intersetcts(MapObject obj) {
        Rectangle obj1 = getRectangle();
        Rectangle obj2 = obj.getRectangle();
        return obj1.intersects(obj2);
    }

    private Rectangle getRectangle() {
        return new Rectangle((int) x - col_width, (int) y - col_height, col_width, col_height);
    }

    private void calculateCorners(double x, double y) {
        int leftTile = (int) (x - col_width / 2) / tileSize;
        int rightTile = (int) (x + col_width / 2 - 1) /  tileSize;
        int topTile = (int) (y - col_height / 2) / tileSize;
        int bottomTile = (int) (y + col_height / 2 -1) / tileSize;
        int tl = tileMap.getType(topTile, leftTile);
        int tr = tileMap.getType(topTile, rightTile);
        int bl = tileMap.getType(bottomTile, leftTile);
        int br = tileMap.getType(bottomTile, rightTile);

        //verificando local da colisao
        topLeft = (tl == Tile.BLOCKED_TILE);
        topRight = (tr == Tile.BLOCKED_TILE);
        bottomLeft = (bl == Tile.BLOCKED_TILE);
        bottomRight = (br == Tile.BLOCKED_TILE);
    }

    public void checkTileMapCollision() {
        currentCol = (int) x / tileSize;
        currentRow = (int) y / tileSize;

        xdest = x + dx;
        ydest = y + dy;

        xtemp = x;
        ytemp = y;

        calculateCorners(x, ydest);

        if(dy < 0) {
            if(topLeft || topRight) {
                dy = 0;
                ytemp = currentRow * tileSize + col_height / 2;
            } else {
                ytemp += dy;
            }
        }
        if(dy > 0) {
            if(bottomLeft || bottomRight) {
                dy = 0;
                falling = false;
                ytemp = (currentRow + 1) * tileSize - col_height / 2;
            } else {
                ytemp += dy;
            }
        }

        calculateCorners(xdest, y);

        if(dx < 0) {
            if(topLeft || bottomLeft) {
                dx = 0;
                xtemp = currentCol * tileSize + col_width / 2;
            } else {
                xtemp += dx;
            }
        }
        if(dx > 0) {
            if(topRight || bottomRight) {
                dx = 0;
                xtemp = (currentCol + 1) * tileSize - col_width / 2;
            }
        }
        if(!falling) {
            calculateCorners(x, ydest + 1);
            if(!bottomLeft && !bottomRight) {
                falling = true;
            }
        }
    }

    public int getX() {
        return  (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getCollWidth() {
        return col_width;
    }

    public int getCollHeight() {
        return col_height;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    //muito importante - (global position, local position)
    public void setMapPosition() {
        xMap = tileMap.getX();
        yMap = tileMap.getY();
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean notOnScreen() {
        return x + xMap + width < 0 || x + xMap - width > GamePanel.WIDTH || y + yMap + height < 0 || y + yMap - height > GamePanel.HEIGHT;
    }

}
