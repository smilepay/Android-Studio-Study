package com.example.paysm.r1614263_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.content.Context;


public class ImageActivity extends AppCompatActivity {
    Integer[] img = {
            R.drawable.clothing, R.drawable.computer,
            R.drawable.math,
            R.drawable.media, R.drawable.software
    };
    ImageButton imgButton;
    int number;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Gallery g = findViewById(R.id.gallery1);

        imgButton = findViewById(R.id.imageButton4);

        g.setAdapter(new ImageAdapter(this));
        g.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                imgButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imgButton.setImageResource(img[position]);
                number = position;
            }
        });
    }

    public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context context) {
            mContext = context;
        }

        public int getCount() {
            return img.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            imageView = new ImageView(mContext);
            imageView.setImageResource(img[position]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new Gallery.LayoutParams(200, 250));
            return imageView;
        }
    }
    public void onClick1(View v) {
        if (v.getId() == R.id.button5) {
            Intent intent = getIntent();
            String text1 = intent.getStringExtra("TEXT1");
            String text2 = intent.getStringExtra("TEXT2");
            String text3 = intent.getStringExtra("TEXT3");
            String text4 = intent.getStringExtra("TEXT4");
            Intent intent4 = new Intent(ImageActivity.this, MainActivity.class);
            intent4.putExtra("TEXT1", text1);
            intent4.putExtra("TEXT2", text2);
            intent4.putExtra("TEXT3", text3);
            intent4.putExtra("TEXT4", text4);
            intent4.putExtra("img", img[number]);
            startActivity(intent4);
        }
    }
}


