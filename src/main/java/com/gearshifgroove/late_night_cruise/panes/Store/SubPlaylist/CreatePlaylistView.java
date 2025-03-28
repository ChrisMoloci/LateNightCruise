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

// Author(s): Christian Moloci

public class CreatePlaylistView extends BorderPane {
    // Create a playlists array list
    private static ArrayList<Playlist> playlists;

    public CreatePlaylistView() {
        // Set the padding
        this.setPadding(new Insets(10, 10, 10, 10));

        // Populate the playlists ArrayList with the saved playlists
        playlists = UserLib.getPlaylists();

        // Create a VBox for the top and store the title (context for what to do), and a padding box for added spacing
        VBox top = new VBox();
        Text title = new Text("Create a new playlist");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        title.setFill(Color.WHITE);
        top.getChildren().addAll(new PaddingBox(40, 0), title);

        // Add the VBox to the top of the GridPane
        this.setTop(top);

        // Form stores all the nodes for creating the playlist and will be set to the center of the border pane
        VBox form = new VBox();

        // Create a text Box for naming the playlist
        TextField name = new TextField();
        name.setPromptText("Playlist name");
        name.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), null, null)));

        // Create button to submit the name
        Button create = new Button("Create");
        create.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), null, null)));
        create.setTextFill(Color.WHITE);

        // Cancel button to return back to the playlists view
        Button cancel = new Button("Cancel");
        cancel.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), null, null)));
        cancel.setTextFill(Color.WHITE);

//        Button createTemplatedPlaylist = new Button("Create templated playlist (debug)");
//        createTemplatedPlaylist.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), null, null)));
//        createTemplatedPlaylist.setTextFill(Color.WHITE);

        // HBox to store all the buttons
        HBox buttons = new HBox();
        buttons.getChildren().addAll(create, cancel);

        // Add all the playlist creating nodes to the form VBox and set it to the center of the BorderPane
        form.getChildren().addAll(new PaddingBox(80, 0), name, new PaddingBox(20, 0), buttons);
        this.setCenter(form);

        // Style the form layout a bit
        BorderPane.setAlignment(form, Pos.CENTER);
        form.setMinHeight(200);
        form.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 2);
        form.setAlignment(Pos.CENTER);

        // Button to create a new playlist
        create.setOnAction(e -> {
           try {
               // Create a new ObjectOutputStream
               ObjectOutputStream createPlaylistObject = new ObjectOutputStream(new FileOutputStream("playlist.dat"));
               // Check if another playlist with the same name exists and if it does, append (copy) to the end of its name
               name.setText(checkDuplicates(name.getText()));
               // Overwrite the old stored playlists with the same playlists
               for (Playlist playlist : playlists) {
                   createPlaylistObject.writeObject(playlist);
               }
               // if and else that handles what ids each playlist will get (if no playlists exists,
               // a starting ID must be set first in order for additional playlists to have ids higher than it
               // One the playlist is created, return back to the playlists screen
               if (!playlists.isEmpty()) {
                   System.out.println(playlists.get(UserLib.getPlaylists().size()-1).getId() + 1);
                   createPlaylistObject.writeObject(new Playlist(playlists.get(UserLib.getPlaylists().size()-1).getId() + 1, name.getText()));
                   System.out.println("Playlist created");
                   createPlaylistObject.close(); // Close the ObjectOutputStream
                   returnToPlaylists();
               }
               else {
                   createPlaylistObject.writeObject(new Playlist(1, name.getText()));
                   System.out.println("Playlist created");
                   createPlaylistObject.close(); // close the ObjectOutputStream
                   returnToPlaylists();
               }
               createPlaylistObject.close(); // Close the ObjectOutputStream
           } catch (Exception exception) {
               exception.printStackTrace();
           } // If an error occurs, log the error
        });

        // If the user presses the cancel button, return to the playlists view without creating a new playlist
        cancel.setOnAction(e -> {
            returnToPlaylists();
        });

//        // REMOVE IN PROD - FOR DEBUGGING - CREATES A PLAYLIST WITH EVERY SONG AVAILABLE
//        createTemplatedPlaylist.setOnAction(e -> {
//            try {
//                playlists = UserLib.getPlaylists();
//                name.setText(checkDuplicates(name.getText()));
//                ObjectOutputStream createPlaylistObject = new ObjectOutputStream(new FileOutputStream("playlist.dat"));
//                for (Playlist playlist : playlists) {
//                    createPlaylistObject.writeObject(playlist);
//                }
//                if (!playlists.isEmpty()) {
//                    Playlist debugPlaylist = new Playlist(playlists.get(UserLib.getPlaylists().size()-1).getId() + 1, name.getText());
//                    for (Artist artist : DB.getArtists().values()) {
//                        for (Song song : artist.getSongs()) {
//                            System.out.println(song.getId());
//                            debugPlaylist.addSong(song);
//                        }
//                    }
//                    createPlaylistObject.writeObject(debugPlaylist);
//                    System.out.println("Playlist created");
//                    returnToPlaylists();
//                }
//                else {
//                    Playlist debugPlaylist = new Playlist(1, name.getText());
//                    for (Artist artist : DB.getArtists().values()) {
//                        for (Song song : artist.getSongs()) {
//                            System.out.println(song.getId());
//                            debugPlaylist.addSong(song);
//                        }
//                    }
//                    createPlaylistObject.writeObject(debugPlaylist);
//                    System.out.println("Playlist created");
//                    returnToPlaylists();
//                }
//                createPlaylistObject.close();
//            } catch (Exception exception) {
//                exception.printStackTrace();
//            }
//        });
    }

    // Goes back to the playlists page
    public static void returnToPlaylists() {
        StorePane.displayPane.getChildren().clear();
        StorePane.displayPane.getChildren().add(new Playlists());
    }

    // Checks if a playlist with the same name exists and appends (copy) to the name if it does
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
