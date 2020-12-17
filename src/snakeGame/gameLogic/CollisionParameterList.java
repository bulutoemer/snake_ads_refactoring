package snakeGame.gameLogic;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.stage.Stage;
import snakeGame.userInterface.Food;
import snakeGame.userInterface.Gameboard;
import snakeGame.userInterface.ScoreLabel;

public class CollisionParameterList {
    private Food food;
    private Group group;
    private Bounds foodBound;
    private ScoreLabel score;
    private Control control;
    private Stage stage;
    private Gameboard gameboard;

    public CollisionParameterList(Food food, Group group, Bounds foodBound, ScoreLabel score, Control control, Stage stage, Gameboard gameboard) {
        this.food = food;
        this.group = group;
        this.foodBound = foodBound;
        this.score = score;
        this.control = control;
        this.stage = stage;
        this.gameboard = gameboard;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Bounds getFoodBound() {
        return foodBound;
    }

    public void setFoodBound(Bounds foodBound) {
        this.foodBound = foodBound;
    }

    public ScoreLabel getScore() {
        return score;
    }

    public void setScore(ScoreLabel score) {
        this.score = score;
    }

    public Control getControl() {
        return control;
    }

    public void setControl(Control control) {
        this.control = control;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Gameboard getGameboard() {
        return gameboard;
    }

    public void setGameboard(Gameboard gameboard) {
        this.gameboard = gameboard;
    }
}
