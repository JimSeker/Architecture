package edu.cs4730.fcmretrrofit2livedatademo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *  two simple rest calls.  one by id, which is not really be used in this example, but works
 *  and the second that updates all the data.
 *
 *  A better version of this code, would update only the new id and save even more time and network traffic
 *  by having smart messages, saying new ids, delete these ids.  etc.
 */

public interface Webservice {
    /**
     * @GET declares an HTTP GET request
     * @Path("user") annotation on the userId parameter marks it as a
     * replacement for the {user} placeholder in the @GET path
     */
    //for something like  http://../rest/data/query.php?id=3012
    @GET("/rest/data/query.php")
    Call<String> getScores(@Query("id") int userId);

    /**
     * get all the data.  This would be better as <List<String>> but won't compile...
     * @return
     */
    @GET("/rest/data/query.php")
    Call<String> getAllScores();

    /**
     * this really needs the update, delete, and insert as well...
     */

}
