module org.example.gametest {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.google.gson;
    requires javafx.media;

    opens org.example.gametest to javafx.fxml;
    opens org.example.Scenes to javafx.fxml;
    opens org.example.Sprite to javafx.fxml;
    opens org.example.Controller to javafx.fxml;
    opens org.example.Sprite.Legends.Bot to com.google.gson;


    exports org.example.Controller;
    exports org.example.gametest;
    exports org.example.Scenes;
    exports org.example.Sprite;
    exports org.example.sound;
    exports org.example.Sprite.NormalBullet;
    exports org.example.Sprite.Legends.Bot;
    opens org.example.Sprite.NormalBullet to javafx.fxml;
}