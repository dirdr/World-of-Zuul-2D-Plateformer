import java.awt.*;
import java.util.ArrayList;

public class StateManager {

    private ArrayList<State> aStates;

    private int aCurrentState;

    private static final int aMENU_STATE = 0;
    private static final int aFIRST_LEVEL = 1;



    public StateManager() {
        //initialize the field (ArrayList of state)
        aStates = new ArrayList<State>();
        aCurrentState = aMENU_STATE;
        aStates.add(new Menu(this));
        aStates.add(new FirstLevel(this));
    }


    public void setState(final int pState) {
        //change the aCurrentState variable
        this.aCurrentState = pState;
        //initialize the new state
        this.aStates.get(this.aCurrentState).init();
    }

    public void update() {
        this.aStates.get(this.aCurrentState).update();
    }

    public void paint(Graphics2D g) {
        this.aStates.get(this.aCurrentState).paint(g);
    }
}
