
/**
 * class to manage all of the key press
 *
 * @author Adrien PELFRESNE
 * @version beta1
 */


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class KeyChecker extends KeyAdapter
{
    
    GamePanel aPanel;
    
    
    public KeyChecker(final GamePanel pPanel){
        this.aPanel = pPanel;
    }
    
    @Override public void keyPressed(KeyEvent pE){
        aPanel.keyPressed(pE);
    }
    
    @Override public void keyReleased(KeyEvent pE) {
        aPanel.keyReleased(pE);
    }
    
    
   
}
