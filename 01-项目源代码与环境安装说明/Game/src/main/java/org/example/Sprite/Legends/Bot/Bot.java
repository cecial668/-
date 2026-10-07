package org.example.Sprite.Legends.Bot;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.FloatingIcons.BOSSFlame;
import org.example.Sprite.Person;
import org.example.gametest.HelloApplication;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Bot extends Person {
    private double attackRange = 50.0; // 攻击范围
    private double moveSpeed = 2; // 移动速度
    private GameScene gameScene;
    public JumpPoint[] jumpPoints;
    public int currentJumpPointIndex = 0;
    private BotPunch botPunch;
    private ExecutorService executorService = Executors.newCachedThreadPool(); // 线程池
    private Movement movement;
    private Dijkstra dijkstra;
    private BOSSFlame flame;
    int nextTimer;
    int STimer;
    Clip bossBorn,bossAttack;
    private boolean secondPart=false;
    private static final Random RANDOM = new Random();

    public Bot(Image image, double x, double y, double width, double height, GameScene gameScene, int playerID, BotPunch botPunch, Dijkstra dijkstra) {
        super(image, x, y, width, height, gameScene, playerID);
        this.gameScene = gameScene; 
        this.dijkstra = dijkstra;
        this.botPunch = botPunch != null ? botPunch : new BotPunch(image, x, y, width, height, gameScene, facingDirection, this);
        this.movement = new Movement(this, gameScene, playerID, this.botPunch, this.dijkstra);
        initializeImages();
    }

    private void initializeImages() {
        noneL = new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherSilentL.png").toString());
        noneR = new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherSilentR.png").toString());
        left1 = new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherWalkL1.png").toString());
        left2 = new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherWalkL2.png").toString());
        right1 = new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherWalkR1.png").toString());
        right2 = new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherWalkR2.png").toString());
        upleft = new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherJumpLeft.png").toString());
        upright = new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherJumpRight.png").toString());
        airleft = new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherJumpLeft.png").toString());
        airright = new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherJumpRight.png").toString());
        normalAttackleft = new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherNormalAttackLeft.png").toString());
        normalAttackright = new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherNormalAttackRight.png").toString());
        abilityLeft = new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherAbilityLeft.png").toString());
        abilityRight = new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherAbilityRight.png").toString());
        ultimateLeft = new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherUltimateLeft.png").toString());
        ultimateRight = new Image(HelloApplication.class.getResource("/images/Archer/Actions/ArcherUltimateRight.png").toString());
        flame=new BOSSFlame(null,x,y,250,250,gameScene,this);
    }

    public double getHp() {
        return hp;
    }

    public void paint(GraphicsContext graphicsContext) // 每一帧都会被调用的方法
    {
        if(secondPart==true)
        {
            flame.paint(graphicsContext);
        }

        if(attackCooldown>0)
        {
            attackCooldown-=1;
        }
        if(this.hp<500 && this.lives>0)
        {
            STimer+=1;
            if(STimer>nextTimer)
            {
                STimer=0;
                int times;
                if(hp>250)
                {
                    times=RANDOM.nextInt(1);

                }
                else {
                    times=1+RANDOM.nextInt(1);
                }


                nextTimer=80+(int)(hp/5)+RANDOM.nextInt(100+(int)(hp/5));
                for(int i=0;i<times;i++)
                {
                    double w=50+RANDOM.nextDouble(50);
                    if(nextTimer%2==0)
                    {
                        normalBulletList.add(new BotShootingStar(null,RANDOM.nextDouble(1300),w*-1-RANDOM.nextDouble(300),w,w,gameScene,true,this));
                    }
                    else {
                        normalBulletList.add(new BotShootingStar(null,RANDOM.nextDouble(1300),w*-1-RANDOM.nextDouble(300),w,w,gameScene,false,this));

                    }

                }
                if(gameScene.getPlayer1().lives<1)
                {
                    double w=50+RANDOM.nextDouble(50);
                    if(nextTimer%2==0)
                    {
                        normalBulletList.add(new BotShootingStar(null,RANDOM.nextDouble(1300),w*-1-RANDOM.nextDouble(300),w,w,gameScene,true,this));
                    }
                    else {
                        normalBulletList.add(new BotShootingStar(null,RANDOM.nextDouble(1300),w*-1-RANDOM.nextDouble(300),w,w,gameScene,false,this));

                    }

                }
                else
                {
                    double w=50+RANDOM.nextDouble(50);
                    if(nextTimer%2==0)
                    {
                        normalBulletList.add(new BotShootingStar(null,gameScene.getX1(),w*-1-RANDOM.nextDouble(300),w,w,gameScene,true,this));
                    }
                    else {
                        normalBulletList.add(new BotShootingStar(null,gameScene.getX1(),w*-1-RANDOM.nextDouble(300),w,w,gameScene,false,this));
                    }

                }


            }
            if(STimer==(int)nextTimer/2)
            {
                if(gameScene.getPlayer2().lives<1)
                {
                    double w=50+RANDOM.nextDouble(50);
                    if(nextTimer%2==0)
                    {
                        normalBulletList.add(new BotShootingStar(null,RANDOM.nextDouble(1300),w*-1-RANDOM.nextDouble(300),w,w,gameScene,true,this));
                    }
                    else {
                        normalBulletList.add(new BotShootingStar(null,RANDOM.nextDouble(1300),w*-1-RANDOM.nextDouble(300),w,w,gameScene,false,this));

                    }

                }
                else
                {
                    double w=50+RANDOM.nextDouble(50);
                    if(nextTimer%2==0)
                    {
                        normalBulletList.add(new BotShootingStar(null,gameScene.getX2(),w*-1-RANDOM.nextDouble(300),w,w,gameScene,true,this));
                    }
                    else {
                        normalBulletList.add(new BotShootingStar(null,gameScene.getX2(),w*-1-RANDOM.nextDouble(300),w,w,gameScene,false,this));
                    }

                }
                int times;
                if(hp>250)
                {
                    times=RANDOM.nextInt(1);

                }
                else {
                    times=1+RANDOM.nextInt(1);
                }
                for(int i=0;i<times;i++)
                {
                    double w=50+RANDOM.nextDouble(50);
                    if(nextTimer%2==0)
                    {
                        normalBulletList.add(new BotShootingStar(null,RANDOM.nextDouble(1300),w*-1-RANDOM.nextDouble(300),w,w,gameScene,true,this));
                    }
                    else {
                        normalBulletList.add(new BotShootingStar(null,RANDOM.nextDouble(1300),w*-1-RANDOM.nextDouble(300),w,w,gameScene,false,this));

                    }

                }
            }

        }
        //System.out.println("attempt to paint");
        super.paint(graphicsContext);
        if(secondPart==false && hp<500)
        {
            try{
                File audioFile2 = new File(HelloApplication.class.getResource("/Music/BOSS/BOSSborn.wav").getPath());
                AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                AudioFormat format2 = audioStream2.getFormat();
                DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                bossBorn = (Clip) AudioSystem.getLine(info2);
                bossBorn.open(audioStream2);
                FloatControl volumeControl = (FloatControl) bossBorn.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(-1.0f); // 将音量降低10分贝（根据需要调整值）


            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
                // 处理异常，比如音频文件无法加载或播放等
            }
            bossBorn.setFramePosition(0);  // 重置到开始位置
            bossBorn.start();
            secondPart=true;

        }
        if(secondPart==true)
        {
            moveSpeed=2.5;
        }
        if(GameScene.getGamingTimer()==100)
        {
            try{
                File audioFile2 = new File(HelloApplication.class.getResource("/Music/BOSS/BOSSborn.wav").getPath());
                AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                AudioFormat format2 = audioStream2.getFormat();
                DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                bossBorn = (Clip) AudioSystem.getLine(info2);
                bossBorn.open(audioStream2);
                FloatControl volumeControl = (FloatControl) bossBorn.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(-1.0f); // 将音量降低10分贝（根据需要调整值）


            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
                // 处理异常，比如音频文件无法加载或播放等
            }
            bossBorn.setFramePosition(0);  // 重置到开始位置
            bossBorn.start();

        }
        //System.out.println("paint bot");
    }

    @Override
    public void move() {
        super.move();
        //System.out.println("moving bot");
        if(gameScene.gameRunning==true)
        {
            movement.move();

        }

        
    }
        
    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public BotPunch getBotPunch() {
        return botPunch;
    }

    public GameScene getGameScene() {
        return gameScene;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public double getAttackrange() {
        return attackRange;
    }

    public double getMoveSpeed() {
        return moveSpeed;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public int getCurrentJumpPointIndex() {
        return currentJumpPointIndex;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }



    @Override
    public void setY_speed(double y_speed) {
        super.setY_speed(y_speed);
    }

    @Override
    public double getX_speed() {
        return super.getX_speed();
    }

    @Override
    public void setX_speed(double x_speed) {
        super.setX_speed(x_speed);
    }

    @Override
    public double getY_speed() {
        return super.getY_speed();
    }

    @Override
    public void activate_space_flag() {
        super.activate_space_flag();
    }

    @Override
    public void disactivate_space_flag() {
        super.disactivate_space_flag();
    }

    @Override
    public void activate_Onair() {
        super.activate_Onair();
    }

    @Override
    public void disactivate_Onair() {
        super.disactivate_Onair();
    }

    @Override
    public boolean get_Onair() {
        return super.get_Onair();
    }
}