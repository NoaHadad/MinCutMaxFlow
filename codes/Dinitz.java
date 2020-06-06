package com.example.noa.flowgraph;


import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Dinitz extends Algorithm {

    ArrayList<Node1> iterations;
    LevelGraph levelGraph;
    int maxFlow;
    int len;
    int c;
    int iterStep;
    int endingFlag;
    ArrayList <Edge> edges;

    Dinitz(){
        //initialization

        len=0;

        maxFlow=0;

        c=0;

        iterStep=0;

        endingFlag=0;

        iterations=new ArrayList<Node1>();

        edges=new ArrayList<>();


        //first iteration-the initial graph

        currentIter=-1;
    }

    public  int BFS(){

        residual.BFS();
        //if we didn't find a path from 's' to 't'
        if (MyView.vertices.get(1).father==-1) return 0;

        ArrayBlockingQueue <Integer> q1=new ArrayBlockingQueue <Integer>(MyView.vertices.size());
        if (edges!=null) edges.clear();
        //finding a path

        //finding the vertices which connected to 't' in all the shortest path
        for (int i=0;i<residual.graph.length;i++){
            int l=residual.findLen(i,1);
            if (l!=0)
                if ((MyView.vertices.get(i).distance+1)==MyView.vertices.get(1).distance) {
                    edges.add(new Edge(i,1,l,""));//put the edge in the levelGraph
                    q1.add(i);//add the vertex number to queue1
                }
                }

        //
        while (!q1.isEmpty())   {
            int p=q1.poll();

            //if we reached 's'
            if (p==0) return (MyView.vertices.get(1).distance);

            //finding the vertices which connected to p in all the shortest path
            for (int i=0;i<MyView.vertices.size();i++){
                int l=residual.findLen(i,p);
                if (l!=0)
                    if ((MyView.vertices.get(i).distance+1)==MyView.vertices.get(p).distance){
                        edges.add(new Edge(i,p,l,""));
                        q1.add(i);}}

        }



        return (MyView.vertices.get(1).distance);
    }


    public Step1 prevIter() {

        int newLen=0;

        if (currentIter>0) {

            currentIter--;

            String str=iterations.get(currentIter).path;
            int min=iterations.get(currentIter).flow;
            maxFlow=iterations.get(currentIter).maxFlow;
            len=iterations.get(currentIter).length;
            newLen=iterations.get(currentIter).newLen;
            residual = iterations.get(currentIter).graph;
            levelGraph = iterations.get(currentIter).levelGraph;
            return new Step1(min,maxFlow, str,len,newLen);
        }

        return new Step1(0,0, "",len,newLen);

    }


    public Step1 nextIter() {

        int newLen=0;
        int next = -1;
        int min = 0;
        String str = "s";

        if (currentIter==(iterations.size()-1)) {

            //building the levelGraph
            if (c == 1) {
                c = 0;
                iterations.add(new Node1(residual, levelGraph, len, iterations.get(currentIter), min, maxFlow, "", 1));
                currentIter++;
                return new Step1(0, maxFlow, "", len, 1);
            }

            boolean flag = true;
            //checking if there is a path from 's' in the level graph or we need to do BFS
            for (int i = 1; i < MyView1.vertices.size(); i++) {
                if ((levelGraph.findLen(0,i) != 0) && flag) flag = false;
            }

            if (flag) {
                len = BFS();
                if (len == 0 ) {
                    //return the final residual graph
                    if (0 == endingFlag) {
                        iterations.add(new Node1(residual, levelGraph, len, iterations.get(currentIter), 0, maxFlow, "", 3));
                        currentIter++;
                        endingFlag = 1;
                        return new Step1(0, maxFlow, "", len, 3);

                    }
                    //return the final graph
                    else if (1 == endingFlag) {
                        iterations.add(new Node1(residual, levelGraph, len, iterations.get(currentIter), 0, maxFlow, "", 2));
                        currentIter++;
                        endingFlag = 2;
                        return new Step1(0, maxFlow, "", len, 2);
                    }
                    //if we press next after finishing
                    return new Step1(0, maxFlow, "", len, 2);
                }
                    //return the residual graph with the shortest length
                    c++;
                    if (currentIter==-1) iterations.add(new Node1(residual, levelGraph, len, null, min, 0, "", 0));
                    else    iterations.add(new Node1(residual, levelGraph, len, iterations.get(currentIter), min, maxFlow, "", 0));
                    currentIter++;
                    return new Step1(0, maxFlow, "", len, 0);


            }

            if (iterStep == 0) {
                //finding a path from s

                for (Vertex v : MyView.vertices)
                    v.father = -1;
                flag = true;

                //finding a vertex connected to 's'
                for (int i = 1; i < MyView1.vertices.size(); i++)

                    //if there is an edge and the vertex wasn't founded
                    if (levelGraph.findLen(0,i) != 0 && flag) {
                        str = str + "->" + Character.toString((char) MyView1.vertices.get(i).number);
                        min = levelGraph.findLen(0,i);
                        MyView.vertices.get(i).father = 0;
                        next = i;
                        flag = false;
                    }

                //finding the rest of the path

                while (next != 1) {//'t' wasn't reached
                    flag = true;
                    for (int i = 1; i < MyView1.vertices.size(); i++)
                        if (levelGraph.findLen(next,i) != 0 && flag) {
                            MyView.vertices.get(i).father = next;
                            str = str + "->" + Character.toString((char) MyView1.vertices.get(i).number);
                            min = Math.min(min, levelGraph.findLen(next,i));
                            flag = false;
                            next = i;
                        }
                }

                //updating maxFlow
                maxFlow += min;

                newLen = 4;
                iterations.add(new Node1(residual, levelGraph, len, iterations.get(currentIter), min, maxFlow, str, 4));
                currentIter++;
                iterStep++;
            } else {
                //updating the graphs

                min = iterations.get(currentIter).flow;

                int f = MyView.vertices.get(1).father;
                int v = 1;

                while (f != -1) {
                    levelGraph.updateGraph(f,v,min);
                    residual.updateGraph(f,v,min);
                    v = f;
                    f = MyView.vertices.get(v).father;
                }

                //updating the level graph

                levelGraph.BFS();

                ArrayBlockingQueue <Integer> q1=new ArrayBlockingQueue <Integer>(MyView.vertices.size());

                //finding a path
                edges.clear();
                //finding the vertices which connected to 't' in all the shortest path
                for (int i=0;i<residual.graph.length;i++){
                    int l=residual.findLen(i,1);
                    if (l!=0)
                        if ((MyView.vertices.get(i).distance+1)==MyView.vertices.get(1).distance) {
                            edges.add(new Edge(i,1,l,""));//put the edge in the levelGraph
                            q1.add(i);//add the vertex number to queue1
                        }
                }


                //
                while (!q1.isEmpty()) {
                    int p = q1.poll();

                    //if we reached 's'
                    if (p == 0) {
                    } else {
                        //finding the vertices which connected to p in all the shortest path
                        for (int i = 0; i < MyView.vertices.size(); i++) {
                            int l = residual.findLen(i, p);
                            if (l != 0)
                                if ((MyView.vertices.get(i).distance + 1) == MyView.vertices.get(p).distance) {
                                    edges.add(new Edge(i, p, l, ""));
                                    q1.add(i);
                                }
                        }

                    }
                }

                levelGraph=new LevelGraph(MyView1.size(),edges);



                str = iterations.get(currentIter).path;
                newLen=5;
                iterations.add(new Node1( residual, levelGraph, iterations.get(currentIter).length, iterations.get(currentIter), iterations.get(currentIter).flow, maxFlow, iterations.get(currentIter).path, 5));
                currentIter++;
                iterStep = 0;
            }
        }
        else{
            currentIter++;

            str=iterations.get(currentIter).path;
            min=iterations.get(currentIter).flow;
            maxFlow=iterations.get(currentIter).maxFlow;
            newLen=iterations.get(currentIter).newLen;
            len=iterations.get(currentIter).length;
            residual = iterations.get(currentIter).graph;
            levelGraph = iterations.get(currentIter).levelGraph;

        }

        return new Step1(min,maxFlow, str,len,newLen);
    }



    public void updateEdgesGl(){

        MyView1.print=2;

            }
    }






