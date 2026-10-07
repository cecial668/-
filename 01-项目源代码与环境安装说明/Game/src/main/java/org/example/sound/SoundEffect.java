package org.example.sound;

import javafx.scene.media.AudioClip;

public class SoundEffect {
    //添加鼠标按键音效
    public static void play(String src){
        AudioClip audioClip = new AudioClip(SoundEffect.class.getResource(src).toString());
        audioClip.play();
    }
}
