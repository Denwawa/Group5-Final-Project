/*
 * IST 240 - Deliverable 1
 * Group 5 Members: Theodore Nguyen, Riyank Goel, Nicholas Marzullo
 * Professor Choman
 */
package group5.pkgfinal.project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author theodore
 */
public class MainMap extends JPanel implements ActionListener {
    
    //options class
    Options options;
    //images of characters
    ImageIcon sourceLionImage = new ImageIcon("images/Nittany_Lion.jpg");
    ImageIcon sourceFootballImage = new ImageIcon("images/football.jpg");
    ImageIcon sourceStudentImage = new ImageIcon("images/student.jpg");
    
    //back to the menu button
    JButton backButton;

    //Main Penn State map Image
    ImageIcon sourceImage1 = new ImageIcon("images/Penn_State_Map.jpg");
    Image myImage1 = sourceImage1.getImage();
    
    ImageIcon worldCampusImage = new ImageIcon("images/WorldCampus.jpg");

    //Game Panels to be added to map
    JButton univParkGame,
            worldCampGame,
            berksGame,
            scrantonGame,
            fayetteGame,
            montAltoGame,
            gameOverGame;

    //Rectangles for intersection. Used for when the player intersects with the campus.
    Rectangle univParkRctngle, wrldCmpRctngle, brksRctngle, scrantonRctngle, fytteRctngle,montAltoRctngle, gameOverRctngle;

    //adds the player
    JButton player;
    Rectangle playerRctngle;
    int vertical, horizontal;
    
    GameScore gameScore;

    //Game Classes
    UniversityParkGame universityPark;

    //constructor
    public MainMap() {

        //sets a nul layout to add the buttons 
        super();
        InitialSetUpForMainMap();
        CreateGamesThatWillBeSwapped();

    }

    //The Initial Setup when the player goes to the Main Map
    public void InitialSetUpForMainMap() {
        //sets a nul layout to add the buttons 
        setBackground(Color.white);
        setLayout(null);

        //adds back button to map
        backButton = new JButton("click here to go back to the Main Menu");
        add(backButton);
        backButton.setBounds(new Rectangle(500, 10, 300, 30));

        //Player
        player = new JButton(sourceStudentImage);
        add(player);
        player.setBounds(new Rectangle(horizontal, vertical, 70, 150));

        //University Park
        univParkGame = new JButton("UniversityPark");
        univParkGame.setBackground(Color.blue);
        add(univParkGame);
        univParkGame.setBounds(new Rectangle(575, 365, 30, 30));

        //World Campus
 
        worldCampGame = new JButton(worldCampusImage);
        worldCampGame.setText("World Campus");
        worldCampGame.setHorizontalTextPosition(AbstractButton.CENTER);
        add(worldCampGame);
        worldCampGame.setBounds(new Rectangle(600, 100, 100, 100));

        //Berks Campus
        berksGame = new JButton("Berks Campus");
        berksGame.setBackground(Color.pink);
        add(berksGame);
        berksGame.setBounds(new Rectangle(935, 475, 30, 30));

        //Scranton Campus
        scrantonGame = new JButton("Scranton Campus");
        scrantonGame.setBackground(Color.yellow);
        add(scrantonGame);
        scrantonGame.setBounds(new Rectangle(980, 215, 30, 30));

        //Fayette Campus
        fayetteGame = new JButton("Fayette Campus");
        fayetteGame.setBackground(Color.red);
        add(fayetteGame);
        fayetteGame.setBounds(new Rectangle(217, 580, 30, 30));
        
        //Mont Alto Campus
        montAltoGame = new JButton("Mont Alto");
        montAltoGame.setBackground(Color.orange);
        add(montAltoGame);
        montAltoGame.setBounds(new Rectangle(632, 605, 30, 30));


        //Game Over Button
        gameOverGame = new JButton("Game Over");
        gameOverGame.setBackground(Color.gray);
        add(gameOverGame);
        gameOverGame.setBounds(new Rectangle(1050, 50, 100, 100));
        gameOverGame.addActionListener(this);
        

        setFocusable(true);
        requestFocusInWindow();
    }

    public void movePlayerRight() {
        horizontal += 30;
        player.setBounds(horizontal, vertical, 70, 150);
    }

    public void movePlayerLeft() {
        horizontal -= 30;
        player.setBounds(horizontal, vertical, 70, 150);
    }

    public void movePlayerUp() {
        vertical -= 30;
        player.setBounds(horizontal, vertical, 70, 150);

    }

    public void movePlayerDown() {
        vertical += 30;
        player.setBounds(horizontal, vertical, 70, 150);
    }

    public String intersectsWhichCampus() {
        if (ifIntersectsUnivParkGame()) {
            return "UnivPark";
        }
        if (ifIntersectsWorldCampusGame()) {
            return "World";
        }
        if (ifIntersectsBerksGame()) {
            return "Berks";
        }
        if (ifIntersectsScrantonGame()) {
            return "Scranton";
        }
        if (ifIntersectsFayetteGame()) {
            return "Fayette";
        }
        if (ifIntersectsMontAltoGame()) {
            return "Mont Alto";
        }
        return "Nothing";
    }

    public boolean ifIntersectsUnivParkGame() {
        univParkRctngle = univParkGame.getBounds();
        playerRctngle = player.getBounds();

        return playerRctngle.intersects(univParkRctngle);
    }

    public boolean ifIntersectsWorldCampusGame() {
        wrldCmpRctngle = worldCampGame.getBounds();
        playerRctngle = player.getBounds();

        return playerRctngle.intersects(wrldCmpRctngle);
    }

    public boolean ifIntersectsBerksGame() {
        brksRctngle = berksGame.getBounds();
        playerRctngle = player.getBounds();

        return playerRctngle.intersects(brksRctngle);
    }

    public boolean ifIntersectsScrantonGame() {
        scrantonRctngle = scrantonGame.getBounds();
        playerRctngle = player.getBounds();

        return playerRctngle.intersects(scrantonRctngle);
    }

    public boolean ifIntersectsFayetteGame() {
        fytteRctngle = fayetteGame.getBounds();
        playerRctngle = player.getBounds();

        return playerRctngle.intersects(fytteRctngle);
    }
    
    public boolean ifIntersectsMontAltoGame() {
        montAltoRctngle = montAltoGame.getBounds();
        playerRctngle = player.getBounds();

        return playerRctngle.intersects(montAltoRctngle);
    }
    
    //change to Penn State Nittany Lion for Player
    public void ChangeToLionIcon(){
        remove(player);
        player = new JButton(sourceLionImage);
        add(player);
        player.setBounds(new Rectangle(horizontal, vertical, 70, 150));
    }
    //change to Barkley for Player
    public void ChangeToFootballIcon(){
        remove(player);
        player = new JButton(sourceFootballImage);
        add(player);
        player.setBounds(new Rectangle(horizontal, vertical, 70, 150));
    }
    
    //change to Penn State Nittany Lion for Player
    public void ChangeToStudentIcon(){
        remove(player);
        player = new JButton(sourceStudentImage);
        add(player);
        player.setBounds(new Rectangle(horizontal, vertical, 70, 150));
    }
    
    //Put your Game Class here so they can be used to switch between panels
    public void CreateGamesThatWillBeSwapped() {
        universityPark = new UniversityParkGame();
        universityPark.backToMap.addActionListener(this);//adds a back button to return to the main menu
    }
    


    //Draws the penn state map
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(myImage1, 0, 0, this);
    }
    

    @Override
    public void actionPerformed(ActionEvent eventMap) {
        Object obj = eventMap.getSource();
        

    }


}
