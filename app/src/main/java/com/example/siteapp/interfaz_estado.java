package com.example.siteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.example.siteapp.databinding.ActivityDepartamentoAdministrativoBinding;
import com.example.siteapp.databinding.ActivityDepartamentoTecnicoBinding;
import com.example.siteapp.databinding.ActivityInterfazEstadoBinding;

import java.util.HashMap;
import java.util.Map;

public class interfaz_estado extends AppCompatActivity {
    private ActivityInterfazEstadoBinding v30;
    int valor3=0;
    private ListView lv4;
    String idCliente;
    String idIncidencia;
    String cedula;
    RequestQueue requestQueue;
    Context ct;
    final String[] valor = {""};

    private String problemas2 [] = {"Receptado", "En curso", " Finalizado"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_estado);
        v30 = ActivityInterfazEstadoBinding.inflate(getLayoutInflater());
        View view = v30.getRoot();
        setContentView(view);

        idCliente = getIntent().getStringExtra("idClient");
        idIncidencia = getIntent().getStringExtra("idIncidencia");
        cedula = getIntent().getStringExtra("cedula");

        lv4 = (ListView) findViewById(R.id.lv4);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, problemas2);
        lv4.setAdapter(adapter3);

        v30.lv4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                valor3 = i;
                Log.i("result","IDCLiente: "+idCliente);
                Log.i("result","IDIncidencia: "+idIncidencia);
                Log.i("result","Estado: "+valor3);
                Log.i("result","cedulaclient: "+cedula);
                valor[0] = problemas2[i];
                //Toast.makeText(getApplicationContext(), valor[0], Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Estado de la incidencia " + valor[0], Toast.LENGTH_SHORT).show();
            }
        });

          v30.btnstate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarestado( "http://192.168.101.5/conexion_php/modificar_estado.php"  );
                //FragmentTransaction ft = getFragmentManager().beginTransaction();
                //ft.commit();
            }
        });


    }

    private void modificarestado (String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("oliver", response);

                if (response.equals("1")) {
                    Toast.makeText(getBaseContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();



                } else {
                    Toast.makeText(getBaseContext(), "OPERACION FALLIDA ", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent( getApplicationContext(),interfaz_mostrar_incidencias_nivel_tecnico.class);
                startActivity(intent);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Error: "+error, Toast.LENGTH_SHORT).show();
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                //parametros.put("id".toString().toString());

                parametros.put("cedula", cedula.toString());

                parametros.put("estado", String.valueOf(valor3));

                //parametros.put("idCliente", idCliente);
                parametros.put("idIncidencia",idIncidencia);
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
