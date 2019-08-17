package com.example.paysm.r1614263_2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Document;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.content.Intent;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
    TextView textview;
    Document doc = null;
    LinearLayout layout = null;
    RadioGroup rg;
    RadioButton rb1,rb2,rb3;
    Intent intent;
    int i=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview = (TextView) findViewById(R.id.textView1);

        rg = (RadioGroup)findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
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
            String name = rb1.getText().toString();
            intent = new Intent(MainActivity.this, WeatherActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("number",1);
            startActivity(intent);
        }
        else if(i==2) {
            String name2 = rb2.getText().toString();
            intent = new Intent(MainActivity.this, WeatherActivity.class);
            intent.putExtra("name", name2);
            intent.putExtra("number", 2);
            i = 2;
            startActivity(intent);
        }
        else if(i==3){
            String name3 = rb3.getText().toString();
            intent = new Intent(MainActivity.this, WeatherActivity.class);
            intent.putExtra("name", name3);
            intent.putExtra("number",3);
            startActivity(intent);

        }
    }


}