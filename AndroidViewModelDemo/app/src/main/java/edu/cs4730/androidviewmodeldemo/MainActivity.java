package edu.cs4730.androidviewmodeldemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.cs4730.androidviewmodeldemo.databinding.ActivityMainBinding;

/**
 * Very simple example of using a android view model.
 */

public class MainActivity extends AppCompatActivity {
    myAndroidViewModel mViewModel;
    ActivityMainBinding binding;

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
        //get the modelview.
        mViewModel = new ViewModelProvider(this).get(myAndroidViewModel.class);

        //count fields.  Modelview doesn't have livedata here.  So everything is manual.
        binding.tvCount.setText("Count is " + mViewModel.getCount());
        binding.addcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.incrementCount();
                binding.tvCount.setText("Count is " + mViewModel.getCount());
            }
        });

        //Name field is liveData, so we have observers to the do the work here.
        mViewModel.getData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String data) {
                binding.tvName.setText("Name is " + data);
            }
        });
        binding.btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.setName(binding.etName.getText().toString());
            }
        });

    }
}
