package org.example.Sprite.GameUIs;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Person;
import org.example.gametest.HelloApplication;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class UltimateChargingUI extends SampleUI{
    Person owner;
    Image notDone=new Image(HelloApplication.class.getResource("/images/GameUIs/AbilityUI/AbilityNotDoneBoard.png").toString());
    boolean readySoundPlay=false;
    Clip Sound;

    public UltimateChargingUI(Image image, double x, double y, double width, double height, GameScene gameScene, Person owner)
    {
        super(image, x, y, width, height, gameScene);
        this.owner=owner;
        this.image=notDone;
    }

    public void paint(GraphicsContext graphicsContext)
    {
        if(owner.legendID==0)
        {
            if(owner.ultimateTimer<=1200)
            {
                readySoundPlay=false;
                image=notDone;
                height=((double)owner.ultimateTimer/1400)*30;
            }
            else {
                    image=null;
                    if(readySoundPlay==false)
                    {
                        try{
                            File audioFile2 = new File(HelloApplication.class.getResource("/Music/PublicSounds/UltimateReady.wav").getPath());
                            AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                            AudioFormat format2 = audioStream2.getFormat();
                            DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                            Sound = (Clip) AudioSystem.getLine(info2);
                            Sound.open(audioStream2);
                            FloatControl volumeControl = (FloatControl) Sound.getControl(FloatControl.Type.MASTER_GAIN);
                            volumeControl.setValue(-2.7f); // 将音量降低10分贝（根据需要调整值）


                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                            e.printStackTrace();
                            // 处理异常，比如音频文件无法加载或播放等
                        }
                        if(gameScene.gameRunning==true)
                        {
                            Sound.setFramePosition(0);  // 重置到开始位置
                            Sound.start();


                        }
                        readySoundPlay=true;
                    }

                }





        }
        if(owner.legendID==1)
        {
            if(owner.ultimateTimer<=1500)
            {
                readySoundPlay=false;
                image=notDone;
                height=((double)owner.ultimateTimer/1500)*30;
            }
            else {
                image=null;
                if(readySoundPlay==false)
                {
                    try{
                        File audioFile2 = new File(HelloApplication.class.getResource("/Music/PublicSounds/UltimateReady.wav").getPath());
                        AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                        AudioFormat format2 = audioStream2.getFormat();
                        DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                        Sound = (Clip) AudioSystem.getLine(info2);
                        Sound.open(audioStream2);
                        FloatControl volumeControl = (FloatControl) Sound.getControl(FloatControl.Type.MASTER_GAIN);
                        volumeControl.setValue(-2.7f); // 将音量降低10分贝（根据需要调整值）


                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                        e.printStackTrace();
                        // 处理异常，比如音频文件无法加载或播放等
                    }
                    if(gameScene.gameRunning==true)
                    {
                        Sound.setFramePosition(0);  // 重置到开始位置
                        Sound.start();


                    }
                    readySoundPlay=true;
                }
            }

        }
        if(owner.legendID==2)
        {
            if(owner.ultimateTimer<=2000)
            {
                readySoundPlay=false;
                image=notDone;
                height=((double)owner.ultimateTimer/2000)*30;
            }
            else {
                image=null;
                if(readySoundPlay==false)
                {
                    try{
                        File audioFile2 = new File(HelloApplication.class.getResource("/Music/PublicSounds/UltimateReady.wav").getPath());
                        AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                        AudioFormat format2 = audioStream2.getFormat();
                        DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                        Sound = (Clip) AudioSystem.getLine(info2);
                        Sound.open(audioStream2);
                        FloatControl volumeControl = (FloatControl) Sound.getControl(FloatControl.Type.MASTER_GAIN);
                        volumeControl.setValue(-2.7f); // 将音量降低10分贝（根据需要调整值）


                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                        e.printStackTrace();
                        // 处理异常，比如音频文件无法加载或播放等
                    }
                    if(gameScene.gameRunning==true)
                    {
                        Sound.setFramePosition(0);  // 重置到开始位置
                        Sound.start();


                    }
                    readySoundPlay=true;
                }
            }

        }
        if(owner.legendID==3)
        {
            if(owner.ultimateTimer<=3000)
            {
                readySoundPlay=false;
                image=notDone;
                height=((double)owner.ultimateTimer/3000)*30;
            }
            else {
                image=null;
                if(readySoundPlay==false)
                {
                    try{
                        File audioFile2 = new File(HelloApplication.class.getResource("/Music/PublicSounds/UltimateReady.wav").getPath());
                        AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                        AudioFormat format2 = audioStream2.getFormat();
                        DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                        Sound = (Clip) AudioSystem.getLine(info2);
                        Sound.open(audioStream2);
                        FloatControl volumeControl = (FloatControl) Sound.getControl(FloatControl.Type.MASTER_GAIN);
                        volumeControl.setValue(-2.7f); // 将音量降低10分贝（根据需要调整值）


                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                        e.printStackTrace();
                        // 处理异常，比如音频文件无法加载或播放等
                    }
                    if(gameScene.gameRunning==true)
                    {
                        Sound.setFramePosition(0);  // 重置到开始位置
                        Sound.start();


                    }
                    readySoundPlay=true;

                }
            }

        }



        super.paint(graphicsContext);
    }
}
