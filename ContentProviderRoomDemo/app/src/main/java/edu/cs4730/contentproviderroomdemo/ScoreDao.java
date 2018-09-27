package edu.cs4730.contentproviderroomdemo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import android.database.Cursor;

/**
 * This is the Dao for the score "db"  It provides the accessor methods to the database.
 *
 * In this version, queries have a return version of  Cursor so they can be
 * used with the content provider.
 */
@Dao
public interface ScoreDao {

    @Query("SELECT COUNT(*) FROM " + Score.TABLE_NAME)
    int count();

    /**
     * Inserts a score into the table.
     *
     * @param scoreData A new person and score.
     * @return The row ID of the newly inserted data.
     */
    @Insert
    long insert(Score scoreData);

    /**
     * Inserts multiple scores into the database
     *
     * @param scoreDatas An array of new score data.
     * @return The row IDs of the newly inserted scores.
     */
    @Insert
    long[] insertAll(Score[] scoreDatas);

    /**
     * Select all scores.
     *
     * @return A {@link Cursor} of all the name and scores in the table.
     */
    @Query("SELECT * FROM " + Score.TABLE_NAME)
    Cursor selectAll();

    /**
     * Select a score by the ID.
     *
     * @param id The row ID.
     * @return A {@link Cursor} of the selected score.
     */
    @Query("SELECT * FROM " + Score.TABLE_NAME + " WHERE " + Score.COLUMN_ID + " = :id")
    Cursor selectById(long id);

    /**
     * Delete a score by the ID.
     *
     * @param id The row ID.
     * @return A number of scores deleted. This should always be {@code 1}.
     */
    @Query("DELETE FROM " + Score.TABLE_NAME + " WHERE " + Score.COLUMN_ID + " = :id")
    int deleteById(long id);

    /**
     * Update the score. The score is identified by the row ID.
     *
     * @param scoreData The score to update.
     * @return A number of scores updated. This should always be {@code 1}.
     */
    @Update
    int update(Score scoreData);



}
