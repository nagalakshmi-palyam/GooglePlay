package com.lakshmi.googleplaymusicapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mikhaellopez.circularimageview.CircularImageView;

public class AlbumViewHolder extends RecyclerView.ViewHolder {
    private AlbumclickListener itemClickListener;
    private AlbumModel model;
    private TextView moviename;
    private CardView mcardview;
    private CircularImageView ivmoviecone;
    public AlbumViewHolder(@NonNull View itemView,AlbumclickListener itemClickListener) {
        super(itemView);
        this.itemClickListener=itemClickListener;
        InitViews(itemView);
    }
    private void InitViews(View itemview){
       moviename=itemview.findViewById(R.id.moviename);
       ivmoviecone=itemview.findViewById(R.id.movieposter);
       mcardview=itemview.findViewById(R.id.cardview);
    }
    public void SetData(final AlbumModel model) {
        moviename.setText(model.getMoviename());
        ivmoviecone.setImageResource(model.getIvmovie_cone());
        mcardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClicked(model,getAdapterPosition());
            }
        });
    }

}
