 

public class Command
{
    // the first command
    private final String aCommandWord;
    // the complementary command to the first command
    private final String aSecondWord;
    
    /**
     * Command's class Constructor 
     */
    public Command(final String pCommandWord,final String pSecondWord)
    {
        this.aCommandWord = pCommandWord;
        this.aSecondWord = pSecondWord;
    } //Command | Constructor
    
    /**
     * getter for the private field aCommandWord
     */
    public String getCommandWord() 
    {
        return this.aCommandWord;
    } //getCommandWord
    /**
     * getter for the private field aSecondWord
     */
    public String getSecondWord()
    {
        return this.aSecondWord;
    } //getSecondWord
    /**
     * Check if the command have a second word
     * @return true if command have one else  false
     */
    public boolean hasSecondWord()
    {
        return this.aSecondWord != null;
    } //hasSecondWord()
    /**
     * check if the command is Unknown 
     * return true if the first word is null else return false
     */
    public boolean isUnknown()
    {
        return this.aCommandWord == null;
    }
    
    
} // Command
