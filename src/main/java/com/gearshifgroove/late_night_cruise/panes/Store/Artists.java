package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Artist;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.DB;
import com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist.Ownership;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class Artists extends ScrollPane {
    public Artists() {
//        this.getChildren().add(new Text("Artists"));
        HashMap<String, Artist> artists = DB.getArtists();

        VBox artistsBox = new VBox();

        for (Artist artist : artists.values()) {
            artistsBox.getChildren().add(new ArtistListing(artist));
        }

        this.setContent(artistsBox);

        this.setMaxSize(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4, Const.WINDOW_HEIGHT);
        this.setMinSize(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4, Const.WINDOW_HEIGHT);

//        this.getChildren().add(new ArtistListing(DB.getArtists().get("0000")));
    }
}
