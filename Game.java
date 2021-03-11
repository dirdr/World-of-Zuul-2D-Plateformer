import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Game {


    private Room aCurrentRoom;
    private MainFrame aFrame;


    private void createRooms() {
        //creating the differents rooms
        Room vMainEntrance = new Room("Main entrance of the castle, the spawn for jacky!");
        Room vMainRoomEnigma = new Room("Main room, the place whre you should bring together the 4 differents key");
        Room vEnigmaAnnexRoom1 = new Room("The room where you should get the red key");
        Room vEnigmaAnnexRoom2 = new Room("The room where you should get the blue key");
        Room vEnigmaAnnexRoom3 = new Room("The room where you should get the yellow key");
        Room vEnigmaAnnexRoom4 = new Room("the room where you should get the green key");
        Room vMainRoomPlateformAndEnnemies = new Room("pay attention to these dangerous ennemies");
        Room vFinalRoomBoss = new Room("beating the boss will make you get back the (golden) key from your home");


        //set the plan, relation beetwin the differents room/location on the map
        vMainEntrance.setExits(vMainRoomEnigma, null, null, null);
        vMainRoomEnigma.setExits(vMainRoomPlateformAndEnnemies, vMainEntrance, vEnigmaAnnexRoom1, vEnigmaAnnexRoom3);
        vEnigmaAnnexRoom1.setExits(null, null, vEnigmaAnnexRoom2, vMainRoomEnigma);
        vEnigmaAnnexRoom2.setExits(null, null, null, vEnigmaAnnexRoom1);
        vEnigmaAnnexRoom3.setExits(null, null, vMainRoomEnigma, vEnigmaAnnexRoom4);
        vEnigmaAnnexRoom4.setExits(null, null, vEnigmaAnnexRoom3, null);
        vMainRoomPlateformAndEnnemies.setExits(vFinalRoomBoss, vMainRoomPlateformAndEnnemies, null, null);
        vFinalRoomBoss.setExits(null, vMainRoomPlateformAndEnnemies, null, null);

        //set the current room (spawn)
        this.aCurrentRoom = vMainEntrance;
    }

    public Game() {
        this.createRooms();
        this.play();
    }



    public void play() {
        aFrame = new MainFrame();
        aFrame.setSize(900, 900);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        aFrame.setLocation((int) (screenSize.getWidth() / 2 - aFrame.getSize().getWidth() / 2), (int) (screenSize.getHeight() / 2 - aFrame.getSize().getHeight() / 2));
        aFrame.setResizable(false);
        aFrame.setTitle("nom du jeu a definir");
        aFrame.setVisible(true);
    }


    //commit


} // Game
