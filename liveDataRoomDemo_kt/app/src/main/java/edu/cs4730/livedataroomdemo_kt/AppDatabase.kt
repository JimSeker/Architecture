package edu.cs4730.livedataroomdemo_kt

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

/**
 * This creates the database as needed. keeping a static instance handy.
 *
 * note this is java code, since the Kotlin code won't always work at runtime.  likely something
 * to do with the static piece.
 */
@Database(entities = [Score::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ScoreDao(): ScoreDao //the name of the socreDoa class.


    companion object {
        private var db: AppDatabase? = null

        const val DATABASE_NAME: String = "database-name.db"

        fun getInstance(context: Context): AppDatabase {
            if (db == null) {
                db = databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
            }
            return db!!
        }
    }
}
