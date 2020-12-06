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
    JButton score,
            time,
            recentlyPlayed;

    //constructor
    public GameOver() {
        super();
        setBackground(Color.white);
        setLayout(null);

        backToMainMenu = new JButton("click here to restart game");
        add(backToMainMenu);
        backToMainMenu.setBounds(new Rectangle(500, 10, 300, 30));
        
        score = new JButton("Score");
        add(score);
        score.setBounds(new Rectangle(600, 100, 100, 30));
        
        time = new JButton("time");
        add(time);
        time.setBounds(new Rectangle(600, 200, 100, 30));
        
        recentlyPlayed = new JButton("rp");
        add(recentlyPlayed);
        recentlyPlayed.setBounds(new Rectangle(100, 300, 1000, 30));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(gameOver, 0, 0, this);
        g.setFont(new Font("TimesRoman", Font.BOLD, 32));
        g.setColor(Color.WHITE);
        g.drawString("You Won !!",570,400);
    }
    
    public void addScoreInfo(String inputScore,String inputTime,String inputRecentlyPlayed) {
        score.setText(inputScore);
        time.setText(inputTime);
        recentlyPlayed.setText(inputRecentlyPlayed);
    }

}
