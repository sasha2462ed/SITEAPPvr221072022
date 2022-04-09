package com.example.siteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.siteapp.databinding.ActivityInterfazMostrarGraficasBinding;
import com.example.siteapp.databinding.ActivityInterfazMostrarIncidenciasAdministrativasBinding;

public class interfaz_mostrar_graficas extends AppCompatActivity {

    ActivityInterfazMostrarGraficasBinding layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_mostrar_graficas);
        layout=ActivityInterfazMostrarGraficasBinding.inflate(getLayoutInflater());

        setContentView(layout.getRoot());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflador=getMenuInflater();
        inflador.inflate(R.menu.graficos,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {

            case R.id.nod:
                    frlnNod fr8=new frlnNod();
                    FragmentTransaction mFragmentNodo = getSupportFragmentManager().beginTransaction();
                    mFragmentNodo.replace(layout.FragmentC.getId(),fr8);
                    mFragmentNodo.commit();

                break;


            case R.id.ap:

                frlnAp fr9=new frlnAp();
                FragmentTransaction mFragmentApc = getSupportFragmentManager().beginTransaction();
                mFragmentApc.replace(layout.FragmentC.getId(),fr9);
                mFragmentApc.commit();

                break;


            case R.id.salir:

                Intent intent = new Intent( getApplicationContext(),interfaz_tecnico.class);
                startActivity(intent);

                break;

        }

        return super.onOptionsItemSelected(item);
    }




}