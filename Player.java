/**
 * class to manage the player
 *
 * @author Adrien PELFRESNE
 * @version beta1
 */

import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Color;


public class Player {

    GamePanel panel;
    private int aX; //Player x Coordinate
    private int aY; //Player y Coordinate 
    private final int aWidth;
    private final int aHeight;
    private double aXSpeed;
    private double aYSpeed;
    private final Rectangle aHitBox; //utilisation pour gerer les colisions


    boolean aKeyLeft; //press the left key or not 
    boolean aKeyRight; //press the right key or not
    boolean aKeyUp; //press the up key or not 
    boolean aKeyDown; //press the down key or not 

    int aMaxSpeed;

    private boolean aIsGrounded;
    private final double aJumpForce;
    private float aJumpTimeCounter;
    private final float aJumpTime;
    private final double aJumpForceModified;
    private boolean aIsJumping;


    public Player(final int pX, final int pY, final GamePanel panel) {

        this.panel = panel;
        this.aX = pX;
        this.aY = pY;

        this.aJumpForce = 5.5;
        this.aJumpTime = 2;

        this.aJumpForceModified = 0.4; //force de saut modifié


        aWidth = 50; //widht for the player 
        aHeight = 100; //height for th player 

        aHitBox = new Rectangle(pX, pY, aWidth, aHeight);

        this.aMaxSpeed = 7;
    }


    /**
     * set function 
     * manage player each frame
     *
     */
    public void set() {

        if (aKeyLeft && aKeyRight || !aKeyLeft && !aKeyRight) {
            aXSpeed *= 0.7;
        } else if (aKeyLeft && !aKeyRight) {
            aXSpeed--;
        } else if (!aKeyLeft && aKeyRight) {
            aXSpeed++;
        }
        if (aXSpeed > 0 && aXSpeed < 0.75) {
            aXSpeed = 0;
        }
        if (aXSpeed < 0 && aXSpeed > -0.75) {
            aXSpeed = 0;
        }
        if (aXSpeed > aMaxSpeed) {
            aXSpeed = aMaxSpeed;
        }
        if (aXSpeed < -aMaxSpeed) {
            aXSpeed = -aMaxSpeed;
        }

        if (aIsGrounded && aKeyUp) {
            aYSpeed -= aJumpForce;
            aIsGrounded = false;
            aIsJumping = true;
            aJumpTimeCounter = aJumpTime;
        }
        if (aKeyUp && aIsJumping) {
            if (aJumpTimeCounter > 0) {
                aYSpeed -= aJumpForceModified;
                aJumpTimeCounter -= 0.1;
            } else {
                aIsJumping = false;
            }

        }
        if (aKeyUp) {
            aIsJumping = false;
        }
        aYSpeed += 0.4;


        //hit box x
        aHitBox.x += aXSpeed;
        for (Wall wall : panel.getStoredWalls()) {
            if (aHitBox.intersects(wall.getHitBox())) {
                aHitBox.x -= aXSpeed;
                while (!wall.getHitBox().intersects(aHitBox)) {
                    aHitBox.x += Math.signum(aXSpeed);
                }
                aHitBox.x -= Math.signum(aXSpeed);
                aXSpeed = 0;
                aX = aHitBox.x;
            }
        }

        //hit box y
        aHitBox.y += aYSpeed;
        for (Wall wall : panel.getStoredWalls()) {
            if (aHitBox.intersects(wall.getHitBox())) {
                aIsGrounded = true;
                aHitBox.y -= aYSpeed;
                while (!wall.getHitBox().intersects(aHitBox)) {
                    aHitBox.y += Math.signum(aYSpeed);
                }
                aHitBox.y -= Math.signum(aYSpeed);
                aYSpeed = 0;
                aY = aHitBox.y;
            }
        }


        panel.setCameraX(panel.getCameraX() + (int) aXSpeed);
        aY += aYSpeed;

        aHitBox.x = aX;
        aHitBox.y = aY;


    }

    public int getAX() {
        return this.aX;
    }

    public int getAY() {
        return this.aY;
    }


}
