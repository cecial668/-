package org.example.Sprite.GameUIs;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.gametest.HelloApplication;

public class PlayerLife extends SampleUI
{
    public double currentLives=100;

    private double monitorLives;
    Image Life=null;
    Image noneLife=new Image(HelloApplication.class.getResource("/images/GameUIs/Lifenone.png").toString());
    public PlayerLife(Image image, double x, double y, double width, double height, GameScene gameScene, int monitoringLives)
    {
        super(image, x, y, width, height, gameScene);
        this.monitorLives=monitoringLives;
        Life=image;
    }

    public void paint(GraphicsContext graphicsContext)
    {
        if(currentLives<=monitorLives)
        {
            image=noneLife;
        }
        else {
            image=Life;
        }

        super.paint(graphicsContext);
    }
}
