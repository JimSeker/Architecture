package edu.cs4730.livedataroomrecyclerdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.google.android.material.snackbar.Snackbar;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import edu.cs4730.livedataroomrecyclerdemo.databinding.ActivityMainBinding;


/**
 * shows how to use the room database with the AndroidViewModel
 * The viewmodel is the POJO that would normally be the database class with all the function.
 * in this case, just an add method.
 */

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    myAdapter mAdapter;
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
        setSupportActionBar(binding.toolbar);

        //setup the viewmodel and the database (inside the view model)
        scoreListViewModel = new ViewModelProvider(this).get(ScoreListViewModel.class);

        //set the floating action button up.
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Adding data now", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
                scoreListViewModel.addData(generateScores());

            }
        });

        //set the recyclerview.
        binding.myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.myRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new myAdapter(scoreListViewModel, this);
        //add the adapter to the recyclerview
        binding.myRecyclerView.setAdapter(mAdapter);

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
