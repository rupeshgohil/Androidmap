package gohil.aru.androidmap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import gohil.aru.androidmap.R;
import gohil.aru.androidmap.modal.AddressModal;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyHolder> {
    View mView;
    ArrayList<AddressModal> mArraylist;
    public AddressAdapter(Context context,ArrayList<AddressModal> array){
        this.mArraylist =array;
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            AddressModal m = mArraylist.get(position);
            holder.tvname.setText(m.getStrAddress());
    }

    @Override
    public int getItemCount() {
        return mArraylist.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        TextView tvname;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvname = (TextView)itemView.findViewById(R.id.tvname);
        }
    }
}
