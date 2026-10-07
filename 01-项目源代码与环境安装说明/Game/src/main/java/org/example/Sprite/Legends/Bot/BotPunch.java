package org.example.Sprite.Legends.Bot;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import org.example.Sprite.Block;
import org.example.Sprite.Bullet;
import org.example.Sprite.Sprite;
import org.example.Scenes.GameScene;
import org.example.Sprite.Person;

public class BotPunch extends Bullet {
    private Image particle;
    private boolean type=false;
    private int particleCreateTimer=0;
    private int timer=0;
    private int lifeTIme;

    public BotPunch(Image image, double x, double y, double width, double height, GameScene gameScene, boolean direction, Person owner) {

        super(image, x, y, width, height, gameScene);

        this.direction = direction;//方向，false为向左
        this.owner = owner;//子弹的发射者
        this.KnockbackRatex = 7;//水平击退力度

        this.KnockbackRatey = 4.01;
        this.damage = 20;
        lifeTIme = 8;

        this.type = type;
        //击飞力度
        System.out.println(direction);
        if (direction == false) {
            this.x = owner.x - 0.5 * this.width;
        } else {
            this.x = owner.x + owner.width - 0.5 * this.width;
        }
        this.image=null;


        //this.group = group;
        //this.dir = dir;
    }


    public void paint(GraphicsContext graphicsContext) {
        move();
        timer += 1;
        if (timer > 6) {
            owner.normalBulletList.remove(this);
        }
        super.paint(graphicsContext);
    }

    public void move() {
        if (direction == false)//根据方向移动
        {
            x = this.x = owner.x - 0.7 * this.width;
            ;
        } else {
            x = owner.x + owner.width - 0.3 * this.width;
        }
    }

    public void impactNormal(Sprite sprite)//检测与物体的碰撞
    {
        if ((sprite != null && !sprite.equals(this) && getContour().intersects(sprite.getContour()) && !(sprite instanceof Block)) || x < -50 || x > 1300) {
            explode();
        }

    }

    public void explode() {
        /*if (x > -50 && x < 1300 && type == true) {
            owner.playerHitByCut();
        } else if (x > -50 && x < 1300 && type == false) {
            owner.playerHitByNormal();
        }*/
        owner.playerHitByBOSS();


        //owner.normalBulletExplodeList.add(new NormalBulletExplosion(new Image(HelloApplication.class.getResource("/images/Archer/Particles/e1.png").toString()),x-5,y-9,25,25,gameScene,owner));//生成单体爆炸特效类
        owner.normalBulletList.remove(this);//吩咐角色把自己删了

    }
}