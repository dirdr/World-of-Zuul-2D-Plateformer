import java.awt.*;

public abstract class State {

    protected StateManager aStateManager;

    public abstract void paint(Graphics2D pG);
    public abstract void update();
    public abstract void init();
    public abstract void input();




}
