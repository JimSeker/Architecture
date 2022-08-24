package edu.cs4730.livedataroomrecyclerdemo_kt

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room

/**
 * This creates the database as needed. keeping a static instance handy.
 */
@Database(entities = [Score::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ScoreDao(): ScoreDao //the name of the scoreDoa class.

    companion object {
        private lateinit var db: AppDatabase
        const val DATABASE_NAME = "database-name.db"

        @JvmStatic
        fun getInstance(context: Context): AppDatabase {
            db = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
            return db
        }
    }
}