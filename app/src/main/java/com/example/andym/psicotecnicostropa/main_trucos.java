package com.example.andym.psicotecnicostropa;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.ViewFlipper;

import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class main_trucos extends Activity {

    static int contPreguntas = 0;
    int mayor = 0;
    //boolean chequear = false;
    boolean sumar = false;
    boolean arreglo = false;
    boolean arreglo2 = false;
    static String quesSelect[];
    ImageView imagen;
    TextView titulo;
    TextView pregunta;
    TextView paginas;
    TextView title;

    Button siguiente;
    Button siguiente2;
    Button anterior;
    ViewFlipper details;
    VideoView video;
    int Contarpreguntas = 0;
    String Cuentapreguntas[];
    String Title[];
    String tipo = "";
    RelativeLayout relativo;
    Animation animrightatras = null;
    Animation animrightalante = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // animaciones
        final Animation animationin = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_in);
        final Animation animationout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_in);

        // fin animaciones

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_trucos);

        LinearLayout padre = (LinearLayout) findViewById(R.id.lineal);
        Calendar cc1 = new GregorianCalendar();
        int dia = cc1.get(Calendar.DAY_OF_MONTH);
        int mes = cc1.get(Calendar.MONTH)+1;
        if( (mes ==11 || mes ==12) || (mes ==1 && dia <=7)){
            padre.setBackgroundResource(R.color.rojonavidad);
        }else{

        }

        titulo = (TextView) findViewById(R.id.textView1);
        paginas = (TextView) findViewById(R.id.paginas);
        siguiente = (Button) findViewById(R.id.siguiente);
        siguiente2 = (Button) findViewById(R.id.siguiente2);
        siguiente2.setVisibility(View.INVISIBLE);
        anterior = (Button) findViewById(R.id.anterior);
        pregunta = (TextView) findViewById(R.id.pregunta);
        title = (TextView) findViewById(R.id.title);
        imagen = (ImageView) findViewById(R.id.imagen);
        details = (ViewFlipper) findViewById(R.id.details);
        video = (VideoView) findViewById(R.id.videoView1);
        relativo = (RelativeLayout) findViewById(R.id.RelativeLayout1);




        titulo.setText("TRUCOS");
        Contarpreguntas = textotruco.preguntas.length;
        Cuentapreguntas = textotruco.preguntas;
        Title = textotruco.title;



        quesSelect = new String[Contarpreguntas];


        // la primera vez que se inicia..........................

        if (contPreguntas > 0) {
            anterior.setVisibility(View.VISIBLE);
        } else {
            anterior.setVisibility(View.INVISIBLE);
        }
        imprimir();


        // ----------------boton siguiente---------------

        siguiente.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if (contPreguntas == Contarpreguntas - 2) {
                        siguiente.setVisibility(View.INVISIBLE);
                        siguiente2.setVisibility(View.INVISIBLE);
                        anterior.setVisibility(View.VISIBLE);
                        contPreguntas = contPreguntas + 1;
                        details.startAnimation(animationin);
                        imprimir();

                    } else {
                        contPreguntas = contPreguntas + 1;
                        details.startAnimation(animationin);
                        if (arreglo == true) {
                            arreglo = false;
                        }

                        if (arreglo2 == true) {

                            arreglo2 = false;
                        }
                        elseboton();
                        imprimir();

                    }
                } catch (Exception e) {
                    siguiente.setVisibility(View.INVISIBLE);
                    siguiente2.setVisibility(View.INVISIBLE);
                    anterior.setVisibility(View.VISIBLE);
                }

            }
        });
        // boton siguiente 2---------------------------
        siguiente2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                try {
                    if (contPreguntas == Contarpreguntas - 2) {
                        siguiente.setVisibility(View.INVISIBLE);
                        siguiente2.setVisibility(View.INVISIBLE);
                        anterior.setVisibility(View.VISIBLE);
                        contPreguntas = contPreguntas + 1;
                        details.startAnimation(animationin);
                        imprimir();

                    } else {
                        details.startAnimation(animationin);
                        elseboton();
                        contPreguntas = contPreguntas + 1;
                        imprimir();
                    }
                } catch (NullPointerException e) {

                    siguiente2.setVisibility(View.INVISIBLE);
                    siguiente.setVisibility(View.VISIBLE);

                }

            }
        });
        // -----------boton anterior---------------------
        anterior.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                siguiente2.setVisibility(View.VISIBLE);
                siguiente.setVisibility(View.INVISIBLE);

                if (contPreguntas == 1) {
                    details.startAnimation(animationout);
                    contPreguntas = contPreguntas - 1;
                    siguiente.setVisibility(View.VISIBLE);
                    anterior.setVisibility(View.INVISIBLE);
                    imprimir();

                } else {
                    details.startAnimation(animationout);
                    if (arreglo == true) {
                        contPreguntas = contPreguntas - 1;
                        arreglo = false;
                        arreglo2 = true;
                    }

                    contPreguntas = contPreguntas - 1;
                    elseboton();
                    imprimir();
                }

            }
            // cargar todo lo anterior en el boton atras

        });

    }

    private void imprimir() {
        imagen.setVisibility(View.GONE);
        if (contPreguntas == 0) {
            anterior.setVisibility(View.INVISIBLE);
        }
        paginas.setText(contPreguntas + 1 + "/" + Contarpreguntas);
        title.setText(Title[contPreguntas]);
        String recurso = "drawable";
        String coger = Cuentapreguntas[contPreguntas];
        String nombre = "";
        String cuestion = "";
        @SuppressWarnings("unused")
        String num = "";
        boolean cerrar = false;
        for (int i = 0; i < coger.length(); i++) {
            char a = coger.charAt(i);
            if (a == '1') {
                num = a + "";
                ;
            }
            if (cerrar == true) {
                if (a != '@') {
                    nombre = nombre + a;
                }
            } else if (a != '@') {
                cuestion = cuestion + a;
            }
            if (a == '@') {
                if (cerrar == false) {
                    cerrar = true;
                } else {
                    cerrar = false;
                }
            }
        }

        if (!nombre.equals("")) {
            int res_imagen = getResources().getIdentifier(nombre, recurso,
                    getPackageName());
            imagen.setVisibility(View.VISIBLE);
            imagen.setImageResource(res_imagen);

        }

        Charset.forName("UTF-8").encode(cuestion);
        pregunta.setText(Html.fromHtml(cuestion));

        final ScrollView scroll = (ScrollView)findViewById(R.id.ScrollView1);
        scroll.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                scroll.post(new Runnable() {
                    public void run() {
                        scroll.fullScroll(View.FOCUS_UP);
                    }
                });
            }
        });


    }

    private void elseboton() {
        siguiente.setVisibility(View.VISIBLE);
        anterior.setVisibility(View.VISIBLE);
    }
}