package com.example.andym.psicotecnicostropa.tropa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.andym.psicotecnicostropa.R;
import com.example.andym.psicotecnicostropa.tropa.implementacionesTropa.Cronometro;
import com.example.andym.psicotecnicostropa.tropa.dtoTropa.Notas;
import com.example.andym.psicotecnicostropa.tropa.dtoTropa.Preguntas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static android.app.PendingIntent.getActivity;


/**
 * Created by andym on 07/04/2017.
 */

public class main_examen extends Activity {

    Animation animrightatras = null;
    Animation animrightalante = null;

    boolean pasusaexamen = false;
    Thread hilo;
    Cronometro cronometro;

    public static boolean acabatiempo = false;

    static boolean acabar = false;
    static boolean guardado = false;

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

    static int nverb[];
    static int nnume[];
    static int nespa[];
    static int nmeca[];
    static int nper[];
    static int nmemo[];
    static int nabst[];

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

        pasusaexamen = true;
        acabatiempo = false;
        acabar = false;
        guardado = false;
        bloque = 1;
        posi = 0;
        memoria = 10000;//10000
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
                Intent resultado = new Intent(main_examen.this, main_resultado_exam.class);
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
        int h = 0, cantidad = 16, rango = pregunta.length;
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
        cantidad = 16;
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
        cantidad = 16;
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
        cantidad = 16;
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
        cantidad = 16;
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
        cantidad = 16;
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
        cantidad = 16;
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


        notas = new Notas();
        List<Preguntas> pregu = new ArrayList<Preguntas>();
        for (int i = 0; i < 16; i++) {
            Preguntas preguntas = new Preguntas();
            preguntas.setPregunta(main_resultado_exam.bloqueverbal[nverb[i]].getPregunta());
            preguntas.setRespuestaA((main_resultado_exam.bloqueverbal[nverb[i]].getRespuestaA()));
            preguntas.setRespuestaB((main_resultado_exam.bloqueverbal[nverb[i]].getRespuestaB()));
            preguntas.setRespuestaC((main_resultado_exam.bloqueverbal[nverb[i]].getRespuestaC()));
            preguntas.setRespuestaD((main_resultado_exam.bloqueverbal[nverb[i]].getRespuestaD()));
            preguntas.setSolu((main_resultado_exam.bloqueverbal[nverb[i]].getSolu()));
            preguntas.setExpliSol((main_resultado_exam.bloqueverbal[nverb[i]].getExpliSol()));
            preguntas.setImgPregunta((main_resultado_exam.bloqueverbal[nverb[i]].getImgPregunta()));
            preguntas.setImgA((main_resultado_exam.bloqueverbal[nverb[i]].getImgA()));
            preguntas.setImgB((main_resultado_exam.bloqueverbal[nverb[i]].getImgB()));
            preguntas.setImgC((main_resultado_exam.bloqueverbal[nverb[i]].getImgC()));
            preguntas.setImgD((main_resultado_exam.bloqueverbal[nverb[i]].getImgD()));
            preguntas.setImgSol((main_resultado_exam.bloqueverbal[nverb[i]].getImgSol()));
            preguntas.setImgExpli((main_resultado_exam.bloqueverbal[nverb[i]].getImgExpli()));
            preguntas.setRespulsada((main_resultado_exam.bloqueverbal[nverb[i]].getRespulsada()));
            pregu.add(preguntas);
        }
        notas.setBloqueverbal(pregu);

        pregu = new ArrayList<Preguntas>();
        for (int i = 0; i < 16; i++) {
            Preguntas preguntas = new Preguntas();
            preguntas.setPregunta(main_resultado_exam.bloquenumerico[nnume[i]].getPregunta());
            preguntas.setRespuestaA((main_resultado_exam.bloquenumerico[nnume[i]].getRespuestaA()));
            preguntas.setRespuestaB((main_resultado_exam.bloquenumerico[nnume[i]].getRespuestaB()));
            preguntas.setRespuestaC((main_resultado_exam.bloquenumerico[nnume[i]].getRespuestaC()));
            preguntas.setRespuestaD((main_resultado_exam.bloquenumerico[nnume[i]].getRespuestaD()));
            preguntas.setSolu((main_resultado_exam.bloquenumerico[nnume[i]].getSolu()));
            preguntas.setExpliSol((main_resultado_exam.bloquenumerico[nnume[i]].getExpliSol()));
            preguntas.setImgPregunta((main_resultado_exam.bloquenumerico[nnume[i]].getImgPregunta()));
            preguntas.setImgA((main_resultado_exam.bloquenumerico[nnume[i]].getImgA()));
            preguntas.setImgB((main_resultado_exam.bloquenumerico[nnume[i]].getImgB()));
            preguntas.setImgC((main_resultado_exam.bloquenumerico[nnume[i]].getImgC()));
            preguntas.setImgD((main_resultado_exam.bloquenumerico[nnume[i]].getImgD()));
            preguntas.setImgSol((main_resultado_exam.bloquenumerico[nnume[i]].getImgSol()));
            preguntas.setImgExpli((main_resultado_exam.bloquenumerico[nnume[i]].getImgExpli()));
            preguntas.setRespulsada((main_resultado_exam.bloquenumerico[nnume[i]].getRespulsada()));
            pregu.add(preguntas);
        }
        notas.setBloquenumerico(pregu);

        pregu = new ArrayList<Preguntas>();
        for (int i = 0; i < 16; i++) {
            Preguntas preguntas = new Preguntas();
            preguntas.setPregunta(main_resultado_exam.bloqueabstrapto[nabst[i]].getPregunta());
            preguntas.setRespuestaA((main_resultado_exam.bloqueabstrapto[nabst[i]].getRespuestaA()));
            preguntas.setRespuestaB((main_resultado_exam.bloqueabstrapto[nabst[i]].getRespuestaB()));
            preguntas.setRespuestaC((main_resultado_exam.bloqueabstrapto[nabst[i]].getRespuestaC()));
            preguntas.setRespuestaD((main_resultado_exam.bloqueabstrapto[nabst[i]].getRespuestaD()));
            preguntas.setSolu((main_resultado_exam.bloqueabstrapto[nabst[i]].getSolu()));
            preguntas.setExpliSol((main_resultado_exam.bloqueabstrapto[nabst[i]].getExpliSol()));
            preguntas.setImgPregunta((main_resultado_exam.bloqueabstrapto[nabst[i]].getImgPregunta()));
            preguntas.setImgA((main_resultado_exam.bloqueabstrapto[nabst[i]].getImgA()));
            preguntas.setImgB((main_resultado_exam.bloqueabstrapto[nabst[i]].getImgB()));
            preguntas.setImgC((main_resultado_exam.bloqueabstrapto[nabst[i]].getImgC()));
            preguntas.setImgD((main_resultado_exam.bloqueabstrapto[nabst[i]].getImgD()));
            preguntas.setImgSol((main_resultado_exam.bloqueabstrapto[nabst[i]].getImgSol()));
            preguntas.setImgExpli((main_resultado_exam.bloqueabstrapto[nabst[i]].getImgExpli()));
            preguntas.setRespulsada((main_resultado_exam.bloqueabstrapto[nabst[i]].getRespulsada()));
            pregu.add(preguntas);
        }
        notas.setBloqueabstrapto(pregu);

        pregu = new ArrayList<Preguntas>();
        for (int i = 0; i < 16; i++) {
            Preguntas preguntas = new Preguntas();
            preguntas.setPregunta(main_resultado_exam.bloquemecanico[nmeca[i]].getPregunta());
            preguntas.setRespuestaA((main_resultado_exam.bloquemecanico[nmeca[i]].getRespuestaA()));
            preguntas.setRespuestaB((main_resultado_exam.bloquemecanico[nmeca[i]].getRespuestaB()));
            preguntas.setRespuestaC((main_resultado_exam.bloquemecanico[nmeca[i]].getRespuestaC()));
            preguntas.setRespuestaD((main_resultado_exam.bloquemecanico[nmeca[i]].getRespuestaD()));
            preguntas.setSolu((main_resultado_exam.bloquemecanico[nmeca[i]].getSolu()));
            preguntas.setExpliSol((main_resultado_exam.bloquemecanico[nmeca[i]].getExpliSol()));
            preguntas.setImgPregunta((main_resultado_exam.bloquemecanico[nmeca[i]].getImgPregunta()));
            preguntas.setImgA((main_resultado_exam.bloquemecanico[nmeca[i]].getImgA()));
            preguntas.setImgB((main_resultado_exam.bloquemecanico[nmeca[i]].getImgB()));
            preguntas.setImgC((main_resultado_exam.bloquemecanico[nmeca[i]].getImgC()));
            preguntas.setImgD((main_resultado_exam.bloquemecanico[nmeca[i]].getImgD()));
            preguntas.setImgSol((main_resultado_exam.bloquemecanico[nmeca[i]].getImgSol()));
            preguntas.setImgExpli((main_resultado_exam.bloquemecanico[nmeca[i]].getImgExpli()));
            preguntas.setRespulsada((main_resultado_exam.bloquemecanico[nmeca[i]].getRespulsada()));
            pregu.add(preguntas);
        }
        notas.setBloquemecanico(pregu);

        pregu = new ArrayList<Preguntas>();
        for (int i = 0; i < 16; i++) {
            Preguntas preguntas = new Preguntas();
            preguntas.setPregunta(main_resultado_exam.bloquememoria[nmemo[i]].getPregunta());
            preguntas.setRespuestaA((main_resultado_exam.bloquememoria[nmemo[i]].getRespuestaA()));
            preguntas.setRespuestaB((main_resultado_exam.bloquememoria[nmemo[i]].getRespuestaB()));
            preguntas.setRespuestaC((main_resultado_exam.bloquememoria[nmemo[i]].getRespuestaC()));
            preguntas.setRespuestaD((main_resultado_exam.bloquememoria[nmemo[i]].getRespuestaD()));
            preguntas.setSolu((main_resultado_exam.bloquememoria[nmemo[i]].getSolu()));
            preguntas.setExpliSol((main_resultado_exam.bloquememoria[nmemo[i]].getExpliSol()));
            preguntas.setImgPregunta((main_resultado_exam.bloquememoria[nmemo[i]].getImgPregunta()));
            preguntas.setImgA((main_resultado_exam.bloquememoria[nmemo[i]].getImgA()));
            preguntas.setImgB((main_resultado_exam.bloquememoria[nmemo[i]].getImgB()));
            preguntas.setImgC((main_resultado_exam.bloquememoria[nmemo[i]].getImgC()));
            preguntas.setImgD((main_resultado_exam.bloquememoria[nmemo[i]].getImgD()));
            preguntas.setImgSol((main_resultado_exam.bloquememoria[nmemo[i]].getImgSol()));
            preguntas.setImgExpli((main_resultado_exam.bloquememoria[nmemo[i]].getImgExpli()));
            preguntas.setRespulsada((main_resultado_exam.bloquememoria[nmemo[i]].getRespulsada()));
            pregu.add(preguntas);
        }
        notas.setBloquememoria(pregu);

        pregu = new ArrayList<Preguntas>();
        for (int i = 0; i < 16; i++) {
            Preguntas preguntas = new Preguntas();
            preguntas.setPregunta(main_resultado_exam.bloqueperceptiva[nper[i]].getPregunta());
            preguntas.setRespuestaA((main_resultado_exam.bloqueperceptiva[nper[i]].getRespuestaA()));
            preguntas.setRespuestaB((main_resultado_exam.bloqueperceptiva[nper[i]].getRespuestaB()));
            preguntas.setRespuestaC((main_resultado_exam.bloqueperceptiva[nper[i]].getRespuestaC()));
            preguntas.setRespuestaD((main_resultado_exam.bloqueperceptiva[nper[i]].getRespuestaD()));
            preguntas.setSolu((main_resultado_exam.bloqueperceptiva[nper[i]].getSolu()));
            preguntas.setExpliSol((main_resultado_exam.bloqueperceptiva[nper[i]].getExpliSol()));
            preguntas.setImgPregunta((main_resultado_exam.bloqueperceptiva[nper[i]].getImgPregunta()));
            preguntas.setImgA((main_resultado_exam.bloqueperceptiva[nper[i]].getImgA()));
            preguntas.setImgB((main_resultado_exam.bloqueperceptiva[nper[i]].getImgB()));
            preguntas.setImgC((main_resultado_exam.bloqueperceptiva[nper[i]].getImgC()));
            preguntas.setImgD((main_resultado_exam.bloqueperceptiva[nper[i]].getImgD()));
            preguntas.setImgSol((main_resultado_exam.bloqueperceptiva[nper[i]].getImgSol()));
            preguntas.setImgExpli((main_resultado_exam.bloqueperceptiva[nper[i]].getImgExpli()));
            preguntas.setRespulsada((main_resultado_exam.bloqueperceptiva[nper[i]].getRespulsada()));
            pregu.add(preguntas);
        }
        notas.setBloqueperceptiva(pregu);

        pregu = new ArrayList<Preguntas>();
        for (int i = 0; i < 16; i++) {
            Preguntas preguntas = new Preguntas();
            preguntas.setPregunta(main_resultado_exam.bloqueespacial[nespa[i]].getPregunta());
            preguntas.setRespuestaA((main_resultado_exam.bloqueespacial[nespa[i]].getRespuestaA()));
            preguntas.setRespuestaB((main_resultado_exam.bloqueespacial[nespa[i]].getRespuestaB()));
            preguntas.setRespuestaC((main_resultado_exam.bloqueespacial[nespa[i]].getRespuestaC()));
            preguntas.setRespuestaD((main_resultado_exam.bloqueespacial[nespa[i]].getRespuestaD()));
            preguntas.setSolu((main_resultado_exam.bloqueespacial[nespa[i]].getSolu()));
            preguntas.setExpliSol((main_resultado_exam.bloqueespacial[nespa[i]].getExpliSol()));
            preguntas.setImgPregunta((main_resultado_exam.bloqueespacial[nespa[i]].getImgPregunta()));
            preguntas.setImgA((main_resultado_exam.bloqueespacial[nespa[i]].getImgA()));
            preguntas.setImgB((main_resultado_exam.bloqueespacial[nespa[i]].getImgB()));
            preguntas.setImgC((main_resultado_exam.bloqueespacial[nespa[i]].getImgC()));
            preguntas.setImgD((main_resultado_exam.bloqueespacial[nespa[i]].getImgD()));
            preguntas.setImgSol((main_resultado_exam.bloqueespacial[nespa[i]].getImgSol()));
            preguntas.setImgExpli((main_resultado_exam.bloqueespacial[nespa[i]].getImgExpli()));
            preguntas.setRespulsada((main_resultado_exam.bloqueespacial[nespa[i]].getRespulsada()));
            pregu.add(preguntas);
        }
        notas.setBloqueespacial(pregu);


        cuentabloque = (TextView) findViewById(R.id.cuentabloque);
        cuentabloque.setVisibility(View.VISIBLE);
        cuentabloque.setText("1/7");

        cuentatras = (TextView) findViewById(R.id.cuentatras);
        cuentatras.setVisibility(View.VISIBLE);

        cuentatras.setText("00:07:00");

        cronometro = new Cronometro("Cronometro por bloque", cuentatras);
        hilo = new Thread(cronometro);
        hilo.start();
        Cronometro.reiniciar();
        seacabotiempo();

        contador = (TextView) findViewById(R.id.conta);
        contador.setText("1/15");
        imprimir(bloque, posi);
        siguiente = (Button) findViewById(R.id.alante);
        viewflipper = (ViewFlipper) findViewById(R.id.ViewFlipper1);
        viewflipper.setInAnimation(animrightalante);
        viewflipper.showPrevious();
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bloque == 7 && posi == 14) {
                    if (arregloacabar == false) {
                        Cronometro.pause();
                        siguiente.setEnabled(false);
                        new AlertDialog.Builder(main_examen.this)
                                .setIcon(getResources().getDrawable(R.drawable.iexc))
                                .setTitle(getString(R.string.atencion))
                                .setMessage(getString(R.string.bloqueterminado))
                                .setCancelable(false)
                                .setNegativeButton(getString(R.string.revisar), new DialogInterface.OnClickListener() {
                                    @SuppressWarnings("deprecation")
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        siguiente.setEnabled(true);
                                        posi--;
                                        Cronometro.pause();
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
                        viewflipper.setInAnimation(animrightalante);
                        viewflipper.showPrevious();
                    } else {

                        Cronometro.pause();
                        siguiente.setEnabled(false);
                        new AlertDialog.Builder(main_examen.this)
                                .setIcon(getResources().getDrawable(R.drawable.iexc))
                                .setTitle(getString(R.string.atencion))
                                .setMessage(getString(R.string.bloqueterminado))
                                .setCancelable(false)
                                .setNegativeButton(getString(R.string.revisar), new DialogInterface.OnClickListener() {
                                    @SuppressWarnings("deprecation")
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        posi--;
                                        Cronometro.pause();
                                        siguiente.setEnabled(true);
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
                                            Cronometro.reiniciar();
                                            memoria();
                                            cuentatras.setText("00:07:00");
                                        }
                                        viewflipper.setInAnimation(animrightalante);
                                        viewflipper.showPrevious();
                                        limpiarselect();
                                    }
                                }).create().show();
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
                        viewflipper.setInAnimation(animrightalante);
                        viewflipper.showPrevious();
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
                        viewflipper.setInAnimation(animrightatras);
                        viewflipper.showPrevious();
                    }
                }
            }
        });
        notas.setNverb(nverb);
        notas.setNnume(nnume);
        notas.setNmeca(nmeca);
        notas.setNabst(nabst);
        notas.setNespa(nespa);
        notas.setNper(nper);
        notas.setNmemo(nmemo);
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
                    Cronometro.pause();
                    new AlertDialog.Builder(this)
                            .setIcon(R.drawable.pause)
                            .setTitle(getString(R.string.Pausa))
                            .setCancelable(false)
                            .setMessage(getString(R.string.pausa))
                            .setNegativeButton(getString(R.string.continuar), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    Cronometro.pause();
                                }
                            })
                            .setPositiveButton(getString(R.string.salir),
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog,
                                                            int which) {
                                            System.exit(0);
                                        }
                                    }).show();

                } else {
                    new AlertDialog.Builder(this)
                            .setIcon(getResources().getDrawable(R.drawable.isalir))
                            .setTitle(getString(R.string.salir))
                            .setCancelable(false)
                            .setMessage(getString(R.string.saliractivity))
                            .setNegativeButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    System.exit(0);
                                }
                            })
                            .setPositiveButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {

                                }
                            }).show();
                }
            } else{

                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                getString(R.string.nopausa), Toast.LENGTH_SHORT);
                toast1.show();
                pasusaexamen = false;
            }
            return true;

        }
        return super.onKeyDown(keyCode, event);

    }

    Runnable runnable = new Runnable() {

        @Override
        public void run() {

            TextView pregunta = (TextView) findViewById(R.id.pregunta);
            ImageView imgpre = (ImageView) findViewById(R.id.imgpre);

            if (notas.getBloquememoria().get(posi).getRespuestaA().equals("")) {
                respuestaA.setVisibility(View.GONE);
                a.setVisibility(View.GONE);
            } else {
                respuestaA.setVisibility(View.VISIBLE);
                respuestaA.setText(notas.getBloquememoria().get(posi).getRespuestaA());
                a.setVisibility(View.VISIBLE);
            }
            if (notas.getBloquememoria().get(posi).getRespuestaB().equals("")) {
                respuestaB.setVisibility(View.GONE);
                b.setVisibility(View.GONE);
            } else {
                respuestaB.setVisibility(View.VISIBLE);
                respuestaB.setText(notas.getBloquememoria().get(posi).getRespuestaB());
                b.setVisibility(View.VISIBLE);
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


            imgpre.setVisibility(View.GONE);
            pregunta.setVisibility(View.VISIBLE);
            viewflipper.setInAnimation(animrightalante);
            viewflipper.showPrevious();
            pasusaexamen = true;

            Cronometro.pause();
            seacabotiempo();
        }
    };

    public void esperarYCerrar(final int milisegundos) {

        final Handler handler = new Handler();

        Thread t = new Thread() {
            public void run() {
                try {
                    Cronometro.pause();
                    sleep(milisegundos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(runnable);
            }
        };

        t.start();

    }


    private void memoria() {
        if (bloque == 6 && !acabar && posi < 15) {
            TextView pregunta = (TextView) findViewById(R.id.pregunta);
            ImageView imgpre = (ImageView) findViewById(R.id.imgpre);
            Button atras = (Button) findViewById(R.id.atras);
            Button alante = (Button) findViewById(R.id.alante);
            a.setVisibility(View.GONE);
            b.setVisibility(View.GONE);
            c.setVisibility(View.GONE);
            d.setVisibility(View.GONE);
            imgpre.setVisibility(View.VISIBLE);
            pregunta.setVisibility(View.GONE);
            atras.setVisibility(View.INVISIBLE);
            alante.setVisibility(View.INVISIBLE);
            pasusaexamen = false;
            esperarYCerrar(memoria);
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
            //th = null;
        }
        acabar = true;
        arregloacabar = true;
        a.setEnabled(false);
        b.setEnabled(false);
        c.setEnabled(false);
        d.setEnabled(false);
        Msolucion.setVisibility(View.VISIBLE);
        contenedor.setBackgroundColor(Color.parseColor("#E8F0F1"));
        ImageView prohibido = (ImageView) findViewById(R.id.prohibido);
        prohibido.setVisibility(View.VISIBLE);
        ver.setVisibility(View.VISIBLE);
        finish.setVisibility(View.VISIBLE);
        TextView cuentatras = (TextView) findViewById(R.id.cuentatras);
        cuentatras.setVisibility(View.GONE);
        prohibido.setImageResource(getResources().getIdentifier("drawable/" + "prohibido", null, getPackageName()));
    }


    public void seacabotiempo() {

        final Handler handler = new Handler();

        Thread t = new Thread() {
            public void run() {
                try {
                    boolean kk = true;
                    while(kk == true) {
                        sleep(10);
                        if (acabatiempo == true) {
                            handler.post(seacabo);
                            kk = false;
                            acabatiempo = false;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        };

        t.start();

    }

    Runnable seacabo = new Runnable() {

        @Override
        public void run() {

            AlertDialog.Builder builder = new AlertDialog.Builder(main_examen.this);
            builder
                    .setIcon(getResources().getDrawable(R.drawable.iexc))
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
                            Cronometro.reiniciar();
                            limpiarselect();
                            seacabotiempo();
                            memoria();
                            cuentatras.setText("00:07:00");
                        }
                        }
                    }).create().show();

        }
    };

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
