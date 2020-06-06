package com.example.noa.flowgraph;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class LevelGraph {

    NodeEdge graph[];


    LevelGraph(int size, List<Edge> edges) {

        graph = new NodeEdge[size];
        for (Edge e : edges) {
            addEdge(e);
        }

    }

    LevelGraph(LevelGraph other){
        int counter=0;
        for (NodeEdge node: other.graph){
            Edge e=new Edge(node.e);
            graph[counter]=new NodeEdge(e,null);
            NodeEdge head=graph[counter];
            NodeEdge next=node.next;
            while( next!=null){
                new NodeEdge(new Edge(next.e),head);
            }
            counter++;

        }
    }

    public int findLen(int s,int d){
        return NodeEdge.search(graph[s],d).e.length;
    }

    void addEdge(Edge e) {
        if (graph[e.source] == null)
            graph[e.source] = NodeEdge.addEdge(null, e);
        else NodeEdge.addEdge(graph[e.source], e);
    }

    public void updateGraph(int s, int d, int len) {
        NodeEdge n = NodeEdge.search(graph[s], d);
        n.e.length -= len;
        if (n.e.length==0) n.delete();

    }

    public void BFS(){
        //initializatin of the vertices- father=-1, color=0 (WHITE), distance from 's'=-1
        for (Vertex v: MyView.vertices){
            v.father=-1;
            v.color=0;
            v.distance=-1;
        }

        ArrayBlockingQueue<Vertex> q=new ArrayBlockingQueue <Vertex>(MyView.vertices.size());

        //adding 's' to the queue
        q.add(MyView.vertices.get(0));

        //color of s=1 (GRAY- was discovered)
        MyView.vertices.get(0).color=1;
        MyView.vertices.get(0).distance=0;

        //while the queue is not empty and we don't find 't'
        while (!q.isEmpty() && q.peek().number!='t'){

            Vertex current=q.peek();
            int p;
            if (current.number=='s') p=0;
            else p=current.number-63;

            //finding neighbors
            for (int i=0;i<MyView.vertices.size();i++){

                //if there is a edge and we haven't discovered the i vertex yet
                if ((findLen(p,i)!=0) && (MyView.vertices.get(i).color==0)){

                    MyView.vertices.get(i).distance= MyView.vertices.get(p).distance+1;//update the distance to the father distance+1
                    MyView.vertices.get(i).father=p;//update father
                    MyView.vertices.get(i).color=1;//update color to 1(GRAY)
                    q.add( MyView.vertices.get(i));//add the vertex to the queue
                }
            }
            q.poll().color=2;//update color to black (we find the neighbors)
        }



    }



}

