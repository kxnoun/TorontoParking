package com.example.torontoparking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private EditText firstName, lastName, email, password;
    private ProgressBar progressBar;
    private Button registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);

        firstName = (EditText) findViewById(R.id.editTextRegisterFirst);
        lastName = (EditText) findViewById(R.id.editTextRegisterLast);
        email = (EditText) findViewById(R.id.editTextRegisterEmailAddress);
        password = (EditText) findViewById(R.id.editTextRegisterPassword);

        progressBar = (ProgressBar) findViewById(R.id.progressBarRegister);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.registerButton) {
            registerUser();
        }

    }

    private void registerUser() {
        String emailText = email.getText().toString().trim();
        String passwordText = password.getText().toString().trim();
        String firstText = firstName.getText().toString().trim();
        String lastText = lastName.getText().toString().trim();

        if (firstText.isEmpty()) {
            firstName.setError("Please Provide your First Name");
            firstName.requestFocus();
            return;
        }
        if (lastText.isEmpty()) {
            lastName.setError("Please Provide your Last Name");
            lastName.requestFocus();
            return;
        }
        if (emailText.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            email.setError("Please Provide a Valid Email");
            email.requestFocus();
            return;
        }
        if (passwordText.isEmpty() || passwordText.length() < 6) {
            password.setError("Please provide a password longer than 5 characters");
            password.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    User user = new User(firstText, lastText, emailText);

                    FirebaseDatabase.getInstance().getReference("users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Register.this, "Success! You have been registered!", Toast.LENGTH_LONG).show();

                                        Intent i = new Intent(Register.this, LoginActivity.class);
                                        startActivity(i);
                                    }
                                    else {
                                        Toast.makeText(Register.this, "Failed to register. Please try again.", Toast.LENGTH_LONG).show();
                                    }
                                    progressBar.setVisibility(View.GONE);
                                }
                            });
                }
                else {
                    Toast.makeText(Register.this, "Failed to register. Please try again.", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });


    }
}