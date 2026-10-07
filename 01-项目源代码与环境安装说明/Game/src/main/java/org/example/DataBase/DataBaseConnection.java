package org.example.DataBase;
import org.example.Scenes.GameScene;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class DataBaseConnection {
    private String url ;
    private String username;
    private String password ;
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private double finish_time;
    private String finish_date;
    private int myRank;
    public DataBaseConnection() {
        // 注意：出于安全考虑，原始代码中硬编码的云数据库地址、账号、密码已移除。
        // 运行前请替换为你自己的 MySQL 连接信息。
        url = "jdbc:mysql://<your-mysql-host>:3306/test_1";
        username = "<your-username>";
        password = "<your-password>";
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    //向数据库中添加数据
    public void addToDB(){
        String query = "INSERT INTO scores_record (finish_time, finish_date) VALUES ('" +
                finish_time + "', '" + finish_date + "')";
        int rowsInserted=0;
        try {
            rowsInserted = statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (rowsInserted > 0) {
            System.out.println("数据插入成功！");
        }

    }
    //从数据库读取数据并写入到txt文件中，以便于排行榜的读取
    public void readFromDB(){
        int rank=0;
        try (BufferedWriter bw=new BufferedWriter(new FileWriter(DataBaseConnection.class.getResource("/txt/rank.txt").getPath()))){
            String query = "SELECT * FROM scores_record ORDER BY finish_time ASC";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if ((resultSet.getDouble("finish_time"))<100||resultSet.getString("finish_date")==null){
                    continue;
                }
                int id = rank+1;
                double time = resultSet.getDouble("finish_time");
                String date= resultSet.getString("finish_date");
                if(time==finish_time){
                    myRank=id;
                    finish_date=date;
                }
                rank++;
                bw.write(id + " " + time + " " + date + "\n");
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    //关闭数据库链接
    public void closeDB(){
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //将当前对局的完成情况数据写入另一个txt文件
    public void writeMyFinishTime(){
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(DataBaseConnection.class.getResource("/txt/myScores.txt").getPath()))) {
            bw.write(myRank + " " + finish_time + " " + finish_date + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run(){
        DataBaseConnection dbc = new DataBaseConnection();
        dbc.setFinishTime(GameScene.getGamingTimer());
        dbc.setFinish_date(LocalDate.now().toString());
        dbc.addToDB();
        dbc.readFromDB();
        dbc.writeMyFinishTime();
        dbc.closeDB();

    }





    public void setFinishTime(double finish_time){
        this.finish_time = finish_time;
    }
    public void setFinish_date(String finish_date){
        this.finish_date = finish_date;
    }


    public static void main(String[] args) {
        DataBaseConnection dbc = new DataBaseConnection();
        dbc.setFinishTime(47.5);
        dbc.setFinish_date("2024-02-29");
        dbc.addToDB();

        dbc.readFromDB();
        dbc.writeMyFinishTime();
        dbc.closeDB();
    }

}
