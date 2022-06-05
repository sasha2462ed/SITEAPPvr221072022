package com.example.siteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.siteapp.databinding.ActivityInterfazTecnicoBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class interfaz_tecnico extends General {

    private ActivityInterfazTecnicoBinding v6;

    //NotificationBadge notificationBadge;
    int count = 5;
    MenuItem menuItem;
    //String numus;
    Context ct;
    TextView notification;
    String trampa = "2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v6 = ActivityInterfazTecnicoBinding .inflate(getLayoutInflater());
        View view = v6.getRoot();
        setContentView(view);
        //numus= getIntent().getExtras().getString("nomusu");

        ///******/////////////////
        String URL = "http://192.168.101.5/conexion_php/item_notificacion.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()) {
                    try {

                        JSONObject objUser= new JSONObject(response);
                        objUser.getString("CI");
                        String itemn = objUser.getString("CI");
                        Log.i("itemn",itemn);

                        //v7.badget.setNumber(Integer.parseInt(itemn));
                        // v7.badgeu.setNumber(Integer.parseInt(items));


                    } catch (JSONException e) {
                        Log.i("Error",e.getMessage());
                    }


                }else{
                    Toast.makeText(getApplicationContext(), "usuario/contrasena incorrecto", Toast.LENGTH_SHORT).show();
                }

            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
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










        /////*****//////




        //notificationBadge.setNumber(count);
        SharedPreferences admin=this.getSharedPreferences("x",MODE_PRIVATE);
        ct=view.getContext();

        String nombre=admin.getString("nombre","");
        String cedula=admin.getString("cedula","");
        String tip_usuario=admin.getString("tip_usuario","");



        Log.i("result","Message: " +nombre);
        Log.i("result","Message: " +cedula);
        Log.i("result","Message: " +tip_usuario);


        v6.btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(),interfaz_tecnico_usuario.class);
                startActivity(intent);
            }
        });

        v6.btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), interfaz_mostrar_incidencias_nivel_tecnico.class);
                intent.putExtra("trampa", trampa);
                startActivity(intent);

            }
        });

        v6.btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(),interfaz_mostrar_graficas.class);
                startActivity(intent);


            }
        });

        v6.btn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);


            }
        });

        v6.botonenvionotificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(),interfaz_envio_notificacion.class);
                startActivity(intent);


            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflador=getMenuInflater();
        inflador.inflate(R.menu.cerrar,menu);

        menuItem = menu.findItem(R.id.notify);
        if (count == 0) {
            // if no pending notification remove badge
            menuItem = menu.findItem(R.id.notify);
            //menuItem.setActionView(null);

        } else {

            // if notification than set the badge icon layout
            menuItem.setActionView(R.layout.notificacion_badge);
            // get the view from the nav item
            View view = menuItem.getActionView();
            // get the text view of the action view for the nav item
            notification = view.findViewById(R.id.notification);
            //notification.setEnabled(false);
            // set the pending notifications value
            notification.setText(String.valueOf(count));
            menuItem = menu.findItem(R.id.notify);

            notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("result","xxxxxxxxxxxxxxxxxxxxxx");
                    Intent intent = new Intent(getApplicationContext(), interfaz_aviso.class);
                    startActivity(intent);

                }
            });

        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {

            case R.id.cerrar:


                SharedPreferences admin=ct.getSharedPreferences("x",ct.MODE_PRIVATE);
                SharedPreferences.Editor data=admin.edit();
                data.remove("estado");
                data.remove("nombre");
                data.remove("cedula");
                data.remove("tip_usuario");
                data.remove("id");
                data.remove("ap");
                data.apply();

                Intent intent = new Intent( getApplicationContext(),MainActivity.class);
                startActivity(intent);

                break;


            case R.id.notify:


                intent = new Intent(getApplicationContext(), interfaz_aviso.class);
                startActivity(intent);

                break;

        }

        return super.onOptionsItemSelected(item);
    }



    //////////////**notificacion item**/////////////////////////////






    /////**************///////////


}