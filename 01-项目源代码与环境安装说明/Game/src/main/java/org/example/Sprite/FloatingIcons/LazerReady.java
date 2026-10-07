package org.example.Sprite.FloatingIcons;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Person;

public class LazerReady extends FloatingIcon{
    public Person owner;
    private int PositionIndex;
    public LazerReady(Image image, double x, double y, double width, double height, GameScene gameScene, Person owner, int PositionIndex)
    {
        super(image, x, y, width, height, gameScene,owner);
        this.owner=owner;
        this.PositionIndex=PositionIndex;
    }
    public void paint(GraphicsContext graphicsContext)
    {
        if(owner.facingDirection==false)
        {
            x=owner.x-width*0.9-PositionIndex;//始终显示在图标主人的头顶上
            y=owner.y-owner.height*0.1;
        }
        else {
            x=owner.x+owner.width+PositionIndex;//始终显示在图标主人的头顶上
            y=owner.y-owner.height*0.1;
        }
        if(owner.lazerReady==false)
        {
            owner.playerIcons.remove(this);
        }

        super.paint(graphicsContext);
    }
}
