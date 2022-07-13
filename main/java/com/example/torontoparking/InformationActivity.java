package com.example.torontoparking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.MapFragment;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);


        Intent i = getIntent();


        ((TextView)findViewById(R.id.tv_accuracy)).setText(i.getStringExtra("handicapSpaces"));
        ((TextView)findViewById(R.id.tv_lon)).setText(i.getStringExtra("access").concat(" Parking Lot"));
        ((TextView)findViewById(R.id.tv_altitude)).setText(i.getStringExtra("parkingSpaces"));
        ((TextView)findViewById(R.id.parkingLotName)).setText(i.getStringExtra("name"));
        ((TextView)findViewById(R.id.tv_lat2)).setText(i.getStringExtra("assetNumber"));



    }
}