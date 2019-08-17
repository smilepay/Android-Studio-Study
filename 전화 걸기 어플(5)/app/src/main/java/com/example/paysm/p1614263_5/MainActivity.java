package com.example.paysm.p1614263_5;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RadioGroup rg = findViewById(R.id.gr);
        Button b = findViewById(R.id.button);
        final TextView tv = findViewById(R.id.text);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                switch (rg.getCheckedRadioButtonId()) {
                    case R.id.web:
                        intent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://"+tv.getText()));
                        break;
                    case R.id.call:
                        intent = new Intent(Intent.ACTION_DIAL,
                                Uri.parse("tel:"+ tv.getText()));
                        break;
                }
                if (intent != null) {
                    startActivity(intent);
                }
            }
        });
    }
}



