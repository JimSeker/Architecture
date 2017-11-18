package edu.cs4730.roomdemo;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Generates data to pre-populate the database
 */
public class DataGenerator {

    private static final String[] FIRST = new String[]{
        "Jim", "Fred", "Allyson", "Danny", "Shaya"};
    private static final int[] SECOND = new int[]{
        3012, 56, 256, 1001, 2048};


    public static List<Score> generateScores() {
        Log.d("generator", "adding data.");
        List<Score> scores = new ArrayList<>();
        for (int i = 0; i < FIRST.length; i++) {
            Score score = new Score();
            score.setId(i);
            score.setName(FIRST[i]);
            score.setScore(SECOND[i]);
            scores.add(score);
            Log.d("generator", " data. " + i);
        }
        return scores;
    }

}
