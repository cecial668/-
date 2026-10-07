package org.example.Sprite.FloatingIcons;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Person;

public class PlayerNumberIcon extends FloatingIcon{
    public Person owner;
    public PlayerNumberIcon(Image image, double x, double y, double width, double height, GameScene gameScene, Person owner)
    {
        super(image, x, y, width, height, gameScene,owner);
        this.owner=owner;
    }
    public void paint(GraphicsContext graphicsContext)
    {
        x=owner.x-owner.width*0;//始终显示在图标主人的头顶上
        y=owner.y-width-10;
        super.paint(graphicsContext);
    }
}
