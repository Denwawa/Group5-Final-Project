/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group5.pkgfinal.project;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

/**
 *
 * University Park Game
 */
public class UniversityParkGame extends JPanel implements KeyListener, ActionListener {

    //Main Penn State map Image
    ImageIcon sourceUnivParkImage1 = new ImageIcon("images/University_Park.jpg");
    Image univParkImage = sourceUnivParkImage1.getImage();

    //back to the menu button
    JButton backToMap;

    JButton b1;
    GameScore gamescore;
    int limit = 0;
    int delay = 0;
    int score;

    JProgressBar pbVertical;
    Timer tim;

    int progressBarX = 1150, progressBarY = 0;
    int progressBarWidth = 50, progressBarHeight = 600;
    int scoreBarX = 10, scoreBarY = 520;
    int buttonX, buttonY;
    int buttonWidth = 200, buttonHeight = 100;

    int sizeDecrement = 2;

    JLabel universityParkScore;
    Boolean scored;

    XML_240 universityParkXML;
    String xmlFile, theme;

    GameScore gameScore;

    JLabel funFact;

    //constructor
    public UniversityParkGame(GameScore gameScore, JLabel score) {
        super();
        setBackground(Color.white);
        setLayout(null);

        theme = "";
        scored = false;
        universityParkScore = score;
        this.gameScore = gameScore;
        universityParkXML = new XML_240();// creates the 240 class that reads and writes XML
        createClickMeIcon("Math");

        // Progress Bar
        pbVertical = new JProgressBar(JProgressBar.VERTICAL, 0, 60);
        pbVertical.setBounds(progressBarX, progressBarY, progressBarWidth, progressBarHeight);
        pbVertical.setValue(60);
        pbVertical.setString("Progress Bar");
        pbVertical.setStringPainted(true);
        add(pbVertical);

        delay = 10000;
        tim = new Timer(delay, (ActionListener) this);
        setFocusable(true);
        addKeyListener((KeyListener) this);
        requestFocusInWindow();
        b1.addActionListener((ActionListener) this);

        backToMap = new JButton("click here to go back to the Map");
        add(backToMap);
        backToMap.setBounds(new Rectangle(500, 10, 300, 30));

        //Adds a fact about the campus to the panel
        funFact = new JLabel("Penn State’s Beaver Stadium is the 4th largest stadium in the world.");
        add(funFact);
        funFact.setBounds(new Rectangle(300, 615, 550, 30));
        funFact.setFont(new Font("Century Gothic", Font.BOLD, 16));
        funFact.setForeground(Color.blue);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(univParkImage, 0, 0, this);
        g.drawString("score = " + score/* + " " + buttonWidth + " " + buttonHeight */, 10, 520);
        g.drawString("Press Spacebar to start the game", 10, 540);
        g.drawString("You have 60 seconds to keep clicking on the button to score", 10, 560);
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        // get the key code which is pressed
        int key = ke.getKeyCode();

        // check whether the key is a space bar
        if (key == KeyEvent.VK_SPACE) {
            // enable the button
            b1.setEnabled(true);

            // Add a listener object
            ActionListener listener = new ActionListener() {
                // set the counter to 60
                int counter = 60;

                // define the action performed method
                public void actionPerformed(ActionEvent ae) {
                    // loop to get the position of the button to be placed randomly
                    do {
                        buttonX = (int) (Math.random() * (progressBarX));
                        buttonY = (int) (Math.random() * (scoreBarY));

                        // condition to check, so that the button is placed within the limitation of progress bar and the result
                    } while (!(buttonX >= 0 && buttonX + buttonWidth < progressBarX) || !(buttonY >= 0 && buttonY < scoreBarY - buttonHeight));

//                    if(buttonWidth > 40) {
//                        buttonWidth -= sizeDecrement;
//                        buttonHeight -= sizeDecrement;
//                    }
                    b1.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);

                    // set the progress bar value
                    pbVertical.setValue(counter);

                    // set the value to display in the progress bar
                    pbVertical.setString(String.valueOf(counter));

                    // if the counter is less than 1, stop the timer and display the message that the game is over
                    if (counter < 1) {
                        tim.stop();
                        JOptionPane.showMessageDialog(null, "Game Over..... Score :" + score);
                        gameScore.increaseGameComplete();//add this to your code so that the game knows when to add the "world campus" to the map.
                    } else {
                        // decrement the counter
                        counter--;
                    }
                }
            };

            // reinitialize the timer
            tim = new Timer(delay / 10, listener);
            tim.start();
        }
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
            b1.setBounds(100, 100, buttonWidth, buttonHeight);
            b1.setEnabled(false);
            add(b1);
        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object obj = event.getSource();
        if (obj == b1) {
            if (buttonHeight > 40) {
                buttonWidth -= sizeDecrement * 2;
                buttonHeight -= sizeDecrement;
            } else {
                buttonWidth -= sizeDecrement / 2;
            }
            score++;
            repaint();
        }
    }

}
