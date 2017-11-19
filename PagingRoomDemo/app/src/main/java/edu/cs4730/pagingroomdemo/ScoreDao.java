package edu.cs4730.pagingroomdemo;

import android.arch.paging.LivePagedListProvider;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import java.util.List;

/**
 * This is the Dao for the score "db"  It provides the accessor methods to the database.
 *
 * In this version, queries have a return version of  LivePagedListProvider so they can be
 * used with the recyclerview and livepageadapter.
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
    LivePagedListProvider<Integer, Score> selectAll();

    @Query("SELECT * FROM "+Score.TABLE_NAME +  " ORDER BY " +Score.COLUMN_NAME+" ASC")
     LivePagedListProvider<Integer, Score> selectByName();


    /**
     * Select a score by the ID.
     *
     * @param id The row ID.
     * @return A {@link Cursor} of the selected cheese.
     */
    @Query("SELECT * FROM " + Score.TABLE_NAME + " WHERE " + Score.COLUMN_ID + " = :id")
    LivePagedListProvider<Integer, Score> selectById(long id);

    /**
     * Delete a score by the ID.
     *
     * @param id The row ID.
     * @return A number of cheeses deleted. This should always be {@code 1}.
     */
    @Query("DELETE FROM " + Score.TABLE_NAME + " WHERE " + Score.COLUMN_ID + " = :id")
    int deleteById(long id);

    /**
     * Update the score. The cheese is identified by the row ID.
     *
     * @param scoreData The cheese to update.
     * @return A number of cheeses updated. This should always be {@code 1}.
     */
    @Update
    int update(Score scoreData);



}
