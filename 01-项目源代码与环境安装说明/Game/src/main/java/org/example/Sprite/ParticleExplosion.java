package org.example.Sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;

public abstract class ParticleExplosion extends Sprite
{
    public Person owner=null;

    public ParticleExplosion(Image image, double x, double y, double width, double height, GameScene gameScene) {
        super(image, x, y, width, height, gameScene);
        //this.group = group;
        //this.dir = dir;
    }
    public abstract void startAnimationTimer();


}