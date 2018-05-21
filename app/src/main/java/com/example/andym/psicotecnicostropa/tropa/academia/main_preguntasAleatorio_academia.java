package com.example.andym.psicotecnicostropa.tropa.academia;


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
import com.example.andym.psicotecnicostropa.dto.contador;
import com.example.andym.psicotecnicostropa.tropa.dtoTropa.Preguntas;
import com.example.andym.psicotecnicostropa.tropa.implementacionesTropa.ImageLoaderA;
import com.example.andym.psicotecnicostropa.tropa.implementacionesTropa.ImageLoaderB;
import com.example.andym.psicotecnicostropa.tropa.implementacionesTropa.ImageLoaderC;
import com.example.andym.psicotecnicostropa.tropa.implementacionesTropa.ImageLoaderD;
import com.example.andym.psicotecnicostropa.tropa.implementacionesTropa.ImageLoaderExpl;
import com.example.andym.psicotecnicostropa.tropa.implementacionesTropa.ImageLoaderPre;
import com.example.andym.psicotecnicostropa.tropa.implementacionesTropa.ImageLoaderSol;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class main_preguntasAleatorio_academia extends Activity {

    int arreglo = 0;
    boolean memoria = false;
    Preguntas[] pre;
    contador cont;
    int[] pos;
    int colocar = 0;

    int tempomemoria = 10000;//10000


    int[] respulsada;

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
                                getString(R.string.aciertos) + " " + aciertos + " " + getString(R.string.de) + " " + (aciertos + fallos) + " " + getString(R.string.de) + " " + pre.length + " " + getString(R.string.preguntas), Toast.LENGTH_SHORT);
                toast1.show();
            }
        });
        guardar.setVisibility(View.INVISIBLE);

        int as = main_academia.objetPreguntas.length;

        int h = 0, cantidad = main_academia.objetPreguntas.length, rango = main_academia.objetPreguntas.length;
        int num[] = new int[cantidad];

        num[h] = (int) (Math.random() * rango);
        for (h = 1; h < cantidad; h++) {
            num[h] = (int) (Math.random() * rango);
            for (int j = 0; j < h; j++) {
                if (num[h] == num[j]) {
                    h--;
                }
            }
        }

        pre = new Preguntas[rango];
        for(int i = 0; i < num.length; i++){
            pre[i] = main_academia.objetPreguntas[num[i]];
        }

        respulsada = new int[pre.length];
        for (int i = 0; i < pre.length; i++) {
            respulsada[i] = 0;
        }

        pos = new int[pre.length];
        for (int i = 0; i < pre.length; i++) {
            pos[i] = 0;
        }


        final Button alante = (Button) findViewById(R.id.alante);
        final Button atras = (Button) findViewById(R.id.atras);
        //limpiarelementos();
        avanza();
        //colocar++;
        recolocar();
        calcularestado();
        atras.setVisibility(View.INVISIBLE);
        alante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (main_preguntas_academia.bpre && main_preguntas_academia.ba && main_preguntas_academia.bb &&
                        main_preguntas_academia.bc && main_preguntas_academia.bd && main_preguntas_academia.bsol && main_preguntas_academia.bexpl) {
                    main_preguntas_academia.limpiaImgUrl();
                    avanza();
                    colocar++;
                    recolocar();
                }
            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (main_preguntas_academia.bpre && main_preguntas_academia.ba && main_preguntas_academia.bb &&
                        main_preguntas_academia.bc && main_preguntas_academia.bd && main_preguntas_academia.bsol && main_preguntas_academia.bexpl) {
                    main_preguntas_academia.limpiaImgUrl();
                    //limpiarelementos();
                    ocultaratras();
                    if (cont.getCont() > 0) {
                        cont.setCont(cont.getCont() - 1);
                        if (arreglo == 1) {
                            cont.setCont(cont.getCont() - 1);
                        }
                        //limpiarelementos();
                        ocultaratras();
                        arreglo = 2;
                        viewflipper.setInAnimation(animrightatras);
                        viewflipper.showPrevious();
                    } else {
                        Toast.makeText(getApplicationContext(), "Fin", Toast.LENGTH_SHORT).show();
                    }
                    limpiarelementos();
                    colocar--;
                    if (colocar == -1) {
                        colocar = 0;
                    }
                    recolocar();
                }
            }
        });

        a = (RelativeLayout) findViewById(R.id.a);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (main_preguntas_academia.bpre && main_preguntas_academia.ba && main_preguntas_academia.bb &&
                        main_preguntas_academia.bc && main_preguntas_academia.bd && main_preguntas_academia.bsol && main_preguntas_academia.bexpl) {
                    String opt = "a";
                    verificarRes(opt);
                    pos[colocar] = 1;

                    calcularestado();
                    if (memoria) {
                        if (cont.getCont() < pre.length && cont.getCont() >= 0) {
                            alante.setVisibility(View.VISIBLE);
                            if (cont.getCont() != 0) {
                                atras.setVisibility(View.VISIBLE);
                                alante.setVisibility(View.VISIBLE);
                            }
                        }
                        if (cont.getCont() - 1 == 0) {
                            atras.setVisibility(View.INVISIBLE);
                        }
                        if (cont.getCont() - 1 == pre.length) {
                            alante.setVisibility(View.INVISIBLE);
                        }
                        memoria = false;
                    }
                }
            }
        });
        b = (RelativeLayout) findViewById(R.id.b);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (main_preguntas_academia.bpre && main_preguntas_academia.ba && main_preguntas_academia.bb &&
                        main_preguntas_academia.bc && main_preguntas_academia.bd && main_preguntas_academia.bsol && main_preguntas_academia.bexpl) {
                    String opt = "b";
                    verificarRes(opt);
                    pos[colocar] = 2;

                    calcularestado();
                    if (memoria) {
                        if (cont.getCont() < pre.length && cont.getCont() >= 0) {
                            alante.setVisibility(View.VISIBLE);
                            if (cont.getCont() != 0) {
                                atras.setVisibility(View.VISIBLE);
                            }
                        }
                        if (cont.getCont() - 1 == 0) {
                            atras.setVisibility(View.INVISIBLE);
                        }
                        if (cont.getCont() - 1 == pre.length) {
                            alante.setVisibility(View.INVISIBLE);
                        }
                        memoria = false;
                    }
                }
            }
        });
        c = (RelativeLayout) findViewById(R.id.c);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (main_preguntas_academia.bpre && main_preguntas_academia.ba && main_preguntas_academia.bb &&
                        main_preguntas_academia.bc && main_preguntas_academia.bd && main_preguntas_academia.bsol && main_preguntas_academia.bexpl) {
                    String opt = "c";
                    verificarRes(opt);
                    pos[colocar] = 3;

                    calcularestado();
                    if (memoria) {
                        if (cont.getCont() < pre.length && cont.getCont() >= 0) {
                            alante.setVisibility(View.VISIBLE);
                            if (cont.getCont() != 0) {
                                atras.setVisibility(View.VISIBLE);
                            }
                        }
                        if (cont.getCont() - 1 == 0) {
                            atras.setVisibility(View.INVISIBLE);
                        }
                        if (cont.getCont() - 1 == pre.length) {
                            alante.setVisibility(View.INVISIBLE);
                        }
                        memoria = false;
                    }
                }
            }
        });
        d = (RelativeLayout) findViewById(R.id.d);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (main_preguntas_academia.bpre && main_preguntas_academia.ba && main_preguntas_academia.bb &&
                        main_preguntas_academia.bc && main_preguntas_academia.bd && main_preguntas_academia.bsol && main_preguntas_academia.bexpl) {
                    String opt = "d";
                    verificarRes(opt);
                    pos[colocar] = 4;

                    calcularestado();
                    if (memoria) {
                        if (cont.getCont() < pre.length && cont.getCont() >= 0) {
                            alante.setVisibility(View.VISIBLE);
                            if (cont.getCont() != 0) {
                                atras.setVisibility(View.VISIBLE);
                            }
                        }
                        if (cont.getCont() - 1 == 0) {
                            atras.setVisibility(View.INVISIBLE);
                        }
                        if (cont.getCont() - 1 == pre.length) {
                            alante.setVisibility(View.INVISIBLE);
                        }
                        memoria = false;
                    }
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
        if (cont.getCont() < pre.length) {
            
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
            if (pre[cont.getCont()-1].getTipo().equals("si") && pos[cont.getCont()-1] == 0) {
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
            if (cont.getCont() == pre.length) {
                alante.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void ocultaratras() {
        Button alante = (Button) findViewById(R.id.alante);
        Button atras = (Button) findViewById(R.id.atras);
        if (cont.getCont() == pre.length) {
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
            imgpregunta.setImageBitmap(null);
            imgpregunta.setVisibility(View.GONE);
            main_preguntas_academia.bpre = true;
        } else {
            final String url = ("http://s593975491.mialojamiento.es/PsicotecnicosTropa/dirAcademias/" + main_academia.idACAM + "/" + pre[cont.getCont()].getImgPregunta() + "");
            new Thread(new Runnable() {
                public void run() {
                    new ImageLoaderPre(imgpregunta).execute(url);
                }
            }).start();
        }
        if (pre[cont.getCont()].getImgA().equals("")) {
            imgeA.setImageBitmap(null);
            imgeA.setVisibility(View.GONE);
            main_preguntas_academia.ba = true;
        } else {
            final String url = ("http://s593975491.mialojamiento.es/PsicotecnicosTropa/dirAcademias/" + main_academia.idACAM + "/" + pre[cont.getCont()].getImgA() + "");
            new Thread(new Runnable() {
                public void run() {
                    new ImageLoaderA(imgeA).execute(url);
                }
            }).start();
        }
        if (pre[cont.getCont()].getImgB().equals("")) {
            imgeB.setImageBitmap(null);
            imgeB.setVisibility(View.GONE);
            main_preguntas_academia.bb = true;
        } else {
            final String url = ("http://s593975491.mialojamiento.es/PsicotecnicosTropa/dirAcademias/" + main_academia.idACAM + "/" + pre[cont.getCont()].getImgB() + "");
            new Thread(new Runnable() {
                public void run() {
                    new ImageLoaderB(imgeB).execute(url);
                }
            }).start();
        }
        if (pre[cont.getCont()].getImgC().equals("")) {
            imgeC.setImageBitmap(null);
            imgeC.setVisibility(View.GONE);
            main_preguntas_academia.bc = true;
        } else {
            final String url = ("http://s593975491.mialojamiento.es/PsicotecnicosTropa/dirAcademias/" + main_academia.idACAM + "/" + pre[cont.getCont()].getImgC() + "");
            new Thread(new Runnable() {
                public void run() {
                    new ImageLoaderC(imgeC).execute(url);
                }
            }).start();
        }
        if (pre[cont.getCont()].getImgD().equals("")) {
            imgeD.setImageBitmap(null);
            imgeD.setVisibility(View.GONE);
            main_preguntas_academia.bd = true;
        } else {
            final String url = ("http://s593975491.mialojamiento.es/PsicotecnicosTropa/dirAcademias/" + main_academia.idACAM + "/" + pre[cont.getCont()].getImgD() + "");
            new Thread(new Runnable() {
                public void run() {
                    new ImageLoaderD(imgeD).execute(url);
                }
            }).start();
        }
        if (pre[cont.getCont()].getImgSol().equals("")) {
            imgesol.setImageBitmap(null);
            imgesol.setVisibility(View.GONE);
            main_preguntas_academia.bsol = true;
        } else {
            final String url = ("http://s593975491.mialojamiento.es/PsicotecnicosTropa/dirAcademias/" + main_academia.idACAM + "/" + pre[cont.getCont()].getImgSol() + "");
            new Thread(new Runnable() {
                public void run() {
                    new ImageLoaderSol(imgesol).execute(url);
                }
            }).start();
        }
        if (pre[cont.getCont()].getImgExpli().equals("")) {
            imgeExpl.setImageBitmap(null);
            imgeExpl.setVisibility(View.GONE);
            main_preguntas_academia.bexpl = true;
        } else {
            final String url = ("http://s593975491.mialojamiento.es/PsicotecnicosTropa/dirAcademias/" + main_academia.idACAM + "/" + pre[cont.getCont()].getImgExpli() + "");
            new Thread(new Runnable() {
                public void run() {
                    new ImageLoaderExpl(imgeExpl).execute(url);
                }
            }).start();
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