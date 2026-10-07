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
import org.example.Scenes.GameScene;
import org.example.gametest.HelloApplication;
import org.example.sound.SoundEffect;

import java.io.IOException;
import java.util.Objects;

public class ChoiceMapController {
    @FXML
    private Button level1;

    @FXML
    private Button level2;

    @FXML
    private Button level3;

    @FXML
    private Button back;

    private int chosenNumber = -1; // 初始化为-1，表示未选择

    @FXML
    private void initialize() {
        // 为每个按钮设置点击事件
        level1.setOnAction(event -> {
            chosenNumber = 0;
            buildStage();
        });
        level2.setOnAction(event -> {
            chosenNumber = 1;
            buildStage();
        });
        level3.setOnAction(event -> {
            chosenNumber = 2;
            buildStage();
        });
    }

    private void buildStage() {
        Stage  currentStage = (Stage) level1.getScene().getWindow();
        GameScene gameScene = new GameScene();
        gameScene.setMapkind(chosenNumber);
        gameScene.setBotOn(GameSelectController.getPatternChoice());
        gameScene.init(currentStage);
    }


    public int getChosenNumber() {
        return chosenNumber;
    }

    @FXML
    public void click(MouseEvent event) {
        Object source = event.getSource();

        if (source == level1) {
            clickLevel1();
        } else if (source == level2) {
            clickLevel2();
        } else if (source == level3) {
            clickLevel3();
        } else if (source == back) {
            clickBack();
        }
    }

    public void clickLevel1() {
        SoundEffect.play("/sound/click.wav");
    }

    public void clickLevel2() {
        SoundEffect.play("/sound/click.wav");
    }

    public void clickLevel3() {
        SoundEffect.play("/sound/click.wav");
    }

    public void clickBack() {
        SoundEffect.play("/sound/back.wav");
    }

    @FXML
    public void enter(MouseEvent event) {
        Object source = event.getSource();

        if (source == level1) {
            enterLevel1();
        } else if (source == level2) {
            enterLevel2();
        } else if (source == level3) {
            enterLevel3();
        } else if (source == back) {
            enterBack();
        }
    }

    public void enterLevel1() {
        level1.setOpacity(1);
        SoundEffect.play("/sound/button.wav");
    }

    public void enterLevel2() {
        level2.setOpacity(1);
        SoundEffect.play("/sound/button.wav");
    }

    public void enterLevel3() {
        level3.setOpacity(1);
        SoundEffect.play("/sound/button.wav");
    }

    public void enterBack() {
        back.setOpacity(1);
        SoundEffect.play("/sound/button.wav");
    }

    @FXML
    public void exit(MouseEvent event) {
        Object source = event.getSource();

        if (source == level1) {
            exitLevel1();
        } else if (source == level2) {
            exitLevel2();
        } else if (source == level3) {
            exitLevel3();
        } else if (source == back) {
            exitBack();
        }
    }

    public void exitLevel1() {
        level1.setOpacity(0.9);
    }

    public void exitLevel2() {
        level2.setOpacity(0.9);
    }

    public void exitLevel3() {
        level3.setOpacity(0.9);
    }

    public void exitBack() {
        back.setOpacity(0.7);
    }



    @FXML
    public void onBack(ActionEvent event) {

        Stage currentStage = (Stage) back.getScene().getWindow();
        try {
            Scene selectedScene = new Scene(FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("gameSelect.fxml"))));
            FadeTransition fadeOut = getFadeTransition(currentStage,selectedScene);//创建淡入淡出动画
            //currentStage.setScene(selectedScene);
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
}
