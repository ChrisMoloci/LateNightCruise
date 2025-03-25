package com.gearshifgroove.late_night_cruise;

import com.gearshifgroove.late_night_cruise.panes.MainMenuPane;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Playlist;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Song;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.UserLib;
import com.gearshifgroove.late_night_cruise.panes.StorePane;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class GlobalPlayer {
    public static MediaPlayer player;
//    public static ArrayList<Song> selectedPlaylist;
    public static Playlist selectedPlaylist;
    public static boolean muteState = false;
    public static Image play;
    public static Image pause;

    static {
//        ArrayList<Playlist> playlists = new ArrayList<>();
//        if (playlists.size() > 0) {
//             selectedPlaylist = UserLib.getPlaylists().get(0).getSongs();
//        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader("settings.txt"));
            muteState = Boolean.parseBoolean(reader.readLine());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changeAudioMuteState(boolean mute) {
        if (mute) {
            muteState = true;
        } else {
            muteState = false;
        }
        try {
            FileWriter writer = new FileWriter("settings.txt");
            writer.write(String.valueOf(muteState));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean getAudioMuteState() {
        return muteState;
    }

    public static void changeSong(String file) {
        if (!muteState) {
            Media media = new Media(new File(file).toURI().toString());
            if (player != null) {
                player.stop();
            }
            player = new MediaPlayer(media);
            player.play();
            StorePane.mediaControl.setImage(StorePane.pause);
        }
    }

    public static void stopMedia() {
        if (player != null) {
            player.stop();
            player = null;
        }
    }

    public static void playPlaylist(Playlist playlist, int i) {
        if (!muteState) {
            // Change the song
            changeSong(playlist.getSongs().get(i).getMedia());
            // Use recursion to play the next song
            player.setOnEndOfMedia(() -> {
                if (i < playlist.getSongs().size() - 1) {
                    playPlaylist(playlist, i + 1);
                }
            });
        }
    }

}
