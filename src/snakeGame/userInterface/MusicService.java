package snakeGame.userInterface;

import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import snakeGame.strings.MediaFields;

public class MusicService {
    private static MusicService instance;

    static MediaPlayer ingamemusicPlayer = new MediaPlayer(MediaFields.ingamemusicMedia);
    static MediaPlayer gameovermusicPlayer = new MediaPlayer(MediaFields.gameovermusicMedia);
    static MediaPlayer eatsoundPlayer = new MediaPlayer(MediaFields.eatsoundMedia);
    static MediaPlayer deathsoundPlayer = new MediaPlayer(MediaFields.deathsoundMedia);


    private MusicService() {
    }

    public static MusicService getInstance() {
        if (MusicService.instance == null) {
            MusicService.instance = new MusicService();
        }
        return MusicService.instance;
    }

    public static void playIngameMusicIndefinitely() {
        ingamemusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }


    public static void restartIngamemusic() {
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
}
