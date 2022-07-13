package com.example.torontoparking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SearchView searchView = findViewById(R.id.searchView);

        RecyclerView recyclerView = findViewById(R.id.RecyclerView);

        ArrayList<ParkingLot> parkingLots = ReadCSV.readCSV(getResources().openRawResource(R.raw.parking));

        recyclerAdapter adapter = new recyclerAdapter(this, parkingLots);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SearchActivity context = this;

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<ParkingLot> filteredList = new ArrayList<>();
                for (ParkingLot p: parkingLots) {
                    if (p.name.toLowerCase().contains(newText.toLowerCase())) {
                        filteredList.add(p);
                    }
                }
                adapter.filterList(filteredList);
                return true;
            }
        });


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent i = new Intent(context, InformationActivity.class);
                        ParkingLot currentParking = parkingLots.get(position);
                        i.putExtra("assetNumber", currentParking.assetNumber);
                        i.putExtra("name", currentParking.name);
                        i.putExtra("parkingSpaces", currentParking.parkingSpaces);
                        i.putExtra("handicapSpaces", currentParking.handicapSpaces);
                        i.putExtra("lat", currentParking.getLat());
                        i.putExtra("long", currentParking.getLong());
                        i.putExtra("access", currentParking.access);
                        startActivity(i);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }

}