package edu.cs4730.pagingroomdemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;

/**
 * Created by Seker on 11/19/2017.
 */

public class myViewModel extends ViewModel {

    public LiveData<PagedList<Score>> scoreList;



    public void set(ScoreDao scoreDao) {
        scoreList = scoreDao.selectByName().create(
                /* initial load position */ 0,
                new PagedList.Config.Builder()
                        .setPageSize(25)
                        .setPrefetchDistance(25)
                        .build());
    }
}
