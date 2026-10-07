package org.example.Sprite.CecilNormalBullet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Bullet;
import org.example.Sprite.NormalBullet.NormalBulletExplosion;
import org.example.Sprite.Person;
import org.example.Sprite.Sprite;
import org.example.gametest.HelloApplication;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class CecilNormalBullet extends Bullet
{
    private Image particle;
    Clip wind;
    int Timer=0;
    private int particleCreateTimer=0;
    public CecilNormalBullet(Image image, double x, double y, double width, double height, GameScene gameScene, boolean direction, Person owner) {
        super(image, x, y, width, height, gameScene);
        this.direction = direction;//方向，false为向左
        this.owner=owner;//子弹的发射者
        this.KnockbackRatex=2;//水平击退力度
        this.KnockbackRatey=1;//击飞力度
        this.damage=4;//造成伤害
        speed=5;//飞行速度
        System.out.println(direction);

    }


    public void paint(GraphicsContext graphicsContext)
    {
        move();
        super.paint(graphicsContext);
    }
    public void move()
    {
        
        if(particleCreateTimer>0)
        {
            particleCreateTimer+=1;
        }
        if(direction==false)//根据方向移动
        {
            x=x-speed;
        }
        else
        {
            x=x+speed;
        }
        if(Timer>0)
        {
            y=3000;
            x=500;
            Timer+=1;
        }
        if(Timer>200)
        {
            owner.normalBulletList.remove(this);//效果移除
        }
    }

    public void impactNormal(Sprite sprite)//检测与物体的碰撞
    {
        if((sprite != null && !sprite.equals(this) && getContour().intersects(sprite.getContour())) || x<-50 || x>1300)//如果：撞到墙or角色（在gamescene中有调用）or飞出地图
        {
            if(Timer==0)
            {
                if(x<-50 || x>1300)
                {
                    Timer+=1;

                }

                explode();//爆炸


            }

        }

    }
    public void explode()
    {
        if(Timer==0)
        {
            try{
                File audioFile2 = new File(HelloApplication.class.getResource("/Music/Cecil/Hit.wav").getPath());
                AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                AudioFormat format2 = audioStream2.getFormat();
                DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                wind = (Clip) AudioSystem.getLine(info2);
                wind.open(audioStream2);
                FloatControl volumeControl = (FloatControl) wind.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(-3.0f); // 将音量降低10分贝（根据需要调整值）
                wind.setFramePosition(0);  // 重置到开始位置
                wind.start();

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
                // 处理异常，比如音频文件无法加载或播放等
            }

        }

        Timer+=1;

            owner.normalBulletExplodeList.add(new NormalBulletExplosion(new Image(HelloApplication.class.getResource("/images/Cecil/Particles/普攻爆炸1.png").toString()),x-5,y-9,25,25,gameScene,owner));//生成单体爆炸特效类
        y=3000;
        x=500;

    }
}
