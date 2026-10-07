package org.example.Sprite.FloatingIcons;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Person;
import org.example.gametest.HelloApplication;

import java.util.ArrayList;

public class Miyabi_AbilityAttack extends FloatingIcon{
    public Person owner;
    private int Timer = 0;//内置计时器
    Image AAttackIcon_1L,AAttackIcon_2L,AAttackIcon_3L,AAttackIcon_4L,AAttackIcon_5L,AAttackIcon_6L;

    public ArrayList<Image> NormalAttackIcons = new ArrayList<>();
    boolean facing=false;
    //public ArrayList<Image> NormalAttackIconsR = new ArrayList<>();

    public Miyabi_AbilityAttack(Image image, double x, double y, double width, double height, GameScene gameScene, Person owner) {
        super(image, x, y, width, height, gameScene, owner);
        this.owner = owner;
        facing=owner.facingDirection;
        if(facing==false) {
            AAttackIcon_1L = new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/AbilityL1.png").toString());
            AAttackIcon_2L = new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/AbilityL2.png").toString());
            AAttackIcon_3L = new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/AbilityL3.png").toString());
            AAttackIcon_4L = new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/AbilityL4.png").toString());
            AAttackIcon_5L = new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/AbilityL5.png").toString());
            AAttackIcon_6L = new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/AbilityL0.png").toString());
        }else {
            AAttackIcon_1L = new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/AbilityR1.png").toString());
            AAttackIcon_2L = new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/AbilityR2.png").toString());
            AAttackIcon_3L = new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/AbilityR3.png").toString());
            AAttackIcon_4L = new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/AbilityR4.png").toString());
            AAttackIcon_5L = new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/AbilityR5.png").toString());
            AAttackIcon_6L = new Image(HelloApplication.class.getResource("/images/Hoshimi_Miyabi/Icons/AbilityR0.png").toString());
        }
    }

    public void paint(GraphicsContext graphicsContext) {
        if (facing==false) {
            x = owner.x-130;
        } else {
            x =owner.x-130;
        }
        y=owner.y-60;
        if (Timer<8){
            image=AAttackIcon_6L;
        } else if (Timer <= 16) {
            image = AAttackIcon_5L;
        } else if (Timer <= 24) {
            image = AAttackIcon_4L;
        } else if ( Timer <= 32) {
            image = AAttackIcon_3L;
        } else if (Timer <= 40) {
            image =AAttackIcon_2L;
        }else if(Timer<=48)
        {
            image =AAttackIcon_1L;
        }

        if (Timer > 48) {
            owner.playerIcons.remove(this);

        }
        Timer+=1;
        super.paint(graphicsContext);
    }
}
