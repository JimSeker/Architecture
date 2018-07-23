package edu.cs4730.livedataroomrecyclerdemo;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * This is the Dao for the score "db" It provides the accessor methods to the database.
 * In this example, the return value is LiveData, so an observer can be added else to see when
 * the data changes.
 *
 */
@Dao
public interface ScoreDao {

    /**
     * Select all scores.
     *
     * @return A {@link LiveData <List<Score>>>} of all the name and scores in the table.
     */
    @Query("SELECT * FROM " + Score.TABLE_NAME)
    LiveData<List<Score>> selectAll();


    @Query("SELECT * FROM "+Score.TABLE_NAME +  " ORDER BY " +Score.COLUMN_NAME+" ASC")
    LiveData<List<Score>> selectByName();

    /**
     * Select a score by the ID.
     *
     * @param id The row ID.
     * @return A {@link LiveData <List<Score>>>} of the selected score.
     */
    @Query("SELECT * FROM " + Score.TABLE_NAME + " WHERE " + Score.COLUMN_ID + " = :id")
    LiveData<List<Score>> selectById(long id);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Score> scores);


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
     * Delete a score by the ID.
     *
     * @param id The row ID.
     * @return A number of scores deleted. This should always be {@code 1}.
     */
    @Query("DELETE FROM " + Score.TABLE_NAME + " WHERE " + Score.COLUMN_ID + " = :id")
    int deleteById(long id);

    /**
     * Delete all scores.
     */
    @Query("DELETE FROM " + Score.TABLE_NAME)
    void deleteAllScores();


    /**
     * Update the score. The score is identified by the row ID.
     *
     * @param scoreData The score to update.
     * @return A number of scores updated. This should always be {@code 1}.
     */
    @Update
    int update(Score scoreData);



}
