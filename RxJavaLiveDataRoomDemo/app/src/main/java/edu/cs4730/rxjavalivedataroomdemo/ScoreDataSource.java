package edu.cs4730.rxjavalivedataroomdemo;


import io.reactivex.Flowable;

/**
 * Based on the example basicRxJavaSample, I'm using only a subset of the ScoreDoa here.
 * basically, I would just like to get this example working.
 *
 * This is then implemented by the LocalScoreDataSource and called in the in the injection class.
 *   but it is not clear to me why.
 */

public interface ScoreDataSource {

    /**
     * Gets the user from the data source.
     *
     * @return the user from the data source.
     */
    Flowable<Score> getOneScore();

    /**
     * Inserts a score into the table.
     *
     * @param scoreData A new person and score.
     * @return The row ID of the newly inserted data.
     */
    long insert(Score scoreData);

    /**
     * Deletes all scores from the data source.
     */
    void deleteAllScores();
}
