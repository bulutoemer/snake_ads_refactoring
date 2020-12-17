package snakeGame.strings;

import javafx.scene.image.Image;
import javafx.scene.media.Media;

import java.net.URL;

public class MediaFields {
    private static final MediaFields instance = new MediaFields();

    public static final String TITLE = "Rainbow Snake";
    private static final String SPLASH_MUSIC = "splash.mp4";
    private static final String DEATH_SOUND_FILE = "sound/death1.mp3";
    private static final String EAT_SOUND_FILE = "sound/eat2.mp3";
    private static final String GAMEOVER_MUSIC = "sound/music/gameover1.mp3";
    private static final String IN_GAME_MUSIC = "sound/music/ingame2.mp3";
    private static final String GRAS_TILE_IMAGE = "grassTile.png";

    private MediaFields() {
    }

    public static MediaFields getInstance() {
        return instance;
    }

    public Media getSplashMusic() {
        return new Media(getFileUrl(SPLASH_MUSIC));
    }

    public Media getDeathSound() {
        return new Media(getFileUrl(DEATH_SOUND_FILE));
    }

    public Media getEatSound() {
        return new Media(getFileUrl(EAT_SOUND_FILE));
    }

    public Media getGameoverSound() {
        return new Media(getFileUrl(GAMEOVER_MUSIC));
    }

    public Media getInGameMusic() {
        return new Media(getFileUrl(IN_GAME_MUSIC));
    }

    public Image getGrasTileImage() {
        return new Image(getFileUrl(GRAS_TILE_IMAGE));
    }

    private String getFileUrl(String fileName) {
        URL resource = getClass().getClassLoader().getResource(fileName);
        return resource != null ? resource.toString() : "";
    }
}
