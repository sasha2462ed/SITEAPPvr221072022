package com.example.siteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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
import com.example.siteapp.databinding.ActivityGraficosBinding;
import com.example.siteapp.databinding.ActivityInterfazNotificacionesBinding;
import com.example.siteapp.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class interfaz_notificaciones extends AppCompatActivity {

    private ActivityInterfazNotificacionesBinding layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_notificaciones);
        layout= ActivityInterfazNotificacionesBinding.inflate(getLayoutInflater());
        View view = layout.getRoot();
        setContentView(view);


        RecyclerView list=layout.lista;
        ArrayList<list_notificacion> itemRec1;

        itemRec1=new ArrayList();

        String URL = "http://192.168.101.2/usuarios_bd/validar_notificacion.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()) {
                    try {
                        JSONArray object= null;

                        object = new JSONArray(response);
                        Log.i("result","Data: "+response);
                        for(int i=0;i<object.length();i++) {
                            JSONObject notificacion = object.getJSONObject(i);

                            itemRec1.add(new list_notificacion(
                                    notificacion.getString("tiponot").toString(),
                                    notificacion.getString("comentario").toString(),
                                    notificacion.getString("hora").toString(),
                                    notificacion.getString("estado").toString(),
                                    notificacion.getString("id").toString()




                                    )
                            );
                        }

                        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        RecyclerView.Adapter<adapter_notify.adapter_notificacion> adapter= new adapter_notify(itemRec1);
                        list.setAdapter(adapter);




                    }

                    catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else{
                    Toast.makeText(getApplicationContext(), "Sin incidencias que mostrar", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(MainActivity.this,error.toString(), Toast.LENGTH_SHORT).show();

            }

        }){
            @Override
            protected Map<String, String> getParams () throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


    }
}