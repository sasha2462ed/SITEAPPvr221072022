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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.siteapp.databinding.ActivityInterfazTecnicoUsuarioBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class interfaz_tecnico_usuario extends AppCompatActivity {

    RequestQueue requestQueue;
    String trampa;
    private ActivityInterfazTecnicoUsuarioBinding v5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_tecnico_usuario);
        v5 = ActivityInterfazTecnicoUsuarioBinding.inflate(getLayoutInflater());
        View view = v5.getRoot();
        setContentView(view);
        trampa = getIntent().getStringExtra("trampa");


        v5.btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v5.txp6.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Campo nombre vacio",Toast.LENGTH_SHORT).show();
                } else {
                    if (v5.txp7.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Campo cedula vacio",Toast.LENGTH_SHORT).show();

                    }else {
                        if (v5.txp8.getText().toString().isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Campo contrasena vacio", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            if (v5.txp9.getText().toString().isEmpty()) {
                                Toast.makeText(getApplicationContext(), "Campo telefono vacio", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                if (v5.txp10.getText().toString().isEmpty()) {
                                    Toast.makeText(getApplicationContext(), "Campo direccion vacio", Toast.LENGTH_SHORT).show();

                                }
                                else {
                                    if (v5.txp12.getText().toString().isEmpty()) {
                                        Toast.makeText(getApplicationContext(), "Campo ap vacio", Toast.LENGTH_SHORT).show();

                                    }
                                    else {


                                        insertarproducto("http://192.168.101.5/conexion_php/insertar_usuario.php");

                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
        v5.btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarproducto("http://192.168.101.5/conexion_php/buscar_usuario.php?cedula="+ v5.txp7.getText()+"");
            }
        });

        v5.btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarproducto("http://192.168.101.5/conexion_php/modificar_usuario.php");
            }
        });

        v5.btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences admin=getBaseContext().getSharedPreferences("x", Context.MODE_PRIVATE);
                String tip_usuario=admin.getString("tip_usuario","");

                if (tip_usuario.equals("T")){

                    Intent intent = new Intent( getApplicationContext(),interfaz_tecnico.class);
                    startActivity(intent);

                }

                else if (tip_usuario.equals("D")){

                    Intent intent = new Intent( getApplicationContext(),interfaz_dependiente.class);
                    startActivity(intent);


                }
            }
        });

        v5.btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void insertarproducto (String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("oliver",response);
                if(response.equals("1")){
                    Toast.makeText(getBaseContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();


                }else{
                    Toast.makeText(getBaseContext(), "OPERACION FALLIDA ", Toast.LENGTH_SHORT).show();


                }

                Intent intent = new Intent( getApplicationContext(),interfaz_tecnico_usuario.class);
                startActivity(intent);

            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
            }

        }){
            @Override
            protected Map<String, String> getParams () throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                //parametros.put("id".toString().toString());
                parametros.put("nombre",v5.txp6.getText().toString().trim());
                parametros.put("cedula",v5.txp7.getText().toString().trim());
                parametros.put("contrasena",v5.txp8.getText().toString().trim());
                parametros.put("telefono",v5.txp9.getText().toString().trim());
                parametros.put("direccion",v5.txp10.getText().toString().trim());
                parametros.put("ap",v5.txp12.getText().toString().trim());
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void buscarproducto (String URL){
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {



                if (response != null) {
                    try {


                        JSONObject jsonObject = null;
                        for (int i = 0; i < response.length(); i++) {
                            jsonObject = response.getJSONObject(i);
                            v5.txp6.setText(jsonObject.getString("nombre"));
                            v5.txp7.setText(jsonObject.getString("cedula"));
                            v5.txp8.setText(jsonObject.getString("contrasena"));
                            v5.txp9.setText(jsonObject.getString("telefono"));
                            v5.txp10.setText(jsonObject.getString("direccion"));
                            v5.txp12.setText(jsonObject.getString("ap"));
                        }
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplicationContext(), "Sin incidencias que mostrar", Toast.LENGTH_SHORT).show();
                    }


                } else {

                    Toast.makeText(getApplicationContext(), "Usuario no registrado",Toast.LENGTH_SHORT).show();
                    v5.txp6.getText().clear();
                    v5.txp7.getText().clear();
                    v5.txp8.getText().clear();
                    v5.txp9.getText().clear();
                    v5.txp10.getText().clear();
                    v5.txp12.getText().clear();


                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(), "Error de conexion",Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Usuario no registrado",Toast.LENGTH_SHORT).show();
                v5.txp6.getText().clear();
                v5.txp7.getText().clear();
                v5.txp8.getText().clear();
                v5.txp9.getText().clear();
                v5.txp10.getText().clear();
                v5.txp12.getText().clear();

            }
        });
        requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}