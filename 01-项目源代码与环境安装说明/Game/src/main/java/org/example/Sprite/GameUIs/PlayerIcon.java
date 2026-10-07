package org.example.Sprite.GameUIs;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;

public class PlayerIcon extends SampleUI{
    int type;
    public PlayerIcon(Image image, double x, double y, double width, double height, GameScene gameScene, int type)
    {
        super(image, x, y, width, height, gameScene);
        this.type=type;
    }
    public void paint(GraphicsContext graphicsContext)
    {
        if(type==0)
        {
            x=35;
            y=18;
        }
        else {
            y=18;
            x=1220;
        }
        super.paint(graphicsContext);
    }


}
