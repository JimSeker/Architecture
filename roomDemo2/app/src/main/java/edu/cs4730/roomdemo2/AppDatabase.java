package edu.cs4730.roomdemo2;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 *
 */
@Database(entities = {Score.class,}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ScoreDao ScoreDao();
}
