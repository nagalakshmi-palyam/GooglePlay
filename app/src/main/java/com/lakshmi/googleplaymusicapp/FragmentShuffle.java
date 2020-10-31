package com.lakshmi.googleplaymusicapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FragmentShuffle extends Fragment implements ItemClickListener {
    private RecyclerView mrecyclerview;
    private List<Model> modelList=new ArrayList<>();
    private ItemAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shuffle, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initViews(View view){
        mrecyclerview=view.findViewById(R.id.recyclerview);
    }
    private void builddata(){

    }
    private void SetAdapter(){
        adapter=new ItemAdapter(this,modelList);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        mrecyclerview.setLayoutManager(layoutManager);
        mrecyclerview.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(Model model, int position) {

    }
}