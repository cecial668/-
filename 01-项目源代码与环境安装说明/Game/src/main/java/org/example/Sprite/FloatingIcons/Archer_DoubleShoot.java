package org.example.Sprite.FloatingIcons;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Person;

public class Archer_DoubleShoot extends FloatingIcon{
    public Person owner;
    private int Timer=0;
    public Archer_DoubleShoot(Image image, double x, double y, double width, double height, GameScene gameScene, Person owner)
    {
        super(image, x, y, width, height, gameScene,owner);
        this.owner=owner;
    }
    public void paint(GraphicsContext graphicsContext)
    {
        if(Timer>=600)
        {
            owner.playerIcons.remove(this);
        }
        Timer+=1;
        x=owner.x-owner.width*0.2;
        y=owner.y-width+3;
        super.paint(graphicsContext);
    }

}
