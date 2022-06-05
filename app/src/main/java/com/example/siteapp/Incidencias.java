package com.example.siteapp;

import android.view.View;

import androidx.annotation.NonNull;

public class Incidencias {
    String tipo;
    String comentario;
    String hora;
    String estado;
    String idUser;
    String idIncidencias;
    String cedula;
    String departamento;

    public Incidencias(String idIncidencias,String tipo,String comentario ,String hora ,String estado, String Id,String cedula, String departamento) {

        this.tipo=tipo;
        this.comentario=comentario;
        this.hora=hora;
        this.estado=estado;
        this.idUser=Id;
        this.idIncidencias=idIncidencias;
        this.cedula=cedula;
        this.departamento=departamento;


    }
}
