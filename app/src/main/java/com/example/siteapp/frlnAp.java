package com.example.siteapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.siteapp.databinding.FragmentFrInAdBinding;
import com.example.siteapp.databinding.FragmentFrlnApBinding;
import com.example.siteapp.databinding.FragmentFrlnTecuBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link frlnAp#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frlnAp extends Fragment {

    FragmentFrlnApBinding layout;


    public frlnAp() {
        // Required empty public constructor
    }

    public static frlnAp newInstance(String param1, String param2) {
        frlnAp fragment = new frlnAp();


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
        layout= FragmentFrlnApBinding.inflate(inflater,container,false);
        View v=layout.getRoot();





















        return v;
    }
}