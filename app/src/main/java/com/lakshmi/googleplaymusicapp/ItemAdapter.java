package com.lakshmi.googleplaymusicapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemsplayViewHolder> {
    private ItemClickListener itemClickListener;
    private List<Model> modelist=new ArrayList<>();

    public ItemAdapter(ItemClickListener itemClickListener, List<Model> modelist) {
        this.itemClickListener = itemClickListener;
        this.modelist = modelist;
    }

    @NonNull
    @Override
    public ItemsplayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fraplay_item,parent,false);
        return new ItemsplayViewHolder(view,itemClickListener);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemsplayViewHolder holder, int position) {
        Model data=modelist.get(position);
        holder.SetData(data);

    }

    @Override
    public int getItemCount() {
        return modelist.size();
    }
}
