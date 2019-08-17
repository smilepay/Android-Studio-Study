package com.example.paysm.p1614263_4;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ListView list;
    String[] titles= {
            "학과명: 컴퓨터과학 전공",
            "학과명: 기초공학부",
            "학과명: 역사 문화 학과",
            "학과명: 한국어 문학부",
            "학과명: 법학부",
            "학과명: 미디어 학부",
            "학과명: 성악과",
            "학과명: 회화과",
            "학과명: 사회 심리 학과",
            "학과명: 작곡과"
    } ;
    String[] title_engs= {
            "영문명: DEPARTMENT OF COMPUTER SCIENCE",
            "영문명: DIVISION OF BASIC ENGINEETING",
            "영문명: DEPARTMENT OF HISTORY & CULTURE",
            "영문명: DIVISION OF KOREAN LANGUAGE & LITERATURE",
            "영문명: DIVISION OF LAW",
            "영문명: SCHOOL OF COMMUNICATION & MEDIA",
            "영문명: DEPARTMENT OF ORCHESTRAL INSTRUMENTS",
            "영문명: DEPARTMENT OF PAINTING",
            "영문명: DEPARTMENT OF SOCIAL PSYCHOLOGY",
            "영문명: DEPARTMENT OF COMPOSITION"
    } ;
    Integer[] image2= {
            R.drawable.computer_science,
            R.drawable.engineering,
            R.drawable.history,
            R.drawable.korean,
            R.drawable.law,
            R.drawable.media,
            R.drawable.music,
            R.drawable.painting,
            R.drawable.social,
            R.drawable.song
    };
    String [] years= {
            "2017 년",
            "2014 년",
            "2017 년",
            "1989 년",
            "2018 년",
            "2017 년",
            "1997 년",
            "2012 년",
            "2017 년",
            "2013 년"
    } ;
    String [] counts= {
            "20 명",
            "18 명",
            "50 명",
            "68 명",
            "72 명",
            "93 명",
            "75 명",
            "60 명",
            "72 명",
            "62 명"
    } ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomList adapter = new CustomList(MainActivity.this);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
    }
    public class CustomList extends ArrayAdapter<String> {
        private final Activity context;
        public CustomList(Activity context ) {
            super(context, R.layout.list_item, titles);
            this.context = context;
        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView= inflater.inflate(R.layout.list_item, null, true);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);
            TextView title = (TextView) rowView.findViewById(R.id.title);
            TextView title_eng = (TextView) rowView.findViewById(R.id.title_eng);
            TextView year = (TextView) rowView.findViewById(R.id.year);
            TextView count = (TextView) rowView.findViewById(R.id.count);

            title.setText(titles[position]);
            imageView.setImageResource(image2[position]);
            title_eng.setText(title_engs[position]);
            year.setText(years[position]);
            count.setText(counts[position]);
            return rowView;
        }
    }

}
