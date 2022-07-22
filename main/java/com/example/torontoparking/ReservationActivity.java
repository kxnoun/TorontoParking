package com.example.torontoparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ReservationActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner spinner1, spinner2;
    EditText brand, plateNumber;
    public Vehicle vehicle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        brand = (EditText) findViewById(R.id.editTextBrand);
        plateNumber = (EditText) findViewById(R.id.editTextPlateNumber);
        Button btn = (Button) findViewById(R.id.registerVehicle);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.colors, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new ColorSpinnerClass());

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.size, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new SizeSpinnerClass());


        btn.setOnClickListener(this);


    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.registerVehicle) registerUser();
    }

    public void registerUser() {
        if ((brand.getText().toString().trim().isEmpty())) {
            brand.setError("Please enter a valid brand");
            brand.requestFocus();
            return;
        }
        if ((plateNumber.getText().toString().trim().isEmpty())) {
            plateNumber.setError("Please enter a valid license plate number");
            plateNumber.requestFocus();
            return;
        }
        if (ColorSpinnerClass.choiceNumber1 == 0) {
            spinner1.requestFocus();
            Toast.makeText(ReservationActivity.this, "Please choose a color!", Toast.LENGTH_LONG).show();
            return;
        }
        if (SizeSpinnerClass.choiceNumber2 == 0) {
            spinner1.requestFocus();
            Toast.makeText(ReservationActivity.this, "Please choose a size!", Toast.LENGTH_LONG).show();
            return;
        }

        vehicle = new Vehicle(Vehicle.Colors.valueOf(ColorSpinnerClass.choice1),
                Vehicle.Size.valueOf(SizeSpinnerClass.choice2),
                brand.getText().toString().trim(),
                plateNumber.getText().toString().trim()
        );
        Intent i = new Intent(ReservationActivity.this, ReservationActivity2.class);
        i.putExtra("vehicle",
                SizeSpinnerClass.choice2.concat(" ").concat(ColorSpinnerClass.choice1).concat(" ")
                        .concat(brand.getText().toString().trim()).concat(" ").concat(plateNumber.getText().toString().trim()));
        i.putExtra("name", getIntent().getStringExtra("name"));
        i.putExtra("parkingSpaces", getIntent().getStringExtra("parkingSpaces"));
        startActivity(i);



    }



    static class ColorSpinnerClass implements AdapterView.OnItemSelectedListener
    {
        public static int choiceNumber1;
        public static String choice1;


        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            choice1 = adapterView.getItemAtPosition(i).toString();
            choiceNumber1 = i;
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    static class SizeSpinnerClass implements AdapterView.OnItemSelectedListener
    {
        public static int choiceNumber2;
        public static String choice2;
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            choice2 = adapterView.getItemAtPosition(i).toString();
            choiceNumber2 = i;
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}