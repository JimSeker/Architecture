package edu.cs4730.rxjavaroomdemo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * This creates the database as needed. keeping a static instance handy.
 */
@Database(entities = {Score.class,}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase db;

    public static final String DATABASE_NAME = "database-name.db";

    public abstract ScoreDao ScoreDao();  //the name of the scoreDoa class.


    public static AppDatabase getInstance(final Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
        }
        return db;
    }


}
