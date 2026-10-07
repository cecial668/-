package org.example.Sprite.NormalBullet;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Person;
import org.example.Sprite.ParticleExplosion;
import org.example.gametest.HelloApplication;

public class NormalBulletExplosion extends ParticleExplosion {
    private Person owner;
    private long startTime;
    private static final long DURATION_MS = 1000; // 1 second in milliseconds
    private boolean isActive = true; // 标记该类是否仍然活跃
    private AnimationTimer timer;
    private boolean timerEnd=false;
    Image e2=new Image(HelloApplication.class.getResource("/images/Archer/Particles/e2.png").toString());
    Image e3=new Image(HelloApplication.class.getResource("/images/Archer/Particles/e3.png").toString());
    Image e4=new Image(HelloApplication.class.getResource("/images/Archer/Particles/e4.png").toString());


    public NormalBulletExplosion(Image image, double x, double y, double width, double height, GameScene gameScene, Person owner) {
        super(image, x, y, width, height, gameScene);
        this.owner = owner;
        // 初始化计时器
        startTime = System.nanoTime();
        // 开始动画计时器
        startAnimationTimer();
    }

    public void startAnimationTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (isActive) {
                    long elapsed = now - startTime;
                    if (elapsed >= DURATION_MS * 50_000) {
                        image=e2;//不同时间点呈现不同贴图
                    }
                    if (elapsed >= DURATION_MS * 100_000) {

                        image=e3;
                    }
                    if (elapsed >= DURATION_MS * 150_000) {

                        image=e4;
                    }
                    if (elapsed >= DURATION_MS * 200_000) { // 转换成纳秒
                        // 时间到了，停止计时器并标记为不活跃
                        timerEnd = true;
                        this.stop();
                        isActive = false;


                    }

                }
            }
        };
        // 启动计时器
        timer.start();
    }

    @Override
    public void paint(GraphicsContext graphicsContext)
    {
        if(timerEnd==true)
        {
            owner.normalBulletExplodeList.remove(this);
        }
        super.paint(graphicsContext);
    }

}