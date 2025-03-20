package com.gearshifgroove.late_night_cruise.panes.Store.Data;

import java.util.ArrayList;
import java.util.Arrays;

public class Genres {
    private static ArrayList<Genre> genres;

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

    public static Genre getGenre(String genre) {
        Genre returnGenre = null;
        for (Genre gen : genres) {
            if (gen.getName().equals(genre)) {
                returnGenre = gen;
            }
        }
        return returnGenre;
    }

    public static ArrayList<Genre> getGenres() {
        return genres;
    }
}
