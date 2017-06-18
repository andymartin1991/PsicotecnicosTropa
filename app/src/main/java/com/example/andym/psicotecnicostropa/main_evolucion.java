package com.example.andym.psicotecnicostropa;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.andym.psicotecnicostropa.dto.Notas;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by andym on 16/06/2017.
 */

public class main_evolucion extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_evolucion);

        File ruta_sd = getExternalFilesDir(null);
        final TextView titulo = (TextView) findViewById(R.id.titulo);

        Notas exam = new Notas();
        //List<Notas> notas = new ArrayList<>();

        List<String> namefichbonito = new ArrayList<String>();

        if (ruta_sd.exists()) {
            File[] ficheros = ruta_sd.listFiles();

            for (int x = 0; x < ficheros.length; x++) {
                File a = new File(ruta_sd.getAbsolutePath(), ficheros[x].getName());
                String kk = (ficheros[x].getName());
                String json = "";
                if (kk.length() == 25) {
                    try {
                        BufferedReader fin = new BufferedReader(new InputStreamReader(new FileInputStream(a)));
                        json = fin.readLine();
                        fin.close();

                        //Type mapType = new TypeToken<Map<String, Map>>(){}.getType();
                        //Map<String, String[]> son = new Gson().fromJson(json, mapType);
                        System.out.println(ruta_sd);
                        System.out.println(a);


                        //namefichbonito.add(kk.substring(0, 19) + " Nota: " + (son.get("notaredondeadabar")));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, namefichbonito);
            ListView lista = (ListView) findViewById(R.id.lista);
            lista.setAdapter(adapter);


        } else {
            //no hay datos de evolucion
        }

        //titulo.setText(namefichbonito.get(0));
    }
}
