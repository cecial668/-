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


public class ChoiceHeroController {
    @FXML
    private Button choice1;

    @FXML
    private Button choice2;

    @FXML
    private Button hero1;

    @FXML
    private Button hero2;

    @FXML
    private Button hero3;

    @FXML
    private Button hero4;

    @FXML
    private Button start;

    private static int p1LegendNO ; // 初始化为-1，表示未选择
    private static int p2LegendNO ; // 初始化为-1，表示未选择

    private boolean flag ;

    @FXML
    private void initialize() {
        start.setVisible(false); // 初始时隐藏开始按钮
        p1LegendNO=-1;
        p2LegendNO=-1;
        flag=false;
        choice1.setOpacity(1);
        choice2.setOpacity(0.1);
        //hero1.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> selectCharacter(0));
        //hero2.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> selectCharacter(1));
        //hero3.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> selectCharacter(2));
        //hero4.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> selectCharacter(3));
    }

    private void selectCharacter1(int heroNO) {
        p1LegendNO = heroNO;
    }
    private void selectCharacter2(int heroNO) {
        p2LegendNO = heroNO;
    }

    public void adjustTheBrightness() {
        choice1.setOpacity(0.3);
        choice2.setOpacity(1);
    }

    @FXML
    void onHero1ButtonAction(ActionEvent event) {
        if (!flag) {
            selectCharacter1(1);
            adjustTheBrightness();
            flag=true;
        }else {
            selectCharacter2(1);
            start.setVisible(true);
        }
    }

    @FXML
    void onHero2ButtonAction(ActionEvent event) {
        if (!flag) {
            selectCharacter1(2);
            adjustTheBrightness();
            flag=true;
        }else {
            selectCharacter2(2);
            start.setVisible(true);
        }
    }


    @FXML
    void onHero3ButtonAction(ActionEvent event) {
        if (!flag) {
            selectCharacter1(3);
            adjustTheBrightness();
            flag=true;
        }else {
            selectCharacter2(3);
            start.setVisible(true);
        }
    }

    @FXML
    void onHero4ButtonAction(ActionEvent event) {
        if (!flag) {
            selectCharacter1(4);
            adjustTheBrightness();
            flag=true;
        }else {
            selectCharacter2(4);
            start.setVisible(true);
        }
    }



    @FXML
    public void onStartAction(ActionEvent event) {
        boolean select=GameSelectController.getPatternChoice();
        if(select==false){
            Stage currentStage = (Stage) start.getScene().getWindow();
            try {
                Scene selectedScene = new Scene(FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("before.fxml"))));
                FadeTransition fadeOut = getFadeTransition(currentStage,selectedScene);//创建淡入淡出动画
                //currentStage.setScene(selectedScene);
                fadeOut.play();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(select==true){
            Stage currentStage = (Stage) start.getScene().getWindow();
            try {
                Scene selectedScene = new Scene(FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("before1.fxml"))));
                FadeTransition fadeOut = getFadeTransition(currentStage,selectedScene);//创建淡入淡出动画
                //currentStage.setScene(selectedScene);
                fadeOut.play();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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

    public int getP1LegendNO() {
        return p1LegendNO;
    }

    public int getP2LegendNO() {
        return p2LegendNO;
    }
    @FXML
    public void mouseClickedSelectHero(MouseEvent event) {
        Object source = event.getSource();  // 获取触发事件的控件

        if (source ==choice1) {
            clickChoice1();
        } else if (source == choice2) {
            clickChoice2();
        } else if (source == start) {
            clickStart();
        } else if (source == hero1) {
            clickHero1();
        } else if (source == hero2) {
            clickHero2();
        } else if (source == hero3) {
            clickHero3();
        } else if (source == hero4) {
            clickHero4();
        }
    }

    public void clickStart() {
        SoundEffect.play("/sound/click.wav");
    }

    public void clickChoice1() {
        SoundEffect.play("/sound/click.wav");
    }

    public void clickChoice2() {
        SoundEffect.play("/sound/click.wav");
    }

    public void clickHero1() {
        SoundEffect.play("/sound/click.wav");
    }

    public void clickHero2() {
        SoundEffect.play("/sound/click.wav");
    }

    public void clickHero3() {
        SoundEffect.play("/sound/click.wav");
    }

    public void clickHero4() {
        SoundEffect.play("/sound/click.wav");
    }

    @FXML
    public void mouseEnteredSelectHero(MouseEvent event) {
        // 判断哪个按钮被滑到了
        Object source = event.getSource();  // 获取触发事件的控件

        if (source ==choice1) {
            enterChoice1();
        } else if (source == choice2) {
            enterChoice2();
        } else if (source == hero1) {
            enterHero1();
        } else if (source == hero2) {
            enterHero2();
        } else if (source == hero3) {
            enterHero3();
        } else if (source == hero4) {
            enterHero4();
        } else if (source == start) {
            enterStart();
        }
    }

    public void enterChoice1() {
        choice1.setOpacity(1);
        SoundEffect.play("/sound/button.wav");
    }

    public void enterChoice2() {
        choice2.setOpacity(1);
        SoundEffect.play("/sound/button.wav");
    }

    public void enterHero1() {
        hero1.setOpacity(1);
        SoundEffect.play("/sound/button.wav");
    }

    public void enterHero2() {
        hero2.setOpacity(1);
        SoundEffect.play("/sound/button.wav");
    }

    public void enterHero3() {
        hero3.setOpacity(1);
        SoundEffect.play("/sound/button.wav");
    }

    public void enterHero4() {
        hero4.setOpacity(1);
        SoundEffect.play("/sound/button.wav");
    }

    public void enterStart() {
        start.setOpacity(1);
        SoundEffect.play("/sound/button.wav");
    }

    @FXML
    public void mouseExitedSelectHero(MouseEvent event) {
        // 判断哪个按钮被滑到了
        Object source = event.getSource();  // 获取触发事件的控件

        if (source ==choice1) {
            exitChoice1();
        } else if (source == choice2) {
            exitChoice2();
        } else if (source == hero1) {
            exitHero1();
        } else if (source == hero2) {
            exitHero2();
        } else if (source == hero3) {
            exitHero3();
        } else if (source == hero4) {
            exitHero4();
        } else if (source == start) {
            exitStart();
        }
    }

    public void exitChoice1() {
        choice1.setOpacity(0.7);
    }

    public void exitChoice2() {
        choice2.setOpacity(0.7);
    }

    public void exitHero1() {
        hero1.setOpacity(0.7);
    }

    public void exitHero2() {
        hero2.setOpacity(0.7);
    }

    public void exitHero3() {
        hero3.setOpacity(0.7);
    }

    public void exitHero4() {
        hero4.setOpacity(0.7);
    }

    public void exitStart() {
        start.setOpacity(0.7);
    }

    public static int getP1LengedNo(){
        return p1LegendNO;
    }
    public static int getP2LengedNo(){
        return p2LegendNO;
    }


}
