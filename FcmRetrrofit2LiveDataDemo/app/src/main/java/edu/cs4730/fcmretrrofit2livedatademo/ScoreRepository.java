package edu.cs4730.fcmretrrofit2livedatademo;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * This the retrofit2 method to call the Rest Service and update the room database.
 *
 * Since the mainactivity is observing, it will be notified of new data.
 */

public class ScoreRepository {
    private Webservice webservice;
    Retrofit retrofit;
    AppDatabase db;
    Context context;

    ScoreRepository(Context context) {
        this.context = context;
        retrofit = new Retrofit.Builder()
            .baseUrl(EndPoints.URL_BASE)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build();
        webservice = retrofit.create(Webservice.class);

        db = AppDatabase.getInstance(context);
    }

    public void getScore(int Id) {
        Call<String> call = webservice.getScores(Id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("response", "in response...");
                if (response.isSuccessful()) {
                    String changedList = response.body();
                    //process the data, which is in a csv format on this php, instead json.
                    //update the database here!!!
                    parseAndSet(changedList,true);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void getAllScores() {

        Call<String> call = webservice.getAllScores();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("response", "in response...");
                if (response.isSuccessful()) {
                    String changedList = response.body();
                    Log.d("output", changedList);
                    //process the data, which is in a csv format on this php, instead json.
                    //update the database here!!!
                    parseAndSet(changedList,false);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    void parseAndSet(String returndata, boolean oneline) {
        List<Score> myList = new ArrayList<Score>();
        //first split on end of line markers
        String[] lines;
        if (oneline)
           lines = new String[]{returndata};
        else
           lines = returndata.split("\n");
        //now parse each line and put it into the db.
        String[] tokens;
        for(String line: lines) {
            tokens = line.split("[,]");
            if (tokens[0].compareTo("") != 0)
              myList.add(new Score(Long.valueOf(tokens[0]), tokens[1], Integer.valueOf(tokens[2])));
        }
        //lastly and it must be on a thread
        Thread myThread = new Thread() {
            public void run() {
                db.ScoreDao().insertAll(myList);
            }
        };
        myThread.start();

    }

}
