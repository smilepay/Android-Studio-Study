package com.example.paysm.p1614263_6;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MusicService3 extends Service {
    private static final String TAG = "MusicService";
    MediaPlayer player;
    int num;
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate()");
        player = MediaPlayer.create(this, R.raw.wrong);
        player.setLooping(false); // Set looping
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Music Service가 중지되었습니다.", Toast.LENGTH_LONG)
                .show();
        Log.d(TAG, "onDestroy()");
        player.stop();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Music Service가 시작되었습니다.", Toast.LENGTH_LONG)
                .show();
        Log.d(TAG, "onStart()");
        player.start();
        return super.onStartCommand(intent, flags, startId);
    }
}
