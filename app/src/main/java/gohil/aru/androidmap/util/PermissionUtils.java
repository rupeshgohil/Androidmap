package gohil.aru.androidmap.util;


import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class PermissionUtils {

    public static final int REQUEST_CODE_ACCESS_CAMERA = 100;
    public static final int REQUEST_CODE_ACCESS_CALL = 103;
    public static final int REQUEST_CODE_ACCESS_LOCATION = 101;
    public static final int REQUEST_CODE_ACCESS_STORAGE = 102;
    public static final int REQUEST_CODE_ACCESS_AUDIO = 105;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 106;

    public static final String ACCESS_AUDIO_PERMISSION = Manifest.permission.RECORD_AUDIO;
    public static final String ACCESS_CAMERA_PERMISSION = Manifest.permission.CAMERA;
    public static final String ACCESS_CALL_PERMISSION = Manifest.permission.CALL_PHONE;
    public static final String ACCESS_READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String ACCESS_WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final String ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;


    public static boolean checkForPermission(final Context context, final String permission) {

        //If permission is granted then it returns PackageManager.PERMISSION_GRANTED as result
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }


    public static boolean getCameraPermission(final Activity activity, final Fragment fragment) {

        if (checkForPermission(activity, ACCESS_CAMERA_PERMISSION) && checkForPermission(activity,
                ACCESS_READ_EXTERNAL_STORAGE)) {
            return true;
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (fragment != null) {
                    fragment.requestPermissions(new String[]{ACCESS_CAMERA_PERMISSION, ACCESS_READ_EXTERNAL_STORAGE,
                            ACCESS_WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_ACCESS_CAMERA);
                }
            }
            return false;
        }
    }

    /**
     * Method to check and request location permissions
     */
    public static boolean getLocationPermission(final Activity activity, final Fragment fragment) {

        if (checkForPermission(activity, ACCESS_FINE_LOCATION) && checkForPermission(activity,
                ACCESS_COARSE_LOCATION)) {
            return true;
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (fragment != null) {
                    fragment.requestPermissions(new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, REQUEST_CODE_ACCESS_LOCATION);
                }
            }
            return false;
        }
    }

    /**
     * Method to check and request location permissions
     */
    public static boolean getLocationPermissionOnly(Context context) {

        if (checkForPermission(context, ACCESS_FINE_LOCATION) && checkForPermission(context,
                ACCESS_COARSE_LOCATION)) {
            return true;
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (context != null) {
                    ActivityCompat.requestPermissions((Activity)context,new String[]{ACCESS_FINE_LOCATION,
                            ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
                }
            }
            return false;
        }
    }

    /**
     * Method to check and request Audio permissions
     */
    public static boolean getAudioPermission(final Activity activity, final Fragment fragment) {

        if (checkForPermission(activity, ACCESS_AUDIO_PERMISSION)) {
            return true;
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (fragment != null) {
                    fragment.requestPermissions(new String[]{ACCESS_AUDIO_PERMISSION}, REQUEST_CODE_ACCESS_AUDIO);
                }
            }
            return false;
        }
    }

    /**
     * Method to check and request Call permissions
     */
    public static boolean getCallPermission(final Activity activity, final Fragment fragment) {

        if (checkForPermission(activity, ACCESS_CALL_PERMISSION)) {
            return true;
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (fragment != null) {
                    fragment.requestPermissions(new String[]{ACCESS_CALL_PERMISSION}, REQUEST_CODE_ACCESS_CALL);
                }
            }
            return false;
        }
    }


    public static boolean showRationaleLocationPermission(final Activity activity) {

        return ActivityCompat.shouldShowRequestPermissionRationale(activity, ACCESS_FINE_LOCATION) || ActivityCompat.shouldShowRequestPermissionRationale(activity, ACCESS_FINE_LOCATION);
    }

    /**
     * Method to check and request storage permissions
     */
    public static boolean getStoragePermission(final Activity activity) {

        if (checkForPermission(activity, ACCESS_READ_EXTERNAL_STORAGE) && checkForPermission(activity,
                ACCESS_WRITE_EXTERNAL_STORAGE)) {
            return true;
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (activity != null) {
                    activity.requestPermissions(new String[]{ACCESS_READ_EXTERNAL_STORAGE, ACCESS_WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_ACCESS_STORAGE);
                }
            }
            return false;
        }
    }
    public boolean checkLocationPermission(Activity mContext) {
        if (ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(mContext,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(mContext,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                ActivityCompat.requestPermissions(mContext,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }
}