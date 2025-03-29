package com.gearshifgroove.late_night_cruise;

import com.gearshifgroove.late_night_cruise.panes.MainMenuPane;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.DB;
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

// Author(s): Christian Moloci

// Handles the playing of music/audio
public class GlobalPlayer {
    public static MediaPlayer player;
//    public static ArrayList<Song> selectedPlaylist;
    public static Playlist selectedPlaylist;
    public static boolean muteState = false;
    public static Image play;
    public static Image pause;

    // Gets the mute state
    static {
        // Read in the mute setting from the settings.txt file
        try {
            BufferedReader reader = new BufferedReader(new FileReader("settings.txt"));
            muteState = Boolean.parseBoolean(reader.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        } // Runs if theres an error
    }

    // Finds the last selected playlist or falls back on a demo playlist
    public static void gameMusicInit() {
        // Overwrite the selectedPlaylist if a playlist reference exists in the selectedPlaylist file to get the lastSelectedPlaylist when the user quit the game
        boolean foundLastSelectedPlaylist = false;
        try {
            // Create a BufferedReader and set it to selectedPlaylists
            BufferedReader reader = new BufferedReader(new FileReader("selectedPlaylist.txt"));
            // Read the first line of the file
            String lastSelectedPlaylist = reader.readLine();
            System.out.println(lastSelectedPlaylist);
            // If the file wasn't empty, find the playlist object and set selectedPlaylist to it
            if (!lastSelectedPlaylist.isEmpty()) {
                for (Playlist playlist : UserLib.getPlaylists()) {
                    if (playlist.getName().equals(lastSelectedPlaylist)) {
                        System.out.println("Switching selected playlist to last selected playlist: " + lastSelectedPlaylist);
                        selectedPlaylist = playlist;
                        playPlaylist(selectedPlaylist, 0);
                        foundLastSelectedPlaylist = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } // Runs if an error occurs
        // If no last selected playlist was found, set the default demo playlist
        if (selectedPlaylist == null || !foundLastSelectedPlaylist) {
            selectedPlaylist = DB.demoPlaylist;
            playPlaylist(selectedPlaylist, 0);
        }
    }

    // Changes the muteState boolean
    public static void changeAudioMuteState(boolean mute) {
        if (mute) {
            muteState = true;
        } else {
            muteState = false;
        }
        // Write mute state to the settings.txt file
        try {
            // Create a file writer
            FileWriter writer = new FileWriter("settings.txt");
            // Write the bool value as a string
            writer.write(String.valueOf(muteState));
            // Close the writer
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        } // Runs if an error occurs
    }

    // Returns the value of mute state
    public static boolean getAudioMuteState() {
        return muteState;
    }

    // Changes the song being played by global player and start playing it
    public static void changeSong(String file) {
        // If not in a mute state change song and play, otherwise, don't
        if (!muteState) {
            // Set the media to the file based on the file name
            Media media = new Media(new File(file).toURI().toString());
            // Stop the player if something is playing
            if (player != null) {
                player.stop();
            }
            // Set player to a new media player with the new media with its new file set to it
            player = new MediaPlayer(media);
            // Play it
            player.play();
            // Set the image in the StorePane for media control to pause since the song is now playing
            StorePane.mediaControl.setImage(StorePane.pause);
        }
    }

    // If something is playing, it stops playing it
    public static void stopMedia() {
        if (player != null) {
            player.stop();
            player = null;
        }
    }

    // Playing a playlist using recursion, needs a starting index
    public static void playPlaylist(Playlist playlist, int i) {
        // Play if not in a mute state
        if (!muteState) {
            // Change the song to the song in the playlist at the current index position
            changeSong(playlist.getSongs().get(i).getMedia());
            // Use recursion to play the next song once this song is done but up the index by one
            player.setOnEndOfMedia(() -> {
                if (i < playlist.getSongs().size() - 1) {
                    // Call the function again with a higher index to play the next song
                    playPlaylist(playlist, i + 1);
                }
            });
        }
    }

}
