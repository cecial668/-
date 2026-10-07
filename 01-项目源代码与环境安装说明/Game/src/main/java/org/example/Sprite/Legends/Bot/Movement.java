package org.example.Sprite.Legends.Bot;

import javafx.scene.image.Image;
import org.example.Scenes.GameScene;
import org.example.Sprite.FloatingIcons.BotAttack;
import org.example.Sprite.FloatingIcons.Miyabi_Normal_Attack;
import org.example.Sprite.Person;

import com.google.gson.Gson;
import org.example.gametest.HelloApplication;


import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;




public class Movement {

    private Bot bot;
    private GameScene gameScene;
    private BotPunch botPunch;
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private Dijkstra dijkstra;
    private Waypoint currentTargetWaypoint;
    private static final double ATTACK_DISTANCE = 100.0;
    private static final int ATTACK_INTERVAL = 1000; // 攻击间隔为1秒
    private static final int JUMP_LIMIT = 10; // 每秒跳跃限制次数
    private static final long JUMP_DURATION_LIMIT = 1000; // 跳跃时间窗口
    private static final long STOP_RECOVERY_DURATION = 1500; // 停止恢复时间
    private static final long WAYPOINT_TIMEOUT = 1500; // 目标路径点超时时间为1.5秒
    private static final long POSITION_CHECK_INTERVAL = 1500; // 位置检查间隔为1.5秒
    private static final double POSITION_THRESHOLD = 10.0; // X轴变化的最小像素
    private static final double Y_AXIS_THRESHOLD = 30.0; // Y轴差距的最大值
    private static final Random RANDOM = new Random();
    private long lastAttackTime = 0;
    private final Lock lock = new ReentrantLock();
    private int jumpCount = 0;
    private long firstJumpTime = System.currentTimeMillis();
    private boolean isStopped = false;
    private List<Waypoint> waypoints;
    private long targetWaypointStartTime = 0; // 记录当前目标路径点的开始时间
    private double lastCheckedX = 0;
    private long lastPositionCheckTime = System.currentTimeMillis();
    private int STimer,nextTimer=100;
    Clip BossAttack;

    private MapData loadWaypointsFromJson(String jsonFilePath) {
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader(jsonFilePath);
            return gson.fromJson(reader, MapData.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Movement(Bot bot, GameScene gameScene, int playerID, BotPunch botPunch, Dijkstra dijkstra) {
        this.bot = bot;

        this.gameScene = gameScene;
        this.botPunch = botPunch;
        if (gameScene.Mapkind == 1) {
            MapData mapData = loadWaypointsFromJson("src/main/resources/Navigations/Map1.json");
            System.out.println("Map1 loaded!");
            if (mapData != null) {
                this.waypoints = mapData.getWaypoints();
                System.out.println(waypoints);
            } else {
                this.waypoints = new ArrayList<>(); // 处理加载失败的情况，防止空指针异常
                System.out.println("waypoint is null!");
            }
        } else {
            MapData mapData = loadWaypointsFromJson("src/main/resources/Navigations/Map2.json");
            System.out.println("Map2 loaded!");
            if (mapData != null) {
                this.waypoints = mapData.getWaypoints();
                System.out.println(waypoints);
            } else {
                this.waypoints = new ArrayList<>(); // 处理加载失败的情况，防止空指针异常
                System.out.println("waypoint is null!");
            }
        }

        this.dijkstra = new Dijkstra(waypoints, gameScene);
        this.currentTargetWaypoint = null;
        
        startPathOutput();
    }

    public void move() {
        
        if (isStopped) {
            return; // 如果被停止，忽略移动
        }
            
        double playerX, playerY;
        Person targetPlayer;

        // 计算Bot与两个玩家的距离
        double distanceToPlayer1 = calculateDistance(bot.getX(), bot.getY(), gameScene.getX1(), gameScene.getY1());
        double distanceToPlayer2 = calculateDistance(bot.getX(), bot.getY(), gameScene.getX2(), gameScene.getY2());

        // 判断哪一个玩家更近，或者其中一个玩家已经死亡
        if (gameScene.getPlayer1().hp > 0 && (distanceToPlayer1 < distanceToPlayer2 || gameScene.getPlayer2().hp <= 0)) {
            playerX = gameScene.getX1();
            playerY = gameScene.getY1();
            targetPlayer = gameScene.getPlayer1();
        } else {
            playerX = gameScene.getX2();
            playerY = gameScene.getY2();
            targetPlayer = gameScene.getPlayer2();
        }

        Waypoint botPosition = new Waypoint(bot.getX(), bot.getY());

        long currentTime = System.currentTimeMillis();
        
        if (currentTime - lastPositionCheckTime > POSITION_CHECK_INTERVAL) {
            if (Math.abs(bot.getX() - lastCheckedX) < POSITION_THRESHOLD) {
                // 如果X轴变化小于10个像素，重新检测最近路径点
                System.out.println("Position change too small, recalculating path...");
                Waypoint closestToBot = findClosestWaypointWithYThreshold(bot.getX(), bot.getY());
                if (closestToBot != null) {
                    moveTo(closestToBot);
                    Waypoint closestToPlayer = dijkstra.findClosestWaypoint(playerX, playerY);
                    updateTargetWaypoint(closestToBot, closestToPlayer, targetPlayer);
                }
            }
            lastCheckedX = bot.getX();
            lastPositionCheckTime = currentTime;
        }
             

        // 找到离玩家最近的路径点
        Waypoint closestToPlayer = dijkstra.findClosestWaypoint(playerX, playerY);

        // 找到离Bot最近的路径点
        Waypoint closestToBot = dijkstra.findClosestWaypoint(bot.getX(), bot.getY());

        // 如果当前目标路径点为空，或者Bot已经接近当前目标路径点，更新目标路径点
        if (currentTargetWaypoint == null || isWithinThreshold(botPosition, currentTargetWaypoint, 2.0)) {
            updateTargetWaypoint(closestToBot, closestToPlayer, targetPlayer);
        } else {
            // 检查路径点超时
            if (currentTime - targetWaypointStartTime > WAYPOINT_TIMEOUT) {
                // 如果超时，更新目标路径点
                System.out.println("timeout!");
                moveTo(closestToBot);
                updateTargetWaypoint(closestToBot, closestToPlayer, targetPlayer);
            }
        }

        // 移动到当前目标路径点
        if (currentTargetWaypoint != null) {
            moveTo(currentTargetWaypoint);
            //System.out.println("move to " + currentTargetWaypoint.toString());
        }
        //System.out.println(targetPlayer.toString());
        attack(playerX, playerY, targetPlayer);

    }

    private Waypoint findClosestWaypointWithYThreshold(double botX, double botY) {
        Waypoint closestWaypoint = null;
        double closestDistance = Double.MAX_VALUE;

        for (Waypoint waypoint : waypoints) {
            double distance = calculateDistance(botX, botY, waypoint.getX(), waypoint.getY());
            double yDifference = Math.abs(botY - waypoint.getY());

            if (yDifference <= Y_AXIS_THRESHOLD && distance < closestDistance) {
                closestDistance = distance;
                closestWaypoint = waypoint;
            }
        }

        return closestWaypoint;
    }

    private void updateTargetWaypoint(Waypoint closestToBot, Waypoint closestToPlayer, Person targetPlayer) {
        List<Waypoint> path = dijkstra.findPath(closestToBot, closestToPlayer);
        if (path.size() > 1) {
            currentTargetWaypoint = path.get(1); // 更新为路径中的下一个路径点
            targetWaypointStartTime = System.currentTimeMillis(); // 重置目标路径点开始时间
        } else {
            // 如果路径大小为1，直接移动到玩家位置
            currentTargetWaypoint = null;
            moveToPlayer(targetPlayer.x, targetPlayer.y);
        }
    }

    public Waypoint getCurrentTargetWaypoint() {
        return currentTargetWaypoint;
    }
 
    private boolean isWithinThreshold(Waypoint point1, Waypoint point2, double threshold) {
        double distance = calculateDistance(point1.getX(), point1.getY(), point2.getX(), point2.getY());
        return distance <= threshold;
    }

    public void moveToPlayer(double x,double y) {
        //System.out.println("move to player");
        moveTo(new Waypoint(x, y));
    }
    
    private void moveTo(Waypoint targetWaypoint) {
        double targetX = targetWaypoint.getX();
        double targetY = targetWaypoint.getY();
        double currentX = bot.getX();
        double currentY = bot.getY();
    
        // 允许8像素的容忍值
        double threshold = 8.0;
    
        // 判断目标路径点的Y轴坐标是否与当前路径点一致
        if (Math.abs(currentY - targetY) < threshold) {
            // 高度一致，直接走动到目标路径点
            walk(targetX > currentX);
        } else {
            if (targetY < currentY) {
                // 目标路径点比当前路径点高，判断是否需要跳跃
                if (currentY - targetY > threshold) {
                    //System.out.println("TargetX = " + targetX + ",  TargetY  =" + targetY +  "currentY = " + currentY);
                    jump();
                } else {
                    walk(targetX > currentX);
                }
            } else {
                // 目标路径点比当前路径点低
                walk(targetX > currentX);
            }
        }
    }

    public void walk(boolean right) {
        // 为Bot赋值一个X方向上的速度，同时调整Bot的面向方向
        bot.setX_speed(right ? bot.getMoveSpeed() : -bot.getMoveSpeed());
        bot.facingDirection = right;
    }

    public void jump() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - firstJumpTime > JUMP_DURATION_LIMIT) {
            // 重置跳跃计数和时间窗口
            firstJumpTime = currentTime;
            jumpCount = 0;
        }

        lock.lock();
        try {
            if (jumpCount >= JUMP_LIMIT) {
                // 达到跳跃限制次数，强制停止Bot
                stop();
                isStopped = true;
                executorService.submit(() -> {
                    try {
                        Thread.sleep(STOP_RECOVERY_DURATION); // 停止恢复时间
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        isStopped = false; // 恢复移动
                    }
                });
                return;
            }

            if (!bot.get_Onair()) {
                jumpCount++;
                executorService.submit(() -> {
                    bot.setY_speed(7.5);
                    bot.activate_Onair();
                    bot.activate_space_flag();
                    //System.out.println("jump!");
                    //System.out.println(bot.get_Onair());

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        bot.disactivate_space_flag();
                        bot.disactivate_Onair();
                        lock.unlock();
                    }
                });
            } else {
                lock.unlock();
            }
        } catch (Exception e) {
            lock.unlock();
        }
    }

    public void stop() {
        //System.out.println("stop");
        bot.setX_speed(0);
        bot.setY_speed(0);
        bot.x = Math.round(bot.x);
        bot.y = Math.round(bot.y) - 1;
    }

    private double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    private void startPathOutput() {
        executorService.submit(() -> {
            while (true) {
                Waypoint botPosition = new Waypoint(bot.getX(), bot.getY());
                Waypoint playerPosition = new Waypoint(gameScene.getX1(), gameScene.getY1());
                List<Waypoint> path = dijkstra.findPath(botPosition, playerPosition);
                //System.out.println("Current Path:");
                for (Waypoint waypoint : path) {
                    //System.out.println("Waypoint: (" + waypoint.getX() + ", " + waypoint.getY() + ")");
                }
                try {
                    Thread.sleep(1000); // 每秒输出一次
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void attack(double playerX, double playerY, Person targetPlayer) {
        double botX = bot.getX();
        double botY = bot.getY();

        double yThreshold = 40.0; // Y轴差距的阈值
        double distanceToPlayer = Math.abs(bot.getX() - playerX);
        bot.facingDirection = playerX > bot.getX();
        
        if (distanceToPlayer <= ATTACK_DISTANCE) {
        // 检查X轴和Y轴的条件
        //System.out.println("attempt to attack");

        Long currentTime = System.currentTimeMillis();

            if (currentTime - lastAttackTime >= ATTACK_INTERVAL && Math.abs(botY - playerY) <= yThreshold) {
                {
                    //System.out.println("attack!");
                    bot.attackCooldown=30;
                    bot.playerIcons.add(new BotAttack(null,bot.x+0.5*bot.width, bot.y-150, 150, 147, gameScene, bot,bot.facingDirection));
                    bot.normalBulletList.add(new BotPunch(null, botX, botY-40, 100, 100, gameScene, bot.facingDirection, bot));
                    try{
                        File audioFile2 = new File(HelloApplication.class.getResource("/Music/BOSS/BossAttack.wav").getPath());
                        AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                        AudioFormat format2 = audioStream2.getFormat();
                        DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
                        BossAttack = (Clip) AudioSystem.getLine(info2);
                        BossAttack.open(audioStream2);
                        FloatControl volumeControl = (FloatControl) BossAttack.getControl(FloatControl.Type.MASTER_GAIN);
                        volumeControl.setValue(-0.0f); // 将音量降低10分贝（根据需要调整值）


                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                        e.printStackTrace();
                        // 处理异常，比如音频文件无法加载或播放等
                    }
                    BossAttack.setFramePosition(0);  // 重置到开始位置
                    BossAttack.start();
                }
                lastAttackTime = currentTime;
            }
        }
    }
}