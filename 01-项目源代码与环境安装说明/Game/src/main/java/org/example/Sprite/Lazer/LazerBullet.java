package org.example.Sprite.Lazer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Bullet;
import org.example.Sprite.Person;
import org.example.Sprite.Sprite;
import org.example.gametest.HelloApplication;

public class LazerBullet extends Bullet {
    private int PositionIndex;
    private int Timer;
    Image l0,l1,l2,l3,l4,l5,l6;

    public LazerBullet(Image image, double x, double y, double width, double height, GameScene gameScene, Person owner, int PositionIndex) {
        super(image, x, y, width, height, gameScene);
        this.owner=owner;//子弹的发射者
        this.KnockbackRatex=0;//水平击退力度
        this.PositionIndex=PositionIndex;
        this.KnockbackRatey=0;//击飞力度
        this.damage=0.0035;//造成伤害
        l0=new Image(HelloApplication.class.getResource("/images/OtherImages/Lazer0.png").toString());
        l1=new Image(HelloApplication.class.getResource("/images/OtherImages/Lazer1.png").toString());
        l2=new Image(HelloApplication.class.getResource("/images/OtherImages/Lazer2.png").toString());
        l3=new Image(HelloApplication.class.getResource("/images/OtherImages/Lazer3.png").toString());
        l4=new Image(HelloApplication.class.getResource("/images/OtherImages/Lazer4.png").toString());
        l5=new Image(HelloApplication.class.getResource("/images/OtherImages/Lazer5.png").toString());
        l6=new Image(HelloApplication.class.getResource("/images/OtherImages/Lazer6.png").toString());
        this.image=l0;


        //this.group = group;
        //this.dir = dir;
    }


    public void paint(GraphicsContext graphicsContext)
    {
        Timer+=1;
        if(Timer>300)
        {
            owner.normalBulletList.remove(this);
        }
        if(Timer==10)
        {
            image=l1;
        }
        if(Timer==20)
        {
            image=l2;
        }
        if(Timer==30)
        {
            image=l3;
        }
        if(Timer>40&&Timer<260)
        {
            if(Timer%24==0)
            {
                image=l4;
            }
            if(Timer%24==6)
            {
                image=l5;
            }
            if(Timer%24==12)
            {
                image=l6;
            }
            if(Timer%24==18)
            {
                image=l5;
            }
            if(Timer%24==24)
            {
                image=l4;
            }


        }
        if(Timer==260)
        {
            image=l3;
        }
        if(Timer==270)
        {
            image=l2;
        }
        if(Timer==280)
        {
            image=l1;
        }
        if(Timer==290)
        {
            image=l0;
        }

        move();
        if(owner.facingDirection==false)
        {
            x=owner.x-width*0.9-PositionIndex;//始终显示在图标主人的头顶上
            y=owner.y-owner.height*0.1;
        }
        else {
            x=owner.x+owner.width+PositionIndex;//始终显示在图标主人的头顶上
            y=owner.y-owner.height*0.1;
        }
        super.paint(graphicsContext);
    }
    public void move()
    {
        /*if(particleCreateTimer>=6)//如果是强化箭，每6帧生成1个粒子
        {
            particleCreateTimer=1;//粒子生成计时器，如果是强化箭则始终大于0.
            owner.particleList.add(new EnhancedArrowParticle(particle, x, y, 4, 4, gameScene,owner));
        }
        if(particleCreateTimer>0)
        {
            particleCreateTimer+=1;
        }
        if(direction==false)//根据方向移动
        {
            x=x-speed;
        }
        else
        {
            x=x+speed;
        }*/
    }
    public void impactNormal(Sprite sprite)//检测与物体的碰撞
    {

    }
    public void explode()
    {

        //owner.normalBulletExplodeList.add(new NormalBulletExplosion(new Image(HelloApplication.class.getResource("/images/Archer/Particles/e1.png").toString()),x-5,y-9,25,25,gameScene,owner));//生成单体爆炸特效类
        //owner.normalBulletList.remove(this);//吩咐角色把自己删了

    }
}
