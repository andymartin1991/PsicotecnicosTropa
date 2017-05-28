package com.example.andym.psicotecnicostropa;


import com.example.andym.psicotecnicostropa.dto.Preguntas;
import com.example.andym.psicotecnicostropa.dto.contador;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;

public class main_preguntas extends Activity {

    int arreglo = 0;

    Preguntas[] pre;
    contador cont = new contador();
    int[] pos;
    int colocar = 0;
    int comienzocarga = 3;

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

        ImageView prohibido = (ImageView) findViewById(R.id.prohibido);
        prohibido.setVisibility(View.GONE);
        TextView cuentatras = (TextView) findViewById(R.id.cuentatras);
        cuentatras.setVisibility(View.GONE);
        TextView bloque = (TextView) findViewById(R.id.bloque);
        kk = (TextView) findViewById(R.id.arreglo);
        kk.setVisibility(View.VISIBLE);
        Msolucion = (LinearLayout) findViewById(R.id.solucion);
        Msolucion.setVisibility(View.GONE);
        guardar = (Button) findViewById(R.id.guardar);
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
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar(getIntent().getExtras().getString("tipo"));
            }
        });
        switch (getIntent().getExtras().getString("tipo")) {
            case "verbal":
                this.setTitle(getString(R.string.verbal));
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
                break;

            case "abstrapto":
                this.setTitle(getString(R.string.abstrapto));
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
                bloque.setText(getString(R.string.abstrapto));
                break;

            case "espacial":
                this.setTitle(getString(R.string.espacial));
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
                bloque.setText(getString(R.string.espacial));
                break;

            case "mecanico":
                this.setTitle(getString(R.string.mecanico));
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
                bloque.setText(getString(R.string.mecanico));
                break;

            case "numerico":
                this.setTitle(getString(R.string.numerico));
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
                bloque.setText(getString(R.string.numerico));
                break;

            case "memoria":
                this.setTitle(getString(R.string.memoria));
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
                bloque.setText(getString(R.string.memoria));
                break;

            case "perceptiva":
                this.setTitle(getString(R.string.perceptiva));
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
                bloque.setText(getString(R.string.perceptiva));
                break;

        }

        bloque.setText(getString(R.string.verbal));
        carga(getIntent().getExtras().getString("tipo"));

        respulsada = new int[pregunta.length];
        for (int i = 0; i < pregunta.length; i++) {
            respulsada[i] = 0;
        }
        if (pos == null) {
            pos = new int[pregunta.length];
        }

        pre = new Preguntas[pregunta.length];
        for (int i = 0; i < pregunta.length; i++) {
            pre[i] = new Preguntas(
                    pregunta[i], resA[i], resB[i], resC[i], resD[i], sol[i], expliSol[i], imgPre[i],
                    imgA[i], imgB[i], imgC[i], imgD[i], imgSol[i], imgExpli[i], respulsada[i]);
        }


        Button alante = (Button) findViewById(R.id.alante);
        Button atras = (Button) findViewById(R.id.atras);

        /*if (cont.getCont() == 1) {
            atras.setVisibility(View.INVISIBLE);
        }*/

        avanza();
        calcularestado();

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

    private void carga(final String tipo) {

        try {
            File ruta_sd = getExternalFilesDir(null);
            final File a = new File(ruta_sd.getAbsolutePath(), tipo + "cont");
            final File b = new File(ruta_sd.getAbsolutePath(), tipo + "aciertos");
            final File c = new File(ruta_sd.getAbsolutePath(), tipo + "fallos");
            final File d = new File(ruta_sd.getAbsolutePath(), tipo + "colocar");
            final File e = new File(ruta_sd.getAbsolutePath(), tipo + "pos");
            final File f = new File(ruta_sd.getAbsolutePath(), tipo + "arreglo");
            if (a.exists() && b.exists() && c.exists() && d.exists() && e.exists()) {

                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle(getString(R.string.atencion))
                        .setMessage(getString(R.string.datosencontrado))
                        .setCancelable(false)
                        .setNegativeButton(getString(R.string.borrar),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {

                                    }
                                })
                        // sin listener
                        .setPositiveButton(getString(R.string.cargar),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        FileReader fr = null;
                                        BufferedReader br = null;
                                        try {
                                            fr = new FileReader(a);
                                            br = new BufferedReader(fr);
                                            String linea;
                                            while((linea=br.readLine())!=null)
                                                if(!linea.isEmpty()){
                                                    if(Integer.parseInt(linea)==0){
                                                        comienzocarga = Integer.parseInt(linea);
                                                    }
                                                    cont.setCont(Integer.parseInt(linea)-1);
                                                }
                                        }
                                        catch(Exception y){
                                            y.printStackTrace();
                                        }finally{
                                            try{
                                                if( null != fr ){
                                                    fr.close();
                                                }
                                            }catch (Exception e2){
                                                e2.printStackTrace();
                                            }
                                        }
                                        try {
                                            fr = new FileReader(b);
                                            br = new BufferedReader(fr);
                                            String linea;
                                            while((linea=br.readLine())!=null)
                                                if(!linea.isEmpty()){
                                                    aciertos=Integer.parseInt(linea);
                                                }
                                        }
                                        catch(Exception y){
                                            y.printStackTrace();
                                        }finally{
                                            try{
                                                if( null != fr ){
                                                    fr.close();
                                                }
                                            }catch (Exception e2){
                                                e2.printStackTrace();
                                            }
                                        }
                                        try {
                                            fr = new FileReader(c);
                                            br = new BufferedReader(fr);
                                            String linea;
                                            while((linea=br.readLine())!=null)
                                                if(!linea.isEmpty()){
                                                    fallos=Integer.parseInt(linea);
                                                }
                                        }
                                        catch(Exception y){
                                            y.printStackTrace();
                                        }finally{
                                            try{
                                                if( null != fr ){
                                                    fr.close();
                                                }
                                            }catch (Exception e2){
                                                e2.printStackTrace();
                                            }
                                        }
                                        try {
                                            fr = new FileReader(d);
                                            br = new BufferedReader(fr);
                                            String linea;
                                            while((linea=br.readLine())!=null)
                                                if(!linea.isEmpty()){
                                                    colocar=Integer.parseInt(linea);
                                                }
                                        }
                                        catch(Exception y){
                                            y.printStackTrace();
                                        }finally{
                                            try{
                                                if( null != fr ){
                                                    fr.close();
                                                }
                                            }catch (Exception e2){
                                                e2.printStackTrace();
                                            }
                                        }
                                        try {
                                            pos= new int[pregunta.length];
                                            fr = new FileReader(e);
                                            br = new BufferedReader(fr);
                                            String linea;
                                            int recorro = 0;
                                            while((linea=br.readLine())!=null)
                                                if(!linea.isEmpty()){
                                                    pos[recorro]=Integer.parseInt(linea);
                                                    recorro++;
                                                }

                                        }
                                        catch(Exception y){
                                            y.printStackTrace();
                                            try{
                                                if( null != fr ){
                                                    fr.close();
                                                }
                                            }catch (Exception e2){
                                                e2.printStackTrace();
                                            }
                                        }
                                        try {
                                            fr = new FileReader(f);
                                            br = new BufferedReader(fr);
                                            String linea;
                                            while((linea=br.readLine())!=null)
                                                if(!linea.isEmpty()){
                                                    arreglo=Integer.parseInt(linea);
                                                }
                                        }
                                        catch(Exception y){
                                            y.printStackTrace();
                                        }finally{
                                            try{
                                                if( null != fr ){
                                                    fr.close();
                                                }
                                            }catch (Exception e2){
                                                e2.printStackTrace();
                                            }
                                        }avanza();
                                        if (comienzocarga != 0) {
                                            recolocar();
                                        }
                                        calcularestado();

                                    }


                                }).show();
            }

            }catch(Exception e){

            }

    }

    private void guardar(String tipo) {
        boolean correcto[] = {true, true, true, true, true, true, true, true,};
        try {
            File ruta_sd = getExternalFilesDir(null);
            File f = new File(ruta_sd.getAbsolutePath(), tipo + "aciertos");
            OutputStreamWriter fout =
                    new OutputStreamWriter(
                            new FileOutputStream(f));

            fout.write(aciertos + "");
            fout.close();
            System.out.println(ruta_sd);
            System.out.println(f);
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
            correcto[0] = false;
        }
        try {
            File ruta_sd = getExternalFilesDir(null);
            File f = new File(ruta_sd.getAbsolutePath(), tipo + "fallos");
            OutputStreamWriter fout =
                    new OutputStreamWriter(
                            new FileOutputStream(f));

            fout.write(fallos + "");
            fout.close();
            System.out.println(ruta_sd);
            System.out.println(f);
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
            correcto[1] = false;
        }
        try {
            File ruta_sd = getExternalFilesDir(null);
            File f = new File(ruta_sd.getAbsolutePath(), tipo + "colocar");
            OutputStreamWriter fout =
                    new OutputStreamWriter(
                            new FileOutputStream(f));

            fout.write(colocar + "");
            fout.close();
            System.out.println(ruta_sd);
            System.out.println(f);
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
            correcto[2] = false;
        }
        try {
            File ruta_sd = getExternalFilesDir(null);
            File f = new File(ruta_sd.getAbsolutePath(), tipo + "cont");
            OutputStreamWriter fout =
                    new OutputStreamWriter(
                            new FileOutputStream(f));

            fout.write((cont.getCont()) + "");
            fout.close();
            System.out.println(ruta_sd);
            System.out.println(f);
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
            correcto[3] = false;
        }
        try {
            File ruta_sd = getExternalFilesDir(null);
            File f = new File(ruta_sd.getAbsolutePath(), tipo + "pos");
            OutputStreamWriter fout =
                    new OutputStreamWriter(
                            new FileOutputStream(f));
            for (int i = 0; i < pos.length; i++) {
                fout.write(pos[i] + "\n");
                fout.flush();
            }
            fout.close();
            System.out.println(ruta_sd);
            System.out.println(f);
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
            correcto[4] = false;
        }
        try {
            File ruta_sd = getExternalFilesDir(null);
            File f = new File(ruta_sd.getAbsolutePath(), tipo + "arreglo");
            OutputStreamWriter fout =
                    new OutputStreamWriter(
                            new FileOutputStream(f));

            fout.write(arreglo + "");
            fout.close();
            System.out.println(ruta_sd);
            System.out.println(f);
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
            correcto[5] = false;
        }

        if (!correcto[0] && !correcto[1] && !correcto[2] && !correcto[3] && !correcto[4]) {
            Toast toast1 = Toast.makeText(getApplicationContext(), getString(R.string.errorguardar), Toast.LENGTH_SHORT);
            toast1.show();
            /*si falla borramos todo los archivos en caso de que exista*/
            try {
                File ruta_sd = getExternalFilesDir(null);
                File a = new File(ruta_sd.getAbsolutePath(), tipo + "cont");
                File b = new File(ruta_sd.getAbsolutePath(), tipo + "aciertos");
                File c = new File(ruta_sd.getAbsolutePath(), tipo + "fallos");
                File d = new File(ruta_sd.getAbsolutePath(), tipo + "colocar");
                File e = new File(ruta_sd.getAbsolutePath(), tipo + "pos");
                a.delete();
                b.delete();
                c.delete();
                d.delete();
                e.delete();
            } catch (Exception e) {
                Toast toast2 = Toast.makeText(getApplicationContext(), getString(R.string.errormemo), Toast.LENGTH_SHORT);
                toast2.show();
                toast2.show();
            }

        } else {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(), getString(R.string.guardado), Toast.LENGTH_SHORT);
            toast1.show();
        }
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

        if (getIntent().getExtras().getString("tipo").equals("memoria")) {
            //PONER AQUI LA FUNCION DE MEMORIA

        }

        if (cont.getCont() == -1) {
            cont.setCont(0);
        }
        if (imgPre[cont.getCont()].equals("")) {
            imgpregunta.setVisibility(View.GONE);
            imgpregunta.setImageResource(0);
        } else {
            imgpregunta.setVisibility(View.VISIBLE);
            imgpregunta.setImageResource(getResources().getIdentifier("drawable/" + imgPre[cont.getCont()], null, getPackageName()));
        }
        if (imgA[cont.getCont()].equals("")) {
            imgeA.setVisibility(View.GONE);
            imgeA.setImageResource(0);
        } else {
            imgeA.setVisibility(View.VISIBLE);
            imgeA.setImageResource(getResources().getIdentifier("drawable/" + imgA[cont.getCont()], null, getPackageName()));
        }
        if (imgB[cont.getCont()].equals("")) {
            imgeB.setVisibility(View.GONE);
            imgeB.setImageResource(0);
        } else {
            imgeB.setVisibility(View.VISIBLE);
            imgeB.setImageResource(getResources().getIdentifier("drawable/" + imgB[cont.getCont()], null, getPackageName()));
        }
        if (imgC[cont.getCont()].equals("")) {
            imgeC.setVisibility(View.GONE);
            imgeC.setImageResource(0);
        } else {
            imgeC.setVisibility(View.VISIBLE);
            imgeC.setImageResource(getResources().getIdentifier("drawable/" + imgC[cont.getCont()], null, getPackageName()));
        }
        if (imgD[cont.getCont()].equals("")) {
            imgeD.setVisibility(View.GONE);
            imgeD.setImageResource(0);
        } else {
            imgeD.setVisibility(View.VISIBLE);
            imgeD.setImageResource(getResources().getIdentifier("drawable/" + imgD[cont.getCont()], null, getPackageName()));
        }
        if (imgSol[cont.getCont()].equals("")) {
            imgesol.setVisibility(View.GONE);
            imgesol.setImageResource(0);
        } else {
            imgesol.setVisibility(View.VISIBLE);
            imgesol.setImageResource(getResources().getIdentifier("drawable/" + imgSol[cont.getCont()], null, getPackageName()));
        }
        if (imgExpli[cont.getCont()].equals("")) {
            imgeExpl.setVisibility(View.GONE);
            imgeExpl.setImageResource(0);
        } else {
            imgeExpl.setVisibility(View.VISIBLE);
            imgeExpl.setImageResource(getResources().getIdentifier("drawable/" + imgExpli[cont.getCont()], null, getPackageName()));
        }
        if (expliSol[cont.getCont()].equals("")) {
            explicacion.setVisibility(View.GONE);
        } else {
            explicacion.setVisibility(View.VISIBLE);
            explicacion.setText(pre[cont.getCont()].getExpliSol());
        }
        if (pregunta[cont.getCont()].equals("")) {
            preguntas.setVisibility(View.GONE);
        } else {
            preguntas.setVisibility(View.VISIBLE);
            preguntas.setText(pre[cont.getCont()].getPregunta());
        }
        if (resA[cont.getCont()].equals("")) {
            respuestaA.setVisibility(View.GONE);
        } else {
            respuestaA.setVisibility(View.VISIBLE);
            respuestaA.setText(pre[cont.getCont()].getRespuestaA());
        }
        if (resB[cont.getCont()].equals("")) {
            respuestaB.setVisibility(View.GONE);
        } else {
            respuestaB.setVisibility(View.VISIBLE);
            respuestaB.setText(pre[cont.getCont()].getRespuestaB());
        }
        if (resC[cont.getCont()].equals("")) {
            respuestaC.setVisibility(View.GONE);
            c.setVisibility(View.GONE);
        } else {
            respuestaC.setVisibility(View.VISIBLE);
            respuestaC.setText(pre[cont.getCont()].getRespuestaC());
            c.setVisibility(View.VISIBLE);
        }
        if (resD[cont.getCont()].equals("")) {
            respuestaD.setVisibility(View.GONE);
            d.setVisibility(View.GONE);
        } else {
            respuestaD.setVisibility(View.VISIBLE);
            respuestaD.setText(pre[cont.getCont()].getRespuestaD());
            d.setVisibility(View.VISIBLE);
        }
        if (sol[cont.getCont()].equals("")) {
            solucion.setVisibility(View.GONE);
        } else {
            solucion.setVisibility(View.VISIBLE);
            solucion.setText(pre[cont.getCont()].getSolu());
        }
    }

}
