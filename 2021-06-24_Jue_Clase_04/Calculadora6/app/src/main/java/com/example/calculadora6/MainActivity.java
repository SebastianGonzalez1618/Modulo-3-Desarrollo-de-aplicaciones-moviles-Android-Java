package com.example.calculadora6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private EditText et_num1, et_num2;
    private TextView tv_resultado_sumar, tv_resultado_restar;
    private TextView tv_resultado_multiplicar, tv_resultado_dividir;
    private Switch sw_sumar, sw_restar, sw_multiplicar, sw_dividir;
    private Double num1, num2, resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_num1 = findViewById(R.id.et_num1);
        et_num2 = findViewById(R.id.et_num2);
        tv_resultado_sumar = findViewById(R.id.tv_resultado_sumar);
        tv_resultado_restar = findViewById(R.id.tv_resultado_restar);
        tv_resultado_multiplicar = findViewById(R.id.tv_resultado_multiplicar);
        tv_resultado_dividir = findViewById(R.id.tv_resultado_dividir);
        sw_sumar = findViewById(R.id.sw_sumar);
        sw_restar = findViewById(R.id.sw_restar);
        sw_multiplicar = findViewById(R.id.sw_multiplicar);
        sw_dividir = findViewById(R.id.sw_dividir);
    }

    public void calcular(View v) {
        if (cargarNumeros()) {
            if (sw_sumar.isChecked()) {
                sumar();
            }
            else
            {
                tv_resultado_sumar.setText("");
            }

            if (sw_restar.isChecked())
            {
                restar();
            }
            else
            {
                tv_resultado_restar.setText("");
            }

            if (sw_multiplicar.isChecked())
            {
                multiplicar();
            }
            else
            {
                tv_resultado_multiplicar.setText("");
            }

            if (sw_dividir.isChecked())
            {
                dividir();
            }
            else
            {
                tv_resultado_dividir.setText("");
            }
        }
    }

    public void sumar()
    {
            resultado = num1 + num2;
            mostrarResultado(tv_resultado_sumar, resultado);
    }

    public void restar()
    {
             resultado = num1 - num2;
            mostrarResultado(tv_resultado_restar, resultado);
    }

    public void multiplicar()
    {
            resultado = num1 * num2;
            mostrarResultado(tv_resultado_multiplicar, resultado);
    }

    public void dividir()
    {
        if (num2 == 0)
        {
            mostrarMensaje(tv_resultado_dividir,getString(R.string.div_0));
        }
        else
        {
            resultado = num1 / num2;
            mostrarResultado(tv_resultado_dividir, resultado);
        }
    }

    // Si ambos n??meros fueron ingresados correctamente, almacenar sus valores en variables double.
    // De lo contrario, borrar los resultados, deseleccionar los CheckBox
    // y solicitar que se ingrese ambos n??meros.
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
            tv_resultado_sumar.setText("");
            tv_resultado_restar.setText("");
            tv_resultado_multiplicar.setText("");
            tv_resultado_dividir.setText("");
            sw_sumar.setChecked(false);
            sw_restar.setChecked(false);
            sw_multiplicar.setChecked(false);
            sw_dividir.setChecked(false);
            Toast.makeText(this, getString(R.string.pedir_numeros), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    // Validar que efectivamente se haya ingresado un n??mero, y no signos "-" o puntos sin d??gitos.
    public boolean esNumeroValido(String num)
    {
        return !(num.replace(".", "").replace("-", "").equals(""));
    }

    // Eliminar los decimales de un n??mero si no son significativos.
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

    public void mostrarResultado(TextView texto, double resultado)
    {
        texto.setTextSize(TypedValue.COMPLEX_UNIT_SP,36);
        texto.setText(formatear(String.valueOf(resultado)));
    }


    public void mostrarMensaje(TextView texto, String mensaje)
    {
        texto.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
        texto.setText(mensaje);
    }

}