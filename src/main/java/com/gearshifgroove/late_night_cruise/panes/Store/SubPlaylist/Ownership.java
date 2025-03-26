package com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

// Author(s): Christian Moloci

// Handles the users song licenses
public class Ownership {
    // Create an array list of ownedSongs
    public static ArrayList<String> ownedSongs;

    // Returns all the songs that the user had purchased
    public static ArrayList<String> getOwnedSongs() {
        try {
            // Create a BufferedReader reading the songs in the ownedSongs.txt file
            BufferedReader reader = new BufferedReader(new FileReader("ownedSongs.txt"));
            // Init the ownedSongs ArrayList
            ownedSongs = new ArrayList<>();
            // line will store each line in the file
            String line = reader.readLine();
            // If the line is not null, we will manipulate the data
            if (line != null) {
                // Create a string tokenizer on line and delimit it by a comma
                StringTokenizer tokenizer = new StringTokenizer(line, ", ");
                // While theres more tokens, we will add the ids to the ownedSongs ArrayList
                while (tokenizer.hasMoreTokens()) {
                    ownedSongs.add(tokenizer.nextToken());
                }
            }
            reader.close(); // Close the BufferedReader
        } catch (IOException e) {
            e.printStackTrace();
        } // If an error occurs, log the error

        // Return the owned songs ArrayList
        return ownedSongs;
    }

    public static void addOwnedSongs(String song) {
//        ownedSongs.addAll(getOwnedSongs());
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("ownedSongs.txt", true));
            writer.write(song + ", ");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkOwnership(String song) {
        boolean status = false;
        for (String songToCheck : ownedSongs) {
            if (song.equals(songToCheck)) {
                status = true;
                break;
            } else {
                status = false;
            }
        }
        return status;
    }
}
