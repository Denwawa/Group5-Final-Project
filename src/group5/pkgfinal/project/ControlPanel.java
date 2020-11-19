/*
 * IST 240 - Deliverable 1
 * Group 5 Members: Theodore Nguyen, Riyank Goel, Nicholas Marzullo
 * Professor Choman
 */
package group5.pkgfinal.project;

import java.awt.BorderLayout;
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
import javax.swing.JTextArea;
import javax.swing.Timer;

/**
 *
 * Main Controller for the entire program. Tells code which panel to display
 */
//
public class ControlPanel extends JPanel implements ActionListener, KeyListener {

    IntroPanel introPanel;

    JButton aboutButton,
            instructionsButton,
            optionsButton,
            playButton;

    //Menu Options
    Options optionsMenu;
    About aboutMenu;
    Instructions instructionsMenu;
    GameScore gameScore;

    //adds other panels to access
    MainMap mainMap;
    UniversityParkGame universityPark;
    ScrantonGame scranton;
    FayetteGame fayette;
    WorldCampusGame worldCampus;
    BerksGame berks;
    MontAltoGame montAlto;
    GameOver gameOver;

    Boolean themeSports,
            themeJava,
            themeMath;

    //Timer
    Timer tim;
    int delay = 0;
    int i = 0;
    JLabel timeCount = new JLabel("Timer will start after map is entered.");
    JLabel showScore = new JLabel("Score");
    JLabel recentPlays = new JLabel("Recent Plays: ");

    //constructor
    public ControlPanel() {
        super();
        gameScore = new GameScore();//keeps track of the games played
        InitialSetUpForControlPanel();
        createComponentsThatWillBeSwapped();
        setFocusable(true);
        addKeyListener(this);

    }

    public void resetGame() {
        gameScore = new GameScore();//keeps track of the games played
        InitialSetUpForControlPanel();
        createComponentsThatWillBeSwapped();
        setFocusable(true);
        addKeyListener(this);
        i = 0;

    }

    //Run this method to go back to the intro screen. 
    public void InitialSetUpForControlPanel() {
        //create the layout and graphic components
        //add listeners
        setBackground(Color.white);
        BorderLayout bl = new BorderLayout();
        setLayout(bl);

        //Adds the Buttons 
        IntroPanel introPanel = new IntroPanel();
        introPanel.setBounds(new Rectangle(0, 0, 1200, 700));
        this.aboutButton = introPanel.aboutButton;
        this.instructionsButton = introPanel.instructionsButton;
        this.optionsButton = introPanel.optionsButton;
        this.playButton = introPanel.playButton;
        addTimerAndGame();

        aboutButton.addActionListener(this);
        instructionsButton.addActionListener(this);
        optionsButton.addActionListener(this);
        playButton.addActionListener(this);
        add(introPanel);

        delay = 1000; //milliseconds
        tim = new Timer(delay, this);
    }

    //adds the timer to the panel.
    public void addTimerAndGame() {

        add(timeCount);
        timeCount.setBounds(new Rectangle(10, 600, 200, 30));
        timeCount.setForeground(Color.orange);
        add(showScore);
        showScore.setBounds(new Rectangle(10, 620, 200, 30));
        showScore.setForeground(Color.orange);
        add(recentPlays);
        recentPlays.setBounds(new Rectangle(10, 580, 500, 30));
        recentPlays.setForeground(Color.orange);

    }

    //Put your class here so they can be used to switch between panels
    public void createComponentsThatWillBeSwapped() {

        mainMap = new MainMap();
        mainMap.backButton.addActionListener(this);//adds a back button to return to the main menu
        mainMap.gameOverGame.addActionListener(this);//adds a game over screen button

        optionsMenu = new Options();
        optionsMenu.backButton.addActionListener(this);//adds a back button to return to the main menu
        optionsMenu.lionButton.addActionListener(this);//Allows user to click the button to switch characters
        optionsMenu.studentButton.addActionListener(this);//Allows user to click the button to switch characters
        optionsMenu.footballButton.addActionListener(this);//Allows user to click the button to switch characters
        optionsMenu.sportsButton.addActionListener(this);
        optionsMenu.mathButton.addActionListener(this);
        optionsMenu.javaButton.addActionListener(this);

        aboutMenu = new About();
        aboutMenu.backButton.addActionListener(this);//adds a back button to return to the main menu

        instructionsMenu = new Instructions();
        instructionsMenu.backButton.addActionListener(this);//adds a back button to return to the main menu

        //creates object of each panel for game and adds a back button
        universityPark = new UniversityParkGame(gameScore, showScore, recentPlays, mainMap);
        universityPark.backToMap.addActionListener(this);
        universityPark.b1.addActionListener(this);
        scranton = new ScrantonGame(gameScore, showScore, recentPlays, mainMap);
        scranton.backToMap.addActionListener(this);
        fayette = new FayetteGame(gameScore, showScore, recentPlays, mainMap);
        fayette.backToMap.addActionListener(this);
        worldCampus = new WorldCampusGame(gameScore, showScore, recentPlays);
        worldCampus.backToMap.addActionListener(this);
        berks = new BerksGame(gameScore, showScore, recentPlays, mainMap);
        berks.backToMap.addActionListener(this);
        montAlto = new MontAltoGame(gameScore, showScore, recentPlays, mainMap);
        montAlto.backToMap.addActionListener(this);
        gameOver = new GameOver();
        gameOver.backToMainMenu.addActionListener(this);

    }

    public void pickGame() {
        String campus = mainMap.intersectsWhichCampus();
        if (campus == "UnivPark") {
            remove(mainMap);
            add(universityPark);
            validate();
            repaint();
        } else if (campus == "Scranton") {
            remove(mainMap);
            add(scranton);
            validate();
            repaint();
        } else if (campus == "Berks") {
            remove(mainMap);
            add(berks);
            validate();
            repaint();

        } else if (campus == "Fayette") {
            remove(mainMap);
            add(fayette);
            validate();
            repaint();
        } else if (campus == "World") {
            remove(mainMap);
            add(worldCampus);
            validate();
            repaint();
        } else if (campus == "Mont Alto") {
            remove(mainMap);
            add(montAlto);
            validate();
            repaint();
        } else {
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object obj = event.getSource();

        //Goes to the About Menu
        if (obj == aboutButton) {
            removeAll();
            add(aboutMenu);
            validate();
            repaint();
        }
        //Goes back to the main menu
        if (obj == aboutMenu.backButton) {
            removeAll();
            InitialSetUpForControlPanel(); //rebuild the original ControlPanel again
            validate();
            repaint();
        }

        if (obj == optionsButton) {
            removeAll();
            add(optionsMenu);
            validate();
            repaint();
        }
        //Goes back to the main menu
        if (obj == optionsMenu.backButton) {
            removeAll();
            InitialSetUpForControlPanel(); //rebuild the original ControlPanel again
            validate();
            repaint();
        }
        if (obj == instructionsButton) {
            removeAll();
            add(instructionsMenu);
            validate();
            repaint();
        }
        //Goes back to the main menu
        if (obj == instructionsMenu.backButton) {
            removeAll();
            InitialSetUpForControlPanel(); //rebuild the original ControlPanel again
            validate();
            repaint();
        }

        //Goes to the Main Map through the play button and starts the timer
        if (obj == playButton) {

            removeAll();
            addTimerAndGame();//adds timer
            add(mainMap);
            validate();
            repaint();
            tim.start();//starts timer

        }
        //back to the intro panel from the main map
        if (obj == mainMap.backButton) {
            removeAll();
            InitialSetUpForControlPanel(); //rebuild the original ControlPanel again
            validate();
            repaint();
        }

        //All these if statementrs allow each game panel to return back to the main map
        if (obj == universityPark.backToMap) {
            remove(universityPark);
            add(mainMap);//returns and rebuilds the map
            validate();
            repaint();
        }

        if (obj == scranton.backToMap) {
            remove(scranton);
            add(mainMap);//returns and rebuilds the map
            validate();
            repaint();
        }
        if (obj == fayette.backToMap) {
            remove(fayette);
            add(mainMap);//returns and rebuilds the map
            validate();
            repaint();
        }
        if (obj == worldCampus.backToMap) {
            remove(worldCampus);
            add(mainMap);//returns and rebuilds the map
            validate();
            repaint();
        }
        if (obj == berks.backToMap) {
            remove(berks);
            add(mainMap);//returns and rebuilds the map
            validate();
            repaint();
        }
        if (obj == montAlto.backToMap) {
            remove(montAlto);
            add(mainMap);//returns and rebuilds the map
            validate();
            repaint();

        }
        if (obj == gameOver.backToMainMenu) {
            removeAll();
            InitialSetUpForControlPanel(); //rebuild the original ControlPanel again
            validate();
            repaint();

        }
        //if the user chooses to end the game
        if (obj == mainMap.gameOverGame) {
            resetGame();
            removeAll();
            tim.stop();
            // add(timeCount);
            //add(showScore);
            add(gameOver);
            showScore.setText("Score: ");
            timeCount.setText("Time: ");
            recentPlays.setText("Recent Plays: ");
            validate();
            repaint();

        }
        //Change player icon in the main map from the options menu. Also changes the color of the text for verificaiton
        if (obj == optionsMenu.lionButton) {
            mainMap.ChangeToLionIcon();
            optionsMenu.lionText.setForeground(Color.blue);
            optionsMenu.footballText.setForeground(Color.orange);
            optionsMenu.studentText.setForeground(Color.orange);

        }
        if (obj == optionsMenu.footballButton) {
            mainMap.ChangeToFootballIcon();
            optionsMenu.lionText.setForeground(Color.orange);
            optionsMenu.footballText.setForeground(Color.blue);
            optionsMenu.studentText.setForeground(Color.orange);
        }
        if (obj == optionsMenu.studentButton) {
            mainMap.ChangeToStudentIcon();
            optionsMenu.lionText.setForeground(Color.orange);
            optionsMenu.footballText.setForeground(Color.orange);
            optionsMenu.studentText.setForeground(Color.blue);
        }
        //Change theme in the options menu.
        if (obj == optionsMenu.sportsButton) {
            optionsMenu.currentTheme = "Sports";
            optionsMenu.sportsText.setForeground(Color.blue);
            optionsMenu.javaText.setForeground(Color.orange);
            optionsMenu.mathText.setForeground(Color.orange);

            //sets all the classes to the Sports theme by loading the proper xml file to the classes after the theme is selected.
            berks.createQuestions("Sports");
            fayette.createQuestions("Sports");
            scranton.createQuestions("Sports");
            montAlto.createQuestions("Sports");
            universityPark.createClickMeIcon("Sports");

        }
        if (obj == optionsMenu.mathButton) {
            optionsMenu.currentTheme = "Math";
            optionsMenu.sportsText.setForeground(Color.orange);
            optionsMenu.javaText.setForeground(Color.orange);
            optionsMenu.mathText.setForeground(Color.blue);

            //sets all the classes to the Math theme by loading the proper xml file to the classes after the theme is selected.
            berks.createQuestions("Math");
            fayette.createQuestions("Math");
            scranton.createQuestions("Math");
            montAlto.createQuestions("Math");
            universityPark.createClickMeIcon("Math");

        }
        if (obj == optionsMenu.javaButton) {
            optionsMenu.currentTheme = "Java";
            optionsMenu.sportsText.setForeground(Color.orange);
            optionsMenu.javaText.setForeground(Color.blue);
            optionsMenu.mathText.setForeground(Color.orange);

            //sets all the classes to the Java theme by loading the proper xml file to the classes after the theme is selected.
            berks.createQuestions("Java");
            fayette.createQuestions("Java");
            scranton.createQuestions("Java");
            montAlto.createQuestions("Java");
            universityPark.createClickMeIcon("Java");
        }
        //Increments Timer
        if (obj == tim) {
            i = i + 1;
            timeCount.setText("Time: " + i);
        }

        //Source code to lcick me game
        if (obj == universityPark.buttonTimer) {
            if (universityPark.j >= 0) {
                universityPark.pbVertical.setValue(universityPark.j);
                universityPark.pbVertical.setValue(universityPark.j);
                universityPark.pbVertical.setString("" + universityPark.j);
                universityPark.j = universityPark.j - 1;

                remove(universityPark.b1);
                validate();
                repaint();
                universityPark.delay = universityPark.delay - 10;
                universityPark.buttonTimer.setDelay(universityPark.delay);

                add(universityPark.b1);
                universityPark.b1.setBounds(universityPark.createtBox(universityPark.boxWidth, universityPark.boxHeight));
            } else {
                universityPark.gameStart = false;
                universityPark.gameDone = true;
                remove(universityPark.b1);
                validate();
                repaint();
                add(universityPark.b1);
                universityPark.b1.setBounds(new Rectangle(500, 150, 300, 300));
                universityPark.b1.setText("GAME OVER --- SCORE " + universityPark.universityParkScore);

            }

        }

        if (obj == universityPark.b1 && universityPark.gameStart == true) {

            universityPark.universityParkNumScore++;
            universityPark.buttonTimer.setDelay(delay);
            remove(universityPark.b1);
            validate();
            repaint();

            add(universityPark.b1);
            universityPark.boxWidth = universityPark.boxWidth - (int) ((float) universityPark.boxWidth / 10f);
            universityPark.boxHeight = universityPark.boxHeight - (int) ((float) universityPark.boxHeight / 10f);
            universityPark.b1.setBounds(universityPark.createtBox(universityPark.boxWidth, universityPark.boxHeight));

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            mainMap.movePlayerRight();
            pickGame();
        }

        if (key == KeyEvent.VK_LEFT) {
            mainMap.movePlayerLeft();
            pickGame();
        }

        if (key == KeyEvent.VK_UP) {
            mainMap.movePlayerUp();
            pickGame();
        }

        if (key == KeyEvent.VK_DOWN) {
            mainMap.movePlayerDown();
            pickGame();
        }
        if (key == event.VK_SPACE && universityPark.gameDone == true) {
            universityPark.gameStart = true;
            universityPark.buttonTimer.start();
            universityPark.gameDone = false;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
