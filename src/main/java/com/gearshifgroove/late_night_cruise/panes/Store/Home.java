package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.panes.StorePane;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Home extends VBox {
    public Home() {
        int mainSectionWidth = Const.WINDOW_WIDTH - Const.WINDOW_WIDTH/4;
        this.setMinWidth(mainSectionWidth);
//        this.setMinHeight(Const.WINDOW_HEIGHT-Const.WINDOW_HEIGHT/4);
//        this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        Text title = new Text("Music Shop");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        title.setTextAlignment(TextAlignment.CENTER);
        this.getChildren().add(title);

        // Quick Access Buttons (genres, artists, etc)
        Text quickAccessButtons = new Text("Find your next jam");
        HBox quickAccessButtonsBox = new HBox();
        Button artists = new Button("Artists");
        artists.setMinWidth(mainSectionWidth/3);
        Button genres = new Button("Genres");
        genres.setMinWidth(mainSectionWidth/3);
        Button topSongs = new Button("Top Songs");
        topSongs.setMinWidth(mainSectionWidth/3);

        topSongs.setOnAction(e -> {
            StorePane.displayPane.getChildren().clear();
            StorePane.displayPane.getChildren().add(new TopSongs());
        });

        quickAccessButtonsBox.getChildren().addAll(artists, genres, topSongs);
        this.getChildren().add(quickAccessButtonsBox);


        // Browse Albums and music
    }
}
