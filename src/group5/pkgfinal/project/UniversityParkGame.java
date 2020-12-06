package group5.pkgfinal.project;

import group5.pkgfinal.project.GameScore;
import group5.pkgfinal.project.MainMap;
import group5.pkgfinal.project.XML_240;
import java.awt.Color;
import java.awt.Font;
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

    Boolean gameDone = false;
    Boolean gameStart = false;
    Boolean gameEnter = false;

    int boxWidth = 100;
    int boxHeight = 75;

    JProgressBar pbVertical;
    int j = 30;//30 seconds

    JButton backToMap;
    XML_240 universityParkXML;
    String xmlFile, theme;
    JLabel universityParkScore;

    GameScore gameScore;
    MainMap mainMap;

    Boolean scored;
    JLabel funFact;

    public UniversityParkGame(GameScore gameScore, JLabel score, JLabel recentPlays, MainMap mainMap) {
        super();
        setBackground(Color.white);
        setLayout(null);
        universityParkXML = new XML_240();// creates the 240 class that reads and writes XML
        this.gameScore = gameScore;
        this.universityParkScore = score;
        this.mainMap = mainMap;
        createClickMeIcon("Math");
        //adds a back button to the game
        backToMap = new JButton("click here to go back to the Map");
        add(backToMap);
        backToMap.setBounds(new Rectangle(500, 10, 300, 30));
        
        //Adds a fact about the campus to the panel
        funFact = new JLabel("Fun Fact! Beaver Stadium is the 4th largest stadium in the world!");
        add(funFact);
        funFact.setBounds(new Rectangle(10, 20, 500, 30));
        funFact.setFont(new Font("Century Gothic", Font.BOLD, 16));
        funFact.setForeground(Color.blue);

        pbVertical = new JProgressBar(JProgressBar.VERTICAL, 0, 30);// 60 the maximun number of intervals that th progress bar will show
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

    public void createClickMeIcon(String inputTheme) {
        theme = inputTheme;
        if (theme == "Math") {
            xmlFile = "UniversityParkGameMath.xml";
        } else if (theme == "Sports") {
            xmlFile = "UniversityParkGameSports.xml";
        } else if (theme == "Java") {
            xmlFile = "UniversityParkGameJava.xml";
        }

        if (theme != "") {
            universityParkXML.openReaderXML(xmlFile);

            String gameThemeName = (String) universityParkXML.ReadObject();
            ImageIcon gameImage = new ImageIcon(gameThemeName);
            b1 = new JButton(gameImage);
            b1.setBounds(new Rectangle(50, 50, boxWidth, boxHeight));
        }
        b1.addActionListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();

        if (k == e.VK_SPACE && gameDone == true) {
            gameStart = true;
            buttonTimer.start();
            gameDone = false;

        }

    }

    public void startGame() {
        if (gameDone == false) {
            gameStart = true;
            buttonTimer.start();
        }
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
                buttonTimer.stop();
                gameStart = false;
                gameDone = true;
                remove(b1);
                validate();
                repaint();
                add(b1);
                b1.setBounds(new Rectangle(500, 150, 300, 300));
                b1.setText("GAME OVER --- SCORE " + universityParkScore);
                scored = true;//tells the main game that this game's score has been accoutned for
                mainMap.univParkGame.setBackground(Color.red);//sets the color to red on the main map
                gameScore.increaseGameComplete();//add this to your code so that the game knows when to add the "world campus" to the map.
                gameScore.addToList("University Park Game");
                if (gameScore.gameComplete == 5) {//needs to reach 5 before the WorldCampus is revealed
                    mainMap.showWorldCampus();
                }

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
            gameScore.increaseScore(1);

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

    public void placeBox() {
        remove(b1);
        validate();
        repaint();
    }

    public void gameEnter() {
        gameEnter = true;
    }

    public void gameLeave() {
        gameEnter = false;
    }

}
