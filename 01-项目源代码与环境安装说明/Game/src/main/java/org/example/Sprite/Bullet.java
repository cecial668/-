package org.example.Sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;

public abstract class Bullet extends DamageEntity
{
    public double speed;
    public Person owner=null;
    public boolean direction;
    public double KnockbackRatex;
    public double KnockbackRatey;
    public double damage;

    public Bullet(Image image, double x, double y, double width, double height, GameScene gameScene) {
        super(image, x, y, width, height, gameScene);
        //this.group = group;
        //this.dir = dir;
    }
    public abstract void move();
    public abstract void impactNormal(Sprite sprite);
    public abstract void explode();


}