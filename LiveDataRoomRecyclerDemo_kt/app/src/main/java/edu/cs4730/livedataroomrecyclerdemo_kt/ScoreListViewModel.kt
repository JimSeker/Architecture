package edu.cs4730.livedataroomrecyclerdemo_kt

import edu.cs4730.livedataroomrecyclerdemo_kt.AppDatabase.Companion.getInstance
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.LiveData

/**
 * class to hold the room database.  With the LiveData, you expose observers, so the application
 * can update widgets as needed.
 */
class ScoreListViewModel(application: Application) : AndroidViewModel(application) {
    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private val mObservableScores: MediatorLiveData<List<Score>?>
    private val ad: AppDatabase
    private val TAG = "ViewModel"
    fun addData(scores: List<Score>) {
        val myThread: Thread = object : Thread() {
            //database additions can't be on the main thread.
            override fun run() {
                Log.d(TAG, "Inserting data")
                ad.ScoreDao().insertAll(scores)
            }
        }
        myThread.start()
    }

    /**
     * Expose the LiveData Products query so the UI can observe it.
     */
    val scores: LiveData<List<Score>?>
        get() = mObservableScores

    init {
        ad = getInstance(application)
        mObservableScores = MediatorLiveData()
        // set by default null, until we get data from the database.
        mObservableScores.value = null
        val scores = ad.ScoreDao().selectAll()

        // observe the changes of the scores from the database and forward them
        mObservableScores.addSource(
            scores
        ) { scoreEntities -> mObservableScores.value = scoreEntities }
    }
}