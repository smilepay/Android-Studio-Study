package com.example.paysm.r1614263_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class WorkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        TextView  View5 = findViewById(R.id.textView5);
        TextView  View6 = findViewById(R.id.textView6);
        TextView  View7 = findViewById(R.id.textView7);
        TextView  View8 = findViewById(R.id.textView8);
        ImageButton imgButton = findViewById(R.id.imageButton);

        Intent intent2 = getIntent();
        View5.setText(intent2.getStringExtra("TEXT1"));
        View6.setText(intent2.getStringExtra("TEXT2"));
        View7.setText(intent2.getStringExtra("TEXT3"));
        View8.setText(intent2.getStringExtra("TEXT4"));
        imgButton.setImageResource(intent2.getExtras().getInt("img"));
        imgButton.setScaleType(ImageView.ScaleType.FIT_XY);
    }
    public void onClick1(View v) {
        if (v.getId() == R.id.button6) {
            Intent intent = new Intent(WorkActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
