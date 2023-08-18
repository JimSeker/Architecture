package edu.cosc4730.roomdemo_kt

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import edu.cosc4730.roomdemo_kt.Score

/**
 * This is very simple code.  This is the "database" class and provides a bridge via SocreDoa method
 * to the data itself. This is the simplest version of the RoomDatabase class.
 */
@Database(entities = [Score::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ScoreDao(): ScoreDao

    companion object {
        private var db: AppDatabase? = null
        const val DATABASE_NAME = "database-name.db"

        @JvmStatic  //required in order to work.  kotlin doesn't like static, but it's required from java.
        fun getInstance(context: Context): AppDatabase {
            if (db == null) {
                db = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
            }
            return db!!
        }
    }
}