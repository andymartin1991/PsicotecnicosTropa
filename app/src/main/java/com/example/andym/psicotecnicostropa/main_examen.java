package com.example.andym.psicotecnicostropa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.andym.psicotecnicostropa.dto.Preguntas;


/**
 * Created by andym on 07/04/2017.
 */

public class main_examen extends Activity {
    String[] imgPre = null;
    String[] imgA = null;
    String[] imgB = null;
    String[] imgC = null;
    String[] imgD = null;
    String[] imgSol = null;
    String[] imgExpli = null;
    String[] pregunta = null;
    String[] resA = null;
    String[] resB = null;
    String[] resC = null;
    String[] resD = null;
    String[] sol = null;
    String[] expliSol = null;
    int[] respulsada;

    int[] pos;
//
    int nverb[];
    int nnume[];
    int nespa[];
    int nmeca[];
    int nper[];
    int nmemo[];
    int nabst[];

    RelativeLayout a;
    RelativeLayout b;
    RelativeLayout c;
    RelativeLayout d;
    TextView imppregunta, respuestaA, respuestaB, respuestaC, respuestaD, solucion, explicacion, contador, cuentabloque, cuentatras, estado;
    ImageView imgenPre, imgenA, imgenB, imgenC, imgenD, imgenSol, imgenExp;
    LinearLayout Msolucion;
    ScrollView contenedor;
    int bloque = 1;
    int posi = 0;
    int tempo = 1;//300
    long cuentatiempo = tempo * 1000;
    long guardatiempo = 0;
    CountDownTimer th;
    boolean acabar = false, arregloacabar = false;
    Button siguiente;

    //hola
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_preguntas);

        ImageView prohibido = (ImageView) findViewById(R.id.prohibido);
        prohibido.setVisibility(View.GONE);

        contenedor = (ScrollView) findViewById(R.id.contenedor);

        estado = (TextView) findViewById(R.id.estado);
        estado.setVisibility(View.GONE);
        Msolucion = (LinearLayout) findViewById(R.id.solucion);
        Msolucion.setVisibility(View.GONE);
        pregunta = getResources().getStringArray(R.array.preverbal);
        resA = getResources().getStringArray(R.array.resAverbal);
        resB = getResources().getStringArray(R.array.resBverbal);
        resC = getResources().getStringArray(R.array.resCverbal);
        resD = getResources().getStringArray(R.array.resDverbal);
        sol = getResources().getStringArray(R.array.solverbal);
        expliSol = getResources().getStringArray(R.array.expliSolverbal);
        imgPre = getResources().getStringArray(R.array.imgPreverbal);
        imgA = getResources().getStringArray(R.array.imgAverbal);
        imgB = getResources().getStringArray(R.array.imgBverbal);
        imgC = getResources().getStringArray(R.array.imgCverbal);
        imgD = getResources().getStringArray(R.array.imgDverbal);
        imgSol = getResources().getStringArray(R.array.imgSolverbal);
        imgExpli = getResources().getStringArray(R.array.imgExpliverbal);
        pos = new int[pregunta.length];
        main_resultado_exam.bloqueverbal = new Preguntas[pregunta.length];
        respulsada = new int[pregunta.length];
        for (int i = 0; i < pregunta.length; i++) {
            respulsada[i] = 0;
        }
        for (int i = 0; i < pregunta.length; i++) {
            main_resultado_exam.bloqueverbal[i] = new Preguntas(
                    pregunta[i], resA[i], resB[i], resC[i], resD[i], sol[i], expliSol[i], imgPre[i],
                    imgA[i], imgB[i], imgC[i], imgD[i], imgSol[i], imgExpli[i], respulsada[i]);
        }
        int h = 0, cantidad = 15, rango = pregunta.length;
        nverb = new int[cantidad];

        nverb[h] = (int) (Math.random() * rango);
        for (h = 1; h < cantidad; h++) {
            nverb[h] = (int) (Math.random() * rango);
            for (int j = 0; j < h; j++) {
                if (nverb[h] == nverb[j]) {
                    h--;
                }
            }
        }

        pregunta = getResources().getStringArray(R.array.prenumerico);
        resA = getResources().getStringArray(R.array.resAnumerico);
        resB = getResources().getStringArray(R.array.resBnumerico);
        resC = getResources().getStringArray(R.array.resCnumerico);
        resD = getResources().getStringArray(R.array.resDnumerico);
        sol = getResources().getStringArray(R.array.solnumerico);
        expliSol = getResources().getStringArray(R.array.expliSolnumerico);
        imgPre = getResources().getStringArray(R.array.imgPrenumerico);
        imgA = getResources().getStringArray(R.array.imgAnumerico);
        imgB = getResources().getStringArray(R.array.imgBnumerico);
        imgC = getResources().getStringArray(R.array.imgCnumerico);
        imgD = getResources().getStringArray(R.array.imgDnumerico);
        imgSol = getResources().getStringArray(R.array.imgSolnumerico);
        imgExpli = getResources().getStringArray(R.array.imgExplinumerico);
        pos = new int[pregunta.length];
        main_resultado_exam.bloquenumerico = new Preguntas[pregunta.length];
        respulsada = new int[pregunta.length];
        for (int i = 0; i < pregunta.length; i++) {
            respulsada[i] = 0;
        }
        for (int i = 0; i < pregunta.length; i++) {
            main_resultado_exam.bloquenumerico[i] = new Preguntas(
                    pregunta[i], resA[i], resB[i], resC[i], resD[i], sol[i], expliSol[i], imgPre[i],
                    imgA[i], imgB[i], imgC[i], imgD[i], imgSol[i], imgExpli[i], respulsada[i]);
        }
        h = 0;
        cantidad = 15;
        rango = pregunta.length;
        nnume = new int[cantidad];

        nnume[h] = (int) (Math.random() * rango);
        for (h = 1; h < cantidad; h++) {
            nnume[h] = (int) (Math.random() * rango);
            for (int j = 0; j < h; j++) {
                if (nnume[h] == nnume[j]) {
                    h--;
                }
            }
        }

        pregunta = getResources().getStringArray(R.array.preespacial);
        resA = getResources().getStringArray(R.array.resAespacial);
        resB = getResources().getStringArray(R.array.resBespacial);
        resC = getResources().getStringArray(R.array.resCespacial);
        resD = getResources().getStringArray(R.array.resDespacial);
        sol = getResources().getStringArray(R.array.solespacial);
        expliSol = getResources().getStringArray(R.array.expliSolespacial);
        imgPre = getResources().getStringArray(R.array.imgPreespacial);
        imgA = getResources().getStringArray(R.array.imgAespacial);
        imgB = getResources().getStringArray(R.array.imgBespacial);
        imgC = getResources().getStringArray(R.array.imgCespacial);
        imgD = getResources().getStringArray(R.array.imgDespacial);
        imgSol = getResources().getStringArray(R.array.imgSolespacial);
        imgExpli = getResources().getStringArray(R.array.imgExpliespacial);
        pos = new int[pregunta.length];
        main_resultado_exam.bloqueespacial = new Preguntas[pregunta.length];
        respulsada = new int[pregunta.length];
        for (int i = 0; i < pregunta.length; i++) {
            respulsada[i] = 0;
        }
        for (int i = 0; i < pregunta.length; i++) {
            main_resultado_exam.bloqueespacial[i] = new Preguntas(
                    pregunta[i], resA[i], resB[i], resC[i], resD[i], sol[i], expliSol[i], imgPre[i],
                    imgA[i], imgB[i], imgC[i], imgD[i], imgSol[i], imgExpli[i], respulsada[i]);
        }
        h = 0;
        cantidad = 15;
        rango = pregunta.length;
        nespa = new int[cantidad];

        nespa[h] = (int) (Math.random() * rango);
        for (h = 1; h < cantidad; h++) {
            nespa[h] = (int) (Math.random() * rango);
            for (int j = 0; j < h; j++) {
                if (nespa[h] == nespa[j]) {
                    h--;
                }
            }
        }

        pregunta = getResources().getStringArray(R.array.premecanico);
        resA = getResources().getStringArray(R.array.resAmecanico);
        resB = getResources().getStringArray(R.array.resBmecanico);
        resC = getResources().getStringArray(R.array.resCmecanico);
        resD = getResources().getStringArray(R.array.resDmecanico);
        sol = getResources().getStringArray(R.array.solmecanico);
        expliSol = getResources().getStringArray(R.array.expliSolmecanico);
        imgPre = getResources().getStringArray(R.array.imgPremecanico);
        imgA = getResources().getStringArray(R.array.imgAmecanico);
        imgB = getResources().getStringArray(R.array.imgBmecanico);
        imgC = getResources().getStringArray(R.array.imgCmecanico);
        imgD = getResources().getStringArray(R.array.imgDmecanico);
        imgSol = getResources().getStringArray(R.array.imgSolmecanico);
        imgExpli = getResources().getStringArray(R.array.imgExplimecanico);
        pos = new int[pregunta.length];
        main_resultado_exam.bloquemecanico = new Preguntas[pregunta.length];
        respulsada = new int[pregunta.length];
        for (int i = 0; i < pregunta.length; i++) {
            respulsada[i] = 0;
        }
        for (int i = 0; i < pregunta.length; i++) {
            main_resultado_exam.bloquemecanico[i] = new Preguntas(
                    pregunta[i], resA[i], resB[i], resC[i], resD[i], sol[i], expliSol[i], imgPre[i],
                    imgA[i], imgB[i], imgC[i], imgD[i], imgSol[i], imgExpli[i], respulsada[i]);
        }
        h = 0;
        cantidad = 15;
        rango = pregunta.length;
        nmeca = new int[cantidad];

        nmeca[h] = (int) (Math.random() * rango);
        for (h = 1; h < cantidad; h++) {
            nmeca[h] = (int) (Math.random() * rango);
            for (int j = 0; j < h; j++) {
                if (nmeca[h] == nmeca[j]) {
                    h--;
                }
            }
        }

        pregunta = getResources().getStringArray(R.array.preperceptiva);
        resA = getResources().getStringArray(R.array.resAperceptiva);
        resB = getResources().getStringArray(R.array.resBperceptiva);
        resC = getResources().getStringArray(R.array.resCperceptiva);
        resD = getResources().getStringArray(R.array.resDperceptiva);
        sol = getResources().getStringArray(R.array.solperceptiva);
        expliSol = getResources().getStringArray(R.array.expliSolperceptiva);
        imgPre = getResources().getStringArray(R.array.imgPreperceptiva);
        imgA = getResources().getStringArray(R.array.imgAperceptiva);
        imgB = getResources().getStringArray(R.array.imgBperceptiva);
        imgC = getResources().getStringArray(R.array.imgCperceptiva);
        imgD = getResources().getStringArray(R.array.imgDperceptiva);
        imgSol = getResources().getStringArray(R.array.imgSolperceptiva);
        imgExpli = getResources().getStringArray(R.array.imgExpliperceptiva);
        pos = new int[pregunta.length];
        main_resultado_exam.bloqueperceptiva = new Preguntas[pregunta.length];
        respulsada = new int[pregunta.length];
        for (int i = 0; i < pregunta.length; i++) {
            respulsada[i] = 0;
        }
        for (int i = 0; i < pregunta.length; i++) {
            main_resultado_exam.bloqueperceptiva[i] = new Preguntas(
                    pregunta[i], resA[i], resB[i], resC[i], resD[i], sol[i], expliSol[i], imgPre[i],
                    imgA[i], imgB[i], imgC[i], imgD[i], imgSol[i], imgExpli[i], respulsada[i]);
        }
        h = 0;
        cantidad = 15;
        rango = pregunta.length;
        nper = new int[cantidad];

        nper[h] = (int) (Math.random() * rango);
        for (h = 1; h < cantidad; h++) {
            nper[h] = (int) (Math.random() * rango);
            for (int j = 0; j < h; j++) {
                if (nper[h] == nper[j]) {
                    h--;
                }
            }
        }

        pregunta = getResources().getStringArray(R.array.prememoria);
        resA = getResources().getStringArray(R.array.resAmemoria);
        resB = getResources().getStringArray(R.array.resBmemoria);
        resC = getResources().getStringArray(R.array.resCmemoria);
        resD = getResources().getStringArray(R.array.resDmemoria);
        sol = getResources().getStringArray(R.array.solmemoria);
        expliSol = getResources().getStringArray(R.array.expliSolmemoria);
        imgPre = getResources().getStringArray(R.array.imgPrememoria);
        imgA = getResources().getStringArray(R.array.imgAmemoria);
        imgB = getResources().getStringArray(R.array.imgBmemoria);
        imgC = getResources().getStringArray(R.array.imgCmemoria);
        imgD = getResources().getStringArray(R.array.imgDmemoria);
        imgSol = getResources().getStringArray(R.array.imgSolmemoria);
        imgExpli = getResources().getStringArray(R.array.imgExplimemoria);
        pos = new int[pregunta.length];
        main_resultado_exam.bloquememoria = new Preguntas[pregunta.length];
        respulsada = new int[pregunta.length];
        for (int i = 0; i < pregunta.length; i++) {
            respulsada[i] = 0;
        }
        for (int i = 0; i < pregunta.length; i++) {
            main_resultado_exam.bloquememoria[i] = new Preguntas(
                    pregunta[i], resA[i], resB[i], resC[i], resD[i], sol[i], expliSol[i], imgPre[i],
                    imgA[i], imgB[i], imgC[i], imgD[i], imgSol[i], imgExpli[i], respulsada[i]);
        }
        h = 0;
        cantidad = 15;
        rango = pregunta.length;
        nmemo = new int[cantidad];

        nmemo[h] = (int) (Math.random() * rango);
        for (h = 1; h < cantidad; h++) {
            nmemo[h] = (int) (Math.random() * rango);
            for (int j = 0; j < h; j++) {
                if (nmemo[h] == nmemo[j]) {
                    h--;
                }
            }
        }

        pregunta = getResources().getStringArray(R.array.preabstrapto);
        resA = getResources().getStringArray(R.array.resAabstrapto);
        resB = getResources().getStringArray(R.array.resBabstrapto);
        resC = getResources().getStringArray(R.array.resCabstrapto);
        resD = getResources().getStringArray(R.array.resDabstrapto);
        sol = getResources().getStringArray(R.array.solabstrapto);
        expliSol = getResources().getStringArray(R.array.expliSolabstrapto);
        imgPre = getResources().getStringArray(R.array.imgPreabstrapto);
        imgA = getResources().getStringArray(R.array.imgAabstrapto);
        imgB = getResources().getStringArray(R.array.imgBabstrapto);
        imgC = getResources().getStringArray(R.array.imgCabstrapto);
        imgD = getResources().getStringArray(R.array.imgDabstrapto);
        imgSol = getResources().getStringArray(R.array.imgSolabstrapto);
        imgExpli = getResources().getStringArray(R.array.imgExpliabstrapto);
        pos = new int[pregunta.length];
        main_resultado_exam.bloqueabstrapto = new Preguntas[pregunta.length];
        respulsada = new int[pregunta.length];
        for (int i = 0; i < pregunta.length; i++) {
            respulsada[i] = 0;
        }
        for (int i = 0; i < pregunta.length; i++) {
            main_resultado_exam.bloqueabstrapto[i] = new Preguntas(
                    pregunta[i], resA[i], resB[i], resC[i], resD[i], sol[i], expliSol[i], imgPre[i],
                    imgA[i], imgB[i], imgC[i], imgD[i], imgSol[i], imgExpli[i], respulsada[i]);
        }
        h = 0;
        cantidad = 15;
        rango = pregunta.length;
        nabst = new int[cantidad];

        nabst[h] = (int) (Math.random() * rango);
        for (h = 1; h < cantidad; h++) {
            nabst[h] = (int) (Math.random() * rango);
            for (int j = 0; j < h; j++) {
                if (nabst[h] == nabst[j]) {
                    h--;
                }
            }
        }

        cuentabloque = (TextView) findViewById(R.id.cuentabloque);
        cuentabloque.setVisibility(View.VISIBLE);
        cuentabloque.setText("1/7");

        cuentatras = (TextView) findViewById(R.id.cuentatras);
        cuentatras.setVisibility(View.VISIBLE);

        th = new CountDownTimer(cuentatiempo, 1000) {
            public void onTick(long millisUntilFinished) {
                cuentatras.setText(getString(R.string.tiemporesdta) + " " + millisUntilFinished / 1000 + " " + getString(R.string.segundos));
                guardatiempo = millisUntilFinished;
                memoria();
            }

            public void onFinish() {
                cuentatras.setText(getString(R.string.tiemporesdta) + " " + "0" + " " + getString(R.string.segundos));
                new AlertDialog.Builder(main_examen.this)
                        .setTitle(getString(R.string.atencion))
                        .setMessage(getString(R.string.tiempoterminado))
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @SuppressWarnings("deprecation")
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                posi = 0;
                                bloque++;
                                if (bloque == 8) {
                                    acabar();
                                    Intent resultado = new Intent(main_examen.this, main_resultado_exam.class);
                                    startActivity(resultado);
                                    overridePendingTransition(R.anim.transpain, R.anim.transpaout);
                                } else {
                                    imprimir(bloque, posi);
                                    contador.setText((posi + 1) + "/15");
                                    cuentabloque.setText(bloque + "/7");
                                    cuentatiempo = tempo * 1000;
                                    th.start();
                                }
                            }
                        }).create().show();

            }
        }.start();


        contador = (TextView) findViewById(R.id.conta);
        contador.setText("1/15");
        imprimir(bloque, posi);
        siguiente = (Button) findViewById(R.id.alante);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bloque == 7 && posi == 14) {
                    if (arregloacabar == false) {
                        cuentatiempo = guardatiempo;
                        th.cancel();
                        th = null;
                        siguiente.setEnabled(false);
                        new AlertDialog.Builder(main_examen.this)
                                .setTitle(getString(R.string.atencion))
                                .setMessage(getString(R.string.bloqueterminado))
                                .setCancelable(false)
                                .setNegativeButton(getString(R.string.revisar), new DialogInterface.OnClickListener() {
                                    @SuppressWarnings("deprecation")
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        siguiente.setEnabled(true);
                                        posi--;
                                        th = new CountDownTimer(cuentatiempo, 1000) {
                                            public void onTick(long millisUntilFinished) {
                                                cuentatras.setText(getString(R.string.tiemporesdta) + " " + millisUntilFinished / 1000 + " " + getString(R.string.segundos));
                                                guardatiempo = millisUntilFinished;
                                                memoria();
                                            }

                                            public void onFinish() {
                                                cuentatras.setText(getString(R.string.tiemporesdta) + " " + "0" + " " + getString(R.string.segundos));
                                                new AlertDialog.Builder(main_examen.this)
                                                        .setTitle(getString(R.string.atencion))
                                                        .setMessage(getString(R.string.tiempoterminado))
                                                        .setCancelable(false)
                                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                            @SuppressWarnings("deprecation")
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                posi = 0;
                                                                bloque++;
                                                                if (bloque == 8) {
                                                                    acabar();
                                                                    Intent resultado = new Intent(main_examen.this, main_resultado_exam.class);
                                                                    startActivity(resultado);
                                                                    overridePendingTransition(R.anim.transpain, R.anim.transpaout);

                                                                } else {
                                                                    imprimir(bloque, posi);
                                                                    contador.setText((posi + 1) + "/15");
                                                                    cuentabloque.setText(bloque + "/7");
                                                                    cuentatiempo = tempo * 1000;
                                                                    th.start();
                                                                }
                                                            }
                                                        }).create().show();

                                            }
                                        }.start();
                                    }
                                })
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @SuppressWarnings("deprecation")
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        acabar();
                                        Intent resultado = new Intent(main_examen.this, main_resultado_exam.class);
                                        startActivity(resultado);
                                        overridePendingTransition(R.anim.transpain, R.anim.transpaout);

                                    }
                                }).create().show();
                    } else {
                        acabar();
                        Intent resultado = new Intent(main_examen.this, main_resultado_exam.class);
                        startActivity(resultado);
                        overridePendingTransition(R.anim.transpain, R.anim.transpaout);
                    }
                } else if (acabar == false) {
                    posi++;
                    if (posi < 15) {
                        imprimir(bloque, posi);
                        contador.setText((posi + 1) + "/15");
                    } else {
                        cuentatiempo = guardatiempo;
                        th.cancel();
                        th = null;
                        siguiente.setEnabled(false);
                        new AlertDialog.Builder(main_examen.this)
                                .setTitle(getString(R.string.atencion))
                                .setMessage(getString(R.string.bloqueterminado))
                                .setCancelable(false)
                                .setNegativeButton(getString(R.string.revisar), new DialogInterface.OnClickListener() {
                                    @SuppressWarnings("deprecation")
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        posi--;
                                        th = new CountDownTimer(cuentatiempo, 1000) {
                                            public void onTick(long millisUntilFinished) {
                                                cuentatras.setText(getString(R.string.tiemporesdta) + " " + millisUntilFinished / 1000 + " " + getString(R.string.segundos));
                                                guardatiempo = millisUntilFinished;
                                                siguiente.setEnabled(true);
                                                memoria();
                                            }

                                            public void onFinish() {
                                                cuentatras.setText(getString(R.string.tiemporesdta) + " " + "0" + " " + getString(R.string.segundos));
                                                new AlertDialog.Builder(main_examen.this)
                                                        .setTitle(getString(R.string.atencion))
                                                        .setMessage(getString(R.string.tiempoterminado))
                                                        .setCancelable(false)
                                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                            @SuppressWarnings("deprecation")
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                posi = 0;
                                                                bloque++;
                                                                if (bloque == 8) {
                                                                    acabar();
                                                                    Intent resultado = new Intent(main_examen.this, main_resultado_exam.class);
                                                                    startActivity(resultado);
                                                                    overridePendingTransition(R.anim.transpain, R.anim.transpaout);

                                                                } else {
                                                                    imprimir(bloque, posi);
                                                                    contador.setText((posi + 1) + "/15");
                                                                    cuentabloque.setText(bloque + "/7");
                                                                    cuentatiempo = tempo * 1000;
                                                                    th.start();
                                                                }
                                                                siguiente.setEnabled(true);

                                                            }
                                                        }).create().show();

                                            }
                                        }.start();
                                    }
                                })
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @SuppressWarnings("deprecation")
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        posi = 0;
                                        bloque++;
                                        siguiente.setEnabled(true);
                                        if (bloque != 8) {
                                            imprimir(bloque, posi);
                                            cuentabloque.setText(bloque + "/7");
                                            contador.setText((posi + 1) + "/15");
                                            cuentatiempo = tempo * 1000;
                                            th = new CountDownTimer(cuentatiempo, 1000) {
                                                public void onTick(long millisUntilFinished) {
                                                    cuentatras.setText(getString(R.string.tiemporesdta) + " " + millisUntilFinished / 1000 + " " + getString(R.string.segundos));
                                                    guardatiempo = millisUntilFinished;
                                                    memoria();
                                                }

                                                public void onFinish() {
                                                    cuentatras.setText(getString(R.string.tiemporesdta) + " " + "0" + " " + getString(R.string.segundos));
                                                    new AlertDialog.Builder(main_examen.this)
                                                            .setTitle(getString(R.string.atencion))
                                                            .setMessage(getString(R.string.tiempoterminado))
                                                            .setCancelable(false)
                                                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                @SuppressWarnings("deprecation")
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    posi = 0;
                                                                    bloque++;
                                                                    if (bloque == 8) {
                                                                        acabar();
                                                                        Intent resultado = new Intent(main_examen.this, main_resultado_exam.class);
                                                                        startActivity(resultado);
                                                                        overridePendingTransition(R.anim.transpain, R.anim.transpaout);
                                                                    } else {
                                                                        imprimir(bloque, posi);
                                                                        contador.setText((posi + 1) + "/15");
                                                                        cuentabloque.setText(bloque + "/7");
                                                                        cuentatiempo = tempo * 1000;
                                                                        th.start();
                                                                    }
                                                                }
                                                            }).create().show();

                                                }
                                            }.start();
                                        }
                                    }
                                }).create().show();
                        memoria();
                    }
                } else {
                    //cuando acabo el examen
                    if (bloque <= 7 && posi <= 14) {
                        posi++;
                        if (posi == 15) {
                            bloque++;
                        }
                        if (posi > 14) {
                            posi = 0;
                        }
                        imprimir(bloque, posi);
                        cuentabloque.setText(bloque + "/7");
                        contador.setText((posi + 1) + "/15");

                    }

                }
                recolocar();
                memoria();
            }
        });

        Button atras = (Button) findViewById(R.id.atras);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (posi < 15 && posi >= 0) {
                    if (posi == 0 && acabar == true) {
                        if (bloque > 1) {
                            posi = 14;
                            bloque--;
                            imprimir(bloque, posi);
                            contador.setText((posi + 1) + "/15");
                            recolocar();
                            cuentabloque.setText(bloque + "/7");
                        }
                    } else if (posi > 0) {
                        posi--;
                        imprimir(bloque, posi);
                        contador.setText((posi + 1) + "/15");
                        recolocar();
                    }
                }
            }
        });

    }

    private void imprimir(final int bloque, final int posi) {
        imppregunta = (TextView) findViewById(R.id.pregunta);
        respuestaA = (TextView) findViewById(R.id.resA);
        respuestaB = (TextView) findViewById(R.id.resB);
        respuestaC = (TextView) findViewById(R.id.resC);
        respuestaD = (TextView) findViewById(R.id.resD);
        solucion = (TextView) findViewById(R.id.sol);
        explicacion = (TextView) findViewById(R.id.expl);
        imgenPre = (ImageView) findViewById(R.id.imgpre);
        imgenA = (ImageView) findViewById(R.id.imgA);
        imgenB = (ImageView) findViewById(R.id.imgB);
        imgenC = (ImageView) findViewById(R.id.imgC);
        imgenD = (ImageView) findViewById(R.id.imgD);
        imgenSol = (ImageView) findViewById(R.id.imgSol);
        imgenExp = (ImageView) findViewById(R.id.imgExp);
        c = (RelativeLayout) findViewById(R.id.c);
        d = (RelativeLayout) findViewById(R.id.d);
        TextView bloq = (TextView) findViewById(R.id.bloque);
        switch (bloque) {
            case 1:
                bloq.setText(getString(R.string.verbal));
                if (main_resultado_exam.bloqueverbal[nabst[posi]].getImgPregunta().equals("")) {
                    imgenPre.setVisibility(View.GONE);
                    imgenPre.setImageResource(0);
                } else {
                    imgenPre.setVisibility(View.VISIBLE);
                    imgenPre.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueverbal[nabst[posi]].getImgPregunta(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueverbal[nabst[posi]].getImgA().equals("")) {
                    imgenA.setVisibility(View.GONE);
                    imgenA.setImageResource(0);
                } else {
                    imgenA.setVisibility(View.VISIBLE);
                    imgenA.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueverbal[nabst[posi]].getImgA(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueverbal[nabst[posi]].getImgB().equals("")) {
                    imgenB.setVisibility(View.GONE);
                    imgenB.setImageResource(0);
                } else {
                    imgenB.setVisibility(View.VISIBLE);
                    imgenB.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueverbal[nabst[posi]].getImgB(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueverbal[nabst[posi]].getImgC().equals("")) {
                    imgenC.setVisibility(View.GONE);
                    imgenC.setImageResource(0);
                } else {
                    imgenC.setVisibility(View.VISIBLE);
                    imgenC.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueverbal[nabst[posi]].getImgC(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueverbal[nabst[posi]].getImgD().equals("")) {
                    imgenD.setVisibility(View.GONE);
                    imgenD.setImageResource(0);
                } else {
                    imgenD.setVisibility(View.VISIBLE);
                    imgenD.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueverbal[nabst[posi]].getImgD(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueverbal[nabst[posi]].getImgSol().equals("")) {
                    imgenSol.setVisibility(View.GONE);
                    imgenSol.setImageResource(0);
                } else {
                    imgenSol.setVisibility(View.VISIBLE);
                    imgenSol.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueverbal[nabst[posi]].getImgSol(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueverbal[nabst[posi]].getImgExpli().equals("")) {
                    imgenExp.setVisibility(View.GONE);
                    imgenExp.setImageResource(0);
                } else {
                    imgenExp.setVisibility(View.VISIBLE);
                    imgenExp.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueverbal[nabst[posi]].getImgExpli(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueverbal[nabst[posi]].getExpliSol().equals("")) {
                    explicacion.setVisibility(View.GONE);
                } else {
                    explicacion.setVisibility(View.VISIBLE);
                    explicacion.setText(main_resultado_exam.bloqueverbal[nabst[posi]].getExpliSol());
                }
                if (main_resultado_exam.bloqueverbal[nabst[posi]].getPregunta().equals("")) {
                    imppregunta.setVisibility(View.GONE);
                } else {
                    imppregunta.setVisibility(View.VISIBLE);
                    imppregunta.setText(main_resultado_exam.bloqueverbal[nabst[posi]].getPregunta());
                }
                if (main_resultado_exam.bloqueverbal[nabst[posi]].getRespuestaA().equals("")) {
                    respuestaA.setVisibility(View.GONE);
                } else {
                    respuestaA.setVisibility(View.VISIBLE);
                    respuestaA.setText(main_resultado_exam.bloqueverbal[nabst[posi]].getRespuestaA());
                }
                if (main_resultado_exam.bloqueverbal[nabst[posi]].getRespuestaB().equals("")) {
                    respuestaB.setVisibility(View.GONE);
                } else {
                    respuestaB.setVisibility(View.VISIBLE);
                    respuestaB.setText(main_resultado_exam.bloqueverbal[nabst[posi]].getRespuestaB());
                }
                if (main_resultado_exam.bloqueverbal[nabst[posi]].getRespuestaC().equals("")) {
                    respuestaC.setVisibility(View.GONE);
                    c.setVisibility(View.GONE);
                } else {
                    respuestaC.setVisibility(View.VISIBLE);
                    respuestaC.setText(main_resultado_exam.bloqueverbal[nabst[posi]].getRespuestaC());
                    c.setVisibility(View.VISIBLE);
                }
                if (main_resultado_exam.bloqueverbal[nabst[posi]].getRespuestaD().equals("")) {
                    respuestaD.setVisibility(View.GONE);
                    d.setVisibility(View.GONE);
                } else {
                    respuestaD.setVisibility(View.VISIBLE);
                    respuestaD.setText(main_resultado_exam.bloqueverbal[nabst[posi]].getRespuestaD());
                    d.setVisibility(View.VISIBLE);
                }
                if (main_resultado_exam.bloqueverbal[nabst[posi]].getSolu().equals("")) {
                    solucion.setVisibility(View.GONE);
                } else {
                    solucion.setVisibility(View.VISIBLE);
                    solucion.setText(main_resultado_exam.bloqueverbal[nabst[posi]].getSolu());
                }
                break;
            case 2:
                bloq.setText(getString(R.string.numerico));
                if (main_resultado_exam.bloquenumerico[nabst[posi]].getImgPregunta().equals("")) {
                    imgenPre.setVisibility(View.GONE);
                    imgenPre.setImageResource(0);
                } else {
                    imgenPre.setVisibility(View.VISIBLE);
                    imgenPre.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloquenumerico[nabst[posi]].getImgPregunta(), null, getPackageName()));
                }
                if (main_resultado_exam.bloquenumerico[nabst[posi]].getImgA().equals("")) {
                    imgenA.setVisibility(View.GONE);
                    imgenA.setImageResource(0);
                } else {
                    imgenA.setVisibility(View.VISIBLE);
                    imgenA.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloquenumerico[nabst[posi]].getImgA(), null, getPackageName()));
                }
                if (main_resultado_exam.bloquenumerico[nabst[posi]].getImgB().equals("")) {
                    imgenB.setVisibility(View.GONE);
                    imgenB.setImageResource(0);
                } else {
                    imgenB.setVisibility(View.VISIBLE);
                    imgenB.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloquenumerico[nabst[posi]].getImgB(), null, getPackageName()));
                }
                if (main_resultado_exam.bloquenumerico[nabst[posi]].getImgC().equals("")) {
                    imgenC.setVisibility(View.GONE);
                    imgenC.setImageResource(0);
                } else {
                    imgenC.setVisibility(View.VISIBLE);
                    imgenC.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloquenumerico[nabst[posi]].getImgC(), null, getPackageName()));
                }
                if (main_resultado_exam.bloquenumerico[nabst[posi]].getImgD().equals("")) {
                    imgenD.setVisibility(View.GONE);
                    imgenD.setImageResource(0);
                } else {
                    imgenD.setVisibility(View.VISIBLE);
                    imgenD.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloquenumerico[nabst[posi]].getImgD(), null, getPackageName()));
                }
                if (main_resultado_exam.bloquenumerico[nabst[posi]].getImgSol().equals("")) {
                    imgenSol.setVisibility(View.GONE);
                    imgenSol.setImageResource(0);
                } else {
                    imgenSol.setVisibility(View.VISIBLE);
                    imgenSol.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloquenumerico[nabst[posi]].getImgSol(), null, getPackageName()));
                }
                if (main_resultado_exam.bloquenumerico[nabst[posi]].getImgExpli().equals("")) {
                    imgenExp.setVisibility(View.GONE);
                    imgenExp.setImageResource(0);
                } else {
                    imgenExp.setVisibility(View.VISIBLE);
                    imgenExp.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloquenumerico[nabst[posi]].getImgExpli(), null, getPackageName()));
                }
                if (main_resultado_exam.bloquenumerico[nabst[posi]].getExpliSol().equals("")) {
                    explicacion.setVisibility(View.GONE);
                } else {
                    explicacion.setVisibility(View.VISIBLE);
                    explicacion.setText(main_resultado_exam.bloquenumerico[nabst[posi]].getExpliSol());
                }
                if (main_resultado_exam.bloquenumerico[nabst[posi]].getPregunta().equals("")) {
                    imppregunta.setVisibility(View.GONE);
                } else {
                    imppregunta.setVisibility(View.VISIBLE);
                    imppregunta.setText(main_resultado_exam.bloquenumerico[nabst[posi]].getPregunta());
                }
                if (main_resultado_exam.bloquenumerico[nabst[posi]].getRespuestaA().equals("")) {
                    respuestaA.setVisibility(View.GONE);
                } else {
                    respuestaA.setVisibility(View.VISIBLE);
                    respuestaA.setText(main_resultado_exam.bloquenumerico[nabst[posi]].getRespuestaA());
                }
                if (main_resultado_exam.bloquenumerico[nabst[posi]].getRespuestaB().equals("")) {
                    respuestaB.setVisibility(View.GONE);
                } else {
                    respuestaB.setVisibility(View.VISIBLE);
                    respuestaB.setText(main_resultado_exam.bloquenumerico[nabst[posi]].getRespuestaB());
                }
                if (main_resultado_exam.bloquenumerico[nabst[posi]].getRespuestaC().equals("")) {
                    respuestaC.setVisibility(View.GONE);
                    c.setVisibility(View.GONE);
                } else {
                    respuestaC.setVisibility(View.VISIBLE);
                    respuestaC.setText(main_resultado_exam.bloquenumerico[nabst[posi]].getRespuestaC());
                    c.setVisibility(View.VISIBLE);
                }
                if (main_resultado_exam.bloquenumerico[nabst[posi]].getRespuestaD().equals("")) {
                    respuestaD.setVisibility(View.GONE);
                    d.setVisibility(View.GONE);
                } else {
                    respuestaD.setVisibility(View.VISIBLE);
                    respuestaD.setText(main_resultado_exam.bloquenumerico[nabst[posi]].getRespuestaD());
                    d.setVisibility(View.VISIBLE);
                }
                if (main_resultado_exam.bloquenumerico[nabst[posi]].getSolu().equals("")) {
                    solucion.setVisibility(View.GONE);
                } else {
                    solucion.setVisibility(View.VISIBLE);
                    solucion.setText(main_resultado_exam.bloquenumerico[nabst[posi]].getSolu());
                }
                break;
            case 3:
                bloq.setText(getString(R.string.espacial));
                if (main_resultado_exam.bloqueespacial[nabst[posi]].getImgPregunta().equals("")) {
                    imgenPre.setVisibility(View.GONE);
                    imgenPre.setImageResource(0);
                } else {
                    imgenPre.setVisibility(View.VISIBLE);
                    imgenPre.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueespacial[nabst[posi]].getImgPregunta(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueespacial[nabst[posi]].getImgA().equals("")) {
                    imgenA.setVisibility(View.GONE);
                    imgenA.setImageResource(0);
                } else {
                    imgenA.setVisibility(View.VISIBLE);
                    imgenA.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueespacial[nabst[posi]].getImgA(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueespacial[nabst[posi]].getImgB().equals("")) {
                    imgenB.setVisibility(View.GONE);
                    imgenB.setImageResource(0);
                } else {
                    imgenB.setVisibility(View.VISIBLE);
                    imgenB.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueespacial[nabst[posi]].getImgB(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueespacial[nabst[posi]].getImgC().equals("")) {
                    imgenC.setVisibility(View.GONE);
                    imgenC.setImageResource(0);
                } else {
                    imgenC.setVisibility(View.VISIBLE);
                    imgenC.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueespacial[nabst[posi]].getImgC(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueespacial[nabst[posi]].getImgD().equals("")) {
                    imgenD.setVisibility(View.GONE);
                    imgenD.setImageResource(0);
                } else {
                    imgenD.setVisibility(View.VISIBLE);
                    imgenD.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueespacial[nabst[posi]].getImgD(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueespacial[nabst[posi]].getImgSol().equals("")) {
                    imgenSol.setVisibility(View.GONE);
                    imgenSol.setImageResource(0);
                } else {
                    imgenSol.setVisibility(View.VISIBLE);
                    imgenSol.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueespacial[nabst[posi]].getImgSol(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueespacial[nabst[posi]].getImgExpli().equals("")) {
                    imgenExp.setVisibility(View.GONE);
                    imgenExp.setImageResource(0);
                } else {
                    imgenExp.setVisibility(View.VISIBLE);
                    imgenExp.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueespacial[nabst[posi]].getImgExpli(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueespacial[nabst[posi]].getExpliSol().equals("")) {
                    explicacion.setVisibility(View.GONE);
                } else {
                    explicacion.setVisibility(View.VISIBLE);
                    explicacion.setText(main_resultado_exam.bloqueespacial[nabst[posi]].getExpliSol());
                }
                if (main_resultado_exam.bloqueespacial[nabst[posi]].getPregunta().equals("")) {
                    imppregunta.setVisibility(View.GONE);
                } else {
                    imppregunta.setVisibility(View.VISIBLE);
                    imppregunta.setText(main_resultado_exam.bloqueespacial[nabst[posi]].getPregunta());
                }
                if (main_resultado_exam.bloqueespacial[nabst[posi]].getRespuestaA().equals("")) {
                    respuestaA.setVisibility(View.GONE);
                } else {
                    respuestaA.setVisibility(View.VISIBLE);
                    respuestaA.setText(main_resultado_exam.bloqueespacial[nabst[posi]].getRespuestaA());
                }
                if (main_resultado_exam.bloqueespacial[nabst[posi]].getRespuestaB().equals("")) {
                    respuestaB.setVisibility(View.GONE);
                } else {
                    respuestaB.setVisibility(View.VISIBLE);
                    respuestaB.setText(main_resultado_exam.bloqueespacial[nabst[posi]].getRespuestaB());
                }
                if (main_resultado_exam.bloqueespacial[nabst[posi]].getRespuestaC().equals("")) {
                    respuestaC.setVisibility(View.GONE);
                    c.setVisibility(View.GONE);
                } else {
                    respuestaC.setVisibility(View.VISIBLE);
                    respuestaC.setText(main_resultado_exam.bloqueespacial[nabst[posi]].getRespuestaC());
                    c.setVisibility(View.VISIBLE);
                }
                if (main_resultado_exam.bloqueespacial[nabst[posi]].getRespuestaD().equals("")) {
                    respuestaD.setVisibility(View.GONE);
                    d.setVisibility(View.GONE);
                } else {
                    respuestaD.setVisibility(View.VISIBLE);
                    respuestaD.setText(main_resultado_exam.bloqueespacial[nabst[posi]].getRespuestaD());
                    d.setVisibility(View.VISIBLE);
                }
                if (main_resultado_exam.bloqueespacial[nabst[posi]].getSolu().equals("")) {
                    solucion.setVisibility(View.GONE);
                } else {
                    solucion.setVisibility(View.VISIBLE);
                    solucion.setText(main_resultado_exam.bloqueespacial[nabst[posi]].getSolu());
                }
                break;
            case 4:
                bloq.setText(getString(R.string.mecanico));
                if (main_resultado_exam.bloquemecanico[nabst[posi]].getImgPregunta().equals("")) {
                    imgenPre.setVisibility(View.GONE);
                    imgenPre.setImageResource(0);
                } else {
                    imgenPre.setVisibility(View.VISIBLE);
                    imgenPre.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloquemecanico[nabst[posi]].getImgPregunta(), null, getPackageName()));
                }
                if (main_resultado_exam.bloquemecanico[nabst[posi]].getImgA().equals("")) {
                    imgenA.setVisibility(View.GONE);
                    imgenA.setImageResource(0);
                } else {
                    imgenA.setVisibility(View.VISIBLE);
                    imgenA.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloquemecanico[nabst[posi]].getImgA(), null, getPackageName()));
                }
                if (main_resultado_exam.bloquemecanico[nabst[posi]].getImgB().equals("")) {
                    imgenB.setVisibility(View.GONE);
                    imgenB.setImageResource(0);
                } else {
                    imgenB.setVisibility(View.VISIBLE);
                    imgenB.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloquemecanico[nabst[posi]].getImgB(), null, getPackageName()));
                }
                if (main_resultado_exam.bloquemecanico[nabst[posi]].getImgC().equals("")) {
                    imgenC.setVisibility(View.GONE);
                    imgenC.setImageResource(0);
                } else {
                    imgenC.setVisibility(View.VISIBLE);
                    imgenC.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloquemecanico[nabst[posi]].getImgC(), null, getPackageName()));
                }
                if (main_resultado_exam.bloquemecanico[nabst[posi]].getImgD().equals("")) {
                    imgenD.setVisibility(View.GONE);
                    imgenD.setImageResource(0);
                } else {
                    imgenD.setVisibility(View.VISIBLE);
                    imgenD.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloquemecanico[nabst[posi]].getImgD(), null, getPackageName()));
                }
                if (main_resultado_exam.bloquemecanico[nabst[posi]].getImgSol().equals("")) {
                    imgenSol.setVisibility(View.GONE);
                    imgenSol.setImageResource(0);
                } else {
                    imgenSol.setVisibility(View.VISIBLE);
                    imgenSol.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloquemecanico[nabst[posi]].getImgSol(), null, getPackageName()));
                }
                if (main_resultado_exam.bloquemecanico[nabst[posi]].getImgExpli().equals("")) {
                    imgenExp.setVisibility(View.GONE);
                    imgenExp.setImageResource(0);
                } else {
                    imgenExp.setVisibility(View.VISIBLE);
                    imgenExp.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloquemecanico[nabst[posi]].getImgExpli(), null, getPackageName()));
                }
                if (main_resultado_exam.bloquemecanico[nabst[posi]].getExpliSol().equals("")) {
                    explicacion.setVisibility(View.GONE);
                } else {
                    explicacion.setVisibility(View.VISIBLE);
                    explicacion.setText(main_resultado_exam.bloquemecanico[nabst[posi]].getExpliSol());
                }
                if (main_resultado_exam.bloquemecanico[nabst[posi]].getPregunta().equals("")) {
                    imppregunta.setVisibility(View.GONE);
                } else {
                    imppregunta.setVisibility(View.VISIBLE);
                    imppregunta.setText(main_resultado_exam.bloquemecanico[nabst[posi]].getPregunta());
                }
                if (main_resultado_exam.bloquemecanico[nabst[posi]].getRespuestaA().equals("")) {
                    respuestaA.setVisibility(View.GONE);
                } else {
                    respuestaA.setVisibility(View.VISIBLE);
                    respuestaA.setText(main_resultado_exam.bloquemecanico[nabst[posi]].getRespuestaA());
                }
                if (main_resultado_exam.bloquemecanico[nabst[posi]].getRespuestaB().equals("")) {
                    respuestaB.setVisibility(View.GONE);
                } else {
                    respuestaB.setVisibility(View.VISIBLE);
                    respuestaB.setText(main_resultado_exam.bloquemecanico[nabst[posi]].getRespuestaB());
                }
                if (main_resultado_exam.bloquemecanico[nabst[posi]].getRespuestaC().equals("")) {
                    respuestaC.setVisibility(View.GONE);
                    c.setVisibility(View.GONE);
                } else {
                    respuestaC.setVisibility(View.VISIBLE);
                    respuestaC.setText(main_resultado_exam.bloquemecanico[nabst[posi]].getRespuestaC());
                    c.setVisibility(View.VISIBLE);
                }
                if (main_resultado_exam.bloquemecanico[nabst[posi]].getRespuestaD().equals("")) {
                    respuestaD.setVisibility(View.GONE);
                    d.setVisibility(View.GONE);
                } else {
                    respuestaD.setVisibility(View.VISIBLE);
                    respuestaD.setText(main_resultado_exam.bloquemecanico[nabst[posi]].getRespuestaD());
                    d.setVisibility(View.VISIBLE);
                }
                if (main_resultado_exam.bloquemecanico[nabst[posi]].getSolu().equals("")) {
                    solucion.setVisibility(View.GONE);
                } else {
                    solucion.setVisibility(View.VISIBLE);
                    solucion.setText(main_resultado_exam.bloquemecanico[nabst[posi]].getSolu());
                }
                break;
            case 5:
                bloq.setText(getString(R.string.perceptiva));
                if (main_resultado_exam.bloqueperceptiva[nabst[posi]].getImgPregunta().equals("")) {
                    imgenPre.setVisibility(View.GONE);
                    imgenPre.setImageResource(0);
                } else {
                    imgenPre.setVisibility(View.VISIBLE);
                    imgenPre.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueperceptiva[nabst[posi]].getImgPregunta(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueperceptiva[nabst[posi]].getImgA().equals("")) {
                    imgenA.setVisibility(View.GONE);
                    imgenA.setImageResource(0);
                } else {
                    imgenA.setVisibility(View.VISIBLE);
                    imgenA.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueperceptiva[nabst[posi]].getImgA(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueperceptiva[nabst[posi]].getImgB().equals("")) {
                    imgenB.setVisibility(View.GONE);
                    imgenB.setImageResource(0);
                } else {
                    imgenB.setVisibility(View.VISIBLE);
                    imgenB.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueperceptiva[nabst[posi]].getImgB(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueperceptiva[nabst[posi]].getImgC().equals("")) {
                    imgenC.setVisibility(View.GONE);
                    imgenC.setImageResource(0);
                } else {
                    imgenC.setVisibility(View.VISIBLE);
                    imgenC.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueperceptiva[nabst[posi]].getImgC(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueperceptiva[nabst[posi]].getImgD().equals("")) {
                    imgenD.setVisibility(View.GONE);
                    imgenD.setImageResource(0);
                } else {
                    imgenD.setVisibility(View.VISIBLE);
                    imgenD.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueperceptiva[nabst[posi]].getImgD(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueperceptiva[nabst[posi]].getImgSol().equals("")) {
                    imgenSol.setVisibility(View.GONE);
                    imgenSol.setImageResource(0);
                } else {
                    imgenSol.setVisibility(View.VISIBLE);
                    imgenSol.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueperceptiva[nabst[posi]].getImgSol(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueperceptiva[nabst[posi]].getImgExpli().equals("")) {
                    imgenExp.setVisibility(View.GONE);
                    imgenExp.setImageResource(0);
                } else {
                    imgenExp.setVisibility(View.VISIBLE);
                    imgenExp.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueperceptiva[nabst[posi]].getImgExpli(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueperceptiva[nabst[posi]].getExpliSol().equals("")) {
                    explicacion.setVisibility(View.GONE);
                } else {
                    explicacion.setVisibility(View.VISIBLE);
                    explicacion.setText(main_resultado_exam.bloqueperceptiva[nabst[posi]].getExpliSol());
                }
                if (main_resultado_exam.bloqueperceptiva[nabst[posi]].getPregunta().equals("")) {
                    imppregunta.setVisibility(View.GONE);
                } else {
                    imppregunta.setVisibility(View.VISIBLE);
                    imppregunta.setText(main_resultado_exam.bloqueperceptiva[nabst[posi]].getPregunta());
                }
                if (main_resultado_exam.bloqueperceptiva[nabst[posi]].getRespuestaA().equals("")) {
                    respuestaA.setVisibility(View.GONE);
                } else {
                    respuestaA.setVisibility(View.VISIBLE);
                    respuestaA.setText(main_resultado_exam.bloqueperceptiva[nabst[posi]].getRespuestaA());
                }
                if (main_resultado_exam.bloqueperceptiva[nabst[posi]].getRespuestaB().equals("")) {
                    respuestaB.setVisibility(View.GONE);
                } else {
                    respuestaB.setVisibility(View.VISIBLE);
                    respuestaB.setText(main_resultado_exam.bloqueperceptiva[nabst[posi]].getRespuestaB());
                }
                if (main_resultado_exam.bloqueperceptiva[nabst[posi]].getRespuestaC().equals("")) {
                    respuestaC.setVisibility(View.GONE);
                    c.setVisibility(View.GONE);
                } else {
                    respuestaC.setVisibility(View.VISIBLE);
                    respuestaC.setText(main_resultado_exam.bloqueperceptiva[nabst[posi]].getRespuestaC());
                    c.setVisibility(View.VISIBLE);
                }
                if (main_resultado_exam.bloqueperceptiva[nabst[posi]].getRespuestaD().equals("")) {
                    respuestaD.setVisibility(View.GONE);
                    d.setVisibility(View.GONE);
                } else {
                    respuestaD.setVisibility(View.VISIBLE);
                    respuestaD.setText(main_resultado_exam.bloqueperceptiva[nabst[posi]].getRespuestaD());
                    d.setVisibility(View.VISIBLE);
                }
                if (main_resultado_exam.bloqueperceptiva[nabst[posi]].getSolu().equals("")) {
                    solucion.setVisibility(View.GONE);
                } else {
                    solucion.setVisibility(View.VISIBLE);
                    solucion.setText(main_resultado_exam.bloqueperceptiva[nabst[posi]].getSolu());
                }
                break;
            case 6:
                bloq.setText(getString(R.string.memoria));
                if (main_resultado_exam.bloquememoria[nabst[posi]].getImgPregunta().equals("")) {
                    imgenPre.setVisibility(View.GONE);
                    imgenPre.setImageResource(0);
                } else {
                    imgenPre.setVisibility(View.VISIBLE);
                    imgenPre.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloquememoria[nabst[posi]].getImgPregunta(), null, getPackageName()));
                }
                if (main_resultado_exam.bloquememoria[nabst[posi]].getImgA().equals("")) {
                    imgenA.setVisibility(View.GONE);
                    imgenA.setImageResource(0);
                } else {
                    imgenA.setVisibility(View.VISIBLE);
                    imgenA.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloquememoria[nabst[posi]].getImgA(), null, getPackageName()));
                }
                if (main_resultado_exam.bloquememoria[nabst[posi]].getImgB().equals("")) {
                    imgenB.setVisibility(View.GONE);
                    imgenB.setImageResource(0);
                } else {
                    imgenB.setVisibility(View.VISIBLE);
                    imgenB.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloquememoria[nabst[posi]].getImgB(), null, getPackageName()));
                }
                if (main_resultado_exam.bloquememoria[nabst[posi]].getImgC().equals("")) {
                    imgenC.setVisibility(View.GONE);
                    imgenC.setImageResource(0);
                } else {
                    imgenC.setVisibility(View.VISIBLE);
                    imgenC.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloquememoria[nabst[posi]].getImgC(), null, getPackageName()));
                }
                if (main_resultado_exam.bloquememoria[nabst[posi]].getImgD().equals("")) {
                    imgenD.setVisibility(View.GONE);
                    imgenD.setImageResource(0);
                } else {
                    imgenD.setVisibility(View.VISIBLE);
                    imgenD.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloquememoria[nabst[posi]].getImgD(), null, getPackageName()));
                }
                if (main_resultado_exam.bloquememoria[nabst[posi]].getImgSol().equals("")) {
                    imgenSol.setVisibility(View.GONE);
                    imgenSol.setImageResource(0);
                } else {
                    imgenSol.setVisibility(View.VISIBLE);
                    imgenSol.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloquememoria[nabst[posi]].getImgSol(), null, getPackageName()));
                }
                if (main_resultado_exam.bloquememoria[nabst[posi]].getImgExpli().equals("")) {
                    imgenExp.setVisibility(View.GONE);
                    imgenExp.setImageResource(0);
                } else {
                    imgenExp.setVisibility(View.VISIBLE);
                    imgenExp.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloquememoria[nabst[posi]].getImgExpli(), null, getPackageName()));
                }
                if (main_resultado_exam.bloquememoria[nabst[posi]].getExpliSol().equals("")) {
                    explicacion.setVisibility(View.GONE);
                } else {
                    explicacion.setVisibility(View.VISIBLE);
                    explicacion.setText(main_resultado_exam.bloquememoria[nabst[posi]].getExpliSol());
                }
                if (main_resultado_exam.bloquememoria[nabst[posi]].getPregunta().equals("")) {
                    imppregunta.setVisibility(View.GONE);
                } else {
                    imppregunta.setVisibility(View.VISIBLE);
                    imppregunta.setText(main_resultado_exam.bloquememoria[nabst[posi]].getPregunta());
                }
                if (main_resultado_exam.bloquememoria[nabst[posi]].getRespuestaA().equals("")) {
                    respuestaA.setVisibility(View.GONE);
                } else {
                    respuestaA.setVisibility(View.VISIBLE);
                    respuestaA.setText(main_resultado_exam.bloquememoria[nabst[posi]].getRespuestaA());
                }
                if (main_resultado_exam.bloquememoria[nabst[posi]].getRespuestaB().equals("")) {
                    respuestaB.setVisibility(View.GONE);
                } else {
                    respuestaB.setVisibility(View.VISIBLE);
                    respuestaB.setText(main_resultado_exam.bloquememoria[nabst[posi]].getRespuestaB());
                }
                if (main_resultado_exam.bloquememoria[nabst[posi]].getRespuestaC().equals("")) {
                    respuestaC.setVisibility(View.GONE);
                    c.setVisibility(View.GONE);
                } else {
                    respuestaC.setVisibility(View.VISIBLE);
                    respuestaC.setText(main_resultado_exam.bloquememoria[nabst[posi]].getRespuestaC());
                    c.setVisibility(View.VISIBLE);
                }
                if (main_resultado_exam.bloquememoria[nabst[posi]].getRespuestaD().equals("")) {
                    respuestaD.setVisibility(View.GONE);
                    d.setVisibility(View.GONE);
                } else {
                    respuestaD.setVisibility(View.VISIBLE);
                    respuestaD.setText(main_resultado_exam.bloquememoria[nabst[posi]].getRespuestaD());
                    d.setVisibility(View.VISIBLE);
                }
                if (main_resultado_exam.bloquememoria[nabst[posi]].getSolu().equals("")) {
                    solucion.setVisibility(View.GONE);
                } else {
                    solucion.setVisibility(View.VISIBLE);
                    solucion.setText(main_resultado_exam.bloquememoria[nabst[posi]].getSolu());
                }
                break;
            case 7:
                bloq.setText(getString(R.string.abstrapto));
                if (main_resultado_exam.bloqueabstrapto[nabst[posi]].getImgPregunta().equals("")) {
                    imgenPre.setVisibility(View.GONE);
                    imgenPre.setImageResource(0);
                } else {
                    imgenPre.setVisibility(View.VISIBLE);
                    imgenPre.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueabstrapto[nabst[posi]].getImgPregunta(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueabstrapto[nabst[posi]].getImgA().equals("")) {
                    imgenA.setVisibility(View.GONE);
                    imgenA.setImageResource(0);
                } else {
                    imgenA.setVisibility(View.VISIBLE);
                    imgenA.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueabstrapto[nabst[posi]].getImgA(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueabstrapto[nabst[posi]].getImgB().equals("")) {
                    imgenB.setVisibility(View.GONE);
                    imgenB.setImageResource(0);
                } else {
                    imgenB.setVisibility(View.VISIBLE);
                    imgenB.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueabstrapto[nabst[posi]].getImgB(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueabstrapto[nabst[posi]].getImgC().equals("")) {
                    imgenC.setVisibility(View.GONE);
                    imgenC.setImageResource(0);
                } else {
                    imgenC.setVisibility(View.VISIBLE);
                    imgenC.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueabstrapto[nabst[posi]].getImgC(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueabstrapto[nabst[posi]].getImgD().equals("")) {
                    imgenD.setVisibility(View.GONE);
                    imgenD.setImageResource(0);
                } else {
                    imgenD.setVisibility(View.VISIBLE);
                    imgenD.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueabstrapto[nabst[posi]].getImgD(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueabstrapto[nabst[posi]].getImgSol().equals("")) {
                    imgenSol.setVisibility(View.GONE);
                    imgenSol.setImageResource(0);
                } else {
                    imgenSol.setVisibility(View.VISIBLE);
                    imgenSol.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueabstrapto[nabst[posi]].getImgSol(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueabstrapto[nabst[posi]].getImgExpli().equals("")) {
                    imgenExp.setVisibility(View.GONE);
                    imgenExp.setImageResource(0);
                } else {
                    imgenExp.setVisibility(View.VISIBLE);
                    imgenExp.setImageResource(getResources().getIdentifier("drawable/" + main_resultado_exam.bloqueabstrapto[nabst[posi]].getImgExpli(), null, getPackageName()));
                }
                if (main_resultado_exam.bloqueabstrapto[nabst[posi]].getExpliSol().equals("")) {
                    explicacion.setVisibility(View.GONE);
                } else {
                    explicacion.setVisibility(View.VISIBLE);
                    explicacion.setText(main_resultado_exam.bloqueabstrapto[nabst[posi]].getExpliSol());
                }
                if (main_resultado_exam.bloqueabstrapto[nabst[posi]].getPregunta().equals("")) {
                    imppregunta.setVisibility(View.GONE);
                } else {
                    imppregunta.setVisibility(View.VISIBLE);
                    imppregunta.setText(main_resultado_exam.bloqueabstrapto[nabst[posi]].getPregunta());
                }
                if (main_resultado_exam.bloqueabstrapto[nabst[posi]].getRespuestaA().equals("")) {
                    respuestaA.setVisibility(View.GONE);
                } else {
                    respuestaA.setVisibility(View.VISIBLE);
                    respuestaA.setText(main_resultado_exam.bloqueabstrapto[nabst[posi]].getRespuestaA());
                }
                if (main_resultado_exam.bloqueabstrapto[nabst[posi]].getRespuestaB().equals("")) {
                    respuestaB.setVisibility(View.GONE);
                } else {
                    respuestaB.setVisibility(View.VISIBLE);
                    respuestaB.setText(main_resultado_exam.bloqueabstrapto[nabst[posi]].getRespuestaB());
                }
                if (main_resultado_exam.bloqueabstrapto[nabst[posi]].getRespuestaC().equals("")) {
                    respuestaC.setVisibility(View.GONE);
                    c.setVisibility(View.GONE);
                } else {
                    respuestaC.setVisibility(View.VISIBLE);
                    respuestaC.setText(main_resultado_exam.bloqueabstrapto[nabst[posi]].getRespuestaC());
                    c.setVisibility(View.VISIBLE);
                }
                if (main_resultado_exam.bloqueabstrapto[nabst[posi]].getRespuestaD().equals("")) {
                    respuestaD.setVisibility(View.GONE);
                    d.setVisibility(View.GONE);
                } else {
                    respuestaD.setVisibility(View.VISIBLE);
                    respuestaD.setText(main_resultado_exam.bloqueabstrapto[nabst[posi]].getRespuestaD());
                    d.setVisibility(View.VISIBLE);
                }
                if (main_resultado_exam.bloqueabstrapto[nabst[posi]].getSolu().equals("")) {
                    solucion.setVisibility(View.GONE);
                } else {
                    solucion.setVisibility(View.VISIBLE);
                    solucion.setText(main_resultado_exam.bloqueabstrapto[nabst[posi]].getSolu());
                }
        }

        a = (RelativeLayout) findViewById(R.id.a);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String opt = "a";
                verificarRes(opt);
                switch (bloque) {
                    case 1:
                        main_resultado_exam.bloqueverbal[posi].setRespulsada(1);
                        break;
                    case 2:
                        main_resultado_exam.bloquenumerico[posi].setRespulsada(1);
                        break;
                    case 3:
                        main_resultado_exam.bloqueespacial[posi].setRespulsada(1);
                        break;
                    case 4:
                        main_resultado_exam.bloquemecanico[posi].setRespulsada(1);
                        break;
                    case 5:
                        main_resultado_exam.bloqueperceptiva[posi].setRespulsada(1);
                        break;
                    case 6:
                        main_resultado_exam.bloquememoria[posi].setRespulsada(1);
                        break;
                    case 7:
                        main_resultado_exam.bloqueabstrapto[posi].setRespulsada(1);
                }
                Button alante = (Button) findViewById(R.id.alante);
                alante.setVisibility(View.VISIBLE);
                if (bloque != 6) {
                    Button atras = (Button) findViewById(R.id.atras);
                    atras.setVisibility(View.VISIBLE);
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
                switch (bloque) {
                    case 1:
                        main_resultado_exam.bloqueverbal[posi].setRespulsada(2);
                        break;
                    case 2:
                        main_resultado_exam.bloquenumerico[posi].setRespulsada(2);
                        break;
                    case 3:
                        main_resultado_exam.bloqueespacial[posi].setRespulsada(2);
                        break;
                    case 4:
                        main_resultado_exam.bloquemecanico[posi].setRespulsada(2);
                        break;
                    case 5:
                        main_resultado_exam.bloqueperceptiva[posi].setRespulsada(2);
                        break;
                    case 6:
                        main_resultado_exam.bloquememoria[posi].setRespulsada(2);
                        break;
                    case 7:
                        main_resultado_exam.bloqueabstrapto[posi].setRespulsada(2);
                }
                Button alante = (Button) findViewById(R.id.alante);
                alante.setVisibility(View.VISIBLE);
                if (bloque != 6) {
                    Button atras = (Button) findViewById(R.id.atras);
                    atras.setVisibility(View.VISIBLE);
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
                switch (bloque) {
                    case 1:
                        main_resultado_exam.bloqueverbal[posi].setRespulsada(3);
                        break;
                    case 2:
                        main_resultado_exam.bloquenumerico[posi].setRespulsada(3);
                        break;
                    case 3:
                        main_resultado_exam.bloqueespacial[posi].setRespulsada(3);
                        break;
                    case 4:
                        main_resultado_exam.bloquemecanico[posi].setRespulsada(3);
                        break;
                    case 5:
                        main_resultado_exam.bloqueperceptiva[posi].setRespulsada(3);
                        break;
                    case 6:
                        main_resultado_exam.bloquememoria[posi].setRespulsada(3);
                        break;
                    case 7:
                        main_resultado_exam.bloqueabstrapto[posi].setRespulsada(3);
                }
                Button alante = (Button) findViewById(R.id.alante);
                alante.setVisibility(View.VISIBLE);
                if (bloque != 6) {
                    Button atras = (Button) findViewById(R.id.atras);
                    atras.setVisibility(View.VISIBLE);
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
                switch (bloque) {
                    case 1:
                        main_resultado_exam.bloqueverbal[posi].setRespulsada(4);
                        break;
                    case 2:
                        main_resultado_exam.bloquenumerico[posi].setRespulsada(4);
                        break;
                    case 3:
                        main_resultado_exam.bloqueespacial[posi].setRespulsada(4);
                        break;
                    case 4:
                        main_resultado_exam.bloquemecanico[posi].setRespulsada(4);
                        break;
                    case 5:
                        main_resultado_exam.bloqueperceptiva[posi].setRespulsada(4);
                        break;
                    case 6:
                        main_resultado_exam.bloquememoria[posi].setRespulsada(4);
                        break;
                    case 7:
                        main_resultado_exam.bloqueabstrapto[posi].setRespulsada(4);
                }
                Button alante = (Button) findViewById(R.id.alante);
                alante.setVisibility(View.VISIBLE);
                if (bloque != 6) {
                    Button atras = (Button) findViewById(R.id.atras);
                    atras.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private void verificarRes(String opt) {
        switch (opt) {
            case "a":

                if (respuestaA.getText().equals(solucion.getText())) {
                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                    b.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    c.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    d.setBackgroundResource(R.drawable.boton_opt_preguntas);
                } else {
                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                    b.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    c.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    d.setBackgroundResource(R.drawable.boton_opt_preguntas);
                }
                break;

            case "b":

                if (respuestaB.getText().equals(solucion.getText())) {
                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                    a.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    c.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    d.setBackgroundResource(R.drawable.boton_opt_preguntas);
                } else {
                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                    a.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    c.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    d.setBackgroundResource(R.drawable.boton_opt_preguntas);
                }
                break;

            case "c":

                if (respuestaC.getText().equals(solucion.getText())) {
                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                    a.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    b.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    d.setBackgroundResource(R.drawable.boton_opt_preguntas);
                } else {
                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                    a.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    b.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    d.setBackgroundResource(R.drawable.boton_opt_preguntas);
                }
                break;

            case "d":

                if (respuestaD.getText().equals(solucion.getText())) {
                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                    a.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    b.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    c.setBackgroundResource(R.drawable.boton_opt_preguntas);
                } else {
                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                    a.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    b.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    c.setBackgroundResource(R.drawable.boton_opt_preguntas);
                }

        }
    }

    private void recolocar() {
        a = (RelativeLayout) findViewById(R.id.a);
        b = (RelativeLayout) findViewById(R.id.b);
        c = (RelativeLayout) findViewById(R.id.c);
        d = (RelativeLayout) findViewById(R.id.d);

        a.setBackgroundResource(R.drawable.boton_opt_preguntas);
        b.setBackgroundResource(R.drawable.boton_opt_preguntas);
        c.setBackgroundResource(R.drawable.boton_opt_preguntas);
        d.setBackgroundResource(R.drawable.boton_opt_preguntas);

        switch (bloque) {
            case 1:
                if (main_resultado_exam.bloqueverbal[posi].getRespulsada() != 0) {
                    switch (main_resultado_exam.bloqueverbal[posi].getRespulsada()) {
                        case 1:
                            if (respuestaA.getText().equals(solucion.getText())) {
                                a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            } else {
                                a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            }
                            break;
                        case 2:
                            if (respuestaB.getText().equals(solucion.getText())) {
                                b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            } else {
                                b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            }
                            break;
                        case 3:
                            if (respuestaC.getText().equals(solucion.getText())) {
                                c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            } else {
                                c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            }
                            break;
                        case 4:
                            if (respuestaD.getText().equals(solucion.getText())) {
                                d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            } else {
                                d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            }
                    }
                }
                break;
            case 2:
                if (main_resultado_exam.bloquenumerico[posi].getRespulsada() != 0) {
                    switch (main_resultado_exam.bloquenumerico[posi].getRespulsada()) {
                        case 1:
                            if (respuestaA.getText().equals(solucion.getText())) {
                                a.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                            } else {
                                a.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                            }
                            break;
                        case 2:
                            if (respuestaB.getText().equals(solucion.getText())) {
                                b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            } else {
                                b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            }
                            break;
                        case 3:
                            if (respuestaC.getText().equals(solucion.getText())) {
                                c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            } else {
                                c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            }
                            break;
                        case 4:
                            if (respuestaD.getText().equals(solucion.getText())) {
                                d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            } else {
                                d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            }
                    }
                }
                break;
            case 3:
                if (main_resultado_exam.bloqueespacial[posi].getRespulsada() != 0) {
                    switch (main_resultado_exam.bloqueespacial[posi].getRespulsada()) {
                        case 1:
                            if (respuestaA.getText().equals(solucion.getText())) {
                                a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            } else {
                                a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            }
                            break;
                        case 2:
                            if (respuestaB.getText().equals(solucion.getText())) {
                                b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            } else {
                                b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            }
                            break;
                        case 3:
                            if (respuestaC.getText().equals(solucion.getText())) {
                                c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            } else {
                                c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            }
                            break;
                        case 4:
                            if (respuestaD.getText().equals(solucion.getText())) {
                                d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            } else {
                                d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            }
                    }
                }
                break;
            case 4:
                if (main_resultado_exam.bloquemecanico[posi].getRespulsada() != 0) {
                    switch (main_resultado_exam.bloquemecanico[posi].getRespulsada()) {
                        case 1:
                            if (respuestaA.getText().equals(solucion.getText())) {
                                a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            } else {
                                a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            }
                            break;
                        case 2:
                            if (respuestaB.getText().equals(solucion.getText())) {
                                b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            } else {
                                b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            }
                            break;
                        case 3:
                            if (respuestaC.getText().equals(solucion.getText())) {
                                c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            } else {
                                c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            }
                            break;
                        case 4:
                            if (respuestaD.getText().equals(solucion.getText())) {
                                d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            } else {
                                d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            }
                    }
                }
                break;
            case 5:
                if (main_resultado_exam.bloqueperceptiva[posi].getRespulsada() != 0) {
                    switch (main_resultado_exam.bloqueperceptiva[posi].getRespulsada()) {
                        case 1:
                            if (respuestaA.getText().equals(solucion.getText())) {
                                a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            } else {
                                a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            }
                            break;
                        case 2:
                            if (respuestaB.getText().equals(solucion.getText())) {
                                b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            } else {
                                b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            }
                            break;
                        case 3:
                            if (respuestaC.getText().equals(solucion.getText())) {
                                c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            } else {
                                c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            }
                            break;
                        case 4:
                            if (respuestaD.getText().equals(solucion.getText())) {
                                d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            } else {
                                d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            }
                    }
                }
                break;
            case 6:
                if (main_resultado_exam.bloquememoria[posi].getRespulsada() != 0) {
                    switch (main_resultado_exam.bloquememoria[posi].getRespulsada()) {
                        case 1:
                            if (respuestaA.getText().equals(solucion.getText())) {
                                a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            } else {
                                a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            }
                            break;
                        case 2:
                            if (respuestaB.getText().equals(solucion.getText())) {
                                b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            } else {
                                b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            }
                            break;
                        case 3:
                            if (respuestaC.getText().equals(solucion.getText())) {
                                c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            } else {
                                c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            }
                            break;
                        case 4:
                            if (respuestaD.getText().equals(solucion.getText())) {
                                d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            } else {
                                d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            }
                    }
                }
                break;
            case 7:
                if (main_resultado_exam.bloqueabstrapto[posi].getRespulsada() != 0) {
                    switch (main_resultado_exam.bloqueabstrapto[posi].getRespulsada()) {
                        case 1:
                            a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            break;
                        case 2:
                            if (respuestaB.getText().equals(solucion.getText())) {
                                b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            } else {
                                b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            }
                            break;
                        case 3:
                            if (respuestaC.getText().equals(solucion.getText())) {
                                c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            } else {
                                c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            }
                            break;
                        case 4:
                            if (respuestaD.getText().equals(solucion.getText())) {
                                d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            } else {
                                d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                            }
                    }
                }
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            cuentatiempo = guardatiempo;
            if (acabar == false) {
                th.cancel();
                th = null;
                new AlertDialog.Builder(this)
                        .setIcon(R.drawable.pause)
                        .setTitle(getString(R.string.Pausa))
                        .setCancelable(false)
                        .setMessage(getString(R.string.pausa))
                        .setNegativeButton(getString(R.string.continuar), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                th = new CountDownTimer(cuentatiempo, 1000) {
                                    public void onTick(long millisUntilFinished) {
                                        cuentatras.setText(getString(R.string.tiemporesdta) + " " + millisUntilFinished / 1000 + " " + getString(R.string.segundos));
                                        guardatiempo = millisUntilFinished;
                                    }

                                    public void onFinish() {
                                        cuentatras.setText(getString(R.string.tiemporesdta) + " " + "0" + " " + getString(R.string.segundos));
                                        new AlertDialog.Builder(main_examen.this)
                                                .setTitle(getString(R.string.atencion))
                                                .setMessage(getString(R.string.tiempoterminado))
                                                .setCancelable(false)
                                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                    @SuppressWarnings("deprecation")
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        posi = 0;
                                                        bloque++;
                                                        if (bloque == 8) {
                                                            acabar();
                                                            Intent resultado = new Intent(main_examen.this, main_resultado_exam.class);
                                                            startActivity(resultado);
                                                            overridePendingTransition(R.anim.transpain, R.anim.transpaout);
                                                        } else {
                                                            imprimir(bloque, posi);
                                                            contador.setText((posi + 1) + "/15");
                                                            cuentabloque.setText(bloque + "/7");
                                                            cuentatiempo = tempo * 1000;
                                                            th.start();
                                                            memoria();
                                                        }
                                                    }
                                                }).create().show();

                                    }
                                }.start();
                            }
                        })
                        .setPositiveButton(getString(R.string.salir),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        th = null;
                                        finish();


                                    }
                                }).show();
            } else {
                finish();
            }

            return true;

        }
        return super.onKeyDown(keyCode, event);

    }

    public void esperarYCerrar(int milisegundos) {
        if (posi == 0) {
            cuentatiempo = tempo * 1000;
            cuentatras.setText(getString(R.string.tiemporesdta) + " " + ((cuentatiempo / 1000) - 1) + " " + getString(R.string.segundos));
        } else {
            cuentatiempo = guardatiempo;
        }
        th.cancel();
        th = null;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                RelativeLayout amemo = (RelativeLayout) findViewById(R.id.a);
                RelativeLayout bmemo = (RelativeLayout) findViewById(R.id.b);
                RelativeLayout cmemo = (RelativeLayout) findViewById(R.id.c);
                RelativeLayout dmemo = (RelativeLayout) findViewById(R.id.d);
                TextView pregunta = (TextView) findViewById(R.id.pregunta);
                ImageView imgpre = (ImageView) findViewById(R.id.imgpre);
                amemo.setVisibility(View.VISIBLE);
                bmemo.setVisibility(View.VISIBLE);
                cmemo.setVisibility(View.VISIBLE);
                dmemo.setVisibility(View.VISIBLE);
                imgpre.setVisibility(View.GONE);
                pregunta.setVisibility(View.VISIBLE);
                th = new CountDownTimer(cuentatiempo, 1000) {
                    public void onTick(long millisUntilFinished) {
                        cuentatras.setText(getString(R.string.tiemporesdta) + " " + millisUntilFinished / 1000 + " " + getString(R.string.segundos));
                        guardatiempo = millisUntilFinished;
                    }

                    public void onFinish() {
                        cuentatras.setText(getString(R.string.tiemporesdta) + " " + "0" + " " + getString(R.string.segundos));
                        new AlertDialog.Builder(main_examen.this)
                                .setTitle(getString(R.string.atencion))
                                .setMessage(getString(R.string.tiempoterminado))
                                .setCancelable(false)
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @SuppressWarnings("deprecation")
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        posi = 0;
                                        bloque++;
                                        if (bloque == 8) {
                                            acabar();
                                            Intent resultado = new Intent(main_examen.this, main_resultado_exam.class);
                                            startActivity(resultado);
                                            overridePendingTransition(R.anim.transpain, R.anim.transpaout);
                                        } else {
                                            imprimir(bloque, posi);
                                            contador.setText((posi + 1) + "/15");
                                            cuentabloque.setText(bloque + "/7");
                                            cuentatiempo = tempo * 1000;
                                            th.start();
                                            memoria();
                                        }
                                    }
                                }).create().show();

                    }
                }.start();
            }
        }, milisegundos);
    }

    private void memoria() {
        if (bloque == 6 && !acabar && posi < 15) {
            RelativeLayout amemo = (RelativeLayout) findViewById(R.id.a);
            RelativeLayout bmemo = (RelativeLayout) findViewById(R.id.b);
            RelativeLayout cmemo = (RelativeLayout) findViewById(R.id.c);
            RelativeLayout dmemo = (RelativeLayout) findViewById(R.id.d);
            TextView pregunta = (TextView) findViewById(R.id.pregunta);
            ImageView imgpre = (ImageView) findViewById(R.id.imgpre);
            Button atras = (Button) findViewById(R.id.atras);
            Button alante = (Button) findViewById(R.id.alante);
            amemo.setVisibility(View.GONE);
            bmemo.setVisibility(View.GONE);
            cmemo.setVisibility(View.GONE);
            dmemo.setVisibility(View.GONE);
            imgpre.setVisibility(View.VISIBLE);
            pregunta.setVisibility(View.GONE);
            atras.setVisibility(View.INVISIBLE);
            alante.setVisibility(View.INVISIBLE);
            esperarYCerrar(2000);
        }
        if (bloque != 6) {
            Button atras = (Button) findViewById(R.id.atras);
            atras.setVisibility(View.VISIBLE);
            Button alante = (Button) findViewById(R.id.alante);
            alante.setVisibility(View.VISIBLE);
        }
    }

    private void acabar() {
        siguiente.setEnabled(true);
        if (acabar == false) {
            //th.cancel();
            th = null;
        }
        acabar = true;
        th = null;
        arregloacabar = true;
        a.setEnabled(false);
        b.setEnabled(false);
        c.setEnabled(false);
        d.setEnabled(false);
        Msolucion.setVisibility(View.VISIBLE);
        contenedor.setBackgroundColor(Color.parseColor("#E8F0F1"));
        ImageView prohibido = (ImageView) findViewById(R.id.prohibido);
        prohibido.setVisibility(View.VISIBLE);
        TextView cuentatras = (TextView) findViewById(R.id.cuentatras);
        cuentatras.setVisibility(View.GONE);
        prohibido.setImageResource(getResources().getIdentifier("drawable/" + "prohibido", null, getPackageName()));
    }
}
