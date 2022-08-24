package edu.cs4730.livedataroomrecyclerdemo_kt

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

/**
 * shows how to use the room database with the AndroidViewModel
 * The viewmodel is the POJO that would normally be the database class with all the function.
 * in this case, just an add method.
 */
class MainActivity : AppCompatActivity() {
    lateinit var mRecyclerView: RecyclerView
    lateinit var mAdapter: myAdapter
    var TAG = "MainActivity"
    lateinit var scoreListViewModel: ScoreListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        //setup the viewmodel and the database (inside the view model)
        scoreListViewModel = ViewModelProvider(this)[ScoreListViewModel::class.java]

        //set the floating action button up.
        findViewById<View>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Adding data now", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            scoreListViewModel.addData(generateScores())
        }

        //set the recyclerview.
        mRecyclerView = findViewById(R.id.listtrans)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.itemAnimator = DefaultItemAnimator()
        mAdapter = myAdapter(scoreListViewModel, R.layout.highscore, this)
        //add the adapter to the recyclerview
        mRecyclerView.adapter = mAdapter

        //completely unnecessary, just using it to make sure everything is really working
        scoreListViewModel.scores.observe(
            this
        ) { Log.d(TAG, "Data has been added/changed, displaying") }
    }

    //this just generates some simple data to be inserted into the database.
    fun generateScores(): List<Score> {
        val FIRST = arrayOf(
            "Jim", "Fred", "Allyson", "Danny", "Shaya"
        )
        val SECOND = intArrayOf(
            3012, 56, 256, 1001, 2048
        )
        val scores: MutableList<Score> = ArrayList()
        for (i in FIRST.indices) {
            val score = Score(FIRST[i], SECOND[i])
            //score.setName(FIRST[i]);
            //score.setScore(SECOND[i]);
            scores.add(score)
            Log.d(TAG, "adding data item $i")
        }
        return scores
    }
}
