package com.gearshifgroove.late_night_cruise.panes.Store.Data;

import java.util.ArrayList;
import java.util.Arrays;

public class Genres {
    private static ArrayList<Genre> genres;

    static {
        genres = new ArrayList<>();
        genres.addAll(Arrays.asList(
            new Genre("Unknown Genre"),
            new Genre("Lofi")
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
}
