package entity;

import tilemap.TileMap;

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
}
