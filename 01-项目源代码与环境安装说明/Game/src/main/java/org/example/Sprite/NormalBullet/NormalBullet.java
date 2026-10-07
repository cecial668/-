package org.example.Sprite.NormalBullet;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Bullet;
import org.example.Sprite.Particle.EnhancedArrowParticle;
import org.example.Sprite.Person;
import org.example.Sprite.Sprite;
import org.example.gametest.HelloApplication;


public class NormalBullet extends Bullet
{
    private Image particle;
    private int particleCreateTimer=0;
    public NormalBullet(Image image, double x, double y, double width, double height, GameScene gameScene,boolean direction,Person owner) {
        super(image, x, y, width, height, gameScene);
        this.direction = direction;//方向，false为向左
        this.owner=owner;//子弹的发射者
        this.KnockbackRatex=3;//水平击退力度
        this.KnockbackRatey=3;//击飞力度
        this.damage=5;//造成伤害
        speed=8;//飞行速度
        System.out.println(direction);

        if(width==37)//表示如果为强化箭（特征为宽度37），则设置全新的数值
        {
            particle=new Image(HelloApplication.class.getResource("/images/Archer/Particles/Enhanced_Arrow_Particle.png").toString());
            particle=new Image(HelloApplication.class.getResource("/images/Archer/Particles/Enhanced_Arrow_Particle.png").toString());
            particleCreateTimer=1;
            this.KnockbackRatex=5;
            this.KnockbackRatey=4.5;
            this.damage=9;
            this.speed=12;
        }


        //this.group = group;
        //this.dir = dir;
    }


    public void paint(GraphicsContext graphicsContext)
    {
        move();
        super.paint(graphicsContext);
    }
    public void move()
    {
        if(particleCreateTimer>=6)//如果是强化箭，每6帧生成1个粒子
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
        owner.playHitSound();

        owner.normalBulletExplodeList.add(new NormalBulletExplosion(new Image(HelloApplication.class.getResource("/images/Archer/Particles/e1.png").toString()),x-5,y-9,25,25,gameScene,owner));//生成单体爆炸特效类
        owner.normalBulletList.remove(this);//吩咐角色把自己删了

    }
}
