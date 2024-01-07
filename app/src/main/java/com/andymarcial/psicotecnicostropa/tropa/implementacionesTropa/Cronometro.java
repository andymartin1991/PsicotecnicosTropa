package com.andymarcial.psicotecnicostropa.tropa.implementacionesTropa;

import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.andymarcial.psicotecnicostropa.tropa.main_examen;

/**
 * Created by andym on 28/07/2017.
 */

public class Cronometro implements Runnable
{
    // Atributos privados de la clase
    private static TextView etiq; // Etiqueta para mostrar la información
    private static String nombrecronometro; // Nombre del cronómetro
    private static int segundos;
    private static int minutos;
    private static int horas; // Segundos, minutos y horas que lleva activo el cronómetro
    private static Handler escribirenUI; // Necesario para modificar la UI
    private static Boolean pausado; // Para pausar el cronómetro
    private static String salida; // Salida formateada de los datos del cronómetro

    /**
     * Constructor de la clase
     * @param nombre Nombre del cronómetro
     * @param etiqueta Etiqueta para mostrar información
     */
    public Cronometro(String nombre, TextView etiqueta)
    {
        etiq = etiqueta;
        salida = "";
        segundos = 59;
        minutos = 6;
        horas = 0;
        nombrecronometro = nombre;
        escribirenUI = new Handler();
        pausado = Boolean.FALSE;
    }

    /**
     * Acción del cronómetro, contar tiempo en segundo plano
     */
    public void run()
    {
        try
        {
            while(Boolean.TRUE)
            {
                Thread.sleep(1000);
                salida = "";
                if( !pausado )
                {
                    if(segundos == 0 && minutos == 0){
                        salida = "FIN";
                        pause();
                        main_examen.acabatiempo = true;
                    }else {
                        segundos--;
                        if (segundos == -1) {
                            segundos = 59;
                            minutos--;
                        }
                        if (minutos == -1) {
                            minutos = 59;
                            horas--;
                        }
                        // Formateo la salida
                        salida += "0";
                        salida += horas;
                        salida += ":";
                        if (minutos <= 9) {
                            salida += "0";
                        }
                        salida += minutos;
                        salida += ":";
                        if (segundos <= 9) {
                            salida += "0";
                        }
                        salida += segundos;
                        // Modifico la UI
                    }
                    try
                    {
                        escribirenUI.post(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                etiq.setText(salida);
                            }
                        });
                    }
                    catch (Exception e)
                    {
                        Log.i("Cronometro", "Error en el cronometro " + nombrecronometro + " al escribir en la UI: " + e.toString());

                    }
                }
            }
        }
        catch (InterruptedException e)
        {
            Log.i("Cronometro", "Error en el cronometro " + nombrecronometro + ": " + e.toString());
        }
    }

    public static void reiniciar()
    {
        segundos = 59;
        minutos = 6;
        horas = 0;
        pausado = Boolean.FALSE;
    }
    public static void pause()
    {
        pausado = !pausado;
    }

}