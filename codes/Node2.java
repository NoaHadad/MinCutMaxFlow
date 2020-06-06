package com.example.noa.flowgraph;

public class Node2  {

    int eh[][];
    ResidualGraph residual;
    Node2 next;
    Node2 prev;
    int flow;
    int maxFlow;
    int net;
    int receiving;
    int sending;
    int type;

    Node2(int m,ResidualGraph other,int[][]e, Node2 prev,int flow, int maxFlow, int net, int receiving,int sending,int type) {

        residual=new ResidualGraph(other);

        eh=new int[m][2];
        for (int i=0;i<m;i++){
            this.eh[i][0]=e[i][0];
            this.eh[i][1]=e[i][1];}

        this.prev = prev;

        next = null;

        if (prev!=null)
            prev.next = this;

        this.flow=flow;

        this.receiving=receiving;

        this.maxFlow=maxFlow;

        this.sending=sending;

        this.net=net;

        this.type=type;
    }



}
