package com.gearshifgroove.late_night_cruise.panes.Store.Data;

import com.gearshifgroove.late_night_cruise.panes.Store.Playlists;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

// Author(s): Christian Moloci

// This file manages the users playlist library, not to be mistaken for the Ownership file, this does not manage owned songs
public class UserLib {
    // Crate a playlists array list to store all the users playlists
    private static ArrayList<Playlist> playlists;

    // Gets all the saved playlists and returns them (the reason for doing this each time is because saved playlists might change)
    public static ArrayList<Playlist> getPlaylists() {
        // Initialize the playlist ArrayList
        playlists = new ArrayList<>();

        // Get the saved user playlists from the playlists file
        try {
            // Create an ObjectInputStream and tell it where to get the data from
            ObjectInputStream playlistInputStream = new ObjectInputStream(new FileInputStream("playlist.dat"));
            // Keep pulling playlist objects until an error occurs (this means that all the possible objects have been read)
            while (true) {
                try {
                    // Create a temp playlist var and set the object to it (casting it to a Playlist object in the process)
                    Playlist playlist = (Playlist) playlistInputStream.readObject();
                    playlists.add(playlist);
                } catch (Exception e) {
//                    e.printStackTrace();
                    System.out.println("Read all playlists");
                    break;
                }
            }
            // Close the ObjectInputStream
            playlistInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } // Id an error occurs, log the error

        // Lastly, return the playlists
        return playlists;
    }
}
