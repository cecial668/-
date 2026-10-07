package org.example.Sprite.Legends.Bot;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.gametest.HelloApplication;

public class PaintSignInMap {
    private GameScene gameScene;
    private Image image;
    /* 
    private double x1 = 197;
    private double y1 = 610;
    private double x2 = 266;
    private double y2 = 510;
    private double x3 = 383;
    private double y3 = 360;
    private double x4 = 748;
    private double y4 = 360;
    private double x5 = 854;
    private double y5 = 510;
    private double x6 = 960;
    private double y6 = 610;
    private double x7 = 418;
    private double y7 = 610;
    private double x8 = 715;
    private double y8 = 610;
    private double x9 = 470;
    private double y9 = 210;
    private double x10 = 650;
    private double y10 = 210;
*/
    private double x11 = 220;
    private double y11 = 560;
    private double x12 = 530;
    private double y12 = 560;
    private double x13 = 1000;
    private double y13 = 560;
    private double x14 = 900;
    private double y14 = 430;
    private double x15 = 783;
    private double y15 = 430;
    private double x16 = 434;
    private double y16 = 430;
    private double x17 = 320;
    private double y17 = 430;
    private double x18 = 535;
    private double y18 = 300;
    private double x19 = 688;
    private double y19 = 300;
    private double x20 = 690;
    private double y20 = 560;
    private double x21 = 370;
    private double y21 = 560;
    private double x22 = 850;
    private double y22 = 560;
    



    public PaintSignInMap(GameScene gameScene)  {
        this.gameScene = gameScene;
        this.image = new Image(HelloApplication.class.getResource("/images/Vegeta/Bullets/ball.png").toString());
        startPrintingCoordinates();
    }

    private void startPrintingCoordinates() {
        Thread thread = new Thread(() -> {
            while (true) {
                double xp = gameScene.getX1();
                double yp = gameScene.getY1();
                double xb = gameScene.getX3();
                double yb = gameScene.getY3();

                System.out.println("Player1 Coordinates: X = " + xp + ", Y = " + yp);
                //System.out.println("Bot Coordinates: X1 = " + xb + ", Y = " + yb);
                //System.out.println(gameScene.getBot().get_Onair());
                try {
                    // 每秒输出一次
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void paint() {
        GraphicsContext graphicsContext = gameScene.getGraphicsContext();
        graphicsContext.drawImage(image, x11, y11);
        graphicsContext.drawImage(image, x12, y12);
        graphicsContext.drawImage(image, x13, y13);
        graphicsContext.drawImage(image, x14, y14);
        graphicsContext.drawImage(image, x15, y15);
        graphicsContext.drawImage(image, x16, y16);
        graphicsContext.drawImage(image, x17, y17);
        graphicsContext.drawImage(image, x18, y18);
        graphicsContext.drawImage(image, x19, y19);
        graphicsContext.drawImage(image, x20, y20);
        graphicsContext.drawImage(image, x21, y21);
        graphicsContext.drawImage(image, x22, y22);
    }
}