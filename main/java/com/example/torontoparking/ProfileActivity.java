package com.example.torontoparking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button logout = (Button) findViewById(R.id.logoutButton);
        Button resetButton = (Button) findViewById(R.id.resetUserData);
        Button favorites = (Button) findViewById(R.id.favoritesButton);
        Button recentlyViewed = (Button) findViewById(R.id.recentlyViewedButton);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                MainActivity.loggedIn = false;
                Intent i = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        TextView userFirstName = (TextView) findViewById(R.id.userName);
        TextView firstName = (TextView) findViewById(R.id.userFirstName);
        TextView lastName = (TextView) findViewById(R.id.userLastName);
        TextView email = (TextView) findViewById(R.id.userEmail);
        TextView phone = (TextView) findViewById(R.id.userPhone);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        databaseReference.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile != null) {
                    String greeting = "Hello, ".concat(userProfile.firstName).concat("!");
                    userFirstName.setText(greeting);
                    firstName.setText(userProfile.firstName);
                    lastName.setText(userProfile.lastName);
                    email.setText(userProfile.email);
                    phone.setText(userProfile.phoneNumber);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this, AddPhoneActivity.class);

            }
        });

    }
}