import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface implements ActionListener {

    private GameEngine aEngine;
    private MainFrame aMainFrame;
    private JTextField aEntryField;
    private JTextArea aLog;
    private GamePanel aGamePanel;

    public UserInterface(final GameEngine pGameEngine) {
        this.aEngine = pGameEngine;
        this.init();
    }

    public void init()
    {
        this.aMainFrame = new MainFrame();
        this.aMainFrame.setSize(GamePanel.aWIDTH, GamePanel.aHEIGHT);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.aMainFrame.setLocation((int) (screenSize.getWidth() / 2 - aMainFrame.getSize().getWidth() / 2), (int) (screenSize.getHeight() / 2 - aMainFrame.getSize().getHeight() / 2));
        this.aMainFrame.setResizable(false);
        this.aMainFrame.setTitle("NOM DU JEU A DEFINIR");
        this.aMainFrame.setVisible(true);
        this.aMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.aLog = new JTextArea();
        this.aLog.setEditable(false);
        JScrollPane vListScroller = new JScrollPane(this.aLog);
        vListScroller.setPreferredSize(new Dimension(200, 200));
        vListScroller.setMinimumSize(new Dimension(100, 100));
        //create the gamePanel
        this.aGamePanel = new GamePanel();
        //set the game Panel location
        this.aGamePanel.setLocation(0, 0);
        //set the panel size by calling the MainFrame (JFrame) getSize() method
        this.aGamePanel.setSize(this.aMainFrame.getSize());
        //set the panel to be visible
        this.aGamePanel.setVisible(true);
        //add the Panel to the Frame (JFrame)
        this.aMainFrame.add(this.aGamePanel);

    }

    /**
     * print a message into the aLog Text Area
     * @param pText the text to print
     */
    public void println(final String pText)
    {
        //call the print method and jump a line
        this.print(pText + "\n");
    }

    /**
     * print a message into the aLog Text Area
     * @param pText the text to print
     */
    public void print(final String pText)
    {
        this.aLog.append(pText);
        this.aLog.setCaretPosition(this.aLog.getDocument().getLength());
    }

    /**
     * enable or disable input in the entry field
     * @param pChoice On (true) or off (false)
     */
    public void enable (final boolean pChoice)
    {
        this.aEntryField.setEditable(pChoice);
        if (!pChoice) { //disable
            this.aEntryField.getCaret().setBlinkRate(0);
            this.aEntryField.removeActionListener(this);
        }
    } //enable()

    /**
     * Implemented method from the ActionListener interface
     * @param e the ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        this.processCommand();
    }

    /**
     * A command has been entered. Read the command and do whatever is
     * necessary to process it.
     */
    private void processCommand()
    {
        //get the input from the JText field
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText("");
        //pass the input into the interpret command method
        this.aEngine.interpretCommand(vInput);
    } //process command
}
