


package com.example.siteapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.siteapp.databinding.FragmentFrlnAduBinding;
import com.example.siteapp.databinding.FragmentFrlnNotifyBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link frln_notify#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frln_notify extends Fragment {

    FragmentFrlnNotifyBinding layout;


    public frln_notify() {
        // Required empty public constructor
    }


    public static frln_notify newInstance(String param1, String param2) {
        frln_notify fragment = new frln_notify();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout= FragmentFrlnNotifyBinding.inflate(inflater,container,false);
        View v=layout.getRoot();
/*
        RecyclerView list=layout.lista;
        ArrayList<list_notificacion> itemRec;

        itemRec=new ArrayList();

        String URL = "http://192.168.101.5/conexion_php/buscar_incidencias.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(!response.isEmpty()) {
                    try {
                        JSONArray object= null;

                        object = new JSONArray(response);

                        for(int i=0;i<object.length();i++) {
                            JSONObject indicencia = object.getJSONObject(i);

                            itemRec.add(new list_notificacion(
                                            indicencia.getString("idIncidencias"),
                                            indicencia.getString("asunto").toString(),
                                            indicencia.getString("descripcion").toString(),
                                            indicencia.getString("fecha").toString(),
                                            indicencia.getString("estado").toString()




                                    )
                            );
                        }

                        list.setLayoutManager(new LinearLayoutManager(requireContext()));
                        RecyclerView.Adapter<adapter_notify.adapter_notificacion> adapter= new adapter_notify(itemRec);
                        list.setAdapter(adapter);
                    }

                    catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else{

                    Toast.makeText(requireContext(), "Sin incidencias que mostrar", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("result",error.toString());

            }

        }){
            @Override
            protected Map<String, String> getParams () throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                parametros.put("estado", "NV");
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        requestQueue.add(stringRequest);

*/
        return v;

    }
}
