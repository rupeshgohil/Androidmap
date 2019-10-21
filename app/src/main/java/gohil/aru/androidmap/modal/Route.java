
package gohil.aru.androidmap.modal;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Route implements Parcelable
{

    /*@SerializedName("bounds")
    @Expose
    private Bounds bounds;*/
   /* @SerializedName("copyrights")
    @Expose
    private String copyrights;*/
    @SerializedName("legs")
    @Expose
    private ArrayList<Leg> legs = new ArrayList<>();
    @SerializedName("overview_polyline")
    @Expose
    private OverviewPolyline overviewPolyline;
    @SerializedName("summary")
    @Expose
    private String summary;
   /* @SerializedName("warnings")
    @Expose
    private ArrayList<Object> warnings = null;
    @SerializedName("waypoint_order")
    @Expose
    private ArrayList<Object> waypointOrder = null;*/
    public final static Creator<Route> CREATOR = new Creator<Route>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Route createFromParcel(Parcel in) {
            return new Route(in);
        }

        public Route[] newArray(int size) {
            return (new Route[size]);
        }

    }
    ;

    protected Route(Parcel in) {
       // this.bounds = ((Bounds) in.readValue((Bounds.class.getClassLoader())));
       // this.copyrights = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.legs, (Leg.class.getClassLoader()));
        this.overviewPolyline = ((OverviewPolyline) in.readValue((OverviewPolyline.class.getClassLoader())));
        this.summary = ((String) in.readValue((String.class.getClassLoader())));
       // in.readList(this.warnings, (Object.class.getClassLoader()));
       // in.readList(this.waypointOrder, (Object.class.getClassLoader()));
    }

    public Route() {
    }

   /* public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }*/

   /* public String getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(String copyrights) {
        this.copyrights = copyrights;
    }*/

    public ArrayList<Leg> getLegs() {
        return legs;
    }

    public void setLegs(ArrayList<Leg> legs) {
        this.legs = legs;
    }

    public OverviewPolyline getOverviewPolyline() {
        return overviewPolyline;
    }

    public void setOverviewPolyline(OverviewPolyline overviewPolyline) {
        this.overviewPolyline = overviewPolyline;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

   /*// public ArrayList<Object> getWarnings() {
        return warnings;
    }

    public void setWarnings(ArrayList<Object> warnings) {
        this.warnings = warnings;
    }

    public ArrayList<Object> getWaypointOrder() {
        return waypointOrder;
    }

    public void setWaypointOrder(ArrayList<Object> waypointOrder) {
        this.waypointOrder = waypointOrder;
    }
*/
    public void writeToParcel(Parcel dest, int flags) {
       /* dest.writeValue(bounds);*/
      //  dest.writeValue(copyrights);
        dest.writeList(legs);
        dest.writeValue(overviewPolyline);
        dest.writeValue(summary);
       /* dest.writeList(warnings);
        dest.writeList(waypointOrder);*/
    }

    public int describeContents() {
        return  0;
    }

}
