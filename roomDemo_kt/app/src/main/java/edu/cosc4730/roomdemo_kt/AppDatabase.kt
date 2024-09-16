package edu.cosc4730.roomdemo_kt

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

/**
 * This is very simple code.  This is the "database" class and provides a bridge via SocreDoa method
 * to the data itself. This is the simplest version of the RoomDatabase class.
 *
 * note you may need to use  the java code of the code for this one., since the Kotlin code won't always work at runtime.
 * But for kotlin, I was able to get KAPT to work, see changes in gradle build files.
 * You will need to add the KAPT in the gradle build files.  I can't get kSP to work, but maybe
 * if it's a new project it might work?
 */
@Database(entities = [Score::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ScoreDao(): ScoreDao

    companion object {
        private var db: AppDatabase? = null

        const val DATABASE_NAME: String = "database-name.db"

        @JvmStatic
        fun getInstance(context: Context): AppDatabase {
            if (db == null) {
                db = databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
            }
            return db!!
        }
    }
}
