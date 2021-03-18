import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class FirstLevel extends State {

    private ArrayList<Wall> aStoredWalls;
    private int aCameraX;
    private Player aPlayer;
    private Image aBackGround;



    @Override
    public void input() {
        this.aPlayer.aKeyLeft = KeyHandler.isPressed(KeyHandler.aLEFT);
        this.aPlayer.aKeyRight = KeyHandler.isPressed(KeyHandler.aRIGHT);
        this.aPlayer.aKeyUp = KeyHandler.isPressed(KeyHandler.aUP);

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
        pG.drawImage(this.aBackGround, 0, 0, GamePanel.aWIDTH, GamePanel.aHEIGHT, null);
        this.aPlayer.draw(pG);
    }

    /**
     * Overridden update method from abstract class "State"
     * Update the level
     */
    @Override
    public void update() {
        input();
        this.aPlayer.update();
    }

    /**
     * Overridden init method from abstract class "State"
     * Initialize the level's components
     */
    @Override
    public void init() {
        BufferedImageManager vLoader = new BufferedImageManager();
        try {
            this.aBackGround = vLoader.load("TileSet/spr_Sky_strip.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
