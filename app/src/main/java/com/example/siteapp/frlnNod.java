package com.example.siteapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.siteapp.databinding.FragmentFrlnApBinding;
import com.example.siteapp.databinding.FragmentFrlnNodBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link frlnNod#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frlnNod extends Fragment {

    FragmentFrlnNodBinding layout;

    public frlnNod() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static frlnNod newInstance(String param1, String param2) {
        frlnNod fragment = new frlnNod();
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
        layout= FragmentFrlnNodBinding.inflate(inflater,container,false);
        View v=layout.getRoot();





















        return v;
    }
}