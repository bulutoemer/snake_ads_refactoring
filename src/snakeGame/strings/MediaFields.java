package snakeGame.strings;

import javafx.scene.media.Media;

import java.io.File;

public class MediaFields
{
    public static final String MEDIA_DIRECTORY = "src/snakeGame/userInterface/media/";
    public static File deathsoundFile = new File(MEDIA_DIRECTORY + "sound/death1.mp3");
    public static Media deathsoundMedia = new Media(deathsoundFile.toURI().toString());
    public static File eatsoundFile = new File(MEDIA_DIRECTORY + "sound/eat2.mp3");
    public static Media eatsoundMedia = new Media(eatsoundFile.toURI().toString());
    public static File gameovermusicFile = new File(MEDIA_DIRECTORY + "sound/music/gameover1.mp3");
    public static Media gameovermusicMedia = new Media(gameovermusicFile.toURI().toString());
    public static File ingamemusicFile = new File(MEDIA_DIRECTORY + "sound/music/ingame2.mp3");
    public static Media ingamemusicMedia = new Media(ingamemusicFile.toURI().toString());
    public static final String IMAGE_DIRECTORY = "snakeGame/userInterface/media/";
    public static File splashFile = new File(MEDIA_DIRECTORY + "splash.mp4");
    public static Media splashMedia = new Media(splashFile.toURI().toString());
    public static String grasTilePath = IMAGE_DIRECTORY + "grassTile.png";
    public static String TITLE = "Rainbow Snake";

    private MediaFields(){}
}
