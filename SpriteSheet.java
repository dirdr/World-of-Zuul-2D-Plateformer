/**
 * SpriteSheet manager class
 *
 * @author Adrien PELFRESNE
 * @version beta1
 */


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class SpriteSheet {


    BufferedImage aSpriteSheet;

    public SpriteSheet(BufferedImage pSpriteSheet) {
        this.aSpriteSheet = pSpriteSheet;
    }

    public BufferedImage spritePicker(final int pX, final int pY, final int pWidth, final int pHeight) {
        return aSpriteSheet.getSubimage(pX, pY, pWidth, pHeight);
    }

}
