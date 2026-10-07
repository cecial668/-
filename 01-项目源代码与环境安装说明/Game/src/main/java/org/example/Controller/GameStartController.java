package org.example.Controller;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.gametest.HelloApplication;
import org.example.sound.SoundEffect;

import java.io.IOException;
import java.util.Objects;

public class GameStartController {
    @FXML
    private ImageView leftOne;

    @FXML
    private ImageView leftTwo;

    @FXML
    private ImageView leftThree;

    @FXML
    private ImageView rightOne;

    @FXML
    private ImageView rightTwo;

    @FXML
    private ImageView rightThree;

    private double initialLeftOneX, initialLeftTwoX, initialLeftThreeX, initialRightOneX, initialRightTwoX, initialRightThreeX;
    private double targetLeftOneX, targetLeftTwoX, targetLeftThreeX,  targetRightOneX, targetRightTwoX, targetRightThreeX;

    public GameStartController() {
        // 初始化ImageView的位置
        initialLeftOneX = 0;
        initialLeftTwoX = 0;
        initialLeftThreeX = 0;
        initialRightOneX = 1200;
        initialRightTwoX = 1200;
        initialRightThreeX = 1200;
        // 设置目标位置
        targetLeftOneX = 415;
        targetLeftTwoX = 415;
        targetLeftThreeX = 415;
        targetRightOneX = 785;
        targetRightTwoX = 785;
        targetRightThreeX = 785;
    }
    @FXML
    private Button gameStartButton;

    @FXML
    private Button gameHelpButton;

    @FXML
    private Button gameExitButton;


    // 存储 FadeTransition 对象，以便可以取消之前的动画
    private FadeTransition leftOneFadeTransition = null;
    private FadeTransition leftTwoFadeTransition = null;
    private FadeTransition leftThreeFadeTransition = null;
    private FadeTransition rightOneFadeTransition = null;
    private FadeTransition rightTwoFadeTransition = null;
    private FadeTransition rightThreeFadeTransition = null;

    @FXML
    public void handleStartButtonAction() {
        Stage currentStage = (Stage) gameStartButton.getScene().getWindow();
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


    @FXML

    public void handleHelpButtonAction() {
        try {
            // 加载HelpDialogPage.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/gametest/HelpDialogPage.fxml"));
            if (loader != null) {
                System.out.println("file found.");
            }
            Parent root = loader.load();

            // 创建新的Stage
            Stage helpDialogStage = new Stage();
            helpDialogStage.setTitle("Help Dialog");

            // 设置Scene
            Scene scene = new Scene(root, 640, 600);
            helpDialogStage.setScene(scene);

            // 显示Stage
            helpDialogStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleExitButtonAction() {
        Stage stage = (Stage) gameExitButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    @FXML
    public void mouseClickedStartGame(MouseEvent event) {
        Object source = event.getSource();

        if (source ==gameStartButton) {
            clickGameStartButton();
        } else if (source == gameHelpButton) {
                clickGameHelpButton();
        } else if (source == gameExitButton) {
            clickGameExitButton();
        }
    }

    public void clickGameStartButton() {
        SoundEffect.play("/sound/click.wav");
    }

    public void clickGameHelpButton() {
        SoundEffect.play("/sound/click.wav");
    }

    public void clickGameExitButton() {
        SoundEffect.play("/sound/click.wav");
    }

    @FXML
    public void mouseEnteredStartGame(MouseEvent event) {
        Object source = event.getSource();

        if (source ==gameStartButton) {
            enterGameStartButton();
        } else if (source == gameHelpButton) {
            enterGameHelpButton();
        } else if (source == gameExitButton) {
            enterGameExitButton();
        }
    }

    public void enterGameStartButton() {
        gameStartButton.setOpacity(1);
        Timeline enterAnimation = createAnimation(leftOne, initialLeftOneX, targetLeftOneX);
        enterAnimation.play();
        enterAnimation = createAnimation(rightOne, initialRightOneX, targetRightOneX);
        enterAnimation.play();
        // 取消之前的淡出动画，以确保可以重新开始淡入动画
        if (leftOneFadeTransition != null) {
            leftOneFadeTransition.stop();
        }
        if (rightOneFadeTransition != null) {
            rightOneFadeTransition.stop();
        }

        // 创建新的淡入动画
        leftOneFadeTransition = createFadeIn(leftOne);
        rightOneFadeTransition = createFadeIn(rightOne);

        // 播放淡入动画
        leftOneFadeTransition.play();
        rightOneFadeTransition.play();
        SoundEffect.play("/sound/button.wav");
    }

    public void enterGameExitButton() {
        gameExitButton.setOpacity(1);
        Timeline enterAnimation = createAnimation(leftThree, initialLeftThreeX, targetLeftThreeX);
        enterAnimation.play();
        enterAnimation = createAnimation(rightThree, initialRightThreeX, targetRightThreeX);
        enterAnimation.play();
        if (leftThreeFadeTransition != null) {
            leftThreeFadeTransition.stop();
        }
        if (rightThreeFadeTransition != null) {
            rightThreeFadeTransition.stop();
        }

        leftThreeFadeTransition = createFadeIn(leftThree);
        rightThreeFadeTransition = createFadeIn(rightThree);

        leftThreeFadeTransition.play();
        rightThreeFadeTransition.play();
        SoundEffect.play("/sound/button.wav");
    }


    public void enterGameHelpButton() {
        gameHelpButton.setOpacity(1);
        Timeline enterAnimation = createAnimation(leftTwo, initialLeftTwoX, targetLeftTwoX);
        enterAnimation.play();
        enterAnimation = createAnimation(rightTwo, initialRightTwoX, targetRightTwoX);
        enterAnimation.play();
        if (leftTwoFadeTransition != null) {
            leftTwoFadeTransition.stop();
        }
        if (rightTwoFadeTransition != null) {
            rightTwoFadeTransition.stop();
        }

        leftTwoFadeTransition = createFadeIn(leftTwo);
        rightTwoFadeTransition = createFadeIn(rightTwo);

        leftTwoFadeTransition.play();
        rightTwoFadeTransition.play();
        SoundEffect.play("/sound/button.wav");
    }


    @FXML
    public void mouseExitedStartGame(MouseEvent event) {
        // 判断哪个按钮脱离了滑动
        Object source = event.getSource();  // 获取触发事件的控件

        if (source == gameStartButton) {
            exitGameStartButton();
        } else if (source == gameHelpButton) {
            exitGameHelpButton();
        } else if (source == gameExitButton) {
            exitGameExitButton();
        }
    }

    public void exitGameStartButton() {
        gameStartButton.setOpacity(0.7);
        // 创建并启动鼠标离开时的动画
        Timeline exitAnimation = createAnimation(leftOne, targetLeftOneX, initialLeftOneX);
        exitAnimation.play();
        exitAnimation = createAnimation(rightOne, targetRightOneX, initialRightOneX);
        exitAnimation.play();
        if (leftOneFadeTransition != null) {
            leftOneFadeTransition.stop();
        }
        if (rightOneFadeTransition != null) {
            rightOneFadeTransition.stop();
        }

        // 创建新的淡出动画
        leftOneFadeTransition = createFadeOut(leftOne);
        rightOneFadeTransition = createFadeOut(rightOne);

        // 播放淡出动画
        leftOneFadeTransition.play();
        rightOneFadeTransition.play();
    }

    public void exitGameHelpButton() {
        gameHelpButton.setOpacity(0.7);
        Timeline exitAnimation = createAnimation(leftTwo, targetLeftTwoX, initialLeftTwoX);
        exitAnimation.play();
        exitAnimation = createAnimation(rightTwo, targetRightTwoX, initialRightTwoX);
        exitAnimation.play();
        if (leftTwoFadeTransition != null) {
            leftTwoFadeTransition.stop();
        }
        if (rightTwoFadeTransition != null) {
            rightTwoFadeTransition.stop();
        }

        leftTwoFadeTransition = createFadeOut(leftTwo);
        rightTwoFadeTransition = createFadeOut(rightTwo);

        leftTwoFadeTransition.play();
        rightTwoFadeTransition.play();
    }

    public void exitGameExitButton() {
        gameExitButton.setOpacity(0.7);
        Timeline exitAnimation = createAnimation(leftThree, targetLeftThreeX, initialLeftThreeX);
        exitAnimation.play();
        exitAnimation = createAnimation(rightThree, targetRightThreeX, initialRightThreeX);
        exitAnimation.play();
        if (leftThreeFadeTransition != null) {
            leftThreeFadeTransition.stop();
        }
        if (rightThreeFadeTransition != null) {
            rightThreeFadeTransition.stop();
        }

        leftThreeFadeTransition = createFadeOut(leftThree);
        rightThreeFadeTransition = createFadeOut(rightThree);

        leftThreeFadeTransition.play();
        rightThreeFadeTransition.play();
    }

    public Timeline createAnimation(final Node node, double fromX, double toX) {
        Timeline animation = new Timeline();
        KeyValue kv = new KeyValue(node.layoutXProperty(), toX, Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.millis(300), kv);
        animation.getKeyFrames().add(kf);
        return animation;
    }

    private FadeTransition createFadeIn(ImageView imageView) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(300), imageView);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setInterpolator(Interpolator.EASE_BOTH);
        return fadeTransition;
    }

    private FadeTransition createFadeOut(ImageView imageView) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(300), imageView);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setInterpolator(Interpolator.EASE_BOTH);
        return fadeTransition;
    }
}