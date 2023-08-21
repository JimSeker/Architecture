package edu.cs4730.livedataroomdemo_kt

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import edu.cs4730.livedataroomdemo_kt.databinding.ActivityMainBinding

/**
 * This is a simple example of how the room architecture to create a simple database.
 * It create a score Score  Plain Old Java Object (entity), a data access object (dao), and roomDatabase.
 * It then uses the liveData objects to get the data, instead of having to use threads to retrieve the data.
 * When data is changed or add the liveData is notified and will display the data via the observer, so
 * there is no "display button", since it's not needed.
 *
 * The traditional class that holds all the database callslike the query, inserts, etc, is replaced
 * with a viewmodel with observers.  so the data can be updated in the widgets as the db changes.
 *
 */
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var TAG = "MainActivity"
    lateinit var scoreListViewModel: ScoreListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        //setup the viewmodel and the database (inside the view model)
        scoreListViewModel = ViewModelProvider(this)[ScoreListViewModel::class.java]
        scoreListViewModel.scores.observe(
            this
        ) { scores ->
            logthis("Data has been added/changed, displaying")
            if (scores != null) {
                for (score in scores) {
                    val data = "id=" + score.id.toString() + " name=" + score.getName()
                        .toString() + " score=" + score.getScore()
                    logthis(data)
                }
            } else {
                logthis("There is no data!!!")
            }
        }
       binding.btnAdd.setOnClickListener {
            scoreListViewModel.addData(
                generateScores()
            )
        }
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
            logthis("adding data item $i")
        }
        return scores
    }

    //simple logger function to both the debug and logger textview.
    fun logthis(item: String) {
        if (item.compareTo("") != 0) {
            Log.d(TAG, item)
            //We are likely not the main UI thread, so lets get there.
            runOnUiThread {
                binding.logger.append(item + "\n")
            }
        }
    }
}
