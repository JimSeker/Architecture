package edu.cs4730.livedatademo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/*
 * a simple example of using liveData .
 *
 * this example has a problem with the edittext, so this will actually cause the user
 * to insert information "backward" from the order of typing.
 */

public class MainActivity extends AppCompatActivity {

    EditText et_name;
    TextView logger, tv_count;
    Button addcount;
    DataViewModel mViewModel;

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
        mViewModel = ViewModelProviders.of(this).get(DataViewModel.class);


        logger = findViewById(R.id.logger);

        tv_count = findViewById(R.id.tv_count);
        //comment out, click the button a couple of times and then rotate phone.  should reset to zero.
        //then return to see it work.
        //tv_count.setText(mViewModel.getCount());

        et_name = (EditText) findViewById(R.id.et_name);

        et_name.addTextChangedListener(mlistener);

        mViewModel.getData().observe(this, new Observer<dataObj>() {
            @Override
            public void onChanged(@Nullable dataObj data) {
                logthis("Data changed, updating!");
                et_name.removeTextChangedListener(mlistener);  //if not, then this will loop infinitely.
                et_name.setText(data.name);
                et_name.addTextChangedListener(mlistener);
                tv_count.setText(data.getCount());
            }
        });
        addcount = findViewById(R.id.btn_add_count);
        addcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logthis("using data from the modelview");
                mViewModel.incrementCount();

            }
        });
    }


    /*`
    * simple method to add the log TextView.
    */
    public void logthis(String newinfo) {
        if (newinfo.compareTo("") != 0) {
            logger.append(newinfo + "\n");
        }
    }


}
