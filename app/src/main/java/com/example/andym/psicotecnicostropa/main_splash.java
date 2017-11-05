package com.example.andym.psicotecnicostropa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.andym.psicotecnicostropa.R.drawable.splashnavideno;


public class main_splash extends Activity {

    //String version = "5.1.4";
    String version = String.valueOf((BuildConfig.VERSION_NAME));
    private static final long SPLASH_SCREEN_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main_splash);

        TextView mensaje = (TextView)findViewById(R.id.mensaje);

        RelativeLayout padre = (RelativeLayout)findViewById(R.id.padre);
        ImageView imagen = (ImageView)findViewById(R.id.imageView1);
        Calendar c1 = new GregorianCalendar();

        int dia = c1.get(Calendar.DAY_OF_MONTH);
        int mes = c1.get(Calendar.MONTH)+1;


        if( (mes ==11 || mes ==12) || (mes ==1 && dia <=7)){
            imagen.setImageResource(R.drawable.splashnavideno);
            padre.setBackgroundResource(R.color.rojonavidad);
            mensaje.setText("Psicotécnicos Tropa\nte desea unas felices fiestas");
        }else{
            imagen.setImageResource(R.drawable.splash);
        }




        mostrar();
    }

    private void mostrar() {

        if (estaConectado()) {
            try {

                Thread leerarchivo = new Thread() {
                    public void run() {
                        try {
                            String contents;
                            URLConnection conn = new URL("http://s593975491.mialojamiento.es/APPpsicotecnicostropa(1)/key.txt").openConnection();
                            InputStream in = conn.getInputStream();
                            contents = readStream(in);
                            if (contents.equals(version)) {
                                TimerTask task = new TimerTask() {
                                    @Override
                                    public void run() {

                                        // Start the next activity
                                        startActivity(new Intent(main_splash.this, main_principal.class));
                                        overridePendingTransition(R.anim.transpain, R.anim.transpaout);
                                        // Close the activity so the user won't able to go back this
                                        // activity pressing Back button
                                        finish();
                                    }
                                };
                                Timer timer = new Timer();
                                timer.schedule(task, SPLASH_SCREEN_DELAY);
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        if (!isFinishing()) {
                                            new AlertDialog.Builder(main_splash.this)
                                                    .setTitle(getString(R.string.atencion))
                                                    .setMessage(getString(R.string.noversion))
                                                    .setCancelable(false)
                                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                                        @SuppressWarnings("deprecation")
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            // Whatever...
                                                            String urlvotar = "https://play.google.com/store/apps/details?id=com.naroh.tropaPsicotecnicoOficial";
                                                            final Intent intent = new Intent(Intent.ACTION_VIEW,
                                                                    Uri.parse(urlvotar));
                                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                                                            startActivity(intent);
                                                            overridePendingTransition(R.anim.transpain, R.anim.transpaout);
                                                            finish();
                                                        }
                                                    })
                                                    .setNegativeButton("Ayuda", new DialogInterface.OnClickListener(){
                                                        @SuppressWarnings("deprecation")
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which){
                                                            startActivity(new Intent(main_splash.this, main_ayuda.class));
                                                            overridePendingTransition(R.anim.transpain, R.anim.transpaout);
                                                        }
                                                    }).create().show();
                                        }
                                    }
                                });
                            }
                        } catch (MalformedURLException e) {
                            Log.w("", "MALFORMED URL EXCEPTION");
                        } catch (IOException e) {
                            Log.w(e.getMessage(), e);
                        }
                    }

                };
                leerarchivo.start();
            } catch (Exception e) {

            }
        }

    }

    protected Boolean estaConectado() {
        if (conectadoWifi()) {
            return true;
        } else {
            if (conectadoRedMovil()) {
                return true;
            } else {
                showAlertDialog(main_splash.this, getString(R.string.conexioninternet), getString(R.string.nointernet),
                        false);
                return false;
            }
        }
    }

    protected Boolean conectadoWifi() {
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            @SuppressWarnings("deprecation")
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (info != null) {
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

    protected Boolean conectadoRedMovil() {
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            @SuppressWarnings("deprecation")
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (info != null) {
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
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

    @SuppressWarnings("deprecation")
    public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(false);
        alertDialog.setIcon(getResources().getDrawable(R.drawable.iexc));
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                overridePendingTransition(R.anim.transpain, R.anim.transpaout);

            }
        });

        alertDialog.show();
    }
}
