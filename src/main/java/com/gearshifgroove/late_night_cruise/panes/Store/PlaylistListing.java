package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.GlobalPlayer;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Playlist;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Song;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.BufferedWriter;
import java.io.FileWriter;

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

        ColumnConstraints play = new ColumnConstraints();
        play.setPercentWidth(20);
        ColumnConstraints playlistInfo = new ColumnConstraints();
        playlistInfo.setPercentWidth(80);

        this.getColumnConstraints().addAll(play, playlistInfo);

        Button selectButton = new Button("Select Playlist");
        selectButton.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        selectButton.setTextFill(Color.WHITE);

        Text playlistName = new Text(playlist.getName());
        playlistName.setFill(Color.WHITE);

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
        });
//        Text artistName = new Text("Artist: ");

//        playButton.setOnAction(e -> {
//            GlobalPlayer.changeSong(song.getMedia());
//        });

        this.add(selectButton, 0, 0, 1, 1);
        this.add(playlistName, 1, 0);
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
