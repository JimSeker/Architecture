package edu.cs4730.rxjavalivedataroomdemo;

import io.reactivex.Flowable;

/**
 * Created by Seker on 12/8/2017.
 */

public class LocalScoreDataSource implements ScoreDataSource{
    private final ScoreDao mScoreDao;

    public LocalScoreDataSource(ScoreDao scoreDao) {
        mScoreDao = scoreDao;
    }

    @Override
    public Flowable<Score> getOneScore() {
        return mScoreDao.getOneScore();
    }

    @Override
    public long insert(Score scoreData) {
        return mScoreDao.insert(scoreData);
    }

    @Override
    public void deleteAllScores() {
        mScoreDao.deleteAllScores();
    }
}
