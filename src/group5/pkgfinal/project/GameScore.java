/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group5.pkgfinal.project;

/**
 *
 * @author theodore
 */
public class GameScore {

    int score;
    int gameComplete;

    public GameScore() {
        super();
        score = 0;
        gameComplete = 0;

    }

    //increments the actual game score
    public void increaseScore(int gameScore) {
        score = score + gameScore;
    }

    public void increaseGameComplete() {
        gameComplete = gameComplete + 1;

    }
    

}
