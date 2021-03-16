import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Menu extends State {


    BufferedImage aBackGround;
    private final String[] aChoices;
    private int aCurrentChoice;
    private Color aTitleColor;
    private Font aTitleFont;
    private Color aBodyColor;
    private Color aBodyColorModified;
    private Font aBodyFont;

    public Menu(final StateManager pStateManager) {
        this.aStateManager = pStateManager;
        this.aChoices = new String[]{"Mode Textuel", "Mode 2d", "Aide", "Quitter"};
        this.aCurrentChoice = 0;
    }

    /**
     * Overridden method from the abstract class State
     * paint all the Menu's components
     * @param pG Graphics2D
     */
    @Override
    public void paint(Graphics2D pG) {
        //Draw the background
        pG.drawImage(aBackGround, 0, 0, 900, 900, null);
        //set the Graphics2D color (Title)
        pG.setColor(aTitleColor);
        //set the Graphics 2d Font (Title)
        pG.setFont(aTitleFont);
        //draw The title
        pG.drawString("Jump Castle", 280, 100);
        //set the Graphics2D Color (body)
        pG.setColor(aBodyColor);
        //set the graphics2D Font (Body)
        pG.setFont(aBodyFont);
        //if the current choice is equal to i draw the string in white
        //create an "highlight" effect
        for (int i = 0; i < aChoices.length; i++) {
            if (i == aCurrentChoice) {
                //white
                pG.setColor(aBodyColorModified);
            } else {
                //grey
                pG.setColor(aBodyColor);
            }
            //Draw the string in the Choices String array
            pG.drawString(this.aChoices[i], 400, 400 + i * 100);
        }
    }

    /**
     * Overridden method from the abstract class "State"
     * Update the Menu
     */
    @Override
    public void update() {
        input();
    }

    /**
     * Overridden method from the abstract class State
     * Initialize all the elements in the menu, including the background, Font, Color
     */
    @Override
    public void init() {

        BufferedImageManager vLoader = new BufferedImageManager();
        try {
            this.aBackGround = vLoader.load("TileSet/e8911046c9c13ecfaf86e91d0cc373aa (1).jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        aTitleColor = new Color(0, 0, 0);
        aTitleFont = new Font("Century Gothic", Font.PLAIN, 55);
        aBodyColor = new Color(89, 89, 89);
        aBodyFont = new Font("Courrier New", Font.PLAIN, 40);
        aBodyColorModified = new Color(255, 255, 255);

    }

    /**
     * check the CurrentChoice and do stuff depending of its value
     */
    public void selectionHandler() {
        if (this.aCurrentChoice == 0) {
            //start textual mode
        }
        if (this.aCurrentChoice == 1) {
            //start 2d mode
        }
        if (this.aCurrentChoice == 2) {
            //help
        }
        if (this.aCurrentChoice == 3) {
            System.exit(0);
        }
    }

    /**
     * Overridden method from the abstract class "State"
     * Handle the input in the menu
     */
    @Override
    public void input() {
        //if the User press Enter invoke the selectionHandler method
        if (KeyHandler.isPressed(KeyHandler.aENTER)) {
            this.selectionHandler();
        }
        if (KeyHandler.isPressed(KeyHandler.aUP)) {
            this.aCurrentChoice--;
            if (this.aCurrentChoice < 0) {
                this.aCurrentChoice = 3;
            }
        }
        if (KeyHandler.isPressed(KeyHandler.aDOWN)) {
            this.aCurrentChoice++;
            if (this.aCurrentChoice > 3) {
                this.aCurrentChoice = 0;
            }

        }

    }
}