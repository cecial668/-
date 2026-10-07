package org.example.Sprite.Legends;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.FloatingIcons.*;
import org.example.Sprite.Person;
import org.example.Sprite.VegetaBullet.VegetaBullet;
import org.example.Sprite.VegetaBullet.VegetaUltimateBullet;
import org.example.Sprite.VegetaBullet.VegetaWaveBullet;
import org.example.gametest.HelloApplication;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class Vegeta extends Person {
    //左方向波
    public Image attackImgL=null;
    Clip explode;
    Clip normalAtk;
    Clip upperCut;
    //右方向波
    public Image attackImgR=null;
    double preX;
    double preY;
    boolean preDir;
    int UltimateTime=0;
    //右方向技能波
    public Image EbulletImgL=null;
    //左方向技能波
    public Image EbulletImgR=null;
    //必杀技
    public Image UbulletImgL=null;
    public Image UbulletImgR=null;
    //强化子弹图标
    public Image shootingEnhanceImg=null;
    //双发子弹图标
    public Image DoubleShootIcon=null;
    public Image ultimateLeft2=null;
    public Image ultimateRight2=null;
    public Image wave=null;
    public Image shanggouQuanL=null;
    public Image shanggouQuanR=null;
    public int action_counter=0;//动作计数器,当值为3时普攻改为上勾拳
    private int UltimateTimer=0;//表示角色大招期间的计时器
    private Image vegetaUltimateWave=null;
    Clip AShoot;




    public Vegeta(Image image, double x, double y, double width, double height, GameScene gameScene, int playerID)
    {
        super(image, x, y, width, height, gameScene, playerID);
        this.legendID=3;
        wave=new Image(HelloApplication.class.getResource("/images/Vegeta/Bullets/ball.png").toString());
        EbulletImgL=new Image(HelloApplication.class.getResource("/images/Archer/Bullets/Enhanced_Arrow_Left.png").toString());
        EbulletImgR=new Image(HelloApplication.class.getResource("/images/Archer/Bullets/Enhanced_Arrow_Right.png").toString());
        //shootingEnhanceImg=new Image(HelloApplication.class.getResource("/images/Archer/Icons/Archer_ShootingEnhance.png").toString());
        noneL=new Image(HelloApplication.class.getResource("/images/Vegeta/Actions/noneL.png").toString());
        noneR=new Image(HelloApplication.class.getResource("/images/Vegeta/Actions/noneR.png").toString());
        left1=new Image(HelloApplication.class.getResource("/images/Vegeta/Actions/runLeft.png").toString());
        left2=new Image(HelloApplication.class.getResource("/images/Vegeta/Actions/runLeft2.png").toString());
        right1=new Image(HelloApplication.class.getResource("/images/Vegeta/Actions/runRight.png").toString());
        right2=new Image(HelloApplication.class.getResource("/images/Vegeta/Actions/runRight2.png").toString());
        upleft=new Image(HelloApplication.class.getResource("/images/Vegeta/Actions/JumpL.png").toString());
        upright=new Image(HelloApplication.class.getResource("/images/Vegeta/Actions/JumpR.png").toString());
        airleft=new Image(HelloApplication.class.getResource("/images/Vegeta/Actions/JumpL.png").toString());
        airright=new Image(HelloApplication.class.getResource("/images/Vegeta/Actions/JumpR.png").toString());
        normalAttackleft=new Image(HelloApplication.class.getResource("/images/Vegeta/Actions/quanLeft.png").toString());
        normalAttackright=new Image(HelloApplication.class.getResource("/images/Vegeta/Actions/quanRight.png").toString());
        abilityLeft=new Image(HelloApplication.class.getResource("/images/Vegeta/Actions/AbilityL.png").toString());
        abilityRight=new Image(HelloApplication.class.getResource("/images/Vegeta/Actions/AbilityR.png").toString());
        ultimateLeft=new Image(HelloApplication.class.getResource("/images/Vegeta/Actions/Ultimate1L.png").toString());
        ultimateLeft2=new Image(HelloApplication.class.getResource("/images/Vegeta/Actions/Ultimate2L.png").toString());
        ultimateRight=new Image(HelloApplication.class.getResource("/images/Vegeta/Actions/Ultimate1R.png").toString());
        ultimateRight2=new Image(HelloApplication.class.getResource("/images/Vegeta/Actions/Ultimate2R.png").toString());
        shanggouQuanL=new Image(HelloApplication.class.getResource("/images/Vegeta/Actions/shanggouQuanL.png").toString());
        shanggouQuanR=new Image(HelloApplication.class.getResource("/images/Vegeta/Actions/shanggouQuanR.png").toString());
        vegetaUltimateWave=new Image(HelloApplication.class.getResource("/images/Vegeta/Particles/UltimateParticle2.png").toString());
    }
    @Override
    public void normalAbility()//普通攻击
    {
        if(abilityTimer>200)//普通技能的冷却时间（该Timer每一帧都会加1），如果超过1000表示冷却完成
        {
            abilityTime=1;//角色贴图呈现出使用技能的样子的计时器
            abilityTimer=0;//重置技能cd0
            normalBulletList.add(new VegetaWaveBullet(wave, x + 0.5*width, y -0.6*width, 75, 75, gameScene, facingDirection, this));
            try{
                File audioFile2 = new File(HelloApplication.class.getResource("/Music/Vegeta/Ball.wav").getPath());
                AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                AudioFormat format2 = audioStream2.getFormat();
                DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                AShoot = (Clip) AudioSystem.getLine(info2);
                AShoot.open(audioStream2);
                FloatControl volumeControl = (FloatControl) AShoot.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(-4.0f); // 将音量降低10分贝（根据需要调整值）
                AShoot.setFramePosition(0);  // 重置到开始位置
                AShoot.start();

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
                // 处理异常，比如音频文件无法加载或播放等
            }


        }

    }


    @Override
    public void ultimateAbility()//绝招的冷却时间
    {
        if(ultimateTimer>3000)
        {
            if(UltimateTime==0)
            {
                    ultimateTimer=0;
                    ultimateAbilityTime=1;//角色贴图变为大招样子的timer
                    UltimateTimer+=1;//进入大招状态，这个变量大于1表示大招正在持续
                    UltimateTime=1;

             }




        }

    }
    @Override
    public void normalAttack()//普通攻击的冷却时间，每20帧调用一次
    {
        if(normalAttackTimer>30)//冷却好了
        {
            normalAttackTimer=0;//普攻进入冷却
            normalATime=1;//普攻贴图计时器,0时不展示贴图，大于0展示贴图,增加到一定时间变为0
            //normalBulletList.add(new VegetaBullet(EbulletImgL, x + 0.5*width, y - 0.1*width, 37, 30, gameScene, facingDirection, this,false));

            if (action_counter<3) {
                try{
                    File audioFile2 = new File(HelloApplication.class.getResource("/Music/Vegeta/Normal.wav").getPath());
                    AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                    AudioFormat format2 = audioStream2.getFormat();
                    DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                    normalAtk = (Clip) AudioSystem.getLine(info2);
                    normalAtk.open(audioStream2);
                    FloatControl volumeControl = (FloatControl) normalAtk.getControl(FloatControl.Type.MASTER_GAIN);
                    volumeControl.setValue(+0.0f); // 将音量降低10分贝（根据需要调整值）
                    normalAtk.setFramePosition(0);  // 重置到开始位置
                    normalAtk.start();

                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    e.printStackTrace();
                    // 处理异常，比如音频文件无法加载或播放等
                }
                normalBulletList.add(new VegetaBullet(null, x + 0.5*width, y - 0.1*width, 150, 50, gameScene, facingDirection, this,false));
                playerIcons.add(new VegetaNormalAttackIcon(null,x+0.5*width, y-150, 150, 100, gameScene, this));
            }else {
                try{
                    File audioFile2 = new File(HelloApplication.class.getResource("/Music/Vegeta/UpPunch.wav").getPath());
                    AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                    AudioFormat format2 = audioStream2.getFormat();
                    DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                    upperCut = (Clip) AudioSystem.getLine(info2);
                    upperCut.open(audioStream2);
                    FloatControl volumeControl = (FloatControl) upperCut.getControl(FloatControl.Type.MASTER_GAIN);
                    volumeControl.setValue(+0.0f); // 将音量降低10分贝（根据需要调整值）
                    upperCut.setFramePosition(0);  // 重置到开始位置
                    upperCut.start();

                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    e.printStackTrace();
                    // 处理异常，比如音频文件无法加载或播放等
                }
                normalBulletList.add(new VegetaBullet(null, x + 0.5*width, y-110, 155, 170, gameScene, facingDirection, this,true));
                playerIcons.add(new VegetaUpperCutIcon(null,x+0.5*width, y, 130, 170, gameScene, this));
            //
            }
            action_counter++;

        }
    }



    @Override
    public void showNormalAttack()//普通攻击动作的展示时间，不用改,每一帧都被调用一次
    {
        if(facingDirection==false)
        {
            if (action_counter<=3) {

                image = normalAttackleft;
            } else  {
                image=shanggouQuanL;
                //action_counter=0;
            }
        }
        else {
            if (action_counter<=3) {
                image = normalAttackright;
            } else  {
                image=shanggouQuanR;
                //action_counter=0;
            }
        }
        if(normalATime>20)
        {
            normalATime=0;
            if(action_counter>3){
                action_counter=0;
            }
        }

    }
    @Override
    public void showAbilityAttack()//普通技能动作的展示时间
    {
        if(facingDirection==false)
        {
            image=abilityLeft;
        }
        else {
            image=abilityRight;
        }
        if(abilityTime>30)
        {
            abilityTime=0;
        }

    }
    @Override
    public void showUltimateAttack()//绝招的展示时间
    {
        if(preDir==false)
        {
            if (ultimateAbilityTime<=90){
                image=ultimateLeft;
            }else {
                image=ultimateLeft2;
            }
        }
        else {
            if (ultimateAbilityTime<=90){
                image=ultimateRight;
            }else {
                image=ultimateRight2;
            }
        }
        if(ultimateAbilityTime>230)
        {
            ultimateAbilityTime=0;
        }

    }
    @Override
    public void paint(GraphicsContext graphicsContext)//每一帧都会被调用的方法
    {
        if (UltimateTime > 0)//大招逻辑
        {


            if (UltimateTime == 1) {
                preX = x;
                preY = y;
                preDir = facingDirection;
                try{
                    File audioFile2 = new File(HelloApplication.class.getResource("/Music/Vegeta/Explode.wav").getPath());
                    AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                    AudioFormat format2 = audioStream2.getFormat();
                    DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                    explode = (Clip) AudioSystem.getLine(info2);
                    explode.open(audioStream2);
                    FloatControl volumeControl = (FloatControl) explode.getControl(FloatControl.Type.MASTER_GAIN);
                    volumeControl.setValue(+3.0f); // 将音量降低10分贝（根据需要调整值）
                    explode.setFramePosition(0);  // 重置到开始位置
                    explode.start();

                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    e.printStackTrace();
                    // 处理异常，比如音频文件无法加载或播放等
                }

            } else {
                if(this.hp>0)
                {
                    x = preX;
                    y = preY;
                    facingDirection = preDir;
                    x_speed=0;
                    y_speed=0;

                }

            }
            UltimateTime += 1;//大招计时器+1
            if (UltimateTime >= 260)//大招计时器到点，则退出大招强化状态
            {
                UltimateTime = 0;
            }
            if(UltimateTime==100)
            {
                normalBulletList.add(new VegetaUltimateBullet(null, x + 0.5*width, y + 0*width-15, 1, 51, gameScene, facingDirection, this,3));
            }
            if(UltimateTime==120)
            {
                normalBulletList.add(new VegetaUltimateBullet(null, x + 0.5*width, y + 0*width-15, 1, 51, gameScene, facingDirection, this,2));
            }
            if(UltimateTime==140)
            {
                normalBulletList.add(new VegetaUltimateBullet(null, x + 0.5*width, y + 0*width-15, 1, 51, gameScene, facingDirection, this,1));
            }
            if(UltimateTime==160)
            {
                normalBulletList.add(new VegetaUltimateBullet(null, x + 0.5*width, y + 0*width-15, 1, 51, gameScene, facingDirection, this,0));
            }




        }
        super.paint(graphicsContext);
    }

}
