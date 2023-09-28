package com.example.temperaturecoverter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextTemperature;
    RadioGroup radioGroupFrom, radioGroupTo;
    Button buttonConvert;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTemperature = findViewById(R.id.editTextTemperature);
        radioGroupFrom = findViewById(R.id.radioGroupFrom);
        radioGroupTo = findViewById(R.id.radioGroupTo);
        buttonConvert = findViewById(R.id.buttonConvert);
        textViewResult = findViewById(R.id.textViewResult);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertTemperature();
            }
        });
    }

    private void convertTemperature() {

        double inputTemperature = Double.parseDouble(editTextTemperature.getText().toString());


        int selectedFromId = radioGroupFrom.getCheckedRadioButtonId();
        int selectedToId = radioGroupTo.getCheckedRadioButtonId();


        RadioButton radioButtonFrom = findViewById(selectedFromId);
        RadioButton radioButtonTo = findViewById(selectedToId);

        String fromUnit = radioButtonFrom.getText().toString();
        String toUnit = radioButtonTo.getText().toString();

        double resultTemperature = convert(inputTemperature, fromUnit, toUnit);

        textViewResult.setText("Converted temperature: " + resultTemperature + " " + toUnit);
    }

    private double convert(double inputTemperature, String fromUnit, String toUnit) {
        double resultTemperature;

        switch (fromUnit) {
            case "Celsius":
                if (toUnit.equals("Fahrenheit")) {
                    resultTemperature = (inputTemperature * 9 / 5) + 32;
                } else {
                    resultTemperature = inputTemperature + 273.15;
                }
                break;

            case "Fahrenheit":
                if (toUnit.equals("Celsius")) {
                    resultTemperature = (inputTemperature - 32) * 5 / 9;
                } else {
                    resultTemperature = ((inputTemperature - 32) * 5 / 9) + 273.15;
                }
                break;

            default:
                if (toUnit.equals("Celsius")) {
                    resultTemperature = inputTemperature - 273.15;
                } else {
                    resultTemperature = ((inputTemperature - 273.15) * 9 / 5) + 32;
                }
                break;
        }

        return resultTemperature;
    }
}
