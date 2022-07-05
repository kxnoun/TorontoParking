package com.example.torontoparking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder>{

    private ArrayList<ParkingLot> parkingLots;
    Context context;

    public recyclerAdapter(Context context, ArrayList<ParkingLot> parkingLots) {
        this.context = context;
        this.parkingLots = parkingLots;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView parkingName;

        public MyViewHolder(View view) {
            super(view);
            parkingName = view.findViewById(R.id.textName);
        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        String name = parkingLots.get(position).name;
        holder.parkingName.setText(name);
    }

    @Override
    public int getItemCount() {
        return parkingLots.size();
    }

    public void filterList(ArrayList<ParkingLot> filteredList) {
        parkingLots = filteredList;
        notifyDataSetChanged();
    }
}
