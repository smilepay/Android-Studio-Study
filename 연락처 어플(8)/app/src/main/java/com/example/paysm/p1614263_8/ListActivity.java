package com.example.paysm.p1614263_8;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.content.Intent;

public class ListActivity extends AppCompatActivity {
    DBHelper helper;
    SQLiteDatabase db;
    EditText edit_name, edit_tel, edit_birth;
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_list);
        helper = new DBHelper(this);
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM contacts", null);
        startManagingCursor(cursor);
        String[] from = {"name", "tel", "birth"};
        int[] to = {R.id.textView4, R.id.textView7, R.id.textView8};
        adapter = new SimpleCursorAdapter(this, R.layout.list, cursor, from, to);
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Cursor c = (Cursor) adapter.getItem(position);
                String name = c.getString(1);
                String tel = c.getString(2);
                String birth = c.getString(3);

                Intent intent = new Intent(ListActivity.this, UpdateActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("tel", tel);
                intent.putExtra("birth", birth);
                startActivity(intent);

            }
        });
    }


    public void onClick(View v) {
        if (v.getId() == R.id.button10) {
            Intent intent = new Intent(ListActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
