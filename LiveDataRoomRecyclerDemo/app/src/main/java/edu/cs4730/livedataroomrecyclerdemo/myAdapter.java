package edu.cs4730.livedataroomrecyclerdemo;

import androidx.lifecycle.Observer;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * this adapter is very similar to the adapters used for listview, except a ViewHolder is required
 * see http://developer.android.com/training/improving-layouts/smooth-scrolling.html
 * except instead having to implement a ViewHolder, it is implemented within
 * the adapter.
 * <p>
 * This code has a ViewModel/LiveData and the observer is set in the adapter, so it will update on it's
 * own without the need to notify that the database has been changed.
 */

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {

    private List<Score> myList;
    private int rowLayout;
    //private  AppCompatActivity activity;  only need it once.
    private final String TAG = "myAdapter";
    private ScoreListViewModel mViewModel;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mName;
        public TextView mScore;

        private final String TAG = "ViewHolder";

        public ViewHolder(View view) {
            super(view);
            mName = view.findViewById(R.id.name);
            mScore = view.findViewById(R.id.score);
        }
    }

    //constructor
    public myAdapter(ScoreListViewModel scoreListViewModel, int rowLayout, AppCompatActivity activity) {
        mViewModel = scoreListViewModel;
        this.rowLayout = rowLayout;
        mViewModel.getScores().observe(activity, new Observer<List<Score>>() {
            @Override
            public void onChanged(@Nullable List<Score> scores) {
                Log.d("ntAdatper", "Data has been added/changed, displaying");
                myList = scores;
                notifyDataSetChanged();
            }
        });
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Score entry = myList.get(i);

        viewHolder.mName.setText(entry.getName());
        viewHolder.mName.setTag(i);  //sample data to show.
        viewHolder.mScore.setText(String.valueOf(entry.getScore()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }
}
