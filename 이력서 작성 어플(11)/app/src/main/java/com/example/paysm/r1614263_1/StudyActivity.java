package com.example.paysm.r1614263_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

public class StudyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        Button b = findViewById(R.id.button4);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.button4) {
                    Intent intents = new Intent(StudyActivity.this, MainActivity.class);
                    Button b = findViewById(R.id.button4);
                    EditText Text1 = (EditText) findViewById(R.id.editText4);
                    intents.putExtra("TEXT1",Text1.getText().toString());
                    EditText Text2 = (EditText) findViewById(R.id.editText3);
                    intents.putExtra("TEXT2", Text2.getText().toString());
                    EditText Text3 = (EditText) findViewById(R.id.editText2);
                    intents.putExtra("TEXT3", Text3.getText().toString());
                    EditText Text4 = (EditText) findViewById(R.id.editText);
                    intents.putExtra("TEXT4", Text4.getText().toString());
                    startActivity(intents);
                }
            }
        });
    }
}
