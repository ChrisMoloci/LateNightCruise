package com.gearshifgroove.late_night_cruise;

import java.io.*;

// Author(s): Christian Moloci

// Gets the users score from the scores.txt file and allows for modifications to it
public class ScoreSystem {
    // Gets the store scoew
    public static int getStoredScore() {
        // Score var that is initialized
        int score = 0;
        // Try getting the score
        try {
            // Create a BufferedReader to work with the scores.txt file
            BufferedReader reader = new BufferedReader(new FileReader("scores.txt"));
            // Read the first line
            String scoreLine = reader.readLine();
            // Parse that line as an int and store it in score
            score = Integer.parseInt(scoreLine);
            // Close the BufferedReader
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } // Runs if an error occurs
        //return the score
        return score;
    }

    // Overwrites the stored score with a new score value
    public static void updateStoredScore(int score) {
        // Try reading the score form scores.txt
        try {
            // Create a BufferedWriter to work with scores.txt
            BufferedWriter writer = new BufferedWriter(new FileWriter("scores.txt"));
            // Overwrite the file with the new value
            writer.write(Integer.toString(score));
            // close the BufferedWriter
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } // Runs if an error occurs
    }
}
