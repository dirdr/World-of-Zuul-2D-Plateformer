import java.awt.*;
import java.util.ArrayList;

public class FirstLevel extends State {

    private ArrayList<Wall> aStoredWalls;
    private int aCameraX;
    private Player aPlayer;
    private Image aBackGround;



    @Override
    public void input() {

    }

    /**
     * First Level's constructor
     * @param pStateManager state manager object for each state extending "State"
     *
     */
    public FirstLevel(final StateManager pStateManager) {
        this.aStateManager = pStateManager;
    }

    /**
     * Overridden paint method from abstract class "State"
     * Paint the level components
     */
    @Override
    public void paint(Graphics2D pG) {

    }

    /**
     * Overridden update method from abstract class "State"
     * Update the level
     */
    @Override
    public void update() {

    }

    /**
     * Overridden init method from abstract class "State"
     * Initialize the level's components
     */
    @Override
    public void init() {
        aPlayer = new Player(400, 300);

    }


    public int getCameraX() {
        return this.aCameraX;
    }

    public void setCameraX(int pCameraX) {
        this.aCameraX = pCameraX;
    }

    public ArrayList<Wall> getStoredWalls() {
        return this.aStoredWalls;
    }


}
