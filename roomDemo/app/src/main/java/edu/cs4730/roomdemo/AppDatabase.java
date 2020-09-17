package edu.cs4730.roomdemo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 *  This is very simple code.  This is the "database" class and provides a bridge via SocreDoa method
 *  to the data itself. This is the simplest version of the RoomDatabase class.
 */
@Database(entities = {Score.class,}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase db;

    public static final String DATABASE_NAME = "database-name.db";

    public abstract ScoreDao ScoreDao();

    public static AppDatabase getInstance(final Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
        }
        return db;
    }

}
