package com.lakshmi.googleplaymusicapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumViewHolder> {
    private List<AlbumModel> modellist=new ArrayList<>();
    private AlbumclickListener albumclickListener;

    public AlbumAdapter(List<AlbumModel> modellist, AlbumclickListener albumclickListener) {
        this.modellist = modellist;
        this.albumclickListener = albumclickListener;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item,parent,false);
        return new AlbumViewHolder(view,albumclickListener);

    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        AlbumModel data=modellist.get(position);
        holder.SetData(data);


    }

    @Override
    public int getItemCount() {
        return modellist.size();
    }
}
