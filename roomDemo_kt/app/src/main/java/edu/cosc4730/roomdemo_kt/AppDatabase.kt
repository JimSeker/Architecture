package edu.cosc4730.roomdemo_kt

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

/**
 * This is very simple code.  This is the "database" class and provides a bridge via SocreDoa method
 * to the data itself. This is the simplest version of the RoomDatabase class.
 *
 * Room implementation code is generated at build time by KSP.
 * The Gradle Kotlin DSL files must keep the Room compiler on the `ksp` configuration
 * or AppDatabase_Impl will not be generated.
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
