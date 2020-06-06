package com.example.noa.flowgraph;

public class Node1 extends Node{

    LevelGraph levelGraph;
    int length;

    Node1(ResidualGraph graph,LevelGraph levelGraph,int length, Node1 prev,int flow, int maxFlow, String path,int newLen) {
        super(graph,prev,flow,maxFlow,path,newLen);

        this.levelGraph = new LevelGraph(levelGraph);

        this.length=length;


    }
}
