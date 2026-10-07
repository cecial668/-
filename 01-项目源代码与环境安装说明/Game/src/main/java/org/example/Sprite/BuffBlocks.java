package org.example.Sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.gametest.HelloApplication;

import javax.sound.sampled.*;
import java.util.Random;

public class BuffBlocks extends Sprite{
    private Image particle;
    double a;
    Random random = new Random();
    public int type;
    private Clip buffSound;
    private int particleCreateTimer=1;
    public BuffBlocks(Image image, double x, double y, double width, double height, GameScene gameScene) {
        super(image, x, y, width, height, gameScene);
        //this.group = group;
        //this.dir = dir;


        type= random.nextInt(5);
        if(type==0)
        {
            this.image=new Image(HelloApplication.class.getResource("/images/OtherImages/Buff_EnhancedAttack.png").toString());

        }
        if(type==1)
        {
            this.image=new Image(HelloApplication.class.getResource("/images/OtherImages/Buff_Life+1.png").toString());
        }
        if(type==2)
        {
            this.image=new Image(HelloApplication.class.getResource("/images/OtherImages/Buff_RefreshCD.png").toString());
        }
        if(type==3)
        {
            this.image=new Image(HelloApplication.class.getResource("/images/OtherImages/Buff_Shield.png").toString());
        }
        if(type==4)
        {
            this.image=new Image(HelloApplication.class.getResource("/images/OtherImages/Buff_SpeedUp.png").toString());
        }
    }


    public void paint(GraphicsContext graphicsContext)
    {
        if(gameScene.gameRunning==false)
        {
            gameScene.buffList.remove(this);
        }
        move();
        super.paint(graphicsContext);
    }
    public void move()
    {
    }
    public void impactNormal(Sprite sprite)
    {
        if ((sprite != null && !sprite.equals(this) && getContour().intersects(sprite.getContour()) && !(sprite instanceof Block)) || x < -50 || x > 1300)

        {

            gameScene.buffList.remove(this);
        }

    }
    public void explode()
    {
    }
}
