package com.example.calculadora7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private EditText et_num1, et_num2;
    private TextView tv_resultado;
    private Double num1, num2, resultado;
    private Spinner sp_operaciones;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_num1 = findViewById(R.id.et_num1);
        et_num2 = findViewById(R.id.et_num2);
        tv_resultado = findViewById(R.id.tv_resultado);
        sp_operaciones = findViewById(R.id.sp_operaciones);
        String[] operaciones =
            {
              getString(R.string.sp_sumar)
            , getString(R.string.sp_restar)
            , getString(R.string.sp_multiplicar)
            , getString(R.string.sp_dividir)
            };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, operaciones);
        sp_operaciones.setAdapter(adapter);
    }

    public void calcular(View v) {
        String opcion = sp_operaciones.getSelectedItem().toString();
        if (cargarNumeros()) {
            if (opcion.equals(getString(R.string.sp_sumar))) {
                sumar();
            }
            else if (opcion.equals(getString(R.string.sp_restar)))
            {
                restar();
            }
            else if (opcion.equals(getString(R.string.sp_multiplicar)))
            {
                multiplicar();
            }
            else if (opcion.equals(getString(R.string.sp_dividir)))
            {
                dividir();
            }
            else
            {
                tv_resultado.setText("");
            }
        }
    }

    public void sumar()
    {
            resultado = num1 + num2;
            mostrarResultado(tv_resultado, resultado);
    }

    public void restar()
    {
             resultado = num1 - num2;
            mostrarResultado(tv_resultado, resultado);
    }

    public void multiplicar()
    {
            resultado = num1 * num2;
            mostrarResultado(tv_resultado, resultado);
    }

    public void dividir()
    {
        if (num2 == 0)
        {
            tv_resultado.setText("");
            Toast.makeText(this, getString(R.string.div_0), Toast.LENGTH_SHORT).show();
        }
        else
        {
            resultado = num1 / num2;
            mostrarResultado(tv_resultado, resultado);
        }
    }

    // Si ambos números fueron ingresados correctamente, almacenar sus valores en variables double.
    // De lo contrario, borrar los resultados, deseleccionar los CheckBox
    // y solicitar que se ingrese ambos números.
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
            tv_resultado.setText("");
            Toast.makeText(this, getString(R.string.pedir_numeros), Toast.LENGTH_SHORT).show();
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

    public void mostrarResultado(TextView texto, double resultado)
    {
        texto.setTextSize(TypedValue.COMPLEX_UNIT_SP,48);
        texto.setText(formatear(String.valueOf(resultado)));
    }

}