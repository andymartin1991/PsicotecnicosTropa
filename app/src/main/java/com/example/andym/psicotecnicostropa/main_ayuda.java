package com.example.andym.psicotecnicostropa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andym on 31/07/2017.
 */

public class main_ayuda extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_ayuda);

        final String[] ayuda = {
                "No puedo actualizar 1",
                "No puedo actualizar 2"
        };
        final String[] textoayuda = {
                "Es posible que se haya quedado guardado en su caché o memoria del “Play Store” la versión anterior, para solucionar esto basta con reiniciar su dispositivo.",
                "Si después de reiniciar su dispositivo aún sigue sin poder actualizar es muy probable que usted tenga más de una cuenta vinculada a su dispositivo, para solucionar esto, valla al “Play Store”, arriba a la izquierda el botón representado por 3 rayas deberás presionarlo y seleccionar otra de las cuentas que usted tiene vinculado, seguido escriba en la barra de búsqueda “Psicotecnicos tropa” sin las comillas, en el resultado de la búsqueda deberá aparecer actualizar, si aún así sigue sin aparecer repita los paso y esta vez selecciona otra cuenta en caso de tenerla. "
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
