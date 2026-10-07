package org.example.Sprite.FloatingIcons;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Person;
import org.example.gametest.HelloApplication;

import java.util.ArrayList;

public class BotAttack extends FloatingIcon{
    public Person owner;
    private int Timer = 0;//内置计时器
    public Image NormalAttackIcon_1L = new Image(HelloApplication.class.getResource("/images/BOSS/normal1L.png").toString());
    public Image NormalAttackIcon_2L = new Image(HelloApplication.class.getResource("/images/BOSS/normal2L.png").toString());
    public Image NormalAttackIcon_3L = new Image(HelloApplication.class.getResource("/images/BOSS/normal3L.png").toString());
    public Image NormalAttackIcon_4L = new Image(HelloApplication.class.getResource("/images/BOSS/normal4L.png").toString());
    public Image NormalAttackIcon_5L = new Image(HelloApplication.class.getResource("/images/BOSS/normal5L.png").toString());
    public Image NormalAttackIcon_6L = new Image(HelloApplication.class.getResource("/images/BOSS/normal6L.png").toString());

    public Image NormalAttackIcon_1R = new Image(HelloApplication.class.getResource("/images/BOSS/normal1.png").toString());
    public Image NormalAttackIcon_2R = new Image(HelloApplication.class.getResource("/images/BOSS/normal2.png").toString());
    public Image NormalAttackIcon_3R = new Image(HelloApplication.class.getResource("/images/BOSS/normal3.png").toString());
    public Image NormalAttackIcon_4R = new Image(HelloApplication.class.getResource("/images/BOSS/normal4.png").toString());
    public Image NormalAttackIcon_5R = new Image(HelloApplication.class.getResource("/images/BOSS/normal5.png").toString());
    public Image NormalAttackIcon_6R = new Image(HelloApplication.class.getResource("/images/BOSS/normal6.png").toString());
    public ArrayList<Image> NormalAttackIcons = new ArrayList<>();
    boolean facing=false;
    //public ArrayList<Image> NormalAttackIconsR = new ArrayList<>();

    public BotAttack(Image image, double x, double y, double width, double height, GameScene gameScene, Person owner, boolean facingDirection) {
        super(image, x, y, width, height, gameScene, owner);
        this.owner = owner;
        facing=facingDirection;
        if(facing==false) {
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
        if (facing==false) {
            x = owner.x-2.6*owner.width;
        } else {
            x =owner.x-0.1*owner.width;
        }
        y=owner.y-50;
        if (Timer<8){
            image=NormalAttackIcons.get(0);
        } else if (Timer <= 16) {
            image = NormalAttackIcons.get(1);
        } else if (Timer <= 24) {
            image = NormalAttackIcons.get(2);
        } else if ( Timer <= 32) {
            image = NormalAttackIcons.get(3);
        } else if (Timer <= 40) {
            image = NormalAttackIcons.get(4);
        }else if (Timer <= 42) {
            image=NormalAttackIcons.get(5);
        }
        if (Timer > 50) {
            owner.playerIcons.remove(this);

        }
        Timer+=1;
        super.paint(graphicsContext);
    }

}
