package com.example.andym.psicotecnicostropa;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by andym on 21/04/2017.
 */

public class main_calculabaremo extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_calculabaremo);

        Spinner spinner = (Spinner) findViewById(R.id.meriaca);
        String[] valores = {
                "NINGUNO",
                "MECES 4 Doctor ->16 puntos",
                "MECES 3, Master ->15 puntos",
                "MECES 2, Grado  ->13 puntos",
                "MECES 1, Técnico Superior ->12 puntos",
                "Apto en la prueba de acceso a las enseñanzas universitarias oficiales de Grado ->11 puntos",
                "Bachillerato LOE, COU y sus equivalentes a efectos académicos ->8 puntos",
                "Título de BUP, 1er curso Bachillerato LOE ->7 puntos",
                "Título de Técnico o equivalentes a efectos académicos ->6 puntos",
                "Título de Graduado en Educación Secundaria Obligatoria o sus equivalentes a efectos académicos ->4 puntos"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valores));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // vacio

            }
        });
    }
}
