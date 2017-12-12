package edu.cs4730.fcmretrrofit2livedatademo;


import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Seker on 4/14/2017.
 *
 * This is called on at least the first startup.  it generates a unique token that is
 * used by the cloud messaging system.  definitely save the token for later use.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {

        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        //Displaying token on logcat
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        storeToken(refreshedToken);

    }

    private void storeToken(String token) {
        //You can implement this method to store the token on your server
        SharedPrefManager.getInstance(getApplicationContext()).saveDeviceToken(token);
    }
}
