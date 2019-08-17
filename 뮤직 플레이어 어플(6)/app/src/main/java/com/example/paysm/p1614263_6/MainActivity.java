package com.example.paysm.p1614263_6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MusicService";
    Button start, stop;
    RadioGroup rg;
    int id;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg = (RadioGroup)findViewById(R.id.radioGroup1);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    public void onClick(View src) {
        switch (src.getId()) {
            case R.id.start:
                Log.d(TAG, "onClick() start ");
                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId){
                            case R.id.radio0:
                                Log.d(TAG, "one");
                                Intent intent = new Intent(MainActivity.this,MusicService.class);
                                startService(intent);
                                break;
                            case R.id.radio1:
                                Intent intent2 = new Intent(MainActivity.this,MusicService2.class);
                                startService(intent2);
                                break;
                            case R.id.radio2:
                                Intent intent3 = new Intent(MainActivity.this,MusicService3.class);
                                startService(intent3);
                                break;
                        }
                     }
                });
                break;
            case R.id.stop:
                Log.d(TAG, "onClick() stop");
                stopService(new Intent(this, MusicService.class));
                stopService(new Intent(this, MusicService2.class));
                stopService(new Intent(this, MusicService3.class));
                break;
        }
    }
}
