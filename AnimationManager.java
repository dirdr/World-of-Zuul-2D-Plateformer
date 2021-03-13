import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AnimationManager {

    private ArrayList<BufferedImage> aFrames;

    public BufferedImage aSprite;
    private boolean aRunning = false;
    private long aBeforeTime;
    private long aPreviousTime;
    private long animationSpeed;
    private int aFrameAtPause;
    private int aCurrentFrame;

    public AnimationManager(ArrayList<BufferedImage> pFrames) {
        this.aFrames = pFrames;
    }

    public void setFrames(ArrayList<BufferedImage> pFrames) {
        this.aFrames = pFrames;
    }

    public BufferedImage getSprite() {
        return this.aSprite;
    }

    public void setSpeed(final long pSpeed) {
        this.animationSpeed = pSpeed;
    }

    public void update(final long pTime) {
        if (aRunning) {

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
        aCurrentFrame = 0;
        aFrameAtPause = 0;
        aPreviousTime = 0;

    }

    public void stop() {
        aRunning = false;
        aCurrentFrame = 0;
        aFrameAtPause = 0;
        aPreviousTime = 0;
    }

    public void pause() {
        aFrameAtPause = aCurrentFrame;
        aRunning = false;
    }

    public void resume() {
        aCurrentFrame = aFrameAtPause;
        aRunning = true;
    }

}
