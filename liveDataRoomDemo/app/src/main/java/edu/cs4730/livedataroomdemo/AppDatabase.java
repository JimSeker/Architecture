package edu.cs4730.livedataroomdemo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;


/**
 * Created by Seker on 11/16/2017.
 */
@Database(entities = {Score.class,}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase db;

    public static final String DATABASE_NAME = "database-name.db";

    public abstract ScoreDao ScoreDao();


    public static AppDatabase getInstance(final Context context) {
        Log.d("AppData", "hi 1");
        if (db == null) {
            Log.d("AppData", "hi 2");
            db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
        }
        return db;
    }


}
