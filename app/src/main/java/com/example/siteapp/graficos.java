package com.example.siteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.siteapp.databinding.ActivityGraficosBinding;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class graficos extends AppCompatActivity {

    ActivityGraficosBinding layout;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout=ActivityGraficosBinding.inflate(getLayoutInflater());

        setContentView(layout.getRoot());

        GraphView graph=layout.myGraph;


        String URL = "http://192.168.101.5/conexion_php/graficos.php";


            JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Log.i("result",response.toString());
                    JSONArray barras = null;

                    try {

                        barras = response.getJSONArray(1);
                        int[] y = new int[barras.length()];

                        for(int c=0;c<barras.length();c++){
                            y[c]= (int) barras.get(c);
                        }

                        /**/


                        DataPoint[] cordenadas= new DataPoint[barras.length()];

                        for (int cont=0;cont<barras.length();cont++)
                        {
                            cordenadas[cont]=new DataPoint(cont,y[cont]);
                        }

                        BarGraphSeries<DataPoint> series=new BarGraphSeries<>(cordenadas);
                        graph.getViewport().setScalable(true);
                        graph.getViewport().setScrollable(true);
                        graph.addSeries(series);


                        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                            @Override
                            public int get(DataPoint data) {
                                //return Color.rgb(100,60,100);
                                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
                            }
                        });

                        series.setSpacing(50);

                        series.setDrawValuesOnTop(true);
                        series.setValuesOnTopColor(Color.CYAN);

                        /******/

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error de conexion",Toast.LENGTH_SHORT).show();

                }
            });
            requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(jsonArrayRequest);




    }
}