package edu.cs4730.retrofit2livedatademo;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import edu.cs4730.retrofit2livedatademo.databinding.ActivityMainBinding;

/**
 * retrofit http://square.github.io/retrofit/
 * https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit  for versions in module.
 *
 * auth https://stackoverflow.com/questions/43366164/retrofit-and-okhttp-basic-authentication
 *
 * Note, https://koz.io/android-m-and-the-war-on-cleartext-traffic/
 *   In the AndroidManifest.xml there is < application ... android:usesCleartextTraffic="true" ...
 *   The test server doesn't have a legit cert, so... @#$@ it, cleartext it is.
 *   For real app, with legit certs on web servers, you should use https and remove the above.
 */
public class MainActivity extends AppCompatActivity {

    private UserProfileViewModel viewModel;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int userId = 1;

        viewModel = new ViewModelProvider( this).get(UserProfileViewModel.class);
        viewModel.setup(new UserRepository());
        viewModel.init(userId);
        viewModel.getUser().observe( this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                //update the ui!
                Log.d("main", user.getUser());
                binding.textview.append(user.getUser());
            }
        });
    }
}
