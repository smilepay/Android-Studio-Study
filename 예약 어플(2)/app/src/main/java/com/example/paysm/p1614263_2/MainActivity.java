package com.example.paysm.p1614263_2;

import java.util.Calendar;
import android.app.TimePickerDialog;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TimePicker;
import android.widget.DatePicker;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.DialogInterface;


public class MainActivity extends AppCompatActivity {
    Button btnSelectDate, btnSelectTime,cancel;
    TextView output;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    String format;
    String formats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSelectDate = findViewById(R.id.date);
        btnSelectTime = findViewById(R.id.time);
        btnSelectTime = findViewById(R.id.time);
        cancel = findViewById(R.id.cancel);
        output = findViewById(R.id.output);
    }

    public void onClick(View v) {
        if (v == btnSelectDate) {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    formats = output.getText().toString();
                    output.setText(year + "년 " + (monthOfYear + 1) + "월 " + dayOfMonth + "일 " + formats);
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnSelectTime) {
            final Calendar c = Calendar.getInstance();
            int mHour = c.get(Calendar.HOUR_OF_DAY);
            int mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    if (hourOfDay == 0){
                        hourOfDay +=12;
                        format = "오전";
                    } else if (hourOfDay == 12){
                        format = "오후";
                    } else if (hourOfDay > 12){
                        hourOfDay -=12;
                        format = "오후";
                    } else{
                        format = "오전";
                    }
                    output.append(" "+format + " "+hourOfDay + "시 " + minute + "분");
                }
            }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if(v== cancel) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("취소하시겠습니까?");
            alertDialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id){
                    output.setText("");
                }
            });
            alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id){
                    dialog.cancel();
                }
            });
            AlertDialog alerDialog = alertDialogBuilder.create();
            alerDialog.show();
        }
    }
}
