package org.example.Sprite.Legends.Bot;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.ParticleExplosion;
import org.example.Sprite.Person;
import org.example.gametest.HelloApplication;

public class BotStarExplosion extends ParticleExplosion {
    private Person owner;
    private long startTime;
    private static final long DURATION_MS = 1000; // 1 second in milliseconds
    private boolean isActive = true; // 标记该类是否仍然活跃
    private AnimationTimer timer;
    private boolean timerEnd=false;
    Image e1=new Image(HelloApplication.class.getResource("/images/BOSS/E/1.png").toString());
    Image e2=new Image(HelloApplication.class.getResource("/images/BOSS/E/2.png").toString());
    Image e3=new Image(HelloApplication.class.getResource("/images/BOSS/E/3.png").toString());
    Image e4=new Image(HelloApplication.class.getResource("/images/BOSS/E/4.png").toString());
    Image e5=new Image(HelloApplication.class.getResource("/images/BOSS/E/5.png").toString());
    Image e6=new Image(HelloApplication.class.getResource("/images/BOSS/E/6.png").toString());
    Image e7=new Image(HelloApplication.class.getResource("/images/BOSS/E/7.png").toString());
    Image e8=new Image(HelloApplication.class.getResource("/images/BOSS/E/8.png").toString());


    public BotStarExplosion(Image image, double x, double y, double width, double height, GameScene gameScene, Person owner) {
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
                    if (elapsed<DURATION_MS*50000) {
                        image=e1;
                    }
                    else if (elapsed <= DURATION_MS * 100_000) {
                        image=e2;//不同时间点呈现不同贴图
                    }
                    else if (elapsed <= DURATION_MS * 150_000) {

                        image=e3;
                    }
                    else if (elapsed <= DURATION_MS * 200_000) {

                        image=e4;
                    } else if (elapsed <= DURATION_MS * 250_000) {

                        image=e5;
                    } else if (elapsed <= DURATION_MS * 300_000) {

                        image=e6;
                    } else if (elapsed <= DURATION_MS * 350_000) {

                        image=e7;
                    } else if (elapsed <= DURATION_MS * 400_000) {

                        image=e8;
                    }
                    else  {
                        // 转换成纳秒
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
