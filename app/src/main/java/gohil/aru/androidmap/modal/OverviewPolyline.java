
package gohil.aru.androidmap.modal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OverviewPolyline implements Parcelable
{

    @SerializedName("points")
    @Expose
    private String points;
    public final static Creator<OverviewPolyline> CREATOR = new Creator<OverviewPolyline>() {


        @SuppressWarnings({
            "unchecked"
        })
        public OverviewPolyline createFromParcel(Parcel in) {
            return new OverviewPolyline(in);
        }

        public OverviewPolyline[] newArray(int size) {
            return (new OverviewPolyline[size]);
        }

    }
    ;

    protected OverviewPolyline(Parcel in) {
        this.points = ((String) in.readValue((String.class.getClassLoader())));
    }

    public OverviewPolyline() {
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(points);
    }

    public int describeContents() {
        return  0;
    }

}
