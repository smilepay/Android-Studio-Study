package com.example.paysm.p1614263_9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.RadioGroup;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    RadioGroup rg;
    Intent intent;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg = findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton:
                        i=1;
                        break;
                    case R.id.radioButton2:
                        i=2;
                        break;
                    case R.id.radioButton3:
                        i=3;
                        break;
                }
            }
        });
    }

    public void onClick(View view) {
        if (i==1){
            intent = new Intent(MainActivity.this,MapsActivity.class);
            intent.putExtra("number",1);
            startActivity(intent);
        }
        else if(i==2) {
            intent = new Intent(MainActivity.this,MapsActivity.class);
            intent.putExtra("number",2);
            startActivity(intent);
        }
        else if(i==3){
            intent = new Intent(MainActivity.this,MapsActivity.class);
            intent.putExtra("number",3);
            startActivity(intent);

        }
    }

}


