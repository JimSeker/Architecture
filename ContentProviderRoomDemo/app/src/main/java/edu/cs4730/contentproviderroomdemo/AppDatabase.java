package edu.cs4730.contentproviderroomdemo;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Context;

import java.util.Random;

/**
 * This is very simple code.  This is the "database" class and provides a bridge via SocreDoa method
 * to the data itself. adds sample data in the initial load of the database, because of this, loaders or a thread is need
 * for the insert.    likely we should have a thread in populateInitalData so we do it correctly?
 */

@Database(entities = {Score.class,}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ScoreDao ScoreDao();

    private static AppDatabase sInstance;

    /**
     * Gets the singleton instance of SampleDatabase.
     *
     * @param context The context.
     * @return The singleton instance of SampleDatabase.
     */
    public static synchronized AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room
                .databaseBuilder(context.getApplicationContext(), AppDatabase.class, "database-name")
                .build();
            sInstance.populateInitialData();
        }
        return sInstance;
    }

    /**
     * Inserts the dummy data into the database if it is currently empty.
     */
    private void populateInitialData() {
        Random r = new Random();
        if (ScoreDao().count() == 0) {
            Score scoreData = new Score();
            beginTransaction();
            try {
                for (int i = 0; i < Score.names.length; i++) {
                    scoreData.name = Score.names[i];
                    scoreData.score = r.nextInt(5000);
                    ScoreDao().insert(scoreData);
                }
                setTransactionSuccessful();
            } finally {
                endTransaction();
            }
        }
    }
}
