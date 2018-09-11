package edu.cs4730.pagingroomdemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

/**
 * uses a the ViewModel and PagedList to load the data slowly (via the liveData observer),
 * which be used in the recyclerview.
 * <p>
 * Lastly, don't listen the lent, this class must be public or it dies.
 */

public class myViewModel extends ViewModel {

    public LiveData<PagedList<Score>> scoreList;

    //this sets the database here, so it can use the pageList to load the data in increments as needed.
    public void set(ScoreDao scoreDao) {
        PagedList.Config pagedListConfig =
            (new PagedList.Config.Builder()).setEnablePlaceholders(true)
                .setPrefetchDistance(10)
                .setPageSize(20).build();

        scoreList = (new LivePagedListBuilder(
            scoreDao.selectByName(),
            pagedListConfig)).build();


//        scoreList = scoreDao.selectByName().create(
//                /* initial load position */ 0,
//                new PagedList.Config.Builder()
//                        .setPageSize(25)
//                        .setPrefetchDistance(25)
//                        .build());

    }
}
