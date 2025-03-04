package com.gearshifgroove.late_night_cruise.panes.Store.Data;

import java.util.ArrayList;

public class Artist {
    private String name;
    private String artistId;
    private ArrayList<Song> songs;

    public Artist(String name, String artistId) {
        this.name = name;
        this.artistId = artistId;
        this.songs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

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
