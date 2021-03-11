/**
 * Class to manage wall and collisions
 *
 * @author Adrien PELFRESNE
 * @version beta1
 */


import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Color;

public class Wall {
    private int aX;
    private final int aY;
    private final int aWidth;
    private final int aHeight;
    private final int aStartX;

    private final Rectangle aHitBox;

    public Wall(final int pX, final int pY, final int pWidth, final int pHeight) {
        this.aX = pX;
        this.aY = pY;
        this.aWidth = pWidth;
        this.aHeight = pHeight;
        aStartX = pX;

        aHitBox = new Rectangle(aX, aY, aWidth, aHeight);
    }

    public void draw(Graphics2D vGtd) {
        vGtd.setColor(Color.BLACK);
        vGtd.drawRect(aX, aY, aWidth, aHeight);
        vGtd.setColor(Color.WHITE);
        vGtd.fillRect(aX + 1, aY + 1, aWidth - 2, aHeight - 2);
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
