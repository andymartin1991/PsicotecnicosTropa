package com.example.andym.psicotecnicostropa;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class main_principal extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_principal);

        final Button fatiga, aleatorio, trucos, contacta, examen, estudio, foro, facebook, twiter, instagran, informacion, evolucion, calcular, ayuda, pruebafisica, compartir, votar;

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

    }
}