package com.gearshifgroove.late_night_cruise.panes.Store.Data;

import java.io.Serializable;
import java.util.ArrayList;

// Author(s): Christian Moloci

// Artist Class, the endpoint of music data
// Artist contain both songs and genres
// Songs are created inside of artists but genres are passed and instantiated in Data -> Genres
public class Artist implements Serializable {
    // Artists hold a name, ID, and their songs
    private String name;
    private String artistId;
    private ArrayList<Song> songs;

    // The constructor sets the name and ID and instantiates and empty array list of songs
    public Artist(String name, String artistId) {
        this.name = name;
        this.artistId = artistId;
        this.songs = new ArrayList<>();
    }

    // getter and setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter, for song array
    public ArrayList<Song> getSongs() {
        return songs;
    }

    // Getter and adder for individual songs in the songs ArrayList
    public Song getSong(String id) {
        Song returnSong = null;
        for (Song song : songs) {
            if (song.getId().equals(id)) {
                returnSong = song;
            }
        }
        return returnSong;
    }

    public void addSong(Song song) {
        this.songs.add(song);
    }
}
