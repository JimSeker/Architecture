package edu.cs4730.androidviewmodeldemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Very simple example of using a android view model.
 */

public class MainActivity extends AppCompatActivity {
    myAndroidViewModel mViewModel;
    EditText et_name;
    TextView tv_name, tv_count;
    Button addcount, btn_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get the modelview.
        mViewModel = new ViewModelProvider(this).get(myAndroidViewModel.class);

        //count fields.  Modelview doesn't have livedata here.  So everything is manual.
        tv_count = findViewById(R.id.textView);
        addcount = findViewById(R.id.button);
        tv_count.setText("Count is " + mViewModel.getCount());
        addcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.incrementCount();
                tv_count.setText("Count is " + mViewModel.getCount());
            }
        });

        //Name field is liveData, so we have observers to the do the work here.
        btn_name = findViewById(R.id.button2);
        et_name = findViewById(R.id.et_name);
        tv_name = findViewById(R.id.tv_name);
        mViewModel.getData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String data) {
                tv_name.setText("Name is " + data);
            }
        });
        btn_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.setName(et_name.getText().toString());
            }
        });

    }
}
