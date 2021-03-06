package edu.cs4730.retrofit2demo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Controller controller = new Controller();
        controller.start();
    }
}
