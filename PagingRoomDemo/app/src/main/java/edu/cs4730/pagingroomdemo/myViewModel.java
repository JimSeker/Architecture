package edu.cs4730.pagingroomdemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;

/**
 * uses a the ViewModel and PagedList to load the data slowly (via the liveData observer),
 * which be used in the recyclerview.
 *
 * Lastly, don't listen the lent, this class must be public or it dies.
 */

public class myViewModel extends ViewModel {

    public LiveData<PagedList<Score>> scoreList;

    //this sets the database here, so it can use the pageList to load the data in increments as needed.
    public void set(ScoreDao scoreDao) {
        scoreList = scoreDao.selectByName().create(
                /* initial load position */ 0,
                new PagedList.Config.Builder()
                        .setPageSize(25)
                        .setPrefetchDistance(25)
                        .build());
    }
}
