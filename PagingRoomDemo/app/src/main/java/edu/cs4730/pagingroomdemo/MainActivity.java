package edu.cs4730.pagingroomdemo;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

/*
 * this a example of using a recyclerview to display the data using a recyclerview PagedListAdapter
 * designed to o gradually load information as needed from a data source, without overloading the
 * device or waiting too long for a big database query.
 *
 * https://developer.android.com/topic/libraries/architecture/paging.html
 *
 * This example needs add/update/delete, but at least it provides a simple example to get paging working.
 */


public class MainActivity extends AppCompatActivity {

    ScoreAdapter adapter;
    myViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(myViewModel.class);

        RecyclerView mRecyclerView = findViewById(R.id.list);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //and default animator
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new ScoreAdapter(R.layout.my_row, this);

        //setup the viewModel with the database.
        viewModel.set(AppDatabase.getInstance(getBaseContext()).ScoreDao());
        //set the observer.
        viewModel.scoreList.observe(MainActivity.this, new Observer<PagedList<Score>>() {
                @Override
                public void onChanged(@Nullable PagedList<Score> scores) {
                    adapter.submitList(scores);
                }
            }
        );

        //make sure there is data in the database.  need a thread to insert.
        Thread myThread = new Thread() {
            public void run() {
                Log.d("MainActivity","starting up thread to add data");
              AppDatabase.AddData();

            }
        };
        myThread.start();

        mRecyclerView.setAdapter(adapter);

    }
}
