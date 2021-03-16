import javax.imageio.IIOException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Key;

public class Menu extends State {

    BufferedImage aBackGround;
    private String[] aChoices;
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


    @Override
    public void paint(Graphics2D pG) {
        pG.drawImage(aBackGround, 0, 0, 900, 900, null);
        pG.setColor(aTitleColor);
        pG.setFont(aTitleFont);
        pG.drawString("Jump Castle", 300, 100);
        pG.setColor(aBodyColor);
        pG.setFont(aBodyFont);
        for (int i = 0; i < aChoices.length; i++) {
            if (aChoices[i].equals(aCurrentChoice)) {
                pG.setColor(aBodyColorModified);

            } else {
                pG.setColor(aBodyColor);
            }
            pG.drawString(this.aChoices[i], 400, 400 + i * 100);
        }
    }


    @Override
    public void update() {

    }

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

    @Override
    public void input() {
        if (KeyHandler.isPressed(KeyHandler.aENTER)) {
            this.selectionHandler();
        }
        if (KeyHandler.isPressed(KeyHandler.aUP)) {
            this.aCurrentChoice--;
        }
        if (KeyHandler.isPressed(KeyHandler.aDOWN)) {
            this.aCurrentChoice++;
        }
    }
}