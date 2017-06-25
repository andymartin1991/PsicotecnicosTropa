package com.example.andym.psicotecnicostropa;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andym.psicotecnicostropa.dto.Notas;
import com.example.andym.psicotecnicostropa.dto.Preguntas;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by andym on 16/06/2017.
 */

public class main_evolucion extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_evolucion);

        final File ruta_sd = getExternalFilesDir(null);
        final TextView titulo = (TextView) findViewById(R.id.titulo);

        Notas exam = new Notas();

        List<String> namefichbonito = new ArrayList<String>();

        if (ruta_sd.exists()) {
            File[] ficheros = ruta_sd.listFiles();
            final List<String> nombrefiche = new ArrayList<String>();

            for (int x = 0; x < ficheros.length; x++) {
                String kk = (ficheros[x].getName());

                if (kk.length() > 25 && kk.substring(19, 25).equals("examen")) {
                    namefichbonito.add(kk.substring(0, 19) + " Nota: " + kk.substring(25, kk.length()));
                    nombrefiche.add((ficheros[x].getName()));
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, namefichbonito);
            ListView lista = (ListView) findViewById(R.id.lista);
            lista.setAdapter(adapter);

            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView adapter, View view, int position, long arg) {
                    File a = new File(ruta_sd.getAbsolutePath(), nombrefiche.get(position));
                    String json = "";
                    try {
                        BufferedReader fin = new BufferedReader(new InputStreamReader(new FileInputStream(a)));
                        json = fin.readLine();
                        fin.close();
                    } catch (IOException e) {

                    }
                    String myJsonString = json;
                    Notas notas = new Notas();
                    try {
                        JSONObject myJsonjObject = new JSONObject(myJsonString);
                        notas.setNotaredondeadabar(myJsonjObject.getDouble("notaredondeadabar"));
                        notas.setNotaredondeada(myJsonjObject.getDouble("notaredondeada"));
                        notas.setBar(myJsonjObject.getDouble("bar"));
                        notas.setNotasobre10(myJsonjObject.getDouble("notasobre10"));
                        notas.setBaremoredondeado(myJsonjObject.getDouble("baremoredondeado"));

                        notas.setAciertosVerbal(myJsonjObject.getInt("aciertosVerbal"));
                        notas.setFallosVerbal(myJsonjObject.getInt("fallosVerbal"));
                        notas.setSincontestarVerbal(myJsonjObject.getInt("sincontestarVerbal"));

                        notas.setAciertosNumerico(myJsonjObject.getInt("aciertosNumerico"));
                        notas.setFallosNumerico(myJsonjObject.getInt("fallosNumerico"));
                        notas.setSincontestarNumerico(myJsonjObject.getInt("sincontestarNumerico"));

                        notas.setAciertosEspacial(myJsonjObject.getInt("aciertosEspacial"));
                        notas.setFallosEspacial(myJsonjObject.getInt("fallosEspacial"));
                        notas.setSincontestarEspacial(myJsonjObject.getInt("sincontestarEspacial"));

                        notas.setAciertosMecanico(myJsonjObject.getInt("aciertosMecanico"));
                        notas.setFallosMecanico(myJsonjObject.getInt("fallosMecanico"));
                        notas.setSincontestarMecanico(myJsonjObject.getInt("sincontestarMecanico"));

                        notas.setAciertosPerceptiva(myJsonjObject.getInt("aciertosPerceptiva"));
                        notas.setFallosPerceptiva(myJsonjObject.getInt("fallosPerceptiva"));
                        notas.setSincontestarPerceptiva(myJsonjObject.getInt("sincontestarPerceptiva"));

                        notas.setAciertosMemoria(myJsonjObject.getInt("aciertosMemoria"));
                        notas.setFallosMemoria(myJsonjObject.getInt("fallosMemoria"));
                        notas.setSincontestarMemoria(myJsonjObject.getInt("sincontestarMemoria"));

                        notas.setAciertosAbstrapto(myJsonjObject.getInt("aciertosAbstrapto"));
                        notas.setFallosAbstrapto(myJsonjObject.getInt("fallosAbstrapto"));
                        notas.setSincontestarAbstrapto(myJsonjObject.getInt("sincontestarAbstrapto"));

                        JSONArray myJsonArray = myJsonjObject.getJSONArray("bloqueverbal");
                        List <Preguntas> pregu = new ArrayList<Preguntas>();
                        for (int i = 0; i < 15; i++) {
                            try {
                                JSONObject oneObject = myJsonArray.getJSONObject(i);
                                Preguntas preguntas = new Preguntas();
                                preguntas.setCont(oneObject.getInt("cont"));
                                preguntas.setPregunta(oneObject.getString("pregunta"));
                                preguntas.setRespuestaA(oneObject.getString("respuestaA"));
                                preguntas.setRespuestaB(oneObject.getString("respuestaB"));
                                preguntas.setRespuestaC(oneObject.getString("respuestaC"));
                                preguntas.setRespuestaD(oneObject.getString("respuestaD"));
                                preguntas.setSolu(oneObject.getString("solu"));
                                preguntas.setExpliSol(oneObject.getString("expliSol"));
                                preguntas.setImgPregunta(oneObject.getString("imgPregunta"));
                                preguntas.setImgA(oneObject.getString("imgA"));
                                preguntas.setImgB(oneObject.getString("imgB"));
                                preguntas.setImgC(oneObject.getString("imgC"));
                                preguntas.setImgD(oneObject.getString("imgD"));
                                preguntas.setImgSol(oneObject.getString("imgSol"));
                                preguntas.setImgExpli(oneObject.getString("imgExpli"));
                                preguntas.setRespulsada(oneObject.getInt("respulsada"));
                                pregu.add(preguntas);
                            } catch (JSONException e) {
                                Log.e(TAG, e.toString());
                            }
                        }
                        notas.setBloqueverbal(pregu);

                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),notas.getNotaredondeadabar()+"", Toast.LENGTH_SHORT);
                        toast1.show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });

        } else {
            //no hay datos de evolucion
        }

        //titulo.setText(namefichbonito.get(0));
    }
}
