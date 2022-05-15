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

import com.example.siteapp.databinding.FragmentFrlnTecuBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link frlnTecu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frlnTecu extends Fragment {
    FragmentFrlnTecuBinding layout;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public frlnTecu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frlnTecu.
     */
    // TODO: Rename and change types and number of parameters
    public static frlnTecu newInstance(String param1, String param2) {
        frlnTecu fragment = new frlnTecu();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layout= FragmentFrlnTecuBinding.inflate(inflater,container,false);
        View v=layout.getRoot();

        RecyclerView list=layout.lista;
        ArrayList<Incidencias> itemRec;

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
                        RecyclerView.Adapter<myAdapter.ContenetViews> adapter= new myAdapter(itemRec);
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

                parametros.put("departamento", String.valueOf(1));

                SharedPreferences admin=requireContext().getSharedPreferences("x", Context.MODE_PRIVATE);
                String id=admin.getString("id","");
                parametros.put("id", id);



                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        requestQueue.add(stringRequest);


        return v;

    }
}