package com.example.noa.flowgraph;

import android.graphics.Point;

public class Vertex {

    Point point;
    protected int number;
    protected int father;
    protected int color;
    protected int distance;
    protected int print;
    protected int h;
    protected int e;
    protected int index;
    static char counter='A';
    static int counterIndex=0;

    Vertex(Point p,char c){
        index=counterIndex;
        counterIndex++;
        point=p;
        number=c;
        e=0;
        h=0;
    }

    Vertex(Point p){
        index=counterIndex;
        counterIndex++;
             point=p;
        number=counter;
        counter++;
        father=-1;
        color=0;
        distance=-1;
        print=0;
        e=0;
        h=0;
    }
}
