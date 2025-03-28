package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.GlobalPlayer;
import com.gearshifgroove.late_night_cruise.LateNightCruise;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.DB;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Playlist;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Song;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.UserLib;
import com.gearshifgroove.late_night_cruise.panes.StorePane;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PlaylistListing extends GridPane {
    private static Playlist playlist;
    public PlaylistListing(Playlist playlist) {
        this.playlist = playlist;

        this.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), new CornerRadii(10), new Insets(3, 5, 3, 5))));
        this.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(10), null, new Insets(3, 5, 3, 5))));
        this.setPadding(new Insets(10, 10, 10, 10));
//        this.setOnMouseClicked(event -> {
//            System.out.println("Playlist: " + playlist.getName());
//        });
//        this.getChildren().add(new Text(playlist.getName()));
//        this.add(new Button("Play Now"), 0, 0);
//        this.add(new Text("Playlist"), 1, 1);

        ColumnConstraints select = new ColumnConstraints();
        select.setPercentWidth(20);
        ColumnConstraints playlistInfo = new ColumnConstraints();
        playlistInfo.setPercentWidth(64);
        ColumnConstraints delete = new ColumnConstraints();
        delete.setPercentWidth(8);
        ColumnConstraints view = new ColumnConstraints();
        view.setPercentWidth(8);

        this.getColumnConstraints().addAll(select, playlistInfo, delete, view);

        Button selectButton = new Button("Select Playlist");
        selectButton.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(25), null, new Insets(3, 0, 3, 3))));
        selectButton.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(25), null)));
        selectButton.setTextFill(Color.WHITE);

        Text playlistName = new Text(playlist.getName());
        playlistName.setFill(Color.WHITE);

        Button deleteButton = new Button("X");
        deleteButton.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(25), null, new Insets(3, 0, 3, 3))));
        deleteButton.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(25), null)));
        deleteButton.setTextFill(Color.WHITE);

        Button viewButton = new Button(">");
        viewButton.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(25), null, new Insets(3, 0, 3, 3))));
        viewButton.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(25), null)));
        viewButton.setTextFill(Color.WHITE);

        deleteButton.setOnAction(e -> {
            ArrayList<Playlist> oldPlaylists = UserLib.getPlaylists();
            try {
                ObjectOutputStream excludePlaylistObjectWriter = new ObjectOutputStream(new FileOutputStream("playlist.dat"));
                for (Playlist p : oldPlaylists) {
                    if (!p.getName().equals(playlist.getName())) {
                        excludePlaylistObjectWriter.writeObject(p);
                    }
                }
                excludePlaylistObjectWriter.close();
                if (GlobalPlayer.selectedPlaylist.getName().equals(playlist.getName())) {
                    GlobalPlayer.selectedPlaylist = DB.demoPlaylist;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            StorePane.displayPane.getChildren().clear();
            StorePane.displayPane.getChildren().add(new Playlists());
        });

        selectButton.setOnAction(e -> {
            System.out.println("Songs in playlist: ");
            for (Song song : playlist.getSongs()) {
                System.out.println(song.getSongName());
            }
//            GlobalPlayer.playPlaylist(playlist.getSongs(), 0);
//            GlobalPlayer.selectedPlaylist = playlist.getSongs();
            GlobalPlayer.selectedPlaylist = playlist;
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("selectedPlaylist.txt"));
                writer.write(playlist.getName());
                writer.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
//            StorePane.selectedPlaylistIndicator = new Text(playlist.getName());
        });


        viewButton.setOnAction(e -> {
            StorePane.displayPane.getChildren().clear();
            StorePane.displayPane.getChildren().add(new PlaylistSongs(playlist));
        });

        this.add(selectButton, 0, 0, 1, 1);
        this.add(playlistName, 1, 0);
        this.add(deleteButton, 2, 0, 1, 1);
        this.add(viewButton, 3, 0, 1, 1);
//        this.add(artistName, 1, 1);
//        this.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH/4);

        this.setMaxWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4);
        this.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4 - 18);
    }

    public Playlist getPlaylist() {
        return playlist;
    }
    public void test() {
        System.out.println("Hello World");
    }
}
