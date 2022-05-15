package com.example.siteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.siteapp.databinding.ActivityInterfazMostrarIncidenciasAdministrativasBinding;
import com.example.siteapp.databinding.ActivityInterfazMostrarIncidenciasTecnicasBinding;
import com.example.siteapp.databinding.ActivityInterfazUsuarioBinding;
import com.example.siteapp.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class interfaz_mostrar_incidencias_tecnicas_nivel_usuario extends AppCompatActivity {

    private ActivityInterfazMostrarIncidenciasTecnicasBinding v29;
    String idCliente;
    String idIncidencia;
    String cedula;
    String estado;
    String trampa;
    String comentario;
    RequestQueue requestQueue;
    Context ct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_mostrar_incidencias_tecnicas);
        v29 = ActivityInterfazMostrarIncidenciasTecnicasBinding.inflate(getLayoutInflater());
        View view = v29.getRoot();
        setContentView(view);

        idCliente = getIntent().getStringExtra("idClient");
        idIncidencia = getIntent().getStringExtra("idIncidencia");
        estado = getIntent().getStringExtra("estado");
        comentario = getIntent().getStringExtra("comentario");
        cedula = getIntent().getStringExtra("cedula");
        trampa = getIntent().getStringExtra("trampa");

        SharedPreferences admin=this.getSharedPreferences("x",MODE_PRIVATE);
        ct=view.getContext();

        String tip_usuario=admin.getString("tip_usuario","");


        if(tip_usuario.equals("T") || tip_usuario.equals("D")) {

            v29.btnestado.setVisibility(View.VISIBLE);
        }else{

            v29.btnestado.setVisibility(View.INVISIBLE);
        }




        v29.btnestado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getBaseContext(),interfaz_estado.class);
                intent.putExtra("idClient",idCliente);
                intent.putExtra("idIncidencia",idIncidencia);
                intent.putExtra("cedula",cedula);
                startActivity(intent);

                    /*Log.i("result","idClient: "+holder.idClient.getText().toString() );
                    Log.i("result","Comentario: "+holder.comentario.getText().toString() );*/
            }


        });

        Log.i("result","IDCLiente: "+idCliente);
        Log.i("result","IDCLiente: "+idIncidencia);

        String URL="http://192.168.101.5/conexion_php/detalle.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("result",response.toString());

                if(!response.isEmpty()) {
                    try {
                        JSONObject objUser= new JSONObject(response.toString());
                        Log.i("result","data:"+response.toString());

                        v29.tvm1.setText(objUser.getString("nombre"));
                        v29.tvm2.setText(objUser.getString("cedula"));
                        v29.tvm3.setText(objUser.getString("contrasena"));
                        v29.tvm4.setText(objUser.getString("telefono"));
                        v29.tvm5.setText(objUser.getString("direccion"));
                        v29.tvm6.setText(objUser.getString("nodo"));
                        v29.tvm7.setText(objUser.getString("ap"));
                        v29.tvm9.setText(comentario);
                        v29.tvm8.setText(estado);

                       /* if ( estado.equals("0")){
                            estado = "Receptado";


                        } else if (estado.equals("1")){
                            estado = "En curso";
                            v29.tvm8.setText(estado);

                        }else if (estado.equals("2")){
                            estado = "Finalizado";
                            v29.tvm8.setText(estado);

                        }

                        */
                        //Toast.makeText( getBaseContext(), "Bienvenido "+nombre, Toast.LENGTH_SHORT).show();

                    }

                    catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else{
                    //Toast.makeText(getApplicationContext(), "Sin datos que mostrar", Toast.LENGTH_SHORT).show();
                    //Log.i("result","Uhmmmm0955454244");
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText( getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();

            }

        }){
            @Override
            protected Map<String, String> getParams () throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                parametros.put("id", idIncidencia);
                //parametros.put("idIncidencia", idIncidencia);

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


        /********************************/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflador=getMenuInflater();
        inflador.inflate(R.menu.regresar,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        SharedPreferences admin=getBaseContext().getSharedPreferences("x", Context.MODE_PRIVATE);
        String tip_usuario=admin.getString("tip_usuario","");

        switch (item.getItemId())
        {

            case R.id.salir:


                finishAffinity();
                System.exit(0);


                break;

            case R.id.regresar:

                if(tip_usuario.equals("C")){

                    Intent intent = new Intent( getApplicationContext(),interfaz_usuario.class);
                    startActivity(intent);

                }

                else if (tip_usuario.equals("T")){

                    Intent intent = new Intent( getApplicationContext(),interfaz_tecnico.class);
                    startActivity(intent);


                }
                else {

                    Intent intent = new Intent( getApplicationContext(),interfaz_dependiente.class);
                    startActivity(intent);

                }

                break;



        }

        return super.onOptionsItemSelected(item);
    }
}