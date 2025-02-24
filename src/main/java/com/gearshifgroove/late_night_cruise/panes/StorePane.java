package com.gearshifgroove.late_night_cruise.panes;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.scenes.Store.Artists;
import com.gearshifgroove.late_night_cruise.scenes.Store.Genres;
import com.gearshifgroove.late_night_cruise.scenes.Store.Home;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class StorePane extends BorderPane {
    public StorePane() {
        // Side menu UI
        VBox sideMenu = new VBox();
        sideMenu.setMinWidth(Const.WINDOW_WIDTH/4);
        sideMenu.setMaxWidth(Const.WINDOW_WIDTH/4);
        sideMenu.setSpacing(10);
        sideMenu.setBackground(new Background(new BackgroundFill(Color.rgb(250, 250, 250), CornerRadii.EMPTY, Insets.EMPTY)));
//        sideMenu.getChildren().addAll(new Text("Test"));
        VBox sizedBox = new VBox();
        sizedBox.setMinHeight(50);
        TextField search = new TextField("Search...");
//        search.setPrefWidth(sideMenu.getMinWidth()-sideMenu.getMinWidth()/2);
        Button home = new Button("Home");
        home.setMinWidth(sideMenu.getMinWidth());
        home.setMaxWidth(sideMenu.getMinWidth());
        Button genres = new Button("Browse Genres");
        genres.setMinWidth(sideMenu.getMinWidth());
        genres.setMaxWidth(sideMenu.getMinWidth());
        Button artists = new Button("Browse Artists");
        artists.setMinWidth(sideMenu.getMinWidth());
        artists.setMaxWidth(sideMenu.getMinWidth());
        sideMenu.getChildren().addAll(sizedBox, search, home, genres, artists);

        // Main Content
        Pane displayPane = new Pane(new Home());

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

        this.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setLeft(sideMenu);
        this.setCenter(displayPane);

    }
}
