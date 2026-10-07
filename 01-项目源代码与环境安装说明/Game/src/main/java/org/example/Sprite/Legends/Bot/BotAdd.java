package org.example.Sprite.Legends.Bot;

import java.util.ArrayList;
import java.util.List;
import org.example.Scenes.GameScene;
import javafx.scene.canvas.GraphicsContext;

public class BotAdd {
    private List<Bot> bots = new ArrayList<>();
    private GameScene gameScene;

    public BotAdd(GameScene gameScene) {
        this.gameScene = gameScene;
    }

    // 添加单个Bot
    public void addBot(double x, double y, int playerID) {
        Bot newBot = new Bot(null, x, y, 35, 40, gameScene, playerID, null, null);
        bots.add(newBot);
    }

    // 获取所有Bot
    public List<Bot> getBots() {
        return bots;
    }

    // 更新并绘制所有Bot
    public void updateAndPaintBots(GraphicsContext graphicsContext) {
        for (Bot bot : bots) {
            bot.move();
            bot.paint(graphicsContext);
        }
    }
}