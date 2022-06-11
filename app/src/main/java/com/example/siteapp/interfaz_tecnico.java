package com.example.siteapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class interfaz_tecnico extends General {

    private ActivityInterfazTecnicoBinding v6;

    int count;
    MenuItem menuItem;
    Context ct;
    TextView notification;
    String trampa = "2";
    private static final String CHANNEL_ID = "CHANNEL_ID";
    private static final String CHANNEL_NAME = "CHANNEL_NAME";
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v6 = ActivityInterfazTecnicoBinding .inflate(getLayoutInflater());
        View view = v6.getRoot();
        setContentView(view);

        SharedPreferences admin=this.getSharedPreferences("x",MODE_PRIVATE);
        ct=view.getContext();
/**/
        Anyclass Anyclass = new Anyclass();
        Anyclass.execute();
/*/
////*****************//////////////
        String URL = "http://192.168.101.5/conexion_php/item_notificacion.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()) {
                    try {
                        JSONArray object= null;
                        object = new JSONArray(response);
                        Log.i("result","Data: "+response);
                        for(int i=0;i<object.length();i++) {
                            JSONObject indicencia = object.getJSONObject(0);
                            indicencia.getString("CI");
                            int itemn = Integer.parseInt(indicencia.getString("CI").toString());
                            Log.i("resultm", String.valueOf(itemn));
                            JSONObject indicencia1 = object.getJSONObject(1);
                            indicencia1.getString("CII");
                            int items = Integer.parseInt(indicencia1.getString("CII").toString());
                            Log.i("results", String.valueOf(items));

                            count=items;
                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{

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
        /*********///////

        String nombre=admin.getString("nombre","");
        String cedula=admin.getString("cedula","");
        String tip_usuario=admin.getString("tip_usuario","");
        Log.i("result","Message: " +nombre);
        Log.i("result","Message: " +cedula);
        Log.i("result","Message: " +tip_usuario);

        ///***Botones***/////////

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

    /////////////////////*****notificacion desde aqui*****/////////////
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showNotification() {

        NotificationChannel CHANNEL = new NotificationChannel (CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(CHANNEL);
        showNewNotification();

    }

    private void showNewNotification() {

        setPendingIntent(interfaz_aviso.class);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_add_black_24dp)
                .setContentTitle("Usted tiene notificaciones pendientes")
                .setContentText("")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.getNotification().flags |= Notification.FLAG_AUTO_CANCEL;
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());
        managerCompat.notify( 1, builder.build());

    }

    private void setPendingIntent(Class<?> clsActivity){

        Intent intent = new Intent(this, clsActivity);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(clsActivity);
        stackBuilder.addNextIntent(intent);
        pendingIntent = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);

    }

public void deleteNotificationChannel(){

    NotificationManager notificationManager =
            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    // The id of the channel.
    String id = "CHANNEL_ID";
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        notificationManager.deleteNotificationChannel(id);
    } else {
        NotificationManager manager = ((NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE));
        manager.cancelAll();

    }
}

/////////******aqui termina notificacion**/////////////

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
//////////*****************////////////
public class Anyclass extends AsyncTask<Void, Integer, Boolean> {
    public void inc(){
        String URL = "http://192.168.101.5/conexion_php/item_notificacion.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()) {
                    try {
                        JSONArray object= null;
                        object = new JSONArray(response);
                        Log.i("result","Data: "+response);

                        for(int i=0;i<object.length();i++) {
                            JSONObject indicencia = object.getJSONObject(0);
                            indicencia.getString("CI");
                            int itemn = Integer.parseInt(indicencia.getString("CI").toString());
                            Log.i("resultm", String.valueOf(itemn));
                            JSONObject indicencia1 = object.getJSONObject(1);
                            indicencia1.getString("CII");
                            int items = Integer.parseInt(indicencia1.getString("CII").toString());
                            Log.i("results", String.valueOf(items));

                            if (Objects.equals(String.valueOf(items),"0")) {
                                deleteNotificationChannel();
                            } else {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                                    showNotification();
                                } else {
                                    showNewNotification();
                                }
                            }
                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
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
    }
    ///*********//////
    public void hilo (){
        try{
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void ejecutar(){
        Anyclass Anyclass = new Anyclass();
        Anyclass.execute();
    }
    @Override
    protected Boolean doInBackground(Void... voids) {
        //for(int {i=1; i<3; i++)
        while(true){
            hilo();
            inc();
        }
    }
    @Override
    protected void onPostExecute(Boolean aBoolean) {
        ejecutar();
    }
}
//////////////******************////////////////

}