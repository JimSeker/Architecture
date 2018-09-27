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

package edu.cs4730.fcmretrrofit2livedatademo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.annotation.Nullable;

import java.util.List;

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

        mObservableScores.addSource(scores, mObservableScores::setValue  );
                //note mObservableScores::setValue is lamba expression, which I don't actually know what it is doing.
    }

    /**
     * Expose the LiveData Products query so the UI can observe it.
     */
    public LiveData<List<Score>> getScores() {
        return mObservableScores;
    }
}
