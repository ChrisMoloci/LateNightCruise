package com.gearshifgroove.late_night_cruise.panes.Store.Data;

import java.io.Serializable;

// Author(s): Christian Moloci

// Genres are created separately and added to artists
public class Genre implements Serializable {
    // Holds only the Genre name
    private String name;

    // Constructor
    public Genre(String name) {
        this.name = name;
    }

    // Getter and setter for Genre name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
