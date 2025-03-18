package com.gearshifgroove.late_night_cruise.panes.Store.Data;

import com.gearshifgroove.late_night_cruise.panes.Store.Playlists;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class UserLib {
    private static ArrayList<Playlist> playlists = null;

    public static ArrayList<Playlist> getPlaylists() {
        playlists = new ArrayList<>();

        try {
            ObjectInputStream playlistInputStream = new ObjectInputStream(new FileInputStream("playlist.dat"));
            while (true) {
                try {
                    Playlist playlist = (Playlist) playlistInputStream.readObject();
                    playlists.add(playlist);
                } catch (Exception e) {
//                    e.printStackTrace();
                    System.out.println("Read all playlists");
                    break;
                }
            }
            playlistInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return playlists;
    }
}
