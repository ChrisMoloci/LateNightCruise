package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Artist;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.DB;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Genre;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Song;
import com.gearshifgroove.late_night_cruise.panes.StorePane;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class GenreListing extends GridPane {
    private ArrayList<Song> filteredSongs;
    private Genre genre;

    public GenreListing(Genre genre) {
        filteredSongs = new ArrayList<>();
        this.genre = genre;

        for (Artist artist : DB.getArtists().values()) {
            for (Song song : artist.getSongs()) {
                if (song.getGenre().getName().equals(genre.getName())) {
                    filteredSongs.add(song);
                }
            }
        }

        this.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), null, null)));
        this.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(10), null, new Insets(3, 5, 3, 5))));
        this.setPadding(new Insets(10, 0, 10, 10));

        ColumnConstraints artistInfo = new ColumnConstraints();
        artistInfo.setPercentWidth(92);
        ColumnConstraints artistSelect = new ColumnConstraints();
        artistSelect.setPercentWidth(8);

        this.getColumnConstraints().addAll(artistInfo, artistSelect);

        Text title = new Text(genre.getName());
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        title.setFill(Color.WHITE);

        Button viewGenre = new Button(">");
        viewGenre.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(25), null, new Insets(3, 0, 3, 0))));
        viewGenre.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(25), null)));
        viewGenre.setTextFill(Color.WHITE);
//        viewGenre.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        viewGenre.setOnAction(e -> {
            StorePane.displayPane.getChildren().clear();
            StorePane.displayPane.getChildren().add(new GenrePage(filteredSongs));
        });

        this.add(title, 0, 0);
        this.add(viewGenre, 1, 0);

        this.setMinWidth((Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4) - 18);
        this.setMaxWidth((Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4) - 18);
    }
    public Genre getGenre() {
        return genre;
    }
}
