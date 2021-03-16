/**
 * GamePanel class to manage panel
 *
 * @author Adrien PELFRESNE
 * ESIEE Paris Groupe 9
 * @version beta1
 */


import javax.imageio.ImageIO;
import javax.management.remote.SubjectDelegationPermission;
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


public class GamePanel extends javax.swing.JPanel implements KeyListener, Runnable {

    //GameTimer
    private Timer aGameTimer;
    //State Manager
    private StateManager vStateManager;
    private Graphics2D aG;
    private BufferedImage aImage;
    private Thread thread;
    private boolean running;


    public GamePanel() {
        //JPanel constructor
        super();
        //set the focus on the GamePanel possible
        setFocusable(true);
        //request the focus (allow to get all the input's key and to update this JPanel)
        requestFocus();
    }

    /**
     * allow the thread to reStart
     */
    public void addNotify() {
        super.addNotify();
        if(thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }


    /**
     * Initialize the GamePanel
     */
    public void init() {
        aImage = new BufferedImage(900, 900, BufferedImage.TYPE_INT_RGB);
        aG = (Graphics2D) aImage.getGraphics();
        this.vStateManager = new StateManager();
        this.running = true;
    }


    /**
     * Run Method, start by invoke the init method
     * and, run the Main GameLoop which make the Game Update everyFrame (update, paint, paintToScreen)
     */
    public void run() {
        //initialize the gamePanel's
        init();
         //start time
        long vStart;
        //elapsed time (difference between the currentTime and the start time)
        long vElapsed;
        //Thread wait time
        long vWait;

        // game loop
        while(running) {

            //initialize the vStart var
            vStart = System.nanoTime();
            //update the gamePanel
            update();
            //paint the GamePanel
            paint();
            //paint to screen the Game Panel
            paintToScreen();;
            //changing the vElapsed var each frame
            vElapsed = System.nanoTime() - vStart;
            //changing the vWait var each frame
            int FPS = 60;
            long targetTime = 1000 / FPS;
            vWait = targetTime - vElapsed / 1000000;
            if(vWait < 0) vWait = 5;

            try {
                Thread.sleep(vWait);
            }
            catch(Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void update() {
        vStateManager.update(); //update the current state
        KeyHandler.update(); //update the key pressed or released by the user
    }

    /**
     * repaint the stateManager object (the current state)
     */
    public void paint() {
        vStateManager.paint(aG);
    }

    /**
     * paint the image to the screen
     */
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
