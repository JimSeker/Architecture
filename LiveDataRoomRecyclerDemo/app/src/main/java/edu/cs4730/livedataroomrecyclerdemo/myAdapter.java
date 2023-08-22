package edu.cs4730.livedataroomrecyclerdemo;

import androidx.annotation.NonNull;
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

import edu.cs4730.livedataroomrecyclerdemo.databinding.HighscoreBinding;

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
    private final String TAG = "myAdapter";
    private ScoreListViewModel mViewModel;

    //the viewbinding now provides the references.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        HighscoreBinding viewBinding;
        public ViewHolder(HighscoreBinding view) {
            super(view.getRoot());
            viewBinding = view;
        }
    }

    //constructor
    public myAdapter(ScoreListViewModel scoreListViewModel,AppCompatActivity activity) {
        mViewModel = scoreListViewModel;
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        HighscoreBinding v = HighscoreBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Score entry = myList.get(i);

        viewHolder.viewBinding.name.setText(entry.getName());
        viewHolder.viewBinding.name.setTag(i);  //sample data to show.
        viewHolder.viewBinding.score.setText(String.valueOf(entry.getScore()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }
}
