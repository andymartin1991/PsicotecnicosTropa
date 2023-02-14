package com.example.andym.nttdata.tropa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.andym.nttdata.R;
import com.example.andym.nttdata.tropa.academia.main_academia;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class main_principal extends Activity {

    int textlength = 0;
    String[] listview_array = new String[0];
    URLConnection conn = null;
    String Correo="";
    String Password="";
    String Academia="";
    boolean recordar = false;
    int beta = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_principal);

        /*LinearLayout padre = (LinearLayout)findViewById(R.id.padre);
        Calendar c1 = new GregorianCalendar();
        int dia = c1.get(Calendar.DAY_OF_MONTH);
        int mes = c1.get(Calendar.MONTH)+1;
        if( (mes ==11 || mes ==12) || (mes ==1 && dia <=7)){
            padre.setBackgroundResource(R.color.rojonavidad);
        }else{

        }*/

        final Button fatiga, aleatorio, trucos, contacta, examen, estudio, foro, facebook, twiter, instagran, informacion, evolucion, calcular, ayuda, pruebafisica, compartir, votar, patatas, academia;

        fatiga = (Button) findViewById(R.id.button1);
        aleatorio = (Button) findViewById(R.id.button2);
        trucos = (Button) findViewById(R.id.button3);
        contacta = (Button) findViewById(R.id.button4);
        examen = (Button) findViewById(R.id.button5);
        estudio = (Button) findViewById(R.id.button15);
        foro = (Button) findViewById(R.id.button11);
        facebook = (Button) findViewById(R.id.button12);
        twiter = (Button) findViewById(R.id.button13);
        instagran = (Button) findViewById(R.id.button14);
        informacion = (Button) findViewById(R.id.button21);
        evolucion = (Button) findViewById(R.id.button22);
        calcular = (Button) findViewById(R.id.button23);
        ayuda = (Button) findViewById(R.id.button24);
        pruebafisica = (Button) findViewById(R.id.button25);
        compartir = (Button) findViewById(R.id.button31);
        votar = (Button) findViewById(R.id.button32);
        patatas = (Button) findViewById(R.id.papa);
        academia = (Button) findViewById(R.id.button6);

        /*Button exiopo = (Button)findViewById(R.id.exitoopo);
        exiopo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                        }
                        startActivity(new Intent(main_principal.this, main_tropaymarineria.class));
                        overridePendingTransition(R.anim.transpain, R.anim.transpaout);
                    }
                }).start();
            }
        });*/

        final boolean[] entra = {true};
        estudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entra[0] == true) {
                    entra[0] = false;
                    Animation animation = AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.rotar);
                    estudio.startAnimation(animation);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                            }
                            startActivity(new Intent(main_principal.this, main_optTipo.class));
                            overridePendingTransition(R.anim.transpain, R.anim.transpaout);
                            entra[0] = true;
                        }
                    }).start();

                }
            }
        });

        aleatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entra[0] == true) {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Generando ...", Toast.LENGTH_SHORT);
                    toast1.show();
                    entra[0] = false;
                    Animation animation = AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.rotar);
                    aleatorio.startAnimation(animation);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                            }
                            startActivity(new Intent(main_principal.this, main_preguntasAleatorio.class));
                            overridePendingTransition(R.anim.transpain, R.anim.transpaout);
                            entra[0] = true;
                        }
                    }).start();
                }
            }
        });

        trucos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entra[0] == true) {
                    entra[0] = false;
                    Animation animation = AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.rotar);
                    trucos.startAnimation(animation);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                            }
                            startActivity(new Intent(main_principal.this, main_trucos.class));
                            overridePendingTransition(R.anim.transpain, R.anim.transpaout);
                            entra[0] = true;
                        }
                    }).start();
                }
            }
        });

        contacta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entra[0] == true) {
                    entra[0] = false;
                    Animation animation = AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.rotar);
                    contacta.startAnimation(animation);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                            }
                            startActivity(new Intent(main_principal.this, main_contacto.class));
                            overridePendingTransition(R.anim.transpain, R.anim.transpaout);
                            entra[0] = true;
                        }
                    }).start();
                }
            }
        });

        examen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entra[0] == true) {
                    entra[0] = false;
                    Animation animation = AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.rotar);
                    examen.startAnimation(animation);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                            }
                            startActivity(new Intent(main_principal.this, main_examen.class));
                            overridePendingTransition(R.anim.transpain, R.anim.transpaout);
                            entra[0] = true;
                        }
                    }).start();
                }
            }
        });

        fatiga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entra[0] == true) {
                    entra[0] = false;
                    Animation animation = AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.rotar);
                    fatiga.startAnimation(animation);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                            }
                            startActivity(new Intent(main_principal.this, main_optTipoFatiga.class));
                            overridePendingTransition(R.anim.transpain, R.anim.transpaout);
                            entra[0] = true;
                        }
                    }).start();
                }
            }
        });

        foro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entra[0] == true) {
                    entra[0] = false;
                    Animation animation = AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.rotar);
                    foro.startAnimation(animation);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                            }
                            Intent intent = new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://www.fuerzasarmadas.eu/"));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                    | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                            startActivity(intent);
                            entra[0] = true;
                        }
                    }).start();
                }
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entra[0] == true) {
                    entra[0] = false;
                    Animation animation = AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.rotar);
                    facebook.startAnimation(animation);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                            }
                            Intent intent = new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://www.facebook.com/psicotecnicos.tropa"));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                    | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                            startActivity(intent);
                            entra[0] = true;
                        }
                    }).start();
                }
            }
        });

        twiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entra[0] == true) {
                    entra[0] = false;
                    Animation animation = AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.rotar);
                    twiter.startAnimation(animation);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                            }
                            Intent intent = new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://twitter.com/psicostropa"));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                    | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                            startActivity(intent);
                            entra[0] = true;
                        }
                    }).start();
                }
            }
        });

        instagran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entra[0] == true) {
                    entra[0] = false;
                    Animation animation = AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.rotar);
                    instagran.startAnimation(animation);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                            }
                            Intent intent = new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://instagram.com/psicotecnicostropa/"));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                    | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                            startActivity(intent);
                            entra[0] = true;
                        }
                    }).start();
                }
            }
        });

        informacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entra[0] == true) {
                    entra[0] = false;
                    Animation animation = AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.rotar);
                    informacion.startAnimation(animation);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                            }
                            startActivity(new Intent(main_principal.this, main_info.class));
                            overridePendingTransition(R.anim.transpain, R.anim.transpaout);
                            entra[0] = true;
                        }
                    }).start();
                }
            }
        });

        evolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entra[0] == true) {
                    entra[0] = false;
                    Animation animation = AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.rotar);
                    evolucion.startAnimation(animation);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                            }
                            startActivity(new Intent(main_principal.this, main_evolucion.class));
                            overridePendingTransition(R.anim.transpain, R.anim.transpaout);
                            entra[0] = true;
                        }
                    }).start();
                }
            }
        });

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entra[0] == true) {
                    entra[0] = false;
                    Animation animation = AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.rotar);
                    calcular.startAnimation(animation);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                            }
                            startActivity(new Intent(main_principal.this, main_calculabaremo.class));
                            overridePendingTransition(R.anim.transpain, R.anim.transpaout);
                            entra[0] = true;
                        }
                    }).start();
                }
            }
        });

        ayuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entra[0] == true) {
                    entra[0] = false;
                    Animation animation = AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.rotar);
                    ayuda.startAnimation(animation);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                            }
                            startActivity(new Intent(main_principal.this, main_ayuda.class));
                            overridePendingTransition(R.anim.transpain, R.anim.transpaout);
                            entra[0] = true;
                        }
                    }).start();
                }
            }
        });

        pruebafisica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entra[0] == true) {
                    entra[0] = false;
                    Animation animation = AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.rotar);
                    pruebafisica.startAnimation(animation);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                            }
                            startActivity(new Intent(main_principal.this, main_fisica.class));
                            overridePendingTransition(R.anim.transpain, R.anim.transpaout);
                            entra[0] = true;
                        }
                    }).start();
                }
            }
        });

        compartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entra[0] == true) {
                    entra[0] = false;
                    Animation animation = AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.rotar);
                    compartir.startAnimation(animation);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                            }
                            Intent intentCompartir = new Intent(Intent.ACTION_SEND);
                            intentCompartir.setType("text/plain");
                            intentCompartir.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                            intentCompartir.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.naroh.tropaPsicotecnicoOficial");
                            intentCompartir.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(Intent.createChooser(intentCompartir, getString(R.string.compartiren)));
                            entra[0] = true;
                        }
                    }).start();
                }
            }
        });

        votar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beta++;
                if(beta == 10){
                    TableRow temp= (TableRow)findViewById(R.id.betaaca);
                    temp.setVisibility(View.VISIBLE);

                }
                if (entra[0] == true) {
                    entra[0] = false;
                    Animation animation = AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.rotar);
                    votar.startAnimation(animation);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                            }
                            Intent intent = new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://play.google.com/store/apps/details?id=com.naroh.tropaPsicotecnicoOficial"));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                    | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                            startActivity(intent);
                            entra[0] = true;
                        }
                    }).start();
                }
            }
        });

        patatas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entra[0] == true) {
                    entra[0] = false;
                    Animation animation = AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.rotar);
                    patatas.startAnimation(animation);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                            }
                            Intent intent = new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://patatasarmadas.es/"));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                    | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                            startActivity(intent);
                            entra[0] = true;
                        }
                    }).start();
                }
            }
        });


        final AlertDialog.Builder builder = new AlertDialog.Builder(main_principal.this);
        builder
                .setIcon(getResources().getDrawable(R.drawable.iexc))
                .setTitle(getString(R.string.atencion))
                .setMessage(getString(R.string.atenacademia))
                .setCancelable(false)
                .setNegativeButton("Salir", null)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @SuppressWarnings("deprecation")
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //AlertDialog.Builder builder = new AlertDialog.Builder(main_principal.this);
                                dialogrepetir();
                            }
                        }
                );

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                builder.create().show();
            }
        };
        academia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entra[0] == true) {
                    entra[0] = false;
                    Animation animation = AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.rotar);
                    academia.startAnimation(animation);
                    final Handler handler = new Handler();
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                            }
                            Correo="";
                            Password="";
                            Academia="";
                            handler.post(runnable);
                            entra[0] = true;
                        }
                    }).start();
                }
            }
        });
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
    public void peticion(){
        final String[] contents = {""};

        try {
            conn = new URL("http://s593975491.mialojamiento.es/APPpsicotecnicostropa(1)/lista_academias.php").openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                        JSONObject myJsonjObject = new JSONObject(contents[0]);
                        JSONArray myJsonArray = myJsonjObject.getJSONArray("academias");
                        listview_array = new String[myJsonArray.length()];
                        for (int i = 0; i < myJsonArray.length(); i++) {
                            JSONObject oneObject = myJsonArray.getJSONObject(i);
                            listview_array[i] = oneObject.getString("NOMBRE_ACA");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void dialogrepetir(){
        final LayoutInflater inflater = main_principal.this.getLayoutInflater();
        final View textEntryView = inflater.inflate(R.layout.loggin, null);
        final EditText correo = (EditText) textEntryView.findViewById(R.id.correo);
        final EditText password = (EditText) textEntryView.findViewById(R.id.password);
        final EditText search = (EditText) textEntryView.findViewById(R.id.search);
        final ListView academias = (ListView) textEntryView.findViewById(R.id.academias);
        final CheckBox checRecord = (CheckBox)textEntryView.findViewById(R.id.recordar);
        checRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = ((CheckBox)v).isChecked();
                if (isChecked) {
                    recordar = true;
                }
                else {
                    recordar = false;
                }
            }
        });

        ///////////////////////////////////poner datos si existe guardado login
        File ruta_sd;
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            ruta_sd = getExternalFilesDir(null);
        } else {
            // Load another directory, probably local memory
            ruta_sd = getFilesDir();
        }
        final File a = new File(ruta_sd.getAbsolutePath(), "login");
        if(a.exists()) {
            String fichero = "login";
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(a)));
                String linea[] = {"","",""};
                int i = 0;
                String line;
                while((line = br.readLine()) != null) {
                    linea[i]=line;
                    i++;
                }
                br.close();
                correo.setText(linea[0]);
                password.setText(linea[1]);
                search.setText(linea[2]);
                checRecord.setChecked(true);
            }
            catch(Exception e) {
                System.out.println("Excepcion leyendo fichero "+ fichero + ": " + e);
            }
        }
        ///////////////////////////fin de poner datos si existe guardado login

        peticion();

        final ArrayList<String> array_sort = new ArrayList<String>();
        academias.setVisibility(View.GONE);

        academias.setAdapter(new ArrayAdapter<String>(main_principal.this,
                android.R.layout.simple_list_item_1, listview_array));
        search.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // Abstract Method of TextWatcher Interface.
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textlength = search.getText().length();
                array_sort.clear();

                for (int i = 0; i < listview_array.length; i++) {
                    if (textlength <= listview_array[i].length()) {
                        if (search.getText().toString().equalsIgnoreCase((String) listview_array[i].subSequence(0, textlength))) {
                            array_sort.add(listview_array[i]);
                        }
                    }
                }

                academias.setAdapter(new ArrayAdapter<String>(main_principal.this, android.R.layout.simple_list_item_1, array_sort));
                if (textlength == 0) {
                    academias.setVisibility(View.GONE);
                } else {
                    academias.setVisibility(View.VISIBLE);
                }
            }
        });

        final String[] aca = {""};
        academias.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                search.setText(listview_array[position]);
                aca[0] = (listview_array[position]);
            }
        });
        AlertDialog.Builder builder2 = new AlertDialog.Builder(main_principal.this);
        builder2.setView(textEntryView);
        builder2.setIcon(getResources().getDrawable(R.drawable.iexc));
        builder2.setTitle(getString(R.string.atencion));
        builder2.setCancelable(false);
        builder2.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                /*correo.setText("andymartin1991@gmail.com");
                password.setText("permiso1991");
                search.setText("PsicotecnicosTropa");*/

                if(correo.getText().toString() != null && !correo.getText().toString().equals("")){
                    Correo = correo.getText().toString();
                }
                if(password.getText().toString() != null && !password.getText().toString().equals("")){
                    Password = password.getText().toString();
                }
                if(aca[0].toString() != null && !aca[0].toString().equals("")){
                    Academia = aca[0].toString();
                }
                Academia = search.getText().toString();
                if(Correo.equals("") || Password.equals("") || Academia.equals("")){
                    Toast mensaje = Toast.makeText(getApplicationContext(),
                            "Los campos son obligatorios", Toast.LENGTH_SHORT);
                    mensaje.show();
                    dialogrepetir();
                }else{
                    if(recordar){
                        try {
                            String state = Environment.getExternalStorageState();

                            File ruta_sd;
                            if (Environment.MEDIA_MOUNTED.equals(state)) {
                                // We can read and write the media
                                ruta_sd = getExternalFilesDir(null);
                            } else {
                                // Load another directory, probably local memory
                                ruta_sd = getFilesDir();
                            }
                            //File ruta_sd = getExternalFilesDir(null);
                            File f = new File(ruta_sd.getAbsolutePath(), "login");
                            OutputStreamWriter fout =
                                    new OutputStreamWriter(
                                            new FileOutputStream(f));
                            String aux = Correo+"\n"+Password+"\n"+Academia;
                            fout.write(aux);
                            fout.close();
                            System.out.println(ruta_sd);
                            System.out.println(f);
                        } catch (Exception ex) {
                            Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
                            Toast mensaje = Toast.makeText(getApplicationContext(),
                                    "Error al guardar login", Toast.LENGTH_SHORT);
                            mensaje.show();
                        }
                    }else{
                        File ruta_sd;
                        String state = Environment.getExternalStorageState();
                        if (Environment.MEDIA_MOUNTED.equals(state)) {
                            // We can read and write the media
                            ruta_sd = getExternalFilesDir(null);
                        } else {
                            // Load another directory, probably local memory
                            ruta_sd = getFilesDir();
                        }
                        final File a = new File(ruta_sd.getAbsolutePath(), "login");
                        if(a.exists()) {
                            a.delete();
                        }
                    }
                    Toast mensaje = Toast.makeText(getApplicationContext(),
                            "Enviando...", Toast.LENGTH_SHORT);
                    mensaje.show();

                    Bundle parmetros = new Bundle();
                    parmetros.putString("correo", Correo);
                    parmetros.putString("pass", Password);
                    parmetros.putString("academia", Academia);
                    parmetros.putString("nameacademia", search.getText().toString());

                    Intent i = new Intent(main_principal.this, main_academia.class);
                    i.putExtras(parmetros);
                    startActivity(i);
                }
            }
        });
        builder2.setNegativeButton("Salir", null).create().show();
    }
}