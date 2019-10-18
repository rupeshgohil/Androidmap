package gohil.aru.androidmap

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import gohil.aru.androidmap.util.PermissionUtils
import gohil.aru.androidmap.util.PermissionUtils.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.CameraUpdateFactory
import gohil.aru.androidmap.util.GPSTracker
import android.R.color
import android.content.Intent
import android.graphics.Color
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.model.*
import gohil.aru.androidmap.modal.AddressModal
import android.provider.ContactsContract.CommonDataKinds.Note
import com.google.gson.Gson
import gohil.aru.androidmap.listener.setdataListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), OnMapReadyCallback {

     var mMap: GoogleMap? = null
     var supportMapFragment: SupportMapFragment?=null
    var mgpstraker:GPSTracker? = null
    var latitude = 0.0
    var logitute = 0.0
    var mMarkeroption: MarkerOptions?= null
    var mArraLatlog=  ArrayList<LatLng>()
    var mArrapathlatlong=  ArrayList<LatLng>()
    var mPolyline: Polyline?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(PermissionUtils.getLocationPermissionOnly(this@MainActivity)){
                    loadMap()
            }
        }else{
            loadMap()
        }
        btnshow.setOnClickListener {
            Log.e("address1", Gson().toJson(mAddressArray))
            var mIntent = Intent(this,ShowAddressActivity::class.java)
            mIntent.putExtra("address", mAddressArray)
            startActivity(mIntent)
        }
    }
    private fun loadMap() {
        mAddressArray = ArrayList<AddressModal>()
      supportMapFragment =
            supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment?
        supportMapFragment!!.getMapAsync(this)
        mArraLatlog!!.add(LatLng(22.9925317,72.3608511))
        mArraLatlog!!.add(LatLng(23.1212981,72.5392968))
        mArraLatlog!!.add(LatLng(23.2281532,72.4671326))
        mArraLatlog!!.add(LatLng(23.6051955,72.9330389))
        mgpstraker = GPSTracker(this)
        latitude = mgpstraker!!.latitude
        logitute = mgpstraker!!.longitude
        Log.e("LatLong","Logitute"+logitute+"Latitude"+latitude)

    }
    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0;
        mMap!!.setMyLocationEnabled(true)
        mMarkeroption = MarkerOptions()
        mMarkeroption!!.position(LatLng(latitude,logitute))
        var cameraUpdate = CameraUpdateFactory.newLatLngZoom(LatLng(latitude,logitute), 9.2f);
        mMap!!.animateCamera(cameraUpdate);
        mMap!!.addMarker(mMarkeroption)
        for(i in mArraLatlog.indices){
            setmarker(mArraLatlog.get(i))
        }
    }

    public fun setmarker(get: LatLng) {
        mMarkeroption = MarkerOptions()
        mMarkeroption!!.position(get)
        var cameraUpdate = CameraUpdateFactory.newLatLngZoom(get, 7.2f);
        mMap!!.animateCamera(cameraUpdate);
        mMap!!.addMarker(mMarkeroption)
        mMap!!.setOnMarkerClickListener(GoogleMap.OnMarkerClickListener { marker ->
            val locAddress = marker.position
            Log.e("Clicked","true")
            if(mPolyline != null){
                mPolyline!!.remove()
            }
                setPath(locAddress,LatLng(latitude,logitute))
            true
        })

    }

    private fun setPath(locAddress: LatLng?, latLng: LatLng) {
        val polyOptions = PolylineOptions()
        mArrapathlatlong.clear()
        mArrapathlatlong.add(locAddress!!)
        mArrapathlatlong.add(latLng!!)
        polyOptions.color(Color.RED)
        polyOptions.width(5f)
        polyOptions.addAll(mArrapathlatlong)
       mPolyline =  mMap!!.addPolyline(polyOptions)
        val builder = LatLngBounds.Builder()
        for (latLng in mArrapathlatlong) {
            builder.include(latLng)
        }
        val bounds = builder.build()
        val cu = CameraUpdateFactory.newLatLngBounds(bounds, 100)
        mMap!!.animateCamera(cu)
       Log.e("AddressCurrent",getCurrentCity(locAddress.latitude,locAddress.longitude))
        var mModal = AddressModal()
        mModal.strAddress = getCurrentCity(locAddress.latitude,locAddress.longitude)
        mAddressArray!!.add(mModal)
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.e("Requestcode","re==>"+requestCode+"re2==>"+ PermissionUtils.MY_PERMISSIONS_REQUEST_LOCATION)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {
                if (grantResults.size > 0 && grantResults[0] === PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {

                        mMap!!.setMyLocationEnabled(true)
                    }
                } else {
                    Toast.makeText(
                        this, "permission denied",
                        Toast.LENGTH_LONG
                    ).show()
                }
                return
            }
        }
    }
    override fun onResume() {
        super.onResume()

    }
    companion object{
        var mAddressArray: ArrayList<AddressModal>? =null
    }
}
