package edu.cosc4730.roomdemo_kt

import android.content.Context
import androidx.room.Database
import edu.cosc4730.roomdemo_kt.Score
import androidx.room.RoomDatabase
import edu.cosc4730.roomdemo_kt.ScoreDao
import edu.cosc4730.roomdemo_kt.AppDatabase
import androidx.room.Room

/**
 * This is very simple code.  This is the "database" class and provides a bridge via SocreDoa method
 * to the data itself. This is the simplest version of the RoomDatabase class.
 */
@Database(entities = [Score::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ScoreDao(): ScoreDao

    companion object {
        private lateinit var db: AppDatabase
        const val DATABASE_NAME = "database-name.db"
        fun getInstance(context: Context): AppDatabase {
            db = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()

            return db
        }
    }
}