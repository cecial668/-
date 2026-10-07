package org.example.Sprite.MiyabiBullets;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Block;
import org.example.Sprite.Bullet;
import org.example.Sprite.Person;
import org.example.Sprite.Sprite;
import org.example.gametest.HelloApplication;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MiyabiUltimateCut extends Bullet {
    private Image particle;
    private boolean type=false;
    private int particleCreateTimer=0;
    private int timer=0;
    private int timeSpace=6;
    private boolean size;
    private double DMG=0.0017;
    private Clip sound;
    Image U1,U2,U3,U4,U5,U6,U7,U8,U9,U0;
    public MiyabiUltimateCut(Image image, double x, double y, double width, double height, GameScene gameScene, boolean direction, Person owner,int cutID,int timerSpace) {
        super(image, x, y, width, height, gameScene);
        this.direction = direction;//方向，false为向左
        this.owner=owner;//子弹的发射者
        this.KnockbackRatex=0;//水平击退力度
        this.KnockbackRatey=0;
        //击飞力度
        speed=0;//飞行速度
        this.timeSpace=timerSpace;
        System.out.println(direction);
        damage=DMG;
        if(cutID==0)
        {
            size=true;
            U1=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/21.png").toString());
            U2=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/22.png").toString());
            U3=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/23.png").toString());
            U4=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/24.png").toString());
            U5=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/25.png").toString());
            U6=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/26.png").toString());
            U7=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/27.png").toString());
            U8=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/28.png").toString());
            U9=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/29.png").toString());
            U0=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/20.png").toString());
            try{
                File audioFile2 = new File(HelloApplication.class.getResource("/Music/Hoshimi_Miyabi/Cut1.wav").getPath());
                AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                AudioFormat format2 = audioStream2.getFormat();
                DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                sound = (Clip) AudioSystem.getLine(info2);
                sound.open(audioStream2);
                FloatControl volumeControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(-8.0f); // 将音量降低10分贝（根据需要调整值）


            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
                // 处理异常，比如音频文件无法加载或播放等
            }
        }
        if(cutID==1)
        {
            size=false;
            U1=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/31.png").toString());
            U2=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/32.png").toString());
            U3=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/33.png").toString());
            U4=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/34.png").toString());
            U5=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/35.png").toString());
            U6=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/36.png").toString());
            U7=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/37.png").toString());
            U8=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/38.png").toString());
            U9=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/39.png").toString());
            U0=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/30.png").toString());
            try{
                File audioFile2 = new File(HelloApplication.class.getResource("/Music/Hoshimi_Miyabi/Cut1.wav").getPath());
                AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                AudioFormat format2 = audioStream2.getFormat();
                DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                sound = (Clip) AudioSystem.getLine(info2);
                sound.open(audioStream2);
                FloatControl volumeControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(-8.0f); // 将音量降低10分贝（根据需要调整值）


            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
                // 处理异常，比如音频文件无法加载或播放等
            }
        }
        if(cutID==2)
        {
            size=false;
            U1=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/41.png").toString());
            U2=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/42.png").toString());
            U3=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/43.png").toString());
            U4=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/44.png").toString());
            U5=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/45.png").toString());
            U6=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/46.png").toString());
            U7=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/47.png").toString());
            U8=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/48.png").toString());
            U9=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/49.png").toString());
            U0=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/40.png").toString());
            try{
                File audioFile2 = new File(HelloApplication.class.getResource("/Music/Hoshimi_Miyabi/Cut2.wav").getPath());
                AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                AudioFormat format2 = audioStream2.getFormat();
                DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                sound = (Clip) AudioSystem.getLine(info2);
                sound.open(audioStream2);
                FloatControl volumeControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(-8.0f); // 将音量降低10分贝（根据需要调整值）


            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
                // 处理异常，比如音频文件无法加载或播放等
            }
        }
        if(cutID==3)
        {
            size=true;
            U1=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/51.png").toString());
            U2=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/52.png").toString());
            U3=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/53.png").toString());
            U4=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/54.png").toString());
            U5=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/55.png").toString());
            U6=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/56.png").toString());
            U7=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/57.png").toString());
            U8=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/58.png").toString());
            U9=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/59.png").toString());
            U0=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/50.png").toString());
            try{
                File audioFile2 = new File(HelloApplication.class.getResource("/Music/Hoshimi_Miyabi/Cut3.wav").getPath());
                AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                AudioFormat format2 = audioStream2.getFormat();
                DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                sound = (Clip) AudioSystem.getLine(info2);
                sound.open(audioStream2);
                FloatControl volumeControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(-8.0f); // 将音量降低10分贝（根据需要调整值）


            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
                // 处理异常，比如音频文件无法加载或播放等
            }
        }
        if(cutID==4)
        {
            size=false;
            U1=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/61.png").toString());
            U2=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/62.png").toString());
            U3=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/63.png").toString());
            U4=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/64.png").toString());
            U5=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/65.png").toString());
            U6=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/66.png").toString());
            U7=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/67.png").toString());
            U8=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/68.png").toString());
            U9=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/69.png").toString());
            U0=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/60.png").toString());
            try{
                File audioFile2 = new File(HelloApplication.class.getResource("/Music/Hoshimi_Miyabi/Cut1.wav").getPath());
                AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                AudioFormat format2 = audioStream2.getFormat();
                DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                sound = (Clip) AudioSystem.getLine(info2);
                sound.open(audioStream2);
                FloatControl volumeControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(-8.0f); // 将音量降低10分贝（根据需要调整值）


            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
                // 处理异常，比如音频文件无法加载或播放等
            }
        }
        if(cutID==5)
        {
            size=true;
            U1=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/71.png").toString());
            U2=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/72.png").toString());
            U3=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/73.png").toString());
            U4=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/74.png").toString());
            U5=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/75.png").toString());
            U6=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/76.png").toString());
            U7=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/77.png").toString());
            U8=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/78.png").toString());
            U9=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/79.png").toString());
            U0=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/70.png").toString());
            try{
                File audioFile2 = new File(HelloApplication.class.getResource("/Music/Hoshimi_Miyabi/Cut3.wav").getPath());
                AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                AudioFormat format2 = audioStream2.getFormat();
                DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                sound = (Clip) AudioSystem.getLine(info2);
                sound.open(audioStream2);
                FloatControl volumeControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(-8.0f); // 将音量降低10分贝（根据需要调整值）


            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
                // 处理异常，比如音频文件无法加载或播放等
            }
        }
        if(cutID==6)
        {
            size=false;
            U1=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/81.png").toString());
            U2=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/82.png").toString());
            U3=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/83.png").toString());
            U4=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/84.png").toString());
            U5=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/85.png").toString());
            U6=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/86.png").toString());
            U7=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/87.png").toString());
            U8=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/88.png").toString());
            U9=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/89.png").toString());
            U0=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/UltimateCut/80.png").toString());
            try{
                File audioFile2 = new File(HelloApplication.class.getResource("/Music/Hoshimi_Miyabi/Cut2.wav").getPath());
                AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                AudioFormat format2 = audioStream2.getFormat();
                DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                sound = (Clip) AudioSystem.getLine(info2);
                sound.open(audioStream2);
                FloatControl volumeControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(-8.0f); // 将音量降低10分贝（根据需要调整值）


            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
                // 处理异常，比如音频文件无法加载或播放等
            }
        }
        this.image=U1;


        //this.group = group;
        //this.dir = dir;
    }


    public void paint(GraphicsContext graphicsContext)
    {
        move();
        timer+=1;
        if(size==true)
        {
            if(timer==1*timeSpace)
            {
                sound.setFramePosition(0);  // 重置到开始位置
                sound.start();
                image=U2;
                damage=DMG*0;
            }
            if(timer==2*timeSpace)
            {
                image=U3;
                damage=DMG*0.1;
            }
            if(timer==3*timeSpace)
            {
                image=U4;
                damage=DMG*0.3;
            }
            if(timer==4*timeSpace)
            {
                image=U5;
                damage=DMG*1;
            }
            if(timer==5*timeSpace)
            {
                image=U6;
                damage=DMG*2;
            }
            if(timer==7*timeSpace)
            {
                image=U7;
                damage=DMG*1;
            }
            if(timer==9*timeSpace)
            {
                image=U8;
                damage=DMG*0.7;
            }
            if(timer==11*timeSpace)
            {
                image=U9;
                damage=DMG*0.3;
            }
            if(timer==13*timeSpace)
            {
                image=U0;
                damage=DMG*0.1;
            }
            if(timer==15*timeSpace)
            {
                owner.normalBulletList.remove(this);
            }
        }
        if(size==false)
        {
            if(timer==1*timeSpace)
            {
                image=U2;
                damage=DMG*0;
            }
            if(timer==2*timeSpace)
            {
                image=U3;
                damage=DMG*0.1;
            }
            if(timer==3*timeSpace)
            {
                image=U4;
                damage=DMG*0.3;
            }
            if(timer==4*timeSpace)
            {
                image=U5;
                damage=DMG*1;
            }
            if(timer==5*timeSpace)
            {
                image=U7;
                damage=DMG*2;
            }
            if(timer==7*timeSpace)
            {
                image=U8;
                damage=DMG*1;
            }
            if(timer==9*timeSpace)
            {
                image=U9;
                damage=DMG*0.7;
            }
            if(timer==11*timeSpace)
            {
                image=U0;
                damage=DMG*0.3;
            }

            if(timer==13*timeSpace)
            {
                owner.normalBulletList.remove(this);
            }
        }


        super.paint(graphicsContext);
    }
    public void move()
    {


    }
    public void impactNormal(Sprite sprite)//检测与物体的碰撞
    {
        if ((sprite != null && !sprite.equals(this) && getContour().intersects(sprite.getContour()) && !(sprite instanceof Block)) || x < -50 || x > 1300)
        {

        }

    }
    public void explode()
    {


    }
}
