package org.example.Sprite.VegetaBullet;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Block;
import org.example.Sprite.Bullet;
import org.example.Sprite.Person;
import org.example.Sprite.Sprite;
import org.example.gametest.HelloApplication;

public class VegetaWaveBullet extends Bullet {
    private Image particle;
    private int timer=0;
    Image b1,b2,b3;
    public VegetaWaveBullet(Image image, double x, double y, double width, double height, GameScene gameScene, boolean direction, Person owner) {
        super(image, x, y, width, height, gameScene);
        this.direction = direction;//方向，false为向左
        this.owner=owner;//子弹的发射者
        this.KnockbackRatex=0;//水平击退力度
        KnockbackRatey=4.11;
        //击飞力度
        this.damage=12;//造成伤害
        speed=5;//飞行速度
        System.out.println(direction);
        b1=this.image;
        b2=new Image(HelloApplication.class.getResource("/images/Vegeta/Bullets/ball2.png").toString());;
        b3=new Image(HelloApplication.class.getResource("/images/Vegeta/Bullets/ball3.png").toString());;
    }


    public void paint(GraphicsContext graphicsContext)
    {
        move();
        timer+=1;
        if(timer%20==0)
        {
            image=b1;
        }
        if(timer%20==5)
        {
            image=b2;
        }
        if(timer%20==10)
        {
            image=b3;
        }
        if(timer%20==15)
        {
            image=b2;
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
        speed=speed*0.98;
        if(speed<1)
        {
            speed=1;
        }
    }
    public void impactNormal(Sprite sprite)//检测与物体的碰撞
    {
        if ((sprite != null && !sprite.equals(this) && getContour().intersects(sprite.getContour()) && !(sprite instanceof Block)) || x < -50 || x > 1300)//如果：撞到墙or角色（在gamescene中有调用）or飞出地图
        {
            explode();//爆炸\
            owner.normalBulletList.remove(this);

        }

    }
    public void explode()
    {
        if(x>-50 && x<1300)
        {
            owner.ultimateTimer+=500;
        }

       owner.normalBulletExplodeList.add(new VegetaWaveExplosion(null,x-65,y-90,200,200,gameScene,owner));//生成单体爆炸特效类
        owner.normalBulletList.remove(this);//吩咐角色把自己删了

    }
}
