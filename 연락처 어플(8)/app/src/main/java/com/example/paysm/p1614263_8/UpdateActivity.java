package com.example.paysm.p1614263_8;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    DBHelper helper;
    SQLiteDatabase db;
    EditText edit_name, edit_tel, edit_birth;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);
        helper = new DBHelper(this);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = helper.getReadableDatabase();
        }
        edit_name = findViewById(R.id.name1);
        edit_tel = findViewById(R.id.tel1);
        edit_birth = findViewById(R.id.birth1);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        String tel = intent.getStringExtra("tel");
        String birth = intent.getStringExtra("birth");
        edit_name.setText(name);
        edit_tel.setText(tel);
        edit_birth.setText(birth);
        Cursor cursor;
        cursor = db.rawQuery("SELECT name,tel,birth FROM contacts WHERE name='"
                + name + "';", null);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.update) {
            String name1 = edit_name.getText().toString();
            String tel = edit_tel.getText().toString();
            String birth = edit_birth.getText().toString();
            db.execSQL("UPDATE contacts SET name = '"+ name1 +"' , tel = '"+ tel +"',birth = '"+ birth+"' WHERE name = '"+ name +"' ;");
            Toast.makeText(getApplicationContext(), "성공적으로 수정되었음",
                    Toast.LENGTH_SHORT).show();
            edit_name.setText("");
            edit_tel.setText("");
            edit_birth.setText("");
            Intent intent = new Intent(UpdateActivity.this, ListActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.delete) {
            String name = edit_name.getText().toString();
            String tel = edit_tel.getText().toString();
            String birth = edit_birth.getText().toString();
            db.execSQL("DELETE FROM contacts WHERE name = '"+ name +"' ;");
            edit_name.setText("");
            edit_tel.setText("");
            edit_birth.setText("");
            Intent intent = new Intent(UpdateActivity.this, ListActivity.class);
            startActivity(intent);
        }
    }
}