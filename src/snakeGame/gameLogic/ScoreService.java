package snakeGame.gameLogic;

public class ScoreService {
    private int scoreValue = 0;

    private static ScoreService instance;

    private ScoreService () {}

    public static ScoreService getInstance () {
        if (ScoreService.instance == null) {
            ScoreService.instance = new ScoreService ();
        }
        return ScoreService.instance;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    public void upScoreValue() {
        scoreValue++;
    }
}
