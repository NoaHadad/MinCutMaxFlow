package com.example.noa.flowgraph;

import android.graphics.Point;

public class Edge {

    protected int source;
    protected int destination;
    protected int length;
    protected int using;
    protected int capacity;
    protected String str;


    Edge(int s,int d, int l,String str){
        using=0;
        capacity=l;
        this.str=str;
        source=s;
        destination=d;
        length=l;
        }

    Edge(Edge other){
        using=other.using;
        capacity=other.capacity;
        this.str=other.str;
        source=other.source;
        destination=other.destination;
        length=other.length;
    }

    public void setSource(int s){
        source=s;
    }

    public void setDestination(int d){
        destination=d;
    }

    public void setLength(int l){
        length=l;
    }


}
