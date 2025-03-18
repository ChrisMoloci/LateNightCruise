package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.CustomUIElements.PaddingBox;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Artist;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.DB;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Song;
import com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist.Ownership;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class ArtistPage extends ScrollPane {
    public ArtistPage(Artist artist) {
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, new Insets(0, 0, 0,0))));

        ArrayList<String> ownedSongs = Ownership.getOwnedSongs();

        VBox contents = new VBox();
        contents.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        Text artistName = new Text(artist.getName());
        artistName.setFill(Color.WHITE);
        artistName.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        GridPane songGrid = new GridPane();

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(25);

        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(25);

        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(25);

        songGrid.getColumnConstraints().addAll(col1, col2, col3, col4);

        ArrayList<Song> songs = artist.getSongs();

        int songIndex = 0;
        int songRow = 0;
        for (Song song : songs) {
            if ((songIndex % 4) == 0) {
                songRow++;
            }
            for (String ownedSong : ownedSongs) {
                if (ownedSong.equals(song.getId())) {
                    songGrid.add(new GridBasedSongListing(song, true), songIndex, songRow);
                } else {
                    songGrid.add(new GridBasedSongListing(song, false), songIndex, songRow);
                }
            }
            songIndex++;
        }

//        for (int i = 0; i < 20; i++) {
//            for (int j = 0; j < 4; j++) {
//                System.out.println(i);
//                songGrid.add(new GridBasedSongListing(DB.getArtists().get("0001").getSong("0001"), false), j, i);
//            }
//        }

        contents.getChildren().addAll(new PaddingBox(40, 0), artistName, new PaddingBox(30, 0), songGrid);

        this.setContent(contents);


        this.setMaxSize((Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4) + 1, Const.WINDOW_HEIGHT);
        this.setMinWidth((Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4) + 1);
    }
}
