package com.example.paysm.p1614263_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView input;
    private TextView output;

    private Button add, mul,eql,del,zero,one,two;

    int []number = new int [1000];
    int i=0;
    int sum =0;
    int result = 0;
    int operator;
    int sum1=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.inputs);
        output = findViewById(R.id.outputs);

        add = findViewById(R.id.btn_add);
        eql = findViewById(R.id.btn_eql);
        mul = findViewById(R.id.btn_mul);
        del =  findViewById(R.id.btn_del);
        zero = findViewById(R.id.btn_0);
        one = findViewById(R.id.btn_1);
        two = findViewById(R.id.btn_2);


        View.OnClickListener cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == zero) {
                    number[i] = 0;
                    input.append("0");
                    i++;
                } else if (v == one) {
                    number[i] = 1;
                    input.append("1");
                    i++;
                } else if (v == two) {
                    number[i] = 2;
                    input.append("2");
                    i++;
                } else if (v == add) {
                    input.append("+");
                    if (operator == 1){
                        for (int j = i-1,k = 1; j>= 0; j--, k=k*3){
                            sum = sum + number[j] * k;
                        }
                    }
                    else {
                        operator = 1;
                        if (i!=0) {
                            for (int j = i - 1, k = 1; j >= 0; j--, k = k * 3) {
                                sum = sum + number[j] * k;
                            }
                        }
                        else
                            output.setText("");
                    }
                    i=0;
                } else if (v == mul) {
                    input.append("*");
                    if (operator == 2){
                        for (int j = i-1,k = 1; j>= 0; j--, k=k*3){
                            sum1 = sum1 + number[j] * k;
                        }
                        sum = sum * sum1;
                    }
                    else {
                        operator = 2;
                        if (i!=0) {
                            for (int j = i - 1, k = 1; j >= 0; j--, k = k * 3) {
                                sum = sum + number[j] * k;
                            }
                        }
                        else
                            output.setText("");
                    }
                    i =0;
                } else if (v == eql) {
                    if (sum ==0){
                        sum = result;
                        result = 0;
                    }
                    for (int j = i-1,k = 1; j>= 0; j--, k=k*3){
                        result= result + number[j] * k;
                    }
                    if (operator == 1)
                        result = result + sum;
                    else if (operator == 2)
                        result = result * sum;
                    output.append(Integer.toString(result));
                    i =0;
                    sum =0;
                    operator=0;
                 } else if (v == del) {
                    i = 0;
                    input.setText("");
                    output.setText("");
                    operator = 0;
                    sum=0;
                    result = 0;
                }
            }

          };
        add.setOnClickListener(cl);
        mul.setOnClickListener(cl);
        eql.setOnClickListener(cl);
        zero.setOnClickListener(cl);
        one.setOnClickListener(cl);
        two.setOnClickListener(cl);
        del.setOnClickListener(cl);
    }
}

