/**
 * GamePanel class to manage panel
 *
 * @author Adrien PELFRESNE
 * ESIEE Paris Groupe 9
 * @version beta1
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;


public class GamePanel extends javax.swing.JPanel implements ActionListener {

    private final Player aPlayer;
    private final Timer aGameTimer;
    private final ArrayList<Wall> aStoredWalls;
    private int aCameraX;
    private ArrayList<BufferedImage> aSprite;
    private AnimationManager aCharacter;


    public GamePanel() {
        aPlayer = new Player(400, 300, this);
        aStoredWalls = new ArrayList<Wall>();
        createWall();
        init();
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
    }

    public void init() {
        BufferedImageManager vLoader = new BufferedImageManager();
        BufferedImage vSpriteSheetFromBuffered = null;
        try {
            vSpriteSheetFromBuffered = vLoader.load("Knight/noBKG_KnightIdle_strip.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SpriteSheet vSpriteSheet = new SpriteSheet(vSpriteSheetFromBuffered);
        //idle, reste a gérer les autres animations
        this.aSprite = new ArrayList<BufferedImage>();
        aSprite.add(vSpriteSheet.spritePicker(21, 14, 24, 30));
        aSprite.add(vSpriteSheet.spritePicker(87, 14, 24, 30));
        aSprite.add(vSpriteSheet.spritePicker(151, 14, 24, 30));
        aSprite.add(vSpriteSheet.spritePicker(215, 14, 24, 30));
        aSprite.add(vSpriteSheet.spritePicker(279, 14, 24, 30));
        aCharacter = new AnimationManager(aSprite);
        aCharacter.setSpeed(200);
        aCharacter.start();
    }

    public void paint(Graphics g) {

        super.paint(g);

        Graphics2D vGtd = (Graphics2D) g;

        if (aCharacter != null) {
            aCharacter.update(System.currentTimeMillis());
            g.drawImage(aCharacter.aSprite, aPlayer.getAX(), aPlayer.getAY(), 50, 50,  null);
        }

        for (Wall wall : aStoredWalls) {
            wall.draw(vGtd);
        }

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
