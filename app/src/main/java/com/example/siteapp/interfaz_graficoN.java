package com.example.siteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.siteapp.databinding.ActivityInterfazGraficoNBinding;
import com.example.siteapp.databinding.ActivityMainBinding;

public class interfaz_graficoN extends AppCompatActivity {

    private Spinner spinnergrfN;
    private Spinner spinnergrfap;
    String statenod;
    String stateap;
    ActivityInterfazGraficoNBinding layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_grafico_n);
        layout = ActivityInterfazGraficoNBinding.inflate(getLayoutInflater());
        View view = layout.getRoot();
        setContentView(view);

        spinnergrfN = layout.spinnergrfN;
        String[] opciones = {"Receptado", "En curso", "Finalizado"};
        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item_estado, opciones);
        spinnergrfN.setAdapter(adapter7);
        statenod= spinnergrfN.getSelectedItem().toString();

        spinnergrfap = layout.spinnergrfap;
        String[] opcion = {"Receptado", "En curso", "Finalizado"};
        ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item_estado, opcion);
        spinnergrfap.setAdapter(adapter8);
        stateap = spinnergrfap.getSelectedItem().toString();

    }
}