package com.gearshifgroove.late_night_cruise.panes.Store.Data;

import java.util.HashMap;

// Author(s): Christian Moloci

// Not for instantiation
// Sets up a hashmap to serve as the music Database
public class DB {
    // Create an artists hashmap
    private static HashMap<String, Artist> artists = new HashMap<>();
    // create a demo playlist for if the user has no playlist selected/created (typically for first time users)
    public static Playlist demoPlaylist;

    // Initialise the Database by adding all the data to the Hashmap when the program is run
    static {
        // Initialise artists HashMap
        artists = new HashMap<>();

        // try adding the data and return an error if failed
        try {
            artists.put("0000", new Artist("Unknown", "0000"));
            artists.get("0000").addSong(new Song("0004", "Commercial", "Calm Corporate", artists.get("0000").getName()));

            artists.put("0001", new Artist("Christian Moloci", "0001"));
            artists.get("0001").addSong(new Song("0001", "Lofi", "Intro Song", artists.get("0001").getName()));
            artists.get("0001").addSong(new Song("0002", "Inspirational", "Debug Song", artists.get("0001").getName()));
            artists.get("0001").addSong(new Song("0018", "Electronic", "Fresh Start", artists.get("0001").getName()));
            artists.get("0001").addSong(new Song("0019", "Hip-Hop", "Sonorous Chamber", artists.get("0001").getName()));
            artists.get("0001").addSong(new Song("0020", "Electronic", "Sunrise Over Detroit", artists.get("0001").getName()));
            artists.get("0001").addSong(new Song("0021", "Electronic", "Nocturnal Rhythm", artists.get("0001").getName()));
            artists.get("0001").addSong(new Song("0022", "Electronic", "Frog on a Log", artists.get("0001").getName()));
            artists.get("0001").addSong(new Song("0023", "Electronic", "Idea 1", artists.get("0001").getName()));
            artists.get("0001").addSong(new Song("0024", "Electronic", "Surfin in an Aqueduct", artists.get("0001").getName()));
            artists.get("0001").addSong(new Song("0025", "Electronic", "Playdate", artists.get("0001").getName()));
            artists.get("0001").addSong(new Song("0026", "Electronic", "Idea 2", artists.get("0001").getName()));
            artists.get("0001").addSong(new Song("0027", "Electronic", "Idea 3", artists.get("0001").getName()));
            artists.get("0001").addSong(new Song("0028", "Electronic", "Idea 4", artists.get("0001").getName()));
            artists.get("0001").addSong(new Song("0029", "Inspirational", "Idea 5", artists.get("0001").getName()));
            artists.get("0001").addSong(new Song("0030", "Electronic", "Aquatic Adventure", artists.get("0001").getName()));

            artists.put("0002", new Artist("Aaron Kenny", "0002"));
            artists.get("0002").addSong(new Song("0003", "Piano", "Beneath the Moonlight", artists.get("0002").getName()));

            artists.put("0003", new Artist("Hazy", "0003"));
            artists.get("0003").addSong(new Song("0005", "Unknown Genre", "Drifting", artists.get("0003").getName()));

            artists.put("0004", new Artist("Purrple Cat", "0004"));
            artists.get("0004").addSong(new Song("0006", "Lofi", "Golden Hour", artists.get("0004").getName()));
            artists.get("0004").addSong(new Song("0009", "Lofi", "Nightfall", artists.get("0004").getName()));
            artists.get("0004").addSong(new Song("0012", "Lofi", "Sonder", artists.get("0004").getName()));
            artists.get("0004").addSong(new Song("0017", "Lofi", "Wildflowers", artists.get("0004").getName()));

            artists.put("0005", new Artist("Lakey Inspired", "0005"));
            artists.get("0005").addSong(new Song("0007", "Lofi", "Better Days", artists.get("0005").getName()));

            artists.put("0006", new Artist("A Himitsu", "0006"));
            artists.get("0006").addSong(new Song("0008", "Electronic", "Lost Within", artists.get("0006").getName()));

            artists.put("0007", new Artist("Keys of Moon", "0007"));
            artists.get("0007").addSong(new Song("0016", "Piano", "White Petals", artists.get("0007").getName()));

            artists.put("0008", new Artist("Luke Bergs", "0008"));
            artists.get("0008").addSong(new Song("0010", "Dance", "Dancin' in the Music Cloud", artists.get("0008").getName()));

            artists.put("0009", new Artist("Octilary", "0009"));
            artists.get("0009").addSong(new Song("0011", "Hip-Hop", "Chill Trap Beat", artists.get("0009").getName()));

            artists.put("0010", new Artist("FSM Team", "0010"));
            artists.get("0010").addSong(new Song("0013", "Commercial", "Stay Positive", artists.get("0010").getName()));

            artists.put("0011", new Artist("Tokyo Music Walker", "0011"));
            artists.get("0011").addSong(new Song("0014", "Unknown Genre", "Way Home", artists.get("0011").getName()));

            artists.put("0012", new Artist("Relaxing Time", "0012"));
            artists.get("0012").addSong(new Song("0015", "Emotional", "Whispers of Peaceful Serenade", artists.get("0012").getName()));

        } catch (Exception e) {
            // If an error occurs, log it
            System.out.println("An error occured while initilizing music database");
            e.printStackTrace();
        }

        // Create the demo playlist and add 4 songs from the artist DB to it
        demoPlaylist = new Playlist(0, "Late Night Cruise");
        demoPlaylist.addSong(artists.get("0001").getSong("0018"));
        demoPlaylist.addSong(artists.get("0005").getSong("0007"));
        demoPlaylist.addSong(artists.get("0001").getSong("0019"));
        demoPlaylist.addSong(artists.get("0001").getSong("0022"));
    }

    // Getter for the DB
    public static HashMap<String, Artist> getArtists() {
        return artists;
    }
}
