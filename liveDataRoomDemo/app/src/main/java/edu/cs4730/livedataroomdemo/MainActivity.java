package edu.cs4730.livedataroomdemo;

import androidx.lifecycle.Observer;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a simple example of how the room architecture to create a simple database.
 * It create a score Score  Plain Old Java Object (entity), a data access object (dao), and roomDatabase.
 * It then uses the liveData objects to get the data, instead of having to use threads to retrieve the data.
 * When data is changed or add the liveData is notified and will display the data via the observer, so
 * there is no "display button", since it's not needed.
 * <p>
 * In MainActivity it opens the database
 */

public class MainActivity extends AppCompatActivity {

    AppDatabase db;
    TextView logger;
    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logger = findViewById(R.id.logger);

        AppDatabase ad = AppDatabase.getInstance(this);

        ScoreListViewModel scoreListViewModel = new ScoreListViewModel(ad);
        scoreListViewModel.getScores().observe(this, new Observer<List<Score>>() {
            @Override
            public void onChanged(@Nullable List<Score> scores) {
                logthis("Data has been added/changed, displaying");
                if (scores != null) {
                    for (Score score : scores) {
                        String data = "id=" + score.getId() + " name=" + score.getName() + " score=" + score.getScore();
                        logthis(data);
                    }
                } else {
                    logthis("There is no data!!!");
                }
            }
        });


        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread myThread = new Thread() {
                    public void run() {
                        logthis("Inserting data");
                        ad.ScoreDao().insertAll(generateScores());
                    }
                };
                myThread.start();
            }
        });
    }


    //this just generates some simple data to be inserted into the database.
    public List<Score> generateScores() {
        final String[] FIRST = new String[]{
            "Jim", "Fred", "Allyson", "Danny", "Shaya"};
        final int[] SECOND = new int[]{
            3012, 56, 256, 1001, 2048};

        List<Score> scores = new ArrayList<Score>();
        for (int i = 0; i < FIRST.length; i++) {
            Score score = new Score(FIRST[i], SECOND[i]);
            //score.setName(FIRST[i]);
            //score.setScore(SECOND[i]);
            scores.add(score);
            logthis("adding data item " + i);
        }
        return scores;
    }

    //simple logger function to both the debug and logger textview.
    void logthis(String item) {
        if (item != null && item.compareTo("") != 0) {
            Log.d(TAG, item);
            //We are likely not the main UI thread, so lets get there.
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    logger.append(item + "\n");
                }
            });
        }
    }
}
