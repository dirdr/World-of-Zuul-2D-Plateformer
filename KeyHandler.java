import java.awt.event.KeyEvent;

public class KeyHandler {

    public static boolean[] prevKeyState = new boolean[10];
    public static boolean[] keyState= new boolean[10];

    public static final int aENTER = 0;
    public static final int aECHAP = 1;
    public static final int aUP = 2;
    public static final int aDOWN = 3;
    public static final int aLEFT = 4;
    public static final int aRIGHT = 5;
    public static final int aATTACK = 6;


    public static void set(final int pI, boolean pBool) {
        if(pI == KeyEvent.VK_Z) keyState[aUP] = pBool;
        else if(pI == KeyEvent.VK_Q) keyState[aLEFT] = pBool;
        else if(pI == KeyEvent.VK_S) keyState[aDOWN] = pBool;
        else if(pI == KeyEvent.VK_D) keyState[aRIGHT] = pBool;
        else if(pI == KeyEvent.VK_J) keyState[aATTACK] = pBool;
        else if(pI == KeyEvent.VK_ENTER) keyState[aENTER] = pBool;
        else if(pI == KeyEvent.VK_ESCAPE) keyState[aECHAP] = pBool;
    }

    /**
     * update the input (the keyPressed become a previous key pressed)
     */
    public static void update() {
        for(int i = 0; i < 10; i++) {
            prevKeyState[i] = keyState[i];
        }
    }

    /**
     *
     * @param keyCode (key code)
     * @return a boolean value depending if the key specified with the key code is pressed or not
     */
    public static boolean isPressed(final int keyCode) {
        return keyState[keyCode] && !prevKeyState[keyCode];
    }


}
