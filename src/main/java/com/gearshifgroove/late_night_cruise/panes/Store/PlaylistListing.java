package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.GlobalPlayer;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Playlist;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Song;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PlaylistListing extends GridPane {
    private static Playlist playlist;
    public PlaylistListing(Playlist playlist) {
        this.playlist = playlist;
//        this.setOnMouseClicked(event -> {
//            System.out.println("Playlist: " + playlist.getName());
//        });
//        this.getChildren().add(new Text(playlist.getName()));
//        this.add(new Button("Play Now"), 0, 0);
//        this.add(new Text("Playlist"), 1, 1);

        ColumnConstraints play = new ColumnConstraints();
        play.setPercentWidth(8);
        ColumnConstraints playlistInfo = new ColumnConstraints();
        playlistInfo.setPercentWidth(92);

        this.getColumnConstraints().addAll(play, playlistInfo);

        Button selectButton = new Button("Select Playlist");
        Text playlistName = new Text(playlist.getName());

        selectButton.setOnAction(e -> {
            for (Song song : playlist.getSongs()) {
                System.out.println(song.getSongName());
            }
        });
//        Text artistName = new Text("Artist: ");

//        playButton.setOnAction(e -> {
//            GlobalPlayer.changeSong(song.getMedia());
//        });

        this.add(selectButton, 0, 0, 1, 1);
        this.add(playlistName, 1, 0);
//        this.add(artistName, 1, 1);
        this.setBackground(new Background(new BackgroundFill(Color.rgb(240, 240, 240), null, null)));
//        this.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH/4);
    }

    public Playlist getPlaylist() {
        return playlist;
    }
    public void test() {
        System.out.println("Hello World");
    }
}
