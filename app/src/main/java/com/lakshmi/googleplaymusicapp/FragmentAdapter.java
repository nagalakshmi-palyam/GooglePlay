package com.lakshmi.googleplaymusicapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private FragmentClickListener fragmentClickListener;

    public FragmentAdapter(@NonNull FragmentManager fm, int behavior,FragmentClickListener fragmentClickListener) {
        super(fm, behavior);
        this.fragmentClickListener=fragmentClickListener;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                FragmentAlbums fragmentAlbums=new FragmentAlbums();
                fragmentAlbums.setFragmentListener(fragmentClickListener);
                return fragmentAlbums;
            case 1:
                FragmentPlayList fragmentPlayList=new FragmentPlayList();
                fragmentPlayList.setFragmentListener(fragmentClickListener);
                return fragmentPlayList;

        }
        return new FragmentPlayList();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Albums";
            case 1:
                return "PlayLists";

        }
        return super.getPageTitle(position);
    }
}
