package edu.cs4730.retrofit2livedatademo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * retrofit http://square.github.io/retrofit/
 *
 * auth https://stackoverflow.com/questions/43366164/retrofit-and-okhttp-basic-authentication
 *
 */
public class MainActivity extends AppCompatActivity {

    private UserProfileViewModel viewModel;
    TextView logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logger = findViewById(R.id.textview);

        int userId = 12;

        viewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
        viewModel.setup(new UserRepository());
        viewModel.init(userId);
        viewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                //update the ui!
                Log.d("main", user.getUser());
                logger.setText(user.getUser());
            }
        });
    }
}
