package com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.CustomUIElements.PaddingBox;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.*;
import com.gearshifgroove.late_night_cruise.panes.Store.Playlists;
import com.gearshifgroove.late_night_cruise.panes.Store.SongListing;
import com.gearshifgroove.late_night_cruise.panes.StorePane;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CreatePlaylistView extends BorderPane {
    public CreatePlaylistView() {
        Text title = new Text("Create a new playlist");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 50));
//        title.setTextAlignment(TextAlignment.CENTER);
        this.setTop(title);

        VBox form = new VBox();
        TextField name = new TextField();
        name.setPromptText("Playlist name");

        Button create = new Button("Create");

        Button cancel = new Button("Cancel");

        Button createTemplatedPlaylist = new Button("Create templated playlist (debug)");

        HBox buttons = new HBox();
        buttons.getChildren().addAll(create, cancel, createTemplatedPlaylist);

        form.getChildren().addAll(name, new PaddingBox(20), buttons);
        this.setCenter(form);
        BorderPane.setAlignment(form, Pos.CENTER);
        form.setMinHeight(200);
        form.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 2);
        form.setAlignment(Pos.CENTER);
//        form.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        // Create a new playlist
        create.setOnAction(e -> {
           try {
               ArrayList<Playlist> playlists = UserLib.getPlaylists();
               ObjectOutputStream createPlaylistObject = new ObjectOutputStream(new FileOutputStream("playlist.dat"));
               for (Playlist playlist : playlists) {
                   createPlaylistObject.writeObject(playlist);
               }
               if (!playlists.isEmpty()) {
                   System.out.println(playlists.get(UserLib.getPlaylists().size()-1).getId() + 1);
                   createPlaylistObject.writeObject(new Playlist(playlists.get(UserLib.getPlaylists().size()-1).getId() + 1, name.getText()));
                   System.out.println("Playlist created");
                   returnToPlaylists();
               }
               else {
                   createPlaylistObject.writeObject(new Playlist(1, name.getText()));
                   System.out.println("Playlist created");
                   returnToPlaylists();
               }
               createPlaylistObject.close();
           } catch (Exception exception) {
               exception.printStackTrace();
           }
        });

        cancel.setOnAction(e -> {
            returnToPlaylists();
        });

        // REMOVE IN PROD - FOR DEBUGGING - CREATES A PLAYLIST WITH EVERY SONG AVAILABLE
        createTemplatedPlaylist.setOnAction(e -> {
            try {
                ArrayList<Playlist> playlists = UserLib.getPlaylists();
                ObjectOutputStream createPlaylistObject = new ObjectOutputStream(new FileOutputStream("playlist.dat"));
                for (Playlist playlist : playlists) {
                    createPlaylistObject.writeObject(playlist);
                }
                if (!playlists.isEmpty()) {
                    Playlist debugPlaylist = new Playlist(playlists.get(UserLib.getPlaylists().size()-1).getId() + 1, name.getText());
                    for (Artist artist : DB.getArtists().values()) {
                        for (Song song : artist.getSongs()) {
                            System.out.println(song.getId());
                            debugPlaylist.addSong(song);
                        }
                    }
                    createPlaylistObject.writeObject(debugPlaylist);
                    System.out.println("Playlist created");
                    returnToPlaylists();
                }
                else {
                    Playlist debugPlaylist = new Playlist(1, name.getText());
                    for (Artist artist : DB.getArtists().values()) {
                        for (Song song : artist.getSongs()) {
                            System.out.println(song.getId());
                            debugPlaylist.addSong(song);
                        }
                    }
                    createPlaylistObject.writeObject(debugPlaylist);
                    System.out.println("Playlist created");
                    returnToPlaylists();
                }
                createPlaylistObject.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }

    public static void returnToPlaylists() {
        StorePane.displayPane.getChildren().clear();
        StorePane.displayPane.getChildren().add(new Playlists());
    }
}
