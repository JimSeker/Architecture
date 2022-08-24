package edu.cs4730.livedataroomdemo;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.annotation.Nullable;
import java.util.List;

/**
 * class to hold the room database.  With the LiveData, you expose observers, so the application
 * can update widgets as needed.
 */

public class ScoreListViewModel extends AndroidViewModel {

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<Score>> mObservableScores;
    private final AppDatabase ad;
    final private String TAG = "ViewModel";

    public ScoreListViewModel(Application application) {
        super(application);

        ad = AppDatabase.getInstance(application);
        mObservableScores = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        mObservableScores.setValue(null);

        LiveData<List<Score>> scores = ad.ScoreDao().selectAll();

        // observe the changes of the scores from the database and forward them
        //lambda expression 1, which makes little sense.
        //mObservableScores.addSource(scores, mObservableScores::setValue);
        //lambda expression 2  this makes more sense then above method.  scoreEntities is the parameter to the method,
        //  scores and scoreEntities are the same.  score is the source and scoreEntities is the destination.
        //mObservableScores.addSource(scores, scoreEntities -> { mObservableScores.setValue(scoreEntities); } );
        //this is the java one.
        mObservableScores.addSource(scores, new androidx.lifecycle.Observer<List<Score>>() {
                @Override
                public void onChanged(@Nullable List<Score> scoreEntities) {
                    mObservableScores.setValue(scoreEntities);
                }
            }

        );
    }

    public void addData(List<Score> scores) {
        Thread myThread = new Thread() {  //database additions can't be on the main thread.
            public void run() {
                Log.d(TAG, "Inserting data");
                ad.ScoreDao().insertAll(scores);
            }
        };
        myThread.start();
    }

    /**
     * Expose the LiveData Products query so the UI can observe it.
     */
    public LiveData<List<Score>> getScores() {
        return mObservableScores;
    }
}
