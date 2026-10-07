package org.example.Sprite;

import javafx.scene.image.Image;
import org.example.Scenes.GameScene;

public class Block extends Sprite
{
    public Block(Image image, double x, double y, double width, double height, GameScene gameScene) {
        super(image, x, y, width, height, gameScene);
        //this.group = group;
        //this.dir = dir;
    }
}
