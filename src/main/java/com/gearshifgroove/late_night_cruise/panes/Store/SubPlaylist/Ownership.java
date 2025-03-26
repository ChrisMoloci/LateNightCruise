package com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Ownership {
    public static ArrayList<String> ownedSongs;

    public static ArrayList<String> getOwnedSongs() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("ownedSongs.txt"));
            ownedSongs = new ArrayList<>();
            String line = reader.readLine();
            if (line != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ", ");
                while (tokenizer.hasMoreTokens()) {
                    ownedSongs.add(tokenizer.nextToken());
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
