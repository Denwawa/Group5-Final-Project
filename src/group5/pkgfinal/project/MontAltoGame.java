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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * Word Unscrambler
 */
public class MontAltoGame extends JPanel implements ActionListener {

    //Main Penn State map Image
    ImageIcon sourceBerksImage1 = new ImageIcon("images/MontAlto.jpg");
    Image berksImage = sourceBerksImage1.getImage();

    //back to the menu button
    JButton backToMap;
    JLabel displayQuestion;
    JLabel recentPlays;
    ButtonGroup group;

    JLabel displayAnswer;
    JLabel funFact;
    JLabel berksScore;

    //game componenents
    JLabel scrambledWord;
    JTextField unscrambledWordInput;
    String guessedWord,
            matchedWord;
    JButton checkGameAnswer;

    Boolean scored;

    XML_240 montAltoXML;
    String xmlFile, theme;

    GameScore gameScore;

    MainMap mainMap;

    //constructor
    public MontAltoGame(GameScore gameScore, JLabel score, JLabel recentPlays, MainMap mainMap) {
        super();
        //sets the theme to blank and if it was scored yet to false
        theme = "";
        scored = false;
        berksScore = score;
        this.gameScore = gameScore;
        this.mainMap = mainMap;
        this.recentPlays = recentPlays;
        montAltoXML = new XML_240();// creates the 240 class that reads and writes XML
        setBackground(Color.white);
        setLayout(null);

        //Adds a fact about the campus to the panel
        funFact = new JLabel("Fun Fact! This Campus is known for forestry majors!");
        add(funFact);
        funFact.setBounds(new Rectangle(10, 10, 400, 30));
        funFact.setFont(new Font("Century Gothic", Font.BOLD, 16));
        funFact.setForeground(Color.white);
        //adds a back button to the game
        backToMap = new JButton("click here to go back to the Map");
        add(backToMap);
        backToMap.setBounds(new Rectangle(500, 10, 300, 30));

        //Adds the components for the multiple choice game. THe question and answers are all blank.
        //The Radio buttons and question label are filled through an XML document which is selected in the options menu.
        displayQuestion = new JLabel("Unscramble the following word below. Type your answer and hit enter!");
        displayQuestion.setOpaque(true);
        displayQuestion.setBackground(Color.gray);
        displayQuestion.setForeground(Color.black);
        displayQuestion.setBounds(new Rectangle(0, 0, 300, 60));

        displayAnswer = new JLabel();
        displayAnswer.setOpaque(true);
        displayAnswer.setBackground(Color.white);
        displayAnswer.setForeground(Color.black);
        displayAnswer.setBounds(new Rectangle(0, 0, 300, 60));

        //displays the scrambled word based on the theme
        scrambledWord = new JLabel("");
        scrambledWord.setOpaque(true);
        scrambledWord.setBackground(Color.gray);
        scrambledWord.setForeground(Color.black);
        scrambledWord.setBounds(new Rectangle(0, 0, 300, 60));

        unscrambledWordInput = new JTextField("");
        checkGameAnswer = new JButton("Click to Check Answer");

        //Adds all the components to the map
        add(displayQuestion);
        displayQuestion.setBounds(new Rectangle(400, 50, 500, 50));
        add(scrambledWord);
        scrambledWord.setBounds(new Rectangle(500, 150, 300, 50));
        add(unscrambledWordInput);
        unscrambledWordInput.addActionListener(this);
        unscrambledWordInput.setBounds(new Rectangle(500, 250, 300, 50));
        add(checkGameAnswer);
        checkGameAnswer.addActionListener(this);
        checkGameAnswer.setBounds(new Rectangle(500, 350, 300, 50));
        add(displayAnswer);
        displayAnswer.setBounds(new Rectangle(500, 550, 300, 50));
        
        matchedWord = "help";

        // createQuestions("Math");//sets default to Math
    }

    //Sets background image
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(berksImage, 0, 0, this);
    }

//    //Fills in the missing text for the questions and answers in the constructor.
//    public void createQuestions(String inputTheme) {
//        theme = inputTheme;
//        if (theme == "Math") {
//            xmlFile = "MontAltoGameMath.xml";
//        } else if (theme == "Sports") {
//            xmlFile = "MontAltoGameSports.xml";
//        } else if (theme == "Java") {
//            xmlFile = "MontAltoGameJava.xml";
//        }
//
//        String q1 = "";
//        String a1 = "";
//        String a2 = "";
//        String a3 = "";
//        String a4 = "";
//
//        Boolean a1Boolean = false;
//        Boolean a2Boolean = false;
//        Boolean a3Boolean = false;
//        Boolean a4Boolean = false;
//
//        if (theme != "") {
//            montAltoXML.openReaderXML(xmlFile);
//            q1 = (String) montAltoXML.ReadObject();//reads the lines in the XML file from the top to bottom.
//            a1Boolean = (Boolean) montAltoXML.ReadObject();
//            a1 = (String) montAltoXML.ReadObject();
//            a2Boolean = (Boolean) montAltoXML.ReadObject();
//            a2 = (String) montAltoXML.ReadObject();
//            a3Boolean = (Boolean) montAltoXML.ReadObject();
//            a3 = (String) montAltoXML.ReadObject();
//            a4Boolean = (Boolean) montAltoXML.ReadObject();
//            a4 = (String) montAltoXML.ReadObject();
//        }
//        displayQuestion.setText(q1);
//
//        multipleChoice1.changeButtonText(a1);
//        multipleChoice2.changeButtonText(a2);
//        multipleChoice3.changeButtonText(a3);
//        multipleChoice4.changeButtonText(a4);
//
//        multipleChoice1.changeIsCorrect(a1Boolean);
//        multipleChoice2.changeIsCorrect(a2Boolean);
//        multipleChoice3.changeIsCorrect(a3Boolean);
//        multipleChoice4.changeIsCorrect(a4Boolean);
//    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        Object obj = e.getSource();
        
        if (scored == false){
            gameScore.addToList("Mont Alto Game");
        }
        
        if (obj == unscrambledWordInput){
            guessedWord = unscrambledWordInput.getText();
            if (guessedWord.equals(matchedWord)){
            displayAnswer.setText("Correct!");
            }
            else
            {
                displayAnswer.setText("Incorrect! Guess Again!");
                unscrambledWordInput.setText("");
            }
        }


    }

//        if (obj == answer1 && scored == false) {
//            if (multipleChoice1.isTrue == true) {
//                gameScore.increaseScore(1);//increases the score by 1 point. change the number in the paranthases by the actual score.
//            }
//            berksScore.setText("Score: " + gameScore.score);
//            recentPlays.setText("Recent Plays: " + gameScore.listGames());
//            displayAnswer.setText(multipleChoice1.isCorrect);
//            remove(answer2);
//            remove(answer3);
//            remove(answer4);
//            scored = true;
//            gameScore.increaseGameComplete();//add this to your code so that the game knows when to add the "world campus" to the map.
//            if (gameScore.gameComplete == 5) {//needs to reach 5 before the WorldCampus is revealed
//                mainMap.showWorldCampus();
//            }
//
//        }
//        if (obj == answer2 && scored == false) {
//            if (multipleChoice2.isTrue == true) {
//                gameScore.increaseScore(1);
//            }
//            //updates the score based off the answer
//            berksScore.setText("Score: " + gameScore.score);
//            recentPlays.setText("Recent Plays: " + gameScore.listGames());
//            displayAnswer.setText(multipleChoice2.isCorrect);
//            remove(answer1);
//            remove(answer3);
//            remove(answer4);
//            scored = true;
//            gameScore.increaseGameComplete();//add this to your code so that the game knows when to add the "world campus" to the map.
//            if (gameScore.gameComplete == 5) {//needs to reach 5 before the WorldCampus is revealed
//                mainMap.showWorldCampus();
//            }
//
//        }
//        if (obj == answer3 && scored == false) {
//            if (multipleChoice3.isTrue == true) {
//                gameScore.increaseScore(1);
//
//            }
//            berksScore.setText("Score: " + gameScore.score);
//            recentPlays.setText("Recent Plays: " + gameScore.listGames());
//            displayAnswer.setText(multipleChoice3.isCorrect);
//            remove(answer1);
//            remove(answer2);
//            remove(answer4);
//            scored = true;
//            gameScore.increaseGameComplete();//add this to your code so that the game knows when to add the "world campus" to the map.
//            if (gameScore.gameComplete == 5) {//needs to reach 5 before the WorldCampus is revealed
//                mainMap.showWorldCampus();
//            }
//
//        }
//        if (obj == answer4 && scored == false) {
//            if (multipleChoice4.isTrue == true) {
//                gameScore.increaseScore(1);
//
//            }
//            berksScore.setText("Score: " + gameScore.score);
//            recentPlays.setText("Recent Plays: " + gameScore.listGames());
//            displayAnswer.setText(multipleChoice4.isCorrect);
//            remove(answer1);
//            remove(answer2);
//            remove(answer3);
//            scored = true;
//            gameScore.increaseGameComplete();//add this to your code so that the game knows when to add the "world campus" to the map.
//            if (gameScore.gameComplete == 5) {//needs to reach 5 before the WorldCampus is revealed
//                mainMap.showWorldCampus();
//            }
//
//        }
}
