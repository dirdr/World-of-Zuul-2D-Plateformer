import java.awt.*;

public class Entity {

    protected Map aMap;
    protected int aTileSize;
    protected double xMap;
    protected  double yMap;

    protected double aX;
    protected double aY;

    protected double aDX;
    protected double aDY;

    protected int aWidth;
    protected int aHeight;

    //HitBox
    protected int aRows;
    protected int aColumns;



    public boolean isIntersected(Entity pSecondEntity) {
           return true;
    }

   



}
