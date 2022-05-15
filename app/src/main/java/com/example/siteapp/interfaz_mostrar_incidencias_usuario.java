package com.example.siteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.siteapp.databinding.ActivityInterfazMostrarIncidenciasAdministrativasBinding;
import com.example.siteapp.databinding.ActivityInterfazMostrarIncidenciasUsuarioBinding;

public class interfaz_mostrar_incidencias_usuario extends AppCompatActivity {

    ActivityInterfazMostrarIncidenciasUsuarioBinding layout;
    RecyclerView lista;

    String trampa;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            layout=ActivityInterfazMostrarIncidenciasUsuarioBinding.inflate(getLayoutInflater());

            setContentView(layout.getRoot());
//        SharedPreferences admin=getBaseContext().getSharedPreferences("x", Context.MODE_PRIVATE);
//        String tipo=admin.getString("tipo","");
//        Log.i("result","Data: "+tipo);



/*
        itemRec=new ArrayList<>();
        lista=layout.list;
        lista.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter adapter= new myAdapter(itemRec);
        lista.setAdapter(adapter);
*/

        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {

            MenuInflater inflador=getMenuInflater();
            inflador.inflate(R.menu.incidencias_dependientes,menu);
            return super.onCreateOptionsMenu(menu);

        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {

            trampa = getIntent().getStringExtra("trampa");


            SharedPreferences admin=getBaseContext().getSharedPreferences("x", Context.MODE_PRIVATE);
            String tip_usuario=admin.getString("tip_usuario","");
            Log.i("result","Data: "+tip_usuario);
            Log.i("result","dependiente: "+trampa);

            switch (item.getItemId())
            {

                case R.id.admin:

                    if (tip_usuario.equals("D") && trampa.equals("0")){

                        frlnAdu fr3=new frlnAdu();
                        FragmentTransaction mFragmentAdmin = getSupportFragmentManager().beginTransaction();
                        mFragmentAdmin.replace(layout.containerF.getId(),fr3);
                        mFragmentAdmin.commit();


                    }

                    else if (tip_usuario.equals("D") && trampa.equals("2")){

                        frInAd fr2=new frInAd();
                        FragmentTransaction mFragmentAdmin = getSupportFragmentManager().beginTransaction();
                        mFragmentAdmin.replace(layout.containerF.getId(),fr2);
                        mFragmentAdmin.commit();


                    }

                    break;
                case R.id.tecnico:

                   if (tip_usuario.equals("D") && trampa.equals("0")){

                        frlnTecu fr4 = new frlnTecu();
                        FragmentTransaction mFragmentTec = getSupportFragmentManager().beginTransaction();
                        mFragmentTec.replace(layout.containerF.getId(),fr4);
                        mFragmentTec.commit();


                    }

                    else if (tip_usuario.equals("D") && trampa.equals("2")){

                        frInTec fr1 = new frInTec();
                        FragmentTransaction mFragmentTec = getSupportFragmentManager().beginTransaction();
                        mFragmentTec.replace(layout.containerF.getId(),fr1);
                        mFragmentTec.commit();


                    }

                    break;


                case R.id.regre:

                    if(tip_usuario.equals("C")){

                        Intent intent = new Intent( getApplicationContext(),interfaz_usuario.class);
                        startActivity(intent);

                    }

                    else if (tip_usuario.equals("T")) {

                        Intent intent = new Intent( getApplicationContext(),interfaz_tecnico.class);
                        startActivity(intent);


                    } else {

                        Intent intent = new Intent( getApplicationContext(),interfaz_dependiente.class);
                        startActivity(intent);


                    }

                    break;

            }

            return super.onOptionsItemSelected(item);
        }

    }