package org.example.Sprite.CecilSkill1Bullet;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Bullet;
import org.example.Sprite.Person;
import org.example.Sprite.*;
import org.example.gametest.HelloApplication;


public class Skill1Bullet extends Bullet{
    //技能1-1
    public Image EbulletImg1=null;
    //技能1-2
    public Image EbulletImg2=null;
    //技能1-3
    public Image EbulletImg3=null;
    //技能1-4
    public Image EbulletImg4=null;
    //技能1-5
    public Image EbulletImg5=null;
    //技能1-6
    public Image EbulletImg6=null;
    int KillTimer=0;
    private long startTime;
    private boolean timerEnd=false;
    double maxspeed=6;
    private  int timer=0;
    private int bounceTime;
    private boolean isActive = true; // 标记该类是否仍然活跃
    private static final long DURATION_MS = 1000; // 1 second in milliseconds
    private Image particle;
    double a;
    private int particleCreateTimer=0;
    public Skill1Bullet(Image image, double x, double y, double width, double height, GameScene gameScene, boolean direction, Person owner) {
        super(image, x, y, width, height, gameScene);
        //技能1图标
        EbulletImg1=new Image(HelloApplication.class.getResource("/images/Cecil/Bullets/技能1-1.png").toString());
        EbulletImg2=new Image(HelloApplication.class.getResource("/images/Cecil/Bullets/技能1-2.png").toString());
        EbulletImg3=new Image(HelloApplication.class.getResource("/images/Cecil/Bullets/技能1-3.png").toString());
        EbulletImg4=new Image(HelloApplication.class.getResource("/images/Cecil/Bullets/技能1-4.png").toString());
        EbulletImg5=new Image(HelloApplication.class.getResource("/images/Cecil/Bullets/技能1-5.png").toString());
        EbulletImg6=new Image(HelloApplication.class.getResource("/images/Cecil/Bullets/技能1-6.png").toString());
        this.direction = direction;
        this.owner=owner;
        this.KnockbackRatex=0.00;
        this.KnockbackRatey=0.000;
        this.damage=0.005;
        speed=6;

        System.out.println(direction);
        a=0.25;

    }


    public void paint(GraphicsContext graphicsContext)
    {
        KillTimer+=1;
        if(KillTimer>=200)
        {
            speed=speed*1.03;
        }
        move();
        timer+=1;
        image=EbulletImg1;
        if(timer>=0&&timer<=5){
            image=EbulletImg1;
        }
        if(timer>5&&timer<=10){
            image=EbulletImg2;
        }
        if(timer>10&&timer<=15){
            image=EbulletImg3;
        }
        if(timer>15&&timer<=20){
            image=EbulletImg4;
        }
        if(timer>20&&timer<=25){
            image=EbulletImg5;
        }
        if(timer>25&&timer<=30){
            image=EbulletImg6;
        }
        if(timer>30){
        timer=0;
        if(bounceTime>=3)
        {
            owner.normalBulletList.remove(this);
        }
    }

        super.paint(graphicsContext);
    }
    public void move() {

        if (particleCreateTimer > 0) {
            particleCreateTimer += 1;
        }

        if (direction == false) {
            x = x - speed;


        } else {
            x = x + speed;

        }
        if(KillTimer<200)
        {
            speed=speed*0.96;
        }
        if(speed<0.5)
        {
            speed=0.5;
        }
    }



    public void impactNormal(Sprite sprite)
    {

        /*if(x<-50 || x>1500)
        {
                if(direction==false)
                {
                    direction=true;
                    x=x+speed*2;
                }
                else {
                    direction=false;
                    x=x-speed*2;
                }
                bounceTime+=1;
                System.out.println("boundce");
            }
            if(particleCreateTimer==10000){
               // explode();
                particleCreateTimer=0;
        }*/
        if(x<-250 || x>1700)
        {
            owner.normalBulletList.remove(this);
        }




    }
    public void explode()
    {
     /*  //打击后爆炸图
        owner.normalBulletExplodeList.add(new Skill1BulletExplosion(new Image(HelloApplication.class.getResource("/images/Archer/Particles/Archer_Ultimate_Explode1.png").toString()),x,y,60,60,gameScene,owner));
        if(x < -50 || x > 1300){
            owner.normalBulletList.remove(this);

      */
        }


    }



