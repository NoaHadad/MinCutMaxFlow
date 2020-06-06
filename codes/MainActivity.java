package com.example.noa.flowgraph;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewAnimator;

public class MainActivity extends AppCompatActivity {

    Button oneSource;
    Button multipleSource;
    EditText sources;
    EditText targets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        oneSource = (Button) findViewById(R.id.btn1);
        multipleSource = (Button) findViewById(R.id.btn2);
        sources=(EditText) findViewById(R.id.sources);
        targets=(EditText) findViewById(R.id.targets);


        oneSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DrawingOneSourceNet.class);
                MyView.multiple = 0;
                startActivity(intent);
            }});
        multipleSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= sources.getText().toString();
                String t= targets.getText().toString();
                if ((s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4") || s.equals("5") ) && (t.equals("1") || t.equals("2") || t.equals("3") || t.equals("4") || t.equals("5") )) {
                    MyView.sources = Integer.parseInt(sources.getText().toString());
                    MyView.targets = Integer.parseInt(targets.getText().toString());
                    MyView.multiple = 1;
                    Intent intent = new Intent(MainActivity.this, DrawingOneSourceNet.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, "Invalid input. You need to choose numbers between 1-5.", Toast.LENGTH_LONG).show();
                    sources.setText("");
                    targets.setText("");
                }


                }
        });
    }

}