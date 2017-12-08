package edu.cs4730.rxjavaroomdemo;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 *
 * this example uses RxJava with the room database.
 *
 * https://medium.com/google-developers/room-rxjava-acb0cd4f3757  helpful explanation.
 * this example is based off of https://github.com/googlesamples/android-architecture-components/tree/master/BasicRxJavaSample
 */

public class MainActivity extends AppCompatActivity {

    private ViewModelFactory mViewModelFactory;
    private ScoreViewModel mViewModel;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    private TextView mUserName;
    private EditText mUserNameInput, mUserScoreInput;
    private Button mUpdateButton;

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserName = findViewById(R.id.user_name);
        mUserNameInput = findViewById(R.id.user_name_input);
        mUserScoreInput = findViewById(R.id.user_score_input);
        mUpdateButton = findViewById(R.id.update_user);

        //I've no idea what the factory is for, but won't work without it.
        mViewModelFactory = Injection.provideViewModelFactory(this);
        //setup the pieces for the viewmodel, which the observable is setup in onStart.
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ScoreViewModel.class);
        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserName();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        // Subscribe to the emissions of the user name from the view model.
        // Update the user name text view, at every onNext emission.
        // In case of error, log the exception.
        mDisposable.add(mViewModel.getScore()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<Score>() {
                @Override
                public void accept(Score score) throws Exception {
                    mUserName.setText(score.getName() + ": " + score.getScore());
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    Log.e(TAG, "Unable to update username", throwable);
                }
            }));
    }

    @Override
    protected void onStop() {
        super.onStop();

        // clear all the subscriptions
        mDisposable.clear();
    }

    private void updateUserName() {
        String userName = mUserNameInput.getText().toString();
        String userScore = mUserScoreInput.getText().toString();
        // Disable the update button until the user name update has been done
        mUpdateButton.setEnabled(false);
        // Subscribe to updating the user name.
        // Re-enable the button once the user name has been updated
        Log.d(TAG, "starting update.");
        mDisposable.add(mViewModel.updateScore(userName, userScore)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action() {
                @Override
                public void run() throws Exception {
                    mUpdateButton.setEnabled(true);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    Log.e(TAG, "Unable to update username", throwable);
                }
            }));
    }
}
