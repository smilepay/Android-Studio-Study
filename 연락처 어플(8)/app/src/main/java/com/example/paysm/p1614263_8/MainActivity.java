package com.example.paysm.p1614263_8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View v){
        if( v.getId() == R.id.button){
            Intent intent = new Intent(MainActivity.this,ListActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.button3){
            Intent intent = new Intent(MainActivity.this,AddActivity.class);
            startActivity(intent);
        }
    }
}
