package snakeGame.userInterface;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import snakeGame.strings.ConstantFields;

import java.util.Random;


public class Food {
    public Rectangle food = new Rectangle(ConstantFields.RECTANGLE_HEIGHT_WIDTH, ConstantFields.RECTANGLE_HEIGHT_WIDTH);
    private Random rand;
    private double redPart, greenPart, bluePart;
    private Bounds fbound;

    public Food() {
    }

    public double[] getColor() {
        double[] colors = new double[3];
        colors[0] = redPart;
        colors[1] = greenPart;
        colors[2] = bluePart;
        return colors;
    }


    public void setFood(Group g, Stage stage) {
        g.getChildren().remove(food);
        rand = new Random();

        food.setFill(Color.color(redPart = rand.nextDouble(), greenPart = rand.nextDouble(), bluePart = rand.nextDouble())); // hier werden zufällige Farben für das Food (und damit auch den Tail) übergeben
        food.relocate(rand.nextInt((int) stage.getWidth() - 50), rand.nextInt((int) stage.getHeight() - 50)); // Random Location mit Abstand vom Rand jeweils 40
        g.getChildren().add(food);
        fbound = food.getBoundsInParent();
    }

    public Bounds getBound() {
        return fbound;
    }


}
