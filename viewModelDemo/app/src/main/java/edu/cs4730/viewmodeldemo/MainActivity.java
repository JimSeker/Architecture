package edu.cs4730.viewmodeldemo;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

/*
 * this is a simple example to show how the ViewModel class works and how to separate
 * the data (ie model) from the UI (view/controller of the MVC) as well.
 *
 * using the modelView class also allows the data to survive a rotation on the device as well.
 * so it doesn't need to be reloaded or stored in preference bundle.
 */
public class MainActivity extends AppCompatActivity {

    EditText et_name;
    TextView logger, tv_count;
    Button addcount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DataViewModel mViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        logger = findViewById(R.id.logger);

        tv_count = findViewById(R.id.tv_count);
        //comment out, click the button a couple of times and then rotate phone.  should reset to zero.
        //then return to see it work.
        //tv_count.setText(mViewModel.getCount());

        et_name = findViewById(R.id.et_name);
        et_name.setText(mViewModel.name);
        et_name.addTextChangedListener(new TextWatcher() {
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

        addcount = findViewById(R.id.btn_add_count);
        addcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logthis("using data from the modelview");
                mViewModel.incrementCount();
                tv_count.setText(mViewModel.getCount());
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
