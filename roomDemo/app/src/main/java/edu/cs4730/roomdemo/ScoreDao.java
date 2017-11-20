package edu.cs4730.roomdemo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * This is the Dao for the score "db"  It provides the accessor methods to the database.
 *
 */
@Dao
public interface ScoreDao {

    /**
     * Select all scores.
     *
     * @return A {@link List<Score>} of all the name and scores in the table.
     */
    @Query("SELECT * FROM score")
    List<Score> selectAll();


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

    @Query("select * from score where score = :scoreValue")
    List<Score> loadScore(int scoreValue);

    /**
     * Delete a score by the ID.
     *
     * @param id The row ID.
     * @return A number of socres deleted. This should always be {@code 1}.
     */
    @Query("DELETE FROM score WHERE id = :id")
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
