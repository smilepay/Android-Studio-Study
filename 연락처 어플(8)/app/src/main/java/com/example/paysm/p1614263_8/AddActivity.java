package com.example.paysm.p1614263_8;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    DBHelper helper;
    SQLiteDatabase db;
    EditText edit_name, edit_tel,edit_birth;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        helper = new DBHelper(this);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = helper.getReadableDatabase();
        }
        edit_name =  findViewById(R.id.name);
        edit_tel = findViewById(R.id.tel);
        edit_birth = findViewById(R.id.birth);
    }

    public void insert(View target) {
        String name = edit_name.getText().toString();
        String tel = edit_tel.getText().toString();
        String birth = edit_birth.getText().toString();
        db.execSQL("INSERT INTO contacts VALUES (null, '" + name + "', '" + tel
                + "', '" + birth + "');");
        Toast.makeText(getApplicationContext(), "성공적으로 추가되었음",
                Toast.LENGTH_SHORT).show();
        edit_name.setText("");
        edit_tel.setText("");
        edit_birth.setText("");
        Intent intent = new Intent(AddActivity.this,ListActivity.class);
        startActivity(intent);
    }
}