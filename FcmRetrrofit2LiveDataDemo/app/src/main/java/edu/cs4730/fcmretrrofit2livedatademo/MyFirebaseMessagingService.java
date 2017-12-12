package edu.cs4730.fcmretrrofit2livedatademo;


import android.util.Log;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Trigger;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * This class is where the push message will go.  onMessageReceived is called this should launch the jobservice, but it's not working very well.
 * so instead it just launchs the networking to update.  Since the app is running, it should be ok.
 * <p>
 * This needs an off-line version as well, that just sets a "variable" to need update.  that way when the app is launched again
 * it will refresh.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

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
     * Schedule a job using FirebaseJobDispatcher.     so if longer then 10 seconds, schedule the job.
     */
    private void scheduleJob() {
        // [START dispatch_job]
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        Job myJob = dispatcher.newJobBuilder()
            .setService(MyJobService.class)
            .setTag("my-job-tag")
            .setTrigger(Trigger.executionWindow(
                2,
                10
            ))
            .build();
        dispatcher.schedule(myJob);
        // [END dispatch_job]
    }

}
