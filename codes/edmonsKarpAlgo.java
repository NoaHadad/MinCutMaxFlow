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

public class edmonsKarpAlgo extends AppCompatActivity {

    EdmondsKarp ek;
    MyView1 view;
    Button next;
    Button prev;
    Button Gf;
    Button G;
    TextView path;
    TextView flow;
    TextView maxFlow;
    TextView final2;
    TextView graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edmons_karp_algo);

        view=(MyView2)findViewById(R.id.simpleDrawingView2);
        view.invalidate();

        next= (Button) findViewById(R.id.button5);
        prev= (Button) findViewById(R.id.button8);
        Gf = (Button) findViewById(R.id.button6);
        G = (Button) findViewById(R.id.button7);
        path = (TextView) findViewById(R.id.path);
        flow=(TextView) findViewById(R.id.flow2);
        final2=(TextView) findViewById(R.id.final1);
        maxFlow=(TextView) findViewById(R.id.flow5);
        ek=new EdmondsKarp();
        graph=(TextView) findViewById(R.id.graph5);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Step s=new Step(ek.next());
                if (s.path.equals("")){
                    path.setText("");
                    flow.setText("");
                    maxFlow.setText("");
                    final2.setText("");
                }
                else {
                    path.setText(""+s.path);
                    flow.setText(Integer.toString(s.flow));
                    maxFlow.setText(Integer.toString(s.maxFlow));
                    final2.setText("");
                }
                if (s.newLen==0) {
                    ek.printGf();
                    view.invalidate();
                    graph.setText("The residual graph");
                }
                else if (s.newLen==1) {ek.printGf();
                    view.invalidate();
                    path.setText(" no more");
                    flow.setText("");
                    graph.setText("The residual graph");

                }
                else if (s.newLen==2){ek.printG();
                    view.invalidate();
                    final2.setText("The algorithm is ended. Max flow is "+Integer.toString(s.maxFlow)+".");
                    graph.setText("The graph               ");

                }
            }});

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Step s=new Step(ek.previous());
                if (s.path.equals("")){
                    path.setText("");
                    flow.setText("");
                    maxFlow.setText("");
                    final2.setText("");
                }
                else {
                    path.setText(""+s.path);
                    flow.setText(Integer.toString(s.flow));
                    maxFlow.setText(Integer.toString(s.maxFlow));
                    final2.setText("");
                }
                  if (s.newLen==0) {
                    ek.printGf();
                    view.invalidate();
                    graph.setText("The residual graph");
                }

                  else if (s.newLen==1) {ek.printGf();
                      view.invalidate();
                      path.setText(" no more");
                      flow.setText("");
                      graph.setText("The residual graph");

                  }
                  else if (s.newLen==2){ek.printG();
                      view.invalidate();
                      final2.setText("The algorithm is ended. Max flow is"+Integer.toString(s.maxFlow)+".");
                      graph.setText("The graph               ");

                  }
            }});

        Gf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ek.printGf();
                view.invalidate();
                graph.setText("The residual graph");
            }});

        G.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ek.printG();
                view.invalidate();
                graph.setText("The graph               ");

            }});

    }

}
