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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class interfaz_estado extends AppCompatActivity {
    private ActivityInterfazEstadoBinding v30;
    //int valor3=0;
    private ListView lv4;
    String idCliente;
    String idIncidencia;
    String cedula;
    String state;
    RequestQueue requestQueue;

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

///////////////////////////*************************////////////////////////


        String URL="http://192.168.101.5/conexion_php/item_estados.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray nodos = new JSONArray(response);

                    JSONArray id = new JSONArray(nodos.get(0).toString());
                    JSONArray name = new JSONArray(nodos.get(1).toString());

                    lv4 = (ListView) findViewById(R.id.lv4);
                    String[] problemas1 = new String[name.length()];

                    JSONObject nods = new JSONObject();

                    for (int i = 0; i < name.length(); i++) {
                        problemas1[i] = name.get(i).toString();
                        nods.put(name.get(i).toString(), id.get(i).toString());
                    }

                    lv4 = (ListView) findViewById(R.id.lv4);
                    ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getApplicationContext(),R.layout.listview_items, problemas1);
                    lv4.setAdapter(adapter3);

                    v30.lv4.setOnItemClickListener (new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //valor=lv2.getItemAtPosition(position).toString();
                            int valor3=0;
                            valor3 = position+1;


                            Toast.makeText(getApplicationContext(), "Estado de la incidencia " + valor3 , Toast.LENGTH_SHORT).show();
                            Log.i("result","Estado: "+valor3);
                            //////////*////////////////

                            //////////*////////////////

                            try{

                                int finalValor = valor3;
                                v30.btnstate.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        String URL= "http://192.168.101.5/conexion_php/modificar_estado.php";

                                        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                Log.i("oliver",response);
                                                if(response.equals("1")){
                                                    Toast.makeText(getBaseContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();


                                                }else{
                                                    Toast.makeText(getBaseContext(), "OPERACION FALLIDA ", Toast.LENGTH_SHORT).show();


                                                }

                                                Intent intent = new Intent( getApplicationContext(),interfaz_mostrar_incidencias_usuario.class);
                                                startActivity(intent);

                                            }

                                        }, new Response.ErrorListener(){
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
                                            }

                                        }){
                                            @Override
                                            protected Map<String, String> getParams() throws AuthFailureError {
                                                Map<String, String> parametros = new HashMap<String, String>();
                                                //parametros.put("id".toString().toString());
                                                parametros.put("cedula", cedula.toString());
                                                parametros.put("estado", String.valueOf(finalValor));
                                                parametros.put("idIncidencia",idIncidencia);
                                                Log.i("result","Estado: "+finalValor);
                                                Log.i("result","Estado: "+cedula);
                                                Log.i("result","Estado: "+idCliente);
                                                return parametros;
                                            }
                                        };
                                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                                        requestQueue.add(stringRequest);


                                    }
                                });



                            } catch (Exception e) {
                                e.printStackTrace();
                            }




                            ///********************//

                        }

                    });



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        }){
            @Override
            protected Map<String, String> getParams () throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
//////////////////////////**********************//////////////////////////////////////
    }
}


