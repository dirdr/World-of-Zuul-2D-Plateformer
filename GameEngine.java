public class GameEngine {

    private UserInterface aGui;
    private Room aCurrentRoom;
    private Parser aParser;

    public GameEngine()
    {
        this.aParser = new Parser();
        this.createRooms();
    }

    /**
     * set the aGui field to be the User interface passed in parameter
     * @param pUserInterface the Gui
     */
    public void setGUI(final UserInterface pUserInterface )
    {
        this.aGui = pUserInterface;
        this.printWelcome();
    }

    /**
     * Useless method for the moment, gonna be improve soon with some powerUp for the main character
     */
    private void eat()
    {
        System.out.println("Im not hungry anymore");
    } //eat()

    /**
     * @param pCommand (user input transcribe as a command by the parser && the commandsWord class)
     * if the command has no second word, displays the description of the current room
     */
    private void look(final Command pCommand)
    {
        if(pCommand.getSecondWord() == null) {
            System.out.println(this.aCurrentRoom.getDescription());
        } else {
            System.out.println("i don't know how to look at something particular yet");
        }

    }

    private void printWelcome()
    {
        this.aGui.print( "\n" );
        this.aGui.println( "Welcome to the World of Zuul!" );
        this.aGui.println( "World of Zuul is a new, incredibly boring adventure game." );
        this.aGui.println( "Type 'help' if you need help." );
        this.aGui.print( "\n" );
        this.aGui.println( this.aCurrentRoom.getLongDescription() );
    }

    /**
     * creating all the room used in my game
     */
    private void createRooms()
    {
        //creating the differents rooms
        Room vMainEntrance = new Room("in the Main entrance of the castle, the spawn for jacky!");
        Room vMainRoomEnigma = new Room("in the Main room, the place whre you should bring together the 4 differents key");
        Room vEnigmaAnnexRoom1 = new Room("in The room where you should get the red key");
        Room vEnigmaAnnexRoom2 = new Room("in The room where you should get the blue key");
        Room vEnigmaAnnexRoom3 = new Room("in The room where you should get the yellow key");
        Room vEnigmaAnnexRoom4 = new Room("in the room where you should get the green key");
        Room vMainRoomPlateformAndEnnemies = new Room("in the room where you should pay attention to these dangerous ennemies");
        Room vFinalRoomBoss = new Room("in this room, beating the boss will make you get back the (golden) key from your home");


        //set the plan, relation beetwin the differents room/location on the map

        vMainEntrance.setExit("Up", vMainRoomEnigma);

        //MainRoomEnigma
        vMainRoomEnigma.setExit("Up", vMainRoomPlateformAndEnnemies);
        vMainRoomEnigma.setExit("Down", vMainEntrance);
        vMainRoomEnigma.setExit("West", vEnigmaAnnexRoom1);
        vMainRoomEnigma.setExit("East", vEnigmaAnnexRoom3);

        //EnigmaAnnexRoom1
        vEnigmaAnnexRoom1.setExit("West", vEnigmaAnnexRoom2);
        vEnigmaAnnexRoom1.setExit("East", vMainRoomEnigma);


        //EnigmaAnnexRoom2
        vEnigmaAnnexRoom2.setExit("East", vEnigmaAnnexRoom1);

        //EnigmaAnnexRoom3
        vEnigmaAnnexRoom3.setExit("West", vMainRoomEnigma);
        vEnigmaAnnexRoom3.setExit("East", vEnigmaAnnexRoom4);


        //EnigmaAnnexRoom4
        vEnigmaAnnexRoom4.setExit("West", vEnigmaAnnexRoom4);


        //MainRoomPlateformAndEnnemies
        vMainRoomPlateformAndEnnemies.setExit("Up", vFinalRoomBoss);
        vMainRoomPlateformAndEnnemies.setExit("Down", vMainRoomEnigma);

        //vFinalRoomBoss
        vFinalRoomBoss.setExit("South", vMainRoomPlateformAndEnnemies);

        //set the current room (spawn)
        this.aCurrentRoom = vMainEntrance;
    }

    public void interpretCommand(final String pCommandLine) {
        this.aGui.println( "> " + pCommandLine);
        Command vCommand = this.aParser.getCommand(pCommandLine);

        if (vCommand.isUnknown()) {
            this.aGui.println("I don't know what you mean...");
            return;
        }

        String vCommandWord = vCommand.getCommandWord();

        switch (vCommandWord)
        {
            case "help":
                this.printHelp();
                break;
            case "go":
                this.goRoom(vCommand);
                break;
            case "quit":
                if (vCommand.hasSecondWord() ) {
                    this.aGui.println("Quit What");
                } else {
                    this.endGame();
                }
        }
    }

    private void printHelp()
    {
        this.aGui.println("You are lost...");
        this.aGui.println("Your command words are: " + this.aParser.getCommandString());
    }

    private void goRoom(final Command pCommand )
    {
        if (! pCommand.hasSecondWord()) {
            this.aGui.println("Go Where") ;
            return;
        }

        String vDirection = pCommand.getSecondWord();

        Room vNextRoom = this.aCurrentRoom.getExit(vDirection);

        if (vNextRoom == null) {
            this.aGui.println("There is no door!");
        } else {
            this.aCurrentRoom = vNextRoom;
            this.aGui.println(this.aCurrentRoom.getLongDescription());
        }

    }



    private void endGame()
    {
        this.aGui.println("Merci d'avoir joué! au-revoir");
        this.aGui.enable(false);
    }


}
