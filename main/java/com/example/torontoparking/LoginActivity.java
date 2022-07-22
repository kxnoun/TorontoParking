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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText emailLogin, passwordLogin;
    private Button loginButton;
    private ProgressBar progressBar;
    private TextView register, forgotPassword;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = (TextView) findViewById(R.id.registerLogin);
        register.setOnClickListener(this);

        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(this);

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);

        emailLogin = (EditText) findViewById(R.id.editTextLoginEmailAddress);
        passwordLogin = (EditText) findViewById(R.id.editTextLoginPassword);

        progressBar = (ProgressBar) findViewById(R.id.progressBarLogin);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.registerLogin) {
            Intent i = new Intent(this, Register.class);
            startActivity(i);
        }
        else if (view.getId() == R.id.loginButton) {
            loginUser();
        }
    }

    private void loginUser() {
        String emailText = emailLogin.getText().toString().trim();
        String passwordText = passwordLogin.getText().toString().trim();

        if (emailText.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            emailLogin.setError("Please Provide a Valid Email");
            emailLogin.requestFocus();
            return;
        }

        if (passwordText.isEmpty() || passwordText.length() < 6) {
            passwordLogin.setError("Please provide a password longer than 5 characters");
            passwordLogin.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    MainActivity.loggedIn = true;
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);

                    if (user.isEmailVerified()) {
                        startActivity(i);
                    }
                    else {
                        user.sendEmailVerification();
                        Toast.makeText(LoginActivity.this, "Check your email to verify your account!", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this, "Login Failed! Please try again.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent a = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(a);
    }
}