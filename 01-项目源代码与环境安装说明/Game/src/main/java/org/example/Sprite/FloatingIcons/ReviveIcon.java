package org.example.Sprite.FloatingIcons;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Person;
import org.example.gametest.HelloApplication;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class ReviveIcon extends FloatingIcon{
    private int Timer=0;//内置计时器
    private Image three,two,one,done;
    private int playerID;
    private double targetx=0;
    private double Speed=0;
    Clip reviveCD;
    Clip reviveDone;

    public ReviveIcon(Image image, double x, double y, double width, double height, GameScene gameScene, Person owner,int playerID)
    {
        super(image, x, y, width, height, gameScene,owner);
        this.owner=owner;
        this.playerID=playerID;
        done=new Image(HelloApplication.class.getResource("/images/GameUIs/Revive.png").toString());
        if(playerID==0)
        {
            three=new Image(HelloApplication.class.getResource("/images/GameUIs/p1R3.png").toString());
            two=new Image(HelloApplication.class.getResource("/images/GameUIs/p1R2.png").toString());
            one=new Image(HelloApplication.class.getResource("/images/GameUIs/p1R.png").toString());
        }
        else {
            three=new Image(HelloApplication.class.getResource("/images/GameUIs/p2R3.png").toString());
            two=new Image(HelloApplication.class.getResource("/images/GameUIs/p2R2.png").toString());
            one=new Image(HelloApplication.class.getResource("/images/GameUIs/p2R.png").toString());

        }


        image=three;
    }
    public void paint(GraphicsContext graphicsContext)
    {
        if(Timer==0)
        {
            image=three;
            y=200;
            if(playerID==0)
            {
                x=-500;
                targetx=10;
            }
            else {
                x=1500;
                targetx=990;
            }
        }

        if(Timer<400)//寿命到期，吩咐主人删除自己
        {
            if(playerID==0)
            {
                x+=(targetx-x)*0.05;
                if(x>targetx)
                {
                    x=targetx;
                }

            }
            else
            {
                x-=(x-targetx)*0.05;
                if(x<targetx)
                {
                    x=targetx;
                }
            }

        }
        if(Timer==50)
        {
            try{
                File audioFile2 = new File(HelloApplication.class.getResource("/Music/PublicSounds/ReviveCD.wav").getPath());
                AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                AudioFormat format2 = audioStream2.getFormat();
                DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                reviveCD = (Clip) AudioSystem.getLine(info2);
                reviveCD.open(audioStream2);
                FloatControl volumeControl = (FloatControl) reviveCD.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(+1.0f);


            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
            reviveCD.setFramePosition(0);  // 重置到开始位置
            reviveCD.start();
        }
        if(Timer==200)
        {
            try{
                File audioFile2 = new File(HelloApplication.class.getResource("/Music/PublicSounds/ReviveCD.wav").getPath());
                AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                AudioFormat format2 = audioStream2.getFormat();
                DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                reviveCD = (Clip) AudioSystem.getLine(info2);
                reviveCD.open(audioStream2);
                FloatControl volumeControl = (FloatControl) reviveCD.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(+1.0f);


            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
            image=two;
            reviveCD.setFramePosition(0);  // 重置到开始位置
            reviveCD.start();

        }
        if(Timer==350)
        {
            try{
                File audioFile2 = new File(HelloApplication.class.getResource("/Music/PublicSounds/ReviveCD.wav").getPath());
                AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                AudioFormat format2 = audioStream2.getFormat();
                DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                reviveCD = (Clip) AudioSystem.getLine(info2);
                reviveCD.open(audioStream2);
                FloatControl volumeControl = (FloatControl) reviveCD.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(+1.0f);


            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
            image=one;
            reviveCD.setFramePosition(0);  // 重置到开始位置
            reviveCD.start();
        }
        Timer+=1;
        if(Timer==501)
        {
            try{
                File audioFile2 = new File(HelloApplication.class.getResource("/Music/PublicSounds/ReviveDone.wav").getPath());
                AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                AudioFormat format2 = audioStream2.getFormat();
                DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                reviveDone = (Clip) AudioSystem.getLine(info2);
                reviveDone.open(audioStream2);
                FloatControl volumeControl = (FloatControl) reviveDone.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(+1.0f);


            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
            reviveDone.setFramePosition(0);  // 重置到开始位置
            reviveDone.start();
        }
        if(Timer>=500)
        {

            image=done;
        }
        if(Timer>=550)
        {
            if(playerID==0)
            {
                x=x-Speed;

            }
            else
            {
                x=x+Speed;

            }
            Speed+=0.2;
        }
        if(Timer>650)
        {
            x=-500;
        }
        if(Timer>=1050)
        {
            owner.playerIcons.remove(this);
        }
        Timer+=1;


        super.paint(graphicsContext);
    }
}
