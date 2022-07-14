package com.example.torontoparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);


        Intent i = getIntent();

        if ((i.getStringExtra("handicapSpaces").isEmpty())) {
            ((TextView)findViewById(R.id.allHandicapParkingSpots)).setText("Unknown");
        }
        else {
            ((TextView)findViewById(R.id.allHandicapParkingSpots))
                    .setText(i.getStringExtra("handicapSpaces"));
        }

        if ((i.getStringExtra("parkingSpaces").isEmpty())) {
            ((TextView)findViewById(R.id.allParkingSpots)).setText("Unknown");
        }
        else {
            ((TextView)findViewById(R.id.allParkingSpots)).setText(i.getStringExtra("parkingSpaces"));
        }
        ((TextView)findViewById(R.id.tv_lon)).setText(i.getStringExtra("access").concat(" Parking Lot"));
        ((TextView)findViewById(R.id.parkingLotName)).setText(i.getStringExtra("name"));
        ((TextView)findViewById(R.id.tv_lat2)).setText(i.getStringExtra("assetNumber"));

        Button favoriteButton = (Button) findViewById(R.id.favoriteParkingButton);
        Button reserveButton = (Button) findViewById(R.id.reservationButton);

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.loggedIn) {
                    if (favoriteButton.getText().equals("Favorite")) {
                        favoriteButton.setText("Unfavorite");
                        Toast.makeText(InformationActivity.this, "Successfully Favorited!", Toast.LENGTH_LONG).show();
                    }
                    else {
                        favoriteButton.setText("Favorite");
                    }
                }
                else {
                    Toast.makeText(InformationActivity.this, "Login to favorite a parking!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(InformationActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            }
        });

        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.loggedIn) {

                }
                else {
                    Toast.makeText(InformationActivity.this, "Login to reserve a parking!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(InformationActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            }
        });



    }
}