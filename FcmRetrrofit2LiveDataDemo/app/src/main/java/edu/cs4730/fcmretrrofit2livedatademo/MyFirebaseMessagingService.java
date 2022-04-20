package edu.cs4730.fcmretrrofit2livedatademo;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

/**
 * This class is where the push message will go.  onMessageReceived is called this should launch the jobservice, but it's not working very well.
 * so instead it just launchs the networking to update.  Since the app is running, it should be ok.
 * <p>
 * This needs an off-line version as well, that just sets a "variable" to need update.  that way when the app is launched again
 * it will refresh.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";


    /**
     * Replacement for the FirebaseInstanceID service, which was depreciated in 17.x
     * <p>
     * This is called on at least the first startup.  it generates a unique token that is
     * used by the cloud messaging system.  definitely save the token for later use.
     *
     * @param token
     */
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);

        Log.wtf(TAG, "FCM Token: " + token);
        //store the token for later use in the app.
        SharedPrefManager.getInstance(getApplicationContext()).saveDeviceToken(token);
    }


    //This is called when we get a new push message.
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //Displaying data in log
        //It is optional
        Log.wtf(TAG, "From: " + remoteMessage.getFrom());
        String message = remoteMessage.getData().toString();
        //getData should have the data we need, but when sending from the console, it's in getBody,
        //so this is accounting for the possibilities.
        if (message.compareTo("{}") != 0) {  //none empty message
            Log.d(TAG, "Notification Message Data: " + message);
        } else {
            message = remoteMessage.getNotification().getBody();
            Log.d(TAG, "Notification Message Body: " + message);
        }

        //networking is longer then 10 seconds, so use job service here: but we only run updates when the activity is running, so we good.
        //Calling method to generate notification
        //sendNotification(message);
        if (SharedPrefManager.getInstance(getApplicationContext()).getActivityStatus()) {
            //activity should be running.
            ScoreRepository sr = new ScoreRepository(this);
            sr.getAllScores();
        } else {
            //it will update when it starts.
            SharedPrefManager.getInstance(getApplicationContext()).saveUpdateStatus("yes");
        }
//        scheduleJob();
    }

    /**
     * Schedule a job using Worker     so if longer then 10 seconds, schedule the job.
     */

    private void scheduleJob() {

        Data inputData = new Data.Builder()
            .putString("some_key", "some_value")
            .build();
        Constraints constraints = new Constraints.Builder()
            // The Worker needs Network connectivity
            .setRequiredNetworkType(NetworkType.CONNECTED)
            // Needs the device to be charging
            .setRequiresCharging(true)
            .build();

        OneTimeWorkRequest request =
            // Tell which work to execute
            new OneTimeWorkRequest.Builder(myWorker.class)
                // Sets the input data for the ListenableWorker
                .setInputData(inputData)
                // If you want to delay the start of work by 60 seconds
                .setInitialDelay(60, TimeUnit.SECONDS)
                // Set additional constraints
                .setConstraints(constraints)
                .build();
        //start up the work request finally.
        WorkManager.getInstance(getApplicationContext())
            .enqueueUniqueWork("my-unique-name", ExistingWorkPolicy.KEEP, request);

    }
}
