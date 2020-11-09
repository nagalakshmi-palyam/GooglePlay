package com.lakshmi.googleplaymusicapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class MusicService extends Service  implements LooperPreparedListener  {
    private MusicServiceBinder binder = new MusicServiceBinder();
    private BackgroundHandlerThread handlerThread;
    private MediaPlayer mediaPlayer;
    private boolean isLooperReady;
    private int Song=0;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Lakshmi", "onCreate of music service");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            showNotificationAndStartForeGround();
        } else {
            startForeground(1, new Notification());
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Lakshmi", "on bind");
        handlerThread = new BackgroundHandlerThread("Music Service", this);
        handlerThread.start();
     mediaPlayer = MediaPlayer.create(this,R.raw.arereekkada);

        return binder;
    }
    public void startMusic() {
        if (isLooperReady) {
            handlerThread.addTaskToQueue(new Runnable() {
                @Override
                public void run() {
                    Log.d("Lakshmi", "Thread name" + Thread.currentThread().getName());
                    mediaPlayer.start();
                }
            });
        }
    }

    public void pauseMusic() {
        if (isLooperReady) {
            handlerThread.addTaskToQueue(new Runnable() {
                @Override
                public void run() {
                    Log.d("Lakshmi", "Thread name" + Thread.currentThread().getName());

                    mediaPlayer.pause();
                }
            });
        }
    }

    public void releasePlayer(){
        if (isLooperReady) {
            handlerThread.addTaskToQueue(new Runnable() {
                @Override
                public void run() {
                    Log.d("Lakshmi", "Thread name" + Thread.currentThread().getName());

                    mediaPlayer.pause();
                    mediaPlayer.release();
                }
            });
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Lakshmi", "onDestroy of music service");
       // mediaPlayer.release();

    }
    @Override
    public void onLooperReady() {
        isLooperReady = true;
    }
    public class MusicServiceBinder extends Binder {
        public MusicService getMusicService() {
            return MusicService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent.getExtras()!=null ){
            mediaPlayer = MediaPlayer.create(MusicService.this,intent.getExtras().getInt("songsid"));
        }

        return super.onStartCommand(intent, flags, startId);
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private void showNotificationAndStartForeGround() {
        String NOTIFICATION_CHANNEL_ID = "Nagalakshmi";
        String channelName = "Music Player";
        NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
        chan.setLightColor(Color.BLUE);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        Notification notification = notificationBuilder.setOngoing(true)
                .setSmallIcon(R.drawable.round_pause_black_48dp)
                .setContentTitle("Music Player App Running")
                .setContentText("Songs Playing")
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        startForeground(2, notification);
    }

}
