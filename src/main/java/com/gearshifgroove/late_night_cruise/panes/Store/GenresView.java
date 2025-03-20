package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Genre;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Genres;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GenresView extends ScrollPane {
    public GenresView() {
        ArrayList<Genre> genres = Genres.getGenres();

        this.getChildren().add(new GenreListing(Genres.getGenre("Unknown Genre")));

        VBox content = new VBox();
        content.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

//        for (int i = 0; i < 20; i++) {
//            content.getChildren().add(new GenreListing(Genres.getGenre("Unknown Genre")));
//        }

        for (Genre genre : genres) {
            content.getChildren().add(new GenreListing(genre));
        }

        this.setContent(content);

        this.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4);
        this.setMaxHeight(Const.WINDOW_HEIGHT);
    }
}
