package snakeGame.gameLogic;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import snakeGame.userInterface.Food;
import snakeGame.userInterface.ScoreLabel;
import snakeGame.userInterface.Snake;

public class Control {
    private final GameFlowService gameFlowService = GameFlowService.getInstance();
    private boolean goUp, goDown, goRight, goLeft;

    public void stopMovement() {
        goUp = false;
        goDown = false;
        goRight = false;
        goLeft = false;
    }

    public boolean getGoUp() {
        return goUp;
    }

    public boolean getGoDown() {
        return goDown;
    }

    public boolean getGoLeft() {
        return goLeft;
    }

    public boolean getGoRight() {
        return goRight;
    }

    public void keyHandler(KeyEvent keyEvent, Snake snake, Group group, Food food, ScoreLabel scoreLabel, Stage stage) {
        KeyCode keyCode = keyEvent.getCode();

        if (KeyCode.R == keyCode) {
            gameFlowService.restartGame(snake, group, food, scoreLabel, stage, this);
        }

        if (movementAllowed(keyCode)) {
            changeMovement(keyCode);
        }
    }

    private boolean movementAllowed(KeyCode keyCode) {
        boolean isAllowed = true;
        switch (keyCode) {
            case W:
                if (goDown) isAllowed = false;
                break;
            case S:
                if (goUp) isAllowed = false;
                break;
            case D:
                if (goLeft) isAllowed = false;
                break;
            case A:
                if (goRight) isAllowed = false;
                break;
        }
        return isAllowed;
    }

    private void changeMovement(KeyCode keyCode) {
        switch (keyCode) {
            case W:
                goUp = true;
                goDown = false;
                goRight = false;
                goLeft = false;
                break;
            case S:
                goDown = true;
                goUp = false;
                goRight = false;
                goLeft = false;
                break;
            case D:
                goRight = true;
                goUp = false;
                goDown = false;
                goLeft = false;
                break;
            case A:
                goLeft = true;
                goUp = false;
                goDown = false;
                goRight = false;
                break;
        }
    }

}

