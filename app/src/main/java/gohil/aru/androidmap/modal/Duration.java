
package gohil.aru.androidmap.modal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Duration implements Parcelable
{

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("value")
    @Expose
    private Integer value;
    public final static Creator<Duration> CREATOR = new Creator<Duration>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Duration createFromParcel(Parcel in) {
            return new Duration(in);
        }

        public Duration[] newArray(int size) {
            return (new Duration[size]);
        }

    }
    ;

    protected Duration(Parcel in) {
        this.text = ((String) in.readValue((String.class.getClassLoader())));
        this.value = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Duration() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(text);
        dest.writeValue(value);
    }

    public int describeContents() {
        return  0;
    }

}
