package com.example.andym.psicotecnicostropa;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andym.psicotecnicostropa.dto.Preguntas;
import com.example.andym.psicotecnicostropa.dto.contador;

public class main_preguntasAleatorio extends Activity {


    int arreglo = 0;

    Preguntas[] pre;
    contador cont = new contador();
    int[] pos;
    int colocar = 0;

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
    Button guardar;
    int aciertos = 0;
    int fallos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_preguntas);

        ImageView prohibido = (ImageView)findViewById(R.id.prohibido);
        prohibido.setVisibility(View.GONE);
        TextView cuentatras = (TextView)findViewById(R.id.cuentatras);
        cuentatras.setVisibility(View.GONE);
        TextView bloque = (TextView)findViewById(R.id.bloque);
        kk = (TextView)findViewById(R.id.arreglo);
        kk.setVisibility(View.VISIBLE);
        Msolucion = (LinearLayout)findViewById(R.id.solucion);
        Msolucion.setVisibility(View.GONE);
        guardar = (Button) findViewById(R.id.guardar);
        guardar.setVisibility(View.VISIBLE);
        estado = (TextView)findViewById(R.id.estado);
        estado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                getString(R.string.aciertos)+" "+aciertos+" "+getString(R.string.de)+" "+(aciertos+fallos), Toast.LENGTH_SHORT);

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

        int as = a1.length+a2.length+a3.length+a4.length+a5.length+a6.length+a7.length;
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

        System.arraycopy(a1, 0, pregunta, 0, a1.length);
        System.arraycopy(a2, 0, pregunta, a1.length, a2.length);
        System.arraycopy(a3, 0, pregunta, a2.length, a3.length);
        System.arraycopy(a4, 0, pregunta, a3.length, a4.length);
        System.arraycopy(a5, 0, pregunta, a4.length, a5.length);
        System.arraycopy(a6, 0, pregunta, a5.length, a6.length);
        System.arraycopy(a7, 0, pregunta, a6.length, a7.length);

        System.arraycopy(b1, 0, resA, 0, b1.length);
        System.arraycopy(b2, 0, resA, b1.length, b2.length);
        System.arraycopy(b3, 0, resA, b2.length, b3.length);
        System.arraycopy(b4, 0, resA, b3.length, b4.length);
        System.arraycopy(b5, 0, resA, b4.length, b5.length);
        System.arraycopy(b6, 0, resA, b5.length, b6.length);
        System.arraycopy(b7, 0, resA, b6.length, b7.length);

        System.arraycopy(c1, 0, resB, 0, c1.length);
        System.arraycopy(c2, 0, resB, c1.length, c2.length);
        System.arraycopy(c3, 0, resB, c2.length, c3.length);
        System.arraycopy(c4, 0, resB, c3.length, c4.length);
        System.arraycopy(c5, 0, resB, c4.length, c5.length);
        System.arraycopy(c6, 0, resB, c5.length, c6.length);
        System.arraycopy(c7, 0, resB, c6.length, c7.length);

        System.arraycopy(d1, 0, resC, 0, d1.length);
        System.arraycopy(d2, 0, resC, d1.length, d2.length);
        System.arraycopy(d3, 0, resC, d2.length, d3.length);
        System.arraycopy(d4, 0, resC, d3.length, d4.length);
        System.arraycopy(d5, 0, resC, d4.length, d5.length);
        System.arraycopy(d6, 0, resC, d5.length, d6.length);
        System.arraycopy(d7, 0, resC, d6.length, d7.length);

        System.arraycopy(e1, 0, resD, 0, e1.length);
        System.arraycopy(e2, 0, resD, e1.length, e2.length);
        System.arraycopy(e3, 0, resD, e2.length, e3.length);
        System.arraycopy(e4, 0, resD, e3.length, e4.length);
        System.arraycopy(e5, 0, resD, e4.length, e5.length);
        System.arraycopy(e6, 0, resD, e5.length, e6.length);
        System.arraycopy(e7, 0, resD, e6.length, e7.length);

        System.arraycopy(f1, 0, sol, 0, f1.length);
        System.arraycopy(f2, 0, sol, f1.length, f2.length);
        System.arraycopy(f3, 0, sol, f2.length, f3.length);
        System.arraycopy(f4, 0, sol, f3.length, f4.length);
        System.arraycopy(f5, 0, sol, f4.length, f5.length);
        System.arraycopy(f6, 0, sol, f5.length, f6.length);
        System.arraycopy(f7, 0, sol, f6.length, f7.length);

        System.arraycopy(g1, 0, expliSol, 0, g1.length);
        System.arraycopy(g2, 0, expliSol, g1.length, g2.length);
        System.arraycopy(g3, 0, expliSol, g2.length, g3.length);
        System.arraycopy(g4, 0, expliSol, g3.length, g4.length);
        System.arraycopy(g5, 0, expliSol, g4.length, g5.length);
        System.arraycopy(g6, 0, expliSol, g5.length, g6.length);
        System.arraycopy(g7, 0, expliSol, g6.length, g7.length);

        System.arraycopy(h1, 0, imgPre, 0, h1.length);
        System.arraycopy(h2, 0, imgPre, h1.length, h2.length);
        System.arraycopy(h3, 0, imgPre, h2.length, h3.length);
        System.arraycopy(h4, 0, imgPre, h3.length, h4.length);
        System.arraycopy(h5, 0, imgPre, h4.length, h5.length);
        System.arraycopy(h6, 0, imgPre, h5.length, h6.length);
        System.arraycopy(h7, 0, imgPre, h6.length, h7.length);

        System.arraycopy(i1, 0, imgA, 0, i1.length);
        System.arraycopy(i2, 0, imgA, i1.length, i2.length);
        System.arraycopy(i3, 0, imgA, i2.length, i3.length);
        System.arraycopy(i4, 0, imgA, i3.length, i4.length);
        System.arraycopy(i5, 0, imgA, i4.length, i5.length);
        System.arraycopy(i6, 0, imgA, i5.length, i6.length);
        System.arraycopy(i7, 0, imgA, i6.length, i7.length);

        System.arraycopy(j1, 0, imgB, 0, j1.length);
        System.arraycopy(j2, 0, imgB, j1.length, j2.length);
        System.arraycopy(j3, 0, imgB, j2.length, j3.length);
        System.arraycopy(j4, 0, imgB, j3.length, j4.length);
        System.arraycopy(j5, 0, imgB, j4.length, j5.length);
        System.arraycopy(j6, 0, imgB, j5.length, j6.length);
        System.arraycopy(j7, 0, imgB, j6.length, j7.length);

        System.arraycopy(k1, 0, imgC, 0, k1.length);
        System.arraycopy(k2, 0, imgC, k1.length, k2.length);
        System.arraycopy(k3, 0, imgC, k2.length, k3.length);
        System.arraycopy(k4, 0, imgC, k3.length, k4.length);
        System.arraycopy(k5, 0, imgC, k4.length, k5.length);
        System.arraycopy(k6, 0, imgC, k5.length, k6.length);
        System.arraycopy(k7, 0, imgC, k6.length, k7.length);

        System.arraycopy(l1, 0, imgD, 0, l1.length);
        System.arraycopy(l2, 0, imgD, l1.length, l2.length);
        System.arraycopy(l3, 0, imgD, l2.length, l3.length);
        System.arraycopy(l4, 0, imgD, l3.length, l4.length);
        System.arraycopy(l5, 0, imgD, l4.length, l5.length);
        System.arraycopy(l6, 0, imgD, l5.length, l6.length);
        System.arraycopy(l7, 0, imgD, l6.length, l7.length);


        System.arraycopy(m1, 0, imgSol, 0, m1.length);
        System.arraycopy(m2, 0, imgSol, m1.length, m2.length);
        System.arraycopy(m3, 0, imgSol, m2.length, m3.length);
        System.arraycopy(m4, 0, imgSol, m3.length, m4.length);
        System.arraycopy(m5, 0, imgSol, m4.length, m5.length);
        System.arraycopy(m6, 0, imgSol, m5.length, m6.length);
        System.arraycopy(m7, 0, imgSol, m6.length, m7.length);

        System.arraycopy(n1, 0, imgExpli, 0, n1.length);
        System.arraycopy(n2, 0, imgExpli, n1.length, n2.length);
        System.arraycopy(n3, 0, imgExpli, n2.length, n3.length);
        System.arraycopy(n4, 0, imgExpli, n3.length, n4.length);
        System.arraycopy(n5, 0, imgExpli, n4.length, n5.length);
        System.arraycopy(n6, 0, imgExpli, n5.length, n6.length);
        System.arraycopy(n7, 0, imgExpli, n6.length, n7.length);

        int h=0, cantidad=pregunta.length, rango=pregunta.length;
        int num[];
        num = new int[cantidad];

        num[h]=(int)(Math.random()*rango);
        for(h=1; h<cantidad; h++){
            num[h]=(int)(Math.random()*rango);
            for(int j=0; j<h; j++){
                if(num[h]==num[j]){
                    h--;
                }
            }
        }

        respulsada = new int[cantidad];
        for(int i = 0; i<cantidad; i++){
            respulsada[i]=0;
        }

        pos= new int[cantidad];

        pre = new Preguntas[rango];
        for (int i = 0; i < cantidad; i++) {
            pre[i] = new Preguntas(
                    pregunta[num[i]], resA[num[i]], resB[num[i]], resC[num[i]], resD[num[i]], sol[num[i]], expliSol[num[i]], imgPre[num[i]],
                    imgA[num[i]], imgB[num[i]], imgC[num[i]], imgD[num[i]], imgSol[num[i]], imgExpli[num[i]], respulsada[num[i]]);
        }

        Button alante = (Button) findViewById(R.id.alante);
        Button atras = (Button) findViewById(R.id.atras);
        avanza();
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
                    limpiarelementos();
                    ocultaratras();
                    if (arreglo == 1) {
                        cont.setCont(cont.getCont() - 1);
                        limpiarelementos();
                        ocultaratras();
                    }
                    arreglo = 2;
                } else {
                    Toast.makeText(getApplicationContext(), "Fin", Toast.LENGTH_SHORT).show();
                }
                colocar--;
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
            }
        });

    }
    private void calcularestado(){
        double total = (fallos+aciertos);
        double mitad = total/2;
        double mitadsuperior = (total*0.75);
        estado = (TextView)findViewById(R.id.estado);
        if(aciertos < mitad) {
            estado.setBackgroundResource(R.drawable.boton_estado_rojo);
            estado.setText(getString(R.string.Mal));
        }else {
            estado.setBackgroundResource(R.drawable.boton_estado_naranja);
            estado.setText(getString(R.string.Bien));
            if (aciertos >= mitadsuperior){
                estado.setBackgroundResource(R.drawable.boton_estado_verde);
                estado.setText(getString(R.string.Genial));
            }
        }

    }
    private void recolocar(){
        Msolucion = (LinearLayout)findViewById(R.id.solucion);
        RelativeLayout subcontenedor = (RelativeLayout)findViewById(R.id.subcontenedor);
        ScrollView contenedor = (ScrollView)findViewById(R.id.contenedor);
        ImageView prohibido = (ImageView)findViewById(R.id.prohibido);
        if(pos[colocar]!= 0){
            switch(pos[colocar]){
                case 1:if(respuestaA.getText().equals(solucion.getText())){
                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                }else{
                    a.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                }
                    break;
                case 2:if(respuestaB.getText().equals(solucion.getText())){
                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                }else{
                    b.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                }
                    break;
                case 3:if(respuestaC.getText().equals(solucion.getText())){
                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                }else{
                    c.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                }
                    break;
                case 4:if(respuestaD.getText().equals(solucion.getText())){
                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_true);
                }else{
                    d.setBackgroundResource(R.drawable.boton_opt_preguntas_false);
                }
            }
            a.setEnabled(false);
            b.setEnabled(false);
            c.setEnabled(false);
            d.setEnabled(false);
            Msolucion.setVisibility(View.GONE);
            contenedor.setBackgroundColor(Color.parseColor("#E8F0F1"));
            prohibido.setVisibility(View.VISIBLE);
            prohibido.setImageResource(getResources().getIdentifier("drawable/" + "prohibido", null, getPackageName()));
        }else{
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
        Msolucion.setVisibility(View.GONE);
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
                }break;

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
                }break;

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
            limpiarelementos();
            cont.setCont(cont.getCont() + 1);
            ocultaralante();
            if (arreglo == 2) {
                limpiarelementos();
                cont.setCont(cont.getCont() + 1);
                ocultaralante();
            }
            arreglo = 1;
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
        if (cont.getCont() == 0) {
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
        } else {
            respuestaA.setVisibility(View.VISIBLE);
            respuestaA.setText(pre[cont.getCont()].getRespuestaA());
        }
        if (pre[cont.getCont()].getRespuestaB().equals("")) {
            respuestaB.setVisibility(View.GONE);
        } else {
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
            solucion.setVisibility(View.GONE);
            solucion.setText(pre[cont.getCont()].getSolu());
        }
    }

}