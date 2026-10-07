package org.example.Sprite.FloatingIcons;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.Person;
import org.example.gametest.HelloApplication;

public class BossIcon extends FloatingIcon{
    public Person owner;
    public int timer=0;
    private boolean Dir,attackDir;
    public boolean walkAction=false;
    Image noneL=new Image(HelloApplication.class.getResource("/images/BOSS/bossNoneL.png").toString());
    Image noneR=new Image(HelloApplication.class.getResource("/images/BOSS/bossNoneR.png").toString());
    Image left1=new Image(HelloApplication.class.getResource("/images/BOSS/bossWalk1L.png").toString());
    Image left2=new Image(HelloApplication.class.getResource("/images/BOSS/bossWalk2L.png").toString());
    Image right1=new Image(HelloApplication.class.getResource("/images/BOSS/bossWalk1R.png").toString());
    Image right2=new Image(HelloApplication.class.getResource("/images/BOSS/bossWalk2R.png").toString());
    Image upleft=new Image(HelloApplication.class.getResource("/images/BOSS/bossJumpL.png").toString());
    Image upright=new Image(HelloApplication.class.getResource("/images/BOSS/bossJumpR.png").toString());
    Image airleft=new Image(HelloApplication.class.getResource("/images/BOSS/bossJumpL.png").toString());
    Image airright=new Image(HelloApplication.class.getResource("/images/BOSS/bossJumpR.png").toString());
    Image normalAttackleft1=new Image(HelloApplication.class.getResource("/images/BOSS/bossAttackL1.png").toString());
    Image normalAttackright1=new Image(HelloApplication.class.getResource("/images/BOSS/bossAttackR1.png").toString());
    Image normalAttackleft2=new Image(HelloApplication.class.getResource("/images/BOSS/bossAttackL2.png").toString());
    Image normalAttackright2=new Image(HelloApplication.class.getResource("/images/BOSS/bossAttackR2.png").toString());
    Image normalAttackleft3=new Image(HelloApplication.class.getResource("/images/BOSS/bossAttackL3.png").toString());
    Image normalAttackright3=new Image(HelloApplication.class.getResource("/images/BOSS/bossAttackR3.png").toString());
    public BossIcon(Image image, double x, double y, double width, double height, GameScene gameScene, Person owner)
    {
        super(image, x, y, width, height, gameScene,owner);
        this.owner=owner;
        if(owner.facingDirection==false)
        {
            this.image=noneL;
            Dir=false;
        }
        else {
            this.image=noneR;
            Dir=true;
        }

    }
    public void paint(GraphicsContext graphicsContext)
    {

        if(Dir==false)
        {
            x=owner.x-40;
            y=owner.y-37;

        }
        else {
            x=owner.x-5;
            y=owner.y-37;

        }

        timer+=1;
        if(timer==10)
        {
            timer=0;

            if(owner.x_speed>1 || owner.x_speed<1 &&(owner.y_speed>-0.3 &&owner.y_speed<0.3))//判断为行走
            {
                if(walkAction==false)
                {
                    if(owner.facingDirection==false)
                    {
                        Dir=false;
                        image=left1;

                    }
                    else {
                        Dir=true;
                        image=right1;

                    }
                    walkAction=true;

                }
                else {
                    if(owner.facingDirection==false)
                    {
                        Dir=false;
                        image=left2;

                    }
                    else {
                        Dir=true;
                        image=right2;

                    }
                    walkAction=false;

                }

            }
            if(owner.facingDirection==false)
            {
                if(owner.x_speed>-1 && owner.x_speed<1 && (owner.y_speed>-0.3 &&owner.y_speed<0.3))
                {
                    Dir=false;
                    image=noneL;
                }
            }
            else
            {
                if(owner.x_speed>-1 && owner.x_speed<1 && (owner.y_speed>-0.3 &&owner.y_speed<0.3))
                {
                    Dir=true;
                    image=noneR;
                }
            }
        }
        if(owner.y_speed<-0.3 || owner.y_speed>0.3)//判断为滞空
        {
            if(owner.facingDirection==false)
            {

                image=airleft;
            }
            else {

                image=airright;
            }
        }
        if(owner.attackCooldown==29)
        {
            attackDir= owner.facingDirection;
        }
        if(owner.attackCooldown>20)
        {

            if(attackDir==false)
            {
                if(owner.attackCooldown==29)
                {
                    Dir=false;
                }

                image=normalAttackleft1;
            }
            else {

                image=normalAttackright1;
                if(owner.attackCooldown==29)
                {
                    Dir=true;
                }
            }

        }
        else if(owner.attackCooldown>10)
        {
            if(attackDir==false)
            {

                image=normalAttackleft2;
                if(owner.attackCooldown==19)
                {
                    Dir=false;
                }
            }
            else {

                image=normalAttackright2;
                if(owner.attackCooldown==19)
                {
                    Dir=true;
                }
            }

        }
        else if(owner.attackCooldown>0)
        {
            if(attackDir==false)
            {

                image=normalAttackleft3;
                if(owner.attackCooldown==9)
                {
                    Dir=false;
                }
            }
            else {

                image=normalAttackright3;
                if(owner.attackCooldown==9)
                {
                    Dir=true;
                }
            }

        }
        super.paint(graphicsContext);
    }
}
