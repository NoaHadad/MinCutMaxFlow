package com.example.noa.flowgraph;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DinitsAlgo extends AppCompatActivity {

   // Dinitz ek;
    MyView1 view;
    Button prev;
    Button Gf;
    Button G;
    Button Gl;
    Button next;
    TextView path;
    TextView flow;
    TextView flow1;
    TextView netLen;
    TextView netLen1;
    TextView final8;   int counter=0;
    TextView graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinits_algo);

        view=(MyView1)findViewById(R.id.simpleDrawingView3);
        view.invalidate();

        prev = (Button) findViewById(R.id.button12);
        Gf = (Button) findViewById(R.id.button13);
        G = (Button) findViewById(R.id.button50);
        Gl = (Button) findViewById(R.id.button15);
        next = (Button) findViewById(R.id.button22);
        path = (TextView) findViewById(R.id.path1);
        flow=(TextView) findViewById(R.id.flow3);
        flow1=(TextView) findViewById(R.id.flow88);
        netLen=(TextView) findViewById(R.id.netLength);
        netLen1=(TextView) findViewById(R.id.netLength1);
        graph=(TextView) findViewById(R.id.graph1);
        final8=(TextView) findViewById(R.id.final2);
   //     ek=new Dinitz();


/*
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Step1 s=ek.prevIter();
                path.setText(s.path);
                if (s.flow==0)
                    flow.setText("");
                else
                    flow.setText(Integer.toString(s.flow));
                if (s.maxFlow==0)
                    flow1.setText("");
                else
                flow1.setText(Integer.toString(s.maxFlow));
                view.invalidate();
                if (s.length==0)
                    netLen.setText("");
                else
                    netLen.setText(Integer.toString(s.length));
                if (s.length==0)
                    netLen.setText("");
                else
                    netLen.setText(Integer.toString(s.length));
                if (s.newLen==0){
                    final8.setText("Finding the length of the shortest path");
                    ek.printGf();
                    graph.setText("The residual graph");
                    view.invalidate();
                    counter=0;
                }
                else if (s.newLen==1){

                    final8.setText("Building the level graph");
                    ek.updateEdgesGl();
                    graph.setText("The level graph      ");
                    view.invalidate();
                }
                else if (s.newLen==2){
                    final8.setText("The algorithm is ended. Max flow is "+Integer.toString(s.maxFlow));
                    ek.printG();
                    graph.setText("The graph               ");
                    view.invalidate();
                }
                else if (s.newLen==3){
                    final8.setText("Finding the length of the shortest path");
                    netLen.setText("-");
                    ek.printGf();
                    graph.setText("The residual graph");
                    view.invalidate();
                }
                else if (s.newLen==4){
                    final8.setText("Finding a path in the level graph");
                    ek.updateEdgesGl();
                    graph.setText("The level graph      ");
                    view.invalidate();
                }
                else if (s.newLen==5){
                    final8.setText("Updating the level graph");
                    ek.updateEdgesGl();
                    graph.setText("The level graph      ");
                    view.invalidate();
                    path.setText("");
                }

            }});

        Gf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ek.printGf();
                graph.setText("The residual graph");
                view.invalidate();

            }});

        Gl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ek.updateEdgesGl();
                graph.setText("The level graph      ");
                view.invalidate();

            }});
        G.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ek.printG();
                view.invalidate();
                graph.setText("The graph               ");

            }});

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Step1 s=ek.nextIter();
                path.setText(s.path);
                if (s.flow==0)
                    flow.setText("");
                else
                    flow.setText(Integer.toString(s.flow));

                flow1.setText(Integer.toString(s.maxFlow));
                view.invalidate();
                if (s.length==0)
                    netLen.setText("");
                else
                netLen.setText(Integer.toString(s.length));
                if (s.newLen==0){
                    final8.setText("Finding the length of the shortest path");
                    ek.printGf();
                    graph.setText("The residual graph");
                    view.invalidate();
                    counter=0;
                }
                else if (s.newLen==1){

                    final8.setText("Building the level graph");
                    ek.updateEdgesGl();
                    graph.setText("The level graph      ");
                    view.invalidate();
                }
                else if (s.newLen==2){
                    final8.setText("The algorithm is ended. Max flow is "+Integer.toString(s.maxFlow));
                    ek.printG();
                    graph.setText("The graph               ");
                    view.invalidate();
                }
                else if (s.newLen==3){
                    final8.setText("Finding the length of the shortest path");
                    netLen.setText("-");
                    ek.printGf();
                    graph.setText("The residual graph");
                    view.invalidate();
                }
                else if (s.newLen==4){
                    final8.setText("Finding a path in the level graph");
                    ek.updateEdgesGl();
                    graph.setText("The level graph      ");
                    view.invalidate();
                }
                else if (s.newLen==5){
                    final8.setText("Updating the level graph");
                    ek.updateEdgesGl();
                    graph.setText("The level graph      ");
                    view.invalidate();
                    path.setText("");
                }

            }});*/

    }

}
