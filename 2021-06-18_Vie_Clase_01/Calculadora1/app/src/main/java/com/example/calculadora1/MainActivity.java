package com.example.calculadora1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText num1, num2;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.et_num1);
        num2 = findViewById(R.id.et_num2);

        resultado = findViewById(R.id.txt_resultado);
    }

    public void sumar(View v)
    {
        if (esNumeroValido(num1.getText().toString()) && esNumeroValido(num2.getText().toString()) )
        {
            double valor1 = Double.parseDouble(num1.getText().toString());
            double valor2 = Double.parseDouble(num2.getText().toString());
            double suma = valor1 + valor2;
            resultado.setTextSize(TypedValue.COMPLEX_UNIT_SP,48);
            resultado.setText(formatear(String.valueOf(suma)));
            resultado.setTextSize(TypedValue.COMPLEX_UNIT_SP,48);
        }
        else
        {
            resultado.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
            resultado.setText("Ingrese ambos números.");
            resultado.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
        }

    }

    // Validar que efectivamente se haya ingresado un número, y no signos "-" o puntos sin dígitos.
    public boolean esNumeroValido(String num)
    {
        return !(num.replace(".", "").replace("-", "").equals(""));
    }

    // Eliminar los decimales de un número si no son significativos.
    public String formatear(String numero)
    {
        String parteEntera = numero.split("\\.")[0];
        double parteDecimal = Double.parseDouble(numero.split("\\.")[1]);

        if (parteDecimal == 0)
        {
            return parteEntera;
        }
        else
        {
            return numero;
        }
    }

}