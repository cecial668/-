package org.example.Sprite;

import javafx.scene.Scene;
import org.example.Scenes.GameScene;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public abstract class Role extends Sprite {


    boolean alive = true;
    //Group group;
    //Direction dir;
    //double speed;
    //Map<String, Image> imageMap = new HashMap<>();

    public Role(Image image,double x, double y, double width, double height, GameScene gameScene) {
        super(image, x, y, width, height, gameScene);
        //this.group = group;
        //this.dir = dir;
    }

    public abstract void move();

    public boolean isAlive() {
       return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

}