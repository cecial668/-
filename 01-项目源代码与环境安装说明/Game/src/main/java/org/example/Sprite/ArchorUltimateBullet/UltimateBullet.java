package org.example.Sprite.ArchorUltimateBullet;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Bullet;
import org.example.Sprite.Particle.ArcherExplosionParticle;
import org.example.Sprite.Particle.ArchorUltimateParticle;
import org.example.Sprite.Person;
import org.example.Sprite.*;
import org.example.gametest.HelloApplication;





public class UltimateBullet extends Bullet
{

    private Image particle;
    double a;
    private int particleCreateTimer=1;
    public UltimateBullet(Image image, double x, double y, double width, double height, GameScene gameScene, boolean direction, Person owner) {
        super(image, x, y, width, height, gameScene);
        this.direction = direction;
        this.owner=owner;
        this.KnockbackRatex=8;
        this.KnockbackRatey=6;
        this.damage=15;
        speed=2;
        System.out.println(direction);
        if(width==60)
        {
            a=0.2;
        }
        if(width==80)
        {
            a=0.1;
        }
        if(width==100)
        {
            a=0.06;
        }


    }


    public void paint(GraphicsContext graphicsContext)
    {
        move();
        super.paint(graphicsContext);
    }
    public void move()
    {
        if(particleCreateTimer>=6)
        {
            particleCreateTimer=1;
            if(direction==false)
            {
                particle=new Image(HelloApplication.class.getResource("/images/Archer/Particles/Ultimate_Particle_Left.png").toString());

            }
            else
            {
                particle=new Image(HelloApplication.class.getResource("/images/Archer/Particles/Ultimate_Particle_Right.png").toString());

            }
            owner.particleList.add(new ArchorUltimateParticle(particle, x, y, width*0.9, height*0.9, gameScene,owner,direction));
        }
        if(particleCreateTimer>0)
        {
            particleCreateTimer+=1;
        }
        if(direction==false)
        {
            x=x-speed;
            speed+=a;
        }
        else
        {
            x=x+speed;
            speed+=a;
        }
    }
    public void impactNormal(Sprite sprite)
    {
        if ((sprite != null && !sprite.equals(this) && getContour().intersects(sprite.getContour()) && !(sprite instanceof Block)) || x < -50 || x > 1300)

        {
            explode();
        }

    }
    public void explode()
    {

        if(x>-50 && x<1300)
        {
            owner.playHitSoundb7Archer();
            for(int i=0;i<30;i++)
            {
                owner.particleList.add(new ArcherExplosionParticle(new Image(HelloApplication.class.getResource("/images/Archer/Particles/ArcherUltimateParticle.png").toString()), x+0.3*width, y+0.3*width, width*0.2, width*0.2, gameScene,owner));

            }
            owner.normalBulletExplodeList.add(new Ultimate_Bullet_Explosion(new Image(HelloApplication.class.getResource("/images/Archer/Particles/Archer_Ultimate_Explode1.png").toString()),x-width*0.6,y-width*0.6,width*2,width*2,gameScene,owner));

        }


        owner.normalBulletList.remove(this);

    }
}

