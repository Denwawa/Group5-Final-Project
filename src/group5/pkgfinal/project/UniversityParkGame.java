package group5.pkgfinal.project;

import group5.pkgfinal.project.GameScore;
import group5.pkgfinal.project.MainMap;
import group5.pkgfinal.project.XML_240;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

/**
 *
 * @author theodore nguyen Class: IST 240 Professor Choman Assignment: L09: Lab
 * Assignment - Java: Click Me Game
 */
public class UniversityParkGame extends JPanel implements KeyListener, ActionListener {

    ImageIcon sourceUniversityParkImage1 = new ImageIcon("images/UniversityPark.jpg");
    Image universityParkImage = sourceUniversityParkImage1.getImage();

    JButton b1;
    int universityParkNumScore = 0;

    Timer buttonTimer;
    int delay = 0;

    Boolean gameDone = true;
    Boolean gameStart = false;

    int boxWidth = 100;
    int boxHeight = 75;

    JProgressBar pbVertical;
    int j = 60;

    JButton backToMap;
    XML_240 universityParkXML;
    String xmlFile, theme;
    JLabel universityParkScore;

    GameScore gameScore;
    MainMap mainMap;

    public UniversityParkGame(GameScore gameScore, JLabel score, JLabel recentPlays, MainMap mainMap) {
        super();
        setBackground(Color.white);
        setLayout(null);
        b1 = new JButton("click me");
        add(b1);
        b1.setBounds(new Rectangle(50, 50, boxWidth, boxHeight));
        this.gameScore = gameScore;
        this.universityParkScore = score;
        //adds a back button to the game
        backToMap = new JButton("click here to go back to the Map");
        add(backToMap);
        backToMap.setBounds(new Rectangle(500, 10, 300, 30));

        pbVertical = new JProgressBar(JProgressBar.VERTICAL, 0, 60);// 60 the maximun number of intervals that th progress bar will show
        pbVertical.setStringPainted(true);
        add(pbVertical);
        pbVertical.setBounds(new Rectangle(1050, 0, 50, 600));

        //adds keylistener
        setFocusable(true);
        addKeyListener(this);
        requestFocusInWindow();

        //--Timer--
        delay = 1000; //1 second or 1000 milliseconds
        buttonTimer = new Timer(delay, this);

        //Put this here temporarily
        //This game is broken so I wanted to see if the game would appear
        gameScore.increaseGameComplete();//add this to your code so that the game knows when to add the "world campus" to the map.
        if (gameScore.gameComplete == 5) {//needs to reach 5 before the WorldCampus is revealed
            mainMap.showWorldCampus();
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //paintComponent will be useful in this lab.
        //read more about it in the paiting the screen lesson 
        //and also the keyboard listener method
        universityParkScore.setText("Score: " + gameScore.score);
        g.drawString("Press Spacebar to start the game", 10, 540);
        g.drawString("You have 60 seconds to keep clicking on the button to score", 10, 560);
        g.drawImage(universityParkImage, 0, 0, this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object obj = event.getSource();

        if (obj == buttonTimer) {
            if (j >= 0) {
                pbVertical.setValue(j);
                pbVertical.setValue(j);
                pbVertical.setString("" + j);
                j = j - 1;

                remove(b1);
                validate();
                repaint();
                delay = delay - 10;
                buttonTimer.setDelay(delay);

                add(b1);
                b1.setBounds(createtBox(boxWidth, boxHeight));
            } else {
                gameStart = false;
                gameDone = true;
                remove(b1);
                validate();
                repaint();
                add(b1);
                b1.setBounds(new Rectangle(500, 150, 300, 300));
                b1.setText("GAME OVER --- SCORE " + universityParkNumScore);

            }

        }

        if (obj == b1 && gameStart == true) {

            universityParkNumScore++;
            buttonTimer.setDelay(delay);
            remove(b1);
            validate();
            repaint();

            add(b1);
            boxWidth = boxWidth - (int) ((float) boxWidth / 10f);
            boxHeight = boxHeight - (int) ((float) boxHeight / 10f);
            b1.setBounds(createtBox(boxWidth, boxHeight));

        }

    }

    //returns a random number in the range of the panel.
    public int ranCordinate(int x, int y) {
        return (int) ((Math.random() * (y - x)) + x);
    }

    //creates box with random cordinates.
    public Rectangle createtBox(int width, int height) {

        boxWidth = width;
        boxHeight = height;

        int x = ranCordinate(100, 950);
        int y = ranCordinate(100, 500);

        return (new Rectangle(x, y, boxWidth, boxHeight));
    }

}

//    public void createClickMeIcon(String inputTheme) {
//        theme = inputTheme;
//        if (theme == "Math") {
//            xmlFile = "UniversityParkGameMath.xml";
//        } else if (theme == "Sports") {
//            xmlFile = "UniversityParkGameSports.xml";
//        } else if (theme == "Java") {
//            xmlFile = "UniversityParkGameJava.xml";
//        }
//
//        if (theme != "") {
//            universityParkXML.openReaderXML(xmlFile);
//
//            String gameThemeName = (String) universityParkXML.ReadObject();
//            ImageIcon gameImage = new ImageIcon(gameThemeName);
//            b1 = new JButton(gameImage);
//            b1.setBounds(100, 100, buttonWidth, buttonHeight);
//            b1.setEnabled(false);
//            add(b1);
//        }
//
//    }

