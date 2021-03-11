import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class BufferedImageManager {

    /**
     * method that load and return the sprite sheet with a specified Path
     * @param pPath SpriteSheet path
     * @return the SpriteSheet Image
     * @throws IOException
     */
    public BufferedImage load(final String pPath) throws IOException {
        URL vUrl = this.getClass().getResource(pPath);
        return ImageIO.read(vUrl);
    }


}
