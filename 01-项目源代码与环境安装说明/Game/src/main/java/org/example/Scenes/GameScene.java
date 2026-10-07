package org.example.Scenes;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.Controller.ChoiceHeroController;
import org.example.Sprite.BackGround;
import org.example.Sprite.Block;
import org.example.Sprite.BuffBlocks;
import org.example.Sprite.GameUIs.*;
import org.example.Sprite.Legends.Archer;
import org.example.Sprite.Legends.Cecil;
import org.example.Sprite.Legends.Hoshimi_Miyabi;
import org.example.Sprite.Legends.Vegeta;
import org.example.Sprite.Particle.BuffBlock_Particle;
import org.example.Sprite.Legends.Bot.*;
import org.example.Sprite.Person;
import org.example.gametest.HelloApplication;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameScene {
    ChoiceHeroController choiceHero= new ChoiceHeroController();
    private int P1number = choiceHero.getP1LegendNO();
    private int P2number = choiceHero.getP2LegendNO();

    int randomNumber=0;
    public boolean Playsounds=true;
    Random random = new Random();
    Clip BGM;
    Clip EndSound;

    // 生成一个0到2之间的随机整数（包括0和2）

    private Canvas paintBoard=new Canvas(1300,800);
    private KeyProcess keyProcess=new KeyProcess();
    public int Mapkind=1;

    //private int P1number=3;
    //private int P2number=0;
    public int WinnerID=-1;
    public boolean win=false;




    private Person self=null;
    private Person self2=null;
    private Person self3=null;
    //地形方块
    public List<Block> blocks = new ArrayList<>();
    public List<SampleUI> otherUIList = new ArrayList<>();
    public List<BuffBlocks> buffList = new ArrayList<>();
    private static int gamingTimer=0;
    private int sleepTimer=0;

    private Refresh refresh=new Refresh();


    private GraphicsContext graphicsContext = paintBoard.getGraphicsContext2D();
    private PlayerHPBar p1HP=null;
    private PlayerHPBar p2HP=null;
    private PlayerHPBar p3HP=null;
    private BackGround backGround;
    private Stage stage=null;
    public boolean gameRunning=false;


    public boolean BotOn=false;
    Image Block_image1,Block_image2,Block_image3,Block_image1L,Block_image1R,Wall;



    //1
    //private PaintSignInMap paintSignInMap;
    public void setP1number(int P1number) {
        this.P1number = P1number;
    }
    public void setP2number(int P2number) {
        this.P2number = P2number;
    }
    public static int getGamingTimer(){
        return gamingTimer;
    }







    //游戏场景初始化方法
    public void init(Stage stage) {
        gamingTimer=0;
        AnchorPane root = new AnchorPane(paintBoard);
        stage.getScene().setRoot(root);
        stage.getScene().setOnKeyReleased(keyProcess);
        stage.getScene().setOnKeyPressed(keyProcess);
        this.stage=stage;


        otherUIList.add(new StartCountDownUI(null,400, 2000, 500, 250, this));
        if(Mapkind==0)
        {
            backGround=new BackGround(new Image(HelloApplication.class.getResource("/images/BackGrounds/backGround1.png").toString()),0,0,1300,800,this);
            Block_image1=new Image(HelloApplication.class.getResource("/images/Blocks/Map2Block1.png").toString());
            Block_image2=new Image(HelloApplication.class.getResource("/images/Blocks/Map2Block2.png").toString());
            Block_image3=new Image(HelloApplication.class.getResource("/images/Blocks/Map2Block3.png").toString());
            Block_image1L=new Image(HelloApplication.class.getResource("/images/Blocks/Map1Block1L.png").toString());
            Block_image1R=new Image(HelloApplication.class.getResource("/images/Blocks/Map1Block1r.png").toString());

        }
        if(Mapkind==1)
        {
            Block_image1=new Image(HelloApplication.class.getResource("/images/Blocks/Map1Block1.png").toString());
            Block_image2=new Image(HelloApplication.class.getResource("/images/Blocks/Map1Block2.png").toString());
            Block_image3=new Image(HelloApplication.class.getResource("/images/Blocks/Map1Block3.png").toString());
            Block_image1L=new Image(HelloApplication.class.getResource("/images/Blocks/Map1Block1L.png").toString());
            Block_image1R=new Image(HelloApplication.class.getResource("/images/Blocks/Map1Block1r.png").toString());
            backGround=new BackGround(new Image(HelloApplication.class.getResource("/images/BackGrounds/backGround2.png").toString()),0,0,1300,800,this);

        }
        if(Mapkind==2)
        {
            Block_image1=new Image(HelloApplication.class.getResource("/images/Blocks/Map3Block1.png").toString());
            Block_image2=new Image(HelloApplication.class.getResource("/images/Blocks/Map3Block2.png").toString());
            Block_image3=new Image(HelloApplication.class.getResource("/images/Blocks/Map3Block3.png").toString());
            Wall=new Image(HelloApplication.class.getResource("/images/Blocks/Map3Wall.png").toString());
            backGround=new BackGround(new Image(HelloApplication.class.getResource("/images/BackGrounds/backGround3.png").toString()),0,0,1300,800,this);

        }
;
        if(Mapkind==0)
        {
            for(int i=110;i<=1160;i+=30)
            {
                randomNumber = random.nextInt(3);
                if(randomNumber==0)
                {
                    blocks.add(new Block(Block_image1,i,600,30,30,this));
                }
                if(randomNumber==1)

                {
                    blocks.add(new Block(Block_image2,i,600,30,30,this));
                }
                if(randomNumber==2)
                {
                    blocks.add(new Block(Block_image3,i,600,30,30,this));
                }

            }
            for(int i=310;i<=460;i+=30)
            {
                int randomNumber = random.nextInt(3);
                if(randomNumber==0)
                {
                    blocks.add(new Block(Block_image1,i,470,30,30,this));
                }
                if(randomNumber==1)
                {
                    blocks.add(new Block(Block_image2,i,470,30,30,this));
                }
                if(randomNumber==2)
                {
                    blocks.add(new Block(Block_image3,i,470,30,30,this));
                }
            }

            for(int i=780;i<=930;i+=30)
            {
                int randomNumber = random.nextInt(3);
                if(randomNumber==0)
                {
                    blocks.add(new Block(Block_image1,i,470,30,30,this));
                }
                if(randomNumber==1)
                {
                    blocks.add(new Block(Block_image2,i,470,30,30,this));
                }
                if(randomNumber==2)
                {
                    blocks.add(new Block(Block_image3,i,470,30,30,this));
                }
            }
            for(int i=530;i<=710;i+=30)
            {
                int randomNumber = random.nextInt(3);
                if(randomNumber==0)
                {
                    blocks.add(new Block(Block_image1,i,340,30,30,this));
                }
                if(randomNumber==1)
                {
                    blocks.add(new Block(Block_image2,i,340,30,30,this));
                }
                if(randomNumber==2)
                {
                    blocks.add(new Block(Block_image3,i,340,30,30,this));
                }
            }

        }
        else if(Mapkind==1)
        {
            for(int i=110;i<=440;i+=30)
            {
                randomNumber = random.nextInt(3);
                if(randomNumber==0)
                {
                    blocks.add(new Block(Block_image1,i,600,30,30,this));
                }
                if(randomNumber==1)
                {
                    blocks.add(new Block(Block_image2,i,600,30,30,this));
                }
                if(randomNumber==2)
                {
                    blocks.add(new Block(Block_image3,i,600,30,30,this));
                }
            }
            for(int i=830;i<=1160;i+=30)
            {
                randomNumber = random.nextInt(3);
                if(randomNumber==0)
                {
                    blocks.add(new Block(Block_image1,i,600,30,30,this));
                }
                if(randomNumber==1)
                {
                    blocks.add(new Block(Block_image2,i,600,30,30,this));
                }
                if(randomNumber==2)
                {
                    blocks.add(new Block(Block_image3,i,600,30,30,this));
                }
            }
            for(int i=500;i<=770;i+=30)
            {
                int randomNumber = random.nextInt(3);
                if(randomNumber==0)
                {
                    blocks.add(new Block(Block_image1,i,490,30,30,this));
                }
                if(randomNumber==1)
                {
                    blocks.add(new Block(Block_image2,i,490,30,30,this));
                }
                if(randomNumber==2)
                {
                    blocks.add(new Block(Block_image3,i,490,30,30,this));
                }
            }
            for(int i=230;i<=440;i+=30)
            {
                randomNumber = random.nextInt(3);
                if(randomNumber==0)
                {
                    blocks.add(new Block(Block_image1,i,360,30,30,this));
                }
                if(randomNumber==1)
                {
                    blocks.add(new Block(Block_image2,i,360,30,30,this));
                }
                if(randomNumber==2)
                {
                    blocks.add(new Block(Block_image3,i,360,30,30,this));
                }
            }
            for(int i=830;i<=1040;i+=30)
            {
                randomNumber = random.nextInt(3);
                if(randomNumber==0)
                {
                    blocks.add(new Block(Block_image1,i,360,30,30,this));
                }
                if(randomNumber==1)
                {
                    blocks.add(new Block(Block_image2,i,360,30,30,this));
                }
                if(randomNumber==2)
                {
                    blocks.add(new Block(Block_image3,i,360,30,30,this));
                }
            }
            for(int i=560;i<=710;i+=30)
            {
                int randomNumber = random.nextInt(3);
                if(randomNumber==0)
                {
                    blocks.add(new Block(Block_image1,i,250,30,30,this));
                }
                if(randomNumber==1)
                {
                    blocks.add(new Block(Block_image2,i,250,30,30,this));
                }
                if(randomNumber==2)
                {
                    blocks.add(new Block(Block_image3,i,250,30,30,this));
                }
            }



        }
        else if(Mapkind==2)
        {
            for(int i=200;i<=1070;i+=30)
            {
                randomNumber = random.nextInt(3);
                if(randomNumber==0)
                {
                    blocks.add(new Block(Block_image1,i,650,30,30,this));
                }
                if(randomNumber==1)
                {
                    blocks.add(new Block(Block_image2,i,650,30,30,this));
                }
                if(randomNumber==2)
                {
                    blocks.add(new Block(Block_image3,i,650,30,30,this));
                }

            }
            blocks.add(new Block(Wall,170,140,30,540,this));
            blocks.add(new Block(Wall,1100,140,30,540,this));
            blocks.add(new Block(Wall,605,420,90,260,this));
            for(int i=200;i<=1070;i+=30)
            {
                randomNumber = random.nextInt(3);
                if(randomNumber==0)
                {
                    blocks.add(new Block(Block_image1,i,140,30,30,this));
                }
                if(randomNumber==1)
                {
                    blocks.add(new Block(Block_image2,i,140,30,30,this));
                }
                if(randomNumber==2)
                {
                    blocks.add(new Block(Block_image3,i,140,30,30,this));
                }

            }
            for(int i=310;i<=460;i+=30)
            {
                int randomNumber = random.nextInt(3);
                if(randomNumber==0)
                {
                    blocks.add(new Block(Block_image1,i,520,30,30,this));
                }
                if(randomNumber==1)
                {
                    blocks.add(new Block(Block_image2,i,520,30,30,this));
                }
                if(randomNumber==2)
                {
                    blocks.add(new Block(Block_image3,i,520,30,30,this));
                }
            }

            for(int i=800;i<=950;i+=30)
            {
                int randomNumber = random.nextInt(3);
                if(randomNumber==0)
                {
                    blocks.add(new Block(Block_image1,i,520,30,30,this));
                }
                if(randomNumber==1)
                {
                    blocks.add(new Block(Block_image2,i,520,30,30,this));
                }
                if(randomNumber==2)
                {
                    blocks.add(new Block(Block_image3,i,520,30,30,this));
                }
            }
            for(int i=310;i<=460;i+=30)
            {
                int randomNumber = random.nextInt(3);
                if(randomNumber==0)
                {
                    blocks.add(new Block(Block_image1,i,290,30,30,this));
                }
                if(randomNumber==1)
                {
                    blocks.add(new Block(Block_image2,i,290,30,30,this));
                }
                if(randomNumber==2)
                {
                    blocks.add(new Block(Block_image3,i,290,30,30,this));
                }
            }

            for(int i=800;i<=950;i+=30)
            {
                int randomNumber = random.nextInt(3);
                if(randomNumber==0)
                {
                    blocks.add(new Block(Block_image1,i,290,30,30,this));
                }
                if(randomNumber==1)
                {
                    blocks.add(new Block(Block_image2,i,290,30,30,this));
                }
                if(randomNumber==2)
                {
                    blocks.add(new Block(Block_image3,i,290,30,30,this));
                }
            }


        }



        P1number= ChoiceHeroController.getP1LengedNo();
        P2number= ChoiceHeroController.getP2LengedNo();


        if(P1number==2)
        {
            self = new Cecil(null,300,100,35,40,this,0);

        }
        else if(P1number==4)
        {
            self = new Hoshimi_Miyabi(null,300,100,35,40,this,0);

        }
        else if(P1number==1)
        {
            self = new Archer(null,300,100,35,40,this,0);

        }
        else if(P1number==3)
        {
            self = new Vegeta(null,300,100,35,40,this,0);

        }
        if(P2number==2)
        {
            self2 = new Cecil(null,1000,100,35,40,this,1);

        }
        else if(P2number==4)
        {
            self2 = new Hoshimi_Miyabi(null,1000,100,35,40,this,1);
        }
        else if(P2number==1)
        {
            self2 = new Archer(null,1000,100,35,40,this,1);

        }
        else if(P2number==3)
        {
            self2 = new Vegeta(null,1000,100,35,40,this,1);

        }
        if(BotOn==true)
        {
            self3 = new Bot(null, 637, 100, 35, 40, this, 2, null, null);
        }
        if(Mapkind==2)
        {
            self.y+=200;
            self2.y+=200;
        }






        otherUIList.add(new AbilityUI(null,330,39,30,30,this,self));
        otherUIList.add(new AbilityUI(null,935,39,30,30,this,self2));
        otherUIList.add(new UltimateUI(null,370,39,60,30,this,self));
        otherUIList.add(new UltimateUI(null,865,39,60,30,this,self2));
        otherUIList.add(new AbilityChargingUI(null,330,39,30,30,this,self));
        otherUIList.add(new AbilityChargingUI(null,935,39,30,30,this,self2));
        otherUIList.add(new UltimateChargingUI(null,370,39,60,30,this,self));
        otherUIList.add(new UltimateChargingUI(null,865,39,60,30,this,self2));
        p1HP=new PlayerHPBar(new Image(HelloApplication.class.getResource("/images/GameUIs/P1HPBar.png").toString()),10,10,450,82,this,self);
        p2HP=new PlayerHPBar(new Image(HelloApplication.class.getResource("/images/GameUIs/P2HPBar.png").toString()),840,10,450,82,this,self2);
        if(BotOn==true)
        {
            p3HP=new PlayerHPBar(new Image(HelloApplication.class.getResource("/images/GameUIs/BOSSBAR.png").toString()),446,-15,400,130,this,self3);
        }
        otherUIList.add(new PlayerIcon(self.noneR,0,0,35,40,this,0));
        otherUIList.add(new PlayerIcon(self2.noneL,0,0,35,40,this,1));
        //paintSignInMap = new PaintSignInMap(this);
        refresh.start();
    }




    //画
    public void paint(){
        if(sleepTimer>0){
            sleepTimer+=1;
        }
        if((self.lives==0||self2.lives==0)&&gameRunning==true&&BotOn==false){
            sleepTimer+=1;
            otherUIList.add(new GameEndIcon(null,0,0,600,300,this,0));
            otherUIList.add(new GameEndIcon(null,0,0,600,300,this,1));
            gameRunning=false;
            if(self.lives==0)
            {
                WinnerID=1;
            }
            else {
                WinnerID=0;
            }
        }
        else if(BotOn==true &&self.lives==0 &&self2.lives==0&&gameRunning==true)
        {
            otherUIList.add(new GameEndIcon(null,0,0,600,300,this,0));
            otherUIList.add(new GameEndIcon(null,0,0,600,300,this,1));
            gameRunning=false;
            WinnerID=-1;
            win=false;
            sleepTimer=1;




        }
        else if(BotOn==true && gameRunning==true && self3.lives==0)
        {
            otherUIList.add(new GameEndIcon(null,0,0,600,300,this,0));
            otherUIList.add(new GameEndIcon(null,0,0,600,300,this,1));
            gameRunning=false;
            WinnerID=-1;
            win=true;
            sleepTimer=1;


        }
        if(gamingTimer==1)
        {
            if(BotOn==false)
            {
                try{
                    File audioFile2 = new File(HelloApplication.class.getResource("/Music/PublicSounds/PrisonersAwaking.wav").getPath());
                    AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                    AudioFormat format2 = audioStream2.getFormat();
                    DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                    BGM = (Clip) AudioSystem.getLine(info2);
                    BGM.open(audioStream2);
                    FloatControl volumeControl = (FloatControl) BGM.getControl(FloatControl.Type.MASTER_GAIN);
                    volumeControl.setValue(-5.0f);


                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    e.printStackTrace();
                    // 处理异常，比如音频文件无法加载或播放等
                }
            }
            else {
                try{
                    File audioFile2 = new File(HelloApplication.class.getResource("/Music/PublicSounds/BOSSbattle.wav").getPath());
                    AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                    AudioFormat format2 = audioStream2.getFormat();
                    DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                    BGM = (Clip) AudioSystem.getLine(info2);
                    BGM.open(audioStream2);
                    FloatControl volumeControl = (FloatControl) BGM.getControl(FloatControl.Type.MASTER_GAIN);
                    volumeControl.setValue(-2.0f);


                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    e.printStackTrace();
                    // 处理异常，比如音频文件无法加载或播放等
                }
            }



        }
        if(gameRunning==true)
        {
            gamingTimer+=1;
            if (BGM != null && !BGM.isRunning()) {
                BGM.setFramePosition(0);  // 重置到开始位置
                BGM.start();
            }
        }
        else {
            if(gamingTimer!=0)
            {
                if(EndSound==null)
                {
                    BGM.stop();
                    try{
                        File audioFile2 = new File(HelloApplication.class.getResource("/Music/PublicSounds/GameEnd.wav").getPath());
                        AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                        AudioFormat format2 = audioStream2.getFormat();
                        DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                        EndSound = (Clip) AudioSystem.getLine(info2);
                        EndSound.open(audioStream2);
                        FloatControl volumeControl = (FloatControl) EndSound.getControl(FloatControl.Type.MASTER_GAIN);
                        volumeControl.setValue(+1.0f);


                    } catch (UnsupportedAudioFileException | IOException |LineUnavailableException e) {
                        e.printStackTrace();
                    }
                    EndSound.setFramePosition(0);  // 重置到开始位置
                    EndSound.start();

                }


            }

        }
        if(gamingTimer%1000==0&&buffList.isEmpty()&&gamingTimer!=0)
        {

            if(Mapkind==0)
            {
                int randpos = random.nextInt(4);
                if(randpos==0)
                {
                    buffList.add(new BuffBlocks(null,450,400,30,30,this));
                }
                if(randpos==1)
                {
                    buffList.add(new BuffBlocks(null,850,400,30,30,this));
                }
                if(randpos==2)
                {
                    buffList.add(new BuffBlocks(null,635,300,30,30,this));
                }
                if(randpos==3)
                {
                    buffList.add(new BuffBlocks(null,635,500,30,30,this));
                }

            }
            if(Mapkind==1)
            {
                int randpos = random.nextInt(4);
                if(randpos==0)
                {
                    buffList.add(new BuffBlocks(null,635,200,30,30,this));
                }
                if(randpos==1)
                {
                    buffList.add(new BuffBlocks(null,635,400,30,30,this));
                }
                if(randpos==2)
                {
                    buffList.add(new BuffBlocks(null,200,550,30,30,this));
                }
                if(randpos==3)
                {
                    buffList.add(new BuffBlocks(null,1100,560,30,30,this));
                }

            }

        }
        if(self.gameStartCountdown==0&&(self.lives!=0&&self2.lives!=0)&&BotOn==false)
        {
            gameRunning=true;

        }
        if(self3!=null)
        {
            if(self.gameStartCountdown==0&&(self.lives!=0&&self2.lives!=0&&self3.lives!=0)&&BotOn==true)
            {
                gameRunning=true;

            }

        }


        graphicsContext.clearRect(0,0,1300,900);
        backGround.paint(graphicsContext);
        for (int i = 0; i < blocks.size(); i++) {
            Block blocktemp = blocks.get(i);
            blocktemp.paint(graphicsContext);
        }
        for(Block b:blocks){
            self.impact(b);
        }
        for(Block b:blocks){
            self2.impact(b);
            if(BotOn==true)
            {
                //1
                self3.impact(b);
            }
        }
        if (!self.normalBulletList.isEmpty())
        {
            for (int i = 0; i < self.normalBulletList.size(); i++) {
                for(Block b:blocks)
                {

                    if(BotOn==true)
                    {
                        self3.impactWithNormalBullet(self.normalBulletList.get(i));
                        self.normalBulletList.get(i).impactNormal(b);//检测自己的子弹是否撞墙
                    }
                    else {
                        self.normalBulletList.get(i).impactNormal(b);//检测自己的子弹是否撞墙
                        self2.impactWithNormalBullet(self.normalBulletList.get(i));//检测自己的子弹是否撞到对方，如果是则调用对方受击的方法

                    }
                }

            }

        }

        if (!self2.normalBulletList.isEmpty())
        {
            for (int i = 0; i < self2.normalBulletList.size(); i++) {
                for(Block b:blocks)
                {

                    if(BotOn==true)
                    {
                        self3.impactWithNormalBullet(self2.normalBulletList.get(i));
                        self2.normalBulletList.get(i).impactNormal(b);
                    }
                    else {
                        self2.normalBulletList.get(i).impactNormal(b);
                        self.impactWithNormalBullet(self2.normalBulletList.get(i));

                    }
                }
            }

        }

        if(BotOn==true)
        {
            for (int i = 0; i < self3.normalBulletList.size(); i++) {
                for (Block b : blocks) {
                    self3.normalBulletList.get(i).impactNormal(b);
                    self2.impactWithNormalBullet(self3.normalBulletList.get(i));
                    self.impactWithNormalBullet(self3.normalBulletList.get(i));

                }
            }
        }
        if (!buffList.isEmpty())
        {
            for (int i = 0; i < buffList.size(); i++) {
                self.impactWithBuff(buffList.get(i));
                buffList.get(i).impactNormal(self);
                self2.impactWithBuff(buffList.get(i));
                buffList.get(i).impactNormal(self2);
            }

        }


        self.impact(self2);
        self2.impact(self);

        if(BotOn==true)
        {
            self.impact(self3);
            self2.impact(self3);
            self3.impact(self2);
            self3.impact(self);
            self3.paint(graphicsContext);
        }
        self.paint(graphicsContext);
        self2.paint(graphicsContext);
        p1HP.paint(graphicsContext);
        p2HP.paint(graphicsContext);
        if(BotOn==true)
        {
            p3HP.paint(graphicsContext);

        }
        if (!otherUIList.isEmpty()) {
            for (int i = 0; i < otherUIList.size(); i++) {
                otherUIList.get(i).paint(graphicsContext);
            }
        }
        if (!buffList.isEmpty()) {
            for (int i = 0; i < buffList.size(); i++) {
                buffList.get(i).paint(graphicsContext);
                if(gamingTimer%2==0)
                {
                    if(gameRunning==true)
                    {
                        self.particleList.add(new BuffBlock_Particle(new Image(HelloApplication.class.getResource("/images/OtherImages/Buff_Particle.png").toString()),buffList.get(i).x+15,buffList.get(i).y+15,3,3,this,self));
                    }

                }
            }
        }
        //结束后的页面跳转
        if (sleepTimer==1000){
            if (BotOn) {
                jumpToPVEGameOverPage();
            }else{
                jumpToPVPGameOverPage();
            }
        }

    }
    public void clear(){

    }
    //键盘监听类
    private class KeyProcess implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            KeyCode keyCode = event.getCode();

            if(event.getEventType() == KeyEvent.KEY_PRESSED) {
                if(self != null) {
                    self.pressed(keyCode);
                    self2.pressed(keyCode);
                }
            }
            else if(event.getEventType() == KeyEvent.KEY_RELEASED) {
                if(self != null) {
                    self.released(keyCode);
                    self2.released(keyCode);

                }
            }
        }

    }







    private class Refresh extends AnimationTimer {

        @Override
        public void handle(long l) {
            paint();
        }
    }

    public void jumpToPVEGameOverPage(){
        Scene scene = null;
        if(win){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gameOverPVE1.fxml"));
            try {
                scene = new Scene(fxmlLoader.load(),1300,800);
                stage.setScene(scene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }else {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gameOverPVE2.fxml"));
            try {
                scene = new Scene(fxmlLoader.load(),1300,800);
                stage.setScene(scene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void jumpToPVPGameOverPage(){
        Scene scene = null;
        if(WinnerID==0){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gameOverPVP1.fxml"));
            try {
                scene = new Scene(fxmlLoader.load(),1300,800);
                stage.setScene(scene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gameOverPVP2.fxml"));
            try {
                scene = new Scene(fxmlLoader.load(),1300,800);
                stage.setScene(scene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }




    public Person getPlayer1() {
        return self;
    }

    public Person getPlayer2() {
        return self2;
    }

    public Person getBot() {
        if(BotOn==true)
        {
            return self3;
        }
        else {
            return null;
        }
    }
    public void setBotOn(boolean Boton){
        BotOn=Boton;
    }
    public void setMapkind(int mapkind){
        Mapkind=mapkind;
    }
    public double getX1() {
        return self.x;
    }

    public double getX2() {
        return self2.x;
    }

    public double getY1() {
        return self.y;
    }
    public double getY2() {
        return self2.y;
    }


    //public List<Block> getBlocks() {
      //  return blocks;
  //  }

    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    public double getX3() {
        return self3.x;
    }
    public double getY3() {
        return self3.y;
    }


}