import java.awt.*;
import java.util.ArrayList;

public class StateManager {


    //Store all the game's states
    private ArrayList<State> aStates;

    //the aCurrentState int is the Current State the State manager is Handling with
    private int aCurrentState;

    //states

    public static final int aMENU_STATE = 0;
    public static final int aFIRST_LEVEL = 1;



    public StateManager() {
        //initialize the field (ArrayList of state)
        this.aStates = new ArrayList<State>();
        //add the Menu state to the ArrayList with the State Manager reference
        this.aStates.add(new Menu(this));
        //add the First level state to the ArrayList with the state manager reference
        this.aStates.add(new FirstLevel(this));
        //set the current state to the menu state
        this.setState(aMENU_STATE);
    }


    public void setState(final int pState) {
        //change the aCurrentState variable
        this.aCurrentState = pState;
        //initialize the new state
        this.aStates.get(this.aCurrentState).init();
    }

    /**
     * call the current state update method
     */
    public void update() {
        this.aStates.get(this.aCurrentState).update();
    }

    /**
     * call the current state paint method
     * @param g Graphics 2D
     */
    public void paint(Graphics2D g) {
        this.aStates.get(this.aCurrentState).paint(g);
    }


}
