/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group5.pkgfinal.project;

import java.util.ArrayList;

/**
 *
 * @author theodore
 */
public class GameScore {

    int score;
    int gameComplete;

    ArrayList<String> recentlyPlayed;

    public GameScore() {
        super();
        score = 0;
        gameComplete = 0;
        recentlyPlayed = new ArrayList<String>();

    }

    //increments the actual game score
    public void increaseScore(int gameScore) {
        score = score + gameScore;
    }

    public void increaseGameComplete() {
        gameComplete = gameComplete + 1;

    }

    //keeps track of the last 3 games played
    public void addToList(String gamePlayed) {
        if (recentlyPlayed.size() < 3) {
            recentlyPlayed.add(gamePlayed);
        } else {
            recentlyPlayed.remove(0);
            recentlyPlayed.add(gamePlayed);
        }
    }

    //returns the last 3 games played
    public String listGames() {
        
        String gamesPlayed = "";
        for (int i = recentlyPlayed.size() - 1; i >= 0; i--) {
            if (i > 0){
            gamesPlayed = gamesPlayed + recentlyPlayed.get(i) + ", ";
            }
            else
            {
                gamesPlayed = gamesPlayed + recentlyPlayed.get(i);
            }
           
        }
        return gamesPlayed;
    }

}
