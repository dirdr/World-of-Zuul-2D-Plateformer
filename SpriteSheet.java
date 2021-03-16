/**
 * The SpriteSheet Class is used to load a SpriteSheet(Buffered Image) and to pick a sprite from it
 *
 * @author Adrien PELFRESNE
 * @version beta1
 */


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class SpriteSheet {

    private final BufferedImage aSpriteSheet;

    /**
     *
     * @param pSpriteSheet
     * initialize the spriteSheet field with the pSpriteSheet parameter
     */
    public SpriteSheet(final BufferedImage pSpriteSheet) {
        this.aSpriteSheet = pSpriteSheet;
    }

    /**
     *
     * @param pX Sprite x position starting at the top left corner
     * @param pY Sprite y Position starting at the top left corner
     * @param pWidth Sprite Width
     * @param pHeight Sprite height
     * @return the Sprite coming from the SpriteSheet with the specified coordinates
     */
    public BufferedImage spritePicker(final int pX, final int pY, final int pWidth, final int pHeight) {
        return aSpriteSheet.getSubimage(pX, pY, pWidth, pHeight);
    }

}
