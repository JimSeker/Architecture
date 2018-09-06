package edu.cs4730.roomdemo;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 *  This is very simple code.  This is the "database" class and provides a bridge via SocreDoa method
 *  to the data itself. This is the simplest version of the RoomDatabase class.
 */
@Database(entities = {Score.class,}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ScoreDao ScoreDao();

}
