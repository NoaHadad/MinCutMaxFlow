package com.example.noa.flowgraph;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DrawingOneSourceNet extends AppCompatActivity {

    Button edge;
    Button algo;
    Button delE;
    Button delV;
    MyView view;
    EditText source;
    EditText destination;
    EditText length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drawing_one_source_net);
        edge = (Button) findViewById(R.id.button2);
        algo = (Button) findViewById(R.id.button3);
        delE= (Button) findViewById(R.id.delE);
        delV= (Button) findViewById(R.id.delV);
        view=(MyView)findViewById(R.id.simpleDrawingView1);
        source=(EditText)findViewById(R.id.source1);
        destination=(EditText)findViewById(R.id.dest2);
        length=(EditText)findViewById(R.id.length1);


        algo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DrawingOneSourceNet.this, choosingAlgo.class);
                startActivity(intent);
            }});

        delE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.deleteEdge();
                view.invalidate();
            }});

        delV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.deleteVertex();
                view.invalidate();
            }});

        edge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((source.getText().toString().length()!=1) || (destination.getText().toString().length()!=1) ){
                    Toast.makeText(DrawingOneSourceNet.this, "קלט לא תקין", Toast.LENGTH_LONG).show();
                }
                else {
                    int s = (int) (source.getText().toString().charAt(0)) - 63;
                    int d = (int) (destination.getText().toString().charAt(0)) - 63;
                    boolean flag1 = false;
                    boolean flag2 = false;
                    if ((MyView.multiple==0) && (source.getText().toString().charAt(0) == 's')) flag1 = true;
                    if ((MyView.multiple==0) && (destination.getText().toString().charAt(0) == 't')) flag2 = true;
                    for (Vertex v1 : MyView.vertices) {
                        if ((v1.number - 63) == s) flag1 = true;
                        if ((v1.number - 63) == d) flag2 = true;
                    }
                    if (flag1 && flag2) {
                        String l = length.getText().toString();
                        try {
                            int x = Integer.parseInt(l);
                            view.addEdge(s, d, x);
                            view.invalidate();
                        } catch (Exception e) {
                            Toast.makeText(DrawingOneSourceNet.this, "Invalid input", Toast.LENGTH_LONG).show();
                        } finally {
                            length.setText("");
                        }
                        ;
                    } else {
                        Toast.makeText(DrawingOneSourceNet.this, "Invalid input", Toast.LENGTH_LONG).show();
                    }
                }
                length.setText("");
                source.setText("");
                destination.setText("");
                }


            }
        );
    }

}