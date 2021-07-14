Actividades del día 2, semana 10.

(Clase 6: martes 29 de junio de 2021)

---

El uso de la clase Intent y sus métodos asociados, como `putExtra()`, `getStringExtra()`, `putStringArrayListExtra()` o `getStringArrayListExtra()` permiten pasar datos de una activity a otra, lo que permite un nivel de funcionalidad que no habíamos podido implementar hasta ahora.

Para poder realizar el ejercicio descubrí que se puede definir arrays de strings en `strings.xml`, y cargarlos en Java de la siguiente forma:

Definir arrays de texto en strings.xml:

```xml
<string-array name="nombre_del_array">
    <item>item 1</item>
    <item>item 2</item>
    <item>item 3</item>
    <item>item 4</item>
    <item>item 5</item>
</string-array>
```

Cargar un array de strings desde `strings.xml`:

```Java
String nombre_variable_array[] = getResources().getStringArray(R.array.nombre_del_array);
```

---

### Ejercicio: Crear una app donde se pase datos entre entre actividades, usando los contenidos que hemos visto hasta ahora en clases.

Para este ejercicio desarrolle una aplicación con un pequeño cuestionario en base al cual la aplicación redacta un perfil personal con tus respuestas.

Para poder agregar otros pasatiempos debes primero marcar la opción "Otros", y para poder enviar el cuestionario debes presionar el switch para verificar que tus respuestas son la verdad.

![Cuestionario para redactar tu perfil.](Documentación/Mi_perfil_01.jpg)
![Algunos controles sólo se activan cuando corresponde.](Documentación/Mi_perfil_02.jpg)

La aplicación redacta y formatea un perfil en base a tus respuestas.

![](Documentación/Mi_perfil_03.jpg)
![](Documentación/Mi_perfil_04.jpg)

No puedes enviar tus respuestas si marcaste la opción "Otros" en la sección de pasatiempos sin especificar esos otros pasatiempos.
![](Documentación/Mi_perfil_05.jpg)


Tu perfil es redactado tomando en cuenta sólo la información que hayas entregado, por lo que será tan completo como tú decidas.

![](Documentación/Mi_perfil_06.jpg)
![](Documentación/Mi_perfil_07.jpg)

![](Documentación/Mi_perfil_08.jpg)
![](Documentación/Mi_perfil_09.jpg)

#### Cuestionario.
`MainActivity.java`
```Java
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

    // Sólo permitir ingresar otros pasatiempos si la opción "Otros" está marcada.
    public void otros_pasatiempos(View v)
    {
        et_pasatiempos_otros.setEnabled(cb_pasatiempos_otros.isChecked());
    }

    // Sólo permitir enviar el cuestionario si se marca la confirmación de que la información es real.
    public void verificar_veracidad(View v)
    {
        bt_enviar.setEnabled(sw_confirmar_veracidad.isChecked());
    }

    // Procesar respuestas y enviar el cuestionario para redactar el perfil.
    public void presentarse(View v)
    {
        // Sección "nombre".
        String nombre = et_nombre.getText().toString();

        // Sección "pasatiempos".
        CheckBox[] pasatiempos = {cb_pasatiempos_1, cb_pasatiempos_2, cb_pasatiempos_3
                , cb_pasatiempos_4, cb_pasatiempos_5};
        ArrayList<String> pasatiempos_marcados = new ArrayList<>();

        // Sólo agregar los pasatiempos efectivamente marcados.
        for (CheckBox pasatiempo: pasatiempos)
        {
            if (pasatiempo.isChecked())
            {
                pasatiempos_marcados.add(pasatiempo.getText().toString().toLowerCase());
            }
        }

        // Si se marca la opción para "otros" pasatiempos, validar que se escriba qué pasatiempos son.
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

        // Sección "Algo que me..."
        String me_produce_emocion = et_algo_que_me.getText().toString().toLowerCase();
        String emocion = "";
        if (!me_produce_emocion.equals(""))
        {
            emocion = "Me " + sp_algo_que_me.getSelectedItem().toString() + " ";
            me_produce_emocion = validar_punto_final(me_produce_emocion);
        }

        // Sección "Algo sobre mí"
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

    // Formatear texto para asegurar que un nuevo párrafo comience con mayuscula.
    public String empezar_con_mayuscula(String texto)
    {
        return texto.substring(0,1).toUpperCase() + texto.substring(1);
    }

    // Formatear texto para asegurar que una oración termine con punto aparte o final.
    public String validar_punto_final(String texto)
    {
        if (!texto.endsWith("."))
        {
            texto += ".";
        }
        return texto;
    }
}
```

#### Perfil.
`presentacion.java`
```Java
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
```