package org.example.Sprite.MiyabiBullets;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Block;
import org.example.Sprite.Bullet;
import org.example.Sprite.Person;
import org.example.Sprite.Sprite;

public class NormalAttackBullet extends Bullet {
    private Image particle;
    private boolean type=false;
    private int particleCreateTimer=0;
    private int timer=0;
    public NormalAttackBullet(Image image, double x, double y, double width, double height, GameScene gameScene, boolean direction, Person owner) {
        super(image, x, y, width, height, gameScene);
        this.direction = direction;//方向，false为向左
        this.owner=owner;//子弹的发射者
        this.KnockbackRatex=3;//水平击退力度
        this.KnockbackRatey=2;
        //击飞力度
        this.damage=11;//造成伤害
        speed=8;//飞行速度
        System.out.println(direction);


        //this.group = group;
        //this.dir = dir;
    }


    public void paint(GraphicsContext graphicsContext)
    {
        move();
        timer+=1;
        if (timer>12){
            owner.normalBulletList.remove(this);
        }
        super.paint(graphicsContext);
    }
    public void move()
    {
        if(direction==false)//根据方向移动
        {
            x=x-speed;
        }
        else
        {
            x=x+speed;
        }
        y=owner.y-0.6*owner.width;
    }
    public void impactNormal(Sprite sprite)//检测与物体的碰撞
    {
        if ((sprite != null && !sprite.equals(this) && getContour().intersects(sprite.getContour()) && !(sprite instanceof Block)) || x < -50 || x > 1300)
        {
            explode();//爆炸
        }

    }
    public void explode()
    {
        owner.normalBulletList.remove(this);//吩咐角色把自己删了

    }
}
