package com.gearshifgroove.late_night_cruise.panes.Store.Data;

import java.util.HashMap;

public class DB {
    private static HashMap<String, Artist> artists = new HashMap<>();

    static {
        artists = new HashMap<>();

        try {
            artists.put("0000", new Artist("Unknown", "0000"));
            artists.get("0000").addSong(new Song("0004", "Unknown Genre", "Calm Corporate", artists.get("0000").getName()));
            artists.get("0000").addSong(new Song("0000", "Unknown Genre", "Coin", artists.get("0000").getName()));
            artists.get("0000").addSong(new Song("debug1", "Unknown Genre", "Bell", artists.get("0000").getName()));

            artists.put("0001", new Artist("Christian Moloci", "0001"));
            artists.get("0001").addSong(new Song("0001", "Lofi", "Intro Song", artists.get("0001").getName()));
            artists.get("0001").addSong(new Song("0002", "Unknown Genre", "Debug Song", artists.get("0001").getName()));

            artists.put("0002", new Artist("Aaron Kenny", "0002"));
            artists.get("0002").addSong(new Song("0003", "Unknown Genre", "Beneath the Moonlight", artists.get("0002").getName()));

            artists.put("0003", new Artist("Hazy", "0003"));
            artists.get("0003").addSong(new Song("0005", "Unknown Genre", "Drifting", artists.get("0003").getName()));

            artists.put("0004", new Artist("Purrple Cat", "0004"));
            artists.get("0004").addSong(new Song("0006", "Unknown Genre", "Golden Hour", artists.get("0004").getName()));
            artists.get("0004").addSong(new Song("0009", "Unknown Genre", "Nightfall", artists.get("0004").getName()));
            artists.get("0004").addSong(new Song("0012", "Unknown Genre", "Sonder", artists.get("0004").getName()));
            artists.get("0004").addSong(new Song("0017", "Unknown Genre", "Wildflowers", artists.get("0004").getName()));

            artists.put("0005", new Artist("Lakey Inspired", "0005"));
            artists.get("0005").addSong(new Song("0007", "Unknown Genre", "Better Days", artists.get("0005").getName()));

            artists.put("0006", new Artist("A Himitsu", "0006"));
            artists.get("0006").addSong(new Song("0008", "Unknown Genre", "Lost Within", artists.get("0006").getName()));

            artists.put("0007", new Artist("Keys of Moon", "0007"));
            artists.get("0007").addSong(new Song("0016", "Unknown Genre", "White Petals", artists.get("0007").getName()));

            artists.put("0008", new Artist("Luke Bergs", "0008"));
            artists.get("0008").addSong(new Song("0010", "Unknown Genre", "Dancin' in the Music Cloud", artists.get("0008").getName()));

            artists.put("0009", new Artist("Octilary", "0009"));
            artists.get("0009").addSong(new Song("0011", "Unknown Genre", "Chill Trap Beat", artists.get("0009").getName()));

            artists.put("0010", new Artist("FSM Team", "0010"));
            artists.get("0010").addSong(new Song("0013", "Unknown Genre", "Stay Positive", artists.get("0010").getName()));

            artists.put("0011", new Artist("Tokyo Music Walker", "0011"));
            artists.get("0011").addSong(new Song("0014", "Unknown Genre", "Way Home", artists.get("0011").getName()));

            artists.put("0012", new Artist("Relaxing Time", "0012"));
            artists.get("0012").addSong(new Song("0015", "Unknown Genre", "Whispers of Peaceful Serenade", artists.get("0012").getName()));

        } catch (Exception e) {
            System.out.println("An error occured while initilizing music database");
            e.printStackTrace();
        }
    }

    public static HashMap<String, Artist> getArtists() {
        return artists;
    }
}
