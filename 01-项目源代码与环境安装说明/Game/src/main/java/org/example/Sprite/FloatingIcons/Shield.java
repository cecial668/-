package org.example.Sprite.FloatingIcons;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Person;

public class Shield extends FloatingIcon{
    public Person owner;
    private int Timer=0;//内置计时器
    private int ShieldTime=0;
    public Shield(Image image, double x, double y, double width, double height, GameScene gameScene, Person owner)
    {
        super(image, x, y, width, height, gameScene,owner);
        this.owner=owner;
        this.ShieldTime=owner.shieldTimer;
    }
    public void paint(GraphicsContext graphicsContext)
    {
        if(Timer>=ShieldTime)//寿命到期，吩咐主人删除自己
        {
            owner.playerIcons.remove(this);
        }
        Timer+=1;
        x=owner.x-owner.width*0.05;//始终显示在图标主人的头顶上
        y=owner.y;
        super.paint(graphicsContext);
    }
}
