package com.example.mi_perfil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class presentacion extends AppCompatActivity
{
    TextView tv_perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentacion);

        tv_perfil = findViewById(R.id.tv_perfil);

        // Obtener las respuestas del cuestionario.
        String nombre = getIntent().getStringExtra("nombre");
        ArrayList<String> pasatiempos = getIntent().getStringArrayListExtra("pasatiempos_marcados");
        String emocion = getIntent().getStringExtra("emocion");
        String me_produce_emocion = getIntent().getStringExtra("me_produce_emocion");
        String algo_sobre_mi = getIntent().getStringExtra("algo_sobre_mi");
        String perfil = "";

        // Escribir el nombre si fue entregado.
        if (!nombre.equals(""))
        {
            perfil += getString(R.string.perfil_soy) + nombre + ". ";
        }

        // Escribir el o los pasatiempos si fueron entregados, y redactar la lista
        // de acuerdo a la cantidad de pasatiempos entregados (usar "," e "y" donde sea necesario).
        if (pasatiempos.size() == 1)
        {
            perfil += getString(R.string.perfil_mi_pasatiempo_favorito_es) + pasatiempos.get(0);
        }
        else if (pasatiempos.size() > 1)
        {
            perfil += getString(R.string.perfil_mis_pasatiempos_favoritos_son);
            for (int i = 0; i <pasatiempos.size(); i++)
            {
                perfil += pasatiempos.get(i);
                if (i < pasatiempos.size() - 2)
                {
                    perfil += ", ";
                }
                else if (i == pasatiempos.size() - 2)
                {
                    perfil += getString(R.string.perfil_y);
                }
            }
        }

        // Incluir el punto final en la lista de pasatiempos, si fue completada en el cuestionario.
        if (pasatiempos.size() > 0)
        {
            perfil +=".";
            // Agregar un espacio en blanco antes de agregar la sección "algo que me..",
            // si esta fue completada.
            if (!emocion.equals(""))
            {
                perfil += " ";
            }
        }

        // Sección "Algo que me..."
        if (!emocion.equals(""))
        {
            perfil += emocion;
        }

        if (!me_produce_emocion.equals(""))
        {
            perfil += me_produce_emocion;
            if (!algo_sobre_mi.equals(""))
            {
                perfil += " ";
            }
        }

        // Sección "Algo sobre mí".
        if (!algo_sobre_mi.equals(""))
        {
            perfil += algo_sobre_mi;
        }

        // Mostrar en pantalla el perfil redactado en base a las respuestas entregadas.
        tv_perfil.setText(perfil);
    }

    // Volver al cuestionario.
    public void volver(View v)
    {
        Intent principal = new Intent(this, MainActivity.class);
        startActivity(principal);
    }
}