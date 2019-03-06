package com.busefisensi.efisiensiku.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.busefisensi.efisiensiku.Model.PassengerModel;
import com.busefisensi.efisiensiku.R;

import java.util.List;

public class PassengerAdapter extends RecyclerView.Adapter<PassengerAdapter.MyViewHolder> {

    private Context context;
    private List<PassengerModel> passengerList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView passenger;

        public MyViewHolder(View view){
            super(view);
            passenger = view.findViewById(R.id.tvPenumpang);
        }
    }

    public PassengerAdapter (Context context, List<PassengerModel> passengerList){
        this.context = context;
        this.passengerList = passengerList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_passengger, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        PassengerModel passengerModel = passengerList.get(position);

        holder.passenger.setText(passengerModel.getNama());

    }

    @Override
    public int getItemCount(){
        return passengerList.size();
    }
}
