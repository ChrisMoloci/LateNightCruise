package com.gearshifgroove.late_night_cruise.panes.Store.Data;

import java.io.Serializable;

public class Genre implements Serializable {
    private String name;

    public Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
