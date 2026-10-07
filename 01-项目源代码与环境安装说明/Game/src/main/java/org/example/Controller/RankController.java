package org.example.Controller;


import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.DataBase.DataBaseConnection;
import org.example.gametest.HelloApplication;
import org.example.sound.SoundEffect;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;


public class RankController {

    @FXML
    private Label myScore;

    @FXML
    private Button backButton;

    @FXML
    private Label scoreRank;

    @FXML
    private Button rankButton;

    @FXML
    public void showRank(ActionEvent event) {
        int rankNumber=0;
        DataBaseConnection dbc=new DataBaseConnection();
        dbc.run();
        StringBuilder sb=new StringBuilder();
        try {
            BufferedReader br=new BufferedReader(new FileReader(HelloApplication.class.getResource("/txt/rank.txt").getPath()));
            String line=br.readLine();
            while(line!=null&&rankNumber<5){
                rankNumber++;
                sb.append(line);
                sb.append("\n");
                line=br.readLine();
            }
            scoreRank.setText(sb.toString());
            StringBuilder sb1=new StringBuilder();
            BufferedReader br1=new BufferedReader(new FileReader(HelloApplication.class.getResource("/txt/MyScores.txt").getPath()));
            String line1=br1.readLine();
            if(line1!=null){
                sb1.append(line1);
            }
            myScore.setText(sb1.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    public void clickBackButton(MouseEvent event) {
        SoundEffect.play("/sound/click.wav");
    }

    @FXML
    public void brightenRankButton(MouseEvent event) {
        rankButton.setOpacity(1);
        SoundEffect.play("/sound/button.wav");
    }

    @FXML
    public void darkenRankButton(MouseEvent event) {
        rankButton.setOpacity(0.7);
    }

    @FXML
    public void backToMenu(ActionEvent event) {
        Stage currentStage = (Stage) backButton.getScene().getWindow();
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
    public void clickRankButton(MouseEvent event) {
        SoundEffect.play("/sound/click.wav");
    }

    @FXML
    public void brightenBackButton(MouseEvent event) {
        backButton.setOpacity(1);
        SoundEffect.play("/sound/button.wav");
    }

    @FXML
    public void darkenBackButton(MouseEvent event) {
        backButton.setOpacity(0.7);
    }
}

