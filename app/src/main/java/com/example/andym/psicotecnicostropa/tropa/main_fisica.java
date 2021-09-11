package com.example.andym.psicotecnicostropa.tropa;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.example.andym.psicotecnicostropa.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by andym on 08/07/2017.
 */

public class main_fisica extends Activity {

    int posanterior = 0;
    TextView titulo;
    TextView t0;
    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
    TextView t5;
    TextView t6;
    TextView t7;
    TextView descripcion;
    TextView detalles;
    TextView valoracion;
    ImageView imagen;
    LinearLayout tab1, tab2, tab3, tab4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fisica);

        /*LinearLayout padre = (LinearLayout)findViewById(R.id.lytMain);
        Calendar c1 = new GregorianCalendar();
        int dia = c1.get(Calendar.DAY_OF_MONTH);
        int mes = c1.get(Calendar.MONTH)+1;
        if( (mes ==11 || mes ==12) || (mes ==1 && dia <=7)){
            padre.setBackgroundResource(R.color.rojonavidad);
        }else{

        }*/

        //Resources res = getResources();

        tab1 = (LinearLayout)findViewById(R.id.tab1);
        tab2 = (LinearLayout)findViewById(R.id.tab2);
        tab3 = (LinearLayout)findViewById(R.id.tab3);
        tab4 = (LinearLayout)findViewById(R.id.tab4);

        final TabHost tabs = (TabHost) findViewById(R.id.tabHost);
        tabs.setup();


        TabHost.TabSpec spec = tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("", getResources().getDrawable(R.drawable.isalto));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("", getResources().getDrawable(R.drawable.iabd));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("mitab3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("", getResources().getDrawable(R.drawable.iflex));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("mitab4");
        spec.setContent(R.id.tab4);
        spec.setIndicator("", getResources().getDrawable(R.drawable.icorrer));
        tabs.addTab(spec);

        tabs.setCurrentTab(0);
        tabs.getTabWidget().setShowDividers(TabWidget.SHOW_DIVIDER_MIDDLE);
        pintar(0);
        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            public void onTabChanged(String tabId) {
                //Log.d(debugTag, "onTabChanged: tab number=" + mTabHost.getCurrentTab());
                Animation animationin = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_in);
                Animation animationout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_in);
                switch (tabs.getCurrentTab()) {
                    case 0:
                        //do what you want when tab 0 is selected
                        pintar(0);
                        if(Integer.parseInt(String.valueOf(tabs.getCurrentTab())) < posanterior){
                            tab1.startAnimation(animationout);
                        }else if(Integer.parseInt(String.valueOf(tabs.getCurrentTab())) > posanterior) {
                            tab1.startAnimation(animationin);
                        }
                        posanterior = Integer.parseInt(String.valueOf(tabs.getCurrentTab()));
                        break;
                    case 1:
                        //do what you want when tab 1 is selected
                        pintar(1);
                        if(Integer.parseInt(String.valueOf(tabs.getCurrentTab())) < posanterior){
                            tab2.startAnimation(animationout);
                        }else if(Integer.parseInt(String.valueOf(tabs.getCurrentTab())) > posanterior) {
                            tab2.startAnimation(animationin);
                        }
                        posanterior = Integer.parseInt(String.valueOf(tabs.getCurrentTab()));
                        break;
                    case 2:
                        //do what you want when tab 2 is selected
                        pintar(2);
                        if(Integer.parseInt(String.valueOf(tabs.getCurrentTab())) < posanterior){
                            tab3.startAnimation(animationout);
                        }else if(Integer.parseInt(String.valueOf(tabs.getCurrentTab())) > posanterior) {
                            tab3.startAnimation(animationin);
                        }
                        posanterior = Integer.parseInt(String.valueOf(tabs.getCurrentTab()));
                        break;
                    case 3:
                        //do what you want when tab 2 is selected
                        pintar(3);
                        if(Integer.parseInt(String.valueOf(tabs.getCurrentTab())) < posanterior){
                            tab4.startAnimation(animationout);
                        }else if(Integer.parseInt(String.valueOf(tabs.getCurrentTab())) > posanterior) {
                            tab4.startAnimation(animationin);
                        }
                        posanterior = Integer.parseInt(String.valueOf(tabs.getCurrentTab()));
                        break;
                    default:

                        break;
                }
            }
        });
    }

    private void pintar (int tipo){
        switch (tipo) {
            case 0:
                titulo = (TextView) findViewById(R.id.titulo0);
                t0 = (TextView) findViewById(R.id.textView100);
                t1 = (TextView) findViewById(R.id.textView110);
                t2 = (TextView) findViewById(R.id.textView120);
                t3 = (TextView) findViewById(R.id.textView130);
                t4 = (TextView) findViewById(R.id.textView140);
                t5 = (TextView) findViewById(R.id.textView150);
                t6 = (TextView) findViewById(R.id.textView160);
                t7 = (TextView) findViewById(R.id.textView170);
                descripcion = (TextView) findViewById(R.id.planedescripcion0);
                detalles = (TextView) findViewById(R.id.planedetalles0);
                valoracion = (TextView) findViewById(R.id.planevaloracion0);
                imagen = (ImageView) findViewById(R.id.imageView0);

                titulo.setText(getString(R.string.saltolongitud));
                t0.setText(getString(R.string.saltoha));
                t1.setText(getString(R.string.saltohb));
                t2.setText(getString(R.string.saltohc));
                t3.setText(getString(R.string.saltohd));
                t4.setText(getString(R.string.saltoma));
                t5.setText(getString(R.string.saltomb));
                t6.setText(getString(R.string.saltomc));
                t7.setText(getString(R.string.saltomd));
                imagen.setImageResource(R.drawable.salto);
                descripcion.setText(getString(R.string.dessalto));
                detalles.setText(getString(R.string.detsalto));
                valoracion.setText(getString(R.string.valsalto));
                break;
            case 1:
                titulo = (TextView) findViewById(R.id.titulo1);
                t0 = (TextView) findViewById(R.id.textView101);
                t1 = (TextView) findViewById(R.id.textView111);
                t2 = (TextView) findViewById(R.id.textView121);
                t3 = (TextView) findViewById(R.id.textView131);
                t4 = (TextView) findViewById(R.id.textView141);
                t5 = (TextView) findViewById(R.id.textView151);
                t6 = (TextView) findViewById(R.id.textView161);
                t7 = (TextView) findViewById(R.id.textView171);
                descripcion = (TextView) findViewById(R.id.planedescripcion1);
                detalles = (TextView) findViewById(R.id.planedetalles1);
                valoracion = (TextView) findViewById(R.id.planevaloracion1);
                imagen = (ImageView) findViewById(R.id.imageView1);

                titulo.setText(getString(R.string.abdominales));
                t0.setText(getString(R.string.abdominalesha));
                t1.setText(getString(R.string.abdominaleshb));
                t2.setText(getString(R.string.abdominaleshc));
                t3.setText(getString(R.string.abdominaleshd));
                t4.setText(getString(R.string.abdominalesma));
                t5.setText(getString(R.string.abdominalesmb));
                t6.setText(getString(R.string.abdominalesmc));
                t7.setText(getString(R.string.abdominalesmd));
                imagen.setImageResource(R.drawable.abdominales);
                descripcion.setText(getString(R.string.desabdo));
                detalles.setText(getString(R.string.detabdo));
                valoracion.setText(getString(R.string.valabdoflexo));
                break;

            case 2:
                titulo = (TextView) findViewById(R.id.titulo2);
                t0 = (TextView) findViewById(R.id.textView102);
                t1 = (TextView) findViewById(R.id.textView112);
                t2 = (TextView) findViewById(R.id.textView122);
                t3 = (TextView) findViewById(R.id.textView132);
                t4 = (TextView) findViewById(R.id.textView142);
                t5 = (TextView) findViewById(R.id.textView152);
                t6 = (TextView) findViewById(R.id.textView162);
                t7 = (TextView) findViewById(R.id.textView172);
                descripcion = (TextView) findViewById(R.id.planedescripcion2);
                detalles = (TextView) findViewById(R.id.planedetalles2);
                valoracion = (TextView) findViewById(R.id.planevaloracion2);
                imagen = (ImageView) findViewById(R.id.imageView2);

                titulo.setText(getString(R.string.flexiones));
                t0.setText(getString(R.string.flexoha));
                t1.setText(getString(R.string.flexohb));
                t2.setText(getString(R.string.flexohc));
                t3.setText(getString(R.string.flexohd));
                t4.setText(getString(R.string.flexoma));
                t5.setText(getString(R.string.flexomb));
                t6.setText(getString(R.string.flexomc));
                t7.setText(getString(R.string.flexomd));
                imagen.setImageResource(R.drawable.flexiones);
                descripcion.setText(getString(R.string.desflexo));
                detalles.setText(getString(R.string.detflexo));
                valoracion.setText(getString(R.string.valabdoflexo));
                break;

            case 3:
                titulo = (TextView) findViewById(R.id.titulo3);
                t0 = (TextView) findViewById(R.id.textView103);
                t1 = (TextView) findViewById(R.id.textView113);
                t2 = (TextView) findViewById(R.id.textView123);
                t3 = (TextView) findViewById(R.id.textView133);
                t4 = (TextView) findViewById(R.id.textView143);
                t5 = (TextView) findViewById(R.id.textView153);
                t6 = (TextView) findViewById(R.id.textView163);
                t7 = (TextView) findViewById(R.id.textView173);
                descripcion = (TextView) findViewById(R.id.planedescripcion3);
                detalles = (TextView) findViewById(R.id.planedetalles3);
                valoracion = (TextView) findViewById(R.id.planevaloracion3);
                imagen = (ImageView) findViewById(R.id.imageView3);

                titulo.setText(getString(R.string.carreraprogresiva));
                t0.setText(getString(R.string.carreraha));
                t1.setText(getString(R.string.carrerahb));
                t2.setText(getString(R.string.carrerahc));
                t3.setText(getString(R.string.carrerahd));
                t4.setText(getString(R.string.carrerama));
                t5.setText(getString(R.string.carreramb));
                t6.setText(getString(R.string.carreramc));
                t7.setText(getString(R.string.carreramd));
                imagen.setImageResource(R.drawable.correr);
                descripcion.setText(getString(R.string.descarrera));
                detalles.setText(getString(R.string.detcarrera));
                valoracion.setText(getString(R.string.valcarrera));
                break;
        }
    }
}
