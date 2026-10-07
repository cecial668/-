package org.example.Sprite.Legends;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.CecilNormalBullet.CeceilEnhancedBullet;
import org.example.Sprite.CecilNormalBullet.CecilNormalBullet;
import org.example.Sprite.CecilSkill1Bullet.Skill1Bullet;
import org.example.Sprite.CecilSkill2Bullet.Ceceil_ShootingStar;
import org.example.Sprite.Person;
import org.example.gametest.HelloApplication;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

//法师类，为一个具体的角色
public class Cecil extends Person {
    //左方向普攻
    public Image bulletImgL=null;//这些图片都是角色不同运动状态的贴图
    //右方向普攻
    public Image bulletImgR=null;//
    int NAcount=0;

    //技能1-1
    public Image EbulletImg1=null;


    //技能2-1
    public Image UbulletImg1=null;
    //技能2-2
    public Image UbulletImg2=null;
    //技能2-3
    public Image UbulletImg3=null;
    Clip normalAtk;
    Clip wind;
    Clip ShootingStr;
    Clip ExplodeSound;

    //强化自身图标
    public Image EnhanceImg=null;
    private static final Random RANDOM = new Random();

    private int EnhanceTimer=0;//表示技能强化期间的计时器
    private boolean Enhance=false;//true表示角色正在被强化
    private int UltimateTime=0;//表示角色使用大招持续时间
    double randPos=0;
    double randSize=0;
    public Cecil(Image image, double x, double y, double width, double height, GameScene gameScene, int playerID)
    {
        super(image,x,y,width,height,gameScene,playerID);
        this.legendID=2;
        //普攻图标
        bulletImgL=new Image(HelloApplication.class.getResource("/images/Cecil/Bullets/普攻弹道左.png").toString());
        bulletImgR=new Image(HelloApplication.class.getResource("/images/Cecil/Bullets/普攻弹道右.png").toString());


        //技能2图标
        UbulletImg1=new Image(HelloApplication.class.getResource("/images/Archer/Bullets/Ultimate_bullet_Left.png").toString());
        UbulletImg2=new Image(HelloApplication.class.getResource("/images/Archer/Bullets/Ultimate_bullet_Right.png").toString());
        UbulletImg3=new Image(HelloApplication.class.getResource("/images/Archer/Icons/Archer_Double_Icon.png").toString());
        EnhanceImg=new Image(HelloApplication.class.getResource("/images/Archer/Icons/Archer_ShootingEnhance.png").toString());
        //角色移动图像
        noneL=new Image(HelloApplication.class.getResource("/images/Cecil/Actions/静止左.png").toString());
        noneR=new Image(HelloApplication.class.getResource("/images/Cecil/Actions/静止右.png").toString());
        left1=new Image(HelloApplication.class.getResource("/images/Cecil/Actions/左走1.png").toString());
        left2=new Image(HelloApplication.class.getResource("/images/Cecil/Actions/左走2.png").toString());
        right1=new Image(HelloApplication.class.getResource("/images/Cecil/Actions/右走1.png").toString());
        right2=new Image(HelloApplication.class.getResource("/images/Cecil/Actions/右走2.png").toString());
        upleft=new Image(HelloApplication.class.getResource("/images/Cecil/Actions/左跳.png").toString());
        upright=new Image(HelloApplication.class.getResource("/images/Cecil/Actions/右跳.png").toString());
        airleft=new Image(HelloApplication.class.getResource("/images/Cecil/Actions/左跳.png").toString());
        airright=new Image(HelloApplication.class.getResource("/images/Cecil/Actions/右跳.png").toString());
        //角色释放技能的图像
        normalAttackleft=new Image(HelloApplication.class.getResource("/images/Cecil/Actions/技能1左.png").toString());
        normalAttackright=new Image(HelloApplication.class.getResource("/images/Cecil/Actions/技能1右.png").toString());
        abilityLeft=new Image(HelloApplication.class.getResource("/images/Cecil/Actions/技能1左.png").toString());
        abilityRight=new Image(HelloApplication.class.getResource("/images/Cecil/Actions/技能1右.png").toString());
        ultimateLeft=new Image(HelloApplication.class.getResource("/images/Cecil/Actions/技能1左.png").toString());
        ultimateRight=new Image(HelloApplication.class.getResource("/images/Cecil/Actions/技能1右.png").toString());


    }


    public void normalAbility()//发动技能1
    {
        if(abilityTimer>700)//技能1的冷却时间（该Timer每一帧都会加1），如果超过1000表示冷却完成
        {
            try{
                File audioFile2 = new File(HelloApplication.class.getResource("/Music/Cecil/callWind.wav").getPath());
                AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                AudioFormat format2 = audioStream2.getFormat();
                DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                wind = (Clip) AudioSystem.getLine(info2);
                wind.open(audioStream2);
                FloatControl volumeControl = (FloatControl) wind.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(-3.0f); // 将音量降低10分贝（根据需要调整值）
                wind.setFramePosition(0);  // 重置到开始位置
                wind.start();

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
                // 处理异常，比如音频文件无法加载或播放等
            }
            abilityTime=1;//角色贴图呈现出使用技能的样子的计时器
            abilityTimer=0;//重置技能cd
            if(facingDirection==false)//判断技能的方向
            {
                normalBulletList.add(new Skill1Bullet(null, x - 160, y + 0*width-30, 180, 75, gameScene, facingDirection, this));//基于方向生成第一道攻击

            }
            else {
                normalBulletList.add(new Skill1Bullet(null, x + width+20, y + 0*width-30, 180, 75, gameScene, facingDirection, this));
            }

        }

    }


    public void ultimateAbility()//发动技能2
    {
        if(ultimateTimer>2000)//技能2的冷却时间
        {
            ultimateTimer = 0;//重置技能cd
            ultimateAbilityTime = 1;//角色贴图变为大招样子的timer
            UltimateTime += 1;//进入技能2状态，表示角色使用大招持续时间
            //Enhance = true;//开始强化
        }


    }

    public void normalAttack()//普通攻击的冷却时间
    {
        if(normalAttackTimer>50)//冷却
        {
            normalAttackTimer=0;//普攻进入冷却
            normalATime=1;//普攻贴图计时器
            NAcount+=1;
            if(NAcount>2)
            {
                NAcount=0;
                if(facingDirection==false)//判断技能的方向
                {
                    normalBulletList.add(new CeceilEnhancedBullet(null, x-10, y + 0*width+10, 60, 30, gameScene, facingDirection, this));//基于方向生成第一道攻击

                }
                else {
                    normalBulletList.add(new CeceilEnhancedBullet(null, x+width+10 , y + 0*width+10, 60, 30, gameScene, facingDirection, this));
                }
                try{
                    File audioFile2 = new File(HelloApplication.class.getResource("/Music/Cecil/windNormal.wav").getPath());
                    AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                    AudioFormat format2 = audioStream2.getFormat();
                    DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                    wind = (Clip) AudioSystem.getLine(info2);
                    wind.open(audioStream2);
                    FloatControl volumeControl = (FloatControl) wind.getControl(FloatControl.Type.MASTER_GAIN);
                    volumeControl.setValue(-3.0f); // 将音量降低10分贝（根据需要调整值）
                    wind.setFramePosition(0);  // 重置到开始位置
                    wind.start();

                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    e.printStackTrace();
                    // 处理异常，比如音频文件无法加载或播放等
                }
            }
            else {
                try{
                    File audioFile2 = new File(HelloApplication.class.getResource("/Music/Cecil/NormalShoot.wav").getPath());
                    AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                    AudioFormat format2 = audioStream2.getFormat();
                    DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                    normalAtk = (Clip) AudioSystem.getLine(info2);
                    normalAtk.open(audioStream2);
                    FloatControl volumeControl = (FloatControl) normalAtk.getControl(FloatControl.Type.MASTER_GAIN);
                    volumeControl.setValue(-3.0f); // 将音量降低10分贝（根据需要调整值）
                    normalAtk.setFramePosition(0);  // 重置到开始位置
                    normalAtk.start();

                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    e.printStackTrace();
                    // 处理异常，比如音频文件无法加载或播放等
                }
                if(facingDirection==false)
                {
                    normalBulletList.add(new CecilNormalBullet(bulletImgL, x + 0.5*width, y + 0.3*width, 37, 10, gameScene, facingDirection, this));
                }
                else {
                    normalBulletList.add(new CecilNormalBullet(bulletImgR, x + 0.5*width, y + 0.3*width, 37, 10, gameScene, facingDirection, this));
                }

            }

        }
    }

    public void showNormalAttack()//普通攻击动作的展示时间，不用改
    {
        if(facingDirection==false)
        {
            image=normalAttackleft;
        }
        else {
            image=normalAttackright;
        }
        if(normalATime>13)
        {
            normalATime=0;
        }
    }

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
    public void showUltimateAttack()//绝招的展示时间
    {
        if(facingDirection==false)
        {
            image=ultimateLeft;
        }
        else {
            image=ultimateRight;
        }
        if(ultimateAbilityTime>30)
        {
            ultimateAbilityTime=0;
        }

    }

    public void paint(GraphicsContext graphicsContext)//每一帧都会被调用的方法
    {
        if(UltimateTime>0)//大招逻辑
        {


            if(UltimateTime==1)
            {
                try{
                    File audioFile2 = new File(HelloApplication.class.getResource("/Music/Cecil/shootingStar.wav").getPath());
                    AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                    AudioFormat format2 = audioStream2.getFormat();
                    DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                    ShootingStr = (Clip) AudioSystem.getLine(info2);
                    ShootingStr.open(audioStream2);
                    FloatControl volumeControl = (FloatControl) ShootingStr.getControl(FloatControl.Type.MASTER_GAIN);
                    volumeControl.setValue(-2.0f); // 将音量降低10分贝（根据需要调整值）

                    ShootingStr.setFramePosition(0);  // 重置到开始位置
                    ShootingStr.start();



                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    e.printStackTrace();
                    // 处理异常，比如音频文件无法加载或播放等
                }
            }
            UltimateTime+=1;//大招计时器+1
            if(UltimateTime%25==0)
            {
                randPos=RANDOM.nextDouble(1300);
                randSize=60+RANDOM.nextDouble(60);
                normalBulletList.add(new Ceceil_ShootingStar(null,randPos,-1*(randSize+RANDOM.nextDouble(50)),randSize,randSize,gameScene,facingDirection,this));
            }
            if(UltimateTime>=800)//大招计时器到点，则退出大招强化状态
            {
                UltimateTime=0;
            }


        }
        super.paint(graphicsContext);


    }





}
