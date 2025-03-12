package com.gearshifgroove.late_night_cruise;

import com.gearshifgroove.late_night_cruise.panes.Store.Data.Playlist;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Song;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.UserLib;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;

public class GlobalPlayer {
    public static MediaPlayer player;
    public static ArrayList<Song> selectedPlaylist;

    static {
        ArrayList<Playlist> playlists = new ArrayList<>();
        if (playlists.size() > 0) {
             selectedPlaylist = UserLib.getPlaylists().get(0).getSongs();
        }
    }

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

    public static void playPlaylist(ArrayList<Song> songs, int i) {
        // Change the song
        changeSong(songs.get(i).getMedia());
        // Use recursion to play the next song
        player.setOnEndOfMedia(() -> {
            if (i < songs.size() - 1) {
                playPlaylist(songs, i + 1);
            }
        });
    }

}
