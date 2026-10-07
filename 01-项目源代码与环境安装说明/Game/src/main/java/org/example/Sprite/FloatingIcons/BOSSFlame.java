package org.example.Sprite.FloatingIcons;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Person;
import org.example.gametest.HelloApplication;

public class BOSSFlame extends FloatingIcon{
    public Person owner;
    int timer=0;
    Image f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11;
    public BOSSFlame(Image image, double x, double y, double width, double height, GameScene gameScene, Person owner)
    {
        super(image, x, y, width, height, gameScene,owner);
        this.owner=owner;
        f1=new Image(HelloApplication.class.getResource("/images/BOSS/1.png").toString());
        f2=new Image(HelloApplication.class.getResource("/images/BOSS/2.png").toString());
        f3=new Image(HelloApplication.class.getResource("/images/BOSS/3.png").toString());
        f4=new Image(HelloApplication.class.getResource("/images/BOSS/4.png").toString());
        f5=new Image(HelloApplication.class.getResource("/images/BOSS/5.png").toString());
        f6=new Image(HelloApplication.class.getResource("/images/BOSS/6.png").toString());
        f7=new Image(HelloApplication.class.getResource("/images/BOSS/7.png").toString());
        f8=new Image(HelloApplication.class.getResource("/images/BOSS/8.png").toString());
        f9=new Image(HelloApplication.class.getResource("/images/BOSS/9.png").toString());
        f10=new Image(HelloApplication.class.getResource("/images/BOSS/10.png").toString());
        f11=new Image(HelloApplication.class.getResource("/images/BOSS/11.png").toString());
    }
    public void paint(GraphicsContext graphicsContext)
    {
        timer+=1;
        if(timer%99==0)
        {
            image=f1;
        }
        if(timer%99==9)
        {
            image=f2;
        }
        if(timer%99==18)
        {
            image=f3;
        }
        if(timer%99==27)
        {
            image=f4;
        }
        if(timer%99==36)
        {
            image=f5;
        }
        if(timer%99==45)
        {
            image=f6;
        }
        if(timer%99==54)
        {
            image=f7;
        }
        if(timer%99==63)
        {
            image=f8;
        }
        if(timer%99==72)
        {
            image=f9;
        }
        if(timer%99==81)
        {
            image=f10;
        }
        if(timer%99==90)
        {
            image=f11;
        }
        x=owner.x-110;//始终显示在图标主人的头顶上
        y=owner.y-150;
        super.paint(graphicsContext);
    }
}
