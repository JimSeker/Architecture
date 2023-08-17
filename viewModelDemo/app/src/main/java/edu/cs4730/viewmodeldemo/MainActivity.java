package edu.cs4730.viewmodeldemo;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import edu.cs4730.viewmodeldemo.databinding.ActivityMainBinding;

/**
 * this is a simple example to show how the ViewModel class works and how to separate
 * the data (ie model) from the UI (view/controller of the MVC) as well.
 * <p>
 * using the modelView class also allows the data to survive a rotation on the device as well.
 * so it doesn't need to be reloaded or stored in preference bundle.
 */

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final DataViewModel mViewModel = new ViewModelProvider(this).get(DataViewModel.class);

        //comment out, click the button a couple of times and then rotate phone.  should reset to zero.
        //then return to see it work.
        //binding.tvCount.setText(mViewModel.getCount());

        binding.etName.setText(mViewModel.name);
        binding.etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mViewModel.name = s.toString();
                logthis("storing name data");
            }

            @Override
            public void afterTextChanged(Editable s) {
                //ignore
            }
        });

        binding.btnAddCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logthis("using data from the modelview");
                mViewModel.incrementCount();
                binding.tvCount.setText(mViewModel.getCount());
            }
        });

    }

    /*`
     * simple method to add the log TextView.
     */
    public void logthis(String newinfo) {
        if (newinfo.compareTo("") != 0) {
            binding.logger.append(newinfo + "\n");
        }
    }


}
