package edu.cs4730.fcmretrrofit2livedatademo;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class myWorker extends Worker {

    public myWorker(@NonNull Context appContext, @NonNull WorkerParameters params) {
        super(appContext, params);
    }

    @Override
    public Result doWork() {
        // Do your work here.
        Data input = getInputData();

        // Return a ListenableWorker.Result
        Data outputData = new Data.Builder()
            .putString("Key", "value")
            .build();
        return Result.success(outputData);
    }

    @Override
    public void onStopped() {
        // Cleanup because you are being stopped.
    }

}
