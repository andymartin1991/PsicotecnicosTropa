package com.example.andym.psicotecnicostropa;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by andym on 04/06/2017.
 */

public class main_info extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_info);



        TextView num = (TextView)findViewById(R.id.num);
        TextView verb = (TextView)findViewById(R.id.verb);
        TextView meca = (TextView)findViewById(R.id.meca);
        TextView abs = (TextView)findViewById(R.id.abs);
        TextView memo = (TextView)findViewById(R.id.memo);
        TextView espa = (TextView)findViewById(R.id.espa);
        TextView perc = (TextView)findViewById(R.id.perc);


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

            if(resA == pregunta){
                if(resB == pregunta){
                    if(resC == pregunta){
                        if(resD == pregunta){
                            if(sol == pregunta){
                                if(expliSol == pregunta){
                                    if(imgPre == pregunta){
                                        if(imgA == pregunta){
                                            if(imgB == pregunta){
                                                if(imgC == pregunta){
                                                    if(imgD == pregunta){
                                                        if(imgSol == pregunta){
                                                            if(imgExpli == pregunta){
                                                                verb.setText(getString(R.string.verbal)+" ("+pregunta+") Correcto");
                                                            }else{
                                                                verb.setText("Incoherencias con imagenes de explicaciones "+imgExpli+" de (" +pregunta+")");
                                                            }
                                                        }else{
                                                            verb.setText("Incoherencias con imagenes de soluciones "+imgSol+" de (" +pregunta+")");
                                                        }
                                                    }else{
                                                        verb.setText("Incoherencias con imagenes D "+imgD+" de (" +pregunta+")");
                                                    }
                                                }else{
                                                    verb.setText("Incoherencias con imagenes C "+imgC+" de (" +pregunta+")");
                                                }
                                            }else{
                                                verb.setText("Incoherencias con imagenes B "+imgB+" de (" +pregunta+")");
                                            }
                                        }else{
                                            verb.setText("Incoherencias con imagenes A "+imgA+" de (" +pregunta+")");
                                        }
                                    }else{
                                        verb.setText("Incoherencias con imagenes de preguntas "+imgPre+" de (" +pregunta+")");
                                    }
                                }else{
                                    verb.setText("Incoherencias con explicacones de soluciones "+expliSol+" de (" +pregunta+")");
                                }
                            }else{
                                verb.setText("Incoherencias con soluciones "+sol+" de (" +pregunta+")");
                            }
                        }else{
                            verb.setText("Incoherencias con respuestas D "+resD+" de (" +pregunta+")");
                        }
                    }else{
                        verb.setText("Incoherencias con respuestas C "+resC+" de (" +pregunta+")");
                    }
                }else{
                    verb.setText("Incoherencias con respuestas B "+resB+" de (" +pregunta+")");
                }
            }else{
                verb.setText("Incoherencias con respuestas A "+resA+" de (" +pregunta+")");
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

        if(resA == pregunta){
            if(resB == pregunta){
                if(resC == pregunta){
                    if(resD == pregunta){
                        if(sol == pregunta){
                            if(expliSol == pregunta){
                                if(imgPre == pregunta){
                                    if(imgA == pregunta){
                                        if(imgB == pregunta){
                                            if(imgC == pregunta){
                                                if(imgD == pregunta){
                                                    if(imgSol == pregunta){
                                                        if(imgExpli == pregunta){
                                                            num.setText(getString(R.string.numerico)+" ("+pregunta+") Correcto");
                                                        }else{
                                                            num.setText("Incoherencias con imagenes de explicaciones "+imgExpli+" de (" +pregunta+")");
                                                        }
                                                    }else{
                                                        num.setText("Incoherencias con imagenes de soluciones "+imgSol+" de (" +pregunta+")");
                                                    }
                                                }else{
                                                    num.setText("Incoherencias con imagenes D "+imgD+" de (" +pregunta+")");
                                                }
                                            }else{
                                                num.setText("Incoherencias con imagenes C "+imgC+" de (" +pregunta+")");
                                            }
                                        }else{
                                            num.setText("Incoherencias con imagenes B "+imgB+" de (" +pregunta+")");
                                        }
                                    }else{
                                        num.setText("Incoherencias con imagenes A "+imgA+" de (" +pregunta+")");
                                    }
                                }else{
                                    num.setText("Incoherencias con imagenes de preguntas "+imgPre+" de (" +pregunta+")");
                                }
                            }else{
                                num.setText("Incoherencias con explicacones de soluciones "+expliSol+" de (" +pregunta+")");
                            }
                        }else{
                            num.setText("Incoherencias con soluciones "+sol+" de (" +pregunta+")");
                        }
                    }else{
                        num.setText("Incoherencias con respuestas D "+resD+" de (" +pregunta+")");
                    }
                }else{
                    num.setText("Incoherencias con respuestas C "+resC+" de (" +pregunta+")");
                }
            }else{
                num.setText("Incoherencias con respuestas B "+resB+" de (" +pregunta+")");
            }
        }else{
            num.setText("Incoherencias con respuestas A "+resA+" de (" +pregunta+")");
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

        if(resA == pregunta){
            if(resB == pregunta){
                if(resC == pregunta){
                    if(resD == pregunta){
                        if(sol == pregunta){
                            if(expliSol == pregunta){
                                if(imgPre == pregunta){
                                    if(imgA == pregunta){
                                        if(imgB == pregunta){
                                            if(imgC == pregunta){
                                                if(imgD == pregunta){
                                                    if(imgSol == pregunta){
                                                        if(imgExpli == pregunta){
                                                            meca.setText(getString(R.string.mecanico)+" ("+pregunta+") Correcto");
                                                        }else{
                                                            meca.setText("Incoherencias con imagenes de explicaciones "+imgExpli+" de (" +pregunta+")");
                                                        }
                                                    }else{
                                                        meca.setText("Incoherencias con imagenes de soluciones "+imgSol+" de (" +pregunta+")");
                                                    }
                                                }else{
                                                    meca.setText("Incoherencias con imagenes D "+imgD+" de (" +pregunta+")");
                                                }
                                            }else{
                                                meca.setText("Incoherencias con imagenes C "+imgC+" de (" +pregunta+")");
                                            }
                                        }else{
                                            meca.setText("Incoherencias con imagenes B "+imgB+" de (" +pregunta+")");
                                        }
                                    }else{
                                        meca.setText("Incoherencias con imagenes A "+imgA+" de (" +pregunta+")");
                                    }
                                }else{
                                    meca.setText("Incoherencias con imagenes de preguntas "+imgPre+" de (" +pregunta+")");
                                }
                            }else{
                                meca.setText("Incoherencias con explicacones de soluciones "+expliSol+" de (" +pregunta+")");
                            }
                        }else{
                            meca.setText("Incoherencias con soluciones "+sol+" de (" +pregunta+")");
                        }
                    }else{
                        meca.setText("Incoherencias con respuestas D "+resD+" de (" +pregunta+")");
                    }
                }else{
                    meca.setText("Incoherencias con respuestas C "+resC+" de (" +pregunta+")");
                }
            }else{
                meca.setText("Incoherencias con respuestas B "+resB+" de (" +pregunta+")");
            }
        }else{
            meca.setText("Incoherencias con respuestas A "+resA+" de (" +pregunta+")");
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

        if(resA == pregunta){
            if(resB == pregunta){
                if(resC == pregunta){
                    if(resD == pregunta){
                        if(sol == pregunta){
                            if(expliSol == pregunta){
                                if(imgPre == pregunta){
                                    if(imgA == pregunta){
                                        if(imgB == pregunta){
                                            if(imgC == pregunta){
                                                if(imgD == pregunta){
                                                    if(imgSol == pregunta){
                                                        if(imgExpli == pregunta){
                                                            abs.setText(getString(R.string.abstrapto)+" ("+pregunta+") Correcto");
                                                        }else{
                                                            abs.setText("Incoherencias con imagenes de explicaciones "+imgExpli+" de (" +pregunta+")");
                                                        }
                                                    }else{
                                                        abs.setText("Incoherencias con imagenes de soluciones "+imgSol+" de (" +pregunta+")");
                                                    }
                                                }else{
                                                    abs.setText("Incoherencias con imagenes D "+imgD+" de (" +pregunta+")");
                                                }
                                            }else{
                                                abs.setText("Incoherencias con imagenes C "+imgC+" de (" +pregunta+")");
                                            }
                                        }else{
                                            abs.setText("Incoherencias con imagenes B "+imgB+" de (" +pregunta+")");
                                        }
                                    }else{
                                        abs.setText("Incoherencias con imagenes A "+imgA+" de (" +pregunta+")");
                                    }
                                }else{
                                    abs.setText("Incoherencias con imagenes de preguntas "+imgPre+" de (" +pregunta+")");
                                }
                            }else{
                                abs.setText("Incoherencias con explicacones de soluciones "+expliSol+" de (" +pregunta+")");
                            }
                        }else{
                            abs.setText("Incoherencias con soluciones "+sol+" de (" +pregunta+")");
                        }
                    }else{
                        abs.setText("Incoherencias con respuestas D "+resD+" de (" +pregunta+")");
                    }
                }else{
                    abs.setText("Incoherencias con respuestas C "+resC+" de (" +pregunta+")");
                }
            }else{
                abs.setText("Incoherencias con respuestas B "+resB+" de (" +pregunta+")");
            }
        }else{
            abs.setText("Incoherencias con respuestas A "+resA+" de (" +pregunta+")");
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

        if(resA == pregunta){
            if(resB == pregunta){
                if(resC == pregunta){
                    if(resD == pregunta){
                        if(sol == pregunta){
                            if(expliSol == pregunta){
                                if(imgPre == pregunta){
                                    if(imgA == pregunta){
                                        if(imgB == pregunta){
                                            if(imgC == pregunta){
                                                if(imgD == pregunta){
                                                    if(imgSol == pregunta){
                                                        if(imgExpli == pregunta){
                                                            memo.setText(getString(R.string.memoria)+" ("+pregunta+") Correcto");
                                                        }else{
                                                            memo.setText("Incoherencias con imagenes de explicaciones "+imgExpli+" de (" +pregunta+")");
                                                        }
                                                    }else{
                                                        memo.setText("Incoherencias con imagenes de soluciones "+imgSol+" de (" +pregunta+")");
                                                    }
                                                }else{
                                                    memo.setText("Incoherencias con imagenes D "+imgD+" de (" +pregunta+")");
                                                }
                                            }else{
                                                memo.setText("Incoherencias con imagenes C "+imgC+" de (" +pregunta+")");
                                            }
                                        }else{
                                            memo.setText("Incoherencias con imagenes B "+imgB+" de (" +pregunta+")");
                                        }
                                    }else{
                                        memo.setText("Incoherencias con imagenes A "+imgA+" de (" +pregunta+")");
                                    }
                                }else{
                                    memo.setText("Incoherencias con imagenes de preguntas "+imgPre+" de (" +pregunta+")");
                                }
                            }else{
                                memo.setText("Incoherencias con explicacones de soluciones "+expliSol+" de (" +pregunta+")");
                            }
                        }else{
                            memo.setText("Incoherencias con soluciones "+sol+" de (" +pregunta+")");
                        }
                    }else{
                        memo.setText("Incoherencias con respuestas D "+resD+" de (" +pregunta+")");
                    }
                }else{
                    memo.setText("Incoherencias con respuestas C "+resC+" de (" +pregunta+")");
                }
            }else{
                memo.setText("Incoherencias con respuestas B "+resB+" de (" +pregunta+")");
            }
        }else{
            memo.setText("Incoherencias con respuestas A "+resA+" de (" +pregunta+")");
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

        if(resA == pregunta){
            if(resB == pregunta){
                if(resC == pregunta){
                    if(resD == pregunta){
                        if(sol == pregunta){
                            if(expliSol == pregunta){
                                if(imgPre == pregunta){
                                    if(imgA == pregunta){
                                        if(imgB == pregunta){
                                            if(imgC == pregunta){
                                                if(imgD == pregunta){
                                                    if(imgSol == pregunta){
                                                        if(imgExpli == pregunta){
                                                            espa.setText(getString(R.string.espacial)+" ("+pregunta+") Correcto");
                                                        }else{
                                                            espa.setText("Incoherencias con imagenes de explicaciones "+imgExpli+" de (" +pregunta+")");
                                                        }
                                                    }else{
                                                        espa.setText("Incoherencias con imagenes de soluciones "+imgSol+" de (" +pregunta+")");
                                                    }
                                                }else{
                                                    espa.setText("Incoherencias con imagenes D "+imgD+" de (" +pregunta+")");
                                                }
                                            }else{
                                                espa.setText("Incoherencias con imagenes C "+imgC+" de (" +pregunta+")");
                                            }
                                        }else{
                                            espa.setText("Incoherencias con imagenes B "+imgB+" de (" +pregunta+")");
                                        }
                                    }else{
                                        espa.setText("Incoherencias con imagenes A "+imgA+" de (" +pregunta+")");
                                    }
                                }else{
                                    espa.setText("Incoherencias con imagenes de preguntas "+imgPre+" de (" +pregunta+")");
                                }
                            }else{
                                espa.setText("Incoherencias con explicacones de soluciones "+expliSol+" de (" +pregunta+")");
                            }
                        }else{
                            espa.setText("Incoherencias con soluciones "+sol+" de (" +pregunta+")");
                        }
                    }else{
                        espa.setText("Incoherencias con respuestas D "+resD+" de (" +pregunta+")");
                    }
                }else{
                    espa.setText("Incoherencias con respuestas C "+resC+" de (" +pregunta+")");
                }
            }else{
                espa.setText("Incoherencias con respuestas B "+resB+" de (" +pregunta+")");
            }
        }else{
            espa.setText("Incoherencias con respuestas A "+resA+" de (" +pregunta+")");
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

        if(resA == pregunta){
            if(resB == pregunta){
                if(resC == pregunta){
                    if(resD == pregunta){
                        if(sol == pregunta){
                            if(expliSol == pregunta){
                                if(imgPre == pregunta){
                                    if(imgA == pregunta){
                                        if(imgB == pregunta){
                                            if(imgC == pregunta){
                                                if(imgD == pregunta){
                                                    if(imgSol == pregunta){
                                                        if(imgExpli == pregunta){
                                                            perc.setText(getString(R.string.perceptiva)+" ("+pregunta+") Correcto");
                                                        }else{
                                                            perc.setText("Incoherencias con imagenes de explicaciones "+imgExpli+" de (" +pregunta+")");
                                                        }
                                                    }else{
                                                        perc.setText("Incoherencias con imagenes de soluciones "+imgSol+" de (" +pregunta+")");
                                                    }
                                                }else{
                                                    perc.setText("Incoherencias con imagenes D "+imgD+" de (" +pregunta+")");
                                                }
                                            }else{
                                                perc.setText("Incoherencias con imagenes C "+imgC+" de (" +pregunta+")");
                                            }
                                        }else{
                                            perc.setText("Incoherencias con imagenes B "+imgB+" de (" +pregunta+")");
                                        }
                                    }else{
                                        perc.setText("Incoherencias con imagenes A "+imgA+" de (" +pregunta+")");
                                    }
                                }else{
                                    perc.setText("Incoherencias con imagenes de preguntas "+imgPre+" de (" +pregunta+")");
                                }
                            }else{
                                perc.setText("Incoherencias con explicacones de soluciones "+expliSol+" de (" +pregunta+")");
                            }
                        }else{
                            perc.setText("Incoherencias con soluciones "+sol+" de (" +pregunta+")");
                        }
                    }else{
                        perc.setText("Incoherencias con respuestas D "+resD+" de (" +pregunta+")");
                    }
                }else{
                    perc.setText("Incoherencias con respuestas C "+resC+" de (" +pregunta+")");
                }
            }else{
                perc.setText("Incoherencias con respuestas B "+resB+" de (" +pregunta+")");
            }
        }else{
            perc.setText("Incoherencias con respuestas A "+resA+" de (" +pregunta+")");
        }
    }
}
