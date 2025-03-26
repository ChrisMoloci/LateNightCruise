package com.gearshifgroove.late_night_cruise.panes.Store.Data;

import java.io.Serializable;
import java.util.ArrayList;

// Author(s): Christian Moloci

// Playlists are used to play music in the game.
// They are customizable so the user can choose what music is played.
// Only owned songs are may be added to a playlist.
public class Playlist implements Serializable {
    // Playlists store an id, a name, and a list of songs which are instantiated in DB
    private int id;
    private String name;
    private ArrayList<Song> songs;

    // Constructor sets the ID, name, and initializes the songs array
    public Playlist(int id, String name) {
        this.id = id;
        this.name = name;
        this.songs = new ArrayList<>();
    }

    // Adds a song to the playlist
    public void addSong(Song song) {
        this.songs.add(song);
        System.out.println("Song added: " + song.getSongName());
    }

    // Gets the entire song array list
    public ArrayList<Song> getSongs() {
        return songs;
    }

    // getter and setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
