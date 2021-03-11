/**
 * Décrivez votre classe MainFrame ici.
 *
 * @author Adrien PELFRESNE
 * @version beta1
 */


import java.io.IOException;

public class MainFrame extends javax.swing.JFrame {




    public MainFrame() throws IOException {

        GamePanel vPanel = new GamePanel();

        vPanel.setLocation(0, 0);
        vPanel.setSize(this.getSize());
        vPanel.setVisible(true);
        this.add(vPanel);
        addKeyListener(new KeyChecker(vPanel));

    }



}
