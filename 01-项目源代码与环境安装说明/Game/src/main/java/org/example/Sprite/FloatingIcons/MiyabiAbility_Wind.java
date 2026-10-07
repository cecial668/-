package org.example.Sprite.FloatingIcons;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Person;
import org.example.gametest.HelloApplication;

public class MiyabiAbility_Wind extends FloatingIcon {
    public Person owner;
    private int Timer = 0;//内置计时器
    Image wind1,wind2,wind3,wind4;
    boolean facing=false;
    //public ArrayList<Image> NormalAttackIconsR = new ArrayList<>();

    public MiyabiAbility_Wind(Image image, double x, double y, double width, double height, GameScene gameScene, Person owner, boolean facingDirection) {
        super(image, x, y, width, height, gameScene, owner);
        this.owner = owner;
        facing=facingDirection;
        if(facingDirection==false)
        {
            wind1=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Particles/yan1L.png").toString());
            wind2=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Particles/yan2L.png").toString());
            wind3=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Particles/yan3L.png").toString());
            wind4=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Particles/yan4L.png").toString());
        }
        else
        {
            wind1=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Particles/yan1.png").toString());
            wind2=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Particles/yan2.png").toString());
            wind3=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Particles/yan3.png").toString());
            wind4=new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Particles/yan4.png").toString());
        }

    }

    public void paint(GraphicsContext graphicsContext) {
        if(Timer==0)
        {
            image=wind1;
        }
        if(Timer==10)
        {
            image=wind2;
        }
        if(Timer==20)
        {
            image=wind3;
        }
        if(Timer==30)
        {
            image=wind4;
        }

        if (Timer > 40) {
            owner.playerIcons.remove(this);

        }
        Timer+=1;
        super.paint(graphicsContext);
    }
}
