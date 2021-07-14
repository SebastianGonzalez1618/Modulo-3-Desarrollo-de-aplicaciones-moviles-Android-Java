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


