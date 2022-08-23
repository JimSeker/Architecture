package edu.cs4730.livedatademo;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * a simple example of using liveData.
 * <p>
 * this example has a problem with the edittext, so this will actually cause the user
 * to insert information "backward" from the order of typing.
 *
 * The "logger" text is the only one that is not saved, so it resets when the phone rotates.
 */

public class MainActivity extends AppCompatActivity {

    EditText et_name;
    TextView logger, tv_count;
    Button addcount;
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
        setContentView(R.layout.activity_main);
        //get the modelview.
        mViewModel = new ViewModelProvider(this).get(DataViewModel.class);

        logger = findViewById(R.id.logger);
        tv_count = findViewById(R.id.tv_count);
        et_name = findViewById(R.id.et_name);
        et_name.addTextChangedListener(mlistener);

        //set the observers for when the data changes.  It will then update.
        //Note, when the phone rotates, this will be called, because the data is new to the activity.
        mViewModel.getDataName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                et_name.removeTextChangedListener(mlistener);  //if not, then this will loop infinitely.
                et_name.setText(s);
                et_name.addTextChangedListener(mlistener);
            }
        });
        mViewModel.getDataCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tv_count.setText(String.valueOf(integer) );
            }
        });

        addcount = findViewById(R.id.btn_add_count);

        //change the count.
        addcount.setOnClickListener(new View.OnClickListener() {
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
            logger.append(newinfo + "\n");
            Log.wtf("mainActivity", newinfo);
        }
    }
}
