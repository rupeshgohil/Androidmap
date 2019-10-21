
package gohil.aru.androidmap.modal;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RouterModal implements Parcelable
{

    @SerializedName("geocoded_waypoints")
    @Expose
    private ArrayList<GeocodedWaypoint> geocodedWaypoints = new ArrayList<>();
    @SerializedName("routes")
    @Expose
    private ArrayList<Route> routes = new ArrayList<>();
    /*@SerializedName("status")
    @Expose
    private String status;*/
    public final static Creator<RouterModal> CREATOR = new Creator<RouterModal>() {


        @SuppressWarnings({
            "unchecked"
        })
        public RouterModal createFromParcel(Parcel in) {
            return new RouterModal(in);
        }

        public RouterModal[] newArray(int size) {
            return (new RouterModal[size]);
        }

    }
    ;

    protected RouterModal(Parcel in) {
        in.readList(this.geocodedWaypoints, (GeocodedWaypoint.class.getClassLoader()));
        in.readList(this.routes, (Route.class.getClassLoader()));
       // this.status = ((String) in.readValue((String.class.getClassLoader())));
    }

    public RouterModal() {
    }

    public List<GeocodedWaypoint> getGeocodedWaypoints() {
        return geocodedWaypoints;
    }

    public void setGeocodedWaypoints(ArrayList<GeocodedWaypoint> geocodedWaypoints) {
        this.geocodedWaypoints = geocodedWaypoints;
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<Route> routes) {
        this.routes = routes;
    }

   /* public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }*/

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(geocodedWaypoints);
        dest.writeList(routes);
        //dest.writeValue(status);
    }

    public int describeContents() {
        return  0;
    }

}
