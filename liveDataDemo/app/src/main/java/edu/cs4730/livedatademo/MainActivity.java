package edu.cs4730.livedatademo;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import edu.cs4730.livedatademo.databinding.ActivityMainBinding;

/**
 * a simple example of using liveData.
 * <p>
 * this example has a problem with the edittext, so this will actually cause the user
 * to insert information "backward" from the order of typing.
 *
 * The "logger" text is the only one that is not saved, so it resets when the phone rotates.
 */

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    DataViewModel mViewModel;

    //this is a terrible way to do this, since the text change triggers the observer to update
    //the edittext text is reversed.  but this is a demo, so I'm going with it anyway.
    TextWatcher mlistener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //ignore
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            mViewModel.setName(s.toString());
            logthis("storing name data");
        }
    };


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
        mViewModel = new ViewModelProvider(this).get(DataViewModel.class);

        binding.etName.addTextChangedListener(mlistener);

        //set the observers for when the data changes.  It will then update.
        //Note, when the phone rotates, this will be called, because the data is new to the activity.
        mViewModel.getDataName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.etName.removeTextChangedListener(mlistener);  //if not, then this will loop infinitely.
                binding.etName.setText(s);
                binding.etName.addTextChangedListener(mlistener);
            }
        });
        mViewModel.getDataCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.tvCount.setText(String.valueOf(integer) );
            }
        });

        //change the count.
        binding.btnAddCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logthis("using data from the modelview");
                mViewModel.incrementCount();

            }
        });
    }

    /**
     * simple method to add the log TextView.
     */
    public void logthis(String newinfo) {
        if (newinfo.compareTo("") != 0) {
            binding.logger.append(newinfo + "\n");
            Log.wtf("mainActivity", newinfo);
        }
    }
}
