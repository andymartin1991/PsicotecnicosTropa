package com.example.andym.nttdata.tropa.academia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.andym.nttdata.R;
import com.example.andym.nttdata.dto.AdapterDirectivos;
import com.example.andym.nttdata.tropa.dtoTropa.Preguntas;
import com.example.andym.nttdata.dto.menu_optTest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by andym on 26/11/2017.
 */

public class main_estudio_academia_sub extends Activity {
    private String test;
    private String listview_array[];
    URLConnection conn = null;
    public static Preguntas[] objetPreguntas;
    //JSONObject objetouser;
    ArrayList<JSONObject> objetouser;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_optipo);



        /*LinearLayout padre = (LinearLayout)findViewById(R.id.lytMain);
        Calendar c1 = new GregorianCalendar();
        int dia = c1.get(Calendar.DAY_OF_MONTH);
        int mes = c1.get(Calendar.MONTH)+1;
        if( (mes ==11 || mes ==12) || (mes ==1 && dia <=7)){
            padre.setBackgroundResource(R.color.rojonavidad);
        }else{

        }*/

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
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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

                final String[] contents = {""};
                try {
                    conn = new URL("http://s593975491.mialojamiento.es/APPpsicotecnicostropa(1)/preguntas.php?idACA="+ main_academia.idACAM+"&test="+test).openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                final Handler handler = new Handler();
                final InputStream[] in = new InputStream[1];
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            in[0] = conn.getInputStream();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            contents[0] = readStream(in[0]).toString();

                            try {
                                JSONObject objetousuario = new JSONObject(contents[0]);
                                JSONArray myJsonArray = objetousuario.getJSONArray("usuario");
                                //id = "";
                               // if(myJsonArray.length()==1) {
                                objetouser = new ArrayList();
                                int i = 0;
                                try {
                                    while (true) {
                                        objetouser.add(myJsonArray.getJSONObject(i));
                                        i++;
                                    }
                                }catch(Exception e){

                                }
                                //id = objetouser.getString("ID_USUARIO");
                                handler.post(userpassok);
                               // }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                //textoError = contents[0];
                                //handler.post(userpasserror);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();


            }

        });

        registerForContextMenu(lista);

    }

    public static String readStream(InputStream in) throws IOException {
        BufferedReader r = null;
        r = new BufferedReader(new InputStreamReader(in));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line);
        }
        if (r != null) {
            r.close();
        }
        in.close();
        return total.toString();
    }

    final Runnable userpassok = new Runnable() {
        @Override
        public void run() {

            try{
                //String id = "Bienvenido "+objetouser.get(0).getString("ID_TEST");
                /*Toast toast1 =
                        Toast.makeText(getApplicationContext(), objetouser.toString(), Toast.LENGTH_SHORT);
                toast1.show();*/

                objetPreguntas = new Preguntas[objetouser.size()];
                for (int i = 0; i < objetouser.size(); i++) {
                    String solucion = "", imgSol = "";
                    switch (objetouser.get(i).getString("SOLUCION").toString()){
                        case "a":
                            if(objetouser.get(i).getString("RES_A").toString().equals("")) {
                                solucion = "A)";
                            }else{
                                solucion = ("A)" + objetouser.get(i).getString("RES_A").toString());
                            }
                            imgSol =  objetouser.get(i).getString("IMG_A").toString();
                            break;
                        case "b":
                            if(objetouser.get(i).getString("RES_B").toString().equals("")) {
                                solucion = "B)";
                            }else{
                                solucion = ("B)" + objetouser.get(i).getString("RES_B").toString());
                            }
                            imgSol =  objetouser.get(i).getString("IMG_B").toString();
                            break;
                        case "c":
                            if(objetouser.get(i).getString("RES_C").toString().equals("")) {
                                solucion = "C)";
                            }else{
                                solucion = ("C)"+objetouser.get(i).getString("RES_C").toString());
                            }
                            imgSol =  objetouser.get(i).getString("IMG_C").toString();
                            break;
                        case "d":
                            if(objetouser.get(i).getString("RES_D").toString().equals("")) {
                                solucion = "D)";
                            }else{
                                solucion = ("D)"+objetouser.get(i).getString("RES_D").toString());
                            }
                            imgSol =  objetouser.get(i).getString("IMG_D").toString();
                            break;
                    }
                    String c = "";
                    String d = "";
                    if(objetouser.get(i).getString("RES_C").toString().equals("") && objetouser.get(i).getString("IMG_C").toString().equals("")) {

                    }else{
                        c = "C)"+objetouser.get(i).getString("RES_C").toString();
                    }
                    if(objetouser.get(i).getString("RES_D").toString().equals("") && objetouser.get(i).getString("IMG_D").toString().equals("")){

                    }else{
                        d = "D)"+objetouser.get(i).getString("RES_D").toString();
                    }
                    objetPreguntas[i] = new Preguntas(objetouser.get(i).getString("PREGUNTA").toString(), "A)"+objetouser.get(i).getString("RES_A").toString(), "B)"+objetouser.get(i).getString("RES_B").toString(),
                            c, d, solucion, objetouser.get(i).getString("EXPLICACION").toString(),
                            objetouser.get(i).getString("IMG_PRE").toString(), objetouser.get(i).getString("IMG_A").toString(), objetouser.get(i).getString("IMG_B").toString(), objetouser.get(i).getString("IMG_C").toString(),
                            objetouser.get(i).getString("IMG_D").toString(), imgSol, objetouser.get(i).getString("IMG_EXPLI").toString(), 0,"");

                }
                Intent preguntas = new Intent(main_estudio_academia_sub.this, main_preguntas_academia.class);
                preguntas.putExtra("tipo", test);

                startActivity(preguntas);
                overridePendingTransition(R.anim.transpain, R.anim.transpaout);
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

}
