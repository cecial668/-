package org.example.Sprite.Particle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Person;
import org.example.gametest.HelloApplication;

import java.util.Random;

public class BOSSHitParticle extends SampleParticle{
    public Person owner;
    double angle;
    private boolean Dir;
    private int Timer = 0;
    private double dx, dy; // 速度分量
    private double SPEED; // 粒子的移动速
    private static final Random RANDOM = new Random();
    private double Ay=0;
    int endtime;

    public BOSSHitParticle(Image image, double x, double y, double width, double height, GameScene gameScene, Person owner)
    {
        super(image, x, y, width, height, gameScene, owner);
        int rand=RANDOM.nextInt(2);
        {
            if(rand==0)
            {
                this.image=new Image(HelloApplication.class.getResource("/images/BOSS/BOSSparticle.png").toString());
            }
            else {
                this.image=new Image(HelloApplication.class.getResource("/images/BOSS/BOSSparticle2.png").toString());
            }
        }
        this.owner = owner;
        Dir=owner.facingDirection;

        // 生成一个随机速度值（2 到 6）
        SPEED = 1 + RANDOM.nextDouble() * 8;

        // 生成一个随机角度（0 到 2*PI）
        double angle = RANDOM.nextDouble() * 2 * Math.PI;


        // 根据角度计算 x 和 y 方向上的速度分量
        dx = SPEED * Math.cos(angle);
        dy = SPEED * Math.sin(angle);

    }

    public void paint(GraphicsContext graphicsContext)
    {
        Timer+=1;
        if (y>1000)
        {
            owner.particleList.remove(this);
        }


        // 更新粒子的位置
        x += dx;
        dx *= 0.96;
        y += dy;
        dy+=0.15;


        super.paint(graphicsContext);
    }
}
