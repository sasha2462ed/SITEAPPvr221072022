package com.example.siteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.siteapp.databinding.ActivityGraficosBinding;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class graficos extends AppCompatActivity {

    ActivityGraficosBinding layout;
    RequestQueue requestQueue;
    private Spinner months;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout=ActivityGraficosBinding.inflate(getLayoutInflater());

        setContentView(layout.getRoot());

        GraphView graph=layout.myGraph;

        String URL = "http://192.168.101.5/conexion_php/graficoso.php";


            JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Log.i("result",response.toString());
                    JSONArray barras = null;

                    try {

                        months = layout.months;
                        String [] mont = { "Enero","Febrero","Marzo","Abril","Mayo"};
                        ArrayAdapter<String> adapter01 = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item_estado, mont);
                        months.setAdapter(adapter01);

                        barras = response.getJSONArray(1);

                        int[] y = new int[barras.length()];
                        for (int c = 0; c < barras.length(); c++) {
                            y[c] = (int) barras.get(c);
                        }

                        ////*********////
                        int mayor = y[0];
                        for (int o = 0; o < y.length; o++) {
                            if (mayor < y[o]) {
                                mayor = y[o];
                            }
                        }
                        Log.i("mayor", String.valueOf(barras.length()));
                        /********************/

                        DataPoint[] cordenadas = new DataPoint[barras.length()];

                        for (int cont = 0; cont < barras.length(); cont++) {
                            cordenadas[cont] = new DataPoint(cont, y[cont]);
                        }
                        graph.removeAllSeries();

                        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(cordenadas);
                        graph.getViewport().setXAxisBoundsManual(true);
                        graph.getViewport().setMinX(0);
                        graph.getViewport().setMaxX(barras.length()-1);

                        graph.getViewport().setYAxisBoundsManual(true);
                        graph.getViewport().setMinY(0);
                        graph.getViewport().setMaxY(mayor);

                        graph.getViewport().setScrollable(true);
                        graph.getViewport().setScrollableY(true);
                        graph.getViewport().setScalable(false);
                        graph.getViewport().setScalableY(true);
                        //graph.getLegendRenderer().setVisible(true);
                        graph.addSeries(series);

                        //////******************////
                        /// ESCALA ROJA DE LA IZQUIERDA
                        BarGraphSeries<DataPoint> series3 = new BarGraphSeries<DataPoint>(cordenadas);
                        graph.getSecondScale().addSeries(series3);
                        graph.getSecondScale().setMinY(0);
                        graph.getSecondScale().setMaxY(mayor);
                        series3.setColor(Color.BLACK);
                        graph.getGridLabelRenderer().setVerticalLabelsSecondScaleColor(Color.BLACK);

                        ///// LINEAS ROJAS
                        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(cordenadas);
                        graph.addSeries(series2);

                        //*** titulos del para ejes x / Y
                        // use static labels for horizontal and vertical labels
                        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
                        staticLabelsFormatter.setHorizontalLabels(mont);
                        //staticLabelsFormatter.setVerticalLabels(mes);
                        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

                        ////*******////////

                        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                            @Override
                            public int get(DataPoint data) {
                                //return Color.rgb(100,60,100);
                                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
                            }
                        });

                        //**** tamanio texto y color de las variables de las barras
                        //series.setSpacing(50);
                        series.setValuesOnTopSize(60);
                        series.setDrawValuesOnTop(true);
                        series.setValuesOnTopColor(Color.CYAN);
                        /******/

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