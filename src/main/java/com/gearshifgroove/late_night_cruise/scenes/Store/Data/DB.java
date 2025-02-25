package com.gearshifgroove.late_night_cruise.scenes.Store.Data;

import java.util.HashMap;

public class DB {
    private static HashMap<String, Artist> artists = new HashMap<>();

    static {
        artists = new HashMap<>();

        try {
            artists.put("0001", new Artist("Christian Moloci", "0001"));
            artists.get("0001").addSong(new Song("0001", "Lofi", "Intro Song"));
            artists.get("0001").addSong(new Song("0002", "Unknown Genre", "Debug Song"));
        } catch (Exception e) {
            System.out.println("An error occured while initilizing music database");
            e.printStackTrace();
        }
    }

    public static HashMap<String, Artist> getArtists() {
        return artists;
    }
}
