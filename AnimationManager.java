import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AnimationManager {

    private ArrayList<BufferedImage> aFrames;

    BufferedImage aSprite;
    private boolean aRunning = false;
    private long aBeforeTime;
    private long aPreviousTime;
    private long animationSpeed;
    private int aFrameAtPause;
    private int aCurrentFrame;

    public AnimationManager(ArrayList<BufferedImage> pFrames) {
        this.aFrames = pFrames;
    }

    public void setSpeed(final long pSpeed) {
        this.animationSpeed = pSpeed;
    }

    public void update(final long pTime) {
        while (aRunning) {

            if (pTime - aPreviousTime >= animationSpeed) {
                aCurrentFrame++;
                try {
                    aSprite = aFrames.get(aCurrentFrame);
                } catch (IndexOutOfBoundsException vE) {
                    aCurrentFrame = 0;
                    aSprite = aFrames.get(aCurrentFrame);
                }

                aPreviousTime = pTime;
            }

        }
    }

    public void start() {
        aRunning = true;
    }

    public void stop() {
        aRunning = false;
    }

    public void pause() {

    }

    public void resume() {

    }

}
