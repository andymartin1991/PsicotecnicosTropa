package com.example.andym.psicotecnicostropa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.andym.psicotecnicostropa.dto.AdapterDirectivos;
import com.example.andym.psicotecnicostropa.dto.menu_optTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class main_optTipoFatiga extends Activity {

    public static String test;
    private String listview_array[];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_optipofatiga);

        LinearLayout padre = (LinearLayout)findViewById(R.id.padre);
        Calendar c1 = new GregorianCalendar();
        int dia = c1.get(Calendar.DAY_OF_MONTH);
        int mes = c1.get(Calendar.MONTH)+1;
        if( (mes ==11 || mes ==12) || (mes ==1 && dia <=7)){
            padre.setBackgroundResource(R.color.rojonavidad);
        }else{

        }

        this.setTitle(getString(R.string.test_bloque));

        listview_array = new String[7];
        listview_array[0] = getString(R.string.aptitud1);
        listview_array[1] = getString(R.string.aptitud2);
        listview_array[2] = getString(R.string.aptitud3);
        listview_array[3] = getString(R.string.aptitud4);
        listview_array[4] = getString(R.string.aptitud5);
        listview_array[5] = getString(R.string.aptitud6);
        listview_array[6] = getString(R.string.aptitud7);


        final ListView lista = (ListView) findViewById(R.id.listadirectivos);
        lista.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listview_array));
        ArrayList<menu_optTest> arraydir = new ArrayList<menu_optTest>();
        menu_optTest directivo;

        directivo = new menu_optTest(
                getResources().getDrawable(
                        R.drawable.verbal), getString(R.string.bloque1), getString(R.string.aptitud1));
        arraydir.add(directivo);
        directivo = new menu_optTest(getResources().getDrawable(
                R.drawable.numerico), getString(R.string.bloque2), getString(R.string.aptitud2));
        arraydir.add(directivo);
        directivo = new menu_optTest(getResources().getDrawable(
                R.drawable.espacial), getString(R.string.bloque3), getString(R.string.aptitud3));
        arraydir.add(directivo);
        directivo = new menu_optTest(getResources().getDrawable(
                R.drawable.mecanico), getString(R.string.bloque4), getString(R.string.aptitud4));
        arraydir.add(directivo);
        directivo = new menu_optTest(getResources().getDrawable(
                R.drawable.perceptiva), getString(R.string.bloque5), getString(R.string.aptitud5));
        arraydir.add(directivo);
        directivo = new menu_optTest(getResources()
                .getDrawable(R.drawable.memoria), getString(R.string.bloque6), getString(R.string.aptitud6));
        arraydir.add(directivo);
        directivo = new menu_optTest(getResources().getDrawable(
                R.drawable.abstrapto), getString(R.string.bloque7), getString(R.string.aptitud7));
        arraydir.add(directivo);

        // Creo el adapter personalizado
        AdapterDirectivos adapter = new AdapterDirectivos(this, arraydir);

        // Lo aplico
        lista.setAdapter(adapter);

        // -------------------seleccion del
        // menu-----------------------------------
        lista.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                switch (arg2) {
                    case 0:
                        test = "verbal";
                        break;
                    case 1:
                        test = "numerico";
                        break;
                    case 2:
                        test = "espacial";
                        break;
                    case 3:
                        test = "mecanico";
                        break;
                    case 4:
                        test = "perceptiva";
                        break;
                    case 5:
                        test = "memoria";
                        break;
                    case 6:
                        test = "abstrapto";
                        break;

                }
                Intent preguntas = new Intent(main_optTipoFatiga.this, main_preguntasFatiga.class);
                //startActivity(new Intent(main_optTipo.this, main_preguntas.class));
                preguntas.putExtra("tipo", test);
                startActivity(preguntas);
                overridePendingTransition(R.anim.transpain, R.anim.transpaout);
            }

        });

        registerForContextMenu(lista);

    }

}
