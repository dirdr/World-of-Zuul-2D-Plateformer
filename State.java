import java.awt.*;

public abstract class State {

    protected StateManager aStateManager;

    /**
     * @param pG Graphics 2D
     * allows for the repainting off the children class
     */
    public abstract void paint(Graphics2D pG);

    /**
     * the update method is used to update the children class
     */
    public abstract void update();

    /**
     * The Init method is used to initialize the children class
     */
    public abstract void init();

    /**
     * The input method is used to handle al the input coming from the user in the children class
     */
    public abstract void input();


}
