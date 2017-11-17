package edu.cs4730.roomdemo2;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

//https://developer.android.com/training/data-storage/room/index.html

public class MainActivity extends AppCompatActivity {
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(),
            AppDatabase.class, "database-name").build();


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread myThread = new Thread() {
                    public void run() {
                        List<Score> scores = db.ScoreDao().loadScore(3012);
                        if (scores != null) {
                            for (Score score : scores) {
                                String data = "id=" + score.getId() + " name=" + score.getName() + " score" + score.getScore();
                                Log.d("ROOM", data);
                            }
                        } else {
                            Log.d("ROOM", "no data loadScore ");

                        }

                        scores = db.ScoreDao().loadAllScores();//myDR.getScores().getValue();
                        //List<Score> scores = mRepository.getScores().getValue();
                        if (scores != null) {
                            for (Score score : scores) {
                                String data = "id=" + score.getId() + " name=" + score.getName() + " score=" + score.getScore();
                                Log.d("ROOM", data);
                            }
                        } else {
                            Log.d("ROOM", "There is no data!!!");
                        }

                    }
                };
                myThread.start();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread myThread = new Thread() {
                    public void run() {
                        db.ScoreDao().insertAll(generateScores());
                    }
                };
                myThread.start();
            }

            ;
        });

    }


    public static List<Score> generateScores() {
        final String[] FIRST = new String[]{
            "Jim", "Fred", "Allyson", "Danny", "Shaya"};
        final int[] SECOND = new int[]{
            3012, 56, 256, 1001, 2048};

        Log.d("generator", "adding data.");
        List<Score> scores = new ArrayList<Score>();
        for (int i = 0; i < FIRST.length; i++) {
            Score score = new Score(i, FIRST[i], SECOND[i]);
            //score.setId(i);
            //score.setName(FIRST[i]);
            //score.setScore(SECOND[i]);
            scores.add(score);
            Log.d("generator", " data. " + i);
        }
        return scores;
    }


}
