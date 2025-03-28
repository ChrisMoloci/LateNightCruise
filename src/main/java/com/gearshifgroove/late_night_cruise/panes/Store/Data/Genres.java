package com.gearshifgroove.late_night_cruise.panes.Store.Data;

import java.util.ArrayList;
import java.util.Arrays;

// Author(s): Christian Moloci

// Instantiates our Genres, kinda like a genre DB
public class Genres {
    // Create an array list
    private static ArrayList<Genre> genres;

    // Add the genres to the array list
    static {
        genres = new ArrayList<>();
        genres.addAll(Arrays.asList(
            new Genre("Unknown Genre"),
            new Genre("Lofi"),
            new Genre("Electronic"),
            new Genre("Synthwave"),
            new Genre("Hip-Hop"),
            new Genre("Commercial"),
            new Genre("Inspirational"),
            new Genre("Piano"),
            new Genre("Dance"),
            new Genre("Emotional")
        ));
    }

    // Getter that returns a genre if found based on String passed in
    public static Genre getGenre(String genre) {
        Genre returnGenre = null;
        for (Genre gen : genres) {
            if (gen.getName().equals(genre)) {
                returnGenre = gen;
            }
        }
        return returnGenre;
    }

    // Getter to return the entire genre array list
    public static ArrayList<Genre> getGenres() {
        return genres;
    }
}
