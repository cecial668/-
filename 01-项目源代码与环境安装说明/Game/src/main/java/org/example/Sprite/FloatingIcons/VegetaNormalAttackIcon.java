package org.example.Sprite.FloatingIcons;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Person;
import org.example.gametest.HelloApplication;

import java.util.ArrayList;

public class VegetaNormalAttackIcon extends FloatingIcon {
    public Person owner;
    boolean Direction;
    private int Timer = 0;//内置计时器
    public Image NormalAttackIcon_1L = new Image(HelloApplication.class.getResource("/images/Vegeta/Icons/1L.png").toString());
    public Image NormalAttackIcon_2L = new Image(HelloApplication.class.getResource("/images/Vegeta/Icons/2L.png").toString());
    public Image NormalAttackIcon_3L = new Image(HelloApplication.class.getResource("/images/Vegeta/Icons/3L.png").toString());
    public Image NormalAttackIcon_4L = new Image(HelloApplication.class.getResource("/images/Vegeta/Icons/4L.png").toString());
    public Image NormalAttackIcon_5L = new Image(HelloApplication.class.getResource("/images/Vegeta/Icons/5L.png").toString());
    public Image NormalAttackIcon_6L = new Image(HelloApplication.class.getResource("/images/Vegeta/Icons/6L.png").toString());

    public Image NormalAttackIcon_1R = new Image(HelloApplication.class.getResource("/images/Vegeta/Icons/1R.png").toString());
    public Image NormalAttackIcon_2R = new Image(HelloApplication.class.getResource("/images/Vegeta/Icons/2R.png").toString());
    public Image NormalAttackIcon_3R = new Image(HelloApplication.class.getResource("/images/Vegeta/Icons/3R.png").toString());
    public Image NormalAttackIcon_4R = new Image(HelloApplication.class.getResource("/images/Vegeta/Icons/4R.png").toString());
    public Image NormalAttackIcon_5R = new Image(HelloApplication.class.getResource("/images/Vegeta/Icons/5R.png").toString());
    public Image NormalAttackIcon_6R = new Image(HelloApplication.class.getResource("/images/Vegeta/Icons/6R.png").toString());
    public ArrayList<Image> NormalAttackIcons = new ArrayList<>();
    //public ArrayList<Image> NormalAttackIconsR = new ArrayList<>();

    public VegetaNormalAttackIcon(Image image, double x, double y, double width, double height, GameScene gameScene, Person owner) {
        super(image, x, y, width, height, gameScene, owner);
        this.owner = owner;
        this.Direction=owner.facingDirection;
        if(Direction==false) {
            NormalAttackIcons.add(NormalAttackIcon_1L);
            NormalAttackIcons.add(NormalAttackIcon_2L);
            NormalAttackIcons.add(NormalAttackIcon_3L);
            NormalAttackIcons.add(NormalAttackIcon_4L);
            NormalAttackIcons.add(NormalAttackIcon_5L);
            NormalAttackIcons.add(NormalAttackIcon_6L);
        }else {
            NormalAttackIcons.add(NormalAttackIcon_1R);
            NormalAttackIcons.add(NormalAttackIcon_2R);
            NormalAttackIcons.add(NormalAttackIcon_3R);
            NormalAttackIcons.add(NormalAttackIcon_4R);
            NormalAttackIcons.add(NormalAttackIcon_5R);
            NormalAttackIcons.add(NormalAttackIcon_6R);
        }
    }

    public void paint(GraphicsContext graphicsContext) {
        if (Direction==false) {
            x = owner.x-width*0.9;
        } else {
            x =owner.x+owner.width-width*0.1;
        }
        y=owner.y-30;
        if (Timer<5){
            image=NormalAttackIcons.get(0);
        } else if (Timer <= 10) {
            image = NormalAttackIcons.get(1);
        } else if (Timer <= 15) {
            image = NormalAttackIcons.get(2);
        } else if ( Timer <= 20) {
            image = NormalAttackIcons.get(3);
        } else if (Timer <= 25) {
            image = NormalAttackIcons.get(4);
        }else if (Timer <= 30) {
            image=NormalAttackIcons.get(5);
        }
        if (Timer > 30) {
                owner.playerIcons.remove(this);

        }
        Timer+=1;
        super.paint(graphicsContext);
        }
}


