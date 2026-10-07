package org.example.Sprite.GameUIs;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.gametest.HelloApplication;

public class GameEndIcon extends SampleUI{
    private int Timer=0;//内置计时器
    private double targetx=350;
    private int type=0;


    public GameEndIcon(Image image, double x, double y, double width, double height, GameScene gameScene,int type)
    {
        super(image, x, y, width, height, gameScene);
        if(type==0)
        {
            this.type=type;
            this.image=new Image(HelloApplication.class.getResource("/images/GameUIs/GameEndChi.png").toString());

        }
        else {
            this.image=new Image(HelloApplication.class.getResource("/images/GameUIs/GameEndEng.png").toString());
            this.type=type;

        }


    }


    public void paint(GraphicsContext graphicsContext)
    {
        if(Timer==0)
        {
            if(type==0)
            {
                x=-2000;
            }
            else {
                x=2800;
            }
            y=200;

        }
        if(type==0)
        {
            x+=(targetx-x)*0.03;
            if(x>targetx)
            {
                x=targetx;
            }
        }
        else {
            x-=(x-targetx)*0.03;
            if(x<targetx)
            {
                x=targetx;
            }
        }



        Timer+=1;


        super.paint(graphicsContext);
    }
}
