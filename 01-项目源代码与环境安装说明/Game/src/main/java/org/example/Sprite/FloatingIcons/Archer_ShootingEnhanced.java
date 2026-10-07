package org.example.Sprite.FloatingIcons;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Person;

public class Archer_ShootingEnhanced extends FloatingIcon{
    public Person owner;
    private int Timer=0;//内置计时器
    public Archer_ShootingEnhanced(Image image, double x, double y, double width, double height, GameScene gameScene,Person owner)
    {
        super(image, x, y, width, height, gameScene,owner);
        this.owner=owner;
    }
    public void paint(GraphicsContext graphicsContext)
    {
        if(Timer>=450)//寿命到期，吩咐主人删除自己
        {
            owner.playerIcons.remove(this);
        }
        Timer+=1;
        x=owner.x+owner.width*0.4;//始终显示在图标主人的头顶上
        y=owner.y-width+3;
        super.paint(graphicsContext);
    }
}
