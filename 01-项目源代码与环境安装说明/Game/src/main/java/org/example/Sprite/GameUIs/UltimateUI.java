package org.example.Sprite.GameUIs;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Person;
import org.example.gametest.HelloApplication;

public class UltimateUI extends SampleUI{
    Person owner;
    Image D1=new Image(HelloApplication.class.getResource("/images/GameUIs/AbilityUI/1.png").toString());
    Image D2=new Image(HelloApplication.class.getResource("/images/GameUIs/AbilityUI/2.png").toString());
    Image D3=new Image(HelloApplication.class.getResource("/images/GameUIs/AbilityUI/3.png").toString());
    Image D4=new Image(HelloApplication.class.getResource("/images/GameUIs/AbilityUI/4.png").toString());
    Image D5=new Image(HelloApplication.class.getResource("/images/GameUIs/AbilityUI/5.png").toString());
    Image D6=new Image(HelloApplication.class.getResource("/images/GameUIs/AbilityUI/6.png").toString());
    Image D7=new Image(HelloApplication.class.getResource("/images/GameUIs/AbilityUI/7.png").toString());
    Image D8=new Image(HelloApplication.class.getResource("/images/GameUIs/AbilityUI/8.png").toString());
    Image D9=new Image(HelloApplication.class.getResource("/images/GameUIs/AbilityUI/9.png").toString());
    Image D10=new Image(HelloApplication.class.getResource("/images/GameUIs/AbilityUI/10.png").toString());
    Image D11=new Image(HelloApplication.class.getResource("/images/GameUIs/AbilityUI/11.png").toString());
    Image D12=new Image(HelloApplication.class.getResource("/images/GameUIs/AbilityUI/12.png").toString());
    Image notDone=new Image(HelloApplication.class.getResource("/images/GameUIs/AbilityUI/UltimateNotDone.png").toString());

    public UltimateUI(Image image, double x, double y, double width, double height, GameScene gameScene, Person owner)
    {
        super(image, x, y, width, height, gameScene);
        this.owner=owner;
    }

    public void paint(GraphicsContext graphicsContext)
    {
        if(owner.legendID==0)
        {
            if(owner.ultimateTimer<=1200)
            {
                image=notDone;
            }
            else {
                switch (owner.abilityTimer%121)
                {
                    case 10:
                        image=D1;
                        break;
                    case 20:
                        image=D2;
                        break;
                    case 30:
                        image=D3;
                        break;
                    case 40:
                        image=D4;
                        break;
                    case 50:
                        image=D5;
                        break;
                    case 60:
                        image=D6;
                        break;
                    case 70:
                        image=D7;
                        break;
                    case 80:
                        image=D8;
                        break;
                    case 90:
                        image=D9;
                        break;
                    case 100:
                        image=D10;
                        break;
                    case 110:
                        image=D11;
                        break;
                    case 120:
                        image=D12;
                        break;
                }
            }

        }
        if(owner.legendID==1)
        {
            if(owner.ultimateTimer<=1500)
            {
                image=notDone;
            }
            else {
                switch (owner.abilityTimer%121)
                {
                    case 10:
                        image=D1;
                        break;
                    case 20:
                        image=D2;
                        break;
                    case 30:
                        image=D3;
                        break;
                    case 40:
                        image=D4;
                        break;
                    case 50:
                        image=D5;
                        break;
                    case 60:
                        image=D6;
                        break;
                    case 70:
                        image=D7;
                        break;
                    case 80:
                        image=D8;
                        break;
                    case 90:
                        image=D9;
                        break;
                    case 100:
                        image=D10;
                        break;
                    case 110:
                        image=D11;
                        break;
                    case 120:
                        image=D12;
                        break;
                }
            }

        }
        if(owner.legendID==2)
        {
            if(owner.ultimateTimer<=2000)
            {
                image=notDone;
            }
            else {
                switch (owner.abilityTimer%121)
                {
                    case 10:
                        image=D1;
                        break;
                    case 20:
                        image=D2;
                        break;
                    case 30:
                        image=D3;
                        break;
                    case 40:
                        image=D4;
                        break;
                    case 50:
                        image=D5;
                        break;
                    case 60:
                        image=D6;
                        break;
                    case 70:
                        image=D7;
                        break;
                    case 80:
                        image=D8;
                        break;
                    case 90:
                        image=D9;
                        break;
                    case 100:
                        image=D10;
                        break;
                    case 110:
                        image=D11;
                        break;
                    case 120:
                        image=D12;
                        break;
                }
            }

        }
        if(owner.legendID==3)
        {
            if(owner.ultimateTimer<=3000)
            {
                image=notDone;
            }
            else {
                switch (owner.abilityTimer%121)
                {
                    case 10:
                        image=D1;
                        break;
                    case 20:
                        image=D2;
                        break;
                    case 30:
                        image=D3;
                        break;
                    case 40:
                        image=D4;
                        break;
                    case 50:
                        image=D5;
                        break;
                    case 60:
                        image=D6;
                        break;
                    case 70:
                        image=D7;
                        break;
                    case 80:
                        image=D8;
                        break;
                    case 90:
                        image=D9;
                        break;
                    case 100:
                        image=D10;
                        break;
                    case 110:
                        image=D11;
                        break;
                    case 120:
                        image=D12;
                        break;
                }
            }

        }

        super.paint(graphicsContext);
    }
}
