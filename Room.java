 import java.util.HashMap;
 import java.util.Set;

public class Room
{
    private String aDescription;
    private HashMap<String, Room> aExits;
    
    /**
     * Room Class Constructor
     */
    public Room(final String pDescription) 
    {
        this.aDescription = pDescription;
        aExits = new HashMap<String, Room>();
    } //Room() | Constructor
    
    /**
     * get the current room Long Description, including the Description of the room + the avaible exits
     */
    public String getLongDescription() 
    {
        return "You are " + aDescription + ".\n" + getExitString();
    } //getLongDescription
     
    
    /**
     * @param a Direction 
     * @return the Room wich is located in the  @param Direction 
     */
    public Room getExit(final String pDirection) 
    {
        Room vExit = aExits.get(pDirection);
        return vExit;
    } //getExit()
    
    /**
     * @param pDirection (the users specified direction), pVoisin, (the Room next to the Direction)
     * the setExit method assign the room wich is located next to the specified direction
     */
    public void setExit(final String pDirection,final Room pVoisin) 
    {
        aExits.put(pDirection, pVoisin);
    } //setExit()
    
    /**
     * Get all the Exit by (for each) in the Hash map keySet
     */
    public String getExitString()
    {
        
        String vReturn= "Exits:";
        for(String vExit : this.aExits.keySet())
        {
            vReturn+= " "+vExit;
        }
        return vReturn;
       
    } //getExitString()
    
    /**
     * getter for the private field aDescription 
     */
    public String getDescription()
    {
        return this.aDescription;
    } //getDescription()
    
  
    
} // Room
