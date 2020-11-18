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
public class About extends JPanel {

    //Main menu 
    ImageIcon sourceImage = new ImageIcon("images/GameOver.jpg");
    Image menuImage = sourceImage.getImage();

    //back to the menu button
    JButton backButton;

    JTextField textField;

    XML_240 aboutXML;
    String xmlFile;

    //constructor
    public About() {
        super();
        setBackground(Color.white);
        setLayout(null);

        backButton = new JButton("click here to go back to the Menu Screen");
        add(backButton);
        backButton.setBounds(new Rectangle(450, 10, 300, 30));

        aboutXML = new XML_240();// creates the 240 class that reads and writes XML
        
        xmlFile = "About.xml";
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(menuImage, 0, 0, this);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 26));
        g.setColor(Color.WHITE);
        
        aboutXML.openReaderXML(xmlFile);
        String q1 = (String) aboutXML.ReadObject();
        String q2 = (String) aboutXML.ReadObject();
        String q3 = (String) aboutXML.ReadObject();

        ImageIcon sourceImage1 = new ImageIcon(q1);
        Image myImage1 = sourceImage1.getImage();

        ImageIcon sourceImage2 = new ImageIcon(q2);
        Image myImage2 = sourceImage2.getImage();

        g.drawString(q3, 450, 100);
        g.drawImage(myImage1, 400, 130, this);
        g.drawImage(myImage2, 600, 130, this);
    }

}
