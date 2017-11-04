package com.example.andym.psicotecnicostropa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by andym on 31/07/2017.
 */

public class main_ayuda extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_ayuda);

        LinearLayout padre = (LinearLayout)findViewById(R.id.padre);
        Calendar c1 = new GregorianCalendar();
        int dia = c1.get(Calendar.DAY_OF_MONTH);
        int mes = c1.get(Calendar.MONTH)+1;
        if( (mes ==11 || mes ==12) || (mes ==1 && dia <=7)){
            padre.setBackgroundResource(R.color.rojonavidad);
        }else{

        }

        final String[] ayuda = {
                "No puedo actualizar 1",
                "No puedo actualizar 2",
                "¿Cómo guardar mi avance en modo estudio?",
                "¿Para qué sirve Evolución?"
        };
        final String[] textoayuda = {
                "Es posible que se haya quedado guardado en su caché o memoria del “Play Store” la versión anterior, para solucionar esto basta con reiniciar su dispositivo.",
                "Si después de reiniciar su dispositivo aún sigue sin poder actualizar es muy probable que usted tenga más de una cuenta vinculada a su dispositivo, para solucionar esto, valla al “Play Store”, arriba a la izquierda el botón representado por 3 rayas deberás presionarlo y seleccionar otra de las cuentas que usted tiene vinculado, seguido escriba en la barra de búsqueda “Psicotecnicos tropa” sin las comillas, en el resultado de la búsqueda deberá aparecer actualizar, si aún así sigue sin aparecer repita los paso y esta vez selecciona otra cuenta en caso de tenerla. ",
                "Mientras estás haciendo los test en modo estudio arriba a la derecha tienes el icono de guardar, deberás pulsar sobre ese icono para guardar su progreso",
                "La funcionalidad de este apartado es el poder ver tu progreso a medidas que vas haciendo simulacros de examen, (ver días y horas de las realizaciones de exámenes, así como ver el examen hecho)"
        };

        ListView list = (ListView)findViewById(R.id.lista);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ayuda);

        list.setAdapter(adaptador);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                AlertDialog.Builder dialogo = new AlertDialog.Builder(main_ayuda.this);
                dialogo.setIcon(R.drawable.ayuda2);
                dialogo.setTitle(ayuda[position]);
                dialogo.setMessage(textoayuda[position]);
                dialogo.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogo1, int id) {

                            }
                        });
                dialogo.show();
            }

        });


    }

}
