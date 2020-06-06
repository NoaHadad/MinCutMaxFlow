package com.example.noa.flowgraph;

import com.example.noa.flowgraph.Edge;
import com.example.noa.flowgraph.MyView;
import com.example.noa.flowgraph.MyView1;
import com.example.noa.flowgraph.Vertex;

public abstract class Algorithm {
    protected  int currentIter;
    protected ResidualGraph residual;

    Algorithm(){

           residual=new ResidualGraph(MyView1.size(),MyView1.getEdges());
      //     MyView1.g=residual;
           currentIter=-1;

    }

    public void printGf(){
        MyView1.print=0;
    }

    public void printG() {
       MyView1.print=1;

    }


    }

