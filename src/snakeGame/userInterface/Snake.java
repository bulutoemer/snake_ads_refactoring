package snakeGame.userInterface;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import snakeGame.GameLoop;
import snakeGame.gameLogic.CollisionParameterList;
import snakeGame.gameLogic.Control;
import snakeGame.gameLogic.GameFlowService;
import snakeGame.strings.ConstantFields;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Snake {
    private final GameFlowService gameFlowService = GameFlowService.getInstance();
    private Rectangle head = new Rectangle(ConstantFields.RECTANGLE_HEIGHT_WIDTH, ConstantFields.RECTANGLE_HEIGHT_WIDTH); // hier Initialisiert, weil in mehreren Methoden
    public LinkedList<Rectangle> theSnake = new LinkedList<>(); //TODO handle this Snake.snake differently
    private static Logger logger = Logger.getLogger(Snake.class.getName());

    public Snake(Group group, Stage stage) {
        theSnake.add(head);
        theSnake.getFirst().relocate(stage.getWidth() / 2, stage.getHeight() / 2);
        group.getChildren().add(theSnake.getFirst());

    }

    public void respawn(Stage stage) {
        theSnake.clear();
        theSnake.add(head);
        theSnake.getFirst().relocate(stage.getWidth() / 2, stage.getHeight() / 2);
    }

    public void snakeDead(Stage stage) {
        theSnake.clear();
        theSnake.add(head);
        theSnake.getFirst().relocate(stage.getWidth() / 2, stage.getHeight() / 2);
    }


    public void eat(Food food) {
        theSnake.add(new Rectangle(ConstantFields.RECTANGLE_HEIGHT_WIDTH, ConstantFields.RECTANGLE_HEIGHT_WIDTH));
        theSnake.getLast().setFill(Color.color(food.getColor()[0], food.getColor()[1], food.getColor()[2]));
    }

    //TODO move collision into GameFlowService
    public void collision(CollisionParameterList parameterList) {
        Bounds headBox = head.getBoundsInParent();

        checkCollisionWithFood(parameterList, headBox);

        checkCollisionWithBorder(parameterList);

        checkCollisionWithSnake(parameterList, headBox);
    }

    private void checkCollisionWithSnake(CollisionParameterList parameterList, Bounds headBox) {
        for (int i = 1; i < this.theSnake.size(); i++) {
            if (headBox.intersects(this.theSnake.get(i).getBoundsInParent())) {
                logger.log(Level.INFO, "DEAD");
                gameFlowService.die(this, parameterList.getGroup(), parameterList.getControl(), parameterList.getStage());
                parameterList.getGameboard().setDeathTouchTail(parameterList.getScore(), parameterList.getGroup(), parameterList.getStage());
                MusicService.playDeathsound();
                MusicService.stopIngamemusic();
                MusicService.restartGameovermusic();
            }


        }
    }

    private void checkCollisionWithBorder(CollisionParameterList parameterList) {
        if (head.getLayoutX() <= 0 || head.getLayoutX() >= parameterList.getStage().getWidth() - ConstantFields.BORDER_WIDTH_BOUND ||
                head.getLayoutY() <= 0 || head.getLayoutY() >= parameterList.getStage().getHeight() - ConstantFields.BORDER_HEIGHT_BOUND) {
            gameFlowService.die(this, parameterList.getGroup(), parameterList.getControl(), parameterList.getStage());
            parameterList.getGameboard().setDeathTouchWall(parameterList.getScore(), parameterList.getGroup(), parameterList.getStage());
            MusicService.playDeathsound();
            MusicService.stopIngamemusic();
            MusicService.restartGameovermusic();
        }
    }

    private void checkCollisionWithFood(CollisionParameterList parameterList, Bounds headBox) {
        if (headBox.intersects(parameterList.getFoodBound())) {
            gameFlowService.eat(this, parameterList.getGroup(), parameterList.getScore(), parameterList.getFood());
            parameterList.getFood().setFood(parameterList.getGroup(), parameterList.getStage());
            MusicService.playEatsound();
        }
    }


    public void moveSnake(int dx, int dy) {

        if (dx != 0 || dy != 0) {
            LinkedList<Rectangle> snakehelp = new LinkedList<>();

            for (int i = 0; i < theSnake.size(); i++) {

                snakehelp.add(new Rectangle());

                snakehelp.get(i).relocate(theSnake.get(i).getLayoutX(), theSnake.get(i).getLayoutY());
            }

            int x = (int) theSnake.getFirst().getLayoutX() + dx;
            int y = (int) theSnake.getFirst().getLayoutY() + dy;
            theSnake.getFirst().relocate(x, y);


            for (int i = 1; i < theSnake.size(); i++) {

                int helpX = (int) snakehelp.get(i - 1).getLayoutX();
                int helpY = (int) snakehelp.get(i - 1).getLayoutY();
                theSnake.get(i).relocate(helpX, helpY);
            }
        }
    }
}
