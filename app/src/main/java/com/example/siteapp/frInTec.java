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
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.siteapp.databinding.FragmentFrInTecBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class frInTec<spinner> extends Fragment {

   FragmentFrInTecBinding layout;
    private Spinner spinner_estado;
    String state_frag;
    String tamanio;


    public frInTec() {
        // Required empty public constructor
    }


    public static frInTec newInstance(String param1, String param2) {
        frInTec fragment = new frInTec();
        /*
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        */
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        layout=FragmentFrInTecBinding.inflate(inflater,container,false);
        View v=layout.getRoot();
        //View vista= inflater.inflate(R.layout.fragment_fr_in_tec, container, false);

        spinner_estado = layout.spinnerEstado;
        String[] opciones = {"Receptado", "En curso", "Finalizado"};
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(getContext(), R.layout.spinner_item_estado, opciones);
        spinner_estado.setAdapter(adapter5);

        //Log.i("result","Data: "+state_frag);

        RecyclerView list=layout.lista;
        ArrayList<Incidencias> itemRec;

        itemRec=new ArrayList();

        /*******************************/


        layout.btnFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String URL;
                SharedPreferences admin=requireContext().getSharedPreferences("x", Context.MODE_PRIVATE);
                String tip_usuario=admin.getString("tip_usuario","");
                Log.i("result","Data: "+tip_usuario);



                    URL="http://192.168.101.5/conexion_php/buscar_incidenciastec.php";


                state_frag = spinner_estado.getSelectedItem().toString();
                switch (state_frag) {
                    case "Receptado":
                        state_frag = "0";

                        break;
                    case "En curso":
                        state_frag = "1";

                        break;
                    case "Finalizado":
                        state_frag = "2";
                        break;
                }

                    StringRequest stringRequest = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(!response.isEmpty()) {
                                try {
                                    JSONArray object= null;

                                    object = new JSONArray(response);
                                    Log.i("result","Data: "+response);
                                    itemRec.clear();
                                    for(int i=0;i<object.length();i++) {
                                        JSONObject indicencia = object.getJSONObject(i);

                                        itemRec.add(new Incidencias(
                                                        indicencia.getString("idIncidencias"),
                                                        indicencia.getString("tipo").toString(),
                                                        indicencia.getString("comentario").toString(),
                                                        indicencia.getString("hora").toString(),
                                                        indicencia.getString("estado").toString(),
                                                        indicencia.getString("id").toString(),
                                                indicencia.getString("cedula").toString()



                                                )
                                        );
                                    }

                                    list.setLayoutManager(new LinearLayoutManager(requireContext()));
                                    RecyclerView.Adapter adapter= new myAdapter(itemRec);
                                    adapter.notifyDataSetChanged();
                                    list.setAdapter(adapter);
                                    tamanio = String.valueOf(itemRec.size());
                                    Log.i("result","Data: "+tamanio);



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

                        }

                    }){
                        @Override
                        protected Map<String, String> getParams () throws AuthFailureError {
                            Map<String,String> parametros = new HashMap<String, String>();

                            parametros.put("departamento", String.valueOf(1));
                            parametros.put("estado", String.valueOf(state_frag));


                            return parametros;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
                    requestQueue.add(stringRequest);


            }
        });

        /********************************/
        return v;

    }
}