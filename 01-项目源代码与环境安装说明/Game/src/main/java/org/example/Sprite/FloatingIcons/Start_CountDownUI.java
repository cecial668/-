package org.example.Sprite.FloatingIcons;

/*public class Start_CountDownUI extends FloatingIcon{
    private int Timer=0;//内置计时器
    private Image three,two,one,done;
    private int playerID;
    private double targety=0;
    private double Speed=0;
    private Person owner;

    public Start_CountDownUI(Image image, double x, double y, double width, double height, GameScene gameScene)
    {
        super(image, x, y, width, height, gameScene);
        this.owner=owner;
        this.playerID=playerID;
        done=new Image(HelloApplication.class.getResource("/images/GameUIs/CDFIGHT.png").toString());
        three=new Image(HelloApplication.class.getResource("/images/GameUIs/CD3.png").toString());
        two=new Image(HelloApplication.class.getResource("/images/GameUIs/CD2.png").toString());
        one=new Image(HelloApplication.class.getResource("/images/GameUIs/CD1.png").toString());
        image=three;
    }
    public void paint(GraphicsContext graphicsContext)
    {
        if(Timer==0 ||Timer==130 ||Timer==260||Timer==390)
        {
            if(Timer==0)
            {
                image=three;
            }
            else if(Timer==130)
            {
                image=two;
            }
            else if(Timer==260)
            {
                image=one;

            }
            else {
                image=done;

            }
            y=2000;
            targety=300;
        }

        if(Timer<100)//寿命到期，吩咐主人删除自己
        {
            if(y>targety+1)
            {
                y-=(y-targety)*0.15;
            }
            else
            {
                Speed=1;
                y-=Speed;
            }
        }
        else if(Timer<130)
        {
            Speed+=3;
            y-=Speed;

        }
        else if(Timer<230)//寿命到期，吩咐主人删除自己
        {
            if(y>targety+1)
            {
                y-=(y-targety)*0.15;
            }
            else
            {
                Speed=1;
                y-=Speed;
            }
        }
        else if(Timer<260)
        {
            Speed+=3;
            y-=Speed;

        }
        else if(Timer<360)//寿命到期，吩咐主人删除自己
        {
            if(y>targety+1)
            {
                y-=(y-targety)*0.1;
            }
            else
            {
                Speed=1;
                y-=Speed;
            }
        }
        else if(Timer<390)
        {
            Speed+=3;
            y-=Speed;

        }
        else if(Timer<490)//寿命到期，吩咐主人删除自己
        {
            if(y>targety+1)
            {
                y-=(y-targety)*0.15;
            }
            else
            {
                Speed=1;
                y-=Speed;
            }
        }
        else if(Timer<520)
        {
            Speed+=3;
            y-=Speed;

        }
        if(Timer>=520)
        {
            owner.playerIcons.remove(this);
        }
        Timer+=1;


        super.paint(graphicsContext);
    }
}*/
