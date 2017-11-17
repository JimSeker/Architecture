package edu.cs4730.roomdemo2;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * This is the Dao for the score "db"
 */
@Dao
public interface ScoreDao {
    @Query("SELECT * FROM score")
    List<Score> loadAllScores();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Score> scores);

    @Query("select * from score where id = :scoreId")
    List<Score> loadScore(int scoreId);


}
