package edu.cs4730.roomdemo;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 *  This is very simple code.  This is the "database" class and provides a bridge via SocreDoa method
 *  to the data itself.  At least I think that is what is going on here.
 */
@Database(entities = {Score.class,}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ScoreDao ScoreDao();
}
