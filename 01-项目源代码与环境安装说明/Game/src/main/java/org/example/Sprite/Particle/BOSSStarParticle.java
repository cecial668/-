package org.example.Sprite.Particle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Person;
import org.example.gametest.HelloApplication;

import java.util.Random;

public class BOSSStarParticle extends SampleParticle{
    public Person owner;
    private int Timer=0;
    private int lifeTime;
    private double dx, dy; // 速度分量
    private static final double SPEED =0.2; // 粒子的移动速度
    private static final Random RANDOM = new Random();
    public BOSSStarParticle(Image image, double x, double y, double width, double height, GameScene gameScene, Person owner,boolean type)
    {
        super(image, x, y, width, height, gameScene,owner);
        this.owner=owner;
        if(type==false)
        {
            int rnd=RANDOM.nextInt(4);

            if(rnd==0)
            {
                this.image=new Image(HelloApplication.class.getResource("/images/BOSS/Smoke1.png").toString());
            }
            if(rnd==1)
            {
                this.image=new Image(HelloApplication.class.getResource("/images/BOSS/Smoke2.png").toString());
            }
            if(rnd==2)
            {
                this.image=new Image(HelloApplication.class.getResource("/images/BOSS/Smoke3.png").toString());
            }
            if(rnd==3)
            {
                this.image=new Image(HelloApplication.class.getResource("/images/BOSS/Smoke4.png").toString());
            }
            lifeTime=70+RANDOM.nextInt(30);

        }
        else
        {
            int rnd=RANDOM.nextInt(3);
            if(rnd==0)
            {
                this.image=new Image(HelloApplication.class.getResource("/images/BOSS/flame1.png").toString());
            }
            if(rnd==1)
            {
                this.image=new Image(HelloApplication.class.getResource("/images/BOSS/flame2.png").toString());
            }
            if(rnd==2)
            {
                this.image=new Image(HelloApplication.class.getResource("/images/BOSS/flame3.png").toString());
            }
            lifeTime=20+RANDOM.nextInt(20);

        }

        // 生成一个随机角度（0 到 2*PI）
        double angle = RANDOM.nextDouble() * 2 * Math.PI;

        // 根据角度计算 x 和 y 方向上的速度分量
        dx = SPEED * Math.cos(angle);
        dy = SPEED * Math.sin(angle);
    }
    public void paint(GraphicsContext graphicsContext)
    {
        if (Timer >= lifeTime)
        {
            owner.particleList.remove(this);
            return;
        }
        Timer += 1;

        // 更新粒子的位置
        x += dx;
        y += dy;

        super.paint(graphicsContext);
    }
}
