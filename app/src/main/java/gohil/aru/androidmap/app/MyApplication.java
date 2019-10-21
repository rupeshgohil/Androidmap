package gohil.aru.androidmap.app;

import android.app.Application;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {
    public static final String BASE_URL_MAP = "https://maps.googleapis.com";
    private static Retrofit retrofit;
    @Override
    public void onCreate() {
        super.onCreate();
    }
    public static Retrofit getRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging); // <-- this is the important line
        if (null == retrofit) {
            // <-- this is the important line!
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL_MAP)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    /*
    https://maps.googleapis.com/maps/api/directions/json?origin=22.2736308,70.7512554&destination=23.0284019,72.5046343&key=
    * */
}
