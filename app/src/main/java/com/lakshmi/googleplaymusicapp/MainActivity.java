package com.lakshmi.googleplaymusicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class MainActivity extends AppCompatActivity implements FragmentClickListener,View.OnClickListener{
    private TextView title;
    private ImageButton back;
    private TextView songname;
    private PlayerAdapter playerAdapter;
    private ImageView mainposter;
    private ImageView movieposter;
    private ViewPager mviewpager;
    private TabLayout mtablayout;
    ImageButton like, notlike,dislike,notdislike;
    ImageButton play,pause,play_main,pause_main;
    private SlidingUpPanelLayout mLayout;
    private boolean isServiceBound;
    private ImageButton next;
    private MusicService musicService;
    private SeekBar  mSeekBarAudio;
    private boolean   mUserIsSeeking;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicServiceBinder binder = (MusicService.MusicServiceBinder) service;
            musicService = binder.getMusicService();
            isServiceBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isServiceBound = false;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initviews();
        setViewPagerAdapter();
       // initializeSeekBar();
    }
    private void Initviews(){
        back=findViewById(R.id.back);
        next=findViewById(R.id.next);
        songname=findViewById(R.id.songs_artist_name);
        mainposter=findViewById(R.id.poster);
        mSeekBarAudio=findViewById(R.id.seekBar3);
        title=findViewById(R.id.songs_title);
        movieposter=findViewById(R.id.songs_cover_one);
        mviewpager=findViewById(R.id.viewpage);
        mtablayout=findViewById(R.id.tab);
        like = (ImageButton) findViewById(R.id.imageButton2);
        notlike = (ImageButton) findViewById(R.id.imageButton2new);
        dislike = (ImageButton) findViewById(R.id.button);
        notdislike = (ImageButton) findViewById(R.id.buttontwo);
        play = (ImageButton) findViewById(R.id.play_button);
        pause = (ImageButton) findViewById(R.id.pause_button);
        play_main = (ImageButton) findViewById(R.id.play_button_main);
        pause_main = (ImageButton) findViewById(R.id.pause_button_main);
        Intent intent = new Intent(MainActivity.this, MusicService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        mLayout =  findViewById(R.id.activity_main);
        like.setOnClickListener(this);
        notlike.setOnClickListener(this);
        dislike.setOnClickListener(this);
        notdislike.setOnClickListener(this);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        play_main.setOnClickListener(this);
        pause_main.setOnClickListener(this);
        next.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    private void setViewPagerAdapter() {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,this);
        mviewpager.setAdapter(fragmentAdapter);
        mtablayout.setupWithViewPager(mviewpager);
    }
    @Override
    public void onBackPressed() {
        if (mLayout != null &&
                (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onDatapassed(Bundle movie) {
        movie.getString("moviename");
        title.setText(movie.getString("moviename"));
        movieposter.setImageResource(movie.getInt("movie"));
        mainposter.setImageResource(movie.getInt("movie"));
        songname.setText(movie.getString("songname"));
        Intent intent=new Intent(MainActivity.this,MusicService.class);
        if(isServiceBound){
            pause.setVisibility(View.GONE);
            play.setVisibility(View.VISIBLE);
           musicService.releasePlayer();
        }
        intent.putExtra("songsid",movie.getInt("song"));
        startService(intent);

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch(id){
            case R.id.imageButton2:
                notlike.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this,"You Like the Song",Toast.LENGTH_SHORT).show();
                if (notdislike.getVisibility() == View.VISIBLE){
                    notdislike.setVisibility(View.GONE);
                }
                break;
            case R.id.imageButton2new:
                notlike.setVisibility(View.GONE);
                break;
            case R.id.button:
                notdislike.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this,"You DisLike the Song",Toast.LENGTH_SHORT).show();
                if (notlike.getVisibility() == View.VISIBLE){
                    notlike.setVisibility(View.GONE);
                }
                break;
            case R.id.buttontwo:
                notdislike.setVisibility(View.GONE);
                break;
            case R.id.play_button:
                play.setVisibility(View.GONE);
                pause.setVisibility(View.VISIBLE);
                //Toast.makeText(MainActivity.this,"Song Is now Playing",Toast.LENGTH_SHORT).show();
                if (play_main.getVisibility() == View.VISIBLE){
                    play_main.setVisibility(View.GONE);
                    pause_main.setVisibility(View.VISIBLE);
                }
                if (isServiceBound) {
                    musicService.startMusic();
                }
                break;
            case R.id.pause_button:
                pause.setVisibility(View.GONE);
                play.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this,"Song is Pause",Toast.LENGTH_SHORT).show();
                if (pause_main.getVisibility() == View.VISIBLE){
                    pause_main.setVisibility(View.GONE);
                    play_main.setVisibility(View.VISIBLE);
                }
                if (isServiceBound) {
                    musicService.pauseMusic();
                    Toast.makeText(MainActivity.this,"Songs pause",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.play_button_main:
                play_main.setVisibility(View.GONE);
                pause_main.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this,"Song Is now Playing",Toast.LENGTH_SHORT).show();
                if (play.getVisibility() == View.VISIBLE){
                    play.setVisibility(View.GONE);
                    pause.setVisibility(View.VISIBLE);
                }

                if (isServiceBound) {
                    musicService.startMusic();
                }
                break;
            case R.id.pause_button_main:
                pause_main.setVisibility(View.GONE);
                play_main.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this,"Song is Pause",Toast.LENGTH_SHORT).show();
                if (pause.getVisibility() == View.VISIBLE){
                    pause.setVisibility(View.GONE);
                    play.setVisibility(View.VISIBLE);
                }
                if (isServiceBound) {
                    musicService.pauseMusic();
                    Toast.makeText(MainActivity.this,"Songs pause",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.next:
                if (isServiceBound) {
                    musicService.startMusic();
                }
                break;
            case R.id.back:
                if (isServiceBound) {
                    musicService.pauseMusic();
                    Toast.makeText(MainActivity.this,"Songs pause",Toast.LENGTH_LONG).show();
                }



        }

        }
    private void initializeSeekBar() {
        mSeekBarAudio.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int userSelectedPosition = 0;

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        mUserIsSeeking = true;
                    }

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser) {
                            userSelectedPosition = progress;
                        }
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        mUserIsSeeking = false;
                        playerAdapter.seekT(2);
                    }
                });
    }



}
