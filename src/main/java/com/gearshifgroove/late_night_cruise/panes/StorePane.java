package com.gearshifgroove.late_night_cruise.panes;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.CustomUIElements.PaddingBox;
import com.gearshifgroove.late_night_cruise.LateNightCruise;
import com.gearshifgroove.late_night_cruise.panes.Store.Artists;
import com.gearshifgroove.late_night_cruise.panes.Store.Genres;
import com.gearshifgroove.late_night_cruise.panes.Store.Home;
import com.gearshifgroove.late_night_cruise.panes.Store.Playlists;
import com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist.AddToPlaylistView;
import com.gearshifgroove.late_night_cruise.scenes.GameScene;
import com.gearshifgroove.late_night_cruise.scenes.MainMenuScene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class StorePane extends BorderPane {
    public static Pane displayPane = new Pane();
    public StorePane() {
        // Side menu UI
        VBox sideMenu = new VBox();
        sideMenu.setMinWidth(Const.WINDOW_WIDTH/4);
        sideMenu.setMaxWidth(Const.WINDOW_WIDTH/4);
        sideMenu.setSpacing(10);
        sideMenu.setBackground(new Background(new BackgroundFill(Color.rgb(250, 250, 250), CornerRadii.EMPTY, Insets.EMPTY)));

        Button back = new Button("Back");
        back.setMinWidth(sideMenu.getMinWidth());
        back.setMaxWidth(sideMenu.getMinWidth());

        TextField search = new TextField("Search...");

        Button home = new Button("Home");
        home.setMinWidth(sideMenu.getMinWidth());
        home.setMaxWidth(sideMenu.getMinWidth());

        Button genres = new Button("Browse Genres");
        genres.setMinWidth(sideMenu.getMinWidth());
        genres.setMaxWidth(sideMenu.getMinWidth());

        Button artists = new Button("Browse Artists");
        artists.setMinWidth(sideMenu.getMinWidth());
        artists.setMaxWidth(sideMenu.getMinWidth());

        Button playlistsButton = new Button("Playlists");
        playlistsButton.setMinWidth(sideMenu.getMinWidth());
        playlistsButton.setMaxWidth(sideMenu.getMinWidth());

        Button debug = new Button("Debug");
        debug.setMinWidth(sideMenu.getMinWidth());
        debug.setMaxWidth(sideMenu.getMinWidth());

        sideMenu.getChildren().addAll(back, new PaddingBox(40), search, home, genres, artists, new PaddingBox(30), playlistsButton, debug);

        // Main Content
        displayPane = new Pane(new Home());
//        displayPane = new Pane(new Playlists());

        back.setOnAction(event -> {
           LateNightCruise.mainStage.setScene(new MainMenuScene());
        });


        home.setOnAction(e -> {
            displayPane.getChildren().clear();
            displayPane.getChildren().add(new Home());
        });

        genres.setOnAction(e -> {
            displayPane.getChildren().clear();
            displayPane.getChildren().add(new Genres());
        });

        artists.setOnAction(e -> {
            displayPane.getChildren().clear();
            displayPane.getChildren().add(new Artists());
        });

        playlistsButton.setOnAction(e -> {
            displayPane.getChildren().clear();
           displayPane.getChildren().add(new Playlists());
        });

//        debug.setOnAction(e -> {
//            displayPane.getChildren().clear();
//            displayPane.getChildren().add(new AddToPlaylistView());
//        });

        this.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setLeft(sideMenu);
        this.setCenter(displayPane);

    }
}
