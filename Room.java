 

public class Room
{
    private String aDescription;
    public Room aNorthExit;
    public Room aSouthExit;
    public Room aWestExit;
    public Room aEastExit;
    
    public Room(final String pDescription) {
        this.aDescription = pDescription;
    }
    
    public String getDescription() {
        return this.aDescription;
    }
    
    public void setExits(final Room pNorthExit,final Room pSouthExit,final Room pWestExit,final Room pEastExit) {
        this.aNorthExit = pNorthExit;
        this.aSouthExit = pSouthExit;
        this.aWestExit = pWestExit;
        this.aEastExit = pEastExit;
    }
    
} // Room
