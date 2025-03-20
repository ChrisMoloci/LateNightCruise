package com.gearshifgroove.late_night_cruise.panes;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.CustomUIElements.PaddingBox;
import com.gearshifgroove.late_night_cruise.LateNightCruise;
import com.gearshifgroove.late_night_cruise.ScoreSystem;
import com.gearshifgroove.late_night_cruise.panes.Store.*;
import com.gearshifgroove.late_night_cruise.scenes.MainMenuScene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class StorePane extends BorderPane {
    public static Text coinCount;

    public static Pane displayPane = new Pane();
    public StorePane() {
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        // Side menu UI
        VBox sideMenu = new VBox();
        sideMenu.setMinWidth(Const.WINDOW_WIDTH/4);
        sideMenu.setMaxWidth(Const.WINDOW_WIDTH/4);
//        sideMenu.setSpacing(10);
        sideMenu.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        HBox menuAndCoinCount = new HBox();
        menuAndCoinCount.setMinWidth(sideMenu.getMinWidth());
        menuAndCoinCount.setMaxWidth(sideMenu.getMinWidth());

        Button back = new Button("Main Menu");
        back.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), CornerRadii.EMPTY, Insets.EMPTY)));
        back.setTextFill(Color.WHITE);
        back.setMinWidth(sideMenu.getMinWidth() - 40);
        back.setMaxWidth(sideMenu.getMinWidth() - 40);

        coinCount = new Text("$" + ScoreSystem.getStoredScore());
//        coinCount.setFont(Font.font("Arial", 20));
        coinCount.setFill(Color.WHITE);

        menuAndCoinCount.getChildren().addAll(back, new PaddingBox(0, 10), coinCount);

        Text store = new Text("Store");
        store.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        store.setFill(Color.WHITE);

        TextField search = new TextField("Search...");
        search.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), null, null)));

        Button home = new Button("Home");
        home.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), CornerRadii.EMPTY, Insets.EMPTY)));
        home.setTextFill(Color.WHITE);
        home.setMinWidth(sideMenu.getMinWidth());
        home.setMaxWidth(sideMenu.getMinWidth());

        Button genres = new Button("Browse Genres");
        genres.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), CornerRadii.EMPTY, Insets.EMPTY)));
        genres.setTextFill(Color.WHITE);
        genres.setMinWidth(sideMenu.getMinWidth());
        genres.setMaxWidth(sideMenu.getMinWidth());

        Button artists = new Button("Browse Artists");
        artists.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), CornerRadii.EMPTY, Insets.EMPTY)));
        artists.setTextFill(Color.WHITE);
        artists.setMinWidth(sideMenu.getMinWidth());
        artists.setMaxWidth(sideMenu.getMinWidth());

        Button songs = new Button("Browse Songs");
        songs.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), CornerRadii.EMPTY, Insets.EMPTY)));
        songs.setTextFill(Color.WHITE);
        songs.setMinWidth(sideMenu.getMinWidth());
        songs.setMaxWidth(sideMenu.getMinWidth());

        Button playlistsButton = new Button("Playlists");
        playlistsButton.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), CornerRadii.EMPTY, Insets.EMPTY)));
        playlistsButton.setTextFill(Color.WHITE);
        playlistsButton.setMinWidth(sideMenu.getMinWidth());
        playlistsButton.setMaxWidth(sideMenu.getMinWidth());

        Button debug = new Button("Debug");
        debug.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), CornerRadii.EMPTY, Insets.EMPTY)));
        debug.setTextFill(Color.WHITE);
        debug.setMinWidth(sideMenu.getMinWidth());
        debug.setMaxWidth(sideMenu.getMinWidth());

        sideMenu.getChildren().addAll(
                menuAndCoinCount, new PaddingBox(10, 0),
                store, new PaddingBox(10, 0),
                search, new PaddingBox(10, 0),
                home, genres, artists, songs, new PaddingBox(30, 0),
                playlistsButton, debug);

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
            displayPane.getChildren().add(new GenresView());
        });

        artists.setOnAction(e -> {
            displayPane.getChildren().clear();
            displayPane.getChildren().add(new Artists());
        });

        songs.setOnAction(e -> {
            displayPane.getChildren().clear();
            displayPane.getChildren().add(new TopSongs());
        });

        playlistsButton.setOnAction(e -> {
            displayPane.getChildren().clear();
           displayPane.getChildren().add(new Playlists());
        });

//        debug.setOnAction(e -> {
//            displayPane.getChildren().clear();
//            displayPane.getChildren().add(new AddToPlaylistView());
//        });

//        this.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setLeft(sideMenu);
        this.setCenter(displayPane);

    }
}
