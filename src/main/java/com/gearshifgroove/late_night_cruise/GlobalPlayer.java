package com.gearshifgroove.late_night_cruise;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class GlobalPlayer {
    public static MediaPlayer player;

    public static void changeSong(String file) {
        Media media = new Media(new File(file).toURI().toString());
        if (player != null) {
            player.stop();
        }
        player = new MediaPlayer(media);
        player.play();
    }

    public static void stopMedia() {
        if (player != null) {
            player.stop();
        }
    }

}
