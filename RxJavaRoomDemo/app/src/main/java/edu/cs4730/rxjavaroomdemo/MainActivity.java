package edu.cs4730.rxjavaroomdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import edu.cs4730.rxjavaroomdemo.databinding.ActivityMainBinding;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * this example uses RxJava with the room database.
 * https://github.com/ReactiveX/RxJava
 * <p>
 * https://medium.com/google-developers/room-rxjava-acb0cd4f3757  helpful explanation.
 * this example is based off of https://github.com/googlesamples/android-architecture-components/tree/master/BasicRxJavaSample
 */

public class MainActivity extends AppCompatActivity {

    private ViewModelFactory mViewModelFactory;
    private ScoreViewModel mViewModel;
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private ActivityMainBinding binding;

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });
        //I've no idea what the factory is for, but won't work without it.
        mViewModelFactory = Injection.provideViewModelFactory(this);
        //setup the pieces for the viewmodel, which the observable is setup in onStart.
        mViewModel = new ViewModelProvider(this, mViewModelFactory).get(ScoreViewModel.class);
        binding.updateUser.setOnClickListener(new View.OnClickListener() {
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
        mDisposable.add(mViewModel.getScore().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Score>() {
                    @Override
                    public void accept(Score score) throws Exception {
                        binding.userName.setText(score.getName() + ": " + score.getScore());
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
        String userName = binding.userNameInput.getText().toString();
        String userScore = binding.userScoreInput.getText().toString();
        // Disable the update button until the user name update has been done
        binding.updateUser.setEnabled(false);
        // Subscribe to updating the user name.
        // Re-enable the button once the user name has been updated
        Log.d(TAG, "starting update.");
        mDisposable.add(mViewModel.updateScore(userName, userScore).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        binding.updateUser.setEnabled(true);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "Unable to update username", throwable);
                    }
                }));
    }
}
