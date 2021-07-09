package com.example.calculadoras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void irImageButton(View v)
    {
        Intent calculadoraImageButton = new Intent(this, calculadoraImageButton.class);
        startActivity(calculadoraImageButton);
    }

    public void irRadioButton(View v)
    {
        Intent calculadoraRadioButton = new Intent(this, calculadoraRadioButton.class);
        startActivity(calculadoraRadioButton);
    }

        public void irCheckBox(View v)
    {
        Intent calculadoraCheckBox = new Intent(this, calculadoraCheckBox.class);
        startActivity(calculadoraCheckBox);
    }

        public void irSwitch(View v)
    {
        Intent calculadoraSwitch = new Intent(this, calculadoraSwitch.class);
        startActivity(calculadoraSwitch);
    }

    public void irSpinner(View v)
    {
        Intent calculadoraSpinner = new Intent(this, calculadoraSpinner.class);
        startActivity(calculadoraSpinner);
    }

}