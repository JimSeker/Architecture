package edu.cs4730.retrofit2livedatademo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 *
 */

public class UserRepository {
    private Webservice webservice;

    Retrofit retrofit;
    private String userName = "user1";
    private String password = "android";

    UserRepository() {

        //for authentication.
        Interceptor BasicAuthInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                String Credentials = okhttp3.Credentials.basic(userName, password);
                Request request = chain.request();
                Request authenticatedRequest = request.newBuilder()
                    .header("Authorization", Credentials).build();
                return chain.proceed(authenticatedRequest);
            }
        };

        OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(BasicAuthInterceptor)
            .build();

        retrofit = new Retrofit.Builder()
            .baseUrl("http://10.216.218.12/")
            // .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(client)
            .build();
        webservice = retrofit.create(Webservice.class);
    }

    public LiveData<User> getUser(int userId) {
        // This is not an optimal implementation, we'll fix it below
        final MutableLiveData<User> data = new MutableLiveData<>();
        Call<String> call = webservice.getUser(userId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("response", "in response...");
                if (response.isSuccessful()) {
                    String changedList = response.body();
                    //process the data, which is in a csv format on this php, instead json.
                    data.setValue((new User(changedList)));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return data;
    }

}
