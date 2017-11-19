package edu.cs4730.pagingroomdemo;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * hope this works.
 */

public class ScoreAdapter extends PagedListAdapter<Score, ScoreAdapter.ViewHolder> {

    private int rowLayout;
    private Context mContext;

    ScoreAdapter(int rowLayout, Context context) {
        super(DIFF_CALLBACK);
        this.rowLayout = rowLayout;
        this.mContext = context;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Score score = getItem(position);
        if (score != null) {
            holder.myName.setText(score.name);
            holder.myScore.setText(String.valueOf(score.score));
        } else {
            // Null defines a placeholder item - PagedListAdapter will automatically invalidate
            // this row when the actual object is loaded from the database
            holder.myName.setText("");
            holder.myScore.setText("");
        }
    }
    private static final DiffCallback<Score> DIFF_CALLBACK = new DiffCallback<Score>() {
        @Override
        public boolean areItemsTheSame(@NonNull Score oldUser, @NonNull Score newUser) {
            // User properties may have changed if reloaded from the DB, but ID is fixed
            return oldUser.getId() == newUser.getId();
        }
        @Override
        public boolean areContentsTheSame(@NonNull Score oldUser, @NonNull Score newUser) {
            // NOTE: if you use equals, your object must properly override Object#equals()
            // Incorrectly returning false here will result in too many animations.
            return oldUser.equals(newUser);
        }
    };

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView myName;
        private TextView myScore;


        ViewHolder(View itemView) {
            super(itemView);
            myName = itemView.findViewById(R.id.tv_name);
            myScore =  itemView.findViewById(R.id.tv_score);

        }
    }

}
