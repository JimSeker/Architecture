package edu.cs4730.retrofit2demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 *  basic example, taking from http://www.vogella.com/tutorials/Retrofit/article.html  
 
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
