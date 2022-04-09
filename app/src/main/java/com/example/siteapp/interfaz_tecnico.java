package com.example.siteapp;

import static android.view.View.INVISIBLE;

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

import com.example.siteapp.databinding.ActivityInterfazTecnicoBinding;
import com.nex3z.notificationbadge.NotificationBadge;

public class interfaz_tecnico extends AppCompatActivity {

    private ActivityInterfazTecnicoBinding v6;

    //NotificationBadge notificationBadge;
   // int count=5;

    //String numus;
    Context ct;
    String trampa = "2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v6 = ActivityInterfazTecnicoBinding .inflate(getLayoutInflater());
        View view = v6.getRoot();
        setContentView(view);
        //numus= getIntent().getExtras().getString("nomusu");

        //notificationBadge.setNumber(count);
        SharedPreferences admin=this.getSharedPreferences("x",MODE_PRIVATE);
        ct=view.getContext();

        String nombre=admin.getString("nombre","");
        String cedula=admin.getString("cedula","");
        String tip_usuario=admin.getString("tip_usuario","");


        Log.i("result","Message: " +nombre);
        Log.i("result","Message: " +cedula);
        Log.i("result","Message: " +tip_usuario);

        //boolean invisble= true;
        if (tip_usuario.equals("D")){

            v6.imageView2.setVisibility(View.VISIBLE);


        }

        else{

            v6.imageView2.setVisibility(View.INVISIBLE);


        }

        v6.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(),interfaz_dependiente.class);
                startActivity(intent);
            }
        });

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
                Intent intent = new Intent( getApplicationContext(),graficos.class);
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
                data.apply();

                Intent intent = new Intent( getApplicationContext(),MainActivity.class);
                startActivity(intent);

                break;


        }

        return super.onOptionsItemSelected(item);
    }




}