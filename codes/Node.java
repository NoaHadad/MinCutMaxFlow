package com.example.noa.flowgraph;

public class Node {

    ResidualGraph graph;
    Node next;
    Node prev;
    int flow;
    String path;
    int maxFlow;
    int newLen;

    Node(ResidualGraph g, Node prev,int flow, int maxFlow, String path,int newLen) {

        graph = new ResidualGraph(g);

        this.prev = prev;

        next = null;

        if (prev!=null)
            prev.next = this;

        this.flow=flow;

        this.path=path;

        this.maxFlow=maxFlow;

        this.newLen=newLen;
    }


}
