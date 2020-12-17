package snakeGame.userInterface;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import snakeGame.gameLogic.ScoreService;
import snakeGame.strings.ConstantFields;

public class ScoreLabel {
    private final ScoreService scoreService = ScoreService.getInstance();
    Label score = new Label("Score: " + scoreService.getScoreValue());

    public ScoreLabel(Group group) {
        score.setFont(new Font(ConstantFields.FONT_NAME_SCORELABEL, ConstantFields.FONT_SIZE_SCORELABEL));
        updateScoreText();
        group.getChildren().add(score);

    }

    public void scoreRespawn(Group group) {
        scoreService.setScoreValue(0);
        score.setFont(new Font(ConstantFields.FONT_NAME_SCORELABEL, ConstantFields.FONT_SIZE_SCORELABEL));
        updateScoreText();
        group.getChildren().add(score);

    }

    public void upScoreValue() {
        scoreService.upScoreValue();
        updateScoreText();
    }

    public int getScore() {
        return scoreService.getScoreValue();
    }

    private void updateScoreText() {
        score.setText("Score: " + scoreService.getScoreValue());
    }

}
