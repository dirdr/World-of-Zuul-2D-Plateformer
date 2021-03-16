import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AnimationManager {

    private ArrayList<BufferedImage> aFrames;

    public BufferedImage aSprite;
    private boolean aRunning = false;
    private long aPreviousTime;
    private long animationSpeed;
    private int aFrameAtPause;
    private int aCurrentFrame;

    /**
     * @param pFrames the ArrayList of BufferedImage containing the sprites
     */
    public AnimationManager(ArrayList<BufferedImage> pFrames) {
        this.aFrames = pFrames;
    }

    /**
     * @param pSpeed the speed of the animation
     * change the speed of the animation
     */
    public void setSpeed(final long pSpeed) {
        this.animationSpeed = pSpeed;
    }

    /**
     * the update method is used to update the animation (get the next sprite each frame)
     * @param pTime A TIMER
     */
    public void update(final long pTime) {
        //if the current animation is currently running
        if (aRunning) {
            //if the elapsed time is greater or equals than the animation speed
            if (pTime - aPreviousTime >= animationSpeed) {
                //increment the frame elapsed by one
                aCurrentFrame++;
                //try to update the sprite
                try {
                    aSprite = aFrames.get(aCurrentFrame);

                } catch (IndexOutOfBoundsException vE) {
                    //if we are catching an IndexOutOfBoundsException
                    //set the current frame for the animation to 0
                    //re try
                    aCurrentFrame = 0;
                    aSprite = aFrames.get(aCurrentFrame);
                }
                //update the time elapsed
                aPreviousTime = pTime;
            }

        }
    }


    /**
     * Start the animation
     */
    public void start() {
        aRunning = true;
        aCurrentFrame = 0;
        aFrameAtPause = 0;
        aPreviousTime = 0;

    }

    /**
     * Stop the animation
     */
    public void stop() {
        aRunning = false;
        aCurrentFrame = 0;
        aFrameAtPause = 0;
        aPreviousTime = 0;
    }

    /**
     * Pause the animation
     * the frame at which the animation was is not reset
     */
    public void pause() {
        aFrameAtPause = aCurrentFrame;
        aRunning = false;
    }

    /**
     * Resume the Animation at which it was when it stopped
     */
    public void resume() {
        aCurrentFrame = aFrameAtPause;
        aRunning = true;
    }

}
