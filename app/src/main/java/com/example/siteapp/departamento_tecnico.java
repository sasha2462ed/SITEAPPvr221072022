package com.example.siteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.siteapp.databinding.ActivityDepartamentoTecnicoBinding;

import java.util.HashMap;
import java.util.Map;

public class departamento_tecnico extends AppCompatActivity {

    RequestQueue requestQueue;
    private ActivityDepartamentoTecnicoBinding v3;
    DatePickerDialog.OnDateSetListener setListener;
    private String incidencia = "tecnica";
    private ListView lv1;
    final String[] valor = {""};
    String trampa;

    private String problemas[] = {"antena caida", "lentitud", "problemas / router_antena","cambio/clave"," sin navegacio","dano/cable","wifi_cambio_nombre/contrasena"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departamento_tecnico);
        v3 = ActivityDepartamentoTecnicoBinding.inflate(getLayoutInflater());
        View view = v3.getRoot();
        setContentView(view);
        trampa = getIntent().getStringExtra("trampa");
        lv1 = (ListView) findViewById(R.id.lv1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, problemas);
        lv1.setAdapter(adapter);

        v3.lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                valor[0] = problemas[i];
                Toast.makeText(getApplicationContext(),"Su incidencia es " + valor[0], Toast.LENGTH_SHORT).show();

            }
        });

        v3.btn4tec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registroTicketstecnicos( "http://192.168.101.5/conexion_php/insertar_incidencias.php"  );


            }
        });

        v3.btn6tec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences admin=getBaseContext().getSharedPreferences("x", Context.MODE_PRIVATE);
                String tip_usuario=admin.getString("tip_usuario","");

                if (tip_usuario.equals("C")){

                    Intent intent = new Intent( getApplicationContext(),interfaz_usuario.class);
                    startActivity(intent);

                }

                else if (tip_usuario.equals("D")){

                    Intent intent = new Intent( getApplicationContext(),interfaz_dependiente.class);
                    startActivity(intent);


                }
            }
        });

        v3.btn7tec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }
        });

    }

    private void registroTicketstecnicos (String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("oliver",response);
                if(response.equals("1")){
                    Toast.makeText(getBaseContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();


                }else{
                    Toast.makeText(getBaseContext(), "OPERACION FALLIDA ", Toast.LENGTH_SHORT).show();


                }

                Intent intent = new Intent( getApplicationContext(),departamento_tecnico.class);
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
                parametros.put("tipo",valor[0].toString());
                parametros.put("departamento", String.valueOf(1));
                parametros.put("comentario",v3.txp4tec.getText().toString());
                SharedPreferences admin=getApplicationContext().getSharedPreferences("x",MODE_PRIVATE);
                String id=admin.getString("id","");
                String ap=admin.getString("ap","");
                parametros.put("id_usuarios", id);
                parametros.put("ap", ap);
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}

