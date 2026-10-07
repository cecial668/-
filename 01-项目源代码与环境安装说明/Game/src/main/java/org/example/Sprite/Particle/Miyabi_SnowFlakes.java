package org.example.Sprite.Particle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Person;
import org.example.gametest.HelloApplication;

import java.util.Random;

public class Miyabi_SnowFlakes extends SampleParticle{
    public Person owner;
    double angle;
    private boolean Dir;
    private int Timer = 0;
    private double dx, dy; // 速度分量
    private double SPEED; // 粒子的移动速度
    private static final Random RANDOM = new Random();
    int endtime;

    public Miyabi_SnowFlakes(Image image, double x, double y, double width, double height, GameScene gameScene, Person owner)
    {
        super(image, x, y, width, height, gameScene, owner);
        int rand=RANDOM.nextInt(2);
        {
            if(rand==0)
            {
                this.image=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Particles/snowFlake1.png").toString());
            }
            else {
                this.image=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Particles/snowFlake2.png").toString());
            }
        }
        this.owner = owner;
        Dir=owner.facingDirection;

        // 生成一个随机速度值（2 到 6）
        SPEED = 1 + RANDOM.nextDouble() * 6;

        // 生成一个随机角度（0 到 2*PI）
        double angle = RANDOM.nextDouble() * 2 * Math.PI;


        // 根据角度计算 x 和 y 方向上的速度分量
        dx = SPEED * Math.cos(angle);
        dy = SPEED * Math.sin(angle);
    }

    public void paint(GraphicsContext graphicsContext)
    {
        if (dx*dx+dy*dy<0.2)
        {
            owner.particleList.remove(this);
            return;
        }
        Timer += 1;

        // 更新粒子的位置
        x += dx;
        dx *= 0.96;
        y += dy;
        dy *= 0.96;

        super.paint(graphicsContext);
    }
}
