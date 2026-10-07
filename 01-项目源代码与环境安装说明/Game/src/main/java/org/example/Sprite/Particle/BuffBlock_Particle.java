package org.example.Sprite.Particle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Person;

import java.util.Random;

public class BuffBlock_Particle extends SampleParticle{
    public Person owner;
    private int Timer=0;
    private double dx, dy; // 速度分量
    private static final double SPEED =0.4; // 粒子的移动速度
    private static final Random RANDOM = new Random();
    public BuffBlock_Particle(Image image, double x, double y, double width, double height, GameScene gameScene, Person owner)
    {
        super(image, x, y, width, height, gameScene,owner);
        this.owner=owner;

        // 生成一个随机角度（0 到 2*PI）
        double angle = RANDOM.nextDouble() * 2 * Math.PI;

        // 根据角度计算 x 和 y 方向上的速度分量
        dx = SPEED * Math.cos(angle);
        dy = SPEED * Math.sin(angle);
    }
    public void paint(GraphicsContext graphicsContext)
    {
        if (Timer >= 100)
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
