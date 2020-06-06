package com.example.noa.flowgraph;

public class Step1 extends Step{

    int length;


    Step1(int flow ,int maxFlow,String path,int length, int newLen){
        super(flow,maxFlow,path,newLen);
        this.length=length;
    }
}
