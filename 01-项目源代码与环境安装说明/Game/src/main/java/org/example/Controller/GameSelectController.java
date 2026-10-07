package org.example.Controller;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
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

public class GameSelectController {
    @FXML
    private Button pvpButton;

    @FXML
    private Button pveButton;

    @FXML
    private Button backButton;

    @FXML
    private Button rankButton;

    private static boolean patternChoice = false;

    @FXML
    public void turnToRank(ActionEvent event) {
        Stage currentStage = (Stage) rankButton.getScene().getWindow();
        try {
            Scene rankScene = new Scene(FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("排行榜.fxml"))));
            FadeTransition fadeOut = getFadeTransition(currentStage,rankScene);//创建淡入淡出动画

            fadeOut.play();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onPVPButtonAction() {
        Stage currentStage = (Stage) pvpButton.getScene().getWindow();
        try {
            Scene selectedScene = new Scene(FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("ChoiceHero.fxml"))));
            FadeTransition fadeOut = getFadeTransition(currentStage,selectedScene);//创建淡入淡出动画
            patternChoice=false;
            fadeOut.play();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onPVEButtonAction(ActionEvent event) {
        Stage currentStage = (Stage) pveButton.getScene().getWindow();
        try {
            Scene selectedScene = new Scene(FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("ChoiceHero.fxml"))));
            FadeTransition fadeOut = getFadeTransition(currentStage,selectedScene);//创建淡入淡出动画
            patternChoice=true;
            System.out.println(patternChoice);
            fadeOut.play();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    @FXML
    public void onBackButtonAction(ActionEvent event) {
        Stage currentStage = (Stage) backButton.getScene().getWindow();
        try {
            Scene startScene = new Scene(FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("gameStart.fxml"))));
            currentStage.setScene(startScene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void mouseClickedSelectGame(MouseEvent event) {
        Object source = event.getSource();

        if (source == pvpButton) {
            clickPvpButton();
        } else if (source == pveButton) {
            clickPveButton();
        } else if (source == backButton) {
            clickBackButton();
        } else if (source == rankButton) {
            clickRankButton();
        }
    }

    public void clickPvpButton() {
        SoundEffect.play("/sound/click.wav");
    }

    public void clickPveButton() {
        SoundEffect.play("/sound/click.wav");
    }

    public void clickBackButton() {
        SoundEffect.play("/sound/click.wav");
    }

    public void clickRankButton() {
        SoundEffect.play("/sound/click.wav");
    }

    @FXML
    public void mouseEnteredSelectGame(MouseEvent event) {
        Object source = event.getSource();

        if (source == pvpButton) {
            enterPvpButton();
        } else if (source == pveButton) {
            enterPveButton();
        } else if (source == backButton) {
            enterBackButton();
        } else if (source == rankButton) {
            enterRankButton();
        }
    }

    public void enterPvpButton() {
        pvpButton.setOpacity(1);
        SoundEffect.play("/sound/button.wav");
    }

    public void enterPveButton() {
        pveButton.setOpacity(1);
        SoundEffect.play("/sound/button.wav");
    }

    public void enterBackButton() {
        backButton.setOpacity(1);
        SoundEffect.play("/sound/button.wav");
    }

    public void enterRankButton() {
        rankButton.setOpacity(1);
        SoundEffect.play("/sound/button.wav");
    }

    @FXML
    public void mouseExitedSelectGame(MouseEvent event) {
        // 判断哪个按钮被滑到了
        Object source = event.getSource();  // 获取触发事件的控件

        if (source == pvpButton) {
            exitPvpButton();
        } else if (source == pveButton) {
            exitPveButton();
        } else if (source == backButton) {
            exitBackButton();
        } else if (source == rankButton) {
            exitRankButton();
        }
    }

    public void exitPvpButton() {
        pvpButton.setOpacity(0.7);
    }

    public void exitPveButton() {
        pveButton.setOpacity(0.7);
    }

    public void exitBackButton() {
        backButton.setOpacity(0.7);
    }

    public void exitRankButton() {
        rankButton.setOpacity(0.7);
    }
    public static boolean getPatternChoice() {
        return patternChoice;
    }
}
