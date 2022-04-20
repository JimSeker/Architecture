package edu.cs4730.pagingroomdemo;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * This is the Dao for the score "db"  It provides the accessor methods to the database.
 * <p>
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
     * @return A {@link DataSource.Factory} of all the name and scores in the table.
     */
    @Query("SELECT * FROM " + Score.TABLE_NAME)
    public abstract DataSource.Factory<Integer, Score> selectAll();


    @Query("SELECT * FROM " + Score.TABLE_NAME + " ORDER BY " + Score.COLUMN_NAME + " ASC")
    public abstract DataSource.Factory<Integer, Score> selectByName();


    /**
     * Select a score by the ID.
     *
     * @param id The row ID.
     * @return A {@link DataSource.Factory} of the selected score.
     */
    @Query("SELECT * FROM " + Score.TABLE_NAME + " WHERE " + Score.COLUMN_ID + " = :id")
    public abstract DataSource.Factory<Integer, Score> selectById(long id);

    /**
     * Delete a score by the ID.
     *
     * @param id The row ID.
     * @return A number of scores deleted. This should always be {@code 1}.
     */
    @Query("DELETE FROM " + Score.TABLE_NAME + " WHERE " + Score.COLUMN_ID + " = :id")
    int deleteById(long id);

    /**
     * Update the score. The scores is identified by the row ID.
     *
     * @param scoreData The score to update.
     * @return A number of scores updated. This should always be {@code 1}.
     */
    @Update
    int update(Score scoreData);


}
