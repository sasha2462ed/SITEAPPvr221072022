package com.example.siteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.siteapp.databinding.ActivityInterfazDependienteBinding;
import com.example.siteapp.databinding.NotificacionBadgeBinding;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.HashMap;
import java.util.Map;

public class interfaz_dependiente extends AppCompatActivity {

    com.nex3z.notificationbadge.NotificationBadge NotificationBadge;
    private ActivityInterfazDependienteBinding v7;
    Context ct;
    int tec ;
    int usu ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_dependiente);
        v7 = ActivityInterfazDependienteBinding.inflate(getLayoutInflater());
        View view = v7.getRoot();
        setContentView(view);
        SharedPreferences admin=this.getSharedPreferences("x",MODE_PRIVATE);
        ct=view.getContext();

        v7.badget.setNumber(tec);

        v7.icono15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),interfaz_aviso.class);
                startActivity(intent);

            }
        });

        v7.badgeu.setNumber(usu);

        v7.icono16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),interfaz_notificaciones.class);
                startActivity(intent);

            }
        });

        v7.btn30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),departamento_administrativo.class);
                intent.putExtra("trampa", "0");
                startActivity(intent);

            }
        });

        v7.btn40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), departamento_tecnico.class);
                intent.putExtra("trampa", "0");
                startActivity(intent);

            }
        });


        v7.btnin4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), interfaz_sugerencia.class);
                intent.putExtra("trampa", "0");
                startActivity(intent);

            }
        });

        v7.btnin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), interfaz_mostrar_incidencias_usuario.class);
                intent.putExtra("trampa", "0");
                startActivity(intent);

            }
        });


        v7.btnin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), interfaz_tecnico_usuario.class);
                intent.putExtra("trampa", "2");
                startActivity(intent);

            }
        });

        v7.btnin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), interfaz_mostrar_incidencias_nivel_tecnico.class);
                intent.putExtra("trampa", "2");
                startActivity(intent);


            }
        });


        v7.btnin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), interfaz_mostrar_graficas.class);
                intent.putExtra("trampa", "2");
                startActivity(intent);

            }
        });

        v7.btnin5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), interfaz_envio_notificacion.class);
                intent.putExtra("trampa", "2");
                startActivity(intent);

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflador=getMenuInflater();
        inflador.inflate(R.menu.item_notificacion,menu);
        return super.onCreateOptionsMenu(menu);

/*
        menuItem = menu.findItem(R.id.notify);
        if (count == 0) {
            // if no pending notification remove badge
            menuItem = menu.findItem(R.id.notify);
            menuItem.setActionView(null);

        } else {
            menuItem = menu.findItem(R.id.notify);
            // if notification than set the badge icon layout
            menuItem.setActionView(R.layout.notificacion_badge);
            // get the view from the nav item
            View view = menuItem.getActionView();
            // get the text view of the action view for the nav item
            notification = view.findViewById(R.id.notification);
            // set the pending notifications value
            notification.setText(String.valueOf(count));

        }
*/
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
                data.apply();

                Intent intent = new Intent( getApplicationContext(),MainActivity.class);
                startActivity(intent);

                break;


            case R.id.salir:


                finishAffinity();
                System.exit(0);

                break;


        }

        return super.onOptionsItemSelected(item);
    }




}