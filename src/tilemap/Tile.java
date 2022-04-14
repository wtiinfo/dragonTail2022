package tilemap;

import java.awt.image.BufferedImage;

public class Tile {

    private BufferedImage image;
    private int type;

    public static final int NORMAL_TILE = 0;
    public static final int BLOCKED_TILE = 1;

    public Tile(BufferedImage image, int type) {
        this.image = image;
        this.type = type;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getType() {
        return type;
    }
}
