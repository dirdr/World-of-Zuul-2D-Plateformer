import java.awt.event.KeyEvent;

public class KeyHandler {

    public static boolean[] aPrevKeyState = new boolean[8];
    public static boolean[] aKeyState= new boolean[8];

    public static final int aENTER = 0;
    public static final int aECHAP = 1;
    public static final int aUP = 2;
    public static final int aDOWN = 3;
    public static final int aLEFT = 4;
    public static final int aRIGHT = 5;
    public static final int aATTACK = 6;


    public static void set(int pI, boolean pBool) {

        if(pI == KeyEvent.VK_ENTER) aKeyState[aENTER] = pBool;
        else if(pI == KeyEvent.VK_ESCAPE) aKeyState[aECHAP] = pBool;
        else if(pI == KeyEvent.VK_Z) aKeyState[aUP] = pBool;
        else if(pI == KeyEvent.VK_S) aKeyState[aDOWN] = pBool;
        else if(pI == KeyEvent.VK_Q) aKeyState[aLEFT] = pBool;
        else if(pI == KeyEvent.VK_D) aKeyState[aRIGHT] = pBool;
        else if(pI == KeyEvent.VK_F) aKeyState[aATTACK] = pBool;
    }


    /**
     * update the input (the keyPressed become a previous key pressed)
     */
    public static void update() {
        System.arraycopy(aKeyState, 0, aPrevKeyState, 0, 8);
    }

    /**
     *
     * @param key key
     * @return a boolean value depending if the key specified with the int value passed as a parameter is pressed or not
     */
        public static boolean isPressed(int key) {
            return aKeyState[key] && !aPrevKeyState[key];
        }



}
