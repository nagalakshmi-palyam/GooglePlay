package com.lakshmi.googleplaymusicapp;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemsplayViewHolder extends RecyclerView.ViewHolder {
    private ItemClickListener itemClickListener;
    private TextView mTvTitle;
    private TextView martist;
    private ImageView imageView;
    private LinearLayout linearLayout;

    public ItemsplayViewHolder(@NonNull View itemView,ItemClickListener itemClickListener) {
        super(itemView);
        this.itemClickListener=itemClickListener;
        InitViews(itemView);
    }
    private void InitViews(View itemview){
        mTvTitle=itemView.findViewById(R.id.songs_title);
        martist=itemview.findViewById(R.id.songs_artist_name);
        imageView=itemView.findViewById(R.id.songs_cover_one);
        linearLayout=itemview.findViewById(R.id.llid);
    }
    public void SetData(final Model model){
        mTvTitle.setText(model.getName());
        martist.setText(model.getSongartist());
        imageView.setImageResource(model.getResid());
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClicked(model,getAdapterPosition());
            }
        });

    }
}
