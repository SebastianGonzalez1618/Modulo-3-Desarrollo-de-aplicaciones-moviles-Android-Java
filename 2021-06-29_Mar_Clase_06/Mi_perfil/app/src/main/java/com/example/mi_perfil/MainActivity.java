package com.example.mi_perfil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    EditText et_nombre;
    CheckBox cb_pasatiempos_1;
    CheckBox cb_pasatiempos_2;
    CheckBox cb_pasatiempos_3;
    CheckBox cb_pasatiempos_4;
    CheckBox cb_pasatiempos_5;
    CheckBox cb_pasatiempos_otros;
    EditText et_pasatiempos_otros;
    Spinner sp_algo_que_me;
    EditText et_algo_que_me;
    EditText et_algo_sobre_mi;
    Switch sw_confirmar_veracidad;
    Button bt_enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nombre = findViewById(R.id.et_nombre);
        cb_pasatiempos_1 = findViewById(R.id.cb_pasatiempos_1);
        cb_pasatiempos_2 = findViewById(R.id.cb_pasatiempos_2);
        cb_pasatiempos_3 = findViewById(R.id.cb_pasatiempos_3);
        cb_pasatiempos_4 = findViewById(R.id.cb_pasatiempos_4);
        cb_pasatiempos_5 = findViewById(R.id.cb_pasatiempos_5);
        cb_pasatiempos_otros = findViewById(R.id.cb_pasatiempos_otros);
        et_pasatiempos_otros = findViewById(R.id.et_pasatiempos_otros);
        sp_algo_que_me = findViewById(R.id.sp_algo_que_me);
        et_algo_que_me = findViewById(R.id.et_algo_que_me);
        et_algo_sobre_mi = findViewById(R.id.et_algo_sobre_mi);
        sw_confirmar_veracidad  = findViewById(R.id.sw_confirmar_veracidad);
        bt_enviar = findViewById(R.id.bt_enviar);

        et_pasatiempos_otros.setEnabled(false);
        bt_enviar.setEnabled(false);

        String[] opciones = getResources().getStringArray(R.array.algo_que_me_opciones);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        sp_algo_que_me.setAdapter(adapter);
    }

    // S??lo permitir ingresar otros pasatiempos si la opci??n "Otros" est?? marcada.
    public void otros_pasatiempos(View v)
    {
        et_pasatiempos_otros.setEnabled(cb_pasatiempos_otros.isChecked());
    }

    // S??lo permitir enviar el cuestionario si se marca la confirmaci??n de que la informaci??n es real.
    public void verificar_veracidad(View v)
    {
        bt_enviar.setEnabled(sw_confirmar_veracidad.isChecked());
    }

    // Procesar respuestas y enviar el cuestionario para redactar el perfil.
    public void presentarse(View v)
    {
        // Secci??n "nombre".
        String nombre = et_nombre.getText().toString();

        // Secci??n "pasatiempos".
        CheckBox[] pasatiempos = {cb_pasatiempos_1, cb_pasatiempos_2, cb_pasatiempos_3
                , cb_pasatiempos_4, cb_pasatiempos_5};
        ArrayList<String> pasatiempos_marcados = new ArrayList<>();

        // S??lo agregar los pasatiempos efectivamente marcados.
        for (CheckBox pasatiempo: pasatiempos)
        {
            if (pasatiempo.isChecked())
            {
                pasatiempos_marcados.add(pasatiempo.getText().toString().toLowerCase());
            }
        }

        // Si se marca la opci??n para "otros" pasatiempos, validar que se escriba qu?? pasatiempos son.
        // De lo contrario, no se puede enviar las respuestas.
        if (cb_pasatiempos_otros.isChecked())
        {
            if (et_pasatiempos_otros.getText().toString().equals(""))
            {
                Toast.makeText(this, getString(R.string.corregir_otros_pasatiempos_en_blanco), Toast.LENGTH_LONG).show();
                return;
            }
            else
            {
                pasatiempos_marcados.add(et_pasatiempos_otros.getText().toString().toLowerCase());
            }
        }

        // Secci??n "Algo que me..."
        String me_produce_emocion = et_algo_que_me.getText().toString().toLowerCase();
        String emocion = "";
        if (!me_produce_emocion.equals(""))
        {
            emocion = "Me " + sp_algo_que_me.getSelectedItem().toString() + " ";
            me_produce_emocion = validar_punto_final(me_produce_emocion);
        }

        // Secci??n "Algo sobre m??"
        String algo_sobre_mi = et_algo_sobre_mi.getText().toString();
        if (!algo_sobre_mi.equals(""))
        {
            algo_sobre_mi = validar_punto_final(empezar_con_mayuscula(algo_sobre_mi));
        }

        // Enviar la persona a su perfil redactado en base a sus respuestas.
        Intent presentacion = new Intent(this, presentacion.class);
        presentacion.putExtra("nombre", nombre);
        presentacion.putStringArrayListExtra("pasatiempos_marcados", pasatiempos_marcados);
        presentacion.putExtra("emocion",   emocion);
        presentacion.putExtra("me_produce_emocion", me_produce_emocion);
        presentacion.putExtra("algo_sobre_mi", algo_sobre_mi);
        startActivity(presentacion);
    }

    // Formatear texto para asegurar que un nuevo p??rrafo comience con mayuscula.
    public String empezar_con_mayuscula(String texto)
    {
        return texto.substring(0,1).toUpperCase() + texto.substring(1);
    }

    // Formatear texto para asegurar que una oraci??n termine con punto aparte o final.
    public String validar_punto_final(String texto)
    {
        if (!texto.endsWith("."))
        {
            texto += ".";
        }
        return texto;
    }
}