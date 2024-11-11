package edu.cs4730.livedataroomrecyclerdemo_kt

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import edu.cs4730.livedataroomrecyclerdemo_kt.databinding.ActivityMainBinding

/**
 * shows how to use the room database with the AndroidViewModel
 * The viewmodel is the POJO that would normally be the database class with all the function.
 * in this case, just an add method.
 */
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mAdapter: myAdapter
    var TAG = "MainActivity"
    lateinit var scoreListViewModel: ScoreListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }
        setSupportActionBar(binding.toolbar)

        //setup the viewmodel and the database (inside the view model)
        scoreListViewModel = ViewModelProvider(this)[ScoreListViewModel::class.java]

        //set the floating action button up.
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Adding data now", Snackbar.LENGTH_LONG).setAction("Action", null)
                .show()
            scoreListViewModel.addData(generateScores())
        }

        //set the recyclerview.
        binding.myRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.myRecyclerView.itemAnimator = DefaultItemAnimator()
        mAdapter = myAdapter(scoreListViewModel, this)
        //add the adapter to the recyclerview
        binding.myRecyclerView.adapter = mAdapter

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
