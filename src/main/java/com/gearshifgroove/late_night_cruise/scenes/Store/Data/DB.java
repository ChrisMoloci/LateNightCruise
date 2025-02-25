package com.gearshifgroove.late_night_cruise.scenes.Store.Data;

import java.util.HashMap;

public class DB {
    private static HashMap<String, Artist> artists = new HashMap<>();

    static {
        artists = new HashMap<>();

        try {
            artists.put("0000", new Artist("Unknown", "0000"));
            artists.get("0000").addSong(new Song("0004", "Unknown Genre", "Calm Corporate"));

            artists.put("0001", new Artist("Christian Moloci", "0001"));
            artists.get("0001").addSong(new Song("0001", "Lofi", "Intro Song"));
            artists.get("0001").addSong(new Song("0002", "Unknown Genre", "Debug Song"));

            artists.put("0002", new Artist("Aaron Kenny", "0002"));
            artists.get("0002").addSong(new Song("0003", "Unknown Genre", "Beneath the Moonlight"));

            artists.put("0003", new Artist("Hazy", "0003"));
            artists.get("0003").addSong(new Song("0005", "Unknown Genre", "Drifting"));
        } catch (Exception e) {
            System.out.println("An error occured while initilizing music database");
            e.printStackTrace();
        }
    }

    public static HashMap<String, Artist> getArtists() {
        return artists;
    }
}
