package org.example.Sprite.GameUIs;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.gametest.HelloApplication;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class StartCountDownUI extends SampleUI{
    private int Timer=0;//内置计时器
    private Image three,two,one,done;
    private int playerID;
    private double targety=0;
    private double Speed=0;
    private Clip CDthree;
    private Clip CDtwo;
    private Clip CDone;
    private Clip Box;
    private Clip Start;

    public StartCountDownUI(Image image, double x, double y, double width, double height, GameScene gameScene)
    {
        super(image, x, y, width, height, gameScene);
        this.playerID=playerID;
        done=new Image(HelloApplication.class.getResource("/images/GameUIs/CDFIGHT.png").toString());
        three=new Image(HelloApplication.class.getResource("/images/GameUIs/CD3.png").toString());
        two=new Image(HelloApplication.class.getResource("/images/GameUIs/CD2.png").toString());
        one=new Image(HelloApplication.class.getResource("/images/GameUIs/CD1.png").toString());
        image=three;
        try{
            File audioFile2 = new File(HelloApplication.class.getResource("/Music/PublicSounds/three.wav").getPath());
            AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
            AudioFormat format2 = audioStream2.getFormat();
            DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
            CDthree = (Clip) AudioSystem.getLine(info2);
            CDthree.open(audioStream2);


        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            // 处理异常，比如音频文件无法加载或播放等
        }
        try{
            File audioFile2 = new File(HelloApplication.class.getResource("/Music/PublicSounds/two.wav").getPath());
            AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
            AudioFormat format2 = audioStream2.getFormat();
            DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
            CDtwo = (Clip) AudioSystem.getLine(info2);
            CDtwo.open(audioStream2);


        } catch (UnsupportedAudioFileException | IOException |LineUnavailableException e) {
            e.printStackTrace();
            // 处理异常，比如音频文件无法加载或播放等
        }
        try{
            File audioFile2 = new File(HelloApplication.class.getResource("/Music/PublicSounds/one.wav").getPath());
            AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
            AudioFormat format2 = audioStream2.getFormat();
            DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
            CDone = (Clip) AudioSystem.getLine(info2);
            CDone.open(audioStream2);


        } catch (UnsupportedAudioFileException | IOException |LineUnavailableException e) {
            e.printStackTrace();
            // 处理异常，比如音频文件无法加载或播放等
        }
        try{
            File audioFile2 = new File(HelloApplication.class.getResource("/Music/PublicSounds/start(1).wav").getPath());
            AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
            AudioFormat format2 = audioStream2.getFormat();
            DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
            Start = (Clip) AudioSystem.getLine(info2);
            Start.open(audioStream2);


        } catch (UnsupportedAudioFileException | IOException |LineUnavailableException e) {
            e.printStackTrace();
            // 处理异常，比如音频文件无法加载或播放等
        }

    }
    public void paint(GraphicsContext graphicsContext)
    {
        if(Timer==0 ||Timer==130 ||Timer==260||Timer==390)
        {
            if(Timer==0)
            {
                image=three;
            }
            else if(Timer==130)
            {
                image=two;
            }
            else if(Timer==260)
            {
                image=one;

            }
            else {
                image=done;

            }
            y=2000;
            targety=300;
            if(Timer==390)
            {
                width=1000;
                height=500;
                x=150;
                targety=175;

            }
        }

        if(Timer<100)//寿命到期，吩咐主人删除自己
        {
            if(y>targety+1)
            {
                y-=(y-targety)*0.15;
            }
            else
            {
                Speed=1;
                y-=Speed;
            }
            if(Timer==5)
            {
                CDthree.setFramePosition(0);  // 重置到开始位置
                CDthree.start();
            }
        }
        else if(Timer<130)
        {
            Speed+=3;
            y-=Speed;

        }
        else if(Timer<230)//寿命到期，吩咐主人删除自己
        {
            if(y>targety+1)
            {
                y-=(y-targety)*0.15;
            }
            else
            {
                Speed=1;
                y-=Speed;
            }
            if(Timer==160)
            {
                CDtwo.setFramePosition(0);  // 重置到开始位置
                CDtwo.start();
            }
        }
        else if(Timer<260)
        {
            Speed+=3;
            y-=Speed;

        }
        else if(Timer<360)//寿命到期，吩咐主人删除自己
        {
            if(y>targety+1)
            {
                y-=(y-targety)*0.1;
            }
            else
            {
                Speed=1;
                y-=Speed;
            }
            if(Timer==265)
            {
                CDone.setFramePosition(0);  // 重置到开始位置
                CDone.start();
            }
        }
        else if(Timer<390)
        {
            Speed+=3;
            y-=Speed;

        }
        else if(Timer<490)//寿命到期，吩咐主人删除自己
        {
            if(y>targety+1)
            {
                y-=(y-targety)*0.15;
            }
            else
            {
                Speed=1;
                y-=Speed;
            }
            if(Timer==400)
            {
                Start.setFramePosition(0);  // 重置到开始位置
                Start.start();
            }
        }
        else if(Timer<520)
        {
            Speed+=3;
            y-=Speed;

        }
        if(Timer>=520)
        {
            y=-400;
            gameScene.otherUIList.remove(this);
        }
        if(Timer>=1520)
        {
            gameScene.otherUIList.remove(this);
        }
        Timer+=1;


        super.paint(graphicsContext);
    }
}
