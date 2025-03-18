package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.*;
import com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist.CreatePlaylistView;
import com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist.Ownership;
import com.gearshifgroove.late_night_cruise.panes.StorePane;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.*;

public class Playlists extends VBox {
    public Playlists() {
        Button createPlaylist = new Button("Create Playlist");
        createPlaylist.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4);
        createPlaylist.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), null, null)));
        createPlaylist.setTextFill(Color.WHITE);
//        this.getItems().add(createPlaylist);
        this.getChildren().add(createPlaylist);

        ScrollPane scrollPane = new ScrollPane();
        VBox playlists = new VBox();
        createPlaylist.setOnAction(e -> {
            StorePane.displayPane.getChildren().clear();
            StorePane.displayPane.getChildren().add(new CreatePlaylistView());
        });

        for (Playlist playlist : UserLib.getPlaylists()) {
//            this.getItems().add(new PlaylistListing(playlist));
            playlists.getChildren().add(new PlaylistListing(playlist));
        }

        scrollPane.setContent(playlists);
        scrollPane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        scrollPane.setMinHeight(Const.WINDOW_HEIGHT);
        scrollPane.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4);
        this.setMaxWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4);
        this.getChildren().add(scrollPane);

        this.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4);
        this.setMaxWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4);
        this.setMinHeight(Const.WINDOW_HEIGHT);

//        this.setSelectionModel(null);
//        this.setEditable(false);
////        this.setMouseTransparent(true);
//        this.setFocusTraversable(false);

    }
}
