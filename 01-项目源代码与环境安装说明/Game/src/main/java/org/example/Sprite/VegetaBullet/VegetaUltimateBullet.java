package org.example.Sprite.VegetaBullet;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Bullet;
import org.example.Sprite.Person;
import org.example.Sprite.Sprite;
import org.example.gametest.HelloApplication;

public class VegetaUltimateBullet extends Bullet {
    private Image particle;
    double a;
    private int WaveCreateTimer=1;
    double speed=10;
    int Timer=0;
    public VegetaUltimateBullet(Image image, double x, double y, double width, double height, GameScene gameScene, boolean direction, Person owner,int type) {
        super(image, x, y, width, height, gameScene);
        this.direction = direction;
        this.owner=owner;
        this.KnockbackRatex=0;
        this.KnockbackRatey=3.14;
        this.damage=0.005;
        //speed=2;
        this.x=owner.x+0.5*owner.width;
        this.y=owner.y+0.5*owner.height;
        this.width=1;
        this.height=1;
        if(type==0)
        {
            this.image=new Image(HelloApplication.class.getResource("/images/Vegeta/Bullets/U1.png").toString());
        }
        if(type==1)
        {
            this.image=new Image(HelloApplication.class.getResource("/images/Vegeta/Bullets/U2.png").toString());
        }
        if(type==2)
        {
            this.image=new Image(HelloApplication.class.getResource("/images/Vegeta/Bullets/U3.png").toString());
        }
        if(type==3)
        {
            this.image=new Image(HelloApplication.class.getResource("/images/Vegeta/Bullets/U4.png").toString());
        }
    }


    public void paint(GraphicsContext graphicsContext)
    {
        move();
        super.paint(graphicsContext);
    }
    public void move()
    {
        Timer+=1;
        width += speed;
        height += speed;
        x -= speed*0.5;
        y -= speed*0.5;
        if(Timer<100)
        {
            speed=speed*0.98;
            if(speed<0.5)
            {
                speed=0.5;
            }
        }
        else
        {
            damage=damage*0.985;
            speed=speed*1.05;
            if(width>4000)
            {
                owner.normalBulletList.remove(this);
            }
        }
    }
    public void impactNormal(Sprite sprite)
    {

    }
    public void explode()
    {
        //owner.normalBulletList.remove(this);

    }
}
