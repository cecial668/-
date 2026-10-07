package org.example.Sprite.Legends;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.ArchorUltimateBullet.UltimateBullet;
import org.example.Sprite.FloatingIcons.Archer_DoubleShoot;
import org.example.Sprite.FloatingIcons.Archer_ShootingEnhanced;
import org.example.Sprite.NormalBullet.NormalBullet;
import org.example.Sprite.Person;
import org.example.gametest.HelloApplication;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

//弓箭手类，为一个具体的角色
public class Archer extends Person {
    //左方向子弹
    public Image bulletImgL=null;//这些图片都是角色不同运动状态的贴图
    //右方向子弹
    public Image bulletImgR=null;//
    //右方向强化子弹
    public Image EbulletImgL=null;
    //左方向强化子弹
    public Image EbulletImgR=null;
    //必杀技子弹
    public Image UbulletImgL=null;
    public Image UbulletImgR=null;
    //强化子弹图标
    public Image shootingEnhanceImg=null;
    Clip AttackS;
    Clip EAttackS;
    Clip ES;
    Clip US;
    //双发子弹图标
    public Image DoubleShootIcon=null;
    private int ShootingEnhanceTimer=0;//表示技能强化期间的计时器
    private boolean ShootingEnhance=false;//true表示角色正在被强化
    private int UltimateTime=0;//表示角色大招期间的计时器
    public Archer(Image image, double x, double y, double width, double height, GameScene gameScene, int playerID)
    {
        super(image,x,y,width,height,gameScene,playerID);
        this.legendID=0;
        bulletImgL=new Image(HelloApplication.class.getResource("/images/Archer/Bullets/Archer_arrow_left.png").toString());
        bulletImgR=new Image(HelloApplication.class.getResource("/images/Archer/Bullets/Archer_arrow_right.png").toString());
        EbulletImgL=new Image(HelloApplication.class.getResource("/images/Archer/Bullets/Enhanced_Arrow_Left.png").toString());
        EbulletImgR=new Image(HelloApplication.class.getResource("/images/Archer/Bullets/Enhanced_Arrow_Right.png").toString());
        shootingEnhanceImg=new Image(HelloApplication.class.getResource("/images/Archer/Icons/Archer_ShootingEnhance.png").toString());
        noneL=new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherSilentL.png").toString());
        noneR=new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherSilentR.png").toString());
        left1=new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherWalkL1.png").toString());
        left2=new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherWalkL2.png").toString());
        right1=new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherWalkR1.png").toString());
        right2=new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherWalkR2.png").toString());
        upleft=new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherJumpLeft.png").toString());
        upright=new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherJumpRight.png").toString());
        airleft=new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherJumpLeft.png").toString());
        airright=new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherJumpRight.png").toString());
        normalAttackleft=new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherNormalAttackLeft.png").toString());
        normalAttackright=new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherNormalAttackRight.png").toString());
        abilityLeft=new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherAbilityLeft.png").toString());
        abilityRight=new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherAbilityRight.png").toString());
        ultimateLeft=new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherUltimateLeft.png").toString());
        ultimateRight=new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherUltimateRight.png").toString());
        UbulletImgL=new Image(HelloApplication.class.getResource("/images/Archer/Bullets/Ultimate_bullet_Left.png").toString());
        UbulletImgR=new Image(HelloApplication.class.getResource("/images/Archer/Bullets/Ultimate_bullet_Right.png").toString());
        DoubleShootIcon=new Image(HelloApplication.class.getResource("/images/Archer/Icons/Archer_Double_Icon.png").toString());


    }
    public void normalAbility()//发动普通技能
    {
        if(abilityTimer>1000)//普通技能的冷却时间（该Timer每一帧都会加1），如果超过1000表示冷却完成
        {
            try{
                File audioFile2 = new File(HelloApplication.class.getResource("/Music/Archer/Enhance.wav").getPath());
                AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                AudioFormat format2 = audioStream2.getFormat();
                DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                ES = (Clip) AudioSystem.getLine(info2);
                ES.open(audioStream2);
                FloatControl volumeControl = (FloatControl) ES.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(-2.0f); // 将音量降低10分贝（根据需要调整值）

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
                // 处理异常，比如音频文件无法加载或播放等
            }
            ES.setFramePosition(0);  // 重置到开始位置
            ES.start();
            abilityTime=1;//角色贴图呈现出使用技能的样子的计时器
            abilityTimer=0;//重置技能cd
            ShootingEnhance=true;//开始强化
            playerIcons.add(new Archer_ShootingEnhanced(shootingEnhanceImg, x, y, 24, 18, gameScene, this));//创建强化图标（射击强化）

        }

    }
    public void ultimateAbility()//绝招的冷却时间
    {
        if(ultimateTimer>1200)
        {
            try{
                File audioFile2 = new File(HelloApplication.class.getResource("/Music/Archer/UltimateBulletShoot.wav").getPath());
                AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                AudioFormat format2 = audioStream2.getFormat();
                DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                US = (Clip) AudioSystem.getLine(info2);
                US.open(audioStream2);
                FloatControl volumeControl = (FloatControl) US.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(-2.0f); // 将音量降低10分贝（根据需要调整值）

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
                // 处理异常，比如音频文件无法加载或播放等
            }
            US.setFramePosition(0);  // 重置到开始位置
            US.start();
            playerIcons.add(new Archer_DoubleShoot(DoubleShootIcon, x, y,24, 18, gameScene, this));//创建强化图标（双重射击）
            ultimateTimer=0;
            ultimateAbilityTime=1;//角色贴图变为大招样子的timer
            UltimateTime+=1;//进入大招状态，这个变量大于1表示大招正在持续
            if(facingDirection==false)//判断开大的方向
            {
                normalBulletList.add(new UltimateBullet(UbulletImgL, x + 0.5*width, y + 0*width-15, 60, 90, gameScene, facingDirection, this));//基于方向生成第一段冲击波

            }
            else {
                normalBulletList.add(new UltimateBullet(UbulletImgR, x + 0.5*width, y + 0*width-15, 60, 90, gameScene, facingDirection, this));
            }

        }

    }
    public void normalAttack()//普通攻击的冷却时间
    {
        if(normalAttackTimer>30)//冷却
        {
            normalAttackTimer=0;//普攻进入冷却
            normalATime=1;//普攻贴图计时器
            if (ShootingEnhance==true)//判断是否具有射击强化效果
            {
                if(facingDirection==false)
                {
                    normalBulletList.add(new NormalBullet(EbulletImgL, x + 0.5*width, y + 0.3*width, 37, 10, gameScene, facingDirection, this));//根据情况生成相应的箭矢
                    if(UltimateTime!=0)//判断是否处于大招双重射击强化期间
                    {
                        normalBulletList.add(new NormalBullet(EbulletImgL, x + 0.5*width, y - 0.1*width, 37, 10, gameScene, facingDirection, this));//附加一根箭矢
                    }

                }
                else {
                    normalBulletList.add(new NormalBullet(EbulletImgR, x + 0.5*width, y + 0.3*width, 37, 10, gameScene, facingDirection, this));
                    if(UltimateTime!=0)
                    {
                        normalBulletList.add(new NormalBullet(EbulletImgR, x + 0.5*width, y - 0.1*width, 37, 10, gameScene, facingDirection, this));
                    }
                }
                try{
                    File audioFile2 = new File(HelloApplication.class.getResource("/Music/Archer/EnhanceShoot.wav").getPath());
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

            }
            else {
                if(facingDirection==false)
                {
                    normalBulletList.add(new NormalBullet(bulletImgL, x + 0.5*width, y + 0.5*width, 20, 5, gameScene, facingDirection, this));
                    if(UltimateTime!=0)
                    {
                        normalBulletList.add(new NormalBullet(bulletImgL, x + 0.5*width, y, 20, 5, gameScene, facingDirection, this));
                    }

                }
                else {
                    normalBulletList.add(new NormalBullet(bulletImgR, x + 0.5*width, y + 0.5*width, 20, 5, gameScene, facingDirection, this));
                    if(UltimateTime!=0)
                    {
                        normalBulletList.add(new NormalBullet(bulletImgR, x + 0.5*width, y, 20, 5, gameScene, facingDirection, this));
                    }
                }
                try{
                    File audioFile2 = new File(HelloApplication.class.getResource("/Music/Archer/Shoot.wav").getPath());
                    AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                    AudioFormat format2 = audioStream2.getFormat();
                    DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                    AttackS = (Clip) AudioSystem.getLine(info2);
                    AttackS.open(audioStream2);
                    FloatControl volumeControl = (FloatControl) AttackS.getControl(FloatControl.Type.MASTER_GAIN);
                    volumeControl.setValue(-2.0f); // 将音量降低10分贝（根据需要调整值）

                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    e.printStackTrace();
                    // 处理异常，比如音频文件无法加载或播放等
                }
                AttackS.setFramePosition(0);  // 重置到开始位置
                AttackS.start();

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
        if(ShootingEnhance==true)//处于射击强化状态则计时器+1
        {
            ShootingEnhanceTimer+=1;
        }
        if(ShootingEnhanceTimer>=450)//若计时器大于450则退出强化状态
        {
            ShootingEnhanceTimer=0;
            ShootingEnhance=false;
        }
        if(UltimateTime>0)//大招逻辑
        {
            UltimateTime+=1;//大招计时器+1
            if(UltimateTime>=600)//大招计时器到点，则退出大招强化状态
            {
                UltimateTime=0;
            }
            if(UltimateTime==60)//大招开启初期，再释放剩余两道冲击波
            {
                try{
                    File audioFile2 = new File(HelloApplication.class.getResource("/Music/Archer/UltimateBulletShoot.wav").getPath());
                    AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                    AudioFormat format2 = audioStream2.getFormat();
                    DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                    US = (Clip) AudioSystem.getLine(info2);
                    US.open(audioStream2);
                    FloatControl volumeControl = (FloatControl) US.getControl(FloatControl.Type.MASTER_GAIN);
                    volumeControl.setValue(-2.0f); // 将音量降低10分贝（根据需要调整值）

                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    e.printStackTrace();
                    // 处理异常，比如音频文件无法加载或播放等
                }
                US.setFramePosition(0);  // 重置到开始位置
                US.start();
                if(facingDirection==false)
                {
                    normalBulletList.add(new UltimateBullet(UbulletImgL, x + 0.5*width, y + 0*width-30, 80, 120, gameScene, facingDirection, this));


                }
                else {
                    normalBulletList.add(new UltimateBullet(UbulletImgR, x + 0.5*width, y + 0*width-30, 80, 120, gameScene, facingDirection, this));

                }

            }
            if(UltimateTime==120)
            {
                try{
                    File audioFile2 = new File(HelloApplication.class.getResource("/Music/Archer/UltimateBulletShoot.wav").getPath());
                    AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                    AudioFormat format2 = audioStream2.getFormat();
                    DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                    US = (Clip) AudioSystem.getLine(info2);
                    US.open(audioStream2);
                    FloatControl volumeControl = (FloatControl) US.getControl(FloatControl.Type.MASTER_GAIN);
                    volumeControl.setValue(-2.0f); // 将音量降低10分贝（根据需要调整值）

                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    e.printStackTrace();
                    // 处理异常，比如音频文件无法加载或播放等
                }
                US.setFramePosition(0);  // 重置到开始位置
                US.start();
                if(facingDirection==false)
                {
                    normalBulletList.add(new UltimateBullet(UbulletImgL, x + 0.5*width, y + 0*width-45, 100, 150, gameScene, facingDirection, this));


                }
                else {
                    normalBulletList.add(new UltimateBullet(UbulletImgR, x + 0.5*width, y + 0*width-45, 100, 150, gameScene, facingDirection, this));
                }

            }
        }
        super.paint(graphicsContext);


    }





}
