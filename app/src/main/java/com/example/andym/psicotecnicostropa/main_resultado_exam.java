package com.example.andym.psicotecnicostropa;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andym.psicotecnicostropa.dto.Preguntas;
import com.example.andym.psicotecnicostropa.dto.Notas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by andym on 08/04/2017.
 */

public class main_resultado_exam extends Activity {

    TextView mostrar, nota, notabar;
    EditText baremo;
    Button calcular, compartir, introbar, guardar;
    Notas notas;
    static Preguntas[] bloqueverbal, bloquenumerico, bloqueespacial, bloquemecanico, bloqueperceptiva, bloquememoria, bloqueabstrapto;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_resultado_exam);

        notas = new Notas();

        final String[] baremorecu = {""};
        try
        {
            File ruta_sd = getExternalFilesDir(null);
            File f = new File(ruta_sd.getAbsolutePath(), "baremo");

            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(f)));
            baremorecu[0] = fin.readLine();
            fin.close();
            System.out.println(ruta_sd);
            System.out.println(f);
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde tarjeta SD");
        }

        baremo = (EditText)findViewById(R.id.baremo);
        baremo.setText(baremorecu[0]);

        int aciertosVerbal = 0;
        int fallosVerbal = 0;
        int sincontestarVerbal = 0;
        for(int i = 0; i<15;i++){
            String respuestacorrecta= bloqueverbal[i].getSolu().substring(0,1);
            String respuestadada = null;
            switch(bloqueverbal[i].getRespulsada()){
                case 1:respuestadada = "A";
                    break;
                case 2:respuestadada = "B";
                    break;
                case 3:respuestadada = "C";
                    break;
                case 4:respuestadada = "D";
                    break;
                default:respuestadada = "M";
            }
            if(respuestadada.equals(respuestacorrecta)){
                aciertosVerbal++;
            }else if(respuestadada.equals("M")){
                sincontestarVerbal++;
            }else{
                fallosVerbal++;
            }
        }
        notas.setAciertosVerbal(aciertosVerbal);
        notas.setFallosVerbal(fallosVerbal);
        notas.setSincontestarVerbal(sincontestarVerbal);

        int aciertosNumerico = 0;
        int fallosNumerico = 0;
        int sincontestarNumerico = 0;
        for(int i = 0; i<15;i++){
            String respuestacorrecta= bloquenumerico[i].getSolu().substring(0,1);
            String respuestadada = null;
            switch(bloquenumerico[i].getRespulsada()){
                case 1:respuestadada = "A";
                    break;
                case 2:respuestadada = "B";
                    break;
                case 3:respuestadada = "C";
                    break;
                case 4:respuestadada = "D";
                    break;
                default:respuestadada = "M";
            }
            if(respuestadada.equals(respuestacorrecta)){
                aciertosNumerico++;
            }else if(respuestadada.equals("M")){
                sincontestarNumerico++;
            }else{
                fallosNumerico++;
            }
        }
        notas.setAciertosNumerico(aciertosNumerico);
        notas.setFallosNumerico(fallosNumerico);
        notas.setSincontestarNumerico(sincontestarNumerico);

        int aciertosEspacial = 0;
        int fallosEspacial = 0;
        int sincontestarEspacial = 0;
        for(int i = 0; i<15;i++){
            String respuestacorrecta= bloqueespacial[i].getSolu().substring(0,1);
            String respuestadada = null;
            switch(bloqueespacial[i].getRespulsada()){
                case 1:respuestadada = "A";
                    break;
                case 2:respuestadada = "B";
                    break;
                case 3:respuestadada = "C";
                    break;
                case 4:respuestadada = "D";
                    break;
                default:respuestadada = "M";
            }
            if(respuestadada.equals(respuestacorrecta)){
                aciertosEspacial++;
            }else if(respuestadada.equals("M")){
                sincontestarEspacial++;
            }else{
                fallosEspacial++;
            }
        }
        notas.setAciertosEspacial(aciertosEspacial);
        notas.setFallosEspacial(fallosEspacial);
        notas.setSincontestarEspacial(sincontestarEspacial);

        int aciertosMecanico = 0;
        int fallosMecanico = 0;
        int sincontestarMecanico = 0;
        for(int i = 0; i<15;i++){
            String respuestacorrecta= bloquemecanico[i].getSolu().substring(0,1);
            String respuestadada = null;
            switch(bloquemecanico[i].getRespulsada()){
                case 1:respuestadada = "A";
                    break;
                case 2:respuestadada = "B";
                    break;
                case 3:respuestadada = "C";
                    break;
                case 4:respuestadada = "D";
                    break;
                default:respuestadada = "M";
            }
            if(respuestadada.equals(respuestacorrecta)){
                aciertosMecanico++;
            }else if(respuestadada.equals("M")){
                sincontestarMecanico++;
            }else{
                fallosMecanico++;
            }
        }
        notas.setAciertosMecanico(aciertosMecanico);
        notas.setFallosMecanico(fallosMecanico);
        notas.setSincontestarMecanico(sincontestarMecanico);

        int aciertosPerceptiva = 0;
        int fallosPerceptiva = 0;
        int sincontestarPerceptiva = 0;
        for(int i = 0; i<15;i++){
            String respuestacorrecta= bloqueperceptiva[i].getSolu().substring(0,1);
            String respuestadada = null;
            switch(bloqueperceptiva[i].getRespulsada()){
                case 1:respuestadada = "A";
                    break;
                case 2:respuestadada = "B";
                    break;
                case 3:respuestadada = "C";
                    break;
                case 4:respuestadada = "D";
                    break;
                default:respuestadada = "M";
            }
            if(respuestadada.equals(respuestacorrecta)){
                aciertosPerceptiva++;
            }else if(respuestadada.equals("M")){
                sincontestarPerceptiva++;
            }else{
                fallosPerceptiva++;
            }
        }
        notas.setAciertosPerceptiva(aciertosPerceptiva);
        notas.setFallosPerceptiva(fallosPerceptiva);
        notas.setSincontestarPerceptiva(sincontestarPerceptiva);

        int aciertosMemoria = 0;
        int fallosMemoria = 0;
        int sincontestarMemoria = 0;
        for(int i = 0; i<15;i++){
            String respuestacorrecta= bloquememoria[i].getSolu().substring(0,1);
            String respuestadada = null;
            switch(bloquememoria[i].getRespulsada()){
                case 1:respuestadada = "A";
                    break;
                case 2:respuestadada = "B";
                    break;
                case 3:respuestadada = "C";
                    break;
                case 4:respuestadada = "D";
                    break;
                default:respuestadada = "M";
            }
            if(respuestadada.equals(respuestacorrecta)){
                aciertosMemoria++;
            }else if(respuestadada.equals("M")){
                sincontestarMemoria++;
            }else{
                fallosMemoria++;
            }
        }
        notas.setAciertosMemoria(aciertosMemoria);
        notas.setFallosMemoria(fallosMemoria);
        notas.setSincontestarMemoria(sincontestarMemoria);

        int aciertosAbstrapto = 0;
        int fallosAbstrapto = 0;
        int sincontestarAbstrapto = 0;
        for(int i = 0; i<15;i++){
            String respuestacorrecta= bloqueabstrapto[i].getSolu().substring(0,1);
            String respuestadada = null;
            switch(bloqueabstrapto[i].getRespulsada()){
                case 1:respuestadada = "A";
                    break;
                case 2:respuestadada = "B";
                    break;
                case 3:respuestadada = "C";
                    break;
                case 4:respuestadada = "D";
                    break;
                default:respuestadada = "M";
            }
            if(respuestadada.equals(respuestacorrecta)){
                aciertosAbstrapto++;
            }else if(respuestadada.equals("M")){
                sincontestarAbstrapto++;
            }else{
                fallosAbstrapto++;
            }
        }
        notas.setAciertosAbstrapto(aciertosAbstrapto);
        notas.setFallosAbstrapto(fallosAbstrapto);
        notas.setSincontestarAbstrapto(sincontestarAbstrapto);

        notas.setNotasobre10
                ((((Double.parseDouble(String.valueOf(aciertosVerbal)) * 10) / 15) +
                        ((Double.parseDouble(String.valueOf(aciertosNumerico)) * 10) / 15) +
                        ((Double.parseDouble(String.valueOf(aciertosEspacial)) * 10) / 15) +
                        ((Double.parseDouble(String.valueOf(aciertosMecanico)) * 10) / 15) +
                        ((Double.parseDouble(String.valueOf(aciertosPerceptiva)) * 10) / 15) +
                        ((Double.parseDouble(String.valueOf(aciertosMemoria)) * 10) / 15) +
                        ((Double.parseDouble(String.valueOf(aciertosAbstrapto)) * 10) / 15)) / 7);
        notas.setNotaredondeada(redondearDecimales(notas.getNotasobre10(),1));
        notabaremo();
        mostrar = (TextView)findViewById(R.id.mostrar);
        mostrar.setText(
                getString(R.string.Verbal)+": "+getString(R.string.aciertos)+" "+aciertosVerbal+", "+getString(R.string.Fallos)+" "+fallosVerbal+" "+getString(R.string.nocontestadas)+" "+sincontestarVerbal+"\n\n"+
                getString(R.string.Numerico)+": "+getString(R.string.aciertos)+" "+aciertosNumerico+", "+getString(R.string.Fallos)+" "+fallosNumerico+" "+getString(R.string.nocontestadas)+" "+sincontestarNumerico+"\n\n"+
                getString(R.string.Espacial)+": "+getString(R.string.aciertos)+" "+aciertosEspacial+", "+getString(R.string.Fallos)+" "+fallosEspacial+" "+getString(R.string.nocontestadas)+" "+sincontestarEspacial+"\n\n"+
                getString(R.string.Mecanico)+": "+getString(R.string.aciertos)+" "+aciertosMecanico+", "+getString(R.string.Fallos)+" "+fallosMecanico+" "+getString(R.string.nocontestadas)+" "+sincontestarMecanico+"\n\n"+
                getString(R.string.Perceptiva)+": "+getString(R.string.aciertos)+" "+aciertosPerceptiva+", "+getString(R.string.Fallos)+" "+fallosPerceptiva+" "+getString(R.string.nocontestadas)+" "+sincontestarPerceptiva+"\n\n"+
                getString(R.string.aptitud6)+": "+getString(R.string.aciertos)+" "+aciertosMemoria+", "+getString(R.string.Fallos)+" "+fallosMemoria+" "+getString(R.string.nocontestadas)+" "+sincontestarMemoria+"\n\n"+
                getString(R.string.Abstrapto)+": "+getString(R.string.aciertos)+" "+aciertosAbstrapto+", "+getString(R.string.Fallos)+" "+fallosAbstrapto+" "+getString(R.string.nocontestadas)+" "+sincontestarAbstrapto

        );


        nota = (TextView)findViewById(R.id.nota);
        nota.setText(getString(R.string.sunotasobre10)+"\n"+ notas.getNotaredondeada());

        notabar = (TextView)findViewById(R.id.notabar);
        try {
            if (!baremorecu[0].equals("")) {
                baremo.setText(baremorecu[0]);
                notas.setBar(Double.parseDouble(baremo.getText().toString()));
                notabaremo();
                notabar.setText(getString(R.string.sunotaconbaremo) + "\n" + notas.getNotaredondeadabar());
            } else {
                notabar.setText(getString(R.string.sunotaconbaremo) + "\n" + "0.0");
            }
        }catch(Exception e){
            notabar.setText(getString(R.string.sunotaconbaremo) + "\n" + "0.0");
        }

        baremo = (EditText)findViewById(R.id.baremo);

        calcular = (Button)findViewById(R.id.calcular);
        calcular.setText(getString(R.string.calcular));
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(baremo.getText().toString().equals("")){
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    getString(R.string.baremonovalido), Toast.LENGTH_SHORT);

                    toast1.show();
                }else {
                    double kk = Double.parseDouble(baremo.getText().toString());
                    if (kk > 0 && kk < 41) {
                        notas.setBar(Double.parseDouble(baremo.getText().toString()));
                        notabaremo();
                        notabar.setText(getString(R.string.sunotaconbaremo) + "\n" + notas.getNotaredondeadabar());
                        try
                        {
                            File ruta_sd = getExternalFilesDir(null);
                            File f = new File(ruta_sd.getAbsolutePath(), "baremo");
                            OutputStreamWriter fout =
                                    new OutputStreamWriter(
                                            new FileOutputStream(f));

                            fout.write(baremo.getText()+"");
                            fout.close();
                            System.out.println(ruta_sd);
                            System.out.println(f);
                        }
                        catch (Exception ex)
                        {
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

        compartir = (Button)findViewById(R.id.compartir);
        compartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(baremo.getText().toString().equals("")){
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    getString(R.string.compartirresul), Toast.LENGTH_SHORT);

                    toast1.show();
                }else {
                    Double kk = Double.parseDouble(baremo.getText().toString());
                    if (kk > 0 && kk < 41) {
                        //poner aqui el texto a compartir
                        Intent intentCompartir = new Intent(Intent.ACTION_SEND);
                        intentCompartir.setType("text/plain");
                        intentCompartir.putExtra(Intent.EXTRA_SUBJECT, "Psicotécnicos Tropa");
                        intentCompartir.putExtra(Intent.EXTRA_STREAM,  Uri.parse("android.resource://com.example.andym.psicotecnicostropa/drawable/ic_launcher"));
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

        introbar = (Button)findViewById(R.id.introbar);
        introbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(main_resultado_exam.this, main_calculabaremo.class));
                overridePendingTransition(R.anim.transpain, R.anim.transpaout);
            }
        });

        guardar = (Button)findViewById(R.id.guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(baremo.getText().toString().equals("")){
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    getString(R.string.guardarresul), Toast.LENGTH_SHORT);

                    toast1.show();
                }else {
                    Double kk = Double.parseDouble(baremo.getText().toString());
                    if (kk > 0 && kk < 41) {
                        if(notas.getNotaredondeadabar()!= 0) {
                            //guardar el objeto
                            // Obtenemos solamente la fecha pero usamos slash en lugar de guiones

                            // Date puede se convertido a String con el método toString()
                            // Usa una sintaxis general del tipo DD MM dd HH:mm:ss
                            Date date = new Date();
                            System.out.println(date.toString());

                            // Se pueden definir formatos diferentes con la clase DateFormat
                            // Obtenemos la fecha y la hora con el formato yyyy-MM-dd HH:mm:ss
                            DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String convertido = fechaHora.format(date);



                            File ruta_sd = getExternalFilesDir(null);
                            File a = new File(ruta_sd.getAbsolutePath(), convertido+"bloqueverbalexamen");
                            File b = new File(ruta_sd.getAbsolutePath(), convertido+"bloquenumericoexamen");
                            File c = new File(ruta_sd.getAbsolutePath(), convertido+"bloqueespacialexamen");
                            File d = new File(ruta_sd.getAbsolutePath(), convertido+"bloquemecanicoexamen");
                            File e = new File(ruta_sd.getAbsolutePath(), convertido+"bloqueperceptivaexamen");
                            File f = new File(ruta_sd.getAbsolutePath(), convertido+"bloquememoriaexamen");
                            File g = new File(ruta_sd.getAbsolutePath(), convertido+"bloqueabstraptoexamen");
                            File h = new File(ruta_sd.getAbsolutePath(), convertido+"notasexamen");
                            try {
                                ObjectOutputStream oosa = new ObjectOutputStream(new FileOutputStream(a));
                                oosa.writeObject(bloqueverbal);
                                oosa.close();
                                ObjectOutputStream oosb = new ObjectOutputStream(new FileOutputStream(b));
                                oosb.writeObject(bloquenumerico);
                                oosb.close();
                                ObjectOutputStream oosc = new ObjectOutputStream(new FileOutputStream(c));
                                oosc.writeObject(bloqueespacial);
                                oosc.close();
                                ObjectOutputStream oosd = new ObjectOutputStream(new FileOutputStream(d));
                                oosd.writeObject(bloquemecanico);
                                oosd.close();
                                ObjectOutputStream oose = new ObjectOutputStream(new FileOutputStream(e));
                                oose.writeObject(bloqueperceptiva);
                                oose.close();
                                ObjectOutputStream oosf = new ObjectOutputStream(new FileOutputStream(f));
                                oosf.writeObject(bloquememoria);
                                oosf.close();
                                ObjectOutputStream oosg = new ObjectOutputStream(new FileOutputStream(g));
                                oosg.writeObject(bloqueabstrapto);
                                oosg.close();
                                ObjectOutputStream oosh = new ObjectOutputStream(new FileOutputStream(h));
                                oosh.writeObject(notas);
                                oosh.close();

                                Toast toast1 =
                                        Toast.makeText(getApplicationContext(), getString(R.string.guardado), Toast.LENGTH_SHORT);
                                toast1.show();
                            } catch (IOException ll) {
                                Toast toast1 =
                                        Toast.makeText(getApplicationContext(),
                                                "Error al guardar su nota", Toast.LENGTH_SHORT);

                                toast1.show();
                            }

                        }else{
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

    }

    public void notabaremo(){
        notas.setBaremoredondeado (((notas.getBar() / 40) * 10) * 0.3);
        double notabaremo = (notas.getNotasobre10() * 0.7);
        double a = redondearDecimales(notas.getBaremoredondeado(),5);
        double b = redondearDecimales(notabaremo,5);
        notas.setNotaredondeadabar(redondearDecimales(a+b,1));
    }
    public static double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
        resultado=Math.round(resultado);
        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
        return resultado;
    }

}
