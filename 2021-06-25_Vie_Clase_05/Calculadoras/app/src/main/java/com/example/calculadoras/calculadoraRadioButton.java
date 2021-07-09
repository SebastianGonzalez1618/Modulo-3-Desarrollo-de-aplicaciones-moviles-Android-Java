package com.example.calculadoras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class calculadoraRadioButton extends AppCompatActivity {

    private EditText et_num1, et_num2;
    private TextView tv_resultado;
    private RadioButton rb_sumar, rb_restar, rb_multiplicar, rb_dividir;
    private Double num1, num2, resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_radio_button);

        et_num1 = findViewById(R.id.et_num1);
        et_num2 = findViewById(R.id.et_num2);
        tv_resultado = findViewById(R.id.txt_resultado);
        rb_sumar = findViewById(R.id.rb_sumar);
        rb_restar = findViewById(R.id.rb_restar);
        rb_multiplicar = findViewById(R.id.rb_multiplicar);
        rb_dividir = findViewById(R.id.rb_dividir);
    }

    public void volver(View v)
    {
        Intent inicio = new Intent(this, MainActivity.class);
        startActivity(inicio);
    }

    public void calcular(View v)
    {
        if(rb_sumar.isChecked())
        {
            sumar();
        }
        else if (rb_restar.isChecked())
        {
            restar();
        }
        else if (rb_multiplicar.isChecked())
        {
            multiplicar();
        }
        else if (rb_dividir.isChecked())
        {
            dividir();
        }
        else
        {
            mostrarMensaje("Seleccione una operación.");
        }
    }


    public void sumar()
    {
        if (cargarNumeros())
        {
            resultado = num1 + num2;
            mostrarResultado(resultado);
        }
    }


    public void restar()
    {
        if (cargarNumeros())
        {
            resultado = num1 - num2;
            mostrarResultado(resultado);
        }
    }


    public void multiplicar()
    {
        if (cargarNumeros())
        {
            resultado = num1 * num2;
            mostrarResultado(resultado);
        }
    }


    public void dividir()
    {
        if (cargarNumeros())
        {
            if (num2 == 0)
            {
                mostrarMensaje("La división por 0 no está definida.");
            }
            else
            {
                resultado = num1 / num2;
                mostrarResultado(resultado);
            }
        }
    }


    // Si ambos números fueron ingresados correctamente, almacenar sus valores en variables double.
    // De lo contrario, solicitar que se ingrese ambos números.
    public boolean cargarNumeros()
    {
        String string_num1 = et_num1.getText().toString();
        String string_num2 = et_num2.getText().toString();
        if (esNumeroValido(string_num1) && esNumeroValido(string_num2))
        {
            num1 = Double.parseDouble(string_num1);
            num2 = Double.parseDouble(string_num2);
            return true;
        }
        else
        {
            mostrarMensaje("Ingrese ambos números.");
            return false;
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


    public void mostrarResultado(double resultado)
    {
        tv_resultado.setTextSize(TypedValue.COMPLEX_UNIT_SP,48);
        tv_resultado.setText(formatear(String.valueOf(resultado)));
    }


    public void mostrarMensaje(String mensaje)
    {
        tv_resultado.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
        tv_resultado.setText(mensaje);
    }
}