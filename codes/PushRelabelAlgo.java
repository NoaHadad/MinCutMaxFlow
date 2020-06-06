package com.example.noa.flowgraph;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PushRelabelAlgo extends AppCompatActivity {

    PushRelabel p;
    MyView2 view;
    Button nextStep;
    Button prevStep;
    Button Gf;
    Button G;
    TextView type;
    TextView floww;
    TextView flow1;
    TextView flow2;
    TextView send;
    TextView recv;
    TextView graph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_relabel_algo);

        view=(MyView2)findViewById(R.id.simpleDrawingView5);
        view.invalidate();

        nextStep = (Button) findViewById(R.id.button99);
        prevStep = (Button) findViewById(R.id.button100);
        Gf = (Button) findViewById(R.id.button991);
        G = (Button) findViewById(R.id.button992);
        type=(TextView) findViewById(R.id.flow225);
        floww=(TextView) findViewById(R.id.flow224);
        flow1=(TextView) findViewById(R.id.trans);
        send=(TextView) findViewById(R.id.sendV);
        recv=(TextView) findViewById(R.id.recivV);
        graph=(TextView) findViewById(R.id.graph7);
        p=new PushRelabel();


        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.round();


                if (p.type == 1 || p.type == 2) {
                    type.setText("push");
                    flow1.setText(Integer.toString(p.flow));
                    send.setText(Character.toString((char) (p.sending)));
                    recv.setText(Character.toString((char) (p.receiving)));
                } else if (p.type == 0) {
                    type.setText("relabel");
                    flow1.setText("");
                    send.setText("");
                    recv.setText("");
                } else if (p.type == 4) {
                    type.setText("There are no more active vertices");
                    flow1.setText("");
                    send.setText("");
                    recv.setText("");
                } else {
                    type.setText("The algorithm is ended. The max flow is " + p.maxFlow);
                    flow1.setText("");
                    send.setText("");
                    recv.setText("");
                }
                if (p.net == 0) {
                    graph.setText("The residual graph");
                p.printGf();
            }
                    else{  p.printG();
                    graph.setText("The graph               ");}
                    view.invalidate();
            }});

        prevStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.prev();


                flow1.setText(Integer.toString(p.flow));
                send.setText(Character.toString((char) (p.sending)));
                recv.setText(Character.toString((char) (p.receiving)));

                if (p.type == 1 || p.type == 2) {
                    type.setText("push");
                    flow1.setText(Integer.toString(p.flow));
                    send.setText(Character.toString((char) (p.sending)));
                    recv.setText(Character.toString((char) (p.receiving)));
                } else if (p.type == 0) {
                    type.setText("relabel");
                    flow1.setText("");
                    send.setText("");
                    recv.setText("");
                } else if (p.type == 4) {
                    type.setText("There are no more active vertices");
                    flow1.setText("");
                    send.setText("");
                    recv.setText("");
                } else {
                    type.setText("The algorithm is ended. The max flow is " + p.maxFlow);
                    flow1.setText("");
                    send.setText("");
                    recv.setText("");
                }
                if (p.net == 0) {
                    p.printGf();
                    graph.setText("The residual graph");
                } else{
                    p.printG();
                graph.setText("The graph               ");
            }
            view.invalidate();
            }});

        Gf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.printGf();
                view.invalidate();
                graph.setText("The residual graph");

            }});

        G.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.printG();
                view.invalidate();
                graph.setText("The graph               ");

            }});

    }

}
