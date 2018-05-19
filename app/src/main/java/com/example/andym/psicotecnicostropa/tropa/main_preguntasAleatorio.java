package com.example.andym.psicotecnicostropa.tropa;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.andym.psicotecnicostropa.R;
import com.example.andym.psicotecnicostropa.tropa.dtoTropa.Preguntas;
import com.example.andym.psicotecnicostropa.dto.contador;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class main_preguntasAleatorio extends Activity {


    int arreglo = 0;
    boolean memoria = false;
    Preguntas[] pre;
    contador cont;
    int[] pos;
    int colocar = 0;
    int inmemo = 0;
    int outmemo = 0;
    int tempomemoria = 10000;//10000

    String[] pregunta = null;
    String[] resA = null;
    String[] resB = null;
    String[] resC = null;
    String[] resD = null;
    String[] sol = null;
    String[] expliSol = null;

    String[] imgPre = null;
    String[] imgA = null;
    String[] imgB = null;
    String[] imgC = null;
    String[] imgD = null;
    String[] imgSol = null;
    String[] imgExpli = null;

    int[] num;

    int[] respulsada;
    int[] listamemo;

    RelativeLayout a;
    TextView respuestaA;
    RelativeLayout b;
    TextView respuestaB;
    RelativeLayout c;
    TextView respuestaC;
    RelativeLayout d;
    TextView respuestaD;
    TextView solucion, estado, kk;

    LinearLayout Msolucion;
    LinearLayout guardar;
    int aciertos = 0;
    int fallos = 0;

    ViewFlipper viewflipper;
    Animation animrightatras = null;
    Animation animrightalante = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_preguntas);

        RelativeLayout padre = (RelativeLayout) findViewById(R.id.relativeLayout);
        RelativeLayout subcontenedor = (RelativeLayout) findViewById(R.id.subcontenedor);
        Calendar cc1 = new GregorianCalendar();
        int dia = cc1.get(Calendar.DAY_OF_MONTH);
        int mes = cc1.get(Calendar.MONTH)+1;
        if( (mes ==11 || mes ==12) || (mes ==1 && dia <=7)){
            padre.setBackgroundResource(R.color.rojonavidad);
            subcontenedor.setBackgroundResource(R.color.rojonavidad);
        }else{

        }

        animrightatras = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                -1.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        animrightatras.setDuration(1000);
        animrightatras.setInterpolator(new OvershootInterpolator());

        animrightalante = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                1.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        animrightalante.setDuration(1000);
        animrightalante.setInterpolator(new OvershootInterpolator());

        // orientacion pantalla
        Configuration config = getResources().getConfiguration();
        if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        viewflipper = (ViewFlipper)findViewById(R.id.ViewFlipper1);

        cont = new contador();
        ImageView prohibido = (ImageView) findViewById(R.id.prohibido);
        prohibido.setVisibility(View.GONE);
        TextView cuentatras = (TextView) findViewById(R.id.cuentatras);
        cuentatras.setVisibility(View.GONE);
        TextView bloque = (TextView) findViewById(R.id.bloque);
        bloque.setText(getString(R.string.modoaleatorio));
        kk = (TextView) findViewById(R.id.arreglo);
        kk.setVisibility(View.VISIBLE);
        Msolucion = (LinearLayout) findViewById(R.id.solucion);
        Msolucion.setVisibility(View.GONE);
        guardar = (LinearLayout) findViewById(R.id.guardar);
        guardar.setVisibility(View.VISIBLE);
        estado = (TextView) findViewById(R.id.estado);
        estado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                getString(R.string.aciertos) + " " + aciertos + " " + getString(R.string.de) + " " + (aciertos + fallos) + " " + getString(R.string.de) + " " + pregunta.length + " " + getString(R.string.preguntas), Toast.LENGTH_SHORT);
                toast1.show();
            }
        });
        guardar.setVisibility(View.INVISIBLE);

        String a1[] = getResources().getStringArray(R.array.preverbal);
        String a2[] = getResources().getStringArray(R.array.preabstrapto);
        String a3[] = getResources().getStringArray(R.array.prememoria);
        String a4[] = getResources().getStringArray(R.array.preperceptiva);
        String a5[] = getResources().getStringArray(R.array.premecanico);
        String a6[] = getResources().getStringArray(R.array.prenumerico);
        String a7[] = getResources().getStringArray(R.array.preespacial);

        String b1[] = getResources().getStringArray(R.array.resAverbal);
        String b2[] = getResources().getStringArray(R.array.resAabstrapto);
        String b3[] = getResources().getStringArray(R.array.resAmemoria);
        String b4[] = getResources().getStringArray(R.array.resAperceptiva);
        String b5[] = getResources().getStringArray(R.array.resAmecanico);
        String b6[] = getResources().getStringArray(R.array.resAnumerico);
        String b7[] = getResources().getStringArray(R.array.resAespacial);

        String c1[] = getResources().getStringArray(R.array.resBverbal);
        String c2[] = getResources().getStringArray(R.array.resBabstrapto);
        String c3[] = getResources().getStringArray(R.array.resBmemoria);
        String c4[] = getResources().getStringArray(R.array.resBperceptiva);
        String c5[] = getResources().getStringArray(R.array.resBmecanico);
        String c6[] = getResources().getStringArray(R.array.resBnumerico);
        String c7[] = getResources().getStringArray(R.array.resBespacial);

        String d1[] = getResources().getStringArray(R.array.resCverbal);
        String d2[] = getResources().getStringArray(R.array.resCabstrapto);
        String d3[] = getResources().getStringArray(R.array.resCmemoria);
        String d4[] = getResources().getStringArray(R.array.resCperceptiva);
        String d5[] = getResources().getStringArray(R.array.resCmecanico);
        String d6[] = getResources().getStringArray(R.array.resCnumerico);
        String d7[] = getResources().getStringArray(R.array.resCespacial);

        String e1[] = getResources().getStringArray(R.array.resDverbal);
        String e2[] = getResources().getStringArray(R.array.resDabstrapto);
        String e3[] = getResources().getStringArray(R.array.resDmemoria);
        String e4[] = getResources().getStringArray(R.array.resDperceptiva);
        String e5[] = getResources().getStringArray(R.array.resDmecanico);
        String e6[] = getResources().getStringArray(R.array.resDnumerico);
        String e7[] = getResources().getStringArray(R.array.resDespacial);

        String f1[] = getResources().getStringArray(R.array.solverbal);
        String f2[] = getResources().getStringArray(R.array.solabstrapto);
        String f3[] = getResources().getStringArray(R.array.solmemoria);
        String f4[] = getResources().getStringArray(R.array.solperceptiva);
        String f5[] = getResources().getStringArray(R.array.solmecanico);
        String f6[] = getResources().getStringArray(R.array.solnumerico);
        String f7[] = getResources().getStringArray(R.array.solespacial);

        String g1[] = getResources().getStringArray(R.array.expliSolverbal);
        String g2[] = getResources().getStringArray(R.array.expliSolabstrapto);
        String g3[] = getResources().getStringArray(R.array.expliSolmemoria);
        String g4[] = getResources().getStringArray(R.array.expliSolperceptiva);
        String g5[] = getResources().getStringArray(R.array.expliSolmecanico);
        String g6[] = getResources().getStringArray(R.array.expliSolnumerico);
        String g7[] = getResources().getStringArray(R.array.expliSolespacial);

        String h1[] = getResources().getStringArray(R.array.imgPreverbal);
        String h2[] = getResources().getStringArray(R.array.imgPreabstrapto);
        String h3[] = getResources().getStringArray(R.array.imgPrememoria);
        String h4[] = getResources().getStringArray(R.array.imgPreperceptiva);
        String h5[] = getResources().getStringArray(R.array.imgPremecanico);
        String h6[] = getResources().getStringArray(R.array.imgPrenumerico);
        String h7[] = getResources().getStringArray(R.array.imgPreespacial);

        String i1[] = getResources().getStringArray(R.array.imgAverbal);
        String i2[] = getResources().getStringArray(R.array.imgAabstrapto);
        String i3[] = getResources().getStringArray(R.array.imgAmemoria);
        String i4[] = getResources().getStringArray(R.array.imgAperceptiva);
        String i5[] = getResources().getStringArray(R.array.imgAmecanico);
        String i6[] = getResources().getStringArray(R.array.imgAnumerico);
        String i7[] = getResources().getStringArray(R.array.imgAespacial);

        String j1[] = getResources().getStringArray(R.array.imgBverbal);
        String j2[] = getResources().getStringArray(R.array.imgBabstrapto);
        String j3[] = getResources().getStringArray(R.array.imgBmemoria);
        String j4[] = getResources().getStringArray(R.array.imgBperceptiva);
        String j5[] = getResources().getStringArray(R.array.imgBmecanico);
        String j6[] = getResources().getStringArray(R.array.imgBnumerico);
        String j7[] = getResources().getStringArray(R.array.imgBespacial);

        String k1[] = getResources().getStringArray(R.array.imgCverbal);
        String k2[] = getResources().getStringArray(R.array.imgCabstrapto);
        String k3[] = getResources().getStringArray(R.array.imgCmemoria);
        String k4[] = getResources().getStringArray(R.array.imgCperceptiva);
        String k5[] = getResources().getStringArray(R.array.imgCmecanico);
        String k6[] = getResources().getStringArray(R.array.imgCnumerico);
        String k7[] = getResources().getStringArray(R.array.imgCespacial);

        String l1[] = getResources().getStringArray(R.array.imgDverbal);
        String l2[] = getResources().getStringArray(R.array.imgDabstrapto);
        String l3[] = getResources().getStringArray(R.array.imgDmemoria);
        String l4[] = getResources().getStringArray(R.array.imgDperceptiva);
        String l5[] = getResources().getStringArray(R.array.imgDmecanico);
        String l6[] = getResources().getStringArray(R.array.imgDnumerico);
        String l7[] = getResources().getStringArray(R.array.imgDespacial);

        String m1[] = getResources().getStringArray(R.array.imgSolverbal);
        String m2[] = getResources().getStringArray(R.array.imgSolabstrapto);
        String m3[] = getResources().getStringArray(R.array.imgSolmemoria);
        String m4[] = getResources().getStringArray(R.array.imgSolperceptiva);
        String m5[] = getResources().getStringArray(R.array.imgSolmecanico);
        String m6[] = getResources().getStringArray(R.array.imgSolnumerico);
        String m7[] = getResources().getStringArray(R.array.imgSolespacial);

        String n1[] = getResources().getStringArray(R.array.imgExpliverbal);
        String n2[] = getResources().getStringArray(R.array.imgExpliabstrapto);
        String n3[] = getResources().getStringArray(R.array.imgExplimemoria);
        String n4[] = getResources().getStringArray(R.array.imgExpliperceptiva);
        String n5[] = getResources().getStringArray(R.array.imgExplimecanico);
        String n6[] = getResources().getStringArray(R.array.imgExplinumerico);
        String n7[] = getResources().getStringArray(R.array.imgExpliespacial);

        int as = a1.length + a2.length + a3.length + a4.length + a5.length + a6.length + a7.length;

        inmemo = (a1.length + a2.length);
        outmemo = inmemo + a3.length;

        listamemo = new int[a3.length];
        for (int i = 0; i < a3.length; i++) {
            listamemo[i] = inmemo + i;
        }
        pregunta = new String[as];
        resA = new String[as];
        resB = new String[as];
        resC = new String[as];
        resD = new String[as];
        sol = new String[as];
        expliSol = new String[as];

        imgPre = new String[as];
        imgA = new String[as];
        imgB = new String[as];
        imgC = new String[as];
        imgD = new String[as];
        imgSol = new String[as];
        imgExpli = new String[as];

        for (int i = 0; i < a1.length; i++) {
            pregunta[i] = a1[i];
        }
        int e = 0;
        for (int i = (a1.length); i < a1.length + a2.length; i++) {
            pregunta[i] = a2[e];
            e++;
        }
        e = 0;
        for (int i = (a1.length + a2.length); i < a1.length + a2.length + a3.length; i++) {
            pregunta[i] = a3[e];
            e++;
        }
        e = 0;
        for (int i = (a1.length + a2.length + a3.length); i < a1.length + a2.length + a3.length + a4.length; i++) {
            pregunta[i] = a4[e];
            e++;
        }
        e = 0;
        for (int i = (a1.length + a2.length + a3.length + a4.length); i < a1.length + a2.length + a3.length + a4.length + a5.length; i++) {
            pregunta[i] = a5[e];
            e++;
        }
        e = 0;
        for (int i = (a1.length + a2.length + a3.length + a4.length + a5.length); i < a1.length + a2.length + a3.length + a4.length + a5.length + a6.length; i++) {
            pregunta[i] = a6[e];
            e++;
        }
        e = 0;
        for (int i = (a1.length + a2.length + a3.length + a4.length + a5.length + a6.length); i < a1.length + a2.length + a3.length + a4.length + a5.length + a6.length + a7.length; i++) {
            pregunta[i] = a7[e];
            e++;
        }


        e = 0;
        for (int i = 0; i < b1.length; i++) {
            resA[i] = b1[i];
        }
        e = 0;
        for (int i = (b1.length); i < b1.length + b2.length; i++) {
            resA[i] = b2[e];
            e++;
        }
        e = 0;
        for (int i = (b1.length + b2.length); i < b1.length + b2.length + b3.length; i++) {
            resA[i] = b3[e];
            e++;
        }
        e = 0;
        for (int i = (b1.length + b2.length + b3.length); i < b1.length + b2.length + b3.length + b4.length; i++) {
            resA[i] = b4[e];
            e++;
        }
        e = 0;
        for (int i = (b1.length + b2.length + b3.length + b4.length); i < b1.length + b2.length + b3.length + b4.length + b5.length; i++) {
            resA[i] = b5[e];
            e++;
        }
        e = 0;
        for (int i = (b1.length + b2.length + b3.length + b4.length + b5.length); i < b1.length + b2.length + b3.length + b4.length + b5.length + b6.length; i++) {
            resA[i] = b6[e];
            e++;
        }
        e = 0;
        for (int i = (b1.length + b2.length + b3.length + b4.length + b5.length + b6.length); i < b1.length + b2.length + b3.length + b4.length + b5.length + b6.length + b7.length; i++) {
            resA[i] = b7[e];
            e++;
        }


        e = 0;
        for (int i = 0; i < c1.length; i++) {
            resB[i] = c1[i];
        }
        e = 0;
        for (int i = (c1.length); i < c1.length + c2.length; i++) {
            resB[i] = c2[e];
            e++;
        }
        e = 0;
        for (int i = (c1.length + c2.length); i < c1.length + c2.length + c3.length; i++) {
            resB[i] = c3[e];
            e++;
        }
        e = 0;
        for (int i = (c1.length + c2.length + c3.length); i < c1.length + c2.length + c3.length + c4.length; i++) {
            resB[i] = c4[e];
            e++;
        }
        e = 0;
        for (int i = (c1.length + c2.length + c3.length + c4.length); i < c1.length + c2.length + c3.length + c4.length + c5.length; i++) {
            resB[i] = c5[e];
            e++;
        }
        e = 0;
        for (int i = (c1.length + c2.length + c3.length + c4.length + c5.length); i < c1.length + c2.length + c3.length + c4.length + c5.length + c6.length; i++) {
            resB[i] = c6[e];
            e++;
        }
        e = 0;
        for (int i = (c1.length + c2.length + c3.length + c4.length + c5.length + c6.length); i < c1.length + c2.length + c3.length + c4.length + c5.length + c6.length + c7.length; i++) {
            resB[i] = c7[e];
            e++;
        }


        e = 0;
        for (int i = 0; i < d1.length; i++) {
            resC[i] = d1[i];
        }
        e = 0;
        for (int i = (d1.length); i < d1.length + d2.length; i++) {
            resC[i] = d2[e];
            e++;
        }
        e = 0;
        for (int i = (d1.length + d2.length); i < d1.length + d2.length + d3.length; i++) {
            resC[i] = d3[e];
            e++;
        }
        e = 0;
        for (int i = (d1.length + d2.length + d3.length); i < d1.length + d2.length + d3.length + d4.length; i++) {
            resC[i] = d4[e];
            e++;
        }
        e = 0;
        for (int i = (d1.length + d2.length + d3.length + d4.length); i < d1.length + d2.length + d3.length + d4.length + d5.length; i++) {
            resC[i] = d5[e];
            e++;
        }
        e = 0;
        for (int i = (d1.length + d2.length + d3.length + d4.length + d5.length); i < d1.length + d2.length + d3.length + d4.length + d5.length + d6.length; i++) {
            resC[i] = d6[e];
            e++;
        }
        e = 0;
        for (int i = (d1.length + d2.length + d3.length + d4.length + d5.length + d6.length); i < d1.length + d2.length + d3.length + d4.length + d5.length + d6.length + d7.length; i++) {
            resC[i] = d7[e];
            e++;
        }

        e = 0;
        for (int i = 0; i < e1.length; i++) {
            resD[i] = e1[i];
        }
        e = 0;
        for (int i = (e1.length); i < e1.length + e2.length; i++) {
            resD[i] = e2[e];
            e++;
        }
        e = 0;
        for (int i = (e1.length + e2.length); i < e1.length + e2.length + e3.length; i++) {
            resD[i] = e3[e];
            e++;
        }
        e = 0;
        for (int i = (e1.length + e2.length + e3.length); i < e1.length + e2.length + e3.length + e4.length; i++) {
            resD[i] = e4[e];
            e++;
        }
        e = 0;
        for (int i = (e1.length + e2.length + e3.length + e4.length); i < e1.length + e2.length + e3.length + e4.length + e5.length; i++) {
            resD[i] = e5[e];
            e++;
        }
        e = 0;
        for (int i = (e1.length + e2.length + e3.length + e4.length + e5.length); i < e1.length + e2.length + e3.length + e4.length + e5.length + e6.length; i++) {
            resD[i] = e6[e];
            e++;
        }
        e = 0;
        for (int i = (e1.length + e2.length + e3.length + e4.length + e5.length + e6.length); i < e1.length + e2.length + e3.length + e4.length + e5.length + e6.length + e7.length; i++) {
            resD[i] = e7[e];
            e++;
        }


        e = 0;
        for (int i = 0; i < f1.length; i++) {
            sol[i] = f1[i];
        }
        e = 0;
        for (int i = (f1.length); i < f1.length + f2.length; i++) {
            sol[i] = f2[e];
            e++;
        }
        e = 0;
        for (int i = (f1.length + f2.length); i < f1.length + f2.length + f3.length; i++) {
            sol[i] = f3[e];
            e++;
        }
        e = 0;
        for (int i = (f1.length + f2.length + f3.length); i < f1.length + f2.length + f3.length + f4.length; i++) {
            sol[i] = f4[e];
            e++;
        }
        e = 0;
        for (int i = (f1.length + f2.length + f3.length + f4.length); i < f1.length + f2.length + f3.length + f4.length + f5.length; i++) {
            sol[i] = f5[e];
            e++;
        }
        e = 0;
        for (int i = (f1.length + f2.length + f3.length + f4.length + f5.length); i < f1.length + f2.length + f3.length + f4.length + f5.length + f6.length; i++) {
            sol[i] = f6[e];
            e++;
        }
        e = 0;
        for (int i = (f1.length + f2.length + f3.length + f4.length + f5.length + f6.length); i < f1.length + f2.length + f3.length + f4.length + f5.length + f6.length + f7.length; i++) {
            sol[i] = f7[e];
            e++;
        }


        e = 0;
        for (int i = 0; i < g1.length; i++) {
            expliSol[i] = g1[i];
        }
        e = 0;
        for (int i = (g1.length); i < g1.length + g2.length; i++) {
            expliSol[i] = g2[e];
            e++;
        }
        e = 0;
        for (int i = (g1.length + g2.length); i < g1.length + g2.length + g3.length; i++) {
            expliSol[i] = g3[e];
            e++;
        }
        e = 0;
        for (int i = (g1.length + g2.length + g3.length); i < g1.length + g2.length + g3.length + g4.length; i++) {
            expliSol[i] = g4[e];
            e++;
        }
        e = 0;
        for (int i = (g1.length + g2.length + g3.length + g4.length); i < g1.length + g2.length + g3.length + g4.length + g5.length; i++) {
            expliSol[i] = g5[e];
            e++;
        }
        e = 0;
        for (int i = (g1.length + g2.length + g3.length + g4.length + g5.length); i < g1.length + g2.length + g3.length + g4.length + g5.length + g6.length; i++) {
            expliSol[i] = g6[e];
            e++;
        }
        e = 0;
        for (int i = (g1.length + g2.length + g3.length + g4.length + g5.length + g6.length); i < g1.length + g2.length + g3.length + g4.length + g5.length + g6.length + g7.length; i++) {
            expliSol[i] = g7[e];
            e++;
        }

        e = 0;
        for (int i = 0; i < h1.length; i++) {
            imgPre[i] = h1[i];
        }
        e = 0;
        for (int i = (h1.length); i < h1.length + h2.length; i++) {
            imgPre[i] = h2[e];
            e++;
        }
        e = 0;
        for (int i = (h1.length + h2.length); i < h1.length + h2.length + h3.length; i++) {
            imgPre[i] = h3[e];
            e++;
        }
        e = 0;
        for (int i = (h1.length + h2.length + h3.length); i < h1.length + h2.length + h3.length + h4.length; i++) {
            imgPre[i] = h4[e];
            e++;
        }
        e = 0;
        for (int i = (h1.length + h2.length + h3.length + h4.length); i < h1.length + h2.length + h3.length + h4.length + h5.length; i++) {
            imgPre[i] = h5[e];
            e++;
        }
        e = 0;
        for (int i = (h1.length + h2.length + h3.length + h4.length + h5.length); i < h1.length + h2.length + h3.length + h4.length + h5.length + h6.length; i++) {
            imgPre[i] = h6[e];
            e++;
        }
        e = 0;
        for (int i = (h1.length + h2.length + h3.length + h4.length + h5.length + h6.length); i < h1.length + h2.length + h3.length + h4.length + h5.length + h6.length + h7.length; i++) {
            imgPre[i] = h7[e];
            e++;
        }


        e = 0;
        for (int i = 0; i < i1.length; i++) {
            imgA[i] = i1[i];
        }
        e = 0;
        for (int i = (i1.length); i < i1.length + i2.length; i++) {
            imgA[i] = i2[e];
            e++;
        }
        e = 0;
        for (int i = (i1.length + i2.length); i < i1.length + i2.length + i3.length; i++) {
            imgA[i] = i3[e];
            e++;
        }
        e = 0;
        for (int i = (i1.length + i2.length + i3.length); i < i1.length + i2.length + i3.length + i4.length; i++) {
            imgA[i] = i4[e];
            e++;
        }
        e = 0;
        for (int i = (i1.length + i2.length + i3.length + i4.length); i < i1.length + i2.length + i3.length + i4.length + i5.length; i++) {
            imgA[i] = i5[e];
            e++;
        }
        e = 0;
        for (int i = (i1.length + i2.length + i3.length + i4.length + i5.length); i < i1.length + i2.length + i3.length + i4.length + i5.length + i6.length; i++) {
            imgA[i] = i6[e];
            e++;
        }
        e = 0;
        for (int i = (i1.length + i2.length + i3.length + i4.length + i5.length + i6.length); i < i1.length + i2.length + i3.length + i4.length + i5.length + i6.length + i7.length; i++) {
            imgA[i] = i7[e];
            e++;
        }

        e = 0;
        for (int i = 0; i < j1.length; i++) {
            imgB[i] = j1[i];
        }
        e = 0;
        for (int i = (j1.length); i < j1.length + j2.length; i++) {
            imgB[i] = j2[e];
            e++;
        }
        e = 0;
        for (int i = (j1.length + j2.length); i < j1.length + j2.length + j3.length; i++) {
            imgB[i] = j3[e];
            e++;
        }
        e = 0;
        for (int i = (j1.length + j2.length + j3.length); i < j1.length + j2.length + j3.length + j4.length; i++) {
            imgB[i] = j4[e];
            e++;
        }
        e = 0;
        for (int i = (j1.length + j2.length + j3.length + j4.length); i < j1.length + j2.length + j3.length + j4.length + j5.length; i++) {
            imgB[i] = j5[e];
            e++;
        }
        e = 0;
        for (int i = (j1.length + j2.length + j3.length + j4.length + j5.length); i < j1.length + j2.length + j3.length + j4.length + j5.length + j6.length; i++) {
            imgB[i] = j6[e];
            e++;
        }
        e = 0;
        for (int i = (j1.length + j2.length + j3.length + j4.length + j5.length + j6.length); i < j1.length + j2.length + j3.length + j4.length + j5.length + j6.length + j7.length; i++) {
            imgB[i] = j7[e];
            e++;
        }

        e = 0;
        for (int i = 0; i < k1.length; i++) {
            imgC[i] = k1[i];
        }
        e = 0;
        for (int i = (k1.length); i < k1.length + k2.length; i++) {
            imgC[i] = k2[e];
            e++;
        }
        e = 0;
        for (int i = (k1.length + k2.length); i < k1.length + k2.length + k3.length; i++) {
            imgC[i] = k3[e];
            e++;
        }
        e = 0;
        for (int i = (k1.length + k2.length + k3.length); i < k1.length + k2.length + k3.length + k4.length; i++) {
            imgC[i] = k4[e];
            e++;
        }
        e = 0;
        for (int i = (k1.length + k2.length + k3.length + k4.length); i < k1.length + k2.length + k3.length + k4.length + k5.length; i++) {
            imgC[i] = k5[e];
            e++;
        }
        e = 0;
        for (int i = (k1.length + k2.length + k3.length + k4.length + k5.length); i < k1.length + k2.length + k3.length + k4.length + k5.length + k6.length; i++) {
            imgC[i] = k6[e];
            e++;
        }
        e = 0;
        for (int i = (k1.length + k2.length + k3.length + k4.length + k5.length + k6.length); i < k1.length + k2.length + k3.length + k4.length + k5.length + k6.length + k7.length; i++) {
            imgC[i] = k7[e];
            e++;
        }


        e = 0;
        for (int i = 0; i < l1.length; i++) {
            imgD[i] = l1[i];
        }
        e = 0;
        for (int i = (l1.length); i < l1.length + l2.length; i++) {
            imgD[i] = l2[e];
            e++;
        }
        e = 0;
        for (int i = (l1.length + l2.length); i < l1.length + l2.length + l3.length; i++) {
            imgD[i] = l3[e];
            e++;
        }
        e = 0;
        for (int i = (l1.length + l2.length + l3.length); i < l1.length + l2.length + l3.length + l4.length; i++) {
            imgD[i] = l4[e];
            e++;
        }
        e = 0;
        for (int i = (l1.length + l2.length + l3.length + l4.length); i < l1.length + l2.length + l3.length + l4.length + l5.length; i++) {
            imgD[i] = l5[e];
            e++;
        }
        e = 0;
        for (int i = (l1.length + l2.length + l3.length + l4.length + l5.length); i < l1.length + l2.length + l3.length + l4.length + l5.length + l6.length; i++) {
            imgD[i] = l6[e];
            e++;
        }
        e = 0;
        for (int i = (l1.length + l2.length + l3.length + l4.length + l5.length + l6.length); i < l1.length + l2.length + l3.length + l4.length + l5.length + l6.length + l7.length; i++) {
            imgD[i] = l7[e];
            e++;
        }


        e = 0;
        for (int i = 0; i < m1.length; i++) {
            imgSol[i] = m1[i];
        }
        e = 0;
        for (int i = (m1.length); i < m1.length + m2.length; i++) {
            imgSol[i] = m2[e];
            e++;
        }
        e = 0;
        for (int i = (m1.length + m2.length); i < m1.length + m2.length + m3.length; i++) {
            imgSol[i] = m3[e];
            e++;
        }
        e = 0;
        for (int i = (m1.length + m2.length + m3.length); i < m1.length + m2.length + m3.length + m4.length; i++) {
            imgSol[i] = m4[e];
            e++;
        }
        e = 0;
        for (int i = (m1.length + m2.length + m3.length + m4.length); i < m1.length + m2.length + m3.length + m4.length + m5.length; i++) {
            imgSol[i] = m5[e];
            e++;
        }
        e = 0;
        for (int i = (m1.length + m2.length + m3.length + m4.length + m5.length); i < m1.length + m2.length + m3.length + m4.length + m5.length + m6.length; i++) {
            imgSol[i] = m6[e];
            e++;
        }
        e = 0;
        for (int i = (m1.length + m2.length + m3.length + m4.length + m5.length + m6.length); i < m1.length + m2.length + m3.length + m4.length + m5.length + m6.length + m7.length; i++) {
            imgSol[i] = m7[e];
            e++;
        }


        e = 0;
        for (int i = 0; i < n1.length; i++) {
            imgExpli[i] = n1[i];
        }
        e = 0;
        for (int i = (n1.length); i < n1.length + n2.length; i++) {
            imgExpli[i] = n2[e];
            e++;
        }
        e = 0;
        for (int i = (n1.length + n2.length); i < n1.length + n2.length + n3.length; i++) {
            imgExpli[i] = n3[e];
            e++;
        }
        e = 0;
        for (int i = (n1.length + n2.length + n3.length); i < n1.length + n2.length + n3.length + n4.length; i++) {
            imgExpli[i] = n4[e];
            e++;
        }
        e = 0;
        for (int i = (n1.length + n2.length + n3.length + n4.length); i < n1.length + n2.length + n3.length + n4.length + n5.length; i++) {
            imgExpli[i] = n5[e];
            e++;
        }
        e = 0;
        for (int i = (n1.length + n2.length + n3.length + n4.length + n5.length); i < n1.length + n2.length + n3.length + n4.length + n5.length + n6.length; i++) {
            imgExpli[i] = n6[e];
            e++;
        }
        e = 0;
        for (int i = (n1.length + n2.length + n3.length + n4.length + n5.length + n6.length); i < n1.length + n2.length + n3.length + n4.length + n5.length + n6.length + n7.length; i++) {
            imgExpli[i] = n7[e];
            e++;
        }

        int h = 0, cantidad = pregunta.length, rango = pregunta.length;
        num = new int[cantidad];

        num[h] = (int) (Math.random() * rango);
        for (h = 1; h < cantidad; h++) {
            num[h] = (int) (Math.random() * rango);
            for (int j = 0; j < h; j++) {
                if (num[h] == num[j]) {
                    h--;
                }
            }
        }

        respulsada = new int[cantidad];
        for (int i = 0; i < cantidad; i++) {
            respulsada[i] = 0;
        }

        pos = new int[cantidad];
        for (int i = 0; i < cantidad; i++) {
            pos[i] = 0;
        }
        pre = new Preguntas[rango];
        for (int i = 0; i < cantidad; i++) {
            pre[i] = new Preguntas(
                    pregunta[num[i]], resA[num[i]], resB[num[i]], resC[num[i]], resD[num[i]], sol[num[i]], expliSol[num[i]], imgPre[num[i]],
                    imgA[num[i]], imgB[num[i]], imgC[num[i]], imgD[num[i]], imgSol[num[i]], imgExpli[num[i]], respulsada[num[i]],"");
        }

        final Button alante = (Button) findViewById(R.id.alante);
        final Button atras = (Button) findViewById(R.id.atras);
        limpiarelementos();
        avanza();
        //colocar++;
        recolocar();
        calcularestado();
        atras.setVisibility(View.INVISIBLE);
        alante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avanza();
				colocar++;
                recolocar();
            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ocultaratras();
                if (cont.getCont() > 0) {
                    cont.setCont(cont.getCont() - 1);
                    if (arreglo == 1) {
                        cont.setCont(cont.getCont() - 1);
                    }
                    limpiarelementos();
                    ocultaratras();
                    arreglo = 2;
                    viewflipper.setInAnimation(animrightatras);
                    viewflipper.showPrevious();
                } else {
                    Toast.makeText(getApplicationContext(), "Fin", Toast.LENGTH_SHORT).show();
                }
				colocar--;
                if (colocar == -1) {
                    colocar = 0;
                }
                recolocar();
            }
        });

        a = (RelativeLayout) findViewById(R.id.a);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String opt = "a";
                verificarRes(opt);
                pos[colocar] = 1;

                calcularestado();
                if (memoria) {
                    if (cont.getCont() < pregunta.length && cont.getCont() >= 0) {
                        alante.setVisibility(View.VISIBLE);
                        if (cont.getCont() != 0) {
                            atras.setVisibility(View.VISIBLE);
                            alante.setVisibility(View.VISIBLE);
                        }
                    }
                    if (cont.getCont() - 1 == 0) {
                        atras.setVisibility(View.INVISIBLE);
                    }
                    if (cont.getCont() - 1 == pregunta.length) {
                        alante.setVisibility(View.INVISIBLE);
                    }
                    memoria = false;
                }

            }
        });
        b = (RelativeLayout) findViewById(R.id.b);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String opt = "b";
                verificarRes(opt);
                pos[colocar] = 2;

                calcularestado();
                if (memoria) {
                    if (cont.getCont() < pregunta.length && cont.getCont() >= 0) {
                        alante.setVisibility(View.VISIBLE);
                        if (cont.getCont() != 0) {
                            atras.setVisibility(View.VISIBLE);
                        }
                    }
                    if (cont.getCont() - 1 == 0) {
                        atras.setVisibility(View.INVISIBLE);
                    }
                    if (cont.getCont() - 1 == pregunta.length) {
                        alante.setVisibility(View.INVISIBLE);
                    }
                    memoria = false;
                }
            }
        });
        c = (RelativeLayout) findViewById(R.id.c);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String opt = "c";
                verificarRes(opt);
                pos[colocar] = 3;

                calcularestado();
                if (memoria) {
                    if (cont.getCont() < pregunta.length && cont.getCont() >= 0) {
                        alante.setVisibility(View.VISIBLE);
                        if (cont.getCont() != 0) {
                            atras.setVisibility(View.VISIBLE);
                        }
                    }
                    if (cont.getCont() - 1 == 0) {
                        atras.setVisibility(View.INVISIBLE);
                    }
                    if (cont.getCont() - 1 == pregunta.length) {
                        alante.setVisibility(View.INVISIBLE);
                    }
                    memoria = false;
                }
            }
        });
        d = (RelativeLayout) findViewById(R.id.d);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String opt = "d";
                verificarRes(opt);
                pos[colocar] = 4;

                calcularestado();
                if (memoria) {
                    if (cont.getCont() < pregunta.length && cont.getCont() >= 0) {
                        alante.setVisibility(View.VISIBLE);
                        if (cont.getCont() != 0) {
                            atras.setVisibility(View.VISIBLE);
                        }
                    }
                    if (cont.getCont() - 1 == 0) {
                        atras.setVisibility(View.INVISIBLE);
                    }
                    if (cont.getCont() - 1 == pregunta.length) {
                        alante.setVisibility(View.INVISIBLE);
                    }
                    memoria = false;
                }
            }
        });

    }

    private void calcularestado() {
        double total = (fallos + aciertos);
        double mitad = total / 2;
        double mitadsuperior = (total * 0.75);
        estado = (TextView) findViewById(R.id.estado);
        if (aciertos < mitad) {
            estado.setBackgroundResource(R.drawable.boton_estado_rojo);
            estado.setText(getString(R.string.Mal));
        } else {
            estado.setBackgroundResource(R.drawable.boton_estado_naranja);
            estado.setText(getString(R.string.Bien));
            if (aciertos >= mitadsuperior) {
                estado.setBackgroundResource(R.drawable.boton_estado_verde);
                estado.setText(getString(R.string.Genial));
            }
        }

    }

    private void recolocar() {
        Msolucion = (LinearLayout) findViewById(R.id.solucion);
        RelativeLayout subcontenedor = (RelativeLayout) findViewById(R.id.subcontenedor);
        ScrollView contenedor = (ScrollView) findViewById(R.id.contenedor);
        ImageView prohibido = (ImageView) findViewById(R.id.prohibido);
        if (pos[colocar] != 0) {
            switch (pos[colocar]) {
                case 1:
                    if (respuestaA.getText().equals(solucion.getText())) {
                        a.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                    } else {
                        a.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                    }
                    break;
                case 2:
                    if (respuestaB.getText().equals(solucion.getText())) {
                        b.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                    } else {
                        b.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                    }
                    break;
                case 3:
                    if (respuestaC.getText().equals(solucion.getText())) {
                        c.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                    } else {
                        c.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                    }
                    break;
                case 4:
                    if (respuestaD.getText().equals(solucion.getText())) {
                        d.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                    } else {
                        d.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                    }
            }
            a.setEnabled(false);
            b.setEnabled(false);
            c.setEnabled(false);
            d.setEnabled(false);
            Msolucion.setVisibility(View.VISIBLE);
            contenedor.setBackgroundColor(Color.parseColor("#E8F0F1"));
            prohibido.setVisibility(View.VISIBLE);
            prohibido.setImageResource(getResources().getIdentifier("drawable/" + "prohibido", null, getPackageName()));
        } else {
            a.setEnabled(true);
            b.setEnabled(true);
            c.setEnabled(true);
            d.setEnabled(true);
            Msolucion.setVisibility(View.GONE);
            contenedor.setBackgroundColor(Color.parseColor("#ffffff"));
            prohibido.setVisibility(View.GONE);
        }
    }

    private void verificarRes(String opt) {
        Msolucion.setVisibility(View.VISIBLE);
        switch (opt) {
            case "a":

                if (respuestaA.getText().equals(solucion.getText())) {
                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_true);

                    b.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    c.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    d.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    aciertos++;
                } else {
                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                    b.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    c.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    d.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    fallos++;
                }
                break;

            case "b":

                if (respuestaB.getText().equals(solucion.getText())) {
                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                    a.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    c.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    d.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    aciertos++;
                } else {
                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                    a.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    c.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    d.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    fallos++;
                }
                break;

            case "c":

                if (respuestaC.getText().equals(solucion.getText())) {
                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                    a.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    b.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    d.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    aciertos++;
                } else {
                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                    a.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    b.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    d.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    fallos++;
                }
                break;

            case "d":

                if (respuestaD.getText().equals(solucion.getText())) {
                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                    a.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    b.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    c.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    aciertos++;
                } else {
                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                    a.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    b.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    c.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    fallos++;
                }

        }
        a.setEnabled(false);
        b.setEnabled(false);
        c.setEnabled(false);
        d.setEnabled(false);

    }

    //////////////////////////////////////////////////
    ///////////inicio de carga y boton avanzar////////
    //////////////////////////////////////////////////
    private void avanza() {
        ocultaralante();
        if (cont.getCont() < pregunta.length) {
            
			if (arreglo == 2) {
            	cont.setCont(cont.getCont() + 1);

                limpiarelementos();
				
				 if(cont.getCont() != 0) {
                    viewflipper.setInAnimation(animrightalante);
                    viewflipper.showPrevious();
                }
				cont.setCont(cont.getCont() + 1);
				ocultaralante();
                //recolocar();
                
            }else{
				limpiarelementos();
				if(cont.getCont() != 0) {
                    viewflipper.setInAnimation(animrightalante);
                    viewflipper.showPrevious();
                }
				cont.setCont(cont.getCont() + 1);
                ocultaralante();
			}
            //ocultaralante();            
			arreglo = 1;
            if (num[cont.getCont()-1] >= inmemo && num[cont.getCont()-1] < outmemo && pos[cont.getCont()-1] == 0) {
                // PONER AQUI LA FUNCION DE MEMORIA
                RelativeLayout amemo = (RelativeLayout) findViewById(R.id.a);
                RelativeLayout bmemo = (RelativeLayout) findViewById(R.id.b);
                RelativeLayout cmemo = (RelativeLayout) findViewById(R.id.c);
                RelativeLayout dmemo = (RelativeLayout) findViewById(R.id.d);
                TextView pregunta = (TextView) findViewById(R.id.pregunta);
                ImageView imgpre = (ImageView) findViewById(R.id.imgpre);
                Button alante = (Button) findViewById(R.id.alante);
                Button atras = (Button) findViewById(R.id.atras);
                amemo.setVisibility(View.GONE);
                bmemo.setVisibility(View.GONE);
                cmemo.setVisibility(View.GONE);
                dmemo.setVisibility(View.GONE);
                imgpre.setVisibility(View.VISIBLE);
                pregunta.setVisibility(View.GONE);
                alante.setVisibility(View.INVISIBLE);
                atras.setVisibility(View.INVISIBLE);
                esperarYCerrar(tempomemoria);
                memoria = true;
            }
            viewflipper.setInAnimation(animrightalante);
            viewflipper.showPrevious();
        } else {
            Toast.makeText(getApplicationContext(), "Fin", Toast.LENGTH_SHORT).show();
        }

        a.setEnabled(true);
        b.setEnabled(true);
        c.setEnabled(true);
        d.setEnabled(true);
    }

    //////////////////////////////////////////////////
    //ocultar elementos al pulsar boton alante o atras////////
    //////////////////////////////////////////////////
    private void ocultaralante() {
        Button alante = (Button) findViewById(R.id.alante);
        Button atras = (Button) findViewById(R.id.atras);
        if (cont.getCont() == 1) {
            atras.setVisibility(View.INVISIBLE);
            alante.setVisibility(View.VISIBLE);
        } else {
            atras.setVisibility(View.VISIBLE);
            if (cont.getCont() == pregunta.length) {
                alante.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void ocultaratras() {
        Button alante = (Button) findViewById(R.id.alante);
        Button atras = (Button) findViewById(R.id.atras);
        if (cont.getCont() == pregunta.length) {
            alante.setVisibility(View.INVISIBLE);
            atras.setVisibility(View.VISIBLE);
        } else {
            alante.setVisibility(View.VISIBLE);
            if (cont.getCont() == 0) {
                atras.setVisibility(View.INVISIBLE);
            }
        }

    }

    /////////////////////////////////////////////////////////////////
    ///////////////limpia elementos//////////////////////////////////
    /////////////////////////////////////////////////////////////////
    private void limpiarelementos() {
        final TextView preguntas = (TextView) findViewById(R.id.pregunta);
        respuestaA = (TextView) findViewById(R.id.resA);
        respuestaB = (TextView) findViewById(R.id.resB);
        respuestaC = (TextView) findViewById(R.id.resC);
        respuestaD = (TextView) findViewById(R.id.resD);
        solucion = (TextView) findViewById(R.id.sol);
        final TextView explicacion = (TextView) findViewById(R.id.expl);
        final TextView cuenta = (TextView) findViewById(R.id.conta);
        final ImageView imgpregunta = (ImageView) findViewById(R.id.imgpre);
        final ImageView imgeA = (ImageView) findViewById(R.id.imgA);
        final ImageView imgeB = (ImageView) findViewById(R.id.imgB);
        final ImageView imgeC = (ImageView) findViewById(R.id.imgC);
        final ImageView imgeD = (ImageView) findViewById(R.id.imgD);
        final ImageView imgesol = (ImageView) findViewById(R.id.imgSol);
        final ImageView imgeExpl = (ImageView) findViewById(R.id.imgExp);

        a = (RelativeLayout) findViewById(R.id.a);
        b = (RelativeLayout) findViewById(R.id.b);
        c = (RelativeLayout) findViewById(R.id.c);
        d = (RelativeLayout) findViewById(R.id.d);

        a.setBackgroundResource(R.drawable.boton_opt_preguntas);
        b.setBackgroundResource(R.drawable.boton_opt_preguntas);
        c.setBackgroundResource(R.drawable.boton_opt_preguntas);
        d.setBackgroundResource(R.drawable.boton_opt_preguntas);


        cuenta.setText(cont.getCont() + 1 + "");


        if (cont.getCont() == -1) {
            cont.setCont(0);
        }
        if (pre[cont.getCont()].getImgPregunta().equals("")) {
            imgpregunta.setVisibility(View.GONE);
            imgpregunta.setImageResource(0);
        } else {
            imgpregunta.setVisibility(View.VISIBLE);
            imgpregunta.setImageResource(getResources().getIdentifier("drawable/" + pre[cont.getCont()].getImgPregunta(), null, getPackageName()));
        }


        if (pre[cont.getCont()].getImgA().equals("")) {
            imgeA.setVisibility(View.GONE);
            imgeA.setImageResource(0);
        } else {
            imgeA.setVisibility(View.VISIBLE);
            imgeA.setImageResource(getResources().getIdentifier("drawable/" + pre[cont.getCont()].getImgA(), null, getPackageName()));
        }

        if (pre[cont.getCont()].getImgB().equals("")) {
            imgeB.setVisibility(View.GONE);
            imgeB.setImageResource(0);
        } else {
            imgeB.setVisibility(View.VISIBLE);
            imgeB.setImageResource(getResources().getIdentifier("drawable/" + pre[cont.getCont()].getImgB(), null, getPackageName()));
        }

        if (pre[cont.getCont()].getImgC().equals("")) {
            imgeC.setVisibility(View.GONE);
            imgeC.setImageResource(0);
        } else {
            imgeC.setVisibility(View.VISIBLE);
            imgeC.setImageResource(getResources().getIdentifier("drawable/" + pre[cont.getCont()].getImgC(), null, getPackageName()));
        }

        if (pre[cont.getCont()].getImgD().equals("")) {
            imgeD.setVisibility(View.GONE);
            imgeD.setImageResource(0);
        } else {
            imgeD.setVisibility(View.VISIBLE);
            imgeD.setImageResource(getResources().getIdentifier("drawable/" + pre[cont.getCont()].getImgD(), null, getPackageName()));
        }

        if (pre[cont.getCont()].getImgSol().equals("")) {
            imgesol.setVisibility(View.GONE);
            imgesol.setImageResource(0);
        } else {
            imgesol.setVisibility(View.VISIBLE);
            imgesol.setImageResource(getResources().getIdentifier("drawable/" + pre[cont.getCont()].getImgSol(), null, getPackageName()));
        }

        if (pre[cont.getCont()].getImgExpli().equals("")) {
            imgeExpl.setVisibility(View.GONE);
            imgeExpl.setImageResource(0);
        } else {
            imgeExpl.setVisibility(View.VISIBLE);
            imgeExpl.setImageResource(getResources().getIdentifier("drawable/" + pre[cont.getCont()].getImgExpli(), null, getPackageName()));
        }

        if (pre[cont.getCont()].getExpliSol().equals("")) {
            explicacion.setVisibility(View.GONE);
        } else {
            explicacion.setVisibility(View.VISIBLE);
            explicacion.setText(pre[cont.getCont()].getExpliSol());
        }

        if (pre[cont.getCont()].getPregunta().equals("")) {
            preguntas.setVisibility(View.GONE);
        } else {
            preguntas.setVisibility(View.VISIBLE);
            preguntas.setText(pre[cont.getCont()].getPregunta());
        }

        if (pre[cont.getCont()].getRespuestaA().equals("")) {
            respuestaA.setVisibility(View.GONE);
			a.setVisibility(View.GONE);
        } else {
			a.setVisibility(View.VISIBLE);
            respuestaA.setVisibility(View.VISIBLE);
            respuestaA.setText(pre[cont.getCont()].getRespuestaA());
        }

        if (pre[cont.getCont()].getRespuestaB().equals("")) {
            respuestaB.setVisibility(View.GONE);
			b.setVisibility(View.GONE);
        } else {
			b.setVisibility(View.VISIBLE);
            respuestaB.setVisibility(View.VISIBLE);
            respuestaB.setText(pre[cont.getCont()].getRespuestaB());
        }

        if (pre[cont.getCont()].getRespuestaC().equals("")) {
            respuestaC.setVisibility(View.GONE);
            c.setVisibility(View.GONE);
        } else {
            respuestaC.setVisibility(View.VISIBLE);
            respuestaC.setText(pre[cont.getCont()].getRespuestaC());
            c.setVisibility(View.VISIBLE);
        }

        if (pre[cont.getCont()].getRespuestaD().equals("")) {
            respuestaD.setVisibility(View.GONE);
            d.setVisibility(View.GONE);
        } else {
            respuestaD.setVisibility(View.VISIBLE);
            respuestaD.setText(pre[cont.getCont()].getRespuestaD());
            d.setVisibility(View.VISIBLE);
        }

        if (pre[cont.getCont()].getSolu().equals("")) {
            solucion.setVisibility(View.GONE);
        } else {
            solucion.setVisibility(View.VISIBLE);
            solucion.setText(pre[cont.getCont()].getSolu());
        }


    }

    public void esperarYCerrar(int milisegundos) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                TextView pregunta = (TextView) findViewById(R.id.pregunta);
                ImageView imgpre = (ImageView) findViewById(R.id.imgpre);
                if (pre[cont.getCont()-1].getRespuestaA().equals("")) {
                    a.setVisibility(View.GONE);
                } else {
                    a.setVisibility(View.VISIBLE);
                }

                if (pre[cont.getCont()-1].getRespuestaB().equals("")) {
                    b.setVisibility(View.GONE);
                } else {
                    b.setVisibility(View.VISIBLE);
                }

                if (pre[cont.getCont()-1].getRespuestaC().equals("")) {
                    c.setVisibility(View.GONE);
                } else {
                    c.setVisibility(View.VISIBLE);
                }

                if (pre[cont.getCont()-1].getRespuestaD().equals("")) {
                    d.setVisibility(View.GONE);
                } else {
                    d.setVisibility(View.VISIBLE);
                }
                imgpre.setVisibility(View.GONE);
                pregunta.setVisibility(View.VISIBLE);
                viewflipper.setInAnimation(animrightalante);
                viewflipper.showPrevious();

            }
        }, milisegundos);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            new AlertDialog.Builder(this)
                    .setIcon(getResources().getDrawable(R.drawable.isalir))
                    .setTitle(getString(R.string.salir))
                    .setCancelable(false)
                    .setMessage(getString(R.string.saliractivity))
                    .setNegativeButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setPositiveButton(getString(R.string.no), new DialogInterface.OnClickListener() {
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