package edu.cs4730.roomdemo;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 *  This is very simple code.  This is the "database" class and provides a bridge via SocreDoa method
 *  to the data itself. This is the simplest version of the RoomDatabase class.
 */
@Database(entities = {Score.class,}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ScoreDao ScoreDao();
}
