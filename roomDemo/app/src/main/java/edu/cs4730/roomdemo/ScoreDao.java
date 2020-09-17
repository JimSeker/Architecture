package edu.cs4730.roomdemo;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Dao;

import java.util.List;

/**
 * This is the Dao for the score "db"  It provides the accessor methods to the database.
 */
@Dao
public interface ScoreDao {

    /**
     * Select all scores.
     *
     * @return A {@link List<Score>} of all the name and scores in the table.
     */
    @Query("SELECT * FROM " + Score.TABLE_NAME)
    List<Score> selectAll();

    // get's all the entries with a specific score.

    @Query("select * from " +Score.TABLE_NAME +" where " + Score.COLUMN_SCORE +" = :scoreValue")
    List<Score> loadScore(int scoreValue);

    /**
     * Inserts a score into the table.  In a conflict (ie same data) replace it.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Score> scores);

    /**
     * Inserts a score into the table.
     *
     * @param scoreData A new person and score.
     * @return The row ID of the newly inserted data.
     */
    @Insert
    long insert(Score scoreData);

    /**
     * Delete a score by the ID.
     *
     * @param id The row ID.
     * @return A number of socres deleted. This should always be {@code 1}.
     */
    @Query("DELETE FROM " + Score.TABLE_NAME + " WHERE " + Score.COLUMN_ID + " = :id")
    int deleteById(long id);

    /**
     * Update the score. The socre is identified by the row ID.
     *
     * @param scoreData The socre to update.
     * @return A number of socres updated. This should always be {@code 1}.
     */
    @Update
    int update(Score scoreData);

}
