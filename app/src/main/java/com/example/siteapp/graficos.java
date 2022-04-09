package com.example.siteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.siteapp.databinding.ActivityGraficosBinding;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;

import java.lang.reflect.Array;

public class graficos extends AppCompatActivity {

    ActivityGraficosBinding layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout=ActivityGraficosBinding.inflate(getLayoutInflater());

        setContentView(layout.getRoot());

        GraphView graph=layout.graph;

        DataPoint[] x= new DataPoint[]{
                new DataPoint(1, 2),
                new DataPoint(2, 3),
                new DataPoint(3, 5),
                new DataPoint(4, 7),

        };





        BarGraphSeries<DataPoint> series=new BarGraphSeries<>();
        for(int i = 0; i < 10; i++){




        }
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


    }
}