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
import snakeGame.gameLogic.CollisionParameterList;
import snakeGame.gameLogic.Control;
import snakeGame.gameLogic.GameFlowService;
import snakeGame.strings.ConstantFields;
import snakeGame.strings.MediaFields;
import snakeGame.userInterface.*;

public class GameLoop extends Application {
    private final GameFlowService gameFlowService = GameFlowService.getInstance();

    static MediaPlayer splashPlayer = new MediaPlayer(MediaFields.getInstance().getSplashMusic());
    static MediaView splashView = new MediaView(splashPlayer);
    Group rootGroup = new Group();
    Pane backgroundPane = new Pane();
    Group splashscreen = new Group();

    Image imgSource;
    BackgroundImage backgroundImage;
    Background backgroundView;
    private long lastUpdate = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnimationTimer timer;
        int speedOffset = 21;
        Food food = new Food();
        Gameboard gameboard = new Gameboard();
        Control control = new Control();
        ScoreLabel scoreLabel = new ScoreLabel(rootGroup);
        Scene scene;
        Scene intro;

        setPrimaryStageProperties(primaryStage);
        setBackground();
        Snake snake = new Snake(rootGroup, primaryStage);

        food.setFood(rootGroup, primaryStage);
        scene = new Scene(backgroundPane, primaryStage.getWidth(), primaryStage.getHeight(), Color.DARKGREEN);
        backgroundPane.getChildren().add(rootGroup);

        intro = new Scene(splashscreen, primaryStage.getWidth(), primaryStage.getHeight());
        splashscreen.getChildren().add(splashView);
        setSplashViewProperties();
        intro.setFill(Color.BLACK);

        primaryStage.setScene(intro);
        primaryStage.show();
        splashPlayer.play();

        MusicService.getInstance().playIngameMusicIndefinitely();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                control.keyHandler(keyEvent, snake, rootGroup, food, scoreLabel, primaryStage);

            }
        });

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= gameFlowService.getFrameDelay()) {
                    int dx = 0, dy = 0;

                    snake.collision(new CollisionParameterList(food, rootGroup, food.getBound(), scoreLabel, control, primaryStage, gameboard));

                    if (control.getGoUp()) dy += -speedOffset;
                    else if (control.getGoDown()) dy += speedOffset;
                    else if (control.getGoRight()) dx += speedOffset;
                    else if (control.getGoLeft()) dx += -speedOffset;
                    snake.moveSnake(dx, dy);

                    lastUpdate = now;
                }
            }
        };

        splashPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                primaryStage.setScene(scene);
                fadeBlackToTransparentTransition(primaryStage).play();
                timer.start();
                MusicService.getInstance().restartIngamemusic();
            }
        });
    }

    private FadeTransition fadeBlackToTransparentTransition(Stage primaryStage) {
        Rectangle blackRectangle = new Rectangle(primaryStage.getWidth(),primaryStage.getHeight(),Color.BLACK);
        FadeTransition fadeBlackToTransparent = new FadeTransition(Duration.millis(ConstantFields.DURATION_MILLIS), blackRectangle);

        fadeBlackToTransparent.setFromValue(ConstantFields.TRANSPARENT_FROM_VALUE);
        fadeBlackToTransparent.setToValue(ConstantFields.TRANSPARENT_TO_VALUE);
        rootGroup.getChildren().add(blackRectangle);

        return fadeBlackToTransparent;
    }

    private void setSplashViewProperties() {
        splashView.setFitHeight(ConstantFields.SPLASHVIEW_HEIGHT);
        splashView.setFitWidth(ConstantFields.SPLASHVIEW_WIDTH);
        splashView.setX(ConstantFields.SPLASHVIEW_X);
        splashView.setY(ConstantFields.SPLASHVIEW_Y);
    }

    private void setBackground() {
        imgSource = MediaFields.getInstance().getGrasTileImage();
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
}
