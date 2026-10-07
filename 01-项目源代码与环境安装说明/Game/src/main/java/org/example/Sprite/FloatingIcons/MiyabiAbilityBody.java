package org.example.Sprite.FloatingIcons;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Person;
import org.example.gametest.HelloApplication;

public class MiyabiAbilityBody extends FloatingIcon{
    public Person owner;
    private int Timer = 0;//内置计时器
    Image b1,b2,b3,b4,b5,b6,b7;
    boolean facing=false;
    double speed=0;

    //public ArrayList<Image> NormalAttackIconsR = new ArrayList<>();

    public MiyabiAbilityBody(Image image, double x, double y, double width, double height, GameScene gameScene, Person owner, boolean facingDirection) {
        super(image, x, y, width, height, gameScene, owner);
        this.owner = owner;
        facing=facingDirection;
        if(facingDirection==false)
        {
            b1=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/A1L.png").toString());
            b2=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/A2L.png").toString());
            b3=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/A3L.png").toString());
            b4=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/A4L.png").toString());
            b5=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/A5L.png").toString());
            b6=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/A6L.png").toString());
            b7=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/A7L.png").toString());

        }
        else
        {
            b1=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/A1.png").toString());
            b2=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/A2.png").toString());
            b3=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/A3.png").toString());
            b4=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/A4.png").toString());
            b5=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/A5.png").toString());
            b6=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/A6.png").toString());
            b7=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/A7.png").toString());
        }


    }

    public void paint(GraphicsContext graphicsContext) {
        if(Timer==0)
        {
            image=b1;
        }
        if(Timer==7)
        {
            image=b2;
        }
        if(Timer==14)
        {
            image=b3;
        }
        if(Timer==21)
        {
            image=b4;
        }
        if(Timer==28)
        {
            image=b5;
        }
        if(Timer==35)
        {
            image=b6;
        }
        if(Timer==42)
        {
            image=b7;
        }
        if(Timer%7==0)
        {
            if(facing==false)
            {
                x-=speed;
            }
            else {
                x+=speed;
            }

        }
        speed+=2;

        if (Timer > 42) {
            owner.playerIcons.remove(this);

        }
        Timer+=1;
        System.out.println("sdasdsad");
        super.paint(graphicsContext);
    }
}
