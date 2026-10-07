package org.example.Sprite.Particle;

import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Person;
import org.example.Sprite.Sprite;

public abstract class SampleParticle extends Sprite
{

    public SampleParticle(Image image, double x, double y, double width, double height, GameScene gameScene, Person owner)
    {
        super(image, x, y, width, height, gameScene);
    }
}