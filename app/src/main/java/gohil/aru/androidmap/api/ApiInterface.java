package gohil.aru.androidmap.api;

import java.util.ArrayList;

import gohil.aru.androidmap.modal.RouterModal;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/maps/api/directions/json")
    Call<ArrayList<RouterModal>> getMapRoutes(
            @Query("origin") String origin,
            @Query("destination") String destinaion,
            @Query("key") String key);


}
