/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.cs4730.livedataroomrecyclerdemo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * class to hold the data returned from the room database.  it has livedata, so observer is here and
 * pass back, I think.
 */

public class ScoreListViewModel extends ViewModel {

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<Score>> mObservableScores;

    public ScoreListViewModel(AppDatabase ad) {

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

    /**
     * Expose the LiveData Products query so the UI can observe it.
     */
    public LiveData<List<Score>> getScores() {
        return mObservableScores;
    }
}
