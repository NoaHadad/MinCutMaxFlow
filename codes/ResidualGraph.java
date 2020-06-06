package com.example.noa.flowgraph;

import org.w3c.dom.Node;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;

public class ResidualGraph {

    NodeEdge graph[];


    ResidualGraph(int size, List<Edge> edges) {
        int sources[] = new int[MyView.getSources()];
        int targets[] = new int[MyView.getTargets()];
        graph = new NodeEdge[size];
        for (Edge e : edges) {
            int s = e.source;
            int t = e.destination;

            if (MyView.multiple == 1) {
                if ((s >= 2) && (s < (MyView.getSources() + 2))) sources[s - 2] += e.length;
                if ((t >= (MyView.getSources() + 2)) && (t < (MyView.getSources() + MyView.getTargets() + 2)))
                    targets[t - 2 - MyView.getSources()] += e.length;
            }
            addEdge(e);
        }
        for (int i = 0; i < sources.length; i++)
            if (sources[i] != 0) addEdge(new Edge(0, i + 2, sources[i],"0/"+sources[i]));
        for (int i = 0; i < sources.length; i++)
            if (targets[i] != 0) addEdge(new Edge(i + 2 + sources.length, 1, targets[i],"0/"+targets[i]));
    }

    ResidualGraph(ResidualGraph other){
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
        n.e.using+=len;
        n.e.str=n.e.using+"/"+n.e.capacity;
          /*  if (n.e.length==0){
                n.prev.next=n.next;
                n.next=null;
            }*/
        n = NodeEdge.search(graph[d], s);
        if (n == null) addEdge(new Edge(d, s, len,""));
        else {
            n.e.length += len;
            n.e.using-=len;
        }
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
