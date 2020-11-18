/*
 * IST 240 - Deliverable 1
 * Group 5 Members: Theodore Nguyen, Riyank Goel, Nicholas Marzullo
 * Professor Choman
 */
package group5.pkgfinal.project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * Berks Game
 */
public class Instructions extends JPanel {

    //main menu
    ImageIcon sourceImage1 = new ImageIcon("images/GameOver.jpg");
    Image menuImage = sourceImage1.getImage();

    //back to the menu button
    JButton backButton;
    
    // Instructions Lines
    JTextField textField;
    
    XML_240 berksXML;
    String xmlFile;

    //constructor
    public Instructions() {
        super();
        setBackground(Color.white);
        setLayout(null);

        backButton = new JButton("click here to go back to the Menu Screen");
        add(backButton);
        backButton.setBounds(new Rectangle(500, 10, 300, 30));
        
        berksXML = new XML_240();// creates the 240 class that reads and writes XML
        
        xmlFile = "Instructions.xml";

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(menuImage, 0, 0, this);
        g.setFont(new Font("TimesRoman", Font.BOLD, 22));
        g.setColor(Color.ORANGE);
        berksXML.openReaderXML(xmlFile);
        String q = (String) berksXML.ReadObject();
        g.drawString(q, 310, 100);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 22));
        g.setColor(Color.YELLOW);
        
        String q1 = "";
        String q2 = "";
        String q3 = "";
        String q4 = "";
        q1 = (String) berksXML.ReadObject();
        q2 = (String) berksXML.ReadObject();
        q3 = (String) berksXML.ReadObject();
        q4 = (String) berksXML.ReadObject();
        
        g.drawString(q1, 310, 140);
        g.drawString(q2, 310, 170);
        g.drawString(q3, 310, 200);
        g.drawString(q4, 310, 230);
        
//        g.drawString("1. Start by making your choices for character and theme", 310, 140);
//        g.drawString("2. Use the arror key to move around the map", 310, 170);
//        g.drawString("3. ...", 310, 200);
//        g.drawString("4. ...", 310, 230);
        
    }

}
