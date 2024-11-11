package edu.cosc4730.roomdemo_kt

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.cosc4730.roomdemo_kt.databinding.ActivityMainBinding


/**
 * This is a simple example of how the room architecture to create a simple database.
 * It create a score Score  Plain Old Java Object (entity), a data access object (dao), and roomDatabase.
 *
 * In MainActivity it opens the database, then in threads data can be added and displayed.
 *
 */
class MainActivity : AppCompatActivity() {
    lateinit var db: AppDatabase
    lateinit var binding: ActivityMainBinding
    var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }
        //get an instance of the database, which is a static method, that either just created it or opened.
        db = AppDatabase.getInstance(this)
        binding.btnDisplay.setOnClickListener {
            val myThread: Thread = object : Thread() {
                override fun run() {
                    //get all the entries with 3012 as the score.  since it's random, may not be any.
                    logthis("Starting display of score=3012")
                    var scores = db.ScoreDao().loadScore(3012)
                    if (scores != null) {
                        for (score in scores) {
                            val data = "id=" + score.id.toString() + " name=" + score.name
                                .toString() + " score=" + score.score
                            logthis(data)
                        }
                    } else {
                        logthis("no data loadScore ")
                    }
                    //get all the entries.
                    scores = db.ScoreDao().selectAll()
                    logthis("Starting display of all data")
                    if (scores != null) {
                        for (score in scores) {
                            val data = "id=" + score.id.toString() + " name=" + score.name
                                .toString() + " score=" + score.score
                            logthis(data)
                        }
                    } else {
                        logthis("There is no data!!!")
                    }
                }
            }
            myThread.start()
        }
        binding.btnAdd.setOnClickListener {
            val myThread: Thread = object : Thread() {
                override fun run() {
                    logthis("Inserting data")
                    db.ScoreDao().insertAll(generateScores())
                }
            }
            myThread.start()
        }
    }

    //this just generates some simple data to be inserted into the database.
    fun generateScores(): List<Score> {
        //Random r = new Random();
        val FIRST = arrayOf(
            "Jim", "Fred", "Allyson", "Danny", "Shaya"
        )
        val SECOND = intArrayOf(
            3012, 56, 256, 1001, 2048
        )
        val scores: MutableList<Score> = ArrayList()
        for (i in FIRST.indices) {
            val score = Score(FIRST[i], SECOND[i])
            //score.setId(i);
            //score.setName(FIRST[i]);
            //score.setScore(r.nextInt(5000));
            scores.add(score)
            logthis("adding data item $i")
        }
        return scores
    }

    //simple logger function to both the debug and logger textview.
    fun logthis(item: String?) {
        if (item != null && item.compareTo("") != 0) {
            Log.d(TAG, item)
            //We are likely not the main UI thread, so lets get there.
            runOnUiThread {
                binding.logger.append(item + "\n")
            }
        }
    }
}
