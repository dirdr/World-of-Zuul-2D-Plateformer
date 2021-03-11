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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.event.KeyEvent;


public class GamePanel extends javax.swing.JPanel implements ActionListener {

    private final Player aPlayer;
    private final Timer aGameTimer;
    private final ArrayList<Wall> aStoredWalls;
    private int aCameraX;
    private Image aBackGround;



    public GamePanel() {
        aPlayer = new Player(50, 50,this);
        aStoredWalls = new ArrayList<Wall>();
        try {
            aBackGround = ImageIO.read(new File("TileSet/spr_Sky_strip.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        createWall();
        aPlayer.init();
        aGameTimer = new Timer();
        aGameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                aPlayer.set();
                for (Wall wall : aStoredWalls) {
                    wall.set(aCameraX);
                }
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

        super.paint(g);

        g.drawImage(aBackGround, 0, 0, 900, 900, this);



        for (Wall wall : aStoredWalls) {
            wall.draw(g);
        }
        aPlayer.draw(g);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    void keyPressed(KeyEvent pE) {

        if (pE.getKeyChar() == 'q') {
            aPlayer.aKeyLeft = true;
        }
        if (pE.getKeyChar() == 'd') {
            aPlayer.aKeyRight = true;
        }
        if (pE.getKeyChar() == 's') {
            aPlayer.aKeyDown = true;
        }
        if (pE.getKeyChar() == ' ' || pE.getKeyChar() == 'z') {
            aPlayer.aKeyUp = true;
        }
    }

    void keyReleased(KeyEvent pE) {

        if (pE.getKeyChar() == 'q') {
            aPlayer.aKeyLeft = false;
        }
        if (pE.getKeyChar() == 'd') {
            aPlayer.aKeyRight = false;
        }
        if (pE.getKeyChar() == 's') {
            aPlayer.aKeyDown = false;
        }
        if (pE.getKeyChar() == ' ' || pE.getKeyChar() == 'z') {
            aPlayer.aKeyUp = false;
        }

    }


}
