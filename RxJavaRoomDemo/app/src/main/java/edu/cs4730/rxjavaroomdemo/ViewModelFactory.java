package edu.cs4730.rxjavaroomdemo;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by Seker on 12/8/2017.
 */

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final ScoreDataSource mDataSource;

    public ViewModelFactory(ScoreDataSource dataSource) {
        mDataSource = dataSource;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ScoreViewModel.class)) {
            return (T) new ScoreViewModel(mDataSource);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
