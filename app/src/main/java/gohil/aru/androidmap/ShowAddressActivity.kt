package gohil.aru.androidmap

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import gohil.aru.androidmap.adapter.AddressAdapter
import gohil.aru.androidmap.listener.setdataListener
import gohil.aru.androidmap.modal.AddressModal

import kotlinx.android.synthetic.main.activity_show_address.*

class ShowAddressActivity : BaseActivity(){

    var mArray = ArrayList<AddressModal>()
    var mAdapter: AddressAdapter? = null
    var mModal = AddressModal()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_address)
        intent = intent
       if(intent != null) {
           // mModal = intent.getSerializableExtra("address") as AddressModal
           mArray = intent.getSerializableExtra("address") as ArrayList<AddressModal>
           Log.e("address", Gson().toJson(mArray))
            setview(mArray)
        }

    }
    private fun setview(mArray: ArrayList<AddressModal>) {
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        linearLayoutManager.reverseLayout = false
        rladdress!!.setHasFixedSize(true)
        rladdress!!.layoutManager = linearLayoutManager
        mAdapter = AddressAdapter(this,mArray)
        rladdress.adapter = mAdapter
    }

}
