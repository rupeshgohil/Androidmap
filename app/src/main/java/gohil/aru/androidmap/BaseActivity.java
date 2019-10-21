package gohil.aru.androidmap;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BaseActivity extends AppCompatActivity {
    public String straddress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public String getCurrentCity(double latitude,double logitude) {

            String result = "Latitude: " +latitude +
                    " Longitude: " + logitude;
            Log.e("LatLong", result);
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = new ArrayList<>();
            try {
                addresses = geocoder.getFromLocation(latitude, logitude, 1);

                // Log.e("CIDT: ", addresses.get(0).getLocality()+"");
                if (addresses.size() > 0) {
                    straddress = addresses.get(0).getAddressLine(0) ;

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return straddress;
    }

}
