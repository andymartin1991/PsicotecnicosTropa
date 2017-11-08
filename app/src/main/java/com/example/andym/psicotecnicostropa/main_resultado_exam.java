package com.example.andym.psicotecnicostropa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andym.psicotecnicostropa.dto.Notas;
import com.example.andym.psicotecnicostropa.dto.Preguntas;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static java.lang.String.valueOf;

/**
 * Created by andym on 08/04/2017.
 */

public class main_resultado_exam extends Activity {

    TextView mostrar, nota, notabar;
    EditText baremo;
    Button calcular, introbar, guardar;
    LinearLayout compartir;
    Notas notas;
    static Preguntas[] bloqueverbal, bloquenumerico, bloqueespacial, bloquemecanico, bloqueperceptiva, bloquememoria, bloqueabstrapto;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_resultado_exam);

        LinearLayout padre = (LinearLayout) findViewById(R.id.padre);
        Calendar cc1 = new GregorianCalendar();
        int dia = cc1.get(Calendar.DAY_OF_MONTH);
        int mes = cc1.get(Calendar.MONTH)+1;
        if( (mes ==11 || mes ==12) || (mes ==1 && dia <=7)){
            padre.setBackgroundResource(R.color.rojonavidad);
        }else{

        }

        // orientacion pantalla
        Configuration config = getResources().getConfiguration();
        if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        notas = main_examen.notas;

        final String[] baremorecu = {""};
        try {
            File ruta_sd;
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                // We can read and write the media
                ruta_sd = getExternalFilesDir(null);
            } else {
                // Load another directory, probably local memory
                ruta_sd = getFilesDir();
            }
            File f = new File(ruta_sd.getAbsolutePath(), "baremo");

            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(f)));
            baremorecu[0] = fin.readLine();
            fin.close();
            System.out.println(ruta_sd);
            System.out.println(f);
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al leer fichero desde tarjeta SD");
        }

        baremo = (EditText) findViewById(R.id.baremo);
        baremo.setText(baremorecu[0]);

        int aciertosVerbal = 0;
        int fallosVerbal = 0;
        int sincontestarVerbal = 0;
        for (int i = 0; i < 15; i++) {
            String respuestacorrecta = notas.getBloqueverbal().get(i).getSolu().substring(0, 1);
            String respuestadada = null;
            switch (notas.getBloqueverbal().get(i).getRespulsada()) {
                case 1:
                    respuestadada = "A";
                    break;
                case 2:
                    respuestadada = "B";
                    break;
                case 3:
                    respuestadada = "C";
                    break;
                case 4:
                    respuestadada = "D";
                    break;
                default:
                    respuestadada = "M";
            }
            if (respuestadada.equals(respuestacorrecta)) {
                aciertosVerbal++;
            } else if (respuestadada.equals("M")) {
                sincontestarVerbal++;
            } else {
                fallosVerbal++;
            }
        }
        notas.setAciertosVerbal(aciertosVerbal);
        notas.setFallosVerbal(fallosVerbal);
        notas.setSincontestarVerbal(sincontestarVerbal);

        int aciertosNumerico = 0;
        int fallosNumerico = 0;
        int sincontestarNumerico = 0;
        for (int i = 0; i < 15; i++) {
            String respuestacorrecta = notas.getBloquenumerico().get(i).getSolu().substring(0, 1);
            String respuestadada = null;
            switch (notas.getBloquenumerico().get(i).getRespulsada()) {
                case 1:
                    respuestadada = "A";
                    break;
                case 2:
                    respuestadada = "B";
                    break;
                case 3:
                    respuestadada = "C";
                    break;
                case 4:
                    respuestadada = "D";
                    break;
                default:
                    respuestadada = "M";
            }
            if (respuestadada.equals(respuestacorrecta)) {
                aciertosNumerico++;
            } else if (respuestadada.equals("M")) {
                sincontestarNumerico++;
            } else {
                fallosNumerico++;
            }
        }
        notas.setAciertosNumerico(aciertosNumerico);
        notas.setFallosNumerico(fallosNumerico);
        notas.setSincontestarNumerico(sincontestarNumerico);

        int aciertosEspacial = 0;
        int fallosEspacial = 0;
        int sincontestarEspacial = 0;
        for (int i = 0; i < 15; i++) {
            String respuestacorrecta = notas.getBloqueespacial().get(i).getSolu().substring(0, 1);
            String respuestadada = null;
            switch (notas.getBloqueespacial().get(i).getRespulsada()) {
                case 1:
                    respuestadada = "A";
                    break;
                case 2:
                    respuestadada = "B";
                    break;
                case 3:
                    respuestadada = "C";
                    break;
                case 4:
                    respuestadada = "D";
                    break;
                default:
                    respuestadada = "M";
            }
            if (respuestadada.equals(respuestacorrecta)) {
                aciertosEspacial++;
            } else if (respuestadada.equals("M")) {
                sincontestarEspacial++;
            } else {
                fallosEspacial++;
            }
        }
        notas.setAciertosEspacial(aciertosEspacial);
        notas.setFallosEspacial(fallosEspacial);
        notas.setSincontestarEspacial(sincontestarEspacial);

        int aciertosMecanico = 0;
        int fallosMecanico = 0;
        int sincontestarMecanico = 0;
        for (int i = 0; i < 15; i++) {
            String respuestacorrecta = notas.getBloquemecanico().get(i).getSolu().substring(0, 1);
            String respuestadada = null;
            switch (notas.getBloquemecanico().get(i).getRespulsada()) {
                case 1:
                    respuestadada = "A";
                    break;
                case 2:
                    respuestadada = "B";
                    break;
                case 3:
                    respuestadada = "C";
                    break;
                case 4:
                    respuestadada = "D";
                    break;
                default:
                    respuestadada = "M";
            }
            if (respuestadada.equals(respuestacorrecta)) {
                aciertosMecanico++;
            } else if (respuestadada.equals("M")) {
                sincontestarMecanico++;
            } else {
                fallosMecanico++;
            }
        }
        notas.setAciertosMecanico(aciertosMecanico);
        notas.setFallosMecanico(fallosMecanico);
        notas.setSincontestarMecanico(sincontestarMecanico);

        int aciertosPerceptiva = 0;
        int fallosPerceptiva = 0;
        int sincontestarPerceptiva = 0;
        for (int i = 0; i < 15; i++) {
            String respuestacorrecta = notas.getBloqueperceptiva().get(i).getSolu().substring(0, 1);
            String respuestadada = null;
            switch (notas.getBloqueperceptiva().get(i).getRespulsada()) {
                case 1:
                    respuestadada = "A";
                    break;
                case 2:
                    respuestadada = "B";
                    break;
                case 3:
                    respuestadada = "C";
                    break;
                case 4:
                    respuestadada = "D";
                    break;
                default:
                    respuestadada = "M";
            }
            if (respuestadada.equals(respuestacorrecta)) {
                aciertosPerceptiva++;
            } else if (respuestadada.equals("M")) {
                sincontestarPerceptiva++;
            } else {
                fallosPerceptiva++;
            }
        }
        notas.setAciertosPerceptiva(aciertosPerceptiva);
        notas.setFallosPerceptiva(fallosPerceptiva);
        notas.setSincontestarPerceptiva(sincontestarPerceptiva);

        int aciertosMemoria = 0;
        int fallosMemoria = 0;
        int sincontestarMemoria = 0;
        for (int i = 0; i < 15; i++) {
            String respuestacorrecta = notas.getBloquememoria().get(i).getSolu().substring(0, 1);
            String respuestadada = null;
            switch (notas.getBloquememoria().get(i).getRespulsada()) {
                case 1:
                    respuestadada = "A";
                    break;
                case 2:
                    respuestadada = "B";
                    break;
                case 3:
                    respuestadada = "C";
                    break;
                case 4:
                    respuestadada = "D";
                    break;
                default:
                    respuestadada = "M";
            }
            if (respuestadada.equals(respuestacorrecta)) {
                aciertosMemoria++;
            } else if (respuestadada.equals("M")) {
                sincontestarMemoria++;
            } else {
                fallosMemoria++;
            }
        }
        notas.setAciertosMemoria(aciertosMemoria);
        notas.setFallosMemoria(fallosMemoria);
        notas.setSincontestarMemoria(sincontestarMemoria);

        int aciertosAbstrapto = 0;
        int fallosAbstrapto = 0;
        int sincontestarAbstrapto = 0;
        for (int i = 0; i < 15; i++) {
            String respuestacorrecta = notas.getBloqueabstrapto().get(i).getSolu().substring(0, 1);
            String respuestadada = null;
            switch (notas.getBloqueabstrapto().get(i).getRespulsada()) {
                case 1:
                    respuestadada = "A";
                    break;
                case 2:
                    respuestadada = "B";
                    break;
                case 3:
                    respuestadada = "C";
                    break;
                case 4:
                    respuestadada = "D";
                    break;
                default:
                    respuestadada = "M";
            }
            if (respuestadada.equals(respuestacorrecta)) {
                aciertosAbstrapto++;
            } else if (respuestadada.equals("M")) {
                sincontestarAbstrapto++;
            } else {
                fallosAbstrapto++;
            }
        }
        notas.setAciertosAbstrapto(aciertosAbstrapto);
        notas.setFallosAbstrapto(fallosAbstrapto);
        notas.setSincontestarAbstrapto(sincontestarAbstrapto);

        notas.setNotasobre10
                ((((Double.parseDouble(valueOf(aciertosVerbal)) * 10) / 15) +
                        ((Double.parseDouble(valueOf(aciertosNumerico)) * 10) / 15) +
                        ((Double.parseDouble(valueOf(aciertosEspacial)) * 10) / 15) +
                        ((Double.parseDouble(valueOf(aciertosMecanico)) * 10) / 15) +
                        ((Double.parseDouble(valueOf(aciertosPerceptiva)) * 10) / 15) +
                        ((Double.parseDouble(valueOf(aciertosMemoria)) * 10) / 15) +
                        ((Double.parseDouble(valueOf(aciertosAbstrapto)) * 10) / 15)) / 7);
        notas.setNotaredondeada(redondearDecimales(notas.getNotasobre10(), 1));
        notabaremo();
        mostrar = (TextView) findViewById(R.id.mostrar);
        mostrar.setText(
                getString(R.string.Verbal) + ": " + getString(R.string.aciertos) + " " + aciertosVerbal + ", " + getString(R.string.Fallos) + " " + fallosVerbal + " " + getString(R.string.nocontestadas) + " " + sincontestarVerbal + "\n\n" +
                        getString(R.string.Numerico) + ": " + getString(R.string.aciertos) + " " + aciertosNumerico + ", " + getString(R.string.Fallos) + " " + fallosNumerico + " " + getString(R.string.nocontestadas) + " " + sincontestarNumerico + "\n\n" +
                        getString(R.string.Espacial) + ": " + getString(R.string.aciertos) + " " + aciertosEspacial + ", " + getString(R.string.Fallos) + " " + fallosEspacial + " " + getString(R.string.nocontestadas) + " " + sincontestarEspacial + "\n\n" +
                        getString(R.string.Mecanico) + ": " + getString(R.string.aciertos) + " " + aciertosMecanico + ", " + getString(R.string.Fallos) + " " + fallosMecanico + " " + getString(R.string.nocontestadas) + " " + sincontestarMecanico + "\n\n" +
                        getString(R.string.Perceptiva) + ": " + getString(R.string.aciertos) + " " + aciertosPerceptiva + ", " + getString(R.string.Fallos) + " " + fallosPerceptiva + " " + getString(R.string.nocontestadas) + " " + sincontestarPerceptiva + "\n\n" +
                        getString(R.string.aptitud6) + ": " + getString(R.string.aciertos) + " " + aciertosMemoria + ", " + getString(R.string.Fallos) + " " + fallosMemoria + " " + getString(R.string.nocontestadas) + " " + sincontestarMemoria + "\n\n" +
                        getString(R.string.Abstrapto) + ": " + getString(R.string.aciertos) + " " + aciertosAbstrapto + ", " + getString(R.string.Fallos) + " " + fallosAbstrapto + " " + getString(R.string.nocontestadas) + " " + sincontestarAbstrapto

        );


        nota = (TextView) findViewById(R.id.nota);
        nota.setText(getString(R.string.sunotasobre10) + "\n" + notas.getNotaredondeada());

        notabar = (TextView) findViewById(R.id.notabar);
        try {
            if (!baremorecu[0].equals("")) {
                baremo.setText(baremorecu[0]);
                notas.setBar(Double.parseDouble(baremo.getText().toString()));
                notabaremo();
                notabar.setText(getString(R.string.sunotaconbaremo) + "\n" + notas.getNotaredondeadabar());
            } else {
                notabar.setText(getString(R.string.sunotaconbaremo) + "\n" + "0.0");
            }
        } catch (Exception e) {
            notabar.setText(getString(R.string.sunotaconbaremo) + "\n" + "0.0");
        }

        baremo = (EditText) findViewById(R.id.baremo);

        calcular = (Button) findViewById(R.id.calcular);
        calcular.setText(getString(R.string.calcular));
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baremo.getText().toString().equals("")) {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    getString(R.string.baremonovalido), Toast.LENGTH_SHORT);

                    toast1.show();
                } else {
                    double kk = Double.parseDouble(baremo.getText().toString());
                    if (kk > 0 && kk < 41) {
                        notas.setBar(Double.parseDouble(baremo.getText().toString()));
                        notabaremo();
                        notabar.setText(getString(R.string.sunotaconbaremo) + "\n" + notas.getNotaredondeadabar());
                        try {
                            File ruta_sd;
                            String state = Environment.getExternalStorageState();
                            if (Environment.MEDIA_MOUNTED.equals(state)) {
                                // We can read and write the media
                                ruta_sd = getExternalFilesDir(null);
                            } else {
                                // Load another directory, probably local memory
                                ruta_sd = getFilesDir();
                            }
                            File f = new File(ruta_sd.getAbsolutePath(), "baremo");
                            OutputStreamWriter fout =
                                    new OutputStreamWriter(
                                            new FileOutputStream(f));

                            fout.write(baremo.getText() + "");
                            fout.close();
                            System.out.println(ruta_sd);
                            System.out.println(f);
                        } catch (Exception ex) {
                            Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
                        }
                    } else {
                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        getString(R.string.baremonovalido), Toast.LENGTH_SHORT);

                        toast1.show();
                    }
                }
            }
        });


        compartir = (LinearLayout) findViewById(R.id.compartir);
        compartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baremo.getText().toString().equals("")) {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    getString(R.string.compartirresul), Toast.LENGTH_SHORT);

                    toast1.show();
                } else {
                    Double kk = Double.parseDouble(baremo.getText().toString());
                    if (kk > 0 && kk < 41) {
                        //poner aqui el texto a compartir
                        Intent intentCompartir = new Intent(Intent.ACTION_SEND);
                        intentCompartir.setType("text/plain");
                        intentCompartir.putExtra(Intent.EXTRA_SUBJECT, "Psicotécnicos Tropa");
                        intentCompartir.putExtra(Intent.EXTRA_STREAM, Uri.parse("android.resource://com.example.andym.psicotecnicostropa/drawable/ic_launcher"));
                        intentCompartir.putExtra(Intent.EXTRA_TEXT, getString(R.string.minota) + " " + notas.getNotaredondeada() + "\n" + getString(R.string.minotabare) + " " + notas.getNotaredondeadabar() + "\n" + "https://play.google.com/store/apps/details?id=com.naroh.tropaPsicotecnicoOficial");
                        intentCompartir.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(Intent.createChooser(intentCompartir, getString(R.string.compartiren)));
                    } else {
                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        getString(R.string.compartirresul), Toast.LENGTH_SHORT);

                        toast1.show();
                    }
                }
            }
        });

        introbar = (Button) findViewById(R.id.introbar);
        introbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(main_resultado_exam.this, main_calculabaremo.class));
                overridePendingTransition(R.anim.transpain, R.anim.transpaout);
            }
        });

        guardar = (Button) findViewById(R.id.guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baremo.getText().toString().equals("")) {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    getString(R.string.guardarresul), Toast.LENGTH_SHORT);

                    toast1.show();
                } else {
                    guardar.setEnabled(false);
                    Double kk = Double.parseDouble(baremo.getText().toString());
                    if (kk > 0 && kk < 41) {
                        if (notas.getNotaredondeadabar() != 0) {
                            Date date = new Date();
                            DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
                            String convertido = fechaHora.format(date);

                            File ruta_sd;
                            String state = Environment.getExternalStorageState();
                            if (Environment.MEDIA_MOUNTED.equals(state)) {
                                // We can read and write the media
                                ruta_sd = getExternalFilesDir(null);
                            } else {
                                // Load another directory, probably local memory
                                ruta_sd = getFilesDir();
                            }
                            String nombre = convertido + "examen" + notas.getNotaredondeadabar();
                            File a = new File(ruta_sd.getAbsolutePath(), convertido+"examen" + notas.getNotaredondeadabar());
                            //
                            try {
                                JSONObject jsonObject = new JSONObject();
                                List<JSONObject> JSONObjectList;
                                JSONObject jsonSub;
                                try {
                                    jsonObject.put("notaredondeadabar", notas.getNotaredondeadabar());
                                    jsonObject.put("notaredondeada", notas.getNotaredondeada());
                                    jsonObject.put("bar", notas.getBar());
                                    jsonObject.put("notasobre10", notas.getNotasobre10());
                                    jsonObject.put("baremoredondeado", notas.getBaremoredondeado());

                                    jsonObject.put("aciertosVerbal", notas.aciertosVerbal);
                                    jsonObject.put("fallosVerbal", notas.fallosVerbal);
                                    jsonObject.put("sincontestarVerbal", notas.sincontestarVerbal);

                                    jsonObject.put("aciertosNumerico", notas.aciertosNumerico);
                                    jsonObject.put("fallosNumerico", notas.fallosNumerico);
                                    jsonObject.put("sincontestarNumerico", notas.sincontestarNumerico);

                                    jsonObject.put("aciertosEspacial", notas.aciertosEspacial);
                                    jsonObject.put("fallosEspacial", notas.fallosEspacial);
                                    jsonObject.put("sincontestarEspacial", notas.sincontestarEspacial);

                                    jsonObject.put("aciertosMecanico", notas.aciertosMecanico);
                                    jsonObject.put("fallosMecanico", notas.fallosMecanico);
                                    jsonObject.put("sincontestarMecanico", notas.sincontestarMecanico);

                                    jsonObject.put("aciertosPerceptiva", notas.aciertosPerceptiva);
                                    jsonObject.put("fallosPerceptiva", notas.fallosPerceptiva);
                                    jsonObject.put("sincontestarPerceptiva", notas.sincontestarPerceptiva);

                                    jsonObject.put("aciertosMemoria", notas.aciertosMemoria);
                                    jsonObject.put("fallosMemoria", notas.fallosMemoria);
                                    jsonObject.put("sincontestarMemoria", notas.sincontestarMemoria);

                                    jsonObject.put("aciertosAbstrapto", notas.aciertosAbstrapto);
                                    jsonObject.put("fallosAbstrapto", notas.fallosAbstrapto);
                                    jsonObject.put("sincontestarAbstrapto", notas.sincontestarAbstrapto);

                                    JSONObjectList = new ArrayList<>();
                                    for (Preguntas e : notas.getBloqueverbal()) {
                                        jsonSub = new JSONObject();
                                        jsonSub.put("cont", e.getCont() + "");
                                        jsonSub.put("pregunta", e.getPregunta());
                                        jsonSub.put("respuestaA", e.getRespuestaA());
                                        jsonSub.put("respuestaB", e.getRespuestaB());
                                        jsonSub.put("respuestaC", e.getRespuestaC());
                                        jsonSub.put("respuestaD", e.getRespuestaD());
                                        jsonSub.put("solu", e.getSolu());
                                        jsonSub.put("expliSol", e.getExpliSol());
                                        jsonSub.put("imgPregunta", e.getImgPregunta());
                                        jsonSub.put("imgA", e.getImgA());
                                        jsonSub.put("imgB", e.getImgB());
                                        jsonSub.put("imgC", e.getImgC());
                                        jsonSub.put("imgD", e.getImgD());
                                        jsonSub.put("imgSol", e.getImgSol());
                                        jsonSub.put("imgExpli", e.getImgExpli());
                                        jsonSub.put("respulsada", e.getRespulsada() + "");
                                        JSONObjectList.add(jsonSub);
                                    }
                                    jsonObject.put("bloqueverbal", JSONObjectList.toString());


                                    JSONObjectList = new ArrayList<>();
                                    for (Preguntas e : notas.getBloquenumerico()) {
                                        jsonSub = new JSONObject();
                                        jsonSub.put("cont", e.getCont());
                                        jsonSub.put("pregunta", e.getPregunta());
                                        jsonSub.put("respuestaA", e.getRespuestaA());
                                        jsonSub.put("respuestaB", e.getRespuestaB());
                                        jsonSub.put("respuestaC", e.getRespuestaC());
                                        jsonSub.put("respuestaD", e.getRespuestaD());
                                        jsonSub.put("solu", e.getSolu());
                                        jsonSub.put("expliSol", e.getExpliSol());
                                        jsonSub.put("imgPregunta", e.getImgPregunta());
                                        jsonSub.put("imgA", e.getImgA());
                                        jsonSub.put("imgB", e.getImgB());
                                        jsonSub.put("imgC", e.getImgC());
                                        jsonSub.put("imgD", e.getImgD());
                                        jsonSub.put("imgSol", e.getImgSol());
                                        jsonSub.put("imgExpli", e.getImgExpli());
                                        jsonSub.put("respulsada", e.getRespulsada());
                                        JSONObjectList.add(jsonSub);
                                    }
                                    jsonObject.put("bloquenumerico", JSONObjectList);


                                    JSONObjectList = new ArrayList<>();
                                    for (Preguntas e : notas.getBloqueespacial()) {
                                        jsonSub = new JSONObject();
                                        jsonSub.put("cont", e.getCont());
                                        jsonSub.put("pregunta", e.getPregunta());
                                        jsonSub.put("respuestaA", e.getRespuestaA());
                                        jsonSub.put("respuestaB", e.getRespuestaB());
                                        jsonSub.put("respuestaC", e.getRespuestaC());
                                        jsonSub.put("respuestaD", e.getRespuestaD());
                                        jsonSub.put("solu", e.getSolu());
                                        jsonSub.put("expliSol", e.getExpliSol());
                                        jsonSub.put("imgPregunta", e.getImgPregunta());
                                        jsonSub.put("imgA", e.getImgA());
                                        jsonSub.put("imgB", e.getImgB());
                                        jsonSub.put("imgC", e.getImgC());
                                        jsonSub.put("imgD", e.getImgD());
                                        jsonSub.put("imgSol", e.getImgSol());
                                        jsonSub.put("imgExpli", e.getImgExpli());
                                        jsonSub.put("respulsada", e.getRespulsada());
                                        JSONObjectList.add(jsonSub);
                                    }
                                    jsonObject.put("bloqueespacial", JSONObjectList);


                                    JSONObjectList = new ArrayList<>();
                                    for (Preguntas e : notas.getBloquemecanico()) {
                                        jsonSub = new JSONObject();
                                        jsonSub.put("cont", e.getCont());
                                        jsonSub.put("pregunta", e.getPregunta());
                                        jsonSub.put("respuestaA", e.getRespuestaA());
                                        jsonSub.put("respuestaB", e.getRespuestaB());
                                        jsonSub.put("respuestaC", e.getRespuestaC());
                                        jsonSub.put("respuestaD", e.getRespuestaD());
                                        jsonSub.put("solu", e.getSolu());
                                        jsonSub.put("expliSol", e.getExpliSol());
                                        jsonSub.put("imgPregunta", e.getImgPregunta());
                                        jsonSub.put("imgA", e.getImgA());
                                        jsonSub.put("imgB", e.getImgB());
                                        jsonSub.put("imgC", e.getImgC());
                                        jsonSub.put("imgD", e.getImgD());
                                        jsonSub.put("imgSol", e.getImgSol());
                                        jsonSub.put("imgExpli", e.getImgExpli());
                                        jsonSub.put("respulsada", e.getRespulsada());
                                        JSONObjectList.add(jsonSub);
                                    }
                                    jsonObject.put("bloquemecanico", JSONObjectList);


                                    JSONObjectList = new ArrayList<>();
                                    for (Preguntas e : notas.getBloqueperceptiva()) {
                                        jsonSub = new JSONObject();
                                        jsonSub.put("cont", e.getCont());
                                        jsonSub.put("pregunta", e.getPregunta());
                                        jsonSub.put("respuestaA", e.getRespuestaA());
                                        jsonSub.put("respuestaB", e.getRespuestaB());
                                        jsonSub.put("respuestaC", e.getRespuestaC());
                                        jsonSub.put("respuestaD", e.getRespuestaD());
                                        jsonSub.put("solu", e.getSolu());
                                        jsonSub.put("expliSol", e.getExpliSol());
                                        jsonSub.put("imgPregunta", e.getImgPregunta());
                                        jsonSub.put("imgA", e.getImgA());
                                        jsonSub.put("imgB", e.getImgB());
                                        jsonSub.put("imgC", e.getImgC());
                                        jsonSub.put("imgD", e.getImgD());
                                        jsonSub.put("imgSol", e.getImgSol());
                                        jsonSub.put("imgExpli", e.getImgExpli());
                                        jsonSub.put("respulsada", e.getRespulsada());
                                        JSONObjectList.add(jsonSub);
                                    }
                                    jsonObject.put("bloqueperceptiva", JSONObjectList);


                                    JSONObjectList = new ArrayList<>();
                                    for (Preguntas e : notas.getBloquememoria()) {
                                        jsonSub = new JSONObject();
                                        jsonSub.put("cont", e.getCont());
                                        jsonSub.put("pregunta", e.getPregunta());
                                        jsonSub.put("respuestaA", e.getRespuestaA());
                                        jsonSub.put("respuestaB", e.getRespuestaB());
                                        jsonSub.put("respuestaC", e.getRespuestaC());
                                        jsonSub.put("respuestaD", e.getRespuestaD());
                                        jsonSub.put("solu", e.getSolu());
                                        jsonSub.put("expliSol", e.getExpliSol());
                                        jsonSub.put("imgPregunta", e.getImgPregunta());
                                        jsonSub.put("imgA", e.getImgA());
                                        jsonSub.put("imgB", e.getImgB());
                                        jsonSub.put("imgC", e.getImgC());
                                        jsonSub.put("imgD", e.getImgD());
                                        jsonSub.put("imgSol", e.getImgSol());
                                        jsonSub.put("imgExpli", e.getImgExpli());
                                        jsonSub.put("respulsada", e.getRespulsada());
                                        JSONObjectList.add(jsonSub);
                                    }
                                    jsonObject.put("bloquememoria", JSONObjectList);


                                    JSONObjectList = new ArrayList<>();
                                    for (Preguntas e : notas.getBloqueabstrapto()) {
                                        jsonSub = new JSONObject();
                                        jsonSub.put("cont", e.getCont());
                                        jsonSub.put("pregunta", e.getPregunta());
                                        jsonSub.put("respuestaA", e.getRespuestaA());
                                        jsonSub.put("respuestaB", e.getRespuestaB());
                                        jsonSub.put("respuestaC", e.getRespuestaC());
                                        jsonSub.put("respuestaD", e.getRespuestaD());
                                        jsonSub.put("solu", e.getSolu());
                                        jsonSub.put("expliSol", e.getExpliSol());
                                        jsonSub.put("imgPregunta", e.getImgPregunta());
                                        jsonSub.put("imgA", e.getImgA());
                                        jsonSub.put("imgB", e.getImgB());
                                        jsonSub.put("imgC", e.getImgC());
                                        jsonSub.put("imgD", e.getImgD());
                                        jsonSub.put("imgSol", e.getImgSol());
                                        jsonSub.put("imgExpli", e.getImgExpli());
                                        jsonSub.put("respulsada", e.getRespulsada());
                                        JSONObjectList.add(jsonSub);
                                    }
                                    jsonObject.put("bloqueabstrapto", JSONObjectList);

                                    main_examen.guardado = true;
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                String arreglojson = jsonObject.toString();
                                arreglojson = arreglojson.replace("\\n", "ppppp");
                                arreglojson = arreglojson.replace("\\p", "p");
                                arreglojson = arreglojson.replace("\"[", "[");
                                arreglojson = arreglojson.replace("]\"", "]");
                                arreglojson = arreglojson.replace("{\\", "{");
                                arreglojson = arreglojson.replace("\\\"", "\"");
                                arreglojson = arreglojson.replace("ppppp", "\\n");



                                OutputStreamWriter foutn =
                                        new OutputStreamWriter(
                                                new FileOutputStream(a));
                                foutn.write(arreglojson);
                                foutn.close();
                                System.out.println(ruta_sd);
                                System.out.println(a);

                                Toast toast1 =
                                        Toast.makeText(getApplicationContext(), getString(R.string.guardado), Toast.LENGTH_SHORT);
                                toast1.show();
                            } catch (IOException ll) {
                                Toast toast1 =
                                        Toast.makeText(getApplicationContext(),
                                                "Error al guardar su nota", Toast.LENGTH_SHORT);

                                toast1.show();
                            }

                        } else {
                            Toast toast1 =
                                    Toast.makeText(getApplicationContext(),
                                            "Necesitas calcular la nota con baremo", Toast.LENGTH_SHORT);

                            toast1.show();
                        }
                    } else {
                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        getString(R.string.guardarresul), Toast.LENGTH_SHORT);

                        toast1.show();
                    }
                }
            }
        });

        if (main_examen.acabar == true) {
            guardar.setEnabled(true);
            if (main_examen.guardado == true) {
                guardar.setEnabled(false);
            } else {
                guardar.setEnabled(true);
            }
        } else {
            guardar.setEnabled(false);
        }


    }

    public void notabaremo() {
        notas.setBaremoredondeado(((notas.getBar() / 40) * 10) * 0.3);
        double notabaremo = (notas.getNotasobre10() * 0.7);
        double a = redondearDecimales(notas.getBaremoredondeado(), 5);
        double b = redondearDecimales(notabaremo, 5);
        notas.setNotaredondeadabar(redondearDecimales(a + b, 1));
    }

    public static double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado = (resultado - parteEntera) * Math.pow(10, numeroDecimales);
        resultado = Math.round(resultado);
        resultado = (resultado / Math.pow(10, numeroDecimales)) + parteEntera;
        return resultado;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(this)
                    .setIcon(getResources().getDrawable(R.drawable.iexc))
                    .setTitle(getString(R.string.atencion))
                    .setCancelable(false)
                    .setMessage(getString(R.string.salirresultado))
                    .setNegativeButton(getString(R.string.verfallos), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setPositiveButton(getString(R.string.permanecer), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which) {

                        }
                    }).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
