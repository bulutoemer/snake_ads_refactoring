package snakeGame;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import snakeGame.gameLogic.Control;
import snakeGame.gameLogic.GameFlowService;
import snakeGame.strings.ConstantFields;
import snakeGame.strings.MediaFields;
import snakeGame.userInterface.*;

public class GameLoop extends Application {
    private final GameFlowService gameFlowService = GameFlowService.getInstance();

    static MediaPlayer splashPlayer = new MediaPlayer(MediaFields.splashMedia);
    static MediaView splashView = new MediaView(splashPlayer);
    static MediaPlayer ingamemusicPlayer = new MediaPlayer(MediaFields.ingamemusicMedia);
    static MediaPlayer gameovermusicPlayer = new MediaPlayer(MediaFields.gameovermusicMedia);
    static MediaPlayer eatsoundPlayer = new MediaPlayer(MediaFields.eatsoundMedia);
    static MediaPlayer deathsoundPlayer = new MediaPlayer(MediaFields.deathsoundMedia);
    Group rootGroup = new Group();
    Pane backgroundPane = new Pane();
    Group splashscreen = new Group();

    Image imgSource;
    BackgroundImage backgroundImage;
    Background backgroundView;
    private long lastUpdate = 0; //für Geschwindigkeitssteuerung

    public static void restartIngamemusic() { //Startet Ingame Musik von vorne
        ingamemusicPlayer.seek(Duration.ZERO);
        ingamemusicPlayer.play();
    }

    public static void stopIngamemusic() {
        ingamemusicPlayer.stop();
    }

    public static void restartGameovermusic() {
        gameovermusicPlayer.seek(Duration.ZERO);
        gameovermusicPlayer.play();
    }

    public static void stopGameovermusic() {
        gameovermusicPlayer.stop();
    }

    public static void playEatsound() {
        eatsoundPlayer.seek(Duration.ZERO);
        eatsoundPlayer.play();
    }

    public static void playDeathsound() {
        deathsoundPlayer.seek(Duration.ZERO);
        deathsoundPlayer.play();
    }

    public static void main(String[] args) {
        launch(args);

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        AnimationTimer timer;
        int offset = 21;
        Food food = new Food();
        Gameboard gameboard = new Gameboard();
        Control control = new Control();
        Snake snake = new Snake(rootGroup, primaryStage); //erstellt neues Snake Listen Objekt und getChilded es
        ScoreLabel scoreLabel = new ScoreLabel(rootGroup);
        Scene scene;
        Rectangle blackRectangle;
        FadeTransition fadeBlackToTransparent;
        Scene intro;

        setPrimaryStageProperties(primaryStage);
        setBackground();

        food.setFood(rootGroup, primaryStage);//setzt ein neues Food random ab
        scene = new Scene(backgroundPane, primaryStage.getWidth(), primaryStage.getHeight(), Color.DARKGREEN);
        backgroundPane.getChildren().add(rootGroup);

        blackRectangle = getNewBlackRectangle(primaryStage.getWidth(),primaryStage.getHeight());
        fadeBlackToTransparent = new FadeTransition(Duration.millis(ConstantFields.DURATION_MILLIS), blackRectangle);
        fadeBlackToTransparent.setFromValue(ConstantFields.TRANSPARENT_FROM_VALUE);
        fadeBlackToTransparent.setToValue(ConstantFields.TRANSPARENT_TO_VALUE);
        rootGroup.getChildren().add(blackRectangle);

        intro = new Scene(splashscreen, primaryStage.getWidth(), primaryStage.getHeight());
        splashscreen.getChildren().add(splashView);
        setSplashViewProperties();
        intro.setFill(Color.BLACK);

        primaryStage.setScene(intro);
        primaryStage.show();
        splashPlayer.play();

        ingamemusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {//Keyeventhandler fragt ab obs ein Keyevent gibt
            @Override
            public void handle(KeyEvent keyEvent) {
                control.keyHandler(keyEvent, snake, rootGroup, food, scoreLabel, primaryStage);//control nimmt Keyevent und schaut speziell nach WASD

            }
        });


        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                if (now - lastUpdate >= gameFlowService.getFrameDelay()) {
                    int dx = 0, dy = 0;

                    snake.collision(food, rootGroup, food.getBound(), scoreLabel, control, primaryStage, gameboard);

                    if (control.getgoUp()) dy += -offset; //offset="speed"
                    else if (control.getgoDown()) dy += offset;
                    else if (control.getgoRight()) dx += offset;
                    else if (control.getgoLeft()) dx += -offset;
                    snake.moveSnake(dx, dy, primaryStage);

                    lastUpdate = now;

                }
            }
        };
        splashPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                primaryStage.setScene(scene);
                fadeBlackToTransparent.play();
                timer.start(); //Animationtimer startet nun erst nach dem Fade out des Hundevideos
                restartIngamemusic();
            }
        });

    }

    private void setSplashViewProperties() {
        splashView.setFitHeight(ConstantFields.SPLASHVIEW_HEIGHT);
        splashView.setFitWidth(ConstantFields.SPLASHVIEW_WIDTH);
        splashView.setX(ConstantFields.SPLASHVIEW_X);
        splashView.setY(ConstantFields.SPLASHVIEW_Y);
    }

    private void setBackground() {
        imgSource = new Image(MediaFields.grasTilePath);
        backgroundImage = new BackgroundImage(imgSource, BackgroundRepeat.REPEAT.REPEAT, BackgroundRepeat.REPEAT.REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        backgroundView = new Background(backgroundImage);
        backgroundPane.setBackground(backgroundView);
    }

    private void setPrimaryStageProperties(Stage primaryStage) {
        primaryStage.setWidth(ConstantFields.PRIMARYSTAGE_WIDTH);
        primaryStage.setHeight(ConstantFields.PRIMARYSTAGE_HEIGHT);

        primaryStage.setMinHeight(ConstantFields.MIN_HEIGHT_WIDTH);
        primaryStage.setMinWidth(ConstantFields.MIN_HEIGHT_WIDTH);
        primaryStage.setTitle(MediaFields.TITLE);
    }

    private Rectangle getNewBlackRectangle( double width, double height) {
        return new Rectangle(width,height,Color.BLACK);
    }

}
