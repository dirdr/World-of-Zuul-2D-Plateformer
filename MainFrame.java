/**
 * The Main Frame Class is used to create the Main Frame
 * @author Adrien PELFRESNE
 * @version inter1
 */


public class MainFrame extends javax.swing.JFrame {


    /**
     * Create the JFrame on which the game is running
     */
    public MainFrame() {
        //create the gamePanel
        GamePanel vPanel = new GamePanel();
        //set the game Panel location
        vPanel.setLocation(0, 0);
        //set the panel size by calling the MainFrame (JFrame) getSize() method
        vPanel.setSize(this.getSize());
        //set the panel to be visible
        vPanel.setVisible(true);
        //add the Panel to the Frame (JFrame)
        this.add(vPanel);
    }

}
