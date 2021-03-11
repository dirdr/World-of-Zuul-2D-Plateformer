/**
 * Class to manage wall and collisions
 *
 * @author Adrien PELFRESNE
 * @version beta1
 */


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Wall {
    private int aX;
    private final int aY;
    private final int aWidth;
    private final int aHeight;
    private final int aStartX;
    private SpriteSheet aSpriteSheetTiles;
    private ArrayList<BufferedImage> aTiles;

    private final Rectangle aHitBox;

    public Wall(final int pX, final int pY, final int pWidth, final int pHeight) {
        this.aX = pX;
        this.aY = pY;
        this.aWidth = pWidth;
        this.aHeight = pHeight;
        aStartX = pX;
        aHitBox = new Rectangle(aX, aY, aWidth, aHeight);
        init();
    }

    public void init() {
        BufferedImageManager vLoader = new BufferedImageManager();
        BufferedImage vSpriteSheetFromBuffered = null;
        try {
            vSpriteSheetFromBuffered = vLoader.load("TileSet/spr_VillageTileSet_strip.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SpriteSheet vSpriteSheet = new SpriteSheet(vSpriteSheetFromBuffered);
        this.aTiles = new ArrayList<BufferedImage>();
        this.aTiles.add(vSpriteSheet.spritePicker(272, 16, 32, 32));
    }

    public void draw(Graphics g) {
        g.drawImage(aTiles.get(0), aX, aY, aWidth, aHeight, null);
    }

    public Rectangle getHitBox() {
        return this.aHitBox;
    }

    public int set(final int pCameraX) {
        this.aX = this.aStartX - pCameraX;
        aHitBox.x = aX;
        return aX;
    }

}
