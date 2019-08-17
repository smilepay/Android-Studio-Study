package com.example.paysm.p1614263_7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    boolean running = true;

    EditText text10;
    EditText text2;
    EditText text3;
    Button button;

    int num10;
    int num102;
    String returnString;
    String returnString1;
    Thread thread1;
    Thread thread2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text10 = findViewById(R.id.editText);
        text2 = findViewById(R.id.editText3);
        text3 = findViewById(R.id.editText4);
        button = findViewById(R.id.button);

        thread1 = new Thread();
        thread2 = new Thread();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num10 = Integer.parseInt(text10.getText().toString());
                num102 = Integer.parseInt(text10.getText().toString());
                onStart();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        running = true;
        thread1 = new Thread(new Runnable() {
            public void run() {
                returnString1 = "";
                while(num10 != 0){
                    if( (num10 % 2) < 10 ) {
                        returnString1 = (num10 % 2) + returnString1;
                        num10 /= 2;
                    }
                    else {
                        int temp1 = (char)((num10 % 2)  + 55);
                        returnString1 = Integer.toString(temp1) + returnString1;
                    }
                }
                text2.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.v("in", "text2=" );
                        text2.setText(returnString1);
                    }
                });
            }
        });
        thread2 = new Thread(new Runnable() {
            public void run() {
                returnString = "";
                while(num102 != 0){
                    if( (num102 % 3) < 10 ) {
                        returnString = (num102 % 3) + returnString;
                        num102 /= 3;
                    }
                    else {
                        int temp1 = (char)((num102 % 3)  + 55);
                        returnString = Integer.toString(temp1) + returnString;
                    }
                }
                text3.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.v("in", "text3=" );
                        text3.setText(returnString);
                    }
                });
            }
        });
        running = true;
        thread1.start();
        Log.v("THREAD", "time1=" );
        thread2.start();
        Log.v("THREAD", "time2=" );
    }

    @Override
    public void onStop() {
        super.onStop();
        running = false;
    }
}

