package com.example.holamundo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mensaje1(View view)
    {
        Toast.makeText(this, "¡Hola!", Toast.LENGTH_SHORT).show();
    }

    public void mensaje2(View view)
    {
        Toast.makeText(this, "Bien, ¿y tú?", Toast.LENGTH_SHORT).show();
    }

    public void mensaje3(View view)
    {
        Toast.makeText(this, "Que estés bien.", Toast.LENGTH_SHORT).show();
    }

}