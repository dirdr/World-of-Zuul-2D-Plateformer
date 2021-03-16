import java.awt.event.KeyEvent;

public class KeyHandler {

    public static boolean[] prevKeyState = new boolean[8];
    public static boolean[] keyState= new boolean[8];

    public static final int aENTER = 0;
    public static final int aECHAP = 1;
    public static final int aUP = 2;
    public static final int aDOWN = 3;
    public static final int aLEFT = 4;
    public static final int aRIGHT = 5;
    public static final int aATTACK = 6;


    public static void set(int pI, boolean pBool) {

        if(pI == KeyEvent.VK_ENTER) keyState[aENTER] = pBool;
        else if(pI == KeyEvent.VK_ESCAPE) keyState[aECHAP] = pBool;
        else if(pI == KeyEvent.VK_Z) keyState[aUP] = pBool;
        else if(pI == KeyEvent.VK_S) keyState[aDOWN] = pBool;
        else if(pI == KeyEvent.VK_Q) keyState[aLEFT] = pBool;
        else if(pI == KeyEvent.VK_D) keyState[aRIGHT] = pBool;
        else if(pI == KeyEvent.VK_F) keyState[aATTACK] = pBool;
    }


    /**
     * update the input (the keyPressed become a previous key pressed)
     */
    public static void update() {
        for(int i = 0; i < 8; i++) {
            prevKeyState[i] = keyState[i];
        }
    }

    /**
     *
     * @param key key
     * @return a boolean value depending if the key specified with the key  is pressed or not
     */
        public static boolean isPressed(int key) {
            return keyState[key] && !prevKeyState[key];
        }



}
