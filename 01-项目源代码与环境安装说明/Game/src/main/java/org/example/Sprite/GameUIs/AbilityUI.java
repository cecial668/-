package org.example.Sprite.GameUIs;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Person;
import org.example.gametest.HelloApplication;

public class AbilityUI extends SampleUI{
    Person owner;
    Image unDone=new Image(HelloApplication.class.getResource("/images/GameUIs/AbilityUI/AbilityNotDone.png").toString());
    Image Done=new Image(HelloApplication.class.getResource("/images/GameUIs/AbilityUI/AbilityDone.png").toString());

    public AbilityUI(Image image, double x, double y, double width, double height, GameScene gameScene,Person owner)
    {
        super(image, x, y, width, height, gameScene);
        this.owner=owner;
    }

    public void paint(GraphicsContext graphicsContext)
    {
        if(owner.legendID==0)
        {
            if(owner.abilityTimer<=1000)
            {
                image=unDone;
            }
            else {
                image=Done;
            }

        }
        if(owner.legendID==1)
        {
            if(owner.abilityTimer<=600)
            {
                image=unDone;
            }
            else {
                image=Done;
            }

        }
        if(owner.legendID==2)
        {
            if(owner.abilityTimer<=700)
            {
                image=unDone;
            }
            else {
                image=Done;
            }

        }
        if(owner.legendID==3)
        {
            if(owner.abilityTimer<=200)
            {
                image=unDone;
            }
            else {
                image=Done;
            }

        }


        super.paint(graphicsContext);
    }
}
