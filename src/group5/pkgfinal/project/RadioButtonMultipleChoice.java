/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group5.pkgfinal.project;

import javax.swing.JRadioButton;

/**
 *
 * @author theodore
 */
public class RadioButtonMultipleChoice {

    String isCorrect;
    JRadioButton button;

    public RadioButtonMultipleChoice(Boolean isTrue, String question) {
        button = new JRadioButton(question);

        if (isTrue == true) {
            isCorrect = "correct";
        } else {
            isCorrect = "incorrect";
        }
    }

    public void changeIsCorrect(Boolean isTrue) {       
        if (isTrue == true) {
            isCorrect = "correct";
        } else {
            isCorrect = "incorrect";
        }
        
    }
    
    public void changeButtonText(String string) {
        button.setText(string);
    }
}

