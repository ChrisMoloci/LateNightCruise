package com.gearshifgroove.late_night_cruise.panes.Store.Data;

import javafx.scene.media.Media;

import java.io.File;
import java.io.Serializable;

public class Song implements Serializable {
    private String id;
//    private Media media;
    private String media;
    private Genre genre;
    private String songName;
    private String artist;

    public Song(String file, String genre, String songName, String artist) {
        this.id = file;
        // For mac
        // this.media = new Media(new File(getClass().getResource("/com/gearshifgroove/late_night_cruise/Songs/"+file+".wav").toExternalForm()).toString());
        // For Windows
//        this.media = new Media(new File("src/main/resources/com/gearshifgroove/late_night_cruise/Songs/"+file+".wav").toURI().toString());
        this.media = "src/main/resources/com/gearshifgroove/late_night_cruise/Songs/"+file+".wav";
        this.genre = Genres.getGenre(genre);
        this.songName = songName;
        this.artist = artist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtist() {
        return artist;
    }
}
