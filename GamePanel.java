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



    public GamePanel() {
        this.init();
        this.run();
    }

    public void init() {
        this.vStateManager = new StateManager();
        createWall();
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
                repaint();
            }
        }, 0, 16);
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

    public void createWall() {
        for (int i = 50; i < 3000; i += 50) {
            aStoredWalls.add(new Wall(i, 600, 50, 50));
        }
        aStoredWalls.add(new Wall(50, 550, 50, 50));
        aStoredWalls.add(new Wall(250, 500, 50, 50));
        aStoredWalls.add(new Wall(400, 450, 50, 50));
        aStoredWalls.add(new Wall(550, 400, 50, 50));
        aStoredWalls.add(new Wall(600, 450, 50, 50));
    }



    public void paint(Graphics g) {
        vStateManager.paint(g);
        super.paint(g);


        g.drawImage(aBackGround, 0, 0, 900, 900, this);

        for (Wall wall : aStoredWalls) {
            wall.draw(g);
        }
        this.aPlayer.draw(g);

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
