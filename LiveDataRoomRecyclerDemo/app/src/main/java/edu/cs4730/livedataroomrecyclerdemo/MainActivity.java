package edu.cs4730.livedataroomrecyclerdemo;

import androidx.lifecycle.Observer;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    myAdapter mAdapter;
    String TAG = "MainActivity";
    AppDatabase ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Adding data now", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

                Thread myThread = new Thread() {
                    public void run() {
                        Log.d(TAG, "Inserting data");
                        ad.ScoreDao().insertAll(generateScores());
                    }
                };
                myThread.start();
            }
        });

        //get the database and a view model.
        ad = AppDatabase.getInstance(this);
        ScoreListViewModel scoreListViewModel = new ScoreListViewModel(ad);

        mRecyclerView = findViewById(R.id.listtrans);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new myAdapter(scoreListViewModel, R.layout.highscore, this);
        //add the adapter to the recyclerview
        mRecyclerView.setAdapter(mAdapter);


        //completely unnecessary, just using it to make sure everything is really working
        scoreListViewModel.getScores().observe(this, new Observer<List<Score>>() {
            @Override
            public void onChanged(@Nullable List<Score> scores) {
                Log.d(TAG, "Data has been added/changed, displaying");
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
            Log.d(TAG, "adding data item " + i);
        }
        return scores;
    }

}
