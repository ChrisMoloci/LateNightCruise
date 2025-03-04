package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Artist;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.DB;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Song;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class TopSongs extends ListView {
    public TopSongs() {
//        super(FXCollections.observableArrayList());
//        ArrayList<SongListing> songListings = new ArrayList<>();

        for (Artist artist : DB.getArtists().values()) {
            for (Song song : artist.getSongs()) {
                System.out.println(song.getId());
//                songListings.add(new SongListing(song));
                this.getItems().add(new SongListing(song));
            }
        }

        this.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4);
        this.setMinHeight(Const.WINDOW_HEIGHT);
        this.setSelectionModel(null);
        this.setEditable(false);
//        this.setMouseTransparent(true);
        this.setFocusTraversable(false);

        this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

//        this.setItems(FXCollections.observableList(songListings));
//        this.getChildren().add(new SongListing());

//        for (SongListing songListing : songListings) {
//            this.getChildren().add(songListing);
//        }
    }

}
