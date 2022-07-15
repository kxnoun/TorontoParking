package com.example.torontoparking;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReservationActivity2 extends AppCompatActivity implements View.OnClickListener {

    Spinner dateSpinner;
    Spinner timeSpinner;
    Spinner durationSpinner;
    Spinner periodSpinner;
    Button registerTimings;
    CheckBox handicapCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation2);



        dateSpinner = (Spinner) findViewById(R.id.dateSpinner);
        timeSpinner = (Spinner) findViewById(R.id.timeSpinner);
        durationSpinner = (Spinner) findViewById(R.id.durationSpinner);
        periodSpinner = (Spinner) findViewById(R.id.periodSpinner);
        registerTimings = (Button) findViewById(R.id.registerTimings);
        CheckBox handicapCheck = (CheckBox) findViewById(R.id.handicapCheck);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.day, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateSpinner.setAdapter(adapter);
        dateSpinner.setOnItemSelectedListener(new DateSpinnerClass());

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.times, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(adapter1);
        timeSpinner.setOnItemSelectedListener(new TimeSpinnerClass());

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.period, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        periodSpinner.setAdapter(adapter2);
        periodSpinner.setOnItemSelectedListener(new PeriodSpinnerClass());

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.duration, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        durationSpinner.setAdapter(adapter3);
        durationSpinner.setOnItemSelectedListener(new DurationSpinnerClass());

        registerTimings.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.registerTimings) registerTime();
    }

    private void registerTime() {
        if (DateSpinnerClass.dateNumberChoice == 0) {
            dateSpinner.requestFocus();
            Toast.makeText(ReservationActivity2.this, "Please choose a date!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TimeSpinnerClass.timeNumberChoice == 0) {
            timeSpinner.requestFocus();
            Toast.makeText(ReservationActivity2.this, "Please choose a time!", Toast.LENGTH_LONG).show();
            return;
        }
        if (PeriodSpinnerClass.periodNumberChoice == 0) {
            periodSpinner.requestFocus();
            Toast.makeText(ReservationActivity2.this, "Please choose a period!", Toast.LENGTH_LONG).show();
            return;
        }
        if (DurationSpinnerClass.durationNumberChoice == 0) {
            durationSpinner.requestFocus();
            Toast.makeText(ReservationActivity2.this, "Please choose a duration!", Toast.LENGTH_LONG).show();
            return;
        }
    }

    static class DateSpinnerClass implements AdapterView.OnItemSelectedListener
    {
        public static int dateNumberChoice;
        public static String dateChoice;


        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            dateChoice = adapterView.getItemAtPosition(i).toString();
            dateNumberChoice = i;
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    static class TimeSpinnerClass implements AdapterView.OnItemSelectedListener
    {
        public static int timeNumberChoice;
        public static String timeChoice;


        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            timeChoice = adapterView.getItemAtPosition(i).toString();
            timeNumberChoice = i;
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    static class PeriodSpinnerClass implements AdapterView.OnItemSelectedListener
    {
        public static int periodNumberChoice;
        public static String periodChoice;


        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            periodChoice = adapterView.getItemAtPosition(i).toString();
            periodNumberChoice = i;
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    static class DurationSpinnerClass implements AdapterView.OnItemSelectedListener
    {
        public static int durationNumberChoice;
        public static String durationChoice;


        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            durationChoice = adapterView.getItemAtPosition(i).toString();
            durationNumberChoice = i;
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}