package com.andymarcial.psicotecnicostropa.tropa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
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
import android.widget.ViewFlipper;

import com.andymarcial.psicotecnicostropa.R;
import com.andymarcial.psicotecnicostropa.tropa.implementacionesTropa.Cronometro;
import com.andymarcial.psicotecnicostropa.tropa.dtoTropa.Notas;

//import java.util.Calendar;
//import java.util.GregorianCalendar;


/**
 * Created by andym on 07/04/2017.
 */

public class main_subevolucion extends Activity {

    Animation animrightatras = null;
    Animation animrightalante = null;

    boolean pasusaexamen = false;
    Thread hilo;
    Cronometro cronometro;

    public static boolean acabatiempo = false;

    static boolean acabar = false;
    static boolean guardado = false;

    RelativeLayout a;
    RelativeLayout b;
    RelativeLayout c;
    RelativeLayout d;
    TextView imppregunta, respuestaA, respuestaB, respuestaC, respuestaD, solucion, explicacion, contador, cuentabloque, cuentatras, estado;
    ImageView imgenPre, imgenA, imgenB, imgenC, imgenD, imgenSol, imgenExp;
    LinearLayout Msolucion;
    ScrollView contenedor;
    int bloque;
    int posi;
    int memoria;
    boolean arregloacabar = false;
    Button siguiente, ver, finish;

    static Notas notas;
    ViewFlipper viewflipper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_preguntas);

        /*RelativeLayout padre = (RelativeLayout) findViewById(R.id.relativeLayout);
        RelativeLayout subcontenedor = (RelativeLayout) findViewById(R.id.subcontenedor);
        Calendar cc1 = new GregorianCalendar();
        int dia = cc1.get(Calendar.DAY_OF_MONTH);
        int mes = cc1.get(Calendar.MONTH)+1;
        if( (mes ==11 || mes ==12) || (mes ==1 && dia <=7)){
            padre.setBackgroundResource(R.color.rojonavidad);
            subcontenedor.setBackgroundResource(R.color.rojonavidad);
        }else{

        }*/

        pasusaexamen = true;
        acabatiempo = false;
        acabar = false;
        guardado = false;
        bloque = 1;
        posi = 0;
        arregloacabar = false;

        // animaciones
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


        ver = (Button)findViewById(R.id.ver);
        finish = (Button)findViewById(R.id.finish);

        ver.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   veropt();
               }
           }
        );

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultado = new Intent(main_subevolucion.this, main_resultado_exam.class);
                startActivity(resultado);
                overridePendingTransition(R.anim.transpain, R.anim.transpaout);
            }
        });

        ImageView prohibido = (ImageView) findViewById(R.id.prohibido);
        prohibido.setVisibility(View.GONE);

        contenedor = (ScrollView) findViewById(R.id.contenedor);

        estado = (TextView) findViewById(R.id.estado);
        estado.setVisibility(View.GONE);
        Msolucion = (LinearLayout) findViewById(R.id.solucion);
        Msolucion.setVisibility(View.GONE);


        notas = (Notas)getIntent().getExtras().getSerializable("notas");


        cuentabloque = (TextView) findViewById(R.id.cuentabloque);
        cuentabloque.setVisibility(View.VISIBLE);
        cuentabloque.setText("1/7");

        cuentatras = (TextView) findViewById(R.id.cuentatras);
        cuentatras.setVisibility(View.VISIBLE);

        contador = (TextView) findViewById(R.id.conta);
        contador.setText("1/15");
        imprimir(bloque, posi);
        siguiente = (Button) findViewById(R.id.alante);
        viewflipper = (ViewFlipper) findViewById(R.id.ViewFlipper1);
        viewflipper.setInAnimation(animrightalante);
        viewflipper.showPrevious();

        acabar();
        imprimir(bloque, posi);
        recolocar();
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bloque == 7 && posi == 14) {
                    if (arregloacabar == false) {

                    } else {
                        Intent resultado = new Intent(main_subevolucion.this, main_resultado_exam.class);
                        startActivity(resultado);
                        overridePendingTransition(R.anim.transpain, R.anim.transpaout);
                    }


                } else if (acabar == false) {

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
                        viewflipper.setInAnimation(animrightalante);
                        viewflipper.showPrevious();
                    }

                }
                recolocar();
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
                        viewflipper.setInAnimation(animrightatras);
                        viewflipper.showPrevious();
                    }
                }
            }
        });
    }


    private void limpiarselect() {
        a.setBackgroundResource(R.drawable.boton_opt_preguntas);
        b.setBackgroundResource(R.drawable.boton_opt_preguntas);
        c.setBackgroundResource(R.drawable.boton_opt_preguntas);
        d.setBackgroundResource(R.drawable.boton_opt_preguntas);
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
                if (notas.getBloqueverbal().get(posi).getImgPregunta().equals("")) {
                    imgenPre.setVisibility(View.GONE);
                    imgenPre.setImageResource(0);
                } else {
                    imgenPre.setVisibility(View.VISIBLE);
                    imgenPre.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueverbal().get(posi).getImgPregunta(), null, getPackageName()));
                }
                if (notas.getBloqueverbal().get(posi).getImgA().equals("")) {
                    imgenA.setVisibility(View.GONE);
                    imgenA.setImageResource(0);
                } else {
                    imgenA.setVisibility(View.VISIBLE);
                    imgenA.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueverbal().get(posi).getImgA(), null, getPackageName()));
                }
                if (notas.getBloqueverbal().get(posi).getImgB().equals("")) {
                    imgenB.setVisibility(View.GONE);
                    imgenB.setImageResource(0);
                } else {
                    imgenB.setVisibility(View.VISIBLE);
                    imgenB.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueverbal().get(posi).getImgB(), null, getPackageName()));
                }
                if (notas.getBloqueverbal().get(posi).getImgC().equals("")) {
                    imgenC.setVisibility(View.GONE);
                    imgenC.setImageResource(0);
                } else {
                    imgenC.setVisibility(View.VISIBLE);
                    imgenC.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueverbal().get(posi).getImgC(), null, getPackageName()));
                }
                if (notas.getBloqueverbal().get(posi).getImgD().equals("")) {
                    imgenD.setVisibility(View.GONE);
                    imgenD.setImageResource(0);
                } else {
                    imgenD.setVisibility(View.VISIBLE);
                    imgenD.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueverbal().get(posi).getImgD(), null, getPackageName()));
                }
                if (notas.getBloqueverbal().get(posi).getImgSol().equals("")) {
                    imgenSol.setVisibility(View.GONE);
                    imgenSol.setImageResource(0);
                } else {
                    imgenSol.setVisibility(View.VISIBLE);
                    imgenSol.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueverbal().get(posi).getImgSol(), null, getPackageName()));
                }
                if (notas.getBloqueverbal().get(posi).getImgExpli().equals("")) {
                    imgenExp.setVisibility(View.GONE);
                    imgenExp.setImageResource(0);
                } else {
                    imgenExp.setVisibility(View.VISIBLE);
                    imgenExp.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueverbal().get(posi).getImgExpli(), null, getPackageName()));
                }
                if (notas.getBloqueverbal().get(posi).getExpliSol().equals("")) {
                    explicacion.setVisibility(View.GONE);
                } else {
                    explicacion.setVisibility(View.VISIBLE);
                    explicacion.setText(notas.getBloqueverbal().get(posi).getExpliSol());
                }
                if (notas.getBloqueverbal().get(posi).getPregunta().equals("")) {
                    imppregunta.setVisibility(View.GONE);
                } else {
                    imppregunta.setVisibility(View.VISIBLE);
                    imppregunta.setText(notas.getBloqueverbal().get(posi).getPregunta());
                }
                if (notas.getBloqueverbal().get(posi).getRespuestaA().equals("")) {
                    respuestaA.setVisibility(View.GONE);
                } else {
                    respuestaA.setVisibility(View.VISIBLE);
                    respuestaA.setText(notas.getBloqueverbal().get(posi).getRespuestaA());
                }
                if (notas.getBloqueverbal().get(posi).getRespuestaB().equals("")) {
                    respuestaB.setVisibility(View.GONE);
                } else {
                    respuestaB.setVisibility(View.VISIBLE);
                    respuestaB.setText(notas.getBloqueverbal().get(posi).getRespuestaB());
                }
                if (notas.getBloqueverbal().get(posi).getRespuestaC().equals("")) {
                    respuestaC.setVisibility(View.GONE);
                    c.setVisibility(View.GONE);
                } else {
                    respuestaC.setVisibility(View.VISIBLE);
                    respuestaC.setText(notas.getBloqueverbal().get(posi).getRespuestaC());
                    c.setVisibility(View.VISIBLE);
                }
                if (notas.getBloqueverbal().get(posi).getRespuestaD().equals("")) {
                    respuestaD.setVisibility(View.GONE);
                    d.setVisibility(View.GONE);
                } else {
                    respuestaD.setVisibility(View.VISIBLE);
                    respuestaD.setText(notas.getBloqueverbal().get(posi).getRespuestaD());
                    d.setVisibility(View.VISIBLE);
                }
                if (notas.getBloqueverbal().get(posi).getSolu().equals("")) {
                    solucion.setVisibility(View.GONE);
                } else {
                    solucion.setVisibility(View.VISIBLE);
                    solucion.setText(notas.getBloqueverbal().get(posi).getSolu());
                }
                break;
            case 2:
                bloq.setText(getString(R.string.numerico));
                if (notas.getBloquenumerico().get(posi).getImgPregunta().equals("")) {
                    imgenPre.setVisibility(View.GONE);
                    imgenPre.setImageResource(0);
                } else {
                    imgenPre.setVisibility(View.VISIBLE);
                    imgenPre.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloquenumerico().get(posi).getImgPregunta(), null, getPackageName()));
                }
                if (notas.getBloquenumerico().get(posi).getImgA().equals("")) {
                    imgenA.setVisibility(View.GONE);
                    imgenA.setImageResource(0);
                } else {
                    imgenA.setVisibility(View.VISIBLE);
                    imgenA.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloquenumerico().get(posi).getImgA(), null, getPackageName()));
                }
                if (notas.getBloquenumerico().get(posi).getImgB().equals("")) {
                    imgenB.setVisibility(View.GONE);
                    imgenB.setImageResource(0);
                } else {
                    imgenB.setVisibility(View.VISIBLE);
                    imgenB.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloquenumerico().get(posi).getImgB(), null, getPackageName()));
                }
                if (notas.getBloquenumerico().get(posi).getImgC().equals("")) {
                    imgenC.setVisibility(View.GONE);
                    imgenC.setImageResource(0);
                } else {
                    imgenC.setVisibility(View.VISIBLE);
                    imgenC.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloquenumerico().get(posi).getImgC(), null, getPackageName()));
                }
                if (notas.getBloquenumerico().get(posi).getImgD().equals("")) {
                    imgenD.setVisibility(View.GONE);
                    imgenD.setImageResource(0);
                } else {
                    imgenD.setVisibility(View.VISIBLE);
                    imgenD.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloquenumerico().get(posi).getImgD(), null, getPackageName()));
                }
                if (notas.getBloquenumerico().get(posi).getImgSol().equals("")) {
                    imgenSol.setVisibility(View.GONE);
                    imgenSol.setImageResource(0);
                } else {
                    imgenSol.setVisibility(View.VISIBLE);
                    imgenSol.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloquenumerico().get(posi).getImgSol(), null, getPackageName()));
                }
                if (notas.getBloquenumerico().get(posi).getImgExpli().equals("")) {
                    imgenExp.setVisibility(View.GONE);
                    imgenExp.setImageResource(0);
                } else {
                    imgenExp.setVisibility(View.VISIBLE);
                    imgenExp.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloquenumerico().get(posi).getImgExpli(), null, getPackageName()));
                }
                if (notas.getBloquenumerico().get(posi).getExpliSol().equals("")) {
                    explicacion.setVisibility(View.GONE);
                } else {
                    explicacion.setVisibility(View.VISIBLE);
                    explicacion.setText(notas.getBloquenumerico().get(posi).getExpliSol());
                }
                if (notas.getBloquenumerico().get(posi).getPregunta().equals("")) {
                    imppregunta.setVisibility(View.GONE);
                } else {
                    imppregunta.setVisibility(View.VISIBLE);
                    imppregunta.setText(notas.getBloquenumerico().get(posi).getPregunta());
                }
                if (notas.getBloquenumerico().get(posi).getRespuestaA().equals("")) {
                    respuestaA.setVisibility(View.GONE);
                } else {
                    respuestaA.setVisibility(View.VISIBLE);
                    respuestaA.setText(notas.getBloquenumerico().get(posi).getRespuestaA());
                }
                if (notas.getBloquenumerico().get(posi).getRespuestaB().equals("")) {
                    respuestaB.setVisibility(View.GONE);
                } else {
                    respuestaB.setVisibility(View.VISIBLE);
                    respuestaB.setText(notas.getBloquenumerico().get(posi).getRespuestaB());
                }
                if (notas.getBloquenumerico().get(posi).getRespuestaC().equals("")) {
                    respuestaC.setVisibility(View.GONE);
                    c.setVisibility(View.GONE);
                } else {
                    respuestaC.setVisibility(View.VISIBLE);
                    respuestaC.setText(notas.getBloquenumerico().get(posi).getRespuestaC());
                    c.setVisibility(View.VISIBLE);
                }
                if (notas.getBloquenumerico().get(posi).getRespuestaD().equals("")) {
                    respuestaD.setVisibility(View.GONE);
                    d.setVisibility(View.GONE);
                } else {
                    respuestaD.setVisibility(View.VISIBLE);
                    respuestaD.setText(notas.getBloquenumerico().get(posi).getRespuestaD());
                    d.setVisibility(View.VISIBLE);
                }
                if (notas.getBloquenumerico().get(posi).getSolu().equals("")) {
                    solucion.setVisibility(View.GONE);
                } else {
                    solucion.setVisibility(View.VISIBLE);
                    solucion.setText(notas.getBloquenumerico().get(posi).getSolu());
                }
                break;
            case 3:
                bloq.setText(getString(R.string.espacial));
                if (notas.getBloqueespacial().get(posi).getImgPregunta().equals("")) {
                    imgenPre.setVisibility(View.GONE);
                    imgenPre.setImageResource(0);
                } else {
                    imgenPre.setVisibility(View.VISIBLE);
                    imgenPre.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueespacial().get(posi).getImgPregunta(), null, getPackageName()));
                }
                if (notas.getBloqueespacial().get(posi).getImgA().equals("")) {
                    imgenA.setVisibility(View.GONE);
                    imgenA.setImageResource(0);
                } else {
                    imgenA.setVisibility(View.VISIBLE);
                    imgenA.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueespacial().get(posi).getImgA(), null, getPackageName()));
                }
                if (notas.getBloqueespacial().get(posi).getImgB().equals("")) {
                    imgenB.setVisibility(View.GONE);
                    imgenB.setImageResource(0);
                } else {
                    imgenB.setVisibility(View.VISIBLE);
                    imgenB.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueespacial().get(posi).getImgB(), null, getPackageName()));
                }
                if (notas.getBloqueespacial().get(posi).getImgC().equals("")) {
                    imgenC.setVisibility(View.GONE);
                    imgenC.setImageResource(0);
                } else {
                    imgenC.setVisibility(View.VISIBLE);
                    imgenC.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueespacial().get(posi).getImgC(), null, getPackageName()));
                }
                if (notas.getBloqueespacial().get(posi).getImgD().equals("")) {
                    imgenD.setVisibility(View.GONE);
                    imgenD.setImageResource(0);
                } else {
                    imgenD.setVisibility(View.VISIBLE);
                    imgenD.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueespacial().get(posi).getImgD(), null, getPackageName()));
                }
                if (notas.getBloqueespacial().get(posi).getImgSol().equals("")) {
                    imgenSol.setVisibility(View.GONE);
                    imgenSol.setImageResource(0);
                } else {
                    imgenSol.setVisibility(View.VISIBLE);
                    imgenSol.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueespacial().get(posi).getImgSol(), null, getPackageName()));
                }
                if (notas.getBloqueespacial().get(posi).getImgExpli().equals("")) {
                    imgenExp.setVisibility(View.GONE);
                    imgenExp.setImageResource(0);
                } else {
                    imgenExp.setVisibility(View.VISIBLE);
                    imgenExp.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueespacial().get(posi).getImgExpli(), null, getPackageName()));
                }
                if (notas.getBloqueespacial().get(posi).getExpliSol().equals("")) {
                    explicacion.setVisibility(View.GONE);
                } else {
                    explicacion.setVisibility(View.VISIBLE);
                    explicacion.setText(notas.getBloqueespacial().get(posi).getExpliSol());
                }
                if (notas.getBloqueespacial().get(posi).getPregunta().equals("")) {
                    imppregunta.setVisibility(View.GONE);
                } else {
                    imppregunta.setVisibility(View.VISIBLE);
                    imppregunta.setText(notas.getBloqueespacial().get(posi).getPregunta());
                }
                if (notas.getBloqueespacial().get(posi).getRespuestaA().equals("")) {
                    respuestaA.setVisibility(View.GONE);
                } else {
                    respuestaA.setVisibility(View.VISIBLE);
                    respuestaA.setText(notas.getBloqueespacial().get(posi).getRespuestaA());
                }
                if (notas.getBloqueespacial().get(posi).getRespuestaB().equals("")) {
                    respuestaB.setVisibility(View.GONE);
                } else {
                    respuestaB.setVisibility(View.VISIBLE);
                    respuestaB.setText(notas.getBloqueespacial().get(posi).getRespuestaB());
                }
                if (notas.getBloqueespacial().get(posi).getRespuestaC().equals("")) {
                    respuestaC.setVisibility(View.GONE);
                    c.setVisibility(View.GONE);
                } else {
                    respuestaC.setVisibility(View.VISIBLE);
                    respuestaC.setText(notas.getBloqueespacial().get(posi).getRespuestaC());
                    c.setVisibility(View.VISIBLE);
                }
                if (notas.getBloqueespacial().get(posi).getRespuestaD().equals("")) {
                    respuestaD.setVisibility(View.GONE);
                    d.setVisibility(View.GONE);
                } else {
                    respuestaD.setVisibility(View.VISIBLE);
                    respuestaD.setText(notas.getBloqueespacial().get(posi).getRespuestaD());
                    d.setVisibility(View.VISIBLE);
                }
                if (notas.getBloqueespacial().get(posi).getSolu().equals("")) {
                    solucion.setVisibility(View.GONE);
                } else {
                    solucion.setVisibility(View.VISIBLE);
                    solucion.setText(notas.getBloqueespacial().get(posi).getSolu());
                }
                break;
            case 4:
                bloq.setText(getString(R.string.mecanico));
                if (notas.getBloquemecanico().get(posi).getImgPregunta().equals("")) {
                    imgenPre.setVisibility(View.GONE);
                    imgenPre.setImageResource(0);
                } else {
                    imgenPre.setVisibility(View.VISIBLE);
                    imgenPre.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloquemecanico().get(posi).getImgPregunta(), null, getPackageName()));
                }
                if (notas.getBloquemecanico().get(posi).getImgA().equals("")) {
                    imgenA.setVisibility(View.GONE);
                    imgenA.setImageResource(0);
                } else {
                    imgenA.setVisibility(View.VISIBLE);
                    imgenA.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloquemecanico().get(posi).getImgA(), null, getPackageName()));
                }
                if (notas.getBloquemecanico().get(posi).getImgB().equals("")) {
                    imgenB.setVisibility(View.GONE);
                    imgenB.setImageResource(0);
                } else {
                    imgenB.setVisibility(View.VISIBLE);
                    imgenB.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloquemecanico().get(posi).getImgB(), null, getPackageName()));
                }
                if (notas.getBloquemecanico().get(posi).getImgC().equals("")) {
                    imgenC.setVisibility(View.GONE);
                    imgenC.setImageResource(0);
                } else {
                    imgenC.setVisibility(View.VISIBLE);
                    imgenC.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloquemecanico().get(posi).getImgC(), null, getPackageName()));
                }
                if (notas.getBloquemecanico().get(posi).getImgD().equals("")) {
                    imgenD.setVisibility(View.GONE);
                    imgenD.setImageResource(0);
                } else {
                    imgenD.setVisibility(View.VISIBLE);
                    imgenD.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloquemecanico().get(posi).getImgD(), null, getPackageName()));
                }
                if (notas.getBloquemecanico().get(posi).getImgSol().equals("")) {
                    imgenSol.setVisibility(View.GONE);
                    imgenSol.setImageResource(0);
                } else {
                    imgenSol.setVisibility(View.VISIBLE);
                    imgenSol.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloquemecanico().get(posi).getImgSol(), null, getPackageName()));
                }
                if (notas.getBloquemecanico().get(posi).getImgExpli().equals("")) {
                    imgenExp.setVisibility(View.GONE);
                    imgenExp.setImageResource(0);
                } else {
                    imgenExp.setVisibility(View.VISIBLE);
                    imgenExp.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloquemecanico().get(posi).getImgExpli(), null, getPackageName()));
                }
                if (notas.getBloquemecanico().get(posi).getExpliSol().equals("")) {
                    explicacion.setVisibility(View.GONE);
                } else {
                    explicacion.setVisibility(View.VISIBLE);
                    explicacion.setText(notas.getBloquemecanico().get(posi).getExpliSol());
                }
                if (notas.getBloquemecanico().get(posi).getPregunta().equals("")) {
                    imppregunta.setVisibility(View.GONE);
                } else {
                    imppregunta.setVisibility(View.VISIBLE);
                    imppregunta.setText(notas.getBloquemecanico().get(posi).getPregunta());
                }
                if (notas.getBloquemecanico().get(posi).getRespuestaA().equals("")) {
                    respuestaA.setVisibility(View.GONE);
                } else {
                    respuestaA.setVisibility(View.VISIBLE);
                    respuestaA.setText(notas.getBloquemecanico().get(posi).getRespuestaA());
                }
                if (notas.getBloquemecanico().get(posi).getRespuestaB().equals("")) {
                    respuestaB.setVisibility(View.GONE);
                } else {
                    respuestaB.setVisibility(View.VISIBLE);
                    respuestaB.setText(notas.getBloquemecanico().get(posi).getRespuestaB());
                }
                if (notas.getBloquemecanico().get(posi).getRespuestaC().equals("")) {
                    respuestaC.setVisibility(View.GONE);
                    c.setVisibility(View.GONE);
                } else {
                    respuestaC.setVisibility(View.VISIBLE);
                    respuestaC.setText(notas.getBloquemecanico().get(posi).getRespuestaC());
                    c.setVisibility(View.VISIBLE);
                }
                if (notas.getBloquemecanico().get(posi).getRespuestaD().equals("")) {
                    respuestaD.setVisibility(View.GONE);
                    d.setVisibility(View.GONE);
                } else {
                    respuestaD.setVisibility(View.VISIBLE);
                    respuestaD.setText(notas.getBloquemecanico().get(posi).getRespuestaD());
                    d.setVisibility(View.VISIBLE);
                }
                if (notas.getBloquemecanico().get(posi).getSolu().equals("")) {
                    solucion.setVisibility(View.GONE);
                } else {
                    solucion.setVisibility(View.VISIBLE);
                    solucion.setText(notas.getBloquemecanico().get(posi).getSolu());
                }
                break;
            case 5:
                bloq.setText(getString(R.string.perceptiva));
                if (notas.getBloqueperceptiva().get(posi).getImgPregunta().equals("")) {
                    imgenPre.setVisibility(View.GONE);
                    imgenPre.setImageResource(0);
                } else {
                    imgenPre.setVisibility(View.VISIBLE);
                    imgenPre.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueperceptiva().get(posi).getImgPregunta(), null, getPackageName()));
                }
                if (notas.getBloqueperceptiva().get(posi).getImgA().equals("")) {
                    imgenA.setVisibility(View.GONE);
                    imgenA.setImageResource(0);
                } else {
                    imgenA.setVisibility(View.VISIBLE);
                    imgenA.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueperceptiva().get(posi).getImgA(), null, getPackageName()));
                }
                if (notas.getBloqueperceptiva().get(posi).getImgB().equals("")) {
                    imgenB.setVisibility(View.GONE);
                    imgenB.setImageResource(0);
                } else {
                    imgenB.setVisibility(View.VISIBLE);
                    imgenB.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueperceptiva().get(posi).getImgB(), null, getPackageName()));
                }
                if (notas.getBloqueperceptiva().get(posi).getImgC().equals("")) {
                    imgenC.setVisibility(View.GONE);
                    imgenC.setImageResource(0);
                } else {
                    imgenC.setVisibility(View.VISIBLE);
                    imgenC.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueperceptiva().get(posi).getImgC(), null, getPackageName()));
                }
                if (notas.getBloqueperceptiva().get(posi).getImgD().equals("")) {
                    imgenD.setVisibility(View.GONE);
                    imgenD.setImageResource(0);
                } else {
                    imgenD.setVisibility(View.VISIBLE);
                    imgenD.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueperceptiva().get(posi).getImgD(), null, getPackageName()));
                }
                if (notas.getBloqueperceptiva().get(posi).getImgSol().equals("")) {
                    imgenSol.setVisibility(View.GONE);
                    imgenSol.setImageResource(0);
                } else {
                    imgenSol.setVisibility(View.VISIBLE);
                    imgenSol.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueperceptiva().get(posi).getImgSol(), null, getPackageName()));
                }
                if (notas.getBloqueperceptiva().get(posi).getImgExpli().equals("")) {
                    imgenExp.setVisibility(View.GONE);
                    imgenExp.setImageResource(0);
                } else {
                    imgenExp.setVisibility(View.VISIBLE);
                    imgenExp.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueperceptiva().get(posi).getImgExpli(), null, getPackageName()));
                }
                if (notas.getBloqueperceptiva().get(posi).getExpliSol().equals("")) {
                    explicacion.setVisibility(View.GONE);
                } else {
                    explicacion.setVisibility(View.VISIBLE);
                    explicacion.setText(notas.getBloqueperceptiva().get(posi).getExpliSol());
                }
                if (notas.getBloqueperceptiva().get(posi).getPregunta().equals("")) {
                    imppregunta.setVisibility(View.GONE);
                } else {
                    imppregunta.setVisibility(View.VISIBLE);
                    imppregunta.setText(notas.getBloqueperceptiva().get(posi).getPregunta());
                }
                if (notas.getBloqueperceptiva().get(posi).getRespuestaA().equals("")) {
                    respuestaA.setVisibility(View.GONE);
                } else {
                    respuestaA.setVisibility(View.VISIBLE);
                    respuestaA.setText(notas.getBloqueperceptiva().get(posi).getRespuestaA());
                }
                if (notas.getBloqueperceptiva().get(posi).getRespuestaB().equals("")) {
                    respuestaB.setVisibility(View.GONE);
                } else {
                    respuestaB.setVisibility(View.VISIBLE);
                    respuestaB.setText(notas.getBloqueperceptiva().get(posi).getRespuestaB());
                }
                if (notas.getBloqueperceptiva().get(posi).getRespuestaC().equals("")) {
                    respuestaC.setVisibility(View.GONE);
                    c.setVisibility(View.GONE);
                } else {
                    respuestaC.setVisibility(View.VISIBLE);
                    respuestaC.setText(notas.getBloqueperceptiva().get(posi).getRespuestaC());
                    c.setVisibility(View.VISIBLE);
                }
                if (notas.getBloqueperceptiva().get(posi).getRespuestaD().equals("")) {
                    respuestaD.setVisibility(View.GONE);
                    d.setVisibility(View.GONE);
                } else {
                    respuestaD.setVisibility(View.VISIBLE);
                    respuestaD.setText(notas.getBloqueperceptiva().get(posi).getRespuestaD());
                    d.setVisibility(View.VISIBLE);
                }
                if (notas.getBloqueperceptiva().get(posi).getSolu().equals("")) {
                    solucion.setVisibility(View.GONE);
                } else {
                    solucion.setVisibility(View.VISIBLE);
                    solucion.setText(notas.getBloqueperceptiva().get(posi).getSolu());
                }
                break;
            case 6:
                bloq.setText(getString(R.string.memoria));
                if (notas.getBloquememoria().get(posi).getImgPregunta().equals("")) {
                    imgenPre.setVisibility(View.GONE);
                    imgenPre.setImageResource(0);
                } else {
                    imgenPre.setVisibility(View.VISIBLE);
                    imgenPre.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloquememoria().get(posi).getImgPregunta(), null, getPackageName()));
                }
                if (notas.getBloquememoria().get(posi).getImgA().equals("")) {
                    imgenA.setVisibility(View.GONE);
                    imgenA.setImageResource(0);
                } else {
                    imgenA.setVisibility(View.VISIBLE);
                    imgenA.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloquememoria().get(posi).getImgA(), null, getPackageName()));
                }
                if (notas.getBloquememoria().get(posi).getImgB().equals("")) {
                    imgenB.setVisibility(View.GONE);
                    imgenB.setImageResource(0);
                } else {
                    imgenB.setVisibility(View.VISIBLE);
                    imgenB.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloquememoria().get(posi).getImgB(), null, getPackageName()));
                }
                if (notas.getBloquememoria().get(posi).getImgC().equals("")) {
                    imgenC.setVisibility(View.GONE);
                    imgenC.setImageResource(0);
                } else {
                    imgenC.setVisibility(View.VISIBLE);
                    imgenC.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloquememoria().get(posi).getImgC(), null, getPackageName()));
                }
                if (notas.getBloquememoria().get(posi).getImgD().equals("")) {
                    imgenD.setVisibility(View.GONE);
                    imgenD.setImageResource(0);
                } else {
                    imgenD.setVisibility(View.VISIBLE);
                    imgenD.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloquememoria().get(posi).getImgD(), null, getPackageName()));
                }
                if (notas.getBloquememoria().get(posi).getImgSol().equals("")) {
                    imgenSol.setVisibility(View.GONE);
                    imgenSol.setImageResource(0);
                } else {
                    imgenSol.setVisibility(View.VISIBLE);
                    imgenSol.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloquememoria().get(posi).getImgSol(), null, getPackageName()));
                }
                if (notas.getBloquememoria().get(posi).getImgExpli().equals("")) {
                    imgenExp.setVisibility(View.GONE);
                    imgenExp.setImageResource(0);
                } else {
                    imgenExp.setVisibility(View.VISIBLE);
                    imgenExp.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloquememoria().get(posi).getImgExpli(), null, getPackageName()));
                }
                if (notas.getBloquememoria().get(posi).getExpliSol().equals("")) {
                    explicacion.setVisibility(View.GONE);
                } else {
                    explicacion.setVisibility(View.VISIBLE);
                    explicacion.setText(notas.getBloquememoria().get(posi).getExpliSol());
                }
                if (notas.getBloquememoria().get(posi).getPregunta().equals("")) {
                    imppregunta.setVisibility(View.GONE);
                } else {
                    imppregunta.setVisibility(View.VISIBLE);
                    imppregunta.setText(notas.getBloquememoria().get(posi).getPregunta());
                }
                if (notas.getBloquememoria().get(posi).getRespuestaA().equals("")) {
                    respuestaA.setVisibility(View.GONE);
                } else {
                    respuestaA.setVisibility(View.VISIBLE);
                    respuestaA.setText(notas.getBloquememoria().get(posi).getRespuestaA());
                }
                if (notas.getBloquememoria().get(posi).getRespuestaB().equals("")) {
                    respuestaB.setVisibility(View.GONE);
                } else {
                    respuestaB.setVisibility(View.VISIBLE);
                    respuestaB.setText(notas.getBloquememoria().get(posi).getRespuestaB());
                }
                if (notas.getBloquememoria().get(posi).getRespuestaC().equals("")) {
                    respuestaC.setVisibility(View.GONE);
                    c.setVisibility(View.GONE);
                } else {
                    respuestaC.setVisibility(View.VISIBLE);
                    respuestaC.setText(notas.getBloquememoria().get(posi).getRespuestaC());
                    c.setVisibility(View.VISIBLE);
                }
                if (notas.getBloquememoria().get(posi).getRespuestaD().equals("")) {
                    respuestaD.setVisibility(View.GONE);
                    d.setVisibility(View.GONE);
                } else {
                    respuestaD.setVisibility(View.VISIBLE);
                    respuestaD.setText(notas.getBloquememoria().get(posi).getRespuestaD());
                    d.setVisibility(View.VISIBLE);
                }
                if (notas.getBloquememoria().get(posi).getSolu().equals("")) {
                    solucion.setVisibility(View.GONE);
                } else {
                    solucion.setVisibility(View.VISIBLE);
                    solucion.setText(notas.getBloquememoria().get(posi).getSolu());
                }
                break;
            case 7:
                bloq.setText(getString(R.string.abstrapto));
                if (notas.getBloqueabstrapto().get(posi).getImgPregunta().equals("")) {
                    imgenPre.setVisibility(View.GONE);
                    imgenPre.setImageResource(0);
                } else {
                    imgenPre.setVisibility(View.VISIBLE);
                    imgenPre.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueabstrapto().get(posi).getImgPregunta(), null, getPackageName()));
                }
                if (notas.getBloqueabstrapto().get(posi).getImgA().equals("")) {
                    imgenA.setVisibility(View.GONE);
                    imgenA.setImageResource(0);
                } else {
                    imgenA.setVisibility(View.VISIBLE);
                    imgenA.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueabstrapto().get(posi).getImgA(), null, getPackageName()));
                }
                if (notas.getBloqueabstrapto().get(posi).getImgB().equals("")) {
                    imgenB.setVisibility(View.GONE);
                    imgenB.setImageResource(0);
                } else {
                    imgenB.setVisibility(View.VISIBLE);
                    imgenB.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueabstrapto().get(posi).getImgB(), null, getPackageName()));
                }
                if (notas.getBloqueabstrapto().get(posi).getImgC().equals("")) {
                    imgenC.setVisibility(View.GONE);
                    imgenC.setImageResource(0);
                } else {
                    imgenC.setVisibility(View.VISIBLE);
                    imgenC.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueabstrapto().get(posi).getImgC(), null, getPackageName()));
                }
                if (notas.getBloqueabstrapto().get(posi).getImgD().equals("")) {
                    imgenD.setVisibility(View.GONE);
                    imgenD.setImageResource(0);
                } else {
                    imgenD.setVisibility(View.VISIBLE);
                    imgenD.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueabstrapto().get(posi).getImgD(), null, getPackageName()));
                }
                if (notas.getBloqueabstrapto().get(posi).getImgSol().equals("")) {
                    imgenSol.setVisibility(View.GONE);
                    imgenSol.setImageResource(0);
                } else {
                    imgenSol.setVisibility(View.VISIBLE);
                    imgenSol.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueabstrapto().get(posi).getImgSol(), null, getPackageName()));
                }
                if (notas.getBloqueabstrapto().get(posi).getImgExpli().equals("")) {
                    imgenExp.setVisibility(View.GONE);
                    imgenExp.setImageResource(0);
                } else {
                    imgenExp.setVisibility(View.VISIBLE);
                    imgenExp.setImageResource(getResources().getIdentifier("drawable/" + notas.getBloqueabstrapto().get(posi).getImgExpli(), null, getPackageName()));
                }
                if (notas.getBloqueabstrapto().get(posi).getExpliSol().equals("")) {
                    explicacion.setVisibility(View.GONE);
                } else {
                    explicacion.setVisibility(View.VISIBLE);
                    explicacion.setText(notas.getBloqueabstrapto().get(posi).getExpliSol());
                }
                if (notas.getBloqueabstrapto().get(posi).getPregunta().equals("")) {
                    imppregunta.setVisibility(View.GONE);
                } else {
                    imppregunta.setVisibility(View.VISIBLE);
                    imppregunta.setText(notas.getBloqueabstrapto().get(posi).getPregunta());
                }
                if (notas.getBloqueabstrapto().get(posi).getRespuestaA().equals("")) {
                    respuestaA.setVisibility(View.GONE);
                } else {
                    respuestaA.setVisibility(View.VISIBLE);
                    respuestaA.setText(notas.getBloqueabstrapto().get(posi).getRespuestaA());
                }
                if (notas.getBloqueabstrapto().get(posi).getRespuestaB().equals("")) {
                    respuestaB.setVisibility(View.GONE);
                } else {
                    respuestaB.setVisibility(View.VISIBLE);
                    respuestaB.setText(notas.getBloqueabstrapto().get(posi).getRespuestaB());
                }
                if (notas.getBloqueabstrapto().get(posi).getRespuestaC().equals("")) {
                    respuestaC.setVisibility(View.GONE);
                    c.setVisibility(View.GONE);
                } else {
                    respuestaC.setVisibility(View.VISIBLE);
                    respuestaC.setText(notas.getBloqueabstrapto().get(posi).getRespuestaC());
                    c.setVisibility(View.VISIBLE);
                }
                if (notas.getBloqueabstrapto().get(posi).getRespuestaD().equals("")) {
                    respuestaD.setVisibility(View.GONE);
                    d.setVisibility(View.GONE);
                } else {
                    respuestaD.setVisibility(View.VISIBLE);
                    respuestaD.setText(notas.getBloqueabstrapto().get(posi).getRespuestaD());
                    d.setVisibility(View.VISIBLE);
                }
                if (notas.getBloqueabstrapto().get(posi).getSolu().equals("")) {
                    solucion.setVisibility(View.GONE);
                } else {
                    solucion.setVisibility(View.VISIBLE);
                    solucion.setText(notas.getBloqueabstrapto().get(posi).getSolu());
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
                        notas.getBloqueverbal().get(posi).setRespulsada(1);
                        break;
                    case 2:
                        notas.getBloquenumerico().get(posi).setRespulsada(1);
                        break;
                    case 3:
                        notas.getBloqueespacial().get(posi).setRespulsada(1);
                        break;
                    case 4:
                        notas.getBloquemecanico().get(posi).setRespulsada(1);
                        break;
                    case 5:
                        notas.getBloqueperceptiva().get(posi).setRespulsada(1);
                        break;
                    case 6:
                        notas.getBloquememoria().get(posi).setRespulsada(1);
                        break;
                    case 7:
                        notas.getBloqueabstrapto().get(posi).setRespulsada(1);
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
                        notas.getBloqueverbal().get(posi).setRespulsada(2);
                        break;
                    case 2:
                        notas.getBloquenumerico().get(posi).setRespulsada(2);
                        break;
                    case 3:
                        notas.getBloqueespacial().get(posi).setRespulsada(2);
                        break;
                    case 4:
                        notas.getBloquemecanico().get(posi).setRespulsada(2);
                        break;
                    case 5:
                        notas.getBloqueperceptiva().get(posi).setRespulsada(2);
                        break;
                    case 6:
                        notas.getBloquememoria().get(posi).setRespulsada(2);
                        break;
                    case 7:
                        notas.getBloqueabstrapto().get(posi).setRespulsada(2);
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
                        notas.getBloqueverbal().get(posi).setRespulsada(3);
                        break;
                    case 2:
                        notas.getBloquenumerico().get(posi).setRespulsada(3);
                        break;
                    case 3:
                        notas.getBloqueespacial().get(posi).setRespulsada(3);
                        break;
                    case 4:
                        notas.getBloquemecanico().get(posi).setRespulsada(3);
                        break;
                    case 5:
                        notas.getBloqueperceptiva().get(posi).setRespulsada(3);
                        break;
                    case 6:
                        notas.getBloquememoria().get(posi).setRespulsada(3);
                        break;
                    case 7:
                        notas.getBloqueabstrapto().get(posi).setRespulsada(3);
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
                        notas.getBloqueverbal().get(posi).setRespulsada(4);
                        break;
                    case 2:
                        notas.getBloquenumerico().get(posi).setRespulsada(4);
                        break;
                    case 3:
                        notas.getBloqueespacial().get(posi).setRespulsada(4);
                        break;
                    case 4:
                        notas.getBloquemecanico().get(posi).setRespulsada(4);
                        break;
                    case 5:
                        notas.getBloqueperceptiva().get(posi).setRespulsada(4);
                        break;
                    case 6:
                        notas.getBloquememoria().get(posi).setRespulsada(4);
                        break;
                    case 7:
                        notas.getBloqueabstrapto().get(posi).setRespulsada(4);
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
                    if(acabar==true){
                        a.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                    }else{
                        a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                    }
                    b.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    c.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    d.setBackgroundResource(R.drawable.boton_opt_preguntas);
                } else {
                    if(acabar==true){
                        a.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                    }else{
                        a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                    }
                    b.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    c.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    d.setBackgroundResource(R.drawable.boton_opt_preguntas);
                }
                break;

            case "b":

                if (respuestaB.getText().equals(solucion.getText())) {
                    if(acabar==true){
                        b.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                    }else{
                        b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                    }
                    a.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    c.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    d.setBackgroundResource(R.drawable.boton_opt_preguntas);
                } else {
                    if(acabar==true){
                        b.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                    }else{
                        b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                    }
                    a.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    c.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    d.setBackgroundResource(R.drawable.boton_opt_preguntas);
                }
                break;

            case "c":

                if (respuestaC.getText().equals(solucion.getText())) {
                    if(acabar==true){
                        c.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                    }else{
                        c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                    }
                    a.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    b.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    d.setBackgroundResource(R.drawable.boton_opt_preguntas);
                } else {
                    if(acabar==true){
                        c.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                    }else{
                        c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                    }
                    a.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    b.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    d.setBackgroundResource(R.drawable.boton_opt_preguntas);
                }
                break;

            case "d":

                if (respuestaD.getText().equals(solucion.getText())) {
                    if(acabar==true){
                        d.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                    }else{
                        d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                    }
                    a.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    b.setBackgroundResource(R.drawable.boton_opt_preguntas);
                    c.setBackgroundResource(R.drawable.boton_opt_preguntas);
                } else {
                    if(acabar==true){
                        d.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                    }else{
                        d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                    }
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

        if (posi != 15) {
            limpiarselect();
        }

        switch (bloque) {
            case 1:
                if (notas.getBloqueverbal().get(posi).getRespulsada() != 0) {
                    switch (notas.getBloqueverbal().get(posi).getRespulsada()) {
                        case 1:
                            if (respuestaA.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                            break;
                        case 2:
                            if (respuestaB.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                            break;
                        case 3:
                            if (respuestaC.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                            break;
                        case 4:
                            if (respuestaD.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                    }
                }
                break;
            case 2:
                if (notas.getBloquenumerico().get(posi).getRespulsada() != 0) {
                    switch (notas.getBloquenumerico().get(posi).getRespulsada()) {
                        case 1:
                            if (respuestaA.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                            break;
                        case 2:
                            if (respuestaB.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                            break;
                        case 3:
                            if (respuestaC.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                            break;
                        case 4:
                            if (respuestaD.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                    }
                }
                break;
            case 3:
                if (notas.getBloqueespacial().get(posi).getRespulsada() != 0) {
                    switch (notas.getBloqueespacial().get(posi).getRespulsada()) {
                        case 1:
                            if (respuestaA.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                            break;
                        case 2:
                            if (respuestaB.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                            break;
                        case 3:
                            if (respuestaC.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                            break;
                        case 4:
                            if (respuestaD.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                    }
                }
                break;
            case 4:
                if (notas.getBloquemecanico().get(posi).getRespulsada() != 0) {
                    switch (notas.getBloquemecanico().get(posi).getRespulsada()) {
                        case 1:
                            if (respuestaA.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                            break;
                        case 2:
                            if (respuestaB.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                            break;
                        case 3:
                            if (respuestaC.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                            break;
                        case 4:
                            if (respuestaD.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                    }
                }
                break;
            case 5:
                if (notas.getBloqueperceptiva().get(posi).getRespulsada() != 0) {
                    switch (notas.getBloqueperceptiva().get(posi).getRespulsada()) {
                        case 1:
                            if (respuestaA.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                            break;
                        case 2:
                            if (respuestaB.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                            break;
                        case 3:
                            if (respuestaC.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                            break;
                        case 4:
                            if (respuestaD.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                    }
                }
                break;
            case 6:
                if (notas.getBloquememoria().get(posi).getRespulsada() != 0) {
                    switch (notas.getBloquememoria().get(posi).getRespulsada()) {
                        case 1:
                            if (respuestaA.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                            break;
                        case 2:
                            if (respuestaB.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                            break;
                        case 3:
                            if (respuestaC.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                            break;
                        case 4:
                            if (respuestaD.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                    }
                }
                break;
            case 7:
                if (notas.getBloqueabstrapto().get(posi).getRespulsada() != 0) {
                    switch (notas.getBloqueabstrapto().get(posi).getRespulsada()) {
                        case 1:
                            if (respuestaA.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                            break;
                        case 2:
                            if (respuestaB.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                            break;
                        case 3:
                            if (respuestaC.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                            break;
                        case 4:
                            if (respuestaD.getText().equals(solucion.getText())) {
                                if(acabar == true){
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                                }else{
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            } else {
                                if(acabar == true){
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                                }else{
                                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_exam);
                                }
                            }
                    }
                }
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(pasusaexamen == true){
                if (acabar == false) {

                } else {
                    new AlertDialog.Builder(this)
                            .setIcon(getResources().getDrawable(R.drawable.isalir))
                            .setTitle(getString(R.string.salir))
                            .setCancelable(false)
                            .setMessage(getString(R.string.salir))
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
                }
            }
            return true;

        }
        return super.onKeyDown(keyCode, event);

    }


    private void acabar() {
        siguiente.setEnabled(true);
        acabar = true;
        arregloacabar = true;
        a.setEnabled(false);
        b.setEnabled(false);
        c.setEnabled(false);
        d.setEnabled(false);
        Msolucion.setVisibility(View.VISIBLE);
        ver.setVisibility(View.VISIBLE);
        finish.setVisibility(View.VISIBLE);
        contenedor.setBackgroundColor(Color.parseColor("#E8F0F1"));
        ImageView prohibido = (ImageView) findViewById(R.id.prohibido);
        prohibido.setVisibility(View.VISIBLE);
        TextView cuentatras = (TextView) findViewById(R.id.cuentatras);
        cuentatras.setVisibility(View.GONE);
        prohibido.setImageResource(getResources().getIdentifier("drawable/" + "prohibido", null, getPackageName()));
    }


    private void veropt(){
        final LayoutInflater inflater = this.getLayoutInflater();
        final View textEntryView = inflater.inflate(R.layout.seleccionbloque, null);
        final Button verbal = (Button)textEntryView.findViewById(R.id.verbal);
        final Button numerico = (Button)textEntryView.findViewById(R.id.numerico);
        final Button espacial = (Button)textEntryView.findViewById(R.id.espacial);
        final Button abstracto = (Button)textEntryView.findViewById(R.id.abstracto);
        final Button percectiva = (Button)textEntryView.findViewById(R.id.perceptiva);
        final Button memoria = (Button)textEntryView.findViewById(R.id.memoria);
        final Button  mecanico = (Button)textEntryView.findViewById(R.id.mecanico);
        final AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setView(textEntryView);
        builder2.setIcon(getResources().getDrawable(R.drawable.iexc));
        builder2.setTitle(getString(R.string.atencion)+", seleccione un bloque");
        builder2.setCancelable(false);
        builder2.setPositiveButton("Ir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                imprimir(bloque, posi);
                cuentabloque.setText(bloque + "/7");
                contador.setText((posi + 1) + "/15");
                viewflipper.setInAnimation(animrightalante);
                viewflipper.showPrevious();
            }
        });
        builder2.setNegativeButton("Salir", null).create();

        verbal.setPressed(true);
        numerico.setPressed(true);
        espacial.setPressed(true);
        abstracto.setPressed(true);
        percectiva.setPressed(true);
        memoria.setPressed(true);
        mecanico.setPressed(true);



        verbal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloque = 1;
                posi = 0;
                verbal.setPressed(true);
                numerico.setPressed(true);
                espacial.setPressed(true);
                abstracto.setPressed(true);
                percectiva.setPressed(true);
                memoria.setPressed(true);
                mecanico.setPressed(true);
            }
        });

        numerico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloque = 2;
                posi = 0;
                verbal.setPressed(true);
                numerico.setPressed(true);
                espacial.setPressed(true);
                abstracto.setPressed(true);
                percectiva.setPressed(true);
                memoria.setPressed(true);
                mecanico.setPressed(true);
            }
        });
        espacial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloque = 3;
                posi = 0;
                verbal.setPressed(true);
                numerico.setPressed(true);
                espacial.setPressed(true);
                abstracto.setPressed(true);
                percectiva.setPressed(true);
                memoria.setPressed(true);
                mecanico.setPressed(true);
            }
        });
        abstracto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloque = 7;
                posi = 0;
                verbal.setPressed(true);
                numerico.setPressed(true);
                espacial.setPressed(true);
                abstracto.setPressed(true);
                percectiva.setPressed(true);
                memoria.setPressed(true);
                mecanico.setPressed(true);
            }
        });
        percectiva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloque = 5;
                posi = 0;
                verbal.setPressed(true);
                numerico.setPressed(true);
                espacial.setPressed(true);
                abstracto.setPressed(true);
                percectiva.setPressed(true);
                memoria.setPressed(true);
                mecanico.setPressed(true);
            }
        });
        memoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloque = 6;
                posi = 0;
                verbal.setPressed(true);
                numerico.setPressed(true);
                espacial.setPressed(true);
                abstracto.setPressed(true);
                percectiva.setPressed(true);
                memoria.setPressed(true);
                mecanico.setPressed(true);
            }
        });
        mecanico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloque = 4;
                posi = 0;
                verbal.setPressed(true);
                numerico.setPressed(true);
                espacial.setPressed(true);
                abstracto.setPressed(true);
                percectiva.setPressed(true);
                memoria.setPressed(true);
                mecanico.setPressed(true);
            }
        });
        builder2.show();
    }
}
