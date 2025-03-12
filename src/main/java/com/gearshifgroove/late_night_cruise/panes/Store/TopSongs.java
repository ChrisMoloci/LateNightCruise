package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Artist;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.DB;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Song;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class TopSongs extends ScrollPane {
    public TopSongs() {
        VBox topSongsBox = new VBox();

        for (Artist artist : DB.getArtists().values()) {
            for (Song song : artist.getSongs()) {
                System.out.println(song.getId());
//                this.getItems().add(new SongListing(song));
                topSongsBox.getChildren().add(new SongListing(song));
            }
        }

        this.setContent(topSongsBox);
        this.setMaxSize(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4, Const.WINDOW_HEIGHT);
//        this.setMinSize(Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);

//
//        this.getItems().get(0);
//
//        this.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), null, null)));
//        this.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4);
//        this.setMinHeight(Const.WINDOW_HEIGHT);
////        this.setSelectionModel(null);
////        this.setEditable(false);
////        this.setFocusTraversable(false);
//
//        this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
//        this.setFixedCellSize(USE_COMPUTED_SIZE);
    }

}
