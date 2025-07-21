package edu.cs4730.livedataroomdemo;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import edu.cs4730.livedataroomdemo.databinding.ActivityMainBinding;

/**
 * This is a simple example of how the room architecture to create a simple database.
 * It create a score Score  Plain Old Java Object (entity), a data access object (dao), and roomDatabase.
 * It then uses the liveData objects to get the data, instead of having to use threads to retrieve the data.
 * When data is changed or add the liveData is notified and will display the data via the observer, so
 * there is no "display button", since it's not needed.
 * <p>
 * The traditional class that holds all the database calls the query, inserts, etc, is replaced
 * with a viewmodel with observers.  so the data can be updated in the widgets as the db changes.
 */

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String TAG = "MainActivity";
    ScoreListViewModel scoreListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });
        //setup the viewmodel and the database (inside the view model)
        scoreListViewModel = new ViewModelProvider(this).get(ScoreListViewModel.class);

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


        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreListViewModel.addData(generateScores());
            }
        });
    }

    //this just generates some simple data to be inserted into the database.
    public List<Score> generateScores() {
        final String[] FIRST = new String[]{"Jim", "Fred", "Allyson", "Danny", "Shaya"};
        final int[] SECOND = new int[]{3012, 56, 256, 1001, 2048};

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
    void logthis(final String item) {
        if (item != null && item.compareTo("") != 0) {
            Log.d(TAG, item);
            //We are likely not the main UI thread, so lets get there.
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    binding.logger.append(item + "\n");
                }
            });
        }
    }
}
