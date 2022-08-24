package edu.cs4730.livedataroomdemo_kt

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


/**
 * This is the Dao for the score "db" It provides the accessor methods to the database.
 * In this example, the return value is LiveData, so an observer can be added else to see when
 * the data changes.
 */
@Dao
interface ScoreDao {
    /**
     * Select all scores.
     *
     * @return A [&lt;][<] of all the name and scores in the table.
     */
    @Query("SELECT * FROM " + Score.TABLE_NAME)
    fun selectAll(): LiveData<List<Score>>

    @Query("SELECT * FROM " + Score.TABLE_NAME + " ORDER BY " + Score.COLUMN_NAME + " ASC")
    fun selectByName(): LiveData<List<Score>>

    /**
     * Select a score by the ID.
     *
     * @param id The row ID.
     * @return A [&lt;][<] of the selected score.
     */
    @Query("SELECT * FROM " + Score.TABLE_NAME + " WHERE " + Score.COLUMN_ID + " = :id")
    fun selectById(id: Long): LiveData<List<Score>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(scores: List<Score>)

    @Query("SELECT COUNT(*) FROM " + Score.TABLE_NAME)
    fun count(): Int

    /**
     * Inserts a score into the table.
     *
     * @param scoreData A new person and score.
     * @return The row ID of the newly inserted data.
     */
    @Insert
    fun insert(scoreData: Score?): Long

    /**
     * Inserts multiple scores into the database
     *
     * @param scoreDatas An array of new score data.
     * @return The row IDs of the newly inserted scores.
     */
    @Insert
    fun insertAll(scoreDatas: Array<Score>): LongArray

    /**
     * Delete a score by the ID.
     *
     * @param id The row ID.
     * @return A number of scores deleted. This should always be `1`.
     */
    @Query("DELETE FROM " + Score.TABLE_NAME + " WHERE " + Score.COLUMN_ID + " = :id")
    fun deleteById(id: Long): Int

    /**
     * Delete all scores.
     */
    @Query("DELETE FROM " + Score.TABLE_NAME)
    fun deleteAllScores()

    /**
     * Update the score. The score is identified by the row ID.
     *
     * @param scoreData The score to update.
     * @return A number of scores updated. This should always be `1`.
     */
    @Update
    fun update(scoreData: Score): Int
}