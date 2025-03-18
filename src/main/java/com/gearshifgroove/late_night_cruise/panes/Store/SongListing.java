package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.GlobalPlayer;
import com.gearshifgroove.late_night_cruise.ScoreSystem;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Song;
import com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist.AddToPlaylistView;
import com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist.Ownership;
import com.gearshifgroove.late_night_cruise.panes.StorePane;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SongListing extends GridPane {
    private boolean songOwned;

    public SongListing(Song song, boolean owned) {
        this.songOwned = owned;
        this.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), null, null)));
        this.setPadding(new Insets(10, 0, 10, 0));
        this.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(10), null, new Insets(3, 5, 3, 5))));

        ColumnConstraints play = new ColumnConstraints();
        play.setPercentWidth(10);
        ColumnConstraints songInfo = new ColumnConstraints();
        songInfo.setPercentWidth(82);
        ColumnConstraints songActions = new ColumnConstraints();
        songActions.setPercentWidth(8);

        this.getColumnConstraints().addAll(play, songInfo, songActions);

        Button playButton = new Button("Play");
        playButton.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(25), null, new Insets(3, 0, 3, 3))));
        playButton.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(25), null)));
        playButton.setTextFill(Color.WHITE);

        Text songName = new Text(song.getSongName());
        songName.setFill(Color.WHITE);
        songName.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Text artistName = new Text("Artist: " + song.getArtist());
        artistName.setFill(Color.WHITE);
        artistName.setFont(Font.font("Arial", 12));

        Button addToPlaylistButton = new Button();
        addToPlaylistButton.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(25), null, new Insets(3, 0, 3, 0))));
        addToPlaylistButton.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(25), null)));
        addToPlaylistButton.setTextFill(Color.WHITE);

        if (songOwned) {
            addToPlaylistButton.setText("+");
        } else {
            addToPlaylistButton.setText("$10");
        }

        playButton.setOnAction(e -> {
            GlobalPlayer.changeSong(song.getMedia());
        });

        addToPlaylistButton.setOnAction(e -> {
            if (songOwned) {
                StorePane.displayPane.getChildren().clear();
                StorePane.displayPane.getChildren().add(new AddToPlaylistView(song));
            } else {
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

        this.add(playButton, 0, 0, 1, 2);
        this.add(songName, 1, 0);
        this.add(artistName, 1, 1);
        this.add(addToPlaylistButton, 2, 0, 1, 2);

        this.setMinWidth((Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4) - 18);
        this.setMaxWidth((Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4) - 18);
    }
}
