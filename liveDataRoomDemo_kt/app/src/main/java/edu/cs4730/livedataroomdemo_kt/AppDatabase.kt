package edu.cs4730.livedataroomdemo_kt

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room

/**
 * This creates the database as needed. keeping a static instance handy.
 */
@Database(entities = [Score::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ScoreDao(): ScoreDao //the name of the socreDoa class.

    companion object {
        private lateinit var db: AppDatabase
        const val DATABASE_NAME = "database-name.db"

        @JvmStatic  //required in order to work.  kotlin doesn't like static, but it's required from java.
        fun getInstance(context: Context): AppDatabase {
            db = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
            return db
        }
    }
}