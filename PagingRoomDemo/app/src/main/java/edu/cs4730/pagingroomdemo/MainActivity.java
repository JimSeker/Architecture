package edu.cs4730.pagingroomdemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    ScoreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myViewModel viewModel = ViewModelProviders.of(this).get(myViewModel.class);

        RecyclerView mRecyclerView = findViewById(R.id.list);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //and default animator
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new ScoreAdapter(R.layout.my_row, this);


        Thread myThread = new Thread() {
            public void run() {
                Log.d("MainActivity","starting up thread to get data");
                viewModel.set(AppDatabase.getInstance(getBaseContext()).ScoreDao());
                viewModel.scoreList.observe(MainActivity.this, new Observer<PagedList<Score>>() {
                            @Override
                            public void onChanged(@Nullable PagedList<Score> scores) {
                                adapter.setList(scores);
                            }
                        }
                );
            }
        };
        myThread.start();

        mRecyclerView.setAdapter(adapter);

    }
}
