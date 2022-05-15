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
import com.example.siteapp.databinding.ActivityDepartamentoAdministrativoBinding;

import java.util.HashMap;
import java.util.Map;

public class departamento_administrativo extends AppCompatActivity {
    String trampa;
    private ActivityDepartamentoAdministrativoBinding v4;
    RequestQueue requestQueue;
    DatePickerDialog.OnDateSetListener setListener1;
    final String[] valor1 = {""};
    private ListView lv2;


    private String problemas1 [] = {"Suspension/servicio", "Cancelacion/servicio", " Nuevo contrato"," Cambio de plan","Retiro / equipos"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departamento_administrativo);
        v4 = ActivityDepartamentoAdministrativoBinding.inflate(getLayoutInflater());
        View view = v4.getRoot();
        setContentView(view);
        trampa = getIntent().getStringExtra("trampa");




        lv2 = (ListView) findViewById(R.id.lv2);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, problemas1);
        lv2.setAdapter(adapter1);

        v4.lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                valor1[0] = problemas1[i];
                Toast.makeText(getApplicationContext(), "Su incidencia es " + valor1[0], Toast.LENGTH_SHORT).show();

            }
        });
        v4.btn4adm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registroTicketsadministrativos( "http://192.168.101.5/conexion_php/insertar_incidencias.php"  );

            }
        });

        v4.btn6adm.setOnClickListener(new View.OnClickListener() {
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

        v4.btn7adm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }
        });

    }
    private void registroTicketsadministrativos (String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("oliver",response);
                if(response.equals("1")){
                    Toast.makeText(getBaseContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();


                }else{
                    Toast.makeText(getBaseContext(), "OPERACION FALLIDA ", Toast.LENGTH_SHORT).show();


                }

                Intent intent = new Intent( getApplicationContext(),departamento_administrativo.class);
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
                parametros.put("tipo",valor1[0].toString());
                parametros.put("departamento", String.valueOf(2));
                parametros.put("comentario",v4.txp4adm.getText().toString());
                SharedPreferences admin=getApplicationContext().getSharedPreferences("x",MODE_PRIVATE);
                String id=admin.getString("id","");
                parametros.put("id_usuarios", id);
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
