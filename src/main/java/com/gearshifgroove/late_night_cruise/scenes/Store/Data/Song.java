package com.gearshifgroove.late_night_cruise.scenes.Store.Data;

import javafx.scene.media.Media;

import java.io.File;

public class Song {
    private String id;
    private Media media;
    private Genre genre;
    private String songName;

    public Song(String file, String genre, String songName) {
        this.id = file;
        this.media = new Media(new File(getClass().getResource("/com/gearshifgroove/late_night_cruise/Songs/"+file+".wav").toExternalForm()).toString());
        this.genre = Genres.getGenre(genre);
        this.songName = songName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
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
}
