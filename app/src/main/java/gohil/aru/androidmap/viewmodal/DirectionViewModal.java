package gohil.aru.androidmap.viewmodal;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import gohil.aru.androidmap.api.ApiInterface;
import gohil.aru.androidmap.modal.RouterModal;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static gohil.aru.androidmap.app.MyApplication.getRetrofit;

public class DirectionViewModal extends ViewModel {
    private MutableLiveData<ArrayList<RouterModal>> directionList;
    private  Retrofit retrofit = null;
    private String strstartLatlong;
    private String strdestinationLatlong;
    private String Key;
    private Context mContext;


    public LiveData<ArrayList<RouterModal>> setParam(@NotNull String startLatlong, @NotNull String destinationLatlong, @NotNull String mapkey,
                         Context mainActivity) {
        this.Key = mapkey;
        this.strdestinationLatlong = destinationLatlong;
        this.strstartLatlong = startLatlong;
        this.mContext = mainActivity;
        if(directionList ==  null){
            directionList = new MutableLiveData<ArrayList<RouterModal>>();

        }
        ApiInterface api = getRetrofit().create(ApiInterface.class);
        Call<ArrayList<RouterModal>> call = api.getMapRoutes(strstartLatlong,strdestinationLatlong,Key);
        call.enqueue(new Callback<ArrayList<RouterModal>>() {
            @Override
            public void onResponse(Call<ArrayList<RouterModal>> call, Response<ArrayList<RouterModal>> response) {
                Log.e("rrrrrr",response.body().toString()+"null");
                if(response.code() == 200){
                    directionList.setValue(response.body());
                }else{
                    try {
                        String Error = response.errorBody().string();
                        Log.e("Error",Error);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            @Override
            public void onFailure(Call<ArrayList<RouterModal>> call, Throwable t) {

                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("OnFails:",t.getMessage()+"Call"+call.toString());

            }
        });
        return directionList;
    }
}
