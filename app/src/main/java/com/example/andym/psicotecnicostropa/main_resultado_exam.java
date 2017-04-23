package com.example.andym.psicotecnicostropa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andym.psicotecnicostropa.dto.Preguntas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by andym on 08/04/2017.
 */

public class main_resultado_exam extends Activity {

    TextView mostrar, nota, notabar;
    EditText baremo;
    Button calcular, compartir, introbar;
    double bar = 0;
    double notasobre10;
    double notaredondeadabar;
    double baremoredondeado;
    static Preguntas[] bloqueverbal, bloquenumerico, bloqueespacial, bloquemecanico, bloqueperceptiva, bloquememoria, bloqueabstrapto;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_resultado_exam);

        String baremorecu = null;
        try
        {
            File ruta_sd = getExternalFilesDir(null);
            File f = new File(ruta_sd.getAbsolutePath(), "baremo");

            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(f)));
            baremorecu = fin.readLine();
            fin.close();
            System.out.println(ruta_sd);
            System.out.println(f);
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde tarjeta SD");
        }

        baremo = (EditText)findViewById(R.id.baremo);
        baremo.setText(baremorecu);

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

        notasobre10 =
                ((((Double.parseDouble(String.valueOf(aciertosVerbal)) * 10) / 15) +
                        ((Double.parseDouble(String.valueOf(aciertosNumerico)) * 10) / 15) +
                        ((Double.parseDouble(String.valueOf(aciertosEspacial)) * 10) / 15) +
                        ((Double.parseDouble(String.valueOf(aciertosMecanico)) * 10) / 15) +
                        ((Double.parseDouble(String.valueOf(aciertosPerceptiva)) * 10) / 15) +
                        ((Double.parseDouble(String.valueOf(aciertosMemoria)) * 10) / 15) +
                        ((Double.parseDouble(String.valueOf(aciertosAbstrapto)) * 10) / 15)) / 7);
        double notaredondeada = redondearDecimales(notasobre10,1);
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
        nota.setText(getString(R.string.sunotasobre10)+"\n"+notaredondeada);

        notabar = (TextView)findViewById(R.id.notabar);
        if(baremorecu.equals(null)) {
            notabar.setText(getString(R.string.sunotaconbaremo) + "\n" + "0.0");
        }else{
            bar = Double.parseDouble(baremo.getText().toString());
            notabaremo();
            notabar.setText(getString(R.string.sunotaconbaremo) + "\n" + notaredondeadabar);
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
                        bar = Double.parseDouble(baremo.getText().toString());
                        notabaremo();
                        notabar.setText(getString(R.string.sunotaconbaremo) + "\n" + notaredondeadabar);
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
                    int kk = Integer.parseInt(baremo.getText().toString());
                    if (kk > 0 && kk < 41) {
                        //poner aqui el texto a compartir
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

    }

    public void notabaremo(){
        baremoredondeado = ((bar / 40) * 10) * 0.3;
        double notabaremo = (notasobre10 * 0.7);
        double a = redondearDecimales(baremoredondeado,5);
        double b = redondearDecimales(notabaremo,5);
        notaredondeadabar = redondearDecimales(a+b,1);
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
