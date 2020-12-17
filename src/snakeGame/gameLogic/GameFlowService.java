package snakeGame.gameLogic;

import javafx.scene.Group;
import javafx.stage.Stage;
import snakeGame.userInterface.*;

//TODO find a way so that we don't have to pass all the parameters
public class GameFlowService {
    private static GameFlowService instance;
    private final ScoreService scoreService = ScoreService.getInstance();
    private long frameDelay = 25000000; //25-30 mill. guter Startwert
    public long delayDecrease = 600000;  //von speedRefresh abziehen


    private GameFlowService () {
    }

    public static GameFlowService getInstance () {
        if (GameFlowService.instance == null) {
            GameFlowService.instance = new GameFlowService ();
        }
        return GameFlowService.instance;
    }

    public long getFrameDelay() {
        return frameDelay;
    }

    public void setFrameDelay(long frameDelay) {
        this.frameDelay = frameDelay;
    }

    public void respawn(Snake snake, Group group, Food food, ScoreLabel scoreLabel, Stage stage, Control control) {
        group.getChildren().clear();

        snake.respawn(stage);

        group.getChildren().add(snake.snake.getFirst());
        food.setFood(group, stage); // setet neues random food und getchilded es
        scoreLabel.scoreRespawn(group); // respawn Mehtode für Score
        frameDelay = 25000000; // zurück zum Standardwert

        control.stopMovement();
    }

    public void eat(Snake snake, Group group, ScoreLabel score, Food food) {
        snake.eat(food);

        group.getChildren().add(snake.snake.getLast()); //bringt den tail auf die Szene
        score.upScoreValue();
        if (frameDelay >= 8000000) { //maximale Grenze sonst wirds zu schnell
            frameDelay -= delayDecrease;
            System.out.println(frameDelay);
        }
    }

    public void die(Snake snake, Group group, Control control, Stage stage) {
        group.getChildren().clear();

        snake.snakeDead(stage);

        frameDelay = 25000000; // zurück zum Standardwert
        control.stopMovement();
    }

}
