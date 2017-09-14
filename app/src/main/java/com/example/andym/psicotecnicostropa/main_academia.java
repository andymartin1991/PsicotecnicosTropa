package com.example.andym.psicotecnicostropa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by andym on 09/09/2017.
 */
public class main_academia extends Activity {

    URLConnection conn = null;
    String id;
    Button volver;
    TextView error, titulo, username;
    LinearLayout emer, verificado;
    JSONObject objetouser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_academia);

        String correo = getIntent().getStringExtra("correo");
        String password = getIntent().getStringExtra("pass");
        String academia = getIntent().getStringExtra("academia");
        final String academianame = getIntent().getStringExtra("nameacademia");

        volver = (Button)findViewById(R.id.atras);
        error = (TextView) findViewById(R.id.fallo);
        emer = (LinearLayout)findViewById(R.id.emer);
        verificado = (LinearLayout)findViewById(R.id.verificado);
        titulo = (TextView) findViewById(R.id.title);
        username = (TextView) findViewById(R.id.nameusu);

        final String[] contents = {""};
        try {
            conn = new URL("http://s593975491.mialojamiento.es/APPpsicotecnicostropa(1)/login_usuario.php?correo="+correo+"&password="+password+"&academia="+academia).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final Runnable userpasserror = new Runnable() {
            @Override
            public void run() {
                emer.setVisibility(View.VISIBLE);
                verificado.setVisibility(View.GONE);
                error.setText(getString(R.string.errorusuario));
                volver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        };

        final Runnable userpassok = new Runnable() {
            @Override
            public void run() {
                emer.setVisibility(View.GONE);
                verificado.setVisibility(View.VISIBLE);
                titulo.setText(academianame);
                try{
                    username.setText("Bienvenido "+objetouser.getString("NOMBRE_ALU"));
                }catch (JSONException e) {
                }
            }
        };


        final Handler handler = new Handler();
        final InputStream[] in = new InputStream[1];
        new Thread(new Runnable() {
            public void run() {
                try {
                    in[0] = conn.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    contents[0] = readStream(in[0]).toString();

                    try {
                        JSONObject objetousuario = new JSONObject(contents[0]);
                        JSONArray myJsonArray = objetousuario.getJSONArray("usuario");
                        id = "";
                        if(myJsonArray.length()==1) {
                            objetouser = myJsonArray.getJSONObject(0);
                            id = objetouser.getString("ID_USUARIO");
                            handler.post(userpassok);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        handler.post(userpasserror);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();



    }

    public String readStream(InputStream in) throws IOException {
        BufferedReader r = null;
        r = new BufferedReader(new InputStreamReader(in));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line);
        }
        if (r != null) {
            r.close();
        }
        in.close();
        return total.toString();
    }

}