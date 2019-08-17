package com.example.paysm.a1614263_3;
// 소스만 입력하고 Alt+Enter를 눌러서 import 문장을 자동으로 생성한다.


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private SingleTouchView drawView;
    private ImageButton currPaint;
    private ImageButton brushWidth;
    private ImageButton erase;
    int i=5,flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawView = (SingleTouchView) findViewById(R.id.drawing);
        LinearLayout paintLayout = (LinearLayout) findViewById(R.id.paint_colors);
        currPaint = (ImageButton) paintLayout.getChildAt(0);
        brushWidth = (ImageButton)  findViewById(R.id.draw_btn);
        erase = (ImageButton)  findViewById(R.id.erase_btn);
    }

    public void clicked(View view) {
        if (view != currPaint) {
            String color = view.getTag().toString();
            drawView.setColor(color);
            currPaint = (ImageButton) view;
        }
    }
     public void clicked2(View view) {
         if (i < 50 && flag == 0) {
             i += 5;
             drawView.setStrokeWidth(i);
         }
         if (i == 50) {
             flag = 1;
             i -= 5;
             drawView.setStrokeWidth(i);
         }
         if (i > 10 && flag == 1) {
             i -= 5;
             drawView.setStrokeWidth(i);
         }
         if (i == 10) {
             flag = 0;
             i += 5;
             drawView.setStrokeWidth(i);
         }
     }
     public void clicked3(View view){
                drawView.setErase();
        }
    }

