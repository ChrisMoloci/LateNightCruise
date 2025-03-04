package com.gearshifgroove.late_night_cruise.panes.Store.Data;

import java.io.Serializable;
import java.util.ArrayList;

public class Playlist implements Serializable {
    private int id;
    private String name;
    private ArrayList<Song> songs;

    public Playlist(int id, String name) {
        this.id = id;
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        this.songs.add(song);
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
