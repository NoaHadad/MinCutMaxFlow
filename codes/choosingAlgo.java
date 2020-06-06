package com.example.noa.flowgraph;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class choosingAlgo extends AppCompatActivity {


    Button edmondsKarp;
    Button dinits;
    Button pushRelabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_algo);

        edmondsKarp = (Button) findViewById(R.id.button4);
        dinits = (Button) findViewById(R.id.button20);
        pushRelabel= (Button) findViewById(R.id.button500);

        edmondsKarp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(choosingAlgo.this, edmonsKarpAlgo.class);
                startActivity(intent);
            }});

        dinits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(choosingAlgo.this, DinitsAlgo.class);
                startActivity(intent);
            }});

        pushRelabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(choosingAlgo.this, PushRelabelAlgo.class);
                startActivity(intent);
            }});


    }

}
