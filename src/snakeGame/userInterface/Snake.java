package snakeGame.userInterface;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import snakeGame.GameLoop;
import snakeGame.gameLogic.Control;
import snakeGame.gameLogic.GameFlowService;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Snake {
    private final GameFlowService gameFlowService = GameFlowService.getInstance();
    private Rectangle head = new Rectangle(20, 20); // hier Initialisiert, weil in mehreren Methoden
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
        //Last Minute - wird gebraucht um Score nicht zu früh zu löschen (überlegung nur respawn zu verwenden mit dieser implementierung fehlgeschlagen)
        theSnake.clear();
        theSnake.add(head);
        theSnake.getFirst().relocate(stage.getWidth() / 2, stage.getHeight() / 2);
    }


    public void eat(Food food) {//added ein tail rectangle, übernimmt color von food,erhöht score um 1, macht schneller
        theSnake.add(new Rectangle(20, 20));
        theSnake.getLast().setFill(Color.color(food.getColor()[0], food.getColor()[1], food.getColor()[2])); //holt sich aus deathsoundMedia GameObject die Color von Food für sein Tail
    }

    //TODO move collision into GameFlowService
    public void collision(Food food, Group group, Bounds foodBound, ScoreLabel score, Control control, Stage stage, Gameboard gameboard) { //gameobject sind obstacles so wie Food, Boundarys für Collisions
        Bounds headBox = head.getBoundsInParent(); // erstellt eine Boundary um den Snakekopf

        if (headBox.intersects(foodBound)) {//überprüfung Collision Head mit Food Boundary
            gameFlowService.eat(this, group, score, food);
            food.setFood(group, stage);
            GameLoop.playEatsound();
        }

        if (head.getLayoutX() <= 0 || head.getLayoutX() >= stage.getWidth() - 30 || // Überprüfung ob Head den Rand trifft
                head.getLayoutY() <= 0 || head.getLayoutY() >= stage.getHeight() - 54) {
            gameFlowService.die(this, group, control, stage);
            gameboard.setDeathTouchWall(score, group, stage);
            GameLoop.playDeathsound();
            GameLoop.stopIngamemusic();
            GameLoop.restartGameovermusic();
        }


        for (int i = 1; i < this.theSnake.size(); i++) { //Überprüfung Snake beisst sich in den oasch
            if (headBox.intersects(this.theSnake.get(i).getBoundsInParent())) {
                logger.log(Level.INFO, "DEAD");
                gameFlowService.die(this, group, control, stage);
                gameboard.setDeathTouchTail(score, group, stage);
                GameLoop.playDeathsound();
                GameLoop.stopIngamemusic();
                GameLoop.restartGameovermusic();
            }


        }
    }


    public void moveSnake(int dx, int dy) { //dx bzw dy ist jeweils + oder - speed, war zuvor 5

        if (dx != 0 || dy != 0) { //gibt es überhaupt dx/dy werte (wenn wir stehen z.B. nicht)
            LinkedList<Rectangle> snakehelp = new LinkedList<>();

            for (int i = 0; i < theSnake.size(); i++) {

                snakehelp.add(new Rectangle());

                snakehelp.get(i).relocate(theSnake.get(i).getLayoutX(), theSnake.get(i).getLayoutY());
            }

            int x = (int) theSnake.getFirst().getLayoutX() + dx;
            int y = (int) theSnake.getFirst().getLayoutY() + dy;
            theSnake.getFirst().relocate(x, y);//moved erstmal nur den Kopf


            for (int i = 1; i < theSnake.size(); i++) {

                int helpX = (int) snakehelp.get(i - 1).getLayoutX();
                int helpY = (int) snakehelp.get(i - 1).getLayoutY();
                theSnake.get(i).relocate(helpX, helpY);
            }
        }
    }
}
