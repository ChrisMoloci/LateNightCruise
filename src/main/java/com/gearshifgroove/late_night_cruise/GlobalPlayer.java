package com.gearshifgroove.late_night_cruise;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class GlobalPlayer {
    public static MediaPlayer player;

    public static void changeSong(Media media) {
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
