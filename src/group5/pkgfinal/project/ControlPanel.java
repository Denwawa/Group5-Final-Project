/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javax.swing.JPanel;
import javax.swing.JProgressBar;

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

    MainMap mainMap;

    //constructor
    public ControlPanel() {
        super();
        InitialSetUpForControlPanel();
        CreateComponentsThatWillBeSwapped();
        setFocusable(true);
        addKeyListener(this);

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

        aboutButton.addActionListener(this);
        instructionsButton.addActionListener(this);
        optionsButton.addActionListener(this);
        playButton.addActionListener(this);
        add(introPanel);
    }

    //Put your class here so they can be used to switch between panels
    public void CreateComponentsThatWillBeSwapped() {
        mainMap = new MainMap();
        mainMap.backButton.addActionListener(this);//adds a back button to return to the main menu

    }

    public void pickGame() {
        String campus = mainMap.intersectsWhichCampus();
        if (campus == "UnivPark") {
            System.out.println("UnivPark");
        } else if (campus == "Scranton") {
            System.out.println("Scranton");
        } else if (campus == "Berks") {
            System.out.println("Berks");
        } else if (campus == "Fayette") {
            System.out.println("Fayette");
        } else if (campus == "World") {
            System.out.println("World");
        } else {
            System.out.println("Nothing");
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object obj = event.getSource();

        //Goes to the Main Map through the play button
        if (obj == playButton) {
            removeAll();
            add(mainMap);
            validate();
            repaint();

        }
        if (obj == mainMap.backButton) {
            removeAll();
            InitialSetUpForControlPanel(); //rebuild the original ControlPanel again
            validate();
            repaint();
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
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
