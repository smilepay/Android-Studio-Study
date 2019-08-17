package com.example.paysm.r1614263_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
        public void onClick(View v){
            if( v.getId() == R.id.button1){
                Intent intent = new Intent(MainActivity.this,StudyActivity.class);
                startActivity(intent);
            }else if(v.getId() == R.id.button2){
                Intent intent = new Intent(MainActivity.this,ImageActivity.class);
                Intent intents = getIntent();
                String text1 = intents.getStringExtra("TEXT1");
                String text2 = intents.getStringExtra("TEXT2");
                String text3 = intents.getStringExtra("TEXT3");
                String text4 = intents.getStringExtra("TEXT4");
                intent.putExtra("TEXT1", text1);
                intent.putExtra("TEXT2", text2);
                intent.putExtra("TEXT3", text3);
                intent.putExtra("TEXT4", text4);
                startActivity(intent);
            }else if(v.getId() == R.id.button3){
                Intent intent4 = getIntent();
                String text1 = intent4.getStringExtra("TEXT1");
                String text2 = intent4.getStringExtra("TEXT2");
                String text3 = intent4.getStringExtra("TEXT3");
                String text4 = intent4.getStringExtra("TEXT4");
                int image1 = intent4.getExtras().getInt("img");
                Intent intent2 = new Intent(MainActivity.this,WorkActivity.class);
                intent2.putExtra("TEXT1", text1);
                intent2.putExtra("TEXT2", text2);
                intent2.putExtra("TEXT3", text3);
                intent2.putExtra("TEXT4", text4);
                intent2.putExtra("img", image1);
                startActivity(intent2);
            }

        }


}
