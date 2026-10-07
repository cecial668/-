package org.example.Sprite.CecilSkill2Bullet;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Block;
import org.example.Sprite.Bullet;
import org.example.Sprite.Particle.ArcherExplosionParticle;
import org.example.Sprite.Particle.EnhancedArrowParticle;
import org.example.Sprite.Person;
import org.example.Sprite.Sprite;
import org.example.gametest.HelloApplication;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;


public class Ceceil_ShootingStar extends Bullet
{
    public Person owner;
    private int Timer=0;
    private double dx, dy,ax,ay; // 速度分量
    private static final double SPEED =0.045; // 粒子的移动速度
    private static final Random RANDOM = new Random();
    Clip ShootingStr;
    boolean inScreen=false;
    int killTimer=0;

    private Image particle;
    double a;
    Image S1,S2,S3;
    private int particleCreateTimer=1;
    public Ceceil_ShootingStar(Image image, double x, double y, double width, double height, GameScene gameScene, boolean direction, Person owner) {
        super(image, x, y, width, height, gameScene);
        this.direction = direction;
        this.owner=owner;
        this.KnockbackRatex=3.5;
        this.KnockbackRatey=3;
        this.damage=7;
        speed=0;
        System.out.println(direction);
        double angle = ((RANDOM.nextDouble() * 0.25)+0.375) * Math.PI;

        // 根据角度计算 x 和 y 方向上的速度分量
        ax = SPEED * Math.cos(angle);
        ay = SPEED * Math.sin(angle);
        S1=new Image(HelloApplication.class.getResource("/images/Cecil/Bullets/S1.png").toString());
        S2=new Image(HelloApplication.class.getResource("/images/Cecil/Bullets/S2.png").toString());
        S3=new Image(HelloApplication.class.getResource("/images/Cecil/Bullets/S3.png").toString());
        this.image=S1;





        //this.group = group;
        //this.dir = dir;
    }


    public void paint(GraphicsContext graphicsContext)
    {
        Timer+=1;
        if(Timer%18==0)
        {
            image=S1;
        }
        if(Timer%18==6)
        {
            image=S2;
        }
        if(Timer%18==12)
        {
            image=S3;
        }

        move();
        super.paint(graphicsContext);
    }
    public void move()
    {
        if(particleCreateTimer>=4)
        {
            particleCreateTimer=1;
            particle=new Image(HelloApplication.class.getResource("/images/Cecil/Particles/ShootingStarParticle.png").toString());
            owner.particleList.add(new EnhancedArrowParticle(particle, x, y, 5, 5, gameScene,owner));
        }
        if(particleCreateTimer>0)
        {
            particleCreateTimer+=1;
        }
        x=x+dx;
        y=y+dy;
        dx+=ax;
        dy+=ay;


        if(y>0&&inScreen==false)
        {
            inScreen=true;
            try{
                File audioFile2 = new File(HelloApplication.class.getResource("/Music/Cecil/ShootingStrs.wav").getPath());
                AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                AudioFormat format2 = audioStream2.getFormat();
                DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                ShootingStr = (Clip) AudioSystem.getLine(info2);
                ShootingStr.open(audioStream2);
                FloatControl volumeControl = (FloatControl) ShootingStr.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(-7.0f); // 将音量降低10分贝（根据需要调整值）

                ShootingStr.setFramePosition(0);  // 重置到开始位置
                ShootingStr.start();



            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
                // 处理异常，比如音频文件无法加载或播放等
            }
        }


    }
    public void impactNormal(Sprite sprite)
    {
        if ((sprite != null && !sprite.equals(this) && getContour().intersects(sprite.getContour()) && !(sprite instanceof Block)) || y>1000)

        {
            if(killTimer==0)
            {
                explode();

            }

        }

    }
    public void explode()
    {
        if(y<1000)
        {
            for(int i=0;i<50;i++)//打击后爆发粒子效果
            {
                owner.particleList.add(new ArcherExplosionParticle(new Image(HelloApplication.class.getResource("/images/Archer/Particles/ArcherUltimateParticle.png").toString()), x+0.3*width, y+0.3*width, 20, 20, gameScene,owner));

            }
            owner.playExplodeSound();
        }
        owner.normalBulletList.remove(this);
        //owner.normalBulletExplodeList.add(new Ultimate_Bullet_Explosion(new Image(HelloApplication.class.getResource("/images/Archer/Particles/Archer_Ultimate_Explode1.png").toString()),x-width*0.6,y-width*0.6,width*2,width*2,gameScene,owner));


    }
}

