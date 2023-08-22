package edu.cs4730.livedataroomrecyclerdemo_kt

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import edu.cs4730.livedataroomrecyclerdemo_kt.databinding.HighscoreBinding

/**
 * this adapter is very similar to the adapters used for listview, except a ViewHolder is required
 * see http://developer.android.com/training/improving-layouts/smooth-scrolling.html
 * except instead having to implement a ViewHolder, it is implemented within
 * the adapter.
 *
 * This code has a ViewModel/LiveData and the observer is set in the adapter, so it will update on it's
 * own without the need to notify that the database has been changed.
 */
class myAdapter(
    private val mViewModel: ScoreListViewModel,
    activity: AppCompatActivity
) : RecyclerView.Adapter<myAdapter.ViewHolder>() {
    private var myList: List<Score>? = null

    //private  AppCompatActivity activity;  only need it once.
    private val TAG = "myAdapter"

    //the viewbinding now provides the references.
    class ViewHolder(var viewBinding: HighscoreBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {}

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = HighscoreBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false);
        return ViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val entry = myList!![i]
        viewHolder.viewBinding.name.text = entry.getName()
        viewHolder.viewBinding.name.tag = i //sample data to show.
        viewHolder.viewBinding.score.text = entry.getScore().toString()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return myList?.size ?: 0
    }

    //constructor
    init {
        mViewModel.scores.observe(activity) { scores ->
            Log.d("ntAdatper", "Data has been added/changed, displaying")
            myList = scores
            notifyDataSetChanged()
        }
    }
}