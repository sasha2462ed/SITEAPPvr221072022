package com.example.siteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.siteapp.databinding.ActivityInterfazEstadoBinding;
import com.example.siteapp.databinding.ActivityInterfazEstadoUserBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class interfaz_estado_user extends AppCompatActivity {
    private ActivityInterfazEstadoUserBinding v38;
    int valor3 = 0;
    private ListView lv4;
    String idCliente;
    String idIncidencia;
    String departamento;
    String state;
    RequestQueue requestQueue;

    private String problemas2[] = {" Finalizado"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_estado_user);
        v38 = ActivityInterfazEstadoUserBinding.inflate(getLayoutInflater());
        View view = v38.getRoot();
        setContentView(view);

        idCliente = getIntent().getStringExtra("idClient");
        departamento = getIntent().getStringExtra("departamento");



    }

}

