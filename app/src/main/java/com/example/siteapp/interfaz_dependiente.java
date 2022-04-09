package com.example.siteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.siteapp.databinding.ActivityInterfazDependienteBinding;

import java.util.HashMap;
import java.util.Map;

public class interfaz_dependiente extends AppCompatActivity {

    private ActivityInterfazDependienteBinding v7;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_dependiente);
        v7 = ActivityInterfazDependienteBinding.inflate(getLayoutInflater());
        View view = v7.getRoot();
        setContentView(view);


        v7.btn30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), interfaz_usuario.class);
                startActivity(intent);

            }
        });

        v7.btn40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), interfaz_tecnico.class);
                startActivity(intent);

            }
        });

    }
}