package org.example.Sprite.GameUIs;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Person;
import org.example.gametest.HelloApplication;

public class AbilityChargingUI extends SampleUI{
    Person owner;
    Image notDone=new Image(HelloApplication.class.getResource("/images/GameUIs/AbilityUI/AbilityNotDoneBoard.png").toString());

    public AbilityChargingUI(Image image, double x, double y, double width, double height, GameScene gameScene, Person owner)
    {
        super(image, x, y, width, height, gameScene);
        this.owner=owner;
        this.image=notDone;
    }

    public void paint(GraphicsContext graphicsContext)
    {
        if(owner.legendID==0)
        {
            if(owner.abilityTimer<=1000)
            {
                image=notDone;
                height=((double)owner.abilityTimer/1000)*30;
            }
            else {
                image=null;
            }

        }
        if(owner.legendID==1)
        {
            if(owner.abilityTimer<=600)
            {
                image=notDone;
                height=((double)owner.abilityTimer/600)*30;
            }
            else {
                image=null;
            }

        }
        if(owner.legendID==2)
        {
            if(owner.abilityTimer<=700)
            {
                image=notDone;
                height=((double)owner.abilityTimer/700)*30;
            }
            else {
                image=null;
            }

        }
        if(owner.legendID==3)
        {
            if(owner.abilityTimer<=200)
            {
                image=notDone;
                height=((double)owner.abilityTimer/200)*30;
            }
            else {
                image=null;
            }

        }


        super.paint(graphicsContext);
    }
}
