package com.example.andym.psicotecnicostropa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class main_principal extends Activity {

    int textlength = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_principal);

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
        patatas = (Button) findViewById(R.id.button32);
        academia = (Button) findViewById(R.id.button6);

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
                                    Uri.parse("https://patatasarmadas.sistec.es/"));
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
                        final LayoutInflater inflater = main_principal.this.getLayoutInflater();
                        final View textEntryView = inflater.inflate(R.layout.loggin, null);
                        final EditText correo = (EditText) textEntryView.findViewById(R.id.correo);
                        final EditText password = (EditText) textEntryView.findViewById(R.id.password);
                        final EditText search = (EditText) textEntryView.findViewById(R.id.search);
                        final ListView academias = (ListView) textEntryView.findViewById(R.id.academias);

                        final String listview_array[] = { "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN" };
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
                                if(textlength == 0){
                                    academias.setVisibility(View.GONE);
                                }else{
                                    academias.setVisibility(View.VISIBLE);
                                }
                            }
                        });

                        AlertDialog.Builder builder2 = new AlertDialog.Builder(main_principal.this);
                        builder2.setView(textEntryView);
                        builder2.setIcon(getResources().getDrawable(R.drawable.iexc));
                        builder2.setTitle(getString(R.string.atencion));
                        builder2.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                String Correo	=  correo.getText().toString();
                                String Password = password.getText().toString();


                            }
                        });
                        builder2.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        }).create().show();
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
                            handler.post(runnable);
                            entra[0] = true;
                        }
                    }).start();
                }
            }
        });





    }
}