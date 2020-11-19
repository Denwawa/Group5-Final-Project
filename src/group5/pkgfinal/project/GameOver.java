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
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * Berks Game
 */
public class GameOver extends JPanel {

    //Main Penn State map Image
    ImageIcon sourceGameOverImage1 = new ImageIcon("images/GameOver.jpg");
    Image gameOver = sourceGameOverImage1.getImage();

    //back to the menu button
    JButton backToMainMenu;
    

    //constructor
    public GameOver() {
        super();
        setBackground(Color.white);
        setLayout(null);

        backToMainMenu = new JButton("click here to restart game");
        add(backToMainMenu);
        backToMainMenu.setBounds(new Rectangle(500, 10, 300, 30));
        


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(gameOver, 0, 0, this);
    }

}
