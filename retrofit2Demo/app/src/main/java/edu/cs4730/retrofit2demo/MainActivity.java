package edu.cs4730.retrofit2demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

/**
 *  basic example, taking from http://www.vogella.com/tutorials/Retrofit/article.html
 *
 * doumentations and versions.
 * retrofit http://square.github.io/retrofit/
 * https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit  for versions in module.
 *
 *  Note the output is all the logcat, not the screen.
 */

public class MainActivity extends AppCompatActivity {

    DataViewModel mViewModel;
    TextView logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the modelview.
        mViewModel = new ViewModelProvider(this).get(DataViewModel.class);
        logger = findViewById(R.id.logger);

        Controller controller = new Controller();
        controller.start(mViewModel);


        mViewModel.getData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> items) {
                logger.setText("Data:\n");
                for(int i =0; i < items.size(); i++) {
                    logthis(items.get(i));
                }
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
