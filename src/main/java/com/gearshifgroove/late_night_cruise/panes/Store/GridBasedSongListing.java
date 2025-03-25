package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.CustomUIElements.PaddingBox;
import com.gearshifgroove.late_night_cruise.GlobalPlayer;
import com.gearshifgroove.late_night_cruise.ScoreSystem;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Song;
import com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist.AddToPlaylistView;
import com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist.Ownership;
import com.gearshifgroove.late_night_cruise.panes.StorePane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GridBasedSongListing extends VBox {
    private boolean songOwned;

    public GridBasedSongListing(Song song, boolean owned) {
        songOwned = owned;

        this.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(16), null, new Insets(10, 4, 10, 0))));
        this.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), new CornerRadii(16), new Insets(10, 4, 10, 0))));
//        this.setPadding(new Insets(10, 10, 10, 10));
        this.setMinWidth(((Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4) / 4) - 4);
        this.setMaxWidth(((Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4) / 4) - 4);
        this.setPadding(new Insets(10, 0, 10, 0));

        Button playButton = new Button("Play");
        playButton.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(120), null, new Insets(3, 15, 3, 15))));
        playButton.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(120), new Insets(3, 15, 3, 15))));
        playButton.setTextFill(Color.WHITE);
        playButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        playButton.setMinWidth(((Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4) / 4) - 10);
        playButton.setMinHeight(90);

        Button addToPlaylistButton = new Button("");
        addToPlaylistButton.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(25), null, new Insets(3, 10, 3, 10))));
        addToPlaylistButton.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(25), new Insets(3, 10, 3, 10))));
        addToPlaylistButton.setTextFill(Color.WHITE);
        addToPlaylistButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        addToPlaylistButton.setMinWidth(((Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4) / 4) - 5);

        VBox songInfo = new VBox();
        songInfo.setPadding(new Insets(0, 0, 0, 5));

        Text songName = new Text(song.getSongName());
        songName.setFill(Color.WHITE);
        songName.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Text artistName = new Text(song.getArtist());
        artistName.setFill(Color.WHITE);
        artistName.setFont(Font.font("Arial", 12));

        songInfo.getChildren().addAll(songName, artistName);

        this.getChildren().addAll(playButton, addToPlaylistButton, new PaddingBox(15, 0), songInfo);
        playButton.setAlignment(Pos.CENTER);
        addToPlaylistButton.setAlignment(Pos.CENTER);

        if (songOwned) {
            addToPlaylistButton.setText("+");
        } else {
            addToPlaylistButton.setText("$10");
        }

        playButton.setOnAction(e -> {
            if (GlobalPlayer.getAudioMuteState()) {
                GlobalPlayer.changeAudioMuteState(false);
                GlobalPlayer.changeSong(song.getMedia());
                GlobalPlayer.changeAudioMuteState(true);
            } else {
                GlobalPlayer.changeSong(song.getMedia());
            }
        });

        System.out.println(songOwned);
        addToPlaylistButton.setOnAction(e -> {
            System.out.println(songOwned);
            if (songOwned) {
                StorePane.displayPane.getChildren().clear();
                StorePane.displayPane.getChildren().add(new AddToPlaylistView(song));
            }
            else {
                if (ScoreSystem.getStoredScore() >= 10) {
                    Ownership.addOwnedSongs(song.getId());
                    ScoreSystem.updateStoredScore(ScoreSystem.getStoredScore() - 10);
                    System.out.println("Bought song");
                    addToPlaylistButton.setText("+");
                    this.songOwned = true;
                    StorePane.coinCount.setText("$" + ScoreSystem.getStoredScore());
                }
            }
        });

    }
}
