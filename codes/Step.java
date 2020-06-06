package com.example.noa.flowgraph;

public class Step {
    int flow;
    int maxFlow;
    String path;
    int newLen;

    Step(int flow ,int maxFlow,String path,int newLen){
        this.flow=flow;
        this.maxFlow=maxFlow;
        this.path=path;
        this.newLen=newLen;
    }

    Step(Step other){
        flow=other.flow;
        path=other.path;
        maxFlow=other.maxFlow;
        newLen=other.newLen;
    }
}
