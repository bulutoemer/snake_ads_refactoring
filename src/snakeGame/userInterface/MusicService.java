package snakeGame.userInterface;

import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import snakeGame.strings.MediaFields;

public class MusicService {
    private static MusicService instance;

    static MediaPlayer ingamemusicPlayer = new MediaPlayer(MediaFields.getInstance().getInGameMusic());
    static MediaPlayer gameovermusicPlayer = new MediaPlayer(MediaFields.getInstance().getGameoverSound());
    static MediaPlayer eatsoundPlayer = new MediaPlayer(MediaFields.getInstance().getEatSound());
    static MediaPlayer deathsoundPlayer = new MediaPlayer(MediaFields.getInstance().getDeathSound());


    private MusicService() {
    }

    public static MusicService getInstance() {
        if (MusicService.instance == null) {
            MusicService.instance = new MusicService();
        }
        return MusicService.instance;
    }

    public void playIngameMusicIndefinitely() {
        ingamemusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }


    public void restartIngamemusic() {
        ingamemusicPlayer.seek(Duration.ZERO);
        ingamemusicPlayer.play();
    }

    public void stopIngamemusic() {
        ingamemusicPlayer.stop();
    }

    public void restartGameovermusic() {
        gameovermusicPlayer.seek(Duration.ZERO);
        gameovermusicPlayer.play();
    }

    public void stopGameovermusic() {
        gameovermusicPlayer.stop();
    }

    public void playEatsound() {
        eatsoundPlayer.seek(Duration.ZERO);
        eatsoundPlayer.play();
    }

    public void playDeathsound() {
        deathsoundPlayer.seek(Duration.ZERO);
        deathsoundPlayer.play();
    }
}
