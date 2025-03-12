package com.gearshifgroove.late_night_cruise;

import java.io.*;

public class ScoreSystem {
    public static int getStoredScore() {
        int score = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("scores.txt"));
            String scoreLine = reader.readLine();
            score = Integer.parseInt(scoreLine);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return score;
    }

    public static void updateStoredScore(int score) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("scores.txt"));
            writer.write(Integer.toString(score));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
