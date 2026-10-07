package org.example.Controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.gametest.HelloApplication;
import org.example.sound.SoundEffect;

import java.io.IOException;
import java.util.Objects;

public class GameOverController3 {

    @FXML
    private Button victoryButton;

    @FXML
    public void mouseEnteredOverGame(MouseEvent event) {
        victoryButton.setOpacity(1);
        SoundEffect.play("/sound/button.wav");
    }

    @FXML
    public void mouseExitedOverGame(MouseEvent event) {
        victoryButton.setOpacity(0.7);
    }

    @FXML
    public void handleOverButtonAction() {
        Stage currentStage = (Stage) victoryButton.getScene().getWindow();
        try {
            Scene selectedScene = new Scene(FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("gameStart.fxml"))));
            FadeTransition fadeOut = getFadeTransition(currentStage,selectedScene);//创建淡入淡出动画
            //currentStage.setScene(restartScene);
            fadeOut.play();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SoundEffect.play("/sound/click.wav");
    }

    protected static FadeTransition getFadeTransition(Stage currentStage,Scene nextScene) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(500), currentStage.getScene().getRoot());
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(event -> {
            // 切换场景
            currentStage.setScene(nextScene);

            // 创建淡入动画
            FadeTransition fadeIn = new FadeTransition(Duration.millis(500), nextScene.getRoot());
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });
        return fadeOut;
    }
}