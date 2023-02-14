package com.example.andym.nttdata.tropa;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andym.nttdata.R;

/**
 * Created by xe63008 on 26/04/2017.
 */

public class main_contacto extends Activity {
    String Easunto;
    String Emensaje = "";
    EditText mensaje;
    Button btnEnviarEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_contacto);
        final String[] items = {getString(R.string.asunto1), getString(R.string.asunto2), getString(R.string.asunto3), getString(R.string.asunto4), getString(R.string.asunto5)};

        /*RelativeLayout padre = (RelativeLayout)findViewById(R.id.padre);
        Calendar c1 = new GregorianCalendar();
        int dia = c1.get(Calendar.DAY_OF_MONTH);
        int mes = c1.get(Calendar.MONTH)+1;
        if( (mes ==11 || mes ==12) || (mes ==1 && dia <=7)){
            padre.setBackgroundResource(R.color.rojonavidad);
        }else{

        }*/

        TextView tmensaje;
        TextView tasunto;
        Spinner asunto;

        mensaje = (EditText) findViewById(R.id.mensaje);
        tmensaje = (TextView) findViewById(R.id.tmensaje);
        tasunto = (TextView) findViewById(R.id.tasunto);
        asunto = (Spinner) findViewById(R.id.asunto);
        btnEnviarEmail = (Button) findViewById(R.id.btnEnviarEmail);
        tmensaje.setText(getString(R.string.Cmensaje));
        tasunto.setText(getString(R.string.Casunto));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                main_contacto.this,
                android.R.layout.simple_spinner_dropdown_item, items);
        asunto.setAdapter(adapter);
        // Spinner on item click listener
        asunto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                Easunto = items[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

    }

    public void onClick(View v) {
        // Reemplazamos el email por algun otro real
        String[] to = {"psicotecnicostropa@gmail.com"};
        String[] cc = {""};
        Emensaje = mensaje.getText().toString();
        if (Emensaje.equals("")) {
            Toast t = Toast.makeText(this,
                    String.format(getString(R.string.devesesc)),
                    Toast.LENGTH_LONG);
            t.show();
        } else {
            enviar(to, cc, Easunto, Emensaje);
            finish();
        }

    }

    private void enviar(String[] to, String[] cc, String asunto, String mensaje) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        // String[] to = direccionesEmail;
        // String[] cc = copias;
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        // emailIntent.putExtra(Intent.EXTRA_CC, cc);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, asunto);
        emailIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Email "));
    }
}
