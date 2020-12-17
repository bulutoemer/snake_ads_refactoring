package snakeGame.gameLogic;

import javafx.scene.Group;
import javafx.stage.Stage;
import snakeGame.GameLoop;
import snakeGame.userInterface.*;

import java.util.logging.Level;
import java.util.logging.Logger;

//TODO find a way so that we don't have to pass all the parameters
public class GameFlowService {
    private static final int DEFAULT_FRAME_DELAY = 25000000; //25-30 mill. is a good value to start
    private static final int MAX_FRAME_DELAY = 8000000;
    private static GameFlowService instance;
    private long frameDelay = DEFAULT_FRAME_DELAY;
    public long delayDecrease = 600000;
    private static Logger logger = Logger.getLogger(GameFlowService.class.getName());


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

    public void restartGame(Snake snake,Group group,Food food,ScoreLabel scoreLabel,Stage stage,Control control) {
        respawn(snake, group, food, scoreLabel, stage, control);
        MusicService.getInstance().stopGameovermusic();
        MusicService.getInstance().restartIngamemusic();
    }

    public void respawn(Snake snake, Group group, Food food, ScoreLabel scoreLabel, Stage stage, Control control) {
        group.getChildren().clear();
        snake.respawn(stage);

        group.getChildren().add(snake.theSnake.getFirst());
        food.setFood(group, stage);
        scoreLabel.scoreRespawn(group);
        frameDelay = DEFAULT_FRAME_DELAY;

        control.stopMovement();
    }

    public void eat(Snake snake, Group group, ScoreLabel score, Food food) {
        snake.eat(food);

        group.getChildren().add(snake.theSnake.getLast());
        score.upScoreValue();
        if (frameDelay >= MAX_FRAME_DELAY) {
            frameDelay -= delayDecrease;
            logger.log(Level.INFO, "The current frameDelay is: {0}", frameDelay);
        }
    }

    public void die(Snake snake, Group group, Control control, Stage stage) {
        group.getChildren().clear();
        snake.snakeDead(stage);
        frameDelay = DEFAULT_FRAME_DELAY;
        control.stopMovement();
    }

}
