package edu.cs4730.retrofit2livedatademo;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 *
 */

public interface Webservice {
    /**
     * @GET declares an HTTP GET request
     * @Path("user") annotation on the userId parameter marks it as a
     * replacement for the {user} placeholder in the @GET path
     *
     *
     */
    //for something like  http://../rest/data/query.php?id=3012
    @GET("/rest/data/query.php")
    Call<String> getUser(@Query("id") int userId);
//    @GET("/posts/{id}")
//     Call<List<User>> getUser(@Path("id")int userId);
}
