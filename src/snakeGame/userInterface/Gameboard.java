package snakeGame.userInterface;


import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import snakeGame.strings.ConstantFields;

import java.util.Random;

public class Gameboard {
    String[] touchWall = new String[9];
    String[] touchTail = new String[6];
    Random rand = new Random();


    private String[] stringsTouchWall() {
        touchWall[0] = "Stop touching the wall \nas if it's your boyfriend.....";
        touchWall[1] = "You never touch me \nin the way you touched that wall :*(";
        touchWall[2] = "Walls are your favorite thing huh?";
        touchWall[3] = "The wall you touched is solid,\n no comin through";
        touchWall[4] = "Wall:1, You:0";
        touchWall[5] = "Mimimimi you dead!";
        touchWall[6] = "No one would survive this...";
        touchWall[7] = "You colored the wall, \nwhat a nice thing to do";
        touchWall[8] = "No touchy touchy le wall mi Friend! ";
        return touchWall;
    }

    private String[] stringsTouchTail() {
        touchTail[0] = "Touching yourself huh? ; )";
        touchTail[1] = "Snake ate herself in fury";
        touchTail[2] = "Not your best day is it?....";
        touchTail[3] = "Well...you tried...";
        touchTail[4] = "Stop trying...";
        touchTail[5] = "You touched that ass (tail..)!";
        return touchTail;
    }


    public void setDeathTouchWall(ScoreLabel scoreLabel, Group group, Stage stage) {
        Label deathTouchWall = new Label(stringsTouchWall()[rand.nextInt(9)]
                + "\nPress R for respawn" + "\nScore: " + scoreLabel.getScore());
        deathTouchWall.setFont(new Font(ConstantFields.FONT_NAME_GAMEBOARD, ConstantFields.FONT_SIZE_GAMEBOARD));
        deathTouchWall.setTextFill(Color.BLACK);

        group.getChildren().clear();
        deathTouchWall.relocate(ConstantFields.RELOCATION_TARGET_X, stage.getHeight()/2-300);
        group.getChildren().add(deathTouchWall);

    }

    public void setDeathTouchTail(ScoreLabel scoreLabel, Group group, Stage stage) {
        Label deathTouchTail = new Label(stringsTouchTail()[rand.nextInt(6)] + "\nPress R for respawn"
                +"\nScore: " + scoreLabel.getScore());
        deathTouchTail.setFont(new Font(ConstantFields.FONT_NAME_GAMEBOARD, ConstantFields.FONT_SIZE_GAMEBOARD));
        deathTouchTail.setTextFill(Color.BLACK);

        group.getChildren().clear();
        deathTouchTail.relocate(ConstantFields.RELOCATION_TARGET_X, stage.getHeight()/2-200);
        group.getChildren().add(deathTouchTail);
    }

}
