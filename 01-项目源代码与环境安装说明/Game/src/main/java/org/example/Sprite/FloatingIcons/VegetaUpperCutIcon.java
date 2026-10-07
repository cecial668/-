package org.example.Sprite.FloatingIcons;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Person;
import org.example.gametest.HelloApplication;

import java.util.ArrayList;

public class VegetaUpperCutIcon extends FloatingIcon {
    public Person owner;
    private int Timer = 0;//内置计时器
    int iconNumber = 1;
    boolean Direction;

    public Image UppercutIcon_1L=new Image(HelloApplication.class.getResource("/images/Vegeta/Icons/shanggou1L.png").toString());
    public Image UppercutIcon_2L=new Image(HelloApplication.class.getResource("/images/Vegeta/Icons/shanggou2L.png").toString());
    public Image UppercutIcon_3L=new Image(HelloApplication.class.getResource("/images/Vegeta/Icons/shanggou3L.png").toString());
    public Image UppercutIcon_4L=new Image(HelloApplication.class.getResource("/images/Vegeta/Icons/shanggou4L.png").toString());
    public Image UppercutIcon_5L=new Image(HelloApplication.class.getResource("/images/Vegeta/Icons/shanggou5L.png").toString());

    public Image UppercutIcon_1R=new Image(HelloApplication.class.getResource("/images/Vegeta/Icons/shanggou1R.png").toString());
    public Image UppercutIcon_2R=new Image(HelloApplication.class.getResource("/images/Vegeta/Icons/shanggou2R.png").toString());
    public Image UppercutIcon_3R=new Image(HelloApplication.class.getResource("/images/Vegeta/Icons/shanggou3R.png").toString());
    public Image UppercutIcon_4R=new Image(HelloApplication.class.getResource("/images/Vegeta/Icons/shanggou4R.png").toString());
    public Image UppercutIcon_5R=new Image(HelloApplication.class.getResource("/images/Vegeta/Icons/shanggou5R.png").toString());
    public ArrayList<Image> UppercutIcons = new ArrayList<>();
    //public ArrayList<Image> UppercutIconsR = new ArrayList<>();

    public VegetaUpperCutIcon(Image image, double x, double y, double width, double height, GameScene gameScene, Person owner) {
        super(image, x, y, width, height, gameScene, owner);
        this.owner = owner;
        this.Direction=owner.facingDirection;
        if(Direction==false){
            UppercutIcons.add(UppercutIcon_1L);
            UppercutIcons.add(UppercutIcon_2L);
            UppercutIcons.add(UppercutIcon_3L);
            UppercutIcons.add(UppercutIcon_4L);
            UppercutIcons.add(UppercutIcon_5L);
        }else {
            UppercutIcons.add(UppercutIcon_1R);
            UppercutIcons.add(UppercutIcon_2R);
            UppercutIcons.add(UppercutIcon_3R);
            UppercutIcons.add(UppercutIcon_4R);
            UppercutIcons.add(UppercutIcon_5R);
        }
    }

    public void paint(GraphicsContext graphicsContext) {
        if (Direction==false) {
            x = owner.x - width*0.7;
        } else {
            x = owner.x +owner.width-width*0.3;
        }
        y=owner.y-100;
        if (Timer<5){
            image=UppercutIcons.get(0);
        }else if (Timer <= 10) {
            image = UppercutIcons.get(1);
        } else if (Timer <= 15) {
            image = UppercutIcons.get(2);
        } else if (Timer <= 20) {
            image = UppercutIcons.get(3);
        } else if (Timer <= 25) {
            image = UppercutIcons.get(4);
        }
        if (Timer > 30) {

            owner.playerIcons.remove(this);

        }
        Timer+=1;
        super.paint(graphicsContext);
    }
}
