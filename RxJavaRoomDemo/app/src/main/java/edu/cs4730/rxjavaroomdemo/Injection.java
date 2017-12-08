package edu.cs4730.rxjavaroomdemo;

import android.content.Context;



/**
 * Enables injection of data sources.
 */
public class Injection {

    public static ScoreDataSource provideUserDataSource(Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        return new LocalScoreDataSource(database.ScoreDao());
    }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        ScoreDataSource dataSource = provideUserDataSource(context);
        return new ViewModelFactory(dataSource);
    }
}