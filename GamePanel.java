/**
 * GamePanel class to manage panel
 *
 * @author Adrien PELFRESNE
 * ESIEE Paris Groupe 9
 * @version beta1
 */


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.Key;
import java.util.*;
import java.awt.event.KeyEvent;


public class GamePanel extends javax.swing.JPanel implements KeyListener {

    //GameTimer
    private Timer aGameTimer;
    //State Manager
    private StateManager vStateManager;
    private Graphics2D aG;
    private BufferedImage aImage;



    public GamePanel() {
        this.init();
        this.run();
    }

    public void init() {
        aImage = new BufferedImage(900, 900, BufferedImage.TYPE_INT_RGB);
        aG = (Graphics2D) aImage.getGraphics();
        this.vStateManager = new StateManager();
        aGameTimer = new Timer();
    }

    public void update() {
        KeyHandler.update(); //update the key pressed or released by the user
        vStateManager.update(); //update the current state
    }

    public void run() {
        //MAIN GAME LOOP
        aGameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                update();
                paint();
                paintToScreen();
            }
        }, 0, 16);
    }


    public void paint() {
        vStateManager.paint(aG);
    }

    public void paintToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(aImage, 0, 0, 900, 900, null);
        g2.dispose();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * KeyPressed Implemented Method from KeyListener interface
     * @param pE the keyEvent
     * this method take the key event pE and use the KeyHandler set method to update the boolean array of KeyPressed
     */
    @Override
    public void keyPressed(final KeyEvent pE) {
        KeyHandler.set(pE.getKeyCode(), true); //true because the key is pressed
    }

    /**
     * KeyReleased implemented method from KeyListener Interface
     * @param pE the KeyEvent
     * this method take the key event pE and use the KeyHandler set method to update the boolean array of KeyPressed
     */
    @Override
    public void keyReleased(final KeyEvent pE) {
        KeyHandler.set(pE.getKeyCode(), false); //false because the key is not pressed
    }
}
