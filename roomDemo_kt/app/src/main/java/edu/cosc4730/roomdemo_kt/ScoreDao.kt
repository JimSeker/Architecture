package edu.cosc4730.roomdemo_kt

import androidx.room.*
import edu.cosc4730.roomdemo_kt.Score

/**
 * This is the Dao for the score "db"  It provides the accessor methods to the database.
 */
@Dao
interface ScoreDao {
    /**
     * Select all scores.
     *
     * @return A [<] of all the name and scores in the table.
     */
    @Query("SELECT * FROM " + Score.TABLE_NAME)
    fun selectAll(): List<Score>

    // get's all the entries with a specific score.
    @Query("select * from " + Score.TABLE_NAME + " where " + Score.COLUMN_SCORE + " = :scoreValue")
    fun loadScore(scoreValue: Int): List<Score>

    /**
     * Inserts a score into the table.  In a conflict (ie same data) replace it.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(scores: List<Score>)

    /**
     * Inserts a score into the table.
     *
     * @param scoreData A new person and score.
     * @return The row ID of the newly inserted data.
     */
    @Insert
    fun insert(scoreData: Score): Long

    /**
     * Delete a score by the ID.
     *
     * @param id The row ID.
     * @return A number of socres deleted. This should always be `1`.
     */
    @Query("DELETE FROM " + Score.TABLE_NAME + " WHERE " + Score.COLUMN_ID + " = :id")
    fun deleteById(id: Long): Int

    /**
     * Update the score. The socre is identified by the row ID.
     *
     * @param scoreData The socre to update.
     * @return A number of socres updated. This should always be `1`.
     */
    @Update
    fun update(scoreData: Score): Int
}