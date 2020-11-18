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
 * Berks Game Multiple Choice Game
 */
public class WorldCampusGame extends JPanel implements ActionListener {

    //Main Penn State map Image
    ImageIcon sourceBerksImage1 = new ImageIcon("images/WorldCampus.gif");
    Image berksImage = sourceBerksImage1.getImage();

    //back to the menu button
    JButton backToMap;
    JLabel displayQuestion;
    ButtonGroup group;

    JRadioButton answer1, answer2, answer3, answer4;
    JTextField displayAnswer;
    JLabel funFact;
    JLabel berksScore;

    RadioButtonMultipleChoice multipleChoice1, multipleChoice2, multipleChoice3, multipleChoice4;

    Boolean scored;

    XML_240 berksXML;
    String xmlFile, theme;

    GameScore gameScore;

    //constructor
    public WorldCampusGame(GameScore gameScore,JLabel score) {
        super();
        //sets the theme to blank and if it was scored yet to false
        theme = "";
        scored = false;
        berksScore = score;
        this.gameScore = gameScore;
        berksXML = new XML_240();// creates the 240 class that reads and writes XML
        setBackground(Color.white);
        setLayout(null);

        //Adds a fact about the campus to the panel
        funFact = new JLabel("Fun Fact! This Campus was established in 1958");
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
        displayQuestion = new JLabel("");
        displayQuestion.setOpaque(true);
        displayQuestion.setBackground(Color.gray);
        displayQuestion.setForeground(Color.black);
        displayQuestion.setBounds(new Rectangle(0, 0, 300, 60));

        displayAnswer = new JTextField();

        multipleChoice1 = new RadioButtonMultipleChoice(false, "");
        multipleChoice2 = new RadioButtonMultipleChoice(false, "");
        multipleChoice3 = new RadioButtonMultipleChoice(false, "");
        multipleChoice4 = new RadioButtonMultipleChoice(false, "");

        answer1 = multipleChoice1.button;
        answer2 = multipleChoice2.button;
        answer3 = multipleChoice3.button;
        answer4 = multipleChoice4.button;

        group = new ButtonGroup();
        group.add(answer1);
        group.add(answer2);
        group.add(answer3);
        group.add(answer4);

        //Adds all the components to the map
        add(displayQuestion);
        displayQuestion.setBounds(new Rectangle(400, 50, 500, 50));
        add(answer1);
        answer1.setBounds(new Rectangle(500, 150, 300, 50));
        add(answer2);
        answer2.setBounds(new Rectangle(500, 250, 300, 50));
        add(answer3);
        answer3.setBounds(new Rectangle(500, 350, 300, 50));
        add(answer4);
        answer4.setBounds(new Rectangle(500, 450, 300, 50));
        add(displayAnswer);
        displayAnswer.setBounds(new Rectangle(500, 550, 300, 50));

        answer1.addActionListener(this);
        answer2.addActionListener(this);
        answer3.addActionListener(this);
        answer4.addActionListener(this);

        createQuestions("");
    }

    //Sets background image
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(berksImage, 0, 0, this);
    }

    //Fills in the missing text for the questions and answers in the constructor.
    public void createQuestions(String inputTheme) {
        theme = inputTheme;
        if (theme == "Math") {
            xmlFile = "BerksGameMath.xml";
        } else if (theme == "Sports") {
            xmlFile = "BerksGameSports.xml";
        } else if (theme == "Java") {
            xmlFile = "BerksGameJava.xml";
        }

        String q1 = "";
        String a1 = "";
        String a2 = "";
        String a3 = "";
        String a4 = "";

        Boolean a1Boolean = false;
        Boolean a2Boolean = false;
        Boolean a3Boolean = false;
        Boolean a4Boolean = false;

        if (theme != "") {
            berksXML.openReaderXML(xmlFile);
            q1 = (String) berksXML.ReadObject();//reads the lines in the XML file from the top to bottom.
            a1Boolean = (Boolean) berksXML.ReadObject();
            a1 = (String) berksXML.ReadObject();
            a2Boolean = (Boolean) berksXML.ReadObject();
            a2 = (String) berksXML.ReadObject();
            a3Boolean = (Boolean) berksXML.ReadObject();
            a3 = (String) berksXML.ReadObject();
            a4Boolean = (Boolean) berksXML.ReadObject();
            a4 = (String) berksXML.ReadObject();
        }
        displayQuestion.setText(q1);

        multipleChoice1.changeButtonText(a1);
        multipleChoice2.changeButtonText(a2);
        multipleChoice3.changeButtonText(a3);
        multipleChoice4.changeButtonText(a4);

        multipleChoice1.changeIsCorrect(a1Boolean);
        multipleChoice2.changeIsCorrect(a2Boolean);
        multipleChoice3.changeIsCorrect(a3Boolean);
        multipleChoice4.changeIsCorrect(a4Boolean);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object obj = e.getSource();

        if (obj == answer1 && scored == false) {
            if (multipleChoice1.isTrue == true) {
                gameScore.increaseScore(1);//increases the score by 1 point. change the number in the paranthases by the actual score.
            }
            berksScore.setText("Score: " + gameScore.score);
            System.out.println(multipleChoice1.isTrue);
            displayAnswer.setText(multipleChoice1.isCorrect);
            remove(answer2);
            remove(answer3);
            remove(answer4);
            scored = true;
            gameScore.increaseGameComplete();//add this to your code so that the game knows when to add the "world campus" to the map.

        }
        if (obj == answer2 && scored == false) {
            if (multipleChoice2.isTrue == true) {
                gameScore.increaseScore(1);
            }
            //updates the score based off the answer
            berksScore.setText("Score: " + gameScore.score);
            displayAnswer.setText(multipleChoice2.isCorrect);
            remove(answer1);
            remove(answer3);
            remove(answer4);
            scored = true;
            gameScore.increaseGameComplete();//add this to your code so that the game knows when to add the "world campus" to the map.

        }
        if (obj == answer3 && scored == false) {
            if (multipleChoice3.isTrue == true) {
                gameScore.increaseScore(1);

            }
            berksScore.setText("Score: " + gameScore.score);
            displayAnswer.setText(multipleChoice3.isCorrect);
            remove(answer1);
            remove(answer2);
            remove(answer4);
            scored = true;
            gameScore.increaseGameComplete();//add this to your code so that the game knows when to add the "world campus" to the map.

        }
        if (obj == answer4 && scored == false) {
            if (multipleChoice4.isTrue == true) {
                gameScore.increaseScore(1);

            }
            berksScore.setText("Score: " + gameScore.score);
            displayAnswer.setText(multipleChoice4.isCorrect);
            remove(answer1);
            remove(answer2);
            remove(answer3);
            scored = true;
            gameScore.increaseGameComplete();//add this to your code so that the game knows when to add the "world campus" to the map.

        }
    }
}
