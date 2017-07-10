package com.example.andym.psicotecnicostropa;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by andym on 21/04/2017.
 */

public class main_calculabaremo extends Activity {

    RadioGroup meriA, meriG1, meriG2, meriM1, meriM2, meriM3, meriG25;
    Button calcular;
    TextView mostrar;
    double meriAInt = 0, meriG1Int = 0, meriG2Int = 0, meriM1Int = 0, meriM2Int = 0, meriM3Int = 0, meriG25Int = 0;
    double puntos = 0;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_calculabaremo);

        mostrar = (TextView) findViewById(R.id.barcal);
        mostrar.setText(getString(R.string.baremototal) + " " + puntos);

        calcular = (Button) findViewById(R.id.calcular);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puntos = meriAInt + meriG1Int + meriG25Int + meriG2Int + meriM1Int + meriM2Int + meriM3Int;
                if (puntos > 40) {
                    puntos = 40;
                }
                mostrar.setText(getString(R.string.baremototal) + " " + puntos);
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                getString(R.string.baremototal) + " " + puntos, Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);

                toast1.show();
                try {
                    File ruta_sd;
                    String state = Environment.getExternalStorageState();
                    if (Environment.MEDIA_MOUNTED.equals(state)) {
                        // We can read and write the media
                        ruta_sd = getExternalFilesDir(null);
                    } else {
                        // Load another directory, probably local memory
                        ruta_sd = getFilesDir();
                    }
                    File f = new File(ruta_sd.getAbsolutePath(), "baremo");
                    OutputStreamWriter fout =
                            new OutputStreamWriter(
                                    new FileOutputStream(f));

                    fout.write(puntos + "");
                    fout.close();
                    System.out.println(ruta_sd);
                    System.out.println(f);
                } catch (Exception ex) {
                    Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
                }
            }
        });

        meriA = (RadioGroup) findViewById(R.id.meriA);
        meriA.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.a1) {
                    meriAInt = 16;
                } else if (checkedId == R.id.b1) {
                    meriAInt = 15;
                } else if (checkedId == R.id.c1) {
                    meriAInt = 13;
                } else if (checkedId == R.id.d1) {
                    meriAInt = 12;
                } else if (checkedId == R.id.e1) {
                    meriAInt = 11;
                } else if (checkedId == R.id.f1) {
                    meriAInt = 8;
                } else if (checkedId == R.id.g1) {
                    meriAInt = 7;
                } else if (checkedId == R.id.h1) {
                    meriAInt = 6;
                } else if (checkedId == R.id.i1) {
                    meriAInt = 4;
                } else if (checkedId == R.id.j1) {
                    meriAInt = 0;
                }
            }
        });

        meriG1 = (RadioGroup) findViewById(R.id.meriG1);
        meriG1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.a2) {
                    meriG1Int = 9;
                } else if (checkedId == R.id.b2) {
                    meriG1Int = 6;
                } else if (checkedId == R.id.c2) {
                    meriG1Int = 3;
                } else if (checkedId == R.id.d2) {
                    meriG1Int = 1;
                } else if (checkedId == R.id.e2) {
                    meriG1Int = 0;
                }
            }
        });

        meriG25 = (RadioGroup) findViewById(R.id.meriG25);
        meriG25.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.a3) {
                    meriG25Int = 3;
                } else if (checkedId == R.id.b3) {
                    meriG25Int = 0;
                }
            }
        });

        meriG2 = (RadioGroup) findViewById(R.id.meriG2);
        meriG2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.a4) {
                    meriG2Int = 8;
                } else if (checkedId == R.id.b4) {
                    meriG2Int = 5;
                } else if (checkedId == R.id.c4) {
                    meriG2Int = 3;
                } else if (checkedId == R.id.d4) {
                    meriG2Int = 0;
                }
            }
        });

        meriM1 = (RadioGroup) findViewById(R.id.meriM1);
        meriM1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.a5) {
                    meriM1Int = 2;
                } else if (checkedId == R.id.b5) {
                    meriM1Int = 1.5;
                } else if (checkedId == R.id.c5) {
                    meriM1Int = 1;
                } else if (checkedId == R.id.d5) {
                    meriM1Int = 0.25;
                } else if (checkedId == R.id.e5) {
                    meriM1Int = 0;
                }
            }
        });

        meriM2 = (RadioGroup) findViewById(R.id.meriM2);
        meriM2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.a6) {
                    meriM2Int = 2;
                } else if (checkedId == R.id.b6) {
                    meriM2Int = 1.75;
                } else if (checkedId == R.id.c6) {
                    meriM2Int = 1.5;
                } else if (checkedId == R.id.d6) {
                    meriM2Int = 0.5;
                } else if (checkedId == R.id.e6) {
                    meriM2Int = 0.25;
                } else if (checkedId == R.id.f6) {
                    meriM2Int = 0;
                }
            }
        });

        meriM3 = (RadioGroup) findViewById(R.id.meriM3);
        meriM3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.a7) {
                    meriM3Int = 0.5;
                } else if (checkedId == R.id.b7) {
                    meriM3Int = 0.25;
                } else if (checkedId == R.id.c7) {
                    meriM3Int = 0;
                }
            }
        });

    }
}

