import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;


public class Game {

    private UserInterface aGui;
    private GameEngine aEngine;


    /**
     * Game Class Constructor 
     */
    public Game()
    {
        this.aEngine = new GameEngine();
        this.aGui = new UserInterface(this.aEngine);
        this.aEngine.setGUI(this.aGui);

    } //Constructor | Game()

} // Game
