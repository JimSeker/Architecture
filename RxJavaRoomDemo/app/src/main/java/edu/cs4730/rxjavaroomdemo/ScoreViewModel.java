package edu.cs4730.rxjavaroomdemo;

import androidx.lifecycle.ViewModel;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.completable.CompletableFromAction;

/**
 * View Model for the {@link MainActivity}
 */
public class ScoreViewModel extends ViewModel {

    private final ScoreDataSource mDataSource;

    private Score mScore;

    public ScoreViewModel(ScoreDataSource dataSource) {
        mDataSource = dataSource;
    }

    /**
     * Get the Score name of the Score.
     *
     * @return a {@link Flowable} that will emit every time the Score name has been updated.
     */
    public Flowable<Score> getScore() {
        return mDataSource.getOneScore()
            // for every emission of the Score, get the Score name
            .map(new Function<Score, Score>() {
                @Override
                public Score apply(Score score) throws Exception {
                    mScore = score;
                    return score;
                }
            });

    }

    /**
     * Update the Score name.
     *
     * @param ScoreName the new Score name
     * @return a {@link Completable} that completes when the Score name is updated
     */
    public Completable updateScore(final String ScoreName, final String ScoreValue) {
        return new CompletableFromAction(new Action() {

            @Override
            public void run() throws Exception {
                // if there's no use, create a new user.
                // if we already have a user, then, since the user object is immutable,
                // create a new user, with the id of the previous user and the updated user name.
                if (mScore == null)
                    mScore = new Score(ScoreName, Integer.valueOf(ScoreValue));
                else {
                    //mScore = new Score(mScore.getId(), ScoreName, Integer.valueOf(ScoreValue));  //don't need a new object with updated data!
                    mScore.setName(ScoreName);
                    mScore.setScore(Integer.valueOf(ScoreValue));
                }

                mDataSource.insert(mScore);
            }
        });
    }
}
