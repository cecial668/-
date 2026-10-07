package org.example.Sprite.MiyabiBullets;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Bullet;
import org.example.Sprite.Particle.MiyabiEnhancedAttackParticle;
import org.example.Sprite.Person;
import org.example.Sprite.Sprite;
import org.example.gametest.HelloApplication;
import java.util.Random;
public class MiyabiEnhancedNormalBullet extends Bullet {
    private Image particle;
    Random random = new Random();

    private int particleCreateTimer=1;
    Image B1,B2,B3,BA,BB;
    public MiyabiEnhancedNormalBullet(Image image, double x, double y, double width, double height, GameScene gameScene, boolean direction, Person owner) {
        super(image, x, y, width, height, gameScene);
        this.direction = direction;//方向，false为向左
        this.owner=owner;//子弹的发射者
        this.KnockbackRatex=0;//水平击退力度
        this.KnockbackRatey=0;//击飞力度
        this.damage=0.008;//造成伤害
        speed=10;//飞行速度
        System.out.println(direction);
        particle=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Particles/EnhancedNormalParticle.png").toString());
        if(owner.facingDirection==false)
        {
            B1=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Bullets/EnhanceAttack1L.png").toString());
            B2=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Bullets/EnhanceAttack2L.png").toString());
            B3=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Bullets/EnhanceAttack3L.png").toString());
            BA=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Bullets/EnhanceAttackAL.png").toString());
            BB=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Bullets/EnhanceAttackBL.png").toString());

        }
        else {
            B1=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Bullets/EnhanceAttack1.png").toString());
            B2=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Bullets/EnhanceAttack2.png").toString());
            B3=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Bullets/EnhanceAttack3.png").toString());
            BA=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Bullets/EnhanceAttackA.png").toString());
            BB=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Bullets/EnhanceAttackB.png").toString());

        }
        this.image=B1;
        if(direction==false)
        {
            this.x=owner.x-130;
        }
        else {
            this.x=owner.x+owner.width-10;
        }

    }


    public void paint(GraphicsContext graphicsContext)
    {
        move();
        super.paint(graphicsContext);
    }
    public void move()
    {
        if(particleCreateTimer>=3)//如果是强化箭，每6帧生成1个粒子
        {
            System.out.println("pppppp");
            particleCreateTimer=1;//粒子生成计时器，如果是强化箭则始终大于0.
            double randomNumber = (20-random.nextDouble(40));
            if(direction==false)
            {
                owner.particleList.add(new MiyabiEnhancedAttackParticle(particle, x+40, y+22+randomNumber, 15, 15, gameScene,owner));
            }
            else {
                owner.particleList.add(new MiyabiEnhancedAttackParticle(particle, x+100, y+22+randomNumber, 15, 15, gameScene,owner));

            }

        }
        if(particleCreateTimer>0)
        {
            particleCreateTimer+=1;
        }
        speed-=0.15;
        if((speed*5)%6==0)
        {
            image=B1;
        }
        if((speed*5)%6==2)
        {
            image=B2;
        }
        if((speed*5)%6==4)
        {
            image=B3;
        }
        if(speed<1 && speed>0.5)
        {
            image=BA;
        }
        if(speed<0.5)
        {
            image=BB;
        }
        if(speed<0)
        {
            owner.normalBulletList.remove(this);//吩咐角色把自己删了
        }
        if(direction==false)//根据方向移动
        {
            x=x-speed;
        }
        else
        {
            x=x+speed;
        }
    }
    public void impactNormal(Sprite sprite)//检测与物体的碰撞
    {
        if((sprite != null && !sprite.equals(this) && getContour().intersects(sprite.getContour())) || x<-50 || x>1300)//如果：撞到墙or角色（在gamescene中有调用）or飞出地图
        {
            explode();//爆炸
        }

    }
    public void explode()
    {

        //owner.normalBulletExplodeList.add(new NormalBulletExplosion(new Image(HelloApplication.class.getResource("/images/Archer/Particles/e1.png").toString()),x-5,y-9,25,25,gameScene,owner));//生成单体爆炸特效类
        //owner.normalBulletList.remove(this);//吩咐角色把自己删了

    }

}
