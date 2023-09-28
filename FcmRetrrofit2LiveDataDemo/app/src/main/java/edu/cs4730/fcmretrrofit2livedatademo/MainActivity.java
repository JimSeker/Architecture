package edu.cs4730.fcmretrrofit2livedatademo;

import androidx.lifecycle.Observer;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ProgressDialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import edu.cs4730.fcmretrrofit2livedatademo.databinding.ActivityMainBinding;

/**
 * This project is in a nutshell, only ask for data from the network, when it has been updated on the network.
 * We use Firebase messaging to notify the app, the data has changed.  Retrofit2 to update the data in to the room database
 * which the liveData will see and update the interface.   So the data will always be current, with a minimal network traffic.
 * Using contentproivders terms, we have loaders for the rest service.
 * <p>
 * this just uses the textview to display info.  There is a button to register for the updates.  This would likely be done
 * the app first time runs?  or refresh of the token.   Not sure on the timing of this.
 * <p>
 * It should stated, this is not a full app, just a demo of how the pieces can work.
 * <p>
 * Note the ProgressDialog was deprecated in API 26.  This will need to be fixed at some point.
 * <p>
 * also the update to gradle 7.3.X breaks this code.  no changes are made, just an update to gradle.  I had to back it down via git and
 * it compiles and runs.  no clue what that is is about.
 */

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTokenToServer();
            }
        });

        AppDatabase ad = AppDatabase.getInstance(this);

        ScoreListViewModel scoreListViewModel = new ScoreListViewModel(ad);
        scoreListViewModel.getScores().observe(this, new Observer<List<Score>>() {
            @Override
            public void onChanged(@Nullable List<Score> scores) {
                logthis("Data has been added/changed, displaying");
                if (scores != null) {
                    for (Score score : scores) {
                        String data = "id=" + score.getId() + " name=" + score.getName() + " score=" + score.getScore();
                        logthis(data);
                    }
                } else {
                    logthis("There is no data!!!");
                }
            }
        });


    }

    //simple logger function to both the debug and logger textview.
    void logthis(String item) {
        if (item != null && item.compareTo("") != 0) {
            Log.d(TAG, item);
            binding.logger.append(item + "\n");
        }
    }

    /**
     * This will send the token to the backend server (which is mysql/php/apache.  see
     * the php directory in this project for the source code.
     * <p>
     * Note, this is called onResume.  On the first run, the token hasn't been generated yet, so this
     * returns false.  On later runs (or when OnResume  is called), it will auto register.
     */
    private boolean sendTokenToServer() {

        logthis("Registering Device...");

        final String token = SharedPrefManager.getInstance(this).getDeviceToken();
        final String name = "activeApp";

        if (token == null) {
            logthis("Token not generated");
            return false;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPoints.URL_REGISTER_DEVICE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    logthis(obj.getString("message"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                logthis(error.getMessage());
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("token", token);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        //load update from rest service.
        SharedPrefManager.getInstance(getApplicationContext()).saveActivityStatus(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        //are their updates and is this the first time.
        String info = SharedPrefManager.getInstance(getApplicationContext()).getUpdateStatus();
        if (info == null) {  //first time, register and get data
            if (sendTokenToServer()) //if registers, set it to run, otherwise, have user do it manually.
                info = "yes"; //force the update.
        }
        if ((info != null) && (info.compareTo("yes") == 0)) { //update data
            ScoreRepository sr = new ScoreRepository(this);
            sr.getAllScores();
            SharedPrefManager.getInstance(getApplicationContext()).saveUpdateStatus("no");  //we are current.
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        //don't load update from rest service.
        SharedPrefManager.getInstance(getApplicationContext()).saveActivityStatus(false);
    }
}
