package org.example.Sprite.GameUIs;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.gametest.HelloApplication;

public class HPBlock extends SampleUI
{
    public double currentHP=100;
    private double Yspeed=0;
    private double monitorHP;
    Image damagedHP=new Image(HelloApplication.class.getResource("/images/GameUIs/BloodBlockYellow.png").toString());
    public HPBlock(Image image, double x, double y, double width, double height, GameScene gameScene,double monitoringHP)
    {
        super(image, x, y, width, height, gameScene);
        this.monitorHP=monitoringHP;
    }

    public void paint(GraphicsContext graphicsContext)
    {
        if(currentHP<=monitorHP)
        {
            image=damagedHP;
            Yspeed+=0.18;


        }
        y=y+Yspeed;
        super.paint(graphicsContext);
    }

}
