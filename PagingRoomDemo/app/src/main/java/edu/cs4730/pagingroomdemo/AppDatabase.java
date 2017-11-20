package edu.cs4730.pagingroomdemo;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.util.Random;

/**
 *  This is very simple code.  This is the "database" class and provides a bridge via SocreDoa method
 *  to the data itself.  At least I think that is what is going on here.
 */
@Database(entities = {Score.class,}, version = 1)
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

        }
        return sInstance;
    }

    /**
     * simple method to add the data into system.
     */
    public static void AddData() {
        if (sInstance != null) {
            sInstance.populateInitialData();  //only addes if there is no data.
        }
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
