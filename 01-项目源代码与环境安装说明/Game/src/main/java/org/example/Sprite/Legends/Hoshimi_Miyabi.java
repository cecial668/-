package org.example.Sprite.Legends;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.FloatingIcons.*;
import org.example.Sprite.MiyabiBullets.MiyabiAbilityBullet;
import org.example.Sprite.MiyabiBullets.MiyabiEnhancedNormalBullet;
import org.example.Sprite.MiyabiBullets.MiyabiUltimateCut;
import org.example.Sprite.MiyabiBullets.NormalAttackBullet;
import org.example.Sprite.Person;
import org.example.gametest.HelloApplication;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Hoshimi_Miyabi extends Person {
    int RandTimeSpace;
    double RandPosX;
    double RandPosY;
    int RandNo;
    double RandSize;
    int randSpace;
    Random rand=new Random();
    //左方向子弹
    public Image bulletImgL=null;//这些图片都是角色不同运动状态的贴图
    public int EnhanceAttackTimer=0;
    //右方向子弹
    double preX;
    double preY;
    boolean preDir;
    public Image bulletImgR=null;//
    public boolean UltimateFacing;
    //右方向强化子弹
    public Image EbulletImgL=null;
    //左方向强化子弹
    public Image EbulletImgR=null;
    //必杀技子弹
    public Image UbulletImgL=null;
    public Image UbulletImgR=null;
    private Clip normalAttackS;
    private Clip EAttackS;
    private Clip MoveAttackS;
    private Clip AbilityAttack;
    //强化子弹图标
    public Image shootingEnhanceImg=null;
    //双发子弹图标
    public Image DoubleShootIcon=null;
    private int ShootingEnhanceTimer=0;//表示技能强化期间的计时器
    private boolean ShootingEnhance=false;//true表示角色正在被强化
    private int UltimateTime=0;//表示角色大招期间的计时器
    private int sprintTimer=0;
    public Hoshimi_Miyabi(Image image, double x, double y, double width, double height, GameScene gameScene, int playerID)
    {
        super(image,x,y,width,height,gameScene,playerID);
        this.legendID=1;
        bulletImgL=new Image(HelloApplication.class.getResource("/images/Archer/Bullets/Archer_arrow_left.png").toString());
        bulletImgR=new Image(HelloApplication.class.getResource("/images/Archer/Bullets/Archer_arrow_right.png").toString());
        EbulletImgL=new Image(HelloApplication.class.getResource("/images/Archer/Bullets/Enhanced_Arrow_Left.png").toString());
        EbulletImgR=new Image(HelloApplication.class.getResource("/images/Archer/Bullets/Enhanced_Arrow_Right.png").toString());
        shootingEnhanceImg=new Image(HelloApplication.class.getResource("/images/Archer/Icons/Archer_ShootingEnhance.png").toString());
        noneL=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Actions/noneL.png").toString());
        noneR=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Actions/noneR.png").toString());
        left1=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Actions/WL1.png").toString());
        left2=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Actions/WL2.png").toString());
        right1=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Actions/WR1.png").toString());
        right2=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Actions/WR2.png").toString());
        upleft=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Actions/jumpL.png").toString());
        upright=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Actions/jumpR.png").toString());
        airleft=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Actions/jumpL.png").toString());
        airright=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Actions/jumpR.png").toString());
        normalAttackleft=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Actions/AbilityL.png").toString());
        normalAttackright=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Actions/AbilityR.png").toString());
        abilityLeft=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Actions/AbilityL.png").toString());
        abilityRight=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Actions/AbilityR.png").toString());
        ultimateLeft=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Actions/UltimateL.png").toString());
        ultimateRight=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Actions/UltimateR.png").toString());
        UbulletImgL=new Image(HelloApplication.class.getResource("/images/Archer/Bullets/Ultimate_bullet_Left.png").toString());
        UbulletImgR=new Image(HelloApplication.class.getResource("/images/Archer/Bullets/Ultimate_bullet_Right.png").toString());
        DoubleShootIcon=new Image(HelloApplication.class.getResource("/images/Archer/Icons/Archer_Double_Icon.png").toString());
        try{
            File audioFile2 = new File(HelloApplication.class.getResource("/Music/Hoshimi_Miyabi/AbilityMove.wav").getPath());
            AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
            AudioFormat format2 = audioStream2.getFormat();
            DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
            MoveAttackS = (Clip) AudioSystem.getLine(info2);
            MoveAttackS.open(audioStream2);
            FloatControl volumeControl = (FloatControl) MoveAttackS.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(-2.0f); // 将音量降低10分贝（根据需要调整值）

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            // 处理异常，比如音频文件无法加载或播放等
        }




    }
    public void normalAbility()//发动普通技能
    {

        if(abilityTimer>600 && UltimateTime==0)//普通技能的冷却时间（该Timer每一帧都会加1），如果超过1000表示冷却完成
        {
            abilityTime=1;//角色贴图呈现出使用技能的样子的计时器
            abilityTimer=0;//重置技能cd
            sprintTimer=1;

        }

    }
    public void ultimateAbility()//绝招的冷却时间
    {


        if(ultimateTimer>1500 && sprintTimer==0)
        {
            ultimateTimer=0;
            ultimateAbilityTime=1;//角色贴图变为大招样子的timer
            UltimateTime+=1;//进入大招状态，这个变量大于1表示大招正在持续
            UltimateFacing=facingDirection;

        }

    }
    public void normalAttack()//普通攻击的冷却时间
    {
        if(normalAttackTimer>80)//冷却
        {
            try{
                File audioFile2 = new File(HelloApplication.class.getResource("/Music/Hoshimi_Miyabi/NormalAttack.wav").getPath());
                AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                AudioFormat format2 = audioStream2.getFormat();
                DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                normalAttackS = (Clip) AudioSystem.getLine(info2);
                normalAttackS.open(audioStream2);
                FloatControl volumeControl = (FloatControl) normalAttackS.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(-8.0f); // 将音量降低10分贝（根据需要调整值）


            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
                // 处理异常，比如音频文件无法加载或播放等
            }
            normalAttackS.setFramePosition(0);  // 重置到开始位置
            normalAttackS.start();
            normalAttackTimer=0;//普攻进入冷却
            normalATime=1;//普攻贴图计时器
            playerIcons.add(new Miyabi_Normal_Attack(null,x+0.5*width, y-100, 150, 97, gameScene, this,facingDirection));
            normalBulletList.add(new NormalAttackBullet(null,x + 0.2*width, y-0.6*width, 37, 70, gameScene, facingDirection, this));
            if(EnhanceAttackTimer>250)
            {
                try{
                    File audioFile2 = new File(HelloApplication.class.getResource("/Music/Hoshimi_Miyabi/EnhancedAttack.wav").getPath());
                    AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                    AudioFormat format2 = audioStream2.getFormat();
                    DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                    EAttackS = (Clip) AudioSystem.getLine(info2);
                    EAttackS.open(audioStream2);
                    FloatControl volumeControl = (FloatControl) EAttackS.getControl(FloatControl.Type.MASTER_GAIN);
                    volumeControl.setValue(-2.0f); // 将音量降低10分贝（根据需要调整值）

                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    e.printStackTrace();
                    // 处理异常，比如音频文件无法加载或播放等
                }
                EAttackS.setFramePosition(0);  // 重置到开始位置
                EAttackS.start();
                normalBulletList.add(new MiyabiEnhancedNormalBullet(null,x + 0.2*width, y-20, 140, 70, gameScene, facingDirection, this));
                EnhanceAttackTimer=0;
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
        if(normalATime>25)
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
        if(abilityTime>82)
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
        if(ultimateAbilityTime>40)
        {
            ultimateAbilityTime=0;
        }

    }
    public void playHitSound()
    {

    }

    public void paint(GraphicsContext graphicsContext)//每一帧都会被调用的方法
    {
        EnhanceAttackTimer+=1;
        if(sprintTimer==1)
        {
            preX=x;
            preY=y;
            preDir=facingDirection;
            if(facingDirection==false)
            {
                playerIcons.add(new MiyabiAbility_Wind(null,x-20, y-70, 210, 120, gameScene, this,facingDirection));
                playerIcons.add(new MiyabiAbilityBody(null,x-40, y-60, 165, 120, gameScene, this,facingDirection));

            }
            else
            {
                playerIcons.add(new MiyabiAbilityBody(null,x-80, y-60, 165, 120, gameScene, this,facingDirection));

                playerIcons.add(new MiyabiAbility_Wind(null,x-100, y-70, 210, 120, gameScene, this,facingDirection));

            }
        }
        if(sprintTimer>0)
        {
            sprintTimer+=1;
        }
        if(sprintTimer==3)
        {

            MoveAttackS.setFramePosition(0);  // 重置到开始位置
            MoveAttackS.start();
        }

        if(sprintTimer>1 && sprintTimer<50)
        {
            x=500;
            y=-1000;
        }
        if(sprintTimer==40)
        {
            try{
                File audioFile2 = new File(HelloApplication.class.getResource("/Music/Hoshimi_Miyabi/AbilityAttack.wav").getPath());
                AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                AudioFormat format2 = audioStream2.getFormat();
                DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                AbilityAttack = (Clip) AudioSystem.getLine(info2);
                AbilityAttack.open(audioStream2);
                FloatControl volumeControl = (FloatControl) AbilityAttack.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(-3.0f); // 将音量降低10分贝（根据需要调整值）


            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
                // 处理异常，比如音频文件无法加载或播放等
            }
            AbilityAttack.setFramePosition(0);  // 重置到开始位置
            AbilityAttack.start();
        }
        if(sprintTimer>50)
        {
            if(preDir==false)
            {
                x=preX-300;
                y=preY;
            }
            else {
                x=preX+300;
                y=preY;
            }
            x_speed=0;
            y_speed=0;
            Xa=0;
            sprintTimer=0;
            EnhanceAttackTimer=10000;
            playerIcons.add(new Miyabi_AbilityAttack(null,x + 0.2*width, y-120, 320, 160, gameScene,this));
            normalBulletList.add(new MiyabiAbilityBullet(null,x + 0.2*width, y-120, 320, 160, gameScene,facingDirection,this));
        }



        if(UltimateTime>0)//大招逻辑
        {



            if(UltimateTime==1)
            {
                preX=x;
                preY=y;
                preDir=facingDirection;

            }
            else {
                if(this.hp>0)
                {
                    x=preX;
                    y=preY;
                    y_speed=0;
                    x_speed=0;
                    facingDirection=preDir;
                }

            }
            UltimateTime+=1;//大招计时器+1
            if(UltimateTime>=40)//大招计时器到点，则退出大招强化状态
            {
                EnhanceAttackTimer=10000;
                UltimateTime=0;
            }
            /*if(UltimateTime%10==0)
            {
                RandNo=rand.nextInt(6);
                RandPosX=100-rand.nextDouble(200);
                RandPosY=50-rand.nextDouble(100);
                RandSize=450+rand.nextDouble(100);
                randSpace=4+rand.nextInt(4);
                if(UltimateFacing==false)
                {
                    normalBulletList.add(new MiyabiUltimateCut(null,x-500+RandPosX,y-200+RandPosY,RandSize,RandSize,gameScene,facingDirection,this,RandNo,randSpace));



                }
                else {
                    normalBulletList.add(new MiyabiUltimateCut(null,x+30+RandPosX,y-250+RandPosY,RandSize,RandSize,gameScene,facingDirection,this,RandNo,randSpace));


                }
            }*/

            if(UltimateTime==3)//大招开启初期，再释放剩余两道冲击波
            {
                if(UltimateFacing==false)
                {
                    normalBulletList.add(new MiyabiUltimateCut(null,x-500,y-300,500,500,gameScene,facingDirection,this,0,5));



                }
                else {
                    normalBulletList.add(new MiyabiUltimateCut(null,x+30,y-300,500,500,gameScene,facingDirection,this,0,5));


                }

            }
            if(UltimateTime==8)//大招开启初期，再释放剩余两道冲击波
            {
                if(UltimateFacing==false)
                {
                    normalBulletList.add(new MiyabiUltimateCut(null,x-500,y-300,500,500,gameScene,facingDirection,this,6,8));



                }
                else {
                    normalBulletList.add(new MiyabiUltimateCut(null,x+30,y-300,500,500,gameScene,facingDirection,this,6,8));


                }

            }
            if(UltimateTime==12)//大招开启初期，再释放剩余两道冲击波
            {
                if(UltimateFacing==false)
                {
                    normalBulletList.add(new MiyabiUltimateCut(null,x-500,y-300,500,500,gameScene,facingDirection,this,3,4));



                }
                else {
                    normalBulletList.add(new MiyabiUltimateCut(null,x+30,y-300,500,500,gameScene,facingDirection,this,3,4));


                }

            }
            if(UltimateTime==18)//大招开启初期，再释放剩余两道冲击波
            {
                if(UltimateFacing==false)
                {
                    normalBulletList.add(new MiyabiUltimateCut(null,x-500,y-300,500,500,gameScene,facingDirection,this,2,6));



                }
                else {
                    normalBulletList.add(new MiyabiUltimateCut(null,x+30,y-300,500,500,gameScene,facingDirection,this,2,6));


                }

            }
            if(UltimateTime==23)//大招开启初期，再释放剩余两道冲击波
            {
                if(UltimateFacing==false)
                {
                    normalBulletList.add(new MiyabiUltimateCut(null,x-500,y-300,500,500,gameScene,facingDirection,this,4,5));



                }
                else {
                    normalBulletList.add(new MiyabiUltimateCut(null,x+30,y-300,500,500,gameScene,facingDirection,this,4,5));


                }

            }
            if(UltimateTime==30)//大招开启初期，再释放剩余两道冲击波
            {
                if(UltimateFacing==false)
                {
                    normalBulletList.add(new MiyabiUltimateCut(null,x-500,y-300,500,500,gameScene,facingDirection,this,1,7));



                }
                else {
                    normalBulletList.add(new MiyabiUltimateCut(null,x+30,y-300,500,500,gameScene,facingDirection,this,1,7));


                }

            }
            if(UltimateTime==36)//大招开启初期，再释放剩余两道冲击波
            {
                if(UltimateFacing==false)
                {
                    normalBulletList.add(new MiyabiUltimateCut(null,x-500,y-300,500,500,gameScene,facingDirection,this,5,6));



                }
                else {
                    normalBulletList.add(new MiyabiUltimateCut(null,x+30,y-300,500,500,gameScene,facingDirection,this,5,6));


                }

            }

        }
        super.paint(graphicsContext);


    }


}
