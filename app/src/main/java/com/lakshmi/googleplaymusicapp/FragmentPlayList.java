package com.lakshmi.googleplaymusicapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FragmentPlayList extends Fragment implements ItemClickListener{
    private RecyclerView mrecyclerview;
    private List<Model> modelList=new ArrayList<>();
    private ItemAdapter adapter;
    private FragmentClickListener mFragmentListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_play_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        builddata();
        SetAdapter();
    }
    private void initViews(View view){
        mrecyclerview=view.findViewById(R.id.recyclerview);
    }
    private void builddata(){
        modelList.add(new Model("Arere Ekkada.....","Nani",R.drawable.nenulocal));
        modelList.add(new Model("Unnattundi Gunde..... ","Nani",R.drawable.ninnukori));
        modelList.add(new Model("Othaiyadi pathaila.....","SivaKarthikeyan",R.drawable.kanna));
        modelList.add(new Model("What Amma What is this Amma......","Ram",R.drawable.unnadiokate));
        modelList.add(new Model("Meghama Neeli meghama......","Surya",R.drawable.journey));
        modelList.add(new Model("Life of Ram......","Sharvanand",R.drawable.janu));
        modelList.add(new Model("Ippudaina evaraina.....","Sharvanand",R.drawable.mahanubhavudu));
        modelList.add(new Model("Samajavaragamana.....","AlluArjun",R.drawable.alluarjun));
        modelList.add(new Model("Enkadal solla neram..... ","Karthi",R.drawable.avara));
        modelList.add(new Model("Neekannu Neeli Samudram..... ","Vaishnav Tej",R.drawable.uppena1));
        modelList.add(new Model("Inkem Inkem Kavale..... ","Vijay Devarakonda",R.drawable.geethag));
        modelList.add(new Model("Kadale Kadale..... ","Vijay Sethupathi",R.drawable.kkkk));
        modelList.add(new Model("Nalupaina Kannayya.... ","Vishal",R.drawable.bharani));
        modelList.add(new Model("Sahore Bahubali..... ","Prabhas",R.drawable.bahubali));
        modelList.add(new Model("Ennenno OOhalu..... ","AlluArjun",R.drawable.parugu));
        modelList.add(new Model("He is soo cute..... ","Mahesh Babu",R.drawable.sarileruneekevarru));
        modelList.add(new Model("Ekkadiki Neeparugu..... ","Chakri",R.drawable.bambay));
    }
    private void SetAdapter(){
        adapter=new ItemAdapter(this,modelList);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        mrecyclerview.setLayoutManager(layoutManager);
        mrecyclerview.setAdapter(adapter);
    }
    public void setFragmentListener(FragmentClickListener fragmentListener) {
        mFragmentListener = fragmentListener;
    }
    @Override
    public void onItemClicked(Model model, int position) {
        Bundle bundle=new Bundle();
        bundle.putString("songname",model.getName());
        bundle.putString("artistname",model.getSongartist());
        bundle.putInt("newsongs",model.getResid());
        mFragmentListener.onDatapassed(bundle);
    }


}