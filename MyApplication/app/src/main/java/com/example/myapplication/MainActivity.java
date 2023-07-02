package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonE;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonE = (Button) findViewById(R.id.btn1);
        txt = (TextView) findViewById(R.id.txt1);
        buttonE.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        
    }
}