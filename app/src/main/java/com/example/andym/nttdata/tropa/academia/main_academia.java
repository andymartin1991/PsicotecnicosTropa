package com.example.andym.nttdata.tropa.academia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andym.nttdata.R;
import com.example.andym.nttdata.tropa.dtoTropa.Preguntas;

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
 * Created by andym on 09/09/2017.
 */
public class main_academia extends Activity {



    URLConnection conn = null;
    ArrayList<JSONObject> objetouserList;
    public static Preguntas[] objetPreguntas;
    String id;
    Button volver, estudio, aleatorio, examen;
    TextView error, titulo, username;
    LinearLayout emer, verificado;
    JSONObject objetouser;
    String textoError = "";
    TableLayout tablabotones;
    ProgressBar carga;

    public static Preguntas[] bloqueabstrapto;
    public static Preguntas[] bloquenumerico;
    public static Preguntas[] bloqueespacial;
    public static Preguntas[] bloquemecanico;
    public static Preguntas[] bloqueperceptiva;
    public static Preguntas[] bloquememoria;
    public static Preguntas[] bloqueverbal;

    public static String correo;
    public static String password;
    public static String academia;
    public static String academianame;
    public static String idACAM;
    boolean[] entra = {true};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_academia);



        /*ScrollView padre = (ScrollView)findViewById(R.id.padre);
        Calendar c1 = new GregorianCalendar();
        int dia = c1.get(Calendar.DAY_OF_MONTH);
        int mes = c1.get(Calendar.MONTH)+1;
        if( (mes ==11 || mes ==12) || (mes ==1 && dia <=7)){
            padre.setBackgroundResource(R.color.rojonavidad);
        }else{

        }*/

        correo = getIntent().getStringExtra("correo");
        password = getIntent().getStringExtra("pass");
        academia = getIntent().getStringExtra("academia");
        academianame = getIntent().getStringExtra("nameacademia");

        volver = (Button)findViewById(R.id.atras);
        error = (TextView) findViewById(R.id.fallo);
        emer = (LinearLayout)findViewById(R.id.emer);
        verificado = (LinearLayout)findViewById(R.id.verificado);
        titulo = (TextView) findViewById(R.id.title);
        username = (TextView) findViewById(R.id.nameusu);
        tablabotones = (TableLayout) findViewById(R.id.tablaBotones);
        carga = (ProgressBar) findViewById(R.id.cargacademia);

        final String[] contents = {""};
        try {
            conn = new URL("http://s593975491.mialojamiento.es/APPpsicotecnicostropa(1)/login_usuario.php?correo="+correo+"&password="+password+"&academia="+academia).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final Runnable userpasserror = new Runnable() {
            @Override
            public void run() {
                emer.setVisibility(View.VISIBLE);
                verificado.setVisibility(View.GONE);
                error.setText(Html.fromHtml(textoError));
                carga.setVisibility(View.GONE);
                volver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        };

        final Runnable userpassok = new Runnable() {
            @Override
            public void run() {
                emer.setVisibility(View.GONE);
                verificado.setVisibility(View.VISIBLE);
                titulo.setText(academianame);
                tablabotones.setVisibility(View.VISIBLE);
                carga.setVisibility(View.GONE);
                try{
                    username.setText("Bienvenido "+objetouser.getString("NOMBRE_ALU"));
                    idACAM = objetouser.getString("ID_ACADEMIA");
                }catch (JSONException e) {
                }
            }
        };


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
                        id = "";
                        if(myJsonArray.length()==1) {
                            objetouser = myJsonArray.getJSONObject(0);
                            id = objetouser.getString("ID_USUARIO");
                            handler.post(userpassok);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        textoError = contents[0];
                        handler.post(userpasserror);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        examen = (Button)findViewById(R.id.examen_aca);
        examen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entra[0] == true) {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Generando ...", Toast.LENGTH_SHORT);
                    toast1.show();
                    final String[] contents = {""};
                    try {
                        conn = new URL("http://s593975491.mialojamiento.es/APPpsicotecnicostropa(1)/preguntas_todas.php?idACA="+ main_academia.idACAM).openConnection();
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
                                    objetouserList = new ArrayList();
                                    int i = 0;
                                    try {
                                        while (true) {
                                            objetouserList.add(myJsonArray.getJSONObject(i));
                                            i++;
                                        }
                                    }catch(Exception e){

                                    }
                                    handler.post(exampre);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    //handler.post(userpasserror);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    entra[0] = false;
                    Animation animation = AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.rotar);
                    examen.startAnimation(animation);
                }
            }
        });

        aleatorio = (Button)findViewById(R.id.aleatorio_aca);
        aleatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entra[0] == true) {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Generando ...", Toast.LENGTH_SHORT);
                    toast1.show();
                    final String[] contents = {""};
                    try {
                        conn = new URL("http://s593975491.mialojamiento.es/APPpsicotecnicostropa(1)/preguntas_todas.php?idACA="+ main_academia.idACAM).openConnection();
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
                                    objetouserList = new ArrayList();
                                    int i = 0;
                                    try {
                                        while (true) {
                                            objetouserList.add(myJsonArray.getJSONObject(i));
                                            i++;
                                        }
                                    }catch(Exception e){

                                    }
                                    handler.post(aletopre);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    //handler.post(userpasserror);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    entra[0] = false;
                    Animation animation = AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.rotar);
                    aleatorio.startAnimation(animation);
                    /*new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                            }*/
                            /*startActivity(new Intent(main_academia.this, main_preguntasAleatorio_academia.class));
                            overridePendingTransition(R.anim.transpain, R.anim.transpaout);*/
                    //entra[0] = true;
                        /*}
                    }).start();*/

                }
            }
        });

        estudio = (Button)findViewById(R.id.estudio);
        estudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entra[0] == true) {
                    entra[0] = false;
                    Animation animation = AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.rotar);
                    estudio.startAnimation(animation);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                            }
                            startActivity(new Intent(main_academia.this, main_estudio_academia_sub.class));
                            overridePendingTransition(R.anim.transpain, R.anim.transpaout);
                            entra[0] = true;
                        }
                    }).start();

                }
            }
        });
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

    final Runnable exampre = new Runnable() {
        @Override
        public void run() {
            int nnum = 0, nver = 0, nmec = 0, nabs = 0, nesp = 0, nper = 0, nmem = 0;
            try{
                objetPreguntas = new Preguntas[objetouserList.size()];
                for (int i = 0; i < objetouserList.size(); i++) {
                    String solucion = "", imgSol = "";
                    switch (objetouserList.get(i).getString("SOLUCION").toString()){
                        case "a":
                            if(objetouserList.get(i).getString("RES_A").toString().equals("")) {
                                solucion = "A)";
                            }else{
                                solucion = ("A)" + objetouserList.get(i).getString("RES_A").toString());
                            }
                            imgSol =  objetouserList.get(i).getString("IMG_A").toString();
                            break;
                        case "b":
                            if(objetouserList.get(i).getString("RES_B").toString().equals("")) {
                                solucion = "B)";
                            }else{
                                solucion = ("B)" + objetouserList.get(i).getString("RES_B").toString());
                            }
                            imgSol =  objetouserList.get(i).getString("IMG_B").toString();
                            break;
                        case "c":
                            if(objetouserList.get(i).getString("RES_C").toString().equals("")) {
                                solucion = "C)";
                            }else{
                                solucion = ("C)"+objetouserList.get(i).getString("RES_C").toString());
                            }
                            imgSol =  objetouserList.get(i).getString("IMG_C").toString();
                            break;
                        case "d":
                            if(objetouserList.get(i).getString("RES_D").toString().equals("")) {
                                solucion = "D)";
                            }else{
                                solucion = ("D)"+objetouserList.get(i).getString("RES_D").toString());
                            }
                            imgSol =  objetouserList.get(i).getString("IMG_D").toString();
                            break;
                    }
                    String c = "";
                    String d = "";
                    if(objetouserList.get(i).getString("RES_C").toString().equals("") && objetouserList.get(i).getString("IMG_C").toString().equals("")) {

                    }else{
                        c = "C)"+objetouserList.get(i).getString("RES_C").toString();
                    }
                    if(objetouserList.get(i).getString("RES_D").toString().equals("") && objetouserList.get(i).getString("IMG_D").toString().equals("")){

                    }else{
                        d = "D)"+objetouserList.get(i).getString("RES_D").toString();
                    }

                    String tipo = "";
                    if(objetouserList.get(i).getString("TIPO_TEST").toString().equals("0")){
                        tipo = "verbal";
                        nver++;
                    }
                    if(objetouserList.get(i).getString("TIPO_TEST").toString().equals("1")){
                        tipo = "numerico";
                        nnum++;
                    }
                    if(objetouserList.get(i).getString("TIPO_TEST").toString().equals("2")){
                        tipo = "espacial";
                        nesp++;
                    }
                    if(objetouserList.get(i).getString("TIPO_TEST").toString().equals("3")){
                        tipo = "mecanico";
                        nmec++;
                    }
                    if(objetouserList.get(i).getString("TIPO_TEST").toString().equals("4")){
                        tipo = "perceptiva";
                        nper++;
                    }
                    if(objetouserList.get(i).getString("TIPO_TEST").toString().equals("5")){
                        tipo = "memoria";
                        nmem++;
                    }
                    if(objetouserList.get(i).getString("TIPO_TEST").toString().equals("6")){
                        tipo = "abstrapto";
                        nabs++;
                    }

                    objetPreguntas[i] = new Preguntas(objetouserList.get(i).getString("PREGUNTA").toString(), "A)"+objetouserList.get(i).getString("RES_A").toString(),
                            "B)"+objetouserList.get(i).getString("RES_B").toString(), c, d, solucion, objetouserList.get(i).getString("EXPLICACION").toString(),
                            objetouserList.get(i).getString("IMG_PRE").toString(), objetouserList.get(i).getString("IMG_A").toString(), objetouserList.get(i).getString("IMG_B").toString(),
                            objetouserList.get(i).getString("IMG_C").toString(), objetouserList.get(i).getString("IMG_D").toString(), imgSol,
                            objetouserList.get(i).getString("IMG_EXPLI").toString(), 0,tipo );


                }
                bloqueverbal = new Preguntas[nver];
                bloqueespacial = new Preguntas[nesp];
                bloqueabstrapto = new Preguntas[nabs];
                bloquemecanico = new Preguntas[nmec];
                bloquememoria = new Preguntas[nmem];
                bloquenumerico = new Preguntas[nnum];
                bloqueperceptiva = new Preguntas[nper];
                int n1 = 0, n2 = 0, n3 = 0, n4 = 0, n5 = 0, n6 = 0, n7 = 0;
                for(int i = 0; i<objetPreguntas.length;i++){
                    if(objetPreguntas[i].getTipo().equals("verbal")){
                        bloqueverbal[n1] = objetPreguntas[i];
                        n1++;
                    }
                    if(objetPreguntas[i].getTipo().equals("numerico")){
                        bloquenumerico[n2] = objetPreguntas[i];
                        n2++;
                    }
                    if(objetPreguntas[i].getTipo().equals("espacial")){
                        bloqueespacial[n3] = objetPreguntas[i];
                        n3++;
                    }
                    if(objetPreguntas[i].getTipo().equals("mecanico")){
                        bloquemecanico[n4] = objetPreguntas[i];
                        n4++;
                    }
                    if(objetPreguntas[i].getTipo().equals("perceptiva")){
                        bloqueperceptiva[n5] = objetPreguntas[i];
                        n5++;
                    }
                    if(objetPreguntas[i].getTipo().equals("memoria")){
                        bloquememoria[n6] = objetPreguntas[i];
                        n6++;
                    }
                    if(objetPreguntas[i].getTipo().equals("abstrapto")){
                        bloqueabstrapto[n7] = objetPreguntas[i];
                        n7++;
                    }
                }
                String k = "";
                startActivity(new Intent(main_academia.this, main_examen_academia.class));
                overridePendingTransition(R.anim.transpain, R.anim.transpaout);
                entra[0] = true;

            }catch (JSONException e) {
                e.printStackTrace();
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Error al cargar preguntas", Toast.LENGTH_SHORT);
                toast1.show();
                entra[0] = true;
            }
        }
    };


    final Runnable aletopre = new Runnable() {
        @Override
        public void run() {
            try{
                objetPreguntas = new Preguntas[objetouserList.size()];
                for (int i = 0; i < objetouserList.size(); i++) {
                    String solucion = "", imgSol = "";
                    switch (objetouserList.get(i).getString("SOLUCION").toString()){
                        case "a":
                            if(objetouserList.get(i).getString("RES_A").toString().equals("")) {
                                solucion = "A)";
                            }else{
                                solucion = ("A)" + objetouserList.get(i).getString("RES_A").toString());
                            }
                            imgSol =  objetouserList.get(i).getString("IMG_A").toString();
                            break;
                        case "b":
                            if(objetouserList.get(i).getString("RES_B").toString().equals("")) {
                                solucion = "B)";
                            }else{
                                solucion = ("B)" + objetouserList.get(i).getString("RES_B").toString());
                            }
                            imgSol =  objetouserList.get(i).getString("IMG_B").toString();
                            break;
                        case "c":
                            if(objetouserList.get(i).getString("RES_C").toString().equals("")) {
                                solucion = "C)";
                            }else{
                                solucion = ("C)"+objetouserList.get(i).getString("RES_C").toString());
                            }
                            imgSol =  objetouserList.get(i).getString("IMG_C").toString();
                            break;
                        case "d":
                            if(objetouserList.get(i).getString("RES_D").toString().equals("")) {
                                solucion = "D)";
                            }else{
                                solucion = ("D)"+objetouserList.get(i).getString("RES_D").toString());
                            }
                            imgSol =  objetouserList.get(i).getString("IMG_D").toString();
                            break;
                    }
                    String c = "";
                    String d = "";
                    if(objetouserList.get(i).getString("RES_C").toString().equals("") && objetouserList.get(i).getString("IMG_C").toString().equals("")) {

                    }else{
                        c = "C)"+objetouserList.get(i).getString("RES_C").toString();
                    }
                    if(objetouserList.get(i).getString("RES_D").toString().equals("") && objetouserList.get(i).getString("IMG_D").toString().equals("")){

                    }else{
                        d = "D)"+objetouserList.get(i).getString("RES_D").toString();
                    }

                    String memo = "";
                    if(objetouserList.get(i).getString("TIPO_TEST").toString().equals("5")){
                        memo = "si";
                    }

                    objetPreguntas[i] = new Preguntas(objetouserList.get(i).getString("PREGUNTA").toString(), "A)"+objetouserList.get(i).getString("RES_A").toString(),
                            "B)"+objetouserList.get(i).getString("RES_B").toString(), c, d, solucion, objetouserList.get(i).getString("EXPLICACION").toString(),
                            objetouserList.get(i).getString("IMG_PRE").toString(), objetouserList.get(i).getString("IMG_A").toString(), objetouserList.get(i).getString("IMG_B").toString(),
                            objetouserList.get(i).getString("IMG_C").toString(), objetouserList.get(i).getString("IMG_D").toString(), imgSol,
                            objetouserList.get(i).getString("IMG_EXPLI").toString(), 0,memo );

                }
                startActivity(new Intent(main_academia.this, main_preguntasAleatorio_academia.class));
                overridePendingTransition(R.anim.transpain, R.anim.transpaout);
                entra[0] = true;

            }catch (JSONException e) {
                e.printStackTrace();
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Error al cargar preguntas", Toast.LENGTH_SHORT);
                toast1.show();
                entra[0] = true;
            }
        }
    };


}