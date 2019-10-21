package gohil.aru.androidmap

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
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
import android.content.Intent
import android.graphics.Color
import androidx.lifecycle.Observer
import com.google.android.gms.maps.model.*
import gohil.aru.androidmap.modal.AddressModal
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import gohil.aru.androidmap.modal.RouterModal
import gohil.aru.androidmap.viewmodal.DirectionViewModal
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), OnMapReadyCallback {

     var mMap: GoogleMap? = null
     var supportMapFragment: SupportMapFragment?=null
    var mgpstraker:GPSTracker? = null
    var latitude = 0.0
    var logitute = 0.0
    var mMarkeroption: MarkerOptions?= null
    var mArraLatlog=  ArrayList<LatLng>()
    internal var routelist = ArrayList<LatLng>()
    var mArrapathlatlong=  ArrayList<LatLng>()
    var mPolyline: Polyline?= null
    var mDirectionviewModal: DirectionViewModal?= null
    var strStartLatlong:String? =null
    var strEndLatlong:String? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mDirectionviewModal =  ViewModelProviders.of(this).get(DirectionViewModal::class.java)
       // mDirectionviewModal!!.setParam("","","",this@MainActivity)
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
        mArraLatlog!!.add(LatLng(23.4742474,72.3899406))
        mArraLatlog!!.add(LatLng(23.8459622,72.1284458))
        mArraLatlog!!.add(LatLng(22.3022178,70.788317))
        mArraLatlog!!.add(LatLng(21.123693,72.7365433))
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
               // setPath(locAddress,LatLng(latitude,logitute))
            strStartLatlong = latitude.toString()+","+logitute.toString()
            strEndLatlong = locAddress.latitude.toString()+","+locAddress.longitude.toString()
            Log.e("MainActivity","start_latlong"+strStartLatlong+"--EndLatlong--"+strEndLatlong)
           // mDirectionviewModal!!.setParam(strStartLatlong!!,strEndLatlong!!,"AIzaSyAE5x_dHCWdwTcQBb8BcbT-mCOwaxC-BL4",this@MainActivity)
            mDirectionviewModal!!.setParam(strStartLatlong!!,strEndLatlong!!,"AIzaSyAE5x_dHCWdwTcQBb8BcbT-mCOwaxC-BL4",this@MainActivity).observe(this,
                Observer<ArrayList<RouterModal>> { directionArray ->
                    /*var mDirection  = DirectionResults()

                    if(directionArray.get(0).routes.size > 0){

                    }*/
                    Log.e("REsponse",Gson().toJson(directionArray))
                })
            true
        })

    }

    private fun getResult() {

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
