package com.example.torontoparking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.SecureRandom;
import java.util.Random;

public class ConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[8];
        random.nextBytes(bytes);

        TextView date = (TextView) findViewById(R.id.parkingDate);
        TextView fullName = (TextView) findViewById(R.id.parkingFullName);
        TextView confirmation = (TextView) findViewById(R.id.parkingConfirmationNumber);
        TextView parkingSpace = (TextView) findViewById(R.id.parkingSpaceNumberConfirmation);
        TextView vehicle = (TextView) findViewById(R.id.parkingVehicleConfirmation);
        TextView parkingLotName = (TextView) findViewById(R.id.parkingLotConfirmation);

        confirmation.setText(bytes.toString());
        Random random2 = new Random();


        if (!getIntent().getStringExtra("parkingSpaces").isEmpty()) {
            int max = Integer.parseInt(getIntent().getStringExtra("parkingSpaces"));
            parkingSpace.setText("Parking space".concat(" ").concat(String.valueOf(random2.nextInt(max) + 1)));

        }



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");

        databaseReference.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile != null) {

                    fullName.setText("for ".concat(userProfile.firstName.concat(" ").concat(userProfile.lastName)));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ConfirmationActivity.this, "Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });

        date.setText(getIntent().getStringExtra("date").concat(" at ").concat(getIntent().
                getStringExtra("time")).concat(" ").concat(getIntent().
                getStringExtra("period")).concat(" for ").concat(getIntent().getStringExtra
                ("duration")));
        vehicle.setText(getIntent().getStringExtra("vehicle"));
        parkingLotName.setText("at ".concat(getIntent().getStringExtra("name")));

    }
    @Override
    public void onBackPressed() {
        Intent a = new Intent(ConfirmationActivity.this, MainActivity.class);
        startActivity(a);
    }
}