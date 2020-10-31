package com.lakshmi.googleplaymusicapp;

public class Model {
    private String name;
    private String songartist;
    private int resid;

    public Model(String name,String songartist,int resid) {
        this.name = name;
        this.resid = resid;
        this.songartist=songartist;
    }

    public String getName() {
        return name;
    }

    public int getResid() {
        return resid;
    }

    public String getSongartist() {
        return songartist;
    }
}
