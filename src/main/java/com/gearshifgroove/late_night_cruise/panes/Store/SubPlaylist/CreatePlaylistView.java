package com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.CustomUIElements.PaddingBox;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.*;
import com.gearshifgroove.late_night_cruise.panes.Store.Playlists;
import com.gearshifgroove.late_night_cruise.panes.Store.SongListing;
import com.gearshifgroove.late_night_cruise.panes.StorePane;
import javafx.geometry.Insets;
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
    private static ArrayList<Playlist> playlists;

    public CreatePlaylistView() {
        this.setPadding(new Insets(10, 10, 10, 10));

        playlists = UserLib.getPlaylists();

        VBox top = new VBox();
        Text title = new Text("Create a new playlist");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        title.setFill(Color.WHITE);
        top.getChildren().addAll(new PaddingBox(40, 0), title);
        this.setTop(top);

        VBox form = new VBox();
        TextField name = new TextField();
        name.setPromptText("Playlist name");
        name.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), null, null)));

        Button create = new Button("Create");
        create.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), null, null)));
        create.setTextFill(Color.WHITE);

        Button cancel = new Button("Cancel");
        cancel.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), null, null)));
        cancel.setTextFill(Color.WHITE);

        Button createTemplatedPlaylist = new Button("Create templated playlist (debug)");
        createTemplatedPlaylist.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), null, null)));
        createTemplatedPlaylist.setTextFill(Color.WHITE);

        HBox buttons = new HBox();
        buttons.getChildren().addAll(create, cancel, createTemplatedPlaylist);

        form.getChildren().addAll(new PaddingBox(80, 0), name, new PaddingBox(20, 0), buttons);
        this.setCenter(form);
        BorderPane.setAlignment(form, Pos.CENTER);
        form.setMinHeight(200);
        form.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 2);
        form.setAlignment(Pos.CENTER);
//        form.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        // Create a new playlist
        create.setOnAction(e -> {
           try {
//               ArrayList<Playlist> playlists = UserLib.getPlaylists();
//               for (Playlist playlist : playlists) {
//                   if (playlist.getName().equals(name.getText())) {
//                       name.setText(name.getText() + "(0)");
//                   }
//               }
               ObjectOutputStream createPlaylistObject = new ObjectOutputStream(new FileOutputStream("playlist.dat"));
               name.setText(checkDuplicates(name.getText()));
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
                playlists = UserLib.getPlaylists();
                name.setText(checkDuplicates(name.getText()));
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

    public static String checkDuplicates(String newPlaylistName) {
        for (Playlist playlist : playlists) {
            if (playlist.getName().equals(newPlaylistName)) {
                newPlaylistName = playlist.getName() + "(copy)";
                checkDuplicates(newPlaylistName);
            }
        }
        return newPlaylistName;
    }
}
