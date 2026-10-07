package org.example.Sprite.GameUIs;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Person;
import org.example.gametest.HelloApplication;

import java.util.ArrayList;
import java.util.List;

public class PlayerHPBar extends SampleUI
{
    int currentHP=100;
    Person owner=null;
    Image HPBOss=new Image(HelloApplication.class.getResource("/images/GameUIs/BloodBlockBOSS.png").toString());
    Image HPBlockImg=new Image(HelloApplication.class.getResource("/images/GameUIs/BloodBlock.png").toString());
    Image LifeImg=new Image(HelloApplication.class.getResource("/images/GameUIs/Life.png").toString());
    public List<HPBlock> HPList = new ArrayList<>();
    public List<PlayerLife> LifeList = new ArrayList<>();
    public PlayerHPBar(Image image, double x, double y, double width, double height, GameScene gameScene, Person owner)
    {
        super(image, x, y, width, height, gameScene);
        this.owner=owner;
        reviveHP();
        if(x<200)
        {
            LifeList.add(new PlayerLife(LifeImg,100-15,40,30,25,gameScene,0));
            LifeList.add(new PlayerLife(LifeImg,135-15,40,30,25,gameScene,1));
            LifeList.add(new PlayerLife(LifeImg,170-15,40,30,25,gameScene,2));
        }
        else if(x>600)
        {
            LifeList.add(new PlayerLife(LifeImg,900+200,40,30,25,gameScene,2));
            LifeList.add(new PlayerLife(LifeImg,935+200,40,30,25,gameScene,1));
            LifeList.add(new PlayerLife(LifeImg,970+200,40,30,25,gameScene,0));
        }

    }
    public void reviveHP()
    {
        if(x<200)
        {
            for(int i=0;i<40;i++)
            {
                HPList.add(new HPBlock(HPBlockImg,81.3+(double)i*8.8,13.9,8.8,20,gameScene,2.5*(double)i));
            }

        }
        else if(x>600)
        {
            for(int i=0;i<40;i++)
            {
                HPList.add(new HPBlock(HPBlockImg,858.3+(double)i*8.8,13.9,8.8,20,gameScene,99-2.5*(double)i));
            }

        }
        else {
            for(int i=0;i<100;i++)
            {
                HPList.add(new HPBlock(HPBOss,473.6+(double)i*3.44,10,3.44,26,gameScene,10*(double)i));
            }

        }
    }


    public void paint(GraphicsContext graphicsContext)
    {

        super.paint(graphicsContext);
        if (!HPList.isEmpty()) {
            for (int i = 0; i < HPList.size(); i++)
            {
                HPList.get(i).currentHP=owner.hp;
                HPList.get(i).paint(graphicsContext);
                if(HPList.get(i).y>1000)
                {
                    HPList.remove(i);
                }
            }

        }
        if (!LifeList.isEmpty()) {
            for (int i = 0; i < LifeList.size(); i++) {
                LifeList.get(i).currentLives = owner.lives;
                LifeList.get(i).paint(graphicsContext);
            }
        }
        if(owner.reviveClock==1)
        {
            reviveHP();
        }


    }

}
