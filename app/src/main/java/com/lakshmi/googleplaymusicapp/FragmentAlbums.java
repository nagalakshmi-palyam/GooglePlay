package com.lakshmi.googleplaymusicapp;

import android.content.Intent;
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

public class FragmentAlbums extends Fragment implements AlbumclickListener {
    private RecyclerView mrecyclerview;
    private List<AlbumModel> modelList=new ArrayList<>();
    private AlbumAdapter adapter;
    private FragmentClickListener mFragmentListener;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_albums, container, false);
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
        modelList.add(new AlbumModel(R.drawable.nenusailaja,"Nenusailaja",R.raw.nenusailaja,"Sailaja Sailaja Nuvvenduku....."));
        modelList.add(new AlbumModel(R.drawable.ninnukori,"Ninnukori",R.raw.unnattundigunde,"Unnattundi Gunde....."));
        modelList.add(new AlbumModel(R.drawable.nenulocal,"NenuLocal",R.raw.arereekkada,"Arere Ekkada....."));
        modelList.add(new AlbumModel(R.drawable.journey,"Journey",R.raw.meghama,"Meghama Neeli meghama......"));
        modelList.add(new AlbumModel(R.drawable.janu,"Janu",R.raw.life,"Life of Ram......"));
        modelList.add(new AlbumModel(R.drawable.mahanubhavudu,"Mahanubhavudu",R.raw.eppudina,"Ippudaina evaraina....."));
        modelList.add(new AlbumModel(R.drawable.unnadiokate,"Unnadiokate Zindagi",R.raw.what,"What Amma What is this Amma......"));
        modelList.add(new AlbumModel(R.drawable.alluarjun,"Alavaikuntapuramlo",R.raw.fav,"Samajavaragamana....."));
        modelList.add(new AlbumModel(R.drawable.avara,"Avara",R.raw.enkadal,"Enkadal solla neram....."));
        modelList.add(new AlbumModel(R.drawable.uppena1,"Uppena",R.raw.neekannu,"Neekannu Neeli Samudram..... "));
        modelList.add(new AlbumModel(R.drawable.geethag,"Geetha Govindam",R.raw.neekannu,"Inkem Inkem Kavale..... "));
        modelList.add(new AlbumModel(R.drawable.kkkk,"96",R.raw.kadale,"Kadale Kadale..... "));
        modelList.add(new AlbumModel(R.drawable.bharani,"Bharani",R.raw.nalupina,"Nalupaina Kannayya.... "));
        modelList.add(new AlbumModel(R.drawable.kanna,"Kanna",R.raw.othayadi,"Othaiyadi pathaila....."));
        modelList.add(new AlbumModel(R.drawable.bahubali,"Bahubali",R.raw.sahore,"Sahore Bahubali..... "));
        modelList.add(new AlbumModel(R.drawable.parugu,"Parugu",R.raw.manakanna,"Ennenno OOhalu..... "));
        modelList.add(new AlbumModel(R.drawable.sarileruneekevarru,"Sarileruneekevvaru",R.raw.heissocute,"He is soo cute..... "));
        modelList.add(new AlbumModel(R.drawable.bambay,"Bambay.....",R.raw.hrudayama,"Ekkadiki Neeparugu..... "));
    }
    private void SetAdapter(){
        adapter=new AlbumAdapter(modelList,this);
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),3);
        mrecyclerview.setLayoutManager(layoutManager);
        mrecyclerview.setAdapter(adapter);
    }
    public void setFragmentListener(FragmentClickListener fragmentListener) {
        mFragmentListener = fragmentListener;
    }

    @Override
    public void onItemClicked(AlbumModel model, int position) {
      Bundle bundle=new Bundle();
      bundle.putInt("movie",model.getIvmovie_cone());
      bundle.putInt("song",model.getSongid());
      bundle.putString("moviename",model.getMoviename());
      bundle.putString("songname",model.getSongname());
      mFragmentListener.onDatapassed(bundle);
    }

}