package com.andymarcial.psicotecnicostropa.tropa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.andymarcial.psicotecnicostropa.BuildConfig;
import com.andymarcial.psicotecnicostropa.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andym on 04/06/2017.
 */

public class main_info extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_info);

        /*ScrollView padre = (ScrollView) findViewById(R.id.padre);
        Calendar cc1 = new GregorianCalendar();
        int dia = cc1.get(Calendar.DAY_OF_MONTH);
        int mes = cc1.get(Calendar.MONTH) + 1;
        if ((mes == 11 || mes == 12) || (mes == 1 && dia <= 7)) {
            padre.setBackgroundResource(R.color.rojonavidad);
        } else {

        }*/

        TextView num = (TextView) findViewById(R.id.num);
        TextView verb = (TextView) findViewById(R.id.verb);
        TextView meca = (TextView) findViewById(R.id.meca);
        final TextView abs = (TextView) findViewById(R.id.abs);
        TextView memo = (TextView) findViewById(R.id.memo);
        TextView espa = (TextView) findViewById(R.id.espa);
        TextView perc = (TextView) findViewById(R.id.perc);


        int pregunta = getResources().getStringArray(R.array.preverbal).length;
        int resA = getResources().getStringArray(R.array.resAverbal).length;
        int resB = getResources().getStringArray(R.array.resBverbal).length;
        int resC = getResources().getStringArray(R.array.resCverbal).length;
        int resD = getResources().getStringArray(R.array.resDverbal).length;
        int sol = getResources().getStringArray(R.array.solverbal).length;
        int expliSol = getResources().getStringArray(R.array.expliSolverbal).length;
        int imgPre = getResources().getStringArray(R.array.imgPreverbal).length;
        int imgA = getResources().getStringArray(R.array.imgAverbal).length;
        int imgB = getResources().getStringArray(R.array.imgBverbal).length;
        int imgC = getResources().getStringArray(R.array.imgCverbal).length;
        int imgD = getResources().getStringArray(R.array.imgDverbal).length;
        int imgSol = getResources().getStringArray(R.array.imgSolverbal).length;
        int imgExpli = getResources().getStringArray(R.array.imgExpliverbal).length;

        if (resA == pregunta) {
            if (resB == pregunta) {
                if (resC == pregunta) {
                    if (resD == pregunta) {
                        if (sol == pregunta) {
                            if (expliSol == pregunta) {
                                if (imgPre == pregunta) {
                                    if (imgA == pregunta) {
                                        if (imgB == pregunta) {
                                            if (imgC == pregunta) {
                                                if (imgD == pregunta) {
                                                    if (imgSol == pregunta) {
                                                        if (imgExpli == pregunta) {
                                                            verb.setText(getString(R.string.verbal) + " (" + pregunta + ") Correcto");
                                                        } else {
                                                            verb.setText("Incoherencias con imagenes de explicaciones " + imgExpli + " de (" + pregunta + ")");
                                                        }
                                                    } else {
                                                        verb.setText("Incoherencias con imagenes de soluciones " + imgSol + " de (" + pregunta + ")");
                                                    }
                                                } else {
                                                    verb.setText("Incoherencias con imagenes D " + imgD + " de (" + pregunta + ")");
                                                }
                                            } else {
                                                verb.setText("Incoherencias con imagenes C " + imgC + " de (" + pregunta + ")");
                                            }
                                        } else {
                                            verb.setText("Incoherencias con imagenes B " + imgB + " de (" + pregunta + ")");
                                        }
                                    } else {
                                        verb.setText("Incoherencias con imagenes A " + imgA + " de (" + pregunta + ")");
                                    }
                                } else {
                                    verb.setText("Incoherencias con imagenes de preguntas " + imgPre + " de (" + pregunta + ")");
                                }
                            } else {
                                verb.setText("Incoherencias con explicacones de soluciones " + expliSol + " de (" + pregunta + ")");
                            }
                        } else {
                            verb.setText("Incoherencias con soluciones " + sol + " de (" + pregunta + ")");
                        }
                    } else {
                        verb.setText("Incoherencias con respuestas D " + resD + " de (" + pregunta + ")");
                    }
                } else {
                    verb.setText("Incoherencias con respuestas C " + resC + " de (" + pregunta + ")");
                }
            } else {
                verb.setText("Incoherencias con respuestas B " + resB + " de (" + pregunta + ")");
            }
        } else {
            verb.setText("Incoherencias con respuestas A " + resA + " de (" + pregunta + ")");
        }


        pregunta = getResources().getStringArray(R.array.prenumerico).length;
        resA = getResources().getStringArray(R.array.resAnumerico).length;
        resB = getResources().getStringArray(R.array.resBnumerico).length;
        resC = getResources().getStringArray(R.array.resCnumerico).length;
        resD = getResources().getStringArray(R.array.resDnumerico).length;
        sol = getResources().getStringArray(R.array.solnumerico).length;
        expliSol = getResources().getStringArray(R.array.expliSolnumerico).length;
        imgPre = getResources().getStringArray(R.array.imgPrenumerico).length;
        imgA = getResources().getStringArray(R.array.imgAnumerico).length;
        imgB = getResources().getStringArray(R.array.imgBnumerico).length;
        imgC = getResources().getStringArray(R.array.imgCnumerico).length;
        imgD = getResources().getStringArray(R.array.imgDnumerico).length;
        imgSol = getResources().getStringArray(R.array.imgSolnumerico).length;
        imgExpli = getResources().getStringArray(R.array.imgExplinumerico).length;

        if (resA == pregunta) {
            if (resB == pregunta) {
                if (resC == pregunta) {
                    if (resD == pregunta) {
                        if (sol == pregunta) {
                            if (expliSol == pregunta) {
                                if (imgPre == pregunta) {
                                    if (imgA == pregunta) {
                                        if (imgB == pregunta) {
                                            if (imgC == pregunta) {
                                                if (imgD == pregunta) {
                                                    if (imgSol == pregunta) {
                                                        if (imgExpli == pregunta) {
                                                            num.setText(getString(R.string.numerico) + " (" + pregunta + ") Correcto");
                                                        } else {
                                                            num.setText("Incoherencias con imagenes de explicaciones " + imgExpli + " de (" + pregunta + ")");
                                                        }
                                                    } else {
                                                        num.setText("Incoherencias con imagenes de soluciones " + imgSol + " de (" + pregunta + ")");
                                                    }
                                                } else {
                                                    num.setText("Incoherencias con imagenes D " + imgD + " de (" + pregunta + ")");
                                                }
                                            } else {
                                                num.setText("Incoherencias con imagenes C " + imgC + " de (" + pregunta + ")");
                                            }
                                        } else {
                                            num.setText("Incoherencias con imagenes B " + imgB + " de (" + pregunta + ")");
                                        }
                                    } else {
                                        num.setText("Incoherencias con imagenes A " + imgA + " de (" + pregunta + ")");
                                    }
                                } else {
                                    num.setText("Incoherencias con imagenes de preguntas " + imgPre + " de (" + pregunta + ")");
                                }
                            } else {
                                num.setText("Incoherencias con explicacones de soluciones " + expliSol + " de (" + pregunta + ")");
                            }
                        } else {
                            num.setText("Incoherencias con soluciones " + sol + " de (" + pregunta + ")");
                        }
                    } else {
                        num.setText("Incoherencias con respuestas D " + resD + " de (" + pregunta + ")");
                    }
                } else {
                    num.setText("Incoherencias con respuestas C " + resC + " de (" + pregunta + ")");
                }
            } else {
                num.setText("Incoherencias con respuestas B " + resB + " de (" + pregunta + ")");
            }
        } else {
            num.setText("Incoherencias con respuestas A " + resA + " de (" + pregunta + ")");
        }


        pregunta = getResources().getStringArray(R.array.premecanico).length;
        resA = getResources().getStringArray(R.array.resAmecanico).length;
        resB = getResources().getStringArray(R.array.resBmecanico).length;
        resC = getResources().getStringArray(R.array.resCmecanico).length;
        resD = getResources().getStringArray(R.array.resDmecanico).length;
        sol = getResources().getStringArray(R.array.solmecanico).length;
        expliSol = getResources().getStringArray(R.array.expliSolmecanico).length;
        imgPre = getResources().getStringArray(R.array.imgPremecanico).length;
        imgA = getResources().getStringArray(R.array.imgAmecanico).length;
        imgB = getResources().getStringArray(R.array.imgBmecanico).length;
        imgC = getResources().getStringArray(R.array.imgCmecanico).length;
        imgD = getResources().getStringArray(R.array.imgDmecanico).length;
        imgSol = getResources().getStringArray(R.array.imgSolmecanico).length;
        imgExpli = getResources().getStringArray(R.array.imgExplimecanico).length;

        if (resA == pregunta) {
            if (resB == pregunta) {
                if (resC == pregunta) {
                    if (resD == pregunta) {
                        if (sol == pregunta) {
                            if (expliSol == pregunta) {
                                if (imgPre == pregunta) {
                                    if (imgA == pregunta) {
                                        if (imgB == pregunta) {
                                            if (imgC == pregunta) {
                                                if (imgD == pregunta) {
                                                    if (imgSol == pregunta) {
                                                        if (imgExpli == pregunta) {
                                                            meca.setText(getString(R.string.mecanico) + " (" + pregunta + ") Correcto");
                                                        } else {
                                                            meca.setText("Incoherencias con imagenes de explicaciones " + imgExpli + " de (" + pregunta + ")");
                                                        }
                                                    } else {
                                                        meca.setText("Incoherencias con imagenes de soluciones " + imgSol + " de (" + pregunta + ")");
                                                    }
                                                } else {
                                                    meca.setText("Incoherencias con imagenes D " + imgD + " de (" + pregunta + ")");
                                                }
                                            } else {
                                                meca.setText("Incoherencias con imagenes C " + imgC + " de (" + pregunta + ")");
                                            }
                                        } else {
                                            meca.setText("Incoherencias con imagenes B " + imgB + " de (" + pregunta + ")");
                                        }
                                    } else {
                                        meca.setText("Incoherencias con imagenes A " + imgA + " de (" + pregunta + ")");
                                    }
                                } else {
                                    meca.setText("Incoherencias con imagenes de preguntas " + imgPre + " de (" + pregunta + ")");
                                }
                            } else {
                                meca.setText("Incoherencias con explicacones de soluciones " + expliSol + " de (" + pregunta + ")");
                            }
                        } else {
                            meca.setText("Incoherencias con soluciones " + sol + " de (" + pregunta + ")");
                        }
                    } else {
                        meca.setText("Incoherencias con respuestas D " + resD + " de (" + pregunta + ")");
                    }
                } else {
                    meca.setText("Incoherencias con respuestas C " + resC + " de (" + pregunta + ")");
                }
            } else {
                meca.setText("Incoherencias con respuestas B " + resB + " de (" + pregunta + ")");
            }
        } else {
            meca.setText("Incoherencias con respuestas A " + resA + " de (" + pregunta + ")");
        }


        pregunta = getResources().getStringArray(R.array.preabstrapto).length;
        resA = getResources().getStringArray(R.array.resAabstrapto).length;
        resB = getResources().getStringArray(R.array.resBabstrapto).length;
        resC = getResources().getStringArray(R.array.resCabstrapto).length;
        resD = getResources().getStringArray(R.array.resDabstrapto).length;
        sol = getResources().getStringArray(R.array.solabstrapto).length;
        expliSol = getResources().getStringArray(R.array.expliSolabstrapto).length;
        imgPre = getResources().getStringArray(R.array.imgPreabstrapto).length;
        imgA = getResources().getStringArray(R.array.imgAabstrapto).length;
        imgB = getResources().getStringArray(R.array.imgBabstrapto).length;
        imgC = getResources().getStringArray(R.array.imgCabstrapto).length;
        imgD = getResources().getStringArray(R.array.imgDabstrapto).length;
        imgSol = getResources().getStringArray(R.array.imgSolabstrapto).length;
        imgExpli = getResources().getStringArray(R.array.imgExpliabstrapto).length;

        if (resA == pregunta) {
            if (resB == pregunta) {
                if (resC == pregunta) {
                    if (resD == pregunta) {
                        if (sol == pregunta) {
                            if (expliSol == pregunta) {
                                if (imgPre == pregunta) {
                                    if (imgA == pregunta) {
                                        if (imgB == pregunta) {
                                            if (imgC == pregunta) {
                                                if (imgD == pregunta) {
                                                    if (imgSol == pregunta) {
                                                        if (imgExpli == pregunta) {
                                                            abs.setText(getString(R.string.abstrapto) + " (" + pregunta + ") Correcto");
                                                        } else {
                                                            abs.setText("Incoherencias con imagenes de explicaciones " + imgExpli + " de (" + pregunta + ")");
                                                        }
                                                    } else {
                                                        abs.setText("Incoherencias con imagenes de soluciones " + imgSol + " de (" + pregunta + ")");
                                                    }
                                                } else {
                                                    abs.setText("Incoherencias con imagenes D " + imgD + " de (" + pregunta + ")");
                                                }
                                            } else {
                                                abs.setText("Incoherencias con imagenes C " + imgC + " de (" + pregunta + ")");
                                            }
                                        } else {
                                            abs.setText("Incoherencias con imagenes B " + imgB + " de (" + pregunta + ")");
                                        }
                                    } else {
                                        abs.setText("Incoherencias con imagenes A " + imgA + " de (" + pregunta + ")");
                                    }
                                } else {
                                    abs.setText("Incoherencias con imagenes de preguntas " + imgPre + " de (" + pregunta + ")");
                                }
                            } else {
                                abs.setText("Incoherencias con explicacones de soluciones " + expliSol + " de (" + pregunta + ")");
                            }
                        } else {
                            abs.setText("Incoherencias con soluciones " + sol + " de (" + pregunta + ")");
                        }
                    } else {
                        abs.setText("Incoherencias con respuestas D " + resD + " de (" + pregunta + ")");
                    }
                } else {
                    abs.setText("Incoherencias con respuestas C " + resC + " de (" + pregunta + ")");
                }
            } else {
                abs.setText("Incoherencias con respuestas B " + resB + " de (" + pregunta + ")");
            }
        } else {
            abs.setText("Incoherencias con respuestas A " + resA + " de (" + pregunta + ")");
        }


        pregunta = getResources().getStringArray(R.array.prememoria).length;
        resA = getResources().getStringArray(R.array.resAmemoria).length;
        resB = getResources().getStringArray(R.array.resBmemoria).length;
        resC = getResources().getStringArray(R.array.resCmemoria).length;
        resD = getResources().getStringArray(R.array.resDmemoria).length;
        sol = getResources().getStringArray(R.array.solmemoria).length;
        expliSol = getResources().getStringArray(R.array.expliSolmemoria).length;
        imgPre = getResources().getStringArray(R.array.imgPrememoria).length;
        imgA = getResources().getStringArray(R.array.imgAmemoria).length;
        imgB = getResources().getStringArray(R.array.imgBmemoria).length;
        imgC = getResources().getStringArray(R.array.imgCmemoria).length;
        imgD = getResources().getStringArray(R.array.imgDmemoria).length;
        imgSol = getResources().getStringArray(R.array.imgSolmemoria).length;
        imgExpli = getResources().getStringArray(R.array.imgExplimemoria).length;

        if (resA == pregunta) {
            if (resB == pregunta) {
                if (resC == pregunta) {
                    if (resD == pregunta) {
                        if (sol == pregunta) {
                            if (expliSol == pregunta) {
                                if (imgPre == pregunta) {
                                    if (imgA == pregunta) {
                                        if (imgB == pregunta) {
                                            if (imgC == pregunta) {
                                                if (imgD == pregunta) {
                                                    if (imgSol == pregunta) {
                                                        if (imgExpli == pregunta) {
                                                            memo.setText(getString(R.string.memoria) + " (" + pregunta + ") Correcto");
                                                        } else {
                                                            memo.setText("Incoherencias con imagenes de explicaciones " + imgExpli + " de (" + pregunta + ")");
                                                        }
                                                    } else {
                                                        memo.setText("Incoherencias con imagenes de soluciones " + imgSol + " de (" + pregunta + ")");
                                                    }
                                                } else {
                                                    memo.setText("Incoherencias con imagenes D " + imgD + " de (" + pregunta + ")");
                                                }
                                            } else {
                                                memo.setText("Incoherencias con imagenes C " + imgC + " de (" + pregunta + ")");
                                            }
                                        } else {
                                            memo.setText("Incoherencias con imagenes B " + imgB + " de (" + pregunta + ")");
                                        }
                                    } else {
                                        memo.setText("Incoherencias con imagenes A " + imgA + " de (" + pregunta + ")");
                                    }
                                } else {
                                    memo.setText("Incoherencias con imagenes de preguntas " + imgPre + " de (" + pregunta + ")");
                                }
                            } else {
                                memo.setText("Incoherencias con explicacones de soluciones " + expliSol + " de (" + pregunta + ")");
                            }
                        } else {
                            memo.setText("Incoherencias con soluciones " + sol + " de (" + pregunta + ")");
                        }
                    } else {
                        memo.setText("Incoherencias con respuestas D " + resD + " de (" + pregunta + ")");
                    }
                } else {
                    memo.setText("Incoherencias con respuestas C " + resC + " de (" + pregunta + ")");
                }
            } else {
                memo.setText("Incoherencias con respuestas B " + resB + " de (" + pregunta + ")");
            }
        } else {
            memo.setText("Incoherencias con respuestas A " + resA + " de (" + pregunta + ")");
        }

        pregunta = getResources().getStringArray(R.array.preespacial).length;
        resA = getResources().getStringArray(R.array.resAespacial).length;
        resB = getResources().getStringArray(R.array.resBespacial).length;
        resC = getResources().getStringArray(R.array.resCespacial).length;
        resD = getResources().getStringArray(R.array.resDespacial).length;
        sol = getResources().getStringArray(R.array.solespacial).length;
        expliSol = getResources().getStringArray(R.array.expliSolespacial).length;
        imgPre = getResources().getStringArray(R.array.imgPreespacial).length;
        imgA = getResources().getStringArray(R.array.imgAespacial).length;
        imgB = getResources().getStringArray(R.array.imgBespacial).length;
        imgC = getResources().getStringArray(R.array.imgCespacial).length;
        imgD = getResources().getStringArray(R.array.imgDespacial).length;
        imgSol = getResources().getStringArray(R.array.imgSolespacial).length;
        imgExpli = getResources().getStringArray(R.array.imgExpliespacial).length;

        if (resA == pregunta) {
            if (resB == pregunta) {
                if (resC == pregunta) {
                    if (resD == pregunta) {
                        if (sol == pregunta) {
                            if (expliSol == pregunta) {
                                if (imgPre == pregunta) {
                                    if (imgA == pregunta) {
                                        if (imgB == pregunta) {
                                            if (imgC == pregunta) {
                                                if (imgD == pregunta) {
                                                    if (imgSol == pregunta) {
                                                        if (imgExpli == pregunta) {
                                                            espa.setText(getString(R.string.espacial) + " (" + pregunta + ") Correcto");
                                                        } else {
                                                            espa.setText("Incoherencias con imagenes de explicaciones " + imgExpli + " de (" + pregunta + ")");
                                                        }
                                                    } else {
                                                        espa.setText("Incoherencias con imagenes de soluciones " + imgSol + " de (" + pregunta + ")");
                                                    }
                                                } else {
                                                    espa.setText("Incoherencias con imagenes D " + imgD + " de (" + pregunta + ")");
                                                }
                                            } else {
                                                espa.setText("Incoherencias con imagenes C " + imgC + " de (" + pregunta + ")");
                                            }
                                        } else {
                                            espa.setText("Incoherencias con imagenes B " + imgB + " de (" + pregunta + ")");
                                        }
                                    } else {
                                        espa.setText("Incoherencias con imagenes A " + imgA + " de (" + pregunta + ")");
                                    }
                                } else {
                                    espa.setText("Incoherencias con imagenes de preguntas " + imgPre + " de (" + pregunta + ")");
                                }
                            } else {
                                espa.setText("Incoherencias con explicacones de soluciones " + expliSol + " de (" + pregunta + ")");
                            }
                        } else {
                            espa.setText("Incoherencias con soluciones " + sol + " de (" + pregunta + ")");
                        }
                    } else {
                        espa.setText("Incoherencias con respuestas D " + resD + " de (" + pregunta + ")");
                    }
                } else {
                    espa.setText("Incoherencias con respuestas C " + resC + " de (" + pregunta + ")");
                }
            } else {
                espa.setText("Incoherencias con respuestas B " + resB + " de (" + pregunta + ")");
            }
        } else {
            espa.setText("Incoherencias con respuestas A " + resA + " de (" + pregunta + ")");
        }

        pregunta = getResources().getStringArray(R.array.preperceptiva).length;
        resA = getResources().getStringArray(R.array.resAperceptiva).length;
        resB = getResources().getStringArray(R.array.resBperceptiva).length;
        resC = getResources().getStringArray(R.array.resCperceptiva).length;
        resD = getResources().getStringArray(R.array.resDperceptiva).length;
        sol = getResources().getStringArray(R.array.solperceptiva).length;
        expliSol = getResources().getStringArray(R.array.expliSolperceptiva).length;
        imgPre = getResources().getStringArray(R.array.imgPreperceptiva).length;
        imgA = getResources().getStringArray(R.array.imgAperceptiva).length;
        imgB = getResources().getStringArray(R.array.imgBperceptiva).length;
        imgC = getResources().getStringArray(R.array.imgCperceptiva).length;
        imgD = getResources().getStringArray(R.array.imgDperceptiva).length;
        imgSol = getResources().getStringArray(R.array.imgSolperceptiva).length;
        imgExpli = getResources().getStringArray(R.array.imgExpliperceptiva).length;

        if (resA == pregunta) {
            if (resB == pregunta) {
                if (resC == pregunta) {
                    if (resD == pregunta) {
                        if (sol == pregunta) {
                            if (expliSol == pregunta) {
                                if (imgPre == pregunta) {
                                    if (imgA == pregunta) {
                                        if (imgB == pregunta) {
                                            if (imgC == pregunta) {
                                                if (imgD == pregunta) {
                                                    if (imgSol == pregunta) {
                                                        if (imgExpli == pregunta) {
                                                            perc.setText(getString(R.string.perceptiva) + " (" + pregunta + ") Correcto");
                                                        } else {
                                                            perc.setText("Incoherencias con imagenes de explicaciones " + imgExpli + " de (" + pregunta + ")");
                                                        }
                                                    } else {
                                                        perc.setText("Incoherencias con imagenes de soluciones " + imgSol + " de (" + pregunta + ")");
                                                    }
                                                } else {
                                                    perc.setText("Incoherencias con imagenes D " + imgD + " de (" + pregunta + ")");
                                                }
                                            } else {
                                                perc.setText("Incoherencias con imagenes C " + imgC + " de (" + pregunta + ")");
                                            }
                                        } else {
                                            perc.setText("Incoherencias con imagenes B " + imgB + " de (" + pregunta + ")");
                                        }
                                    } else {
                                        perc.setText("Incoherencias con imagenes A " + imgA + " de (" + pregunta + ")");
                                    }
                                } else {
                                    perc.setText("Incoherencias con imagenes de preguntas " + imgPre + " de (" + pregunta + ")");
                                }
                            } else {
                                perc.setText("Incoherencias con explicacones de soluciones " + expliSol + " de (" + pregunta + ")");
                            }
                        } else {
                            perc.setText("Incoherencias con soluciones " + sol + " de (" + pregunta + ")");
                        }
                    } else {
                        perc.setText("Incoherencias con respuestas D " + resD + " de (" + pregunta + ")");
                    }
                } else {
                    perc.setText("Incoherencias con respuestas C " + resC + " de (" + pregunta + ")");
                }
            } else {
                perc.setText("Incoherencias con respuestas B " + resB + " de (" + pregunta + ")");
            }
        } else {
            perc.setText("Incoherencias con respuestas A " + resA + " de (" + pregunta + ")");
        }

        final TextView dato = (TextView) findViewById(R.id.datos);
        final TextView b = (TextView) findViewById(R.id.b);
        final TextView c = (TextView) findViewById(R.id.c);
        final TextView d = (TextView) findViewById(R.id.d);
        final TextView e = (TextView) findViewById(R.id.e);
        final TextView f = (TextView) findViewById(R.id.f);
        final TextView g = (TextView) findViewById(R.id.g);


        final TextView h = (TextView) findViewById(R.id.h);
        final TextView i = (TextView) findViewById(R.id.i);


        final Button a1 = (Button) findViewById(R.id.a1);
        final Button b1 = (Button) findViewById(R.id.b1);
        final Button c1 = (Button) findViewById(R.id.c1);
        final Button d1 = (Button) findViewById(R.id.d1);
        final Button e1 = (Button) findViewById(R.id.e1);
        final Button f1 = (Button) findViewById(R.id.f1);
        final Button g1 = (Button) findViewById(R.id.g1);


        final Button h1 = (Button) findViewById(R.id.h1);
        final Button i1 = (Button) findViewById(R.id.i1);

        dato.setVisibility(View.GONE);

        if (datos("verbal") || datos("numerico") || datos("espacial") || datos("mecanico") || datos("perceptiva") || datos("memoria") || datos("abstrapto") || datobaremo() || datoevo()) {
            if (datos("verbal")) {
                dato.setVisibility(View.VISIBLE);
                dato.setText("Archivos de datos de verbal encontrados");
                a1.setVisibility(View.VISIBLE);
            }
            if (datos("numerico")) {
                b.setVisibility(View.VISIBLE);
                b.setText("Archivos de datos de numerico encontrados");
                b1.setVisibility(View.VISIBLE);
            }
            if (datos("espacial")) {
                c.setVisibility(View.VISIBLE);
                c.setText("Archivos de datos de espacial encontrados");
                c1.setVisibility(View.VISIBLE);
            }
            if (datos("mecanico")) {
                d.setVisibility(View.VISIBLE);
                d.setText("Archivos de datos de mecanico encontrados");
                d1.setVisibility(View.VISIBLE);
            }
            if (datos("perceptiva")) {
                e.setVisibility(View.VISIBLE);
                e.setText("Archivos de datos de perceptiva encontrados");
                e1.setVisibility(View.VISIBLE);
            }
            if (datos("memoria")) {
                f.setVisibility(View.VISIBLE);
                f.setText("Archivos de datos de memoria encontrados");
                f1.setVisibility(View.VISIBLE);
            }
            if (datos("abstrapto")) {
                g.setVisibility(View.VISIBLE);
                g.setText("Archivos de datos de abstrapto encontrados");
                g1.setVisibility(View.VISIBLE);
            }
            if (datobaremo()) {
                h.setVisibility(View.VISIBLE);
                h.setText("Archivo de dato de baremo encontrados");
                h1.setVisibility(View.VISIBLE);
            }
            if (datoevo()) {
                i.setVisibility(View.VISIBLE);
                i1.setVisibility(View.VISIBLE);
            }
////////////////////////////////////////////////////////////
        } else {
            dato.setVisibility(View.VISIBLE);
            dato.setText("No se han encontrado archivos de datos guardado");
        }


        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(main_info.this)
                        .setIcon(getResources().getDrawable(R.drawable.iborrar))
                        .setTitle(getString(R.string.borrar))
                        .setCancelable(false)
                        .setMessage(getString(R.string.borrardatos))
                        .setNegativeButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                a1.setVisibility(View.GONE);
                                borrar("verbal");
                                if (!datos("verbal") && !datos("numerico") && !datos("espacial") && !datos("mecanico") && !datos("perceptiva") && !datos("memoria") && !datos("abstrapto") && !datobaremo() && !datoevo()) {
                                    dato.setVisibility(View.VISIBLE);
                                    dato.setText("No se han encontrado archivos de datos guardado");
                                } else {
                                    dato.setVisibility(View.GONE);
                                }
                            }
                        })
                        .setPositiveButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                            }
                        }).show();

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(main_info.this)
                        .setIcon(getResources().getDrawable(R.drawable.iborrar))
                        .setTitle(getString(R.string.borrar))
                        .setCancelable(false)
                        .setMessage(getString(R.string.borrardatos))
                        .setNegativeButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                borrar("numerico");
                                b1.setVisibility(View.GONE);
                                b.setVisibility(View.GONE);
                                if (!datos("verbal") && !datos("numerico") && !datos("espacial") && !datos("mecanico") && !datos("perceptiva") && !datos("memoria") && !datos("abstrapto") && !datobaremo() && !datoevo()) {
                                    dato.setVisibility(View.VISIBLE);
                                    dato.setText("No se han encontrado archivos de datos guardado");
                                }
                            }
                        })
                        .setPositiveButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                            }
                        }).show();

            }
        });
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(main_info.this)
                        .setIcon(getResources().getDrawable(R.drawable.iborrar))
                        .setTitle(getString(R.string.borrar))
                        .setCancelable(false)
                        .setMessage(getString(R.string.borrardatos))
                        .setNegativeButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                borrar("espacial");
                                c1.setVisibility(View.GONE);
                                c.setVisibility(View.GONE);
                                if (!datos("verbal") && !datos("numerico") && !datos("espacial") && !datos("mecanico") && !datos("perceptiva") && !datos("memoria") && !datos("abstrapto") && !datobaremo() && !datoevo()) {
                                    dato.setVisibility(View.VISIBLE);
                                    dato.setText("No se han encontrado archivos de datos guardado");
                                }
                            }
                        })
                        .setPositiveButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                            }
                        }).show();

            }
        });
        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(main_info.this)
                        .setIcon(getResources().getDrawable(R.drawable.iborrar))
                        .setTitle(getString(R.string.borrar))
                        .setCancelable(false)
                        .setMessage(getString(R.string.borrardatos))
                        .setNegativeButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                borrar("mecanico");
                                d1.setVisibility(View.GONE);
                                d.setVisibility(View.GONE);
                                if (!datos("verbal") && !datos("numerico") && !datos("espacial") && !datos("mecanico") && !datos("perceptiva") && !datos("memoria") && !datos("abstrapto") && !datobaremo() && !datoevo()) {
                                    dato.setVisibility(View.VISIBLE);
                                    dato.setText("No se han encontrado archivos de datos guardado");
                                }
                            }
                        })
                        .setPositiveButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                            }
                        }).show();

            }
        });
        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(main_info.this)
                        .setIcon(getResources().getDrawable(R.drawable.iborrar))
                        .setTitle(getString(R.string.borrar))
                        .setCancelable(false)
                        .setMessage(getString(R.string.borrardatos))
                        .setNegativeButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                borrar("perceptiva");
                                e1.setVisibility(View.GONE);
                                e.setVisibility(View.GONE);
                                if (!datos("verbal") && !datos("numerico") && !datos("espacial") && !datos("mecanico") && !datos("perceptiva") && !datos("memoria") && !datos("abstrapto") && !datobaremo() && !datoevo()) {
                                    dato.setVisibility(View.VISIBLE);
                                    dato.setText("No se han encontrado archivos de datos guardado");
                                }
                            }
                        })
                        .setPositiveButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                            }
                        }).show();

            }
        });
        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(main_info.this)
                        .setIcon(getResources().getDrawable(R.drawable.iborrar))
                        .setTitle(getString(R.string.borrar))
                        .setCancelable(false)
                        .setMessage(getString(R.string.borrardatos))
                        .setNegativeButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                borrar("memoria");
                                f1.setVisibility(View.GONE);
                                f.setVisibility(View.GONE);
                                if (!datos("verbal") && !datos("numerico") && !datos("espacial") && !datos("mecanico") && !datos("perceptiva") && !datos("memoria") && !datos("abstrapto") && !datobaremo() && !datoevo()) {
                                    dato.setVisibility(View.VISIBLE);
                                    dato.setText("No se han encontrado archivos de datos guardado");
                                }
                            }
                        })
                        .setPositiveButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                            }
                        }).show();

            }
        });
        g1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(main_info.this)
                        .setIcon(getResources().getDrawable(R.drawable.iborrar))
                        .setTitle(getString(R.string.borrar))
                        .setCancelable(false)
                        .setMessage(getString(R.string.borrardatos))
                        .setNegativeButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                borrar("abstrapto");
                                g1.setVisibility(View.GONE);
                                g.setVisibility(View.GONE);
                                if (!datos("verbal") && !datos("numerico") && !datos("espacial") && !datos("mecanico") && !datos("perceptiva") && !datos("memoria") && !datos("abstrapto") && !datobaremo() && !datoevo()) {
                                    dato.setVisibility(View.VISIBLE);
                                    dato.setText("No se han encontrado archivos de datos guardado");
                                }
                            }
                        })
                        .setPositiveButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                            }
                        }).show();

            }
        });
        h1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(main_info.this)
                        .setIcon(getResources().getDrawable(R.drawable.iborrar))
                        .setTitle(getString(R.string.borrar))
                        .setCancelable(false)
                        .setMessage(getString(R.string.borrardatos))
                        .setNegativeButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                borrarbar();
                                h1.setVisibility(View.GONE);
                                h.setVisibility(View.GONE);
                                if (!datos("verbal") && !datos("numerico") && !datos("espacial") && !datos("mecanico") && !datos("perceptiva") && !datos("memoria") && !datos("abstrapto") && !datobaremo() && !datoevo()) {
                                    dato.setVisibility(View.VISIBLE);
                                    dato.setText("No se han encontrado archivos de datos guardado");
                                }
                            }
                        })
                        .setPositiveButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                            }
                        }).show();

            }
        });
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(main_info.this)
                        .setIcon(getResources().getDrawable(R.drawable.iborrar))
                        .setTitle(getString(R.string.borrar))
                        .setCancelable(false)
                        .setMessage(getString(R.string.borrardatos))
                        .setNegativeButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                borrarevo();
                                i1.setVisibility(View.GONE);
                                i.setVisibility(View.GONE);
                            }
                        })
                        .setPositiveButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                            }
                        }).show();
                if (!datos("verbal") && !datos("numerico") && !datos("espacial") && !datos("mecanico") && !datos("perceptiva") && !datos("memoria") && !datos("abstrapto") && !datobaremo() && !datoevo()) {
                    dato.setVisibility(View.VISIBLE);
                    dato.setText("No se han encontrado archivos de datos guardado");
                }
            }
        });

        String version = String.valueOf((BuildConfig.VERSION_NAME));
        TextView versi = (TextView) findViewById(R.id.version);
        versi.setText(version);

    }

    private boolean datos(String tipo) {
        boolean encontrado = false;
        File ruta_sd;
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            ruta_sd = getExternalFilesDir(null);
        } else {
            // Load another directory, probably local memory
            ruta_sd = getFilesDir();
        }
        final File a = new File(ruta_sd.getAbsolutePath(), tipo + "estudio");

        if (a.exists()) {
            encontrado = true;
        }
        return encontrado;
    }

    private boolean datobaremo() {
        boolean encontrado = false;
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
        if (f.exists()) {
            encontrado = true;
        }
        return encontrado;
    }

    private boolean datoevo() {
        boolean encontrado = false;
        File ruta_sd;
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            ruta_sd = getExternalFilesDir(null);
        } else {
            // Load another directory, probably local memory
            ruta_sd = getFilesDir();
        }
        File[] ficheros = ruta_sd.listFiles();
        final List<String> nombrefiche = new ArrayList<String>();
        for (int x = 0; x < ficheros.length; x++) {
            String kk = (ficheros[x].getName());
            if (kk.length() > 25 && kk.substring(19, 25).equals("examen")) {
                nombrefiche.add((ficheros[x].getName()));
                encontrado = true;
            }
        }
        return encontrado;
    }

    private void borrarevo() {
        File ruta_sd;
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            ruta_sd = getExternalFilesDir(null);
        } else {
            // Load another directory, probably local memory
            ruta_sd = getFilesDir();
        }
        File[] ficheros = ruta_sd.listFiles();
        File a = null;
        for (int x = 0; x < ficheros.length; x++) {
            String kk = (ficheros[x].getName());
            if (kk.length() > 25 && kk.substring(19, 25).equals("examen")) {
                a = new File(ruta_sd.getAbsolutePath(), (ficheros[x].getName()));
                a.delete();
            }
        }

    }

    private void borrar(String tipo) {
        File ruta_sd;
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            ruta_sd = getExternalFilesDir(null);
        } else {
            // Load another directory, probably local memory
            ruta_sd = getFilesDir();
        }
        final File a = new File(ruta_sd.getAbsolutePath(), tipo + "estudio");
        a.delete();
    }

    private void borrarbar() {
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
        f.delete();
    }
}
