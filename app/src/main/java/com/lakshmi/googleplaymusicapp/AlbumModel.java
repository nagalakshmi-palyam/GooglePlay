package com.lakshmi.googleplaymusicapp;

import android.widget.ImageView;
import android.widget.TextView;

public class AlbumModel {
    private int ivmovie_cone;
    private String moviename;
    private int songid;
    private String songname;

    public AlbumModel(int ivmovie_cone, String moviename,int songid,String songname) {
        this.ivmovie_cone = ivmovie_cone;
        this.moviename = moviename;
        this.songid=songid;
        this.songname=songname;
    }

    public int getIvmovie_cone() {
        return ivmovie_cone;
    }

    public String getMoviename() {
        return moviename;
    }

    public int getSongid() {
        return songid;
    }

    public String getSongname() {
        return songname;
    }
}
