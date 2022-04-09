package com.example.siteapp;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class adapter_notify extends RecyclerView.Adapter<adapter_notify.adapter_notificacion> {

    ArrayList<list_notificacion> items1;


    public adapter_notify(ArrayList<list_notificacion> items1) {

        this.items1 = items1;


    }

    @NonNull
    @Override
    public adapter_notificacion onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_notificacion, parent, false);
        return new adapter_notificacion(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_notificacion holder, int position) {

        holder.tiponot.setText(items1.get(position).tiponot);
        holder.comentario.setText(items1.get(position).comentario);
        holder.fecha.setText(items1.get(position).hora);
        holder.estado.setText(items1.get(position).estado);


        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences admin=v.getContext().getSharedPreferences("x", Context.MODE_PRIVATE);
                String tipo=admin.getString("tipo","");

                if(tipo.equals("T")){

                    /***/


                    /*****/

                    // v.getContext().startActivity(new Intent(v.getContext(),interfaz_mostrar_incidencias_tecnicas_nivel_usuario.class));

                    Intent intent=new Intent(v.getContext(),interfaz_mostrar_incidencias_tecnicas_nivel_usuario.class);
                    intent.putExtra("estado",holder.estado.getText().toString());
                    intent.putExtra("comentario",holder.comentario.getText().toString());

                    v.getContext().startActivity(intent);

                    /*Log.i("result","idClient: "+holder.idClient.getText().toString() );
                    Log.i("result","Comentario: "+holder.comentario.getText().toString() );*/
                }

            }
        });

        /**/
    }





    @Override
    public int getItemCount() {
        return items1.size();
    }



    public class adapter_notificacion extends RecyclerView.ViewHolder {

        CardView cv;
        TextView tiponot;
        TextView comentario;
        TextView fecha;
        TextView estado;
        ImageView icono;
        TextView idClient;


        public adapter_notificacion (@NonNull View itemView) {
            super(itemView);

            cv=itemView.findViewById(R.id.cv);
            tiponot=itemView.findViewById(R.id.tiponot);
            comentario=itemView.findViewById(R.id.comentario);
            fecha=itemView.findViewById(R.id.fecha);
            estado=itemView.findViewById(R.id.refactor);
            icono=itemView.findViewById(R.id.icono);
            idClient=itemView.findViewById(R.id.txtIdUser);


            /*cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("result","CardView");
                }
            });*/



        }


    }

//    public void updateData(ArrayList<Incidencias> items) {
//        this.items = items;
////        items.clear();
////        items.addAll(items);
//
//        //notifyDataSetChanged();
//    }

}
