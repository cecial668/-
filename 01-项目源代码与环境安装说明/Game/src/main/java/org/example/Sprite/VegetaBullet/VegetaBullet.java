package org.example.Sprite.VegetaBullet;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Block;
import org.example.Sprite.Bullet;
import org.example.Sprite.Person;
import org.example.Sprite.Sprite;

public class VegetaBullet extends Bullet {

    private Image particle;
    private boolean type=false;
    private int particleCreateTimer=0;
    private int timer=0;
    private int lifeTIme;
    public VegetaBullet(Image image, double x, double y, double width, double height, GameScene gameScene, boolean direction, Person owner,boolean type) {
        super(image, x, y, width, height, gameScene);
        this.direction = direction;//方向，false为向左
        this.owner=owner;//子弹的发射者
        this.KnockbackRatex=3;//水平击退力度
        if(!type){
            this.KnockbackRatey=3;
            this.damage=6;
            lifeTIme=8;
        }else {
            this.KnockbackRatey=8;
            this.damage=15;
            lifeTIme=8;
            speed=8;
        }
        this.type=type;
        //击飞力度
        System.out.println(direction);
        if(direction==false)
        {
            this.x= owner.x-0.5*this.width;
        }
        else {
            this.x=owner.x+owner.width-0.5*this.width;
        }


        //this.group = group;
        //this.dir = dir;
    }


    public void paint(GraphicsContext graphicsContext)
    {
        move();
        timer+=1;
        if (timer>6){
            owner.normalBulletList.remove(this);
        }
        super.paint(graphicsContext);
    }
    public void move()
    {
        if(direction==false)//根据方向移动
        {
            x=this.x= owner.x-0.7*this.width;;
        }
        else
        {
            x=owner.x+owner.width-0.3*this.width;
        }
    }
    public void impactNormal(Sprite sprite)//检测与物体的碰撞
    {
        if ((sprite != null && !sprite.equals(this) && getContour().intersects(sprite.getContour()) && !(sprite instanceof Block)) || x < -50 || x > 1300)
        {
            explode();
        }

    }
    public void explode()
    {
        if(x>-50 && x<1300 && type==true)
        {
            owner.playerHitByCut();
            owner.abilityTimer+=1000;
            owner.ultimateTimer+=400;
        }
        else if(x>-50 && x<1300 && type==false)
        {
            owner.playerHitByNormal();
        }

        //owner.normalBulletExplodeList.add(new NormalBulletExplosion(new Image(HelloApplication.class.getResource("/images/Archer/Particles/e1.png").toString()),x-5,y-9,25,25,gameScene,owner));//生成单体爆炸特效类
        owner.normalBulletList.remove(this);//吩咐角色把自己删了

    }
}
