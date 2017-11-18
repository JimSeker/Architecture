package edu.cs4730.roomdemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase ad = AppDatabase.getInstance(this);

        ScoreListViewModel scoreListViewModel = new ScoreListViewModel(ad);
        scoreListViewModel.getScores().observe(this, new Observer<List<Score>>() {
            @Override
            public void onChanged(@Nullable List<Score> scores) {
                if (scores != null) {
                    for (Score score : scores) {
                        String data = "id=" + score.getId() + " name=" + score.getName() + " score" + score.getScore();
                        Log.d("ROOM", data);
                    }
                } else {
                    Log.d("ROOM", "There is no data!!!");
                }
            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

/*
//        LiveData<List<Score>> stuff = myDR.getClass();
                List<Score> scores = ad.ScoreDao().loadAllScores().getValue();
                //List<Score> scores = mRepository.getScores().getValue();
                if (scores != null) {
                    for (Score score : scores) {
                        String data = "id=" + score.getId() + " name=" + score.getName() + " score" + score.getScore();
                        Log.d("ROOM", data);
                    }
                } else {
                    Log.d("ROOM", "There is no data!!!");
                }
*/
            }

        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread myThread = new Thread() {
                    public void run() {
                        ad.ScoreDao().insertAll(DataGenerator.generateScores());
                    }
                };
                myThread.start();
            }
        });

    }


}
