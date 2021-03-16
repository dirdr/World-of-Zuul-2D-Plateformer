/**
 * class to manage the player
 *
 * @author Adrien PELFRESNE
 * @version beta1
 */


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.util.ArrayList;



public class Player {

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



    private final double aJumpForce; //player's jump Force
    private float aJumpTimeCounter;
    private final float aJumpTime;
    private final double aJumpForceModified;
    private int aDirection; //player direction, left: -1, right: 1



    private AnimationManager aCharacterIDLE;
    private AnimationManager aCharacterRunning;
    private AnimationManager aCharacterJumping;
    private AnimationManager aCharacterFalling;


    private final int aMaxSpeed; //Player's max speed can't overcome this value

    private static final int aJUMP_FORCE = 7;
    private static final int aJUMP_TIME = 2;
    private static final double aJUMP_FORCE_MODIFIED = 0.3;
    private static final int aWIDTH = 64;
    private static final int aHEIGHT = 64;
    private static final int aMAX_SPEED = 7;

    private int aCurrentJumpStatus;
    private int aCurrentStatus;


    //status
    private static final int aIDLE = 0;
    private static final int aRUN = 1;

    //jumpStatus
    private static final int aNOT_JUMPING = 0;
    private static final int aRISING = 1;
    private static final int aFALLING = 2;


    /**
     * Player Constructor
     * @param pX x start coordinate
     * @param pY y start coordinate
     *
     */
    public Player(final int pX, final int pY) {

        this.aX = pX;
        this.aY = pY;
        this.aJumpForce = aJUMP_FORCE; //Original Jump Force (Original Gravity) CONST
        this.aJumpTime = aJUMP_TIME;
        this.aJumpForceModified = aJUMP_FORCE_MODIFIED; //modified Jump Force (modified gravity) CONST

        this.aWidth = aWIDTH; //width for the player CONST
        this.aHeight = aHEIGHT; //height for th player CONST

        this.aHitBox = new Rectangle(pX, pY, aWidth, aHeight);
        this.aMaxSpeed = aMAX_SPEED;

        this.aCurrentStatus = aIDLE; //when a player is created is default status is Idle
        this.aCurrentJumpStatus = aNOT_JUMPING; //when a player is created is default jump status is not jumping
        this.aDirection = 1; //when a player is created, is default direction is right

        this.init();  //initialize the Player sprites
        aCharacterIDLE.start(); //start the Idle animation

    }

    public void init() {

        //create a new Loader Object
        BufferedImageManager vLoader = new BufferedImageManager();
        //initialize a Sprite sheet
        BufferedImage vSpriteSheetFromBuffered = null;
        //try to assign the Sprite sheet with the specified path to it (idle)
        try {
            vSpriteSheetFromBuffered = vLoader.load("Knight/noBKG_KnightIdle_strip.png");
        } catch (IOException e) { //catch IOException
            e.printStackTrace();
        }
        //create a new SpriteSheet Object with the bufferedImage coming from the loader
        //SpriteSheet Object allow to use the spritePicker method
        SpriteSheet vSpriteSheet = new SpriteSheet(vSpriteSheetFromBuffered);

        //put each frame's coordinate into the ArrayList of BufferedImage linked to the corresponding animation
        ArrayList<BufferedImage> vIdleAnimation = new ArrayList<>();
        vIdleAnimation.add(vSpriteSheet.spritePicker(23, 14, 26, 30)); //1
        vIdleAnimation.add(vSpriteSheet.spritePicker(87, 14, 26, 30)); //2
        vIdleAnimation.add(vSpriteSheet.spritePicker(151, 14, 26, 30)); //3
        vIdleAnimation.add(vSpriteSheet.spritePicker(215, 14, 26, 30)); //4
        vIdleAnimation.add(vSpriteSheet.spritePicker(279, 14, 26, 30)); //5
        vIdleAnimation.add(vSpriteSheet.spritePicker(343, 14, 26, 30)); //6
        vIdleAnimation.add(vSpriteSheet.spritePicker(407, 14, 26, 30)); //7
        vIdleAnimation.add(vSpriteSheet.spritePicker(471, 14, 26, 30)); //8
        vIdleAnimation.add(vSpriteSheet.spritePicker(535, 14, 26, 30)); //9
        vIdleAnimation.add(vSpriteSheet.spritePicker(599, 14, 26, 30)); //10
        vIdleAnimation.add(vSpriteSheet.spritePicker(663, 14, 26, 30)); //11
        vIdleAnimation.add(vSpriteSheet.spritePicker(727, 14, 26, 30)); //12
        vIdleAnimation.add(vSpriteSheet.spritePicker(791, 14, 26, 30)); //13
        vIdleAnimation.add(vSpriteSheet.spritePicker(855, 14, 26, 30)); //14
        vIdleAnimation.add(vSpriteSheet.spritePicker(919, 14, 26, 30)); //15
        //create the AnimationManager Object with the filled BufferedImages ArrayList
        this.aCharacterIDLE = new AnimationManager(vIdleAnimation);
        this.aCharacterIDLE.setSpeed(64);



        //initialize a Sprite sheet
        vSpriteSheetFromBuffered = null;
        //try to assign the Sprite sheet with the specified path to it (fall)
        try {
            vSpriteSheetFromBuffered = vLoader.load("Knight/noBKG_KnightJumpAndFall_strip.png");
        } catch (IOException e) { //catch IOException
            e.printStackTrace();
        }
        //re assign the SpriteSheet Object with the bufferedImage coming from the loader
        //SpriteSheet Object allow to use the spritePicker method
        vSpriteSheet = new SpriteSheet(vSpriteSheetFromBuffered);


        //put each frame's coordinate into the ArrayList of BufferedImage linked to the corresponding animation
        ArrayList<BufferedImage> vJumpAnimation = new ArrayList<>();
        vJumpAnimation.add(vSpriteSheet.spritePicker(55, 14, 24, 30)); //1
        vJumpAnimation.add(vSpriteSheet.spritePicker(199, 14, 24, 30)); //2
        vJumpAnimation.add(vSpriteSheet.spritePicker(343, 14, 24, 30)); //3
        vJumpAnimation.add(vSpriteSheet.spritePicker(494, 11, 19, 31)); //4
        vJumpAnimation.add(vSpriteSheet.spritePicker(647, 10, 18, 28)); //5
        vJumpAnimation.add(vSpriteSheet.spritePicker(797, 3, 23, 28)); //6
        vJumpAnimation.add(vSpriteSheet.spritePicker(950, 1, 23, 28)); //7
        //create the AnimationManager Object with the filled BufferedImages ArrayList
        this.aCharacterJumping = new AnimationManager(vJumpAnimation);



        //initialize a Sprite sheet
        vSpriteSheetFromBuffered = null;
        //try to assign the Sprite sheet with the specified path to it (fall)
        try {
            vSpriteSheetFromBuffered = vLoader.load("Knight/noBKG_KnightJumpAndFall_strip.png");
        } catch (IOException e) { //catch IOException
            e.printStackTrace();
        }
        //re assign the SpriteSheet Object with the bufferedImage coming from the loader
        //SpriteSheet Object allow to use the spritePicker method
        vSpriteSheet = new SpriteSheet(vSpriteSheetFromBuffered);


        //put each frame's coordinate into the ArrayList of BufferedImage linked to the corresponding animation
        ArrayList<BufferedImage> vFallAnimation = new ArrayList<>();
        vFallAnimation.add(vSpriteSheet.spritePicker(1107, 3, 19, 27)); //8
        vFallAnimation.add(vSpriteSheet.spritePicker(1258, 9, 20, 27)); //9
        vFallAnimation.add(vSpriteSheet.spritePicker(1407, 11, 20, 28)); //10
        vFallAnimation.add(vSpriteSheet.spritePicker(1556, 22, 26, 22)); //11
        vFallAnimation.add(vSpriteSheet.spritePicker(1700, 16, 24, 28)); //12
        vFallAnimation.add(vSpriteSheet.spritePicker(1844, 14, 24, 30)); //13
        vFallAnimation.add(vSpriteSheet.spritePicker(1988, 14, 24, 30)); //14
        this.aCharacterFalling = new AnimationManager(vFallAnimation);

        //initialize a Sprite sheet
        vSpriteSheetFromBuffered = null;
        //try to assign the Sprite sheet with the specified path to it (Run)
        try {
            vSpriteSheetFromBuffered = vLoader.load("Knight/noBKG_KnightRun_strip.png");
        } catch(IOException e){ //catch IOException
            e.printStackTrace();
        }
        //re assign the SpriteSheet Object with the bufferedImage coming from the loader
        //SpriteSheet Object allow to use the spritePicker method
        vSpriteSheet = new SpriteSheet(vSpriteSheetFromBuffered);

        //put each frame's coordinate into the ArrayList of BufferedImage linked to the corresponding animation
        ArrayList<BufferedImage> vRunAnimation = new ArrayList<>();
        vRunAnimation.add(vSpriteSheet.spritePicker(42, 15, 25, 29)); //1
        vRunAnimation.add(vSpriteSheet.spritePicker(140, 14, 25, 30)); //2
        vRunAnimation.add(vSpriteSheet.spritePicker(236, 15, 27, 29)); //3
        vRunAnimation.add(vSpriteSheet.spritePicker(330, 16, 30, 28)); //4
        vRunAnimation.add(vSpriteSheet.spritePicker(426, 15, 29, 29)); //5
        vRunAnimation.add(vSpriteSheet.spritePicker(524, 14, 25, 30)); //6
        vRunAnimation.add(vSpriteSheet.spritePicker(618, 15, 25, 29)); //7
        vRunAnimation.add(vSpriteSheet.spritePicker(713, 16, 25, 28)); //8
        this.aCharacterRunning = new AnimationManager(vRunAnimation);
        this.aCharacterRunning.setSpeed(90);

    }


    /**
     * update function
     * handle direction, status change, jump, hitBox Collisions
     */
    public void update() {


        this.aCurrentStatus = aIDLE;

        if (aKeyLeft && aKeyRight || !aKeyLeft && !aKeyRight) {
            aXSpeed *= 0.7;
        }
        //go Left
        if (aKeyLeft && !aKeyRight) {
            aXSpeed--;
            aCurrentStatus = aRUN;
            this.aDirection = -1;
        }
        //Go right
        if (aKeyRight && !aKeyLeft) {
            aXSpeed++;
            aCurrentStatus = aRUN;
            this.aDirection = 1;
        }
        //if the speed is to slow stop the player
        if (aXSpeed > 0 && aXSpeed < 0.75) {
            aXSpeed = 0;
        }
        //if the speed is to slow stop the player
        if (aXSpeed < 0 && aXSpeed > -0.75) {
            aXSpeed = 0;
        }

        //avoiding speed to overcome maxSpeed
        if (aXSpeed > aMaxSpeed) {
            aXSpeed = aMaxSpeed;
        }
        //avoiding speed to overcome maxSpeed
        if (aXSpeed < -aMaxSpeed) {
            aXSpeed = -aMaxSpeed;
        }

        if (aCurrentJumpStatus == aNOT_JUMPING && aKeyUp) {
            aCurrentJumpStatus = aRISING;
            aYSpeed -= aJumpForce;
            aJumpTimeCounter = aJumpTime;
        }
        if (aKeyUp && aCurrentJumpStatus == aRISING) {
            if (aJumpTimeCounter > 0) {
                aYSpeed -= aJumpForceModified;
                aJumpTimeCounter -= 0.1;
            } else {
                aCurrentJumpStatus = aFALLING;
            }
        }
        if (!aKeyUp) {
            aCurrentJumpStatus = aFALLING;
        }
        //Gravity effect on the player
        aYSpeed += 0.5;
        aHitBox.x = aX;
        aHitBox.y = aY;

    }


    boolean vIdleFlag = false;
    boolean vRunFlag = false;
    boolean vRisingFlag = false;
    boolean vFallingFlag = false;

    public BufferedImage animationChooser() {
        if (this.aCurrentStatus == aRUN) {
            if (!vRunFlag) {
                aCharacterRunning.start();
                vRunFlag = true;
            }
            aCharacterRunning.resume();
            aCharacterRunning.update(System.currentTimeMillis());
            aCharacterRunning.pause();
            return aCharacterRunning.aSprite;
        } else {
            if (!vIdleFlag) {
                aCharacterIDLE.start();
                vIdleFlag = true;
            }
            aCharacterIDLE.resume();
            aCharacterIDLE.update(System.currentTimeMillis());
            aCharacterIDLE.pause();
            return aCharacterIDLE.aSprite;
        }

    }


    /**
     * Draw function for the player
     * draw the Buffered Image comming from the animationChooser method
     * if aDirection = -1 draw the sprite to the left else draw the sprite to the right
     * @param g  the graphics
     */
    public void draw(Graphics g) {
        BufferedImage vImage = this.animationChooser();
        if (aDirection == -1) {
            g.drawImage(vImage, aX + aWidth, aY, -aWidth, aHeight, null);
        } else {
            g.drawImage(vImage, aX, aY, aWidth, aHeight, null);
        }
    }
}

