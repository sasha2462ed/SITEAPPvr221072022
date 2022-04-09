package com.example.siteapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ContenetViews> {

    ArrayList<Incidencias> items;


    public myAdapter(ArrayList<Incidencias> items) {

        this.items=items;
    }

    @NonNull
    @Override
    public myAdapter.ContenetViews onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemlist, parent, false);

        return new ContenetViews(view);

    }

    @Override
    public void onBindViewHolder(@NonNull myAdapter.ContenetViews holder, int position) {

        String idIncidencias=items.get(position).idIncidencias;
        String cedula=items.get(position).cedula;
        holder.tipo.setText(items.get(position).tipo);
        holder.comentario.setText(items.get(position).comentario);
        holder.fecha.setText(items.get(position).hora);
        //holder.estado.setText(items.get(position).estado);

        switch (items.get(position).estado) {
            case "0":
                items.get(position).estado = "Receptado";
                holder.estado.setText(items.get(position).estado);

                break;
            case "1":
                items.get(position).estado = "En curso";
                holder.estado.setText(items.get(position).estado);

                break;
            case "2":
                items.get(position).estado = "Finalizado";
                holder.estado.setText(items.get(position).estado);

                break;
        }
        holder.idClient.setText(items.get(position).idUser);
        /***/
        /***/

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences admin=v.getContext().getSharedPreferences("x", Context.MODE_PRIVATE);
                String tipo=admin.getString("tipo","");

                if(tipo.equals("T") || tipo.equals("D")){

                    /***/


                    /*****/

                    // v.getContext().startActivity(new Intent(v.getContext(),interfaz_mostrar_incidencias_tecnicas_nivel_usuario.class));

                    Intent intent=new Intent(v.getContext(),interfaz_mostrar_incidencias_tecnicas_nivel_usuario.class);
                    intent.putExtra("idClient",holder.idClient.getText().toString());
                    intent.putExtra("idIncidencia",idIncidencias);
                    intent.putExtra("cedula",cedula);
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
        return items.size();
    }

    /*@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("result","Item: "+String.valueOf(id));
    }
*/
    public class ContenetViews extends RecyclerView.ViewHolder {

        CardView cv;
        TextView tipo;
        TextView comentario;
        TextView fecha;
        TextView estado;
        TextView idClient;
        ImageView icono;


        public ContenetViews(@NonNull View itemView) {
            super(itemView);

            cv=itemView.findViewById(R.id.cv);
            tipo=itemView.findViewById(R.id.tipo);
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
