/**
 * Décrivez votre classe MainFrame ici.
 *
 * @author Adrien PELFRESNE
 * @version beta1
 */

import java.awt.Color;

public class MainFrame extends javax.swing.JFrame {
    public MainFrame() {

        GamePanel vPanel = new GamePanel();
        vPanel.setLocation(0, 0);
        vPanel.setSize(this.getSize());
        vPanel.setBackground(Color.LIGHT_GRAY);
        vPanel.setVisible(true);
        this.add(vPanel);

        addKeyListener(new KeyChecker(vPanel));


    }

}
