package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.*;
import com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist.CreatePlaylistView;
import com.gearshifgroove.late_night_cruise.panes.StorePane;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.*;

public class Playlists extends ListView {
    public Playlists() {
//        Playlist myPlaylist = new Playlist("0001", "myPlaylist");
//        Playlist anotherPlaylist = new Playlist("0001", "Playlist");
//////        myPlaylist.addSong(DB.getArtists().get("0001").getSongs().get(0));
////
////        this.getItems().add(new PlaylistListing(myPlaylist));
////
//        try {
//            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("playlist.dat"));
//            output.writeObject(myPlaylist);
//            output.writeObject(anotherPlaylist);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            ObjectInputStream input = new ObjectInputStream((new FileInputStream("playlist.dat")));
//            {
////                Playlist playlist = (Playlist) input.readObject();
////                Playlist playlist2 = (Playlist) input.readObject();
////                this.getItems().add(new PlaylistListing(playlist));
////                this.getItems().add(new PlaylistListing(playlist2));
//                while (true) {
//                    try {
//                        Playlist playlist = (Playlist) input.readObject();
//                        this.getItems().add(new PlaylistListing(playlist));
//                    } catch (Exception e) {
////                        e.printStackTrace();
//                        System.out.println("End of playlist dat file");
//                        break;
//                    }
//                }
//            }
////            System.out.println();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Button createPlaylist = new Button("Create Playlist");
        createPlaylist.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4);
        this.getItems().add(createPlaylist);

        createPlaylist.setOnAction(e -> {
            StorePane.displayPane.getChildren().clear();
            StorePane.displayPane.getChildren().add(new CreatePlaylistView());
        });

        for (Playlist playlist : UserLib.getPlaylists()) {
            this.getItems().add(new PlaylistListing(playlist));
        }

        this.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4);
        this.setMinHeight(Const.WINDOW_HEIGHT);
        this.setSelectionModel(null);
        this.setEditable(false);
//        this.setMouseTransparent(true);
        this.setFocusTraversable(false);

    }
}
