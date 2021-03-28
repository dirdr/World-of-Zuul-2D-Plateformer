import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Game {


    private Room aCurrentRoom;
    private Parser aParser;
    private MainFrame aFrame;
    
    
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
    
    /**
     * Game Class Constructor 
     */
    public Game()
    {
        this.createRooms();
        this.aParser = new Parser();
    } //Constructor | Game()
    
    /**
     * @param pCommand (user input transcribe as a command by the parser && the commandsWord class)
     * takes care of moving the character between rooms according to the direction entered by the user
     */
    private void goRoom(final Command pCommand) 
    {
       if (!pCommand.hasSecondWord()) {
           System.out.println("Go where ?");
           return;
       } else { 
           String vDirection = pCommand.getSecondWord();
           Room vNextRoom = this.aCurrentRoom.getExit(vDirection);     
           if (vNextRoom == null) {
               System.out.println("There is no door !");
           } else {
               this.aCurrentRoom = vNextRoom;
               System.out.println(this.aCurrentRoom.getLongDescription());

           }
           
       }
       
    } //goRoom()
    
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
       
    } //look()
    
    
    /**
     * Welcome message for the User
     */
    private void printWelcome() 
    {
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println(this.aCurrentRoom.getLongDescription());
        
    } //printWelcome()
   
    
    /**
     * displays the list of commands available if the user has typed "help". 
     */
    private void printHelp()
    {
       System.out.println("You are lost. You are alone.");
       System.out.println("You wander around at the university.");
       System.out.println("Your command words are:");
       System.out.println(aParser.getCommandList());
       
    } //printHelp()
    
    
    /**
     * @param pCommand (user input transcribe as a command by the parser && the commandsWord class) 
     * @return  true or false depending on user input
     */
    private boolean quit(final Command pCommand)
    {
        if (pCommand.hasSecondWord()){
            System.out.println("quit What??");
            return false;
        } else {
            return true;
        }
    } //quit()
    
    
    /**
     * @param pCommand (user input transcribe as a command by the parser && the commandsWord class)
     * @return true or false depending on user input
     * 
     */
    private boolean processCommand(final Command pCommand)
    {
        if (pCommand.isUnknown()) {
            System.out.println("don't know what you mean...");
        } else {
            String vCommandWord = pCommand.getCommandWord();
            String vSecondWord = pCommand.getSecondWord();
            switch(vCommandWord) {
                case "help":
                    printHelp();
                    return false;
                case "quit":
                    return quit(pCommand);
                case "go":
                    goRoom(pCommand);
                    return false;
                case "look":
                    look(pCommand);
                    return false;
                case "eat":
                    eat();
                    return false;
            } 
        }
        return false;
    } //processCommand()



    public void play() {
        aFrame = new MainFrame();
        aFrame.setSize(GamePanel.aWIDTH, GamePanel.aHEIGHT);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        aFrame.setLocation((int) (screenSize.getWidth() / 2 - aFrame.getSize().getWidth() / 2), (int) (screenSize.getHeight() / 2 - aFrame.getSize().getHeight() / 2));
        aFrame.setResizable(false);
        aFrame.setTitle("nom du jeu a definir");
        aFrame.setVisible(true);
        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        printWelcome();
        boolean vFinished = false;
        while (!vFinished) {
            Command vCommand = this.aParser.getCommand();
            vFinished = processCommand(vCommand);
            
        }
        System.out.println("Thank you for playing.  Good bye.");  
    }


    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }


    //commit


} // Game
