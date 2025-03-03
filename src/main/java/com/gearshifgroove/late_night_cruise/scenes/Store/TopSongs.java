package com.gearshifgroove.late_night_cruise.scenes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.GlobalPlayer;
import com.gearshifgroove.late_night_cruise.scenes.Store.Data.Artist;
import com.gearshifgroove.late_night_cruise.scenes.Store.Data.DB;
import com.gearshifgroove.late_night_cruise.scenes.Store.Data.Song;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class TopSongs extends VBox {
    public TopSongs() {
        ArrayList<SongListing> songListings = new ArrayList<>();

        for (Artist artist : DB.getArtists().values()) {
            for (Song song : artist.getSongs()) {
                System.out.println(song.getId());
                songListings.add(new SongListing(song));
            }
        }

        for (SongListing songListing : songListings) {
            this.getChildren().add(songListing);
        }
    }

}
