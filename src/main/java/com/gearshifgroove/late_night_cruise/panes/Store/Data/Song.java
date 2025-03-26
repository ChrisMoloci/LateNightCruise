package com.gearshifgroove.late_night_cruise.panes.Store.Data;

import javafx.scene.media.Media;

import java.io.File;
import java.io.Serializable;

// Author(s): Christian Moloci

// Songs are instantiated in DB and contain metadata as well as the reference to where the file can be found
public class Song implements Serializable {
    // Songs contain a unique ID, a media var, genre, songName, and artist name
    private String id;
    private String media;
    private Genre genre;
    private String songName;
    private String artist;

    // The constructor sets the file name, the genre, the songName and the artist
    public Song(String file, String genre, String songName, String artist) {
        this.id = file;
        // For mac
        // this.media = new Media(new File(getClass().getResource("/com/gearshifgroove/late_night_cruise/Songs/"+file+".wav").toExternalForm()).toString());
        // For Windows
//        this.media = new Media(new File("src/main/resources/com/gearshifgroove/late_night_cruise/Songs/"+file+".wav").toURI().toString());
        // Concatenates the file path with the file name
        this.media = "src/main/resources/com/gearshifgroove/late_night_cruise/Songs/"+file+".wav";
        this.genre = Genres.getGenre(genre);
        this.songName = songName;
        this.artist = artist;
    }

    // Getter and setter for ID
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter and setter for media
    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    //Getter and setter for genre
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    // getter and setter for song name
    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    // Getter for artist name
    public String getArtist() {
        return artist;
    }
}
