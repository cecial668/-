package org.example.Sprite.GameUIs;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Sprite;

public class SampleUI extends Sprite{


    public SampleUI(Image image, double x, double y, double width, double height, GameScene gameScene) {
        super(image, x, y, width, height, gameScene);
        //this.group = group;
        //this.dir = dir;
    }
    public void paint(GraphicsContext graphicsContext)
    {
        super.paint(graphicsContext);
    }

}
