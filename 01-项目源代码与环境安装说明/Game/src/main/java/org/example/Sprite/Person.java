package org.example.Sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import org.example.Scenes.GameScene;
import org.example.Sprite.FloatingIcons.*;
import org.example.Sprite.Lazer.LazerBullet;
import org.example.Sprite.Particle.*;
import org.example.gametest.HelloApplication;

import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Person extends Role{
    public boolean facingDirection=false; //false:Left
    public int legendID;//英雄ID，每名英雄对应一个编号，法师为0，武士为1，弓箭手为2，赛亚人为3
    public int attackCooldown=0;//针对boss的攻击冷却，用于让boss贴图得知其是否攻击了
    //水平方向速度
    public double x_speed=0;//角色x方向速度
    public int shieldTimer=0;//角色拥有护盾的计时器
    private int speedUPTimer=0;//角色获得移速加成的计时器
    public int LazerTimer=0;//角色发射激光的计时器
    public boolean lazerReady=false;//角色的激光是否可使用

    //垂直方向速度
    public double y_speed=0;//角色y方向速度
    private int countTimer=0;//计时器
    public int lives=3;//角色生命数
    public int reviveClock=0;//角色复活倒计时

    private Clip buffSound;//角色拾取技能块播放的音效
    private Clip lazerS;//角色发射激光播放的音效
    private Clip ExplodeSound;//角色特殊受击音效
    private Clip HitSound;//角色受到部分攻击时播放特殊受击音效

    //水平加速度
    public double Xa=0;//角色x方向加速度

    private int playerID;//0代表p1，1代表p2
    public int gameStartCountdown=390;//游戏开始前的倒计时

    //重力加速度
    private final double G=0.18;//游戏重力

    //前一帧人物的位置
    private double oldx,oldy;//角色上一帧的位置

    public int abilityTimer=10000;//计时器，计算普通技能的冷却时间
    public int ultimateTimer=10000;//计算必杀技的冷却时间
    public int normalAttackTimer=1000;//计算普通攻击冷却时间
    public double BeingHitTimer=0;

    private boolean a_flag=false;//向左走的按键是否处于按下状态
    private boolean onair=false;//角色是否滞空
    private boolean d_flag=false;//向右
    private boolean j_flag=false;//角色普通攻击键是否处于按下状态
    private boolean space_flag=false;//角色跳跃键是否处于按下状态
    private boolean onground=false;//角色是否站在地上
    public double hp=100;
    private boolean hasjump=false;//角色是否刚刚进行了跳跃操作

    public int normalATime;//角色依次释放普攻、技能、大招的贴图持续时间
    public int abilityTime;
    public int ultimateAbilityTime;
    private double maxSpeed=4;//角色最大速度
    private double maxA=4;//角色最大加速度
    private double XaMove=0.08;//角色被操作时移动加速度
    private double XIndex=0.8;//角色减速时减速比例
    private double XaIndex=0.75;//减速时加速度减小比例
    public Clip personJump;//角色跳跃
    public Clip personDead;//角色死亡


    public List<Bullet> normalBulletList = new ArrayList<>();//装有角色射出的所有子弹类
    public List<ParticleExplosion> normalBulletExplodeList = new ArrayList<>();//装有角色造成的所有爆炸效果
    public List<FloatingIcon> playerIcons = new ArrayList<>();//装有角色产生的所有图标
    public List<SampleParticle> particleList = new ArrayList<>();//装有角色产生的所有粒子效果
    public Image noneL,noneR,left1,left2,right1,right2,upleft,upright,airleft,airright,normalAttackleft,normalAttackright,abilityLeft,abilityRight,ultimateLeft,ultimateRight;//角色所有运动状态的贴图








    public Person(Image image, double x, double y, double width, double height, GameScene gameScene, int playerID) {


        super(image, x, y, width, height, gameScene);

        if(playerID==0)//判断角色是P1控制还是P2控制，并给该角色附上名牌
        {
            playerIcons.add(new PlayerNumberIcon(new Image(HelloApplication.class.getResource("/images/GameUIs/P1.png").toString()),x,y,40,40,gameScene,this));
        }
        else if(playerID==1){
            playerIcons.add(new PlayerNumberIcon(new Image(HelloApplication.class.getResource("/images/GameUIs/P2.png").toString()),x,y,40,40,gameScene,this));

        }
        else//如果发现角色为BOSS单位，将其血量生命设定为指定值，并赋予人机标识
        {
            playerIcons.add(new BossIcon(null,x,y,80,80,gameScene,this));
            hp=1000;
            lives=1;
            playerIcons.add(new PlayerNumberIcon(new Image(HelloApplication.class.getResource("/images/GameUIs/CP.png").toString()),x,y,40,40,gameScene,this));

        }
        try{
            File audioFile2 = new File(HelloApplication.class.getResource("/Music/PublicSounds/playerJump.wav").getPath());
            AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
            AudioFormat format2 = audioStream2.getFormat();
            DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
            personJump = (Clip) AudioSystem.getLine(info2);
            personJump.open(audioStream2);


        } catch (UnsupportedAudioFileException | IOException |LineUnavailableException e) {
            e.printStackTrace();
            // 处理异常，比如音频文件无法加载或播放等
        }
        try{
            File audioFile2 = new File(HelloApplication.class.getResource("/Music/PublicSounds/PlayerDead.wav").getPath());
            AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
            AudioFormat format2 = audioStream2.getFormat();
            DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
            personDead = (Clip) AudioSystem.getLine(info2);
            personDead.open(audioStream2);


        } catch (UnsupportedAudioFileException | IOException |LineUnavailableException e) {
            e.printStackTrace();
            // 处理异常，比如音频文件无法加载或播放等
        }

        //this.group = group;
        this.playerID=playerID;
    }

    public void move() {//角色移动逻辑

        if(onair==true && space_flag==true)//角色滞空且之前有按过跳跃键
        {
            space_flag=false;//重置跳跃冷却
        }
        if(BeingHitTimer<=0)//若角色被攻击，则之后一小段时间内运动逻辑发生变化，以便呈现出击退击飞效果
        {
            if(speedUPTimer!=0)//角色加速期间
            {
                maxSpeed=4;
                maxA=4;
                XaMove=0.2;
                XIndex=0.5;
                XaIndex=0.5;

            }
            else {
                maxSpeed=3;
                maxA=3;
                XaMove=0.08;
                XIndex=0.8;
                XaIndex=0.75;

            }


        }
        else {
            maxSpeed=10;
            maxA=0.5;
            XaMove=0.01;
            XIndex=0.99;
            XaIndex=0.75;

        }
        oldx = x;//角色移动
        oldy = y;
        x+=x_speed;
        y-=y_speed;
        x_speed+=Xa;
        y_speed-=G;
        if(a_flag==true)//视按键触发情况进行移动
        {
            Xa-=XaMove;
        }
        if(d_flag==true)
        {
            Xa+=XaMove;
        }

        if(Xa>=maxA)//限制最大速度与加速度
        {
            Xa=maxA;
        }
        if(Xa<=-maxA)
        {
            Xa=-maxA;
        }
        if(x_speed>=maxSpeed)
        {
            x_speed=maxSpeed;
        }
        if(x_speed<=-maxSpeed)
        {
            x_speed=-maxSpeed;
        }
        if(x_speed>0 && d_flag==false )//若松开按键，角色进入减速状态，速度与加速度每帧下降
        {
            Xa=Xa*XaIndex;
            x_speed=x_speed*XIndex;
        }
        if(x_speed<0 && a_flag==false)
        {
            Xa=Xa*XaIndex;
            x_speed=x_speed*XIndex;
        }
        if(a_flag==true && d_flag==true)//同时按下左右移动键也会减速
        {
            Xa=Xa*XaIndex;
            x_speed=x_speed*XIndex;
        }

        //连跳

        //space_flag=false;
    }
    //按压
    public void pressed(KeyCode keyCode){//监听键盘事件
        if(playerID==0&&gameStartCountdown==0){
            switch (keyCode) {
                case A:
                    if (a_flag == false) {
                        facingDirection = false;
                        a_flag = true;
                    }
                    break;
                case D:
                    if (d_flag == false) {
                        facingDirection = true;
                        d_flag = true;
                    }
                    break;
                case R://ceshi fa she pu tong zi dan
                    if (j_flag == false) {
                        normalAttack();
                        if(lazerReady==true&&lives>0)//若激光准备继续且活着
                        {
                            lazerReady=false;
                            LazerTimer=300;//刷新激光持续时间
                            try{
                                File audioFile2 = new File(HelloApplication.class.getResource("/Music/PublicSounds/Lazer.wav").getPath());
                                AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                                AudioFormat format2 = audioStream2.getFormat();
                                DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                                lazerS = (Clip) AudioSystem.getLine(info2);
                                lazerS.open(audioStream2);
                                FloatControl volumeControl = (FloatControl) lazerS.getControl(FloatControl.Type.MASTER_GAIN);
                                volumeControl.setValue(+4.0f); // 将音量降低10分贝（根据需要调整值）


                            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                                e.printStackTrace();
                                // 处理异常，比如音频文件无法加载或播放等
                            }
                            lazerS.setFramePosition(0);  // 重置到开始位置
                            lazerS.start();
                            for(int i=0;i<1350;i+=50)//添加激光子弹
                            {
                                normalBulletList.add(new LazerBullet( null,x,y,50,50,gameScene,this,i));
                            }

                        }
                        j_flag = true;
                    }
                    break;

                case W:
                    space_flag = true;
                    break;
                case T://技能键，如果角色存活则发动技能
                    System.out.println(abilityTimer);
                    if(lives>0)
                    {
                        normalAbility();

                    }
                    break;
                case Y://大招键
                    if(lives>0)
                    {
                        ultimateAbility();

                    }
                    break;
            }



        }
        else if(playerID==1&&gameStartCountdown==0)
        {
            switch (keyCode) {
                case LEFT:
                    if (a_flag == false) {
                        facingDirection = false;
                        a_flag = true;
                    }
                    break;
                case RIGHT:
                    if (d_flag == false) {
                        facingDirection = true;
                        d_flag = true;
                    }
                    break;
                case NUMPAD1://ceshi fa she pu tong zi dan
                    if (j_flag == false&& lives>0) {
                        normalAttack();
                        if(lazerReady==true)
                        {
                            lazerReady=false;
                            LazerTimer=300;
                            try{
                                File audioFile2 = new File(HelloApplication.class.getResource("/Music/PublicSounds/Lazer.wav").getPath());
                                AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                                AudioFormat format2 = audioStream2.getFormat();
                                DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                                lazerS = (Clip) AudioSystem.getLine(info2);
                                lazerS.open(audioStream2);
                                FloatControl volumeControl = (FloatControl) lazerS.getControl(FloatControl.Type.MASTER_GAIN);
                                volumeControl.setValue(+4.0f); // 将音量降低10分贝（根据需要调整值）


                            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                                e.printStackTrace();
                                // 处理异常，比如音频文件无法加载或播放等
                            }
                            lazerS.setFramePosition(0);  // 重置到开始位置
                            lazerS.start();

                            for(int i=0;i<1350;i+=50)
                            {
                                normalBulletList.add(new LazerBullet( null,x,y,50,50,gameScene,this,i));
                            }
                        }
                        j_flag = true;
                    }
                    break;
                case UP:
                    space_flag = true;
                    break;
                case NUMPAD2:
                    System.out.println(abilityTimer);
                    if(lives>0)
                    {
                        normalAbility();

                    }

                    break;
                case NUMPAD3:
                    if(lives>0)
                    {
                        ultimateAbility();
                    }

                    break;
            }

        }
        else if(playerID==2&&gameStartCountdown==0)
        {
            switch (keyCode) {
                case J:
                    if (a_flag == false) {
                        facingDirection = false;
                        a_flag = true;
                    }
                    break;
                case L:
                    if (d_flag == false) {
                        facingDirection = true;
                        d_flag = true;
                    }
                    break;


                case I:
                    space_flag = true;
                    break;
            }

        }

    }
    public void normalAbility()//在具体角色中将详细复写的方法
    {

    }
    public void ultimateAbility()
    {

    }
    public void normalAttack()
    {}




    public void released(KeyCode keyCode){//松开按键，按键按下状态设置为false
        if(playerID==0&&gameStartCountdown==0) {
            switch (keyCode) {
                case A:
                    if (a_flag == true) {
                        a_flag = false;
                    }
                    break;
                case D:
                    if (d_flag == true) {
                        d_flag = false;
                    }
                    break;
                case R:
                    if (j_flag == true) {
                        j_flag = false;
                    }
                    break;
                case W:
                    hasjump = false;
                    break;
            }
        }
        else if(playerID==1&&gameStartCountdown==0)
        {
            switch (keyCode) {
                case LEFT:
                    if (a_flag == true) {
                        a_flag = false;
                    }
                    break;
                case RIGHT:
                    if (d_flag == true) {
                        d_flag = false;
                    }
                    break;
                case NUMPAD1:
                    if (j_flag == true) {
                        j_flag = false;
                    }
                    break;
                case UP:
                    hasjump = false;
                    break;
            }

        }
    }
    public void impactWithNormalBullet(Bullet bullet)//处理角色与其他子弹类碰撞的逻辑（即角色是否中弹）
    {
        if(bullet.owner!=this && bullet != null && !bullet.equals(this) && getContour().intersects(bullet.getContour()))//检测到中弹
        {
            bullet.explode();//让子弹爆炸
            if(shieldTimer==0) {
                if(speedUPTimer==0)//加速与非加速状态下受伤不同，让角色受伤的同时调用击退方法
                {
                    knockback(bullet.direction, bullet.KnockbackRatex, bullet.KnockbackRatey);
                    hp -= bullet.damage;

                }
                else {
                    knockback(bullet.direction, bullet.KnockbackRatex, bullet.KnockbackRatey);
                    hp -= bullet.damage*0.7;
                }


            }

        }


    }
    public void impactWithBuff(BuffBlocks buff)//角色拾取技能快的逻辑
    {
        if(buff != null && !buff.equals(this) && getContour().intersects(buff.getContour()))//如果碰到
        {
            for(int i=0;i<30;i++)//产生粒子效果
            {
                particleList.add(new BuffObtainParticle(new Image(HelloApplication.class.getResource("/images/OtherImages/BuffObtaineParticle.png").toString()), buff.x+10, buff.y+10, 10, 10, gameScene,this));
            }
            if(buff.type==0)//根据拾取到技能快的类型获得不同加成，0为激光
            {
                try{
                    File audioFile2 = new File(HelloApplication.class.getResource("/Music/PublicSounds/LazerReady.wav").getPath());
                    AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                    AudioFormat format2 = audioStream2.getFormat();
                    DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                    buffSound = (Clip) AudioSystem.getLine(info2);
                    buffSound.open(audioStream2);
                    FloatControl volumeControl = (FloatControl) buffSound.getControl(FloatControl.Type.MASTER_GAIN);
                    volumeControl.setValue(-3.0f); // 将音量降低10分贝（根据需要调整值）


                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    e.printStackTrace();
                    // 处理异常，比如音频文件无法加载或播放等
                }
                lazerReady=true;
                for(int i=0;i<1350;i+=50)
                {
                    playerIcons.add(new LazerReady( new Image(HelloApplication.class.getResource("/images/OtherImages/LazerReady.png").toString()),x,y,50,50,gameScene,this,i));

                }

            }
            if(buff.type==1)//1为生命+1
            {
                try{
                    File audioFile2 = new File(HelloApplication.class.getResource("/Music/PublicSounds/GainHealth.wav").getPath());
                    AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                    AudioFormat format2 = audioStream2.getFormat();
                    DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                    buffSound = (Clip) AudioSystem.getLine(info2);
                    buffSound.open(audioStream2);
                    FloatControl volumeControl = (FloatControl) buffSound.getControl(FloatControl.Type.MASTER_GAIN);
                    volumeControl.setValue(-3.0f); // 将音量降低10分贝（根据需要调整值）


                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    e.printStackTrace();
                    // 处理异常，比如音频文件无法加载或播放等
                }
                if(lives!=3)
                {
                    lives+=1;
                }
            }
            if(buff.type==2)//刷新技能
            {
                try{
                    File audioFile2 = new File(HelloApplication.class.getResource("/Music/PublicSounds/GainTimer.wav").getPath());
                    AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                    AudioFormat format2 = audioStream2.getFormat();
                    DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                    buffSound = (Clip) AudioSystem.getLine(info2);
                    buffSound.open(audioStream2);
                    FloatControl volumeControl = (FloatControl) buffSound.getControl(FloatControl.Type.MASTER_GAIN);
                    volumeControl.setValue(-0.0f); // 将音量降低10分贝（根据需要调整值）


                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    e.printStackTrace();
                    // 处理异常，比如音频文件无法加载或播放等
                }
                abilityTimer=1000000;//计时器，计算普通技能的冷却时间
                ultimateTimer=10000000;//计算必杀技的冷却时间
                normalAttackTimer=1000000;//计算普通攻击冷却时间
            }
            if(buff.type==3)//获得护盾
            {
                try{
                    File audioFile2 = new File(HelloApplication.class.getResource("/Music/PublicSounds/GainShield.wav").getPath());
                    AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                    AudioFormat format2 = audioStream2.getFormat();
                    DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                    buffSound = (Clip) AudioSystem.getLine(info2);
                    buffSound.open(audioStream2);
                    FloatControl volumeControl = (FloatControl) buffSound.getControl(FloatControl.Type.MASTER_GAIN);
                    volumeControl.setValue(+1.0f); // 将音量降低10分贝（根据需要调整值）


                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    e.printStackTrace();
                    // 处理异常，比如音频文件无法加载或播放等
                }
                shieldTimer+=500;
                playerIcons.add(new Shield(new Image(HelloApplication.class.getResource("/images/OtherImages/Shield.png").toString()),x,y,height,height,gameScene,this));
            }
            if(buff.type==4)//获得加速
            {
                try{
                    File audioFile2 = new File(HelloApplication.class.getResource("/Music/PublicSounds/GainSpeed.wav").getPath());
                    AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                    AudioFormat format2 = audioStream2.getFormat();
                    DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                    buffSound = (Clip) AudioSystem.getLine(info2);
                    buffSound.open(audioStream2);
                    FloatControl volumeControl = (FloatControl) buffSound.getControl(FloatControl.Type.MASTER_GAIN);
                    volumeControl.setValue(+4.7f); // 将音量降低10分贝（根据需要调整值）


                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    e.printStackTrace();
                    // 处理异常，比如音频文件无法加载或播放等
                }
                speedUPTimer=1000;

            }
            buffSound.setFramePosition(0);  // 重置到开始位置
            buffSound.start();


        }


    }
    public void knockback(boolean dir,double knockBackX,double knockBacky)//角色被击退
    {
        if(knockBackX==4.66)//如果击退值是特定值，代表受到的攻击来源是特殊攻击，则做出特定的受击反馈
        {
            for(int i=0;i<30;i++)
            {
                if(dir==false)
                {
                    particleList.add(new Miyabi_SnowFlakes(null,x-20,y+20,10,10,gameScene,this));
                }
                else {
                    particleList.add(new Miyabi_SnowFlakes(null,x+45,y+20,10,10,gameScene,this));

                }

            }
        }
        if(knockBacky==4.01)
        {
            for(int i=0;i<25;i++)
            {
                if(dir==false)
                {
                    particleList.add(new BOSSHitParticle(null,x-20,y,10,10,gameScene,this));
                }
                else {
                    particleList.add(new BOSSHitParticle(null,x+45,y,10,10,gameScene,this));

                }

            }
        }
        if(knockBackX==0&& knockBacky==0)//对于无击退效果的帧伤子弹，采取减速效果
        {
            x_speed=x_speed*0.985;
        }
        else if(knockBackX==0&& knockBacky==3.14)
        {

        }
        else if(knockBackX==0&& knockBacky==4.11)
        {
            x_speed=0;
            Xa=0;
            y_speed+=5;

        }
        else {
            if(speedUPTimer==0)
            {
                BeingHitTimer=knockBackX*2.5;//重置击退冷却时间
                if(dir==false)
                {
                    x_speed-=knockBackX;//设置击退速度
                    Xa=0;
                    y_speed+=knockBacky;
                }
                else
                {
                    x_speed=knockBackX;
                    Xa=0;
                    y_speed+=knockBacky;
                }
            }
            else
            {
                BeingHitTimer=knockBackX*1;
                if(dir==false)
                {
                    x_speed-=knockBackX*0.6;
                    Xa=0;
                    y_speed+=knockBacky*0.6;
                }
                else
                {
                    x_speed=knockBackX*0.6;
                    Xa=0;
                    y_speed+=knockBacky*0.6;
                }

            }

        }



    }

    //碰撞检测
    public boolean impact(Sprite sprite) {//检测玩家与地形方块的碰撞关系
        if(sprite != null && !sprite.equals(this) && getContour().intersects(sprite.getContour())) {
            //检测横撞
            if(calculateRec_height(sprite)>sprite.height+this.height){//如果碰撞前瞬间角色与方块的最小外接矩形大于两者高之和，说明角色是竖着碰到地形的

                if(y_speed<0&&y<sprite.y)//如果角色从上至下落到地上（为了防止穿墙）
                {
                    y=sprite.y-this.height-0.01;//强制将角色y坐标更改为地面高度
                    y_speed=0;
                }
                else if(y_speed>0&&y>sprite.y)//如果判定为角色头顶到天花板
                {
                    y=sprite.y+sprite.height+0.01;//将角色y坐标设置为天花板正下方
                    y_speed=0;
                }

                if(space_flag==true) {//如果碰地且跳跃键按下则跳跃

                    y_speed = 7.5;
                    personJump.setFramePosition(0);  // 重置到开始位置
                    personJump.start();
                    if(speedUPTimer!=0)
                    {
                        personJump.setFramePosition(0);  // 重置到开始位置
                        personJump.start();
                        y_speed=8.3;
                    }
                    onair=true;
                    space_flag=false;
                }
                else
                {
                    if(onair=true)
                    {
                        onair=false;
                    }
                    y_speed=0;
                }
            }
            else if(calculateRec_width(sprite)>sprite.width+this.width){//计算角色与方块的共同最大外接矩形
                //防止一帧速度变化过大导致物体直接嵌入障碍物
                if(x_speed>0 && x<sprite.x)//从左边来碰左边（速度向右，且角色在物体左侧）
                {
                    x=sprite.x-this.width-0.01;//角色x坐标设置为与物体左侧接壤（几乎）
                }
                else if(x_speed<0 && x>sprite.x)
                {
                    x=sprite.x+sprite.width+0.01;
                }
                //x=oldx;
                x_speed=0;
            }

            return true;

        }
        return false;

    }





    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    @Override
    public void paint(GraphicsContext graphicsContext) {//绘画角色
        if(shieldTimer>0)//处理计时器读秒
        {
            shieldTimer-=1;
        }
        if(speedUPTimer>0)
        {
            if(speedUPTimer%3==0)//生成加速粒子
            {
                particleList.add(new SpeedUPParticle(new Image(HelloApplication.class.getResource("/images/OtherImages/SpeedUpParticle.png").toString()),x,y+30,16,16,gameScene,this));

            }
            speedUPTimer-=1;
        }
        if(gameStartCountdown>0)
        {
            gameStartCountdown-=1;

        }

        if(y>810)//若角色掉下悬崖
        {
            if(playerID!=2)
            {
                hp=-100000;//杀死角色
            }
            else {
                if(hp>0)//如果是boss掉下，则削减其生命
                {
                    hp=hp*0.8;
                    personDead.setFramePosition(0);  // 重置到开始位置
                    personDead.start();
                    for(int i=0;i<30;i++)//死亡特效
                    {
                        particleList.add(new PlayerDeadParticle(new Image(HelloApplication.class.getResource("/images/GameUIs/DeadParticle.png").toString()), oldx, oldy+0.2*height, width, width, gameScene,this));
                    }

                }
                if(hp>0)
                {//将坠崖后的boss传送到出生点
                    x=637;
                    y=100;
                }
                x_speed=0;
                y_speed=0;
            }

        }
        if(hp<=0 && reviveClock==0){//代表角色生命为0时
            if(lives>1)//若还有复活集会，消耗一次机会进入复活进程
            {
                lives-=1;
                reviveClock=300;
                playerIcons.add(new ReviveIcon(null, x, y, 300, 170, gameScene, this,playerID));
                y=2999;//暂时将复活中的角色移到屏幕外

            }
            else//如果角色没有复活机会，则宣判死亡不会再复活
            {
                lives=0;
                reviveClock=-1;
            }
            personDead.setFramePosition(0);  // 重置到开始位置
            personDead.start();
            if(gameScene.BotOn==true)//如果在合作模式中死亡，则通知boss杀死了角色
            {
                playerKilledByBOSS();
            }
            for(int i=0;i<30;i++)
            {
                particleList.add(new PlayerDeadParticle(new Image(HelloApplication.class.getResource("/images/GameUIs/DeadParticle.png").toString()), oldx, oldy+0.2*height, width, width, gameScene,this));
            }
            y=2999;
        }
        if(reviveClock>0)
        {
            reviveClock-=1;//复活计时器读秒
            if(reviveClock==0)//复活读秒完成，将角色传送到出生点，并赋予一定时间的护盾
            {
                shieldTimer=150;
                playerIcons.add(new Shield(new Image(HelloApplication.class.getResource("/images/OtherImages/Shield.png").toString()),x,y,height,height,gameScene,this));
                if(playerID==0)
                {
                    hp=100;
                    x=300;
                    y=100;


                }
                else {
                    hp=100;
                    x=1000;
                    y=100;

                }
                y_speed=0;
                x_speed=0;
                Xa=0;

            }
        }
        if(BeingHitTimer>0)//受击计时器重置
        {
            BeingHitTimer-=1;
        }

        abilityTimer+=1;
        normalAttackTimer+=1;
        ultimateTimer+=1;//三个技能冷却时间减少
        countTimer=(countTimer+1)%16;
//判断角色运动状态，并将贴图改为对应的状态
        if(facingDirection==false )//向左走
        {
            if(countTimer<=7)
            {
                image=left1;
            }
            else if(countTimer>=7)
            {
                image=left2;
            }
        }
        else if(facingDirection==true)//向右走
        {
            if(countTimer<=7)
            {
                image=right1;
            }
            else if(countTimer>=7)
            {
                image=right2;
            }
        }
        if(Xa<0.07 && Xa>-0.07 && x_speed<0.1 && x_speed>-0.1)//角色原地不动
        {
            if(facingDirection==false)
            {
                image=noneL;
            }
            else
            {
                image=noneR;
            }
        }
        if(onair==true && y_speed>0)//角色向上跳跃
        {
            if(facingDirection==false)
            {
                image=upleft;
            }
            else
            {
                image=upright;

            }
        }
        else if(onair==true || y_speed<G*-1-0.1)//角色滞空
        {
            if(facingDirection==false)
            {
                image=airleft;
            }
            else
            {
                image=airright;

            }
        }
        if(normalATime>=1)//角色普攻贴图
        {
            normalATime+=1;
            showNormalAttack();
        }
        if(abilityTime>=1)//技能
        {
            abilityTime+=1;
            showAbilityAttack();
        }
        if(ultimateAbilityTime>=1)//大招
        {
            ultimateAbilityTime+=1;
            showUltimateAttack();
        }
        super.paint(graphicsContext);
        // 检查 normalBulletList 是否为空
        if (!particleList.isEmpty()) {//遍历角色的一系列容器，依次画出角色的粒子效果，子弹类等贴图
            for (int i = 0; i < particleList.size(); i++) {
                particleList.get(i).paint(graphicsContext);
            }
        }
        if (!normalBulletList.isEmpty()) {
            for (int i = 0; i < normalBulletList.size(); i++) {
                normalBulletList.get(i).paint(graphicsContext);
            }
        }

// 检查 normalBulletExplodeList 是否为空
        if (!normalBulletExplodeList.isEmpty()) {
            for (int i = 0; i < normalBulletExplodeList.size(); i++) {
                normalBulletExplodeList.get(i).paint(graphicsContext);
            }
        }

// 检查 playerIcons 是否为空
        if (!playerIcons.isEmpty()) {
            for (int i = 0; i < playerIcons.size(); i++) {
                playerIcons.get(i).paint(graphicsContext);
            }
        }
        move();//角色移动逻辑


    }
    //算出两精灵最小外接矩形参数
    public double calculateRec_width(Sprite sprite){
        return calculateMax(sprite.x, sprite.x+sprite.width,this.oldx,this.oldx+this.width);
    }
    public double calculateRec_height(Sprite sprite){
        return calculateMax(sprite.y, sprite.y+sprite.height,this.oldy,this.oldy+this.height);
    }
    public double calculateMax(double n1,double n2,double n3,double n4){//辅助函数，用于计算四个坐标中的最大间距
        double max_1,max_2,max;
        double min_1,min_2,min;
        max_1=Math.max(n1,n2);
        max_2=Math.max(n3,n4);
        min_1=Math.min(n1,n2);
        min_2=Math.min(n3,n4);
        max=Math.max(max_1,max_2);
        min=Math.min(min_1,min_2);
        return max-min;


    }
    public void showNormalAttack()
    {

    }
    public void showAbilityAttack()
    {}
    public void showUltimateAttack()
    {}



    public void playExplodeSound()//播放音效
    {
        try{
            File audioFile2 = new File(HelloApplication.class.getResource("/Music/Cecil/Uexplode.wav").getPath());
            AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
            AudioFormat format2 = audioStream2.getFormat();
            DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
            ExplodeSound = (Clip) AudioSystem.getLine(info2);
            ExplodeSound.open(audioStream2);
            FloatControl volumeControl = (FloatControl) ExplodeSound.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(-1.0f); // 将音量降低10分贝（根据需要调整值）

            ExplodeSound.setFramePosition(0);  // 重置到开始位置
            ExplodeSound.start();


        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            // 处理异常，比如音频文件无法加载或播放等
        }



    }
    public void playHitSound()
    {
        try{
            File audioFile2 = new File(HelloApplication.class.getResource("/Music/Archer/HitAttack.wav").getPath());
            AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
            AudioFormat format2 = audioStream2.getFormat();
            DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
            HitSound = (Clip) AudioSystem.getLine(info2);
            HitSound.open(audioStream2);
            FloatControl volumeControl = (FloatControl) HitSound.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(-0.0f); // 将音量降低10分贝（根据需要调整值）

            HitSound.setFramePosition(0);  // 重置到开始位置
            HitSound.start();



        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            // 处理异常，比如音频文件无法加载或播放等
        }

    }
    public void playHitSoundb7Archer()
    {
        try{
            File audioFile2 = new File(HelloApplication.class.getResource("/Music/Archer/UltimateExplosion.wav").getPath());
            AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
            AudioFormat format2 = audioStream2.getFormat();
            DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
            HitSound = (Clip) AudioSystem.getLine(info2);
            HitSound.open(audioStream2);
            FloatControl volumeControl = (FloatControl) HitSound.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(-0.0f); // 将音量降低10分贝（根据需要调整值）

            HitSound.setFramePosition(0);  // 重置到开始位置
            HitSound.start();



        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            // 处理异常，比如音频文件无法加载或播放等
        }

    }

    public void playerHitByCut()
    {
        try{
            File audioFile2 = new File(HelloApplication.class.getResource("/Music/Vegeta/HitCut.wav").getPath());
            AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
            AudioFormat format2 = audioStream2.getFormat();
            DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
            HitSound = (Clip) AudioSystem.getLine(info2);
            HitSound.open(audioStream2);
            FloatControl volumeControl = (FloatControl) HitSound.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(-1.0f); // 将音量降低10分贝（根据需要调整值）

            HitSound.setFramePosition(0);  // 重置到开始位置
            HitSound.start();



        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            // 处理异常，比如音频文件无法加载或播放等
        }

    }
    public void playerHitByNormal()
    {
        try{
            File audioFile2 = new File(HelloApplication.class.getResource("/Music/Vegeta/NormalHityes.wav").getPath());
            AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
            AudioFormat format2 = audioStream2.getFormat();
            DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
            HitSound = (Clip) AudioSystem.getLine(info2);
            HitSound.open(audioStream2);
            FloatControl volumeControl = (FloatControl) HitSound.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(-4.0f); // 将音量降低10分贝（根据需要调整值）

            HitSound.setFramePosition(0);  // 重置到开始位置
            HitSound.start();



        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            // 处理异常，比如音频文件无法加载或播放等
        }

    }
    public void playerHitByBOSS()
    {
        try{
            File audioFile2 = new File(HelloApplication.class.getResource("/Music/BOSS/BOSShit.wav").getPath());
            AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
            AudioFormat format2 = audioStream2.getFormat();
            DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
            HitSound = (Clip) AudioSystem.getLine(info2);
            HitSound.open(audioStream2);
            FloatControl volumeControl = (FloatControl) HitSound.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(-1.0f); // 将音量降低10分贝（根据需要调整值）

            HitSound.setFramePosition(0);  // 重置到开始位置
            HitSound.start();



        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            // 处理异常，比如音频文件无法加载或播放等
        }

    }
    public void playerKilledByBOSS()
    {
        try{
            File audioFile2 = new File(HelloApplication.class.getResource("/Music/BOSS/BOSSkill.wav").getPath());
            AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
            AudioFormat format2 = audioStream2.getFormat();
            DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
            HitSound = (Clip) AudioSystem.getLine(info2);
            HitSound.open(audioStream2);
            FloatControl volumeControl = (FloatControl) HitSound.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(-1.0f); // 将音量降低10分贝（根据需要调整值）

            HitSound.setFramePosition(0);  // 重置到开始位置
            HitSound.start();



        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            // 处理异常，比如音频文件无法加载或播放等
        }

    }
    public void HitbyStar()
    {
        try{
            File audioFile2 = new File(HelloApplication.class.getResource("/Music/BOSS/Explode.wav").getPath());
            AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
            AudioFormat format2 = audioStream2.getFormat();
            DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
            HitSound = (Clip) AudioSystem.getLine(info2);
            HitSound.open(audioStream2);
            FloatControl volumeControl = (FloatControl) HitSound.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(-1.0f); // 将音量降低10分贝（根据需要调整值）

            HitSound.setFramePosition(0);  // 重置到开始位置
            HitSound.start();



        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            // 处理异常，比如音频文件无法加载或播放等
        }

    }


    public void setY_speed(double y_speed) {
        this.y_speed = y_speed;
    }

    public double getY_speed() {
        return this.y_speed;
    }
    public void activate_space_flag() {
        this.space_flag = true;
    }

    public void disactivate_space_flag() {
        this.space_flag = false;
    }

    public void activate_Onair() {
        this.onair = true;
    }

    public void disactivate_Onair() {
        this.onair = false;
    }

    public boolean get_Onair() {
        return onair;
    }

    public void setX_speed(double x_speed) {
        this.x_speed = x_speed;
    }

    public double getX_speed() {
        return x_speed;
    }










}
