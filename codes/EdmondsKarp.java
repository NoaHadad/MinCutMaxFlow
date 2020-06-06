package com.example.noa.flowgraph;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class EdmondsKarp extends Algorithm{
    ArrayList<Node> iterations;
    int maxFlow;
    int counter;
    int counter1;
    boolean endFlag;

    EdmondsKarp(){

        //initialization

        maxFlow=0;

        counter=0;

        counter1=0;

        endFlag=true;

               for (Vertex v: MyView.vertices){
            v.print=0;
        }


        iterations=new ArrayList<Node>();

        }

    public  int BFS(){

        //initialization of the father to -1 and the color to 0 (WHITE)
        for (Vertex v: MyView1.vertices){
            v.father=-1;
            v.color=0;
        }

        //creation of queue
        ArrayBlockingQueue <Vertex> q=new ArrayBlockingQueue <Vertex>(MyView1.vertices.size());

        //adding the source to th queue
        q.add(MyView1.vertices.get(0));

        //discovering the source vertex- changing the color to 1 (GRAY)
        MyView1.vertices.get(0).color=1;

        //while there are vertices in the queue(the vertices that connected to 's') and until we don't discover 't'
        while (!q.isEmpty() && q.peek().number!='t'){
            Vertex current=q.peek();
            int p ; //the place of the current vertex in the array of vertices
            if (current.number=='s') p=0;
            else p=current.number-63;
            //finding neighbors
            for (int i=0;i<MyView1.vertices.size();i++){

                //if there are edge from the current vertex to the 'i' vertex and the vertex was not discovered before
                int length=residual.findLen(p,i);
                if ((length!=0) && (MyView1.vertices.get(i).color==0)){
                    MyView1.vertices.get(i).father=p; //the vertex father is p
                    MyView1.vertices.get(i).color=1; //the vertex was discover- change color to GRAY(1)
                    q.add( MyView1.vertices.get(i)); //adding the vertex to the queue
                }
            }
            q.poll().color=2; //we finished to find the neighbors of the current vertex- change color to 2 (BLACK)
        }

        //if we do not find father vertex to 't' - the isn't a path from 's' to 't'
        if (MyView1.vertices.get(1).father==-1) return 0;

        return 1;
    }



            public Step next() {
                Node next;
                int min = 0;
                String str = "";
                if ((currentIter+1)==iterations.size()) {
                    if (counter == 0) {
                        if (BFS() == 1) {

                            min = residual.findLen(MyView1.vertices.get(1).father,1);//initial min to the edge connected to 't'

                            //getting the vertex connected to 't'
                            Vertex v = MyView1.vertices.get(MyView1.vertices.get(1).father);

                            //finding the edge with the minimum capacity

                            //while we don't reach 's'
                            while (v.father != -1) {
                                //update to the minimum edge in the path
                                min = Math.min(min, residual.findLen(v.father,v.number - 63));

                                //get the father
                                v = MyView1.vertices.get(v.father);
                            }


                            //
                            v = MyView1.vertices.get(MyView1.vertices.get(1).father);
                            while (v.father != -1) {

                                //saving the path
                                str = Character.toString((char) (v.number)) + "->" + str;

                                //getting the father
                                v = MyView1.vertices.get(v.father);
                            }
                            str = "s->" + str + "t";
                            maxFlow += min;


                            //add the new iteration to the list
                            if (currentIter==-1) iterations.add(new Node(residual, null, min, maxFlow, str,0));
                            else iterations.add(new Node(residual, iterations.get(currentIter), min, maxFlow, str,0));

                            currentIter++;

                            counter++;
                        }

                        else if (endFlag) {

                            if (counter1 == 0) {
                                iterations.add(new Node(residual, iterations.get(currentIter), 0, maxFlow, "", 1));
                                currentIter++;
                                counter1++;
                                return new Step(0, maxFlow, "", 1);
                            } else {
                                iterations.add(new Node(residual, iterations.get(currentIter), 0, maxFlow, "", 2));
                                currentIter++;
                                endFlag = false;
                                counter = 2;
                                return new Step(0, maxFlow, "", 2);
                            }
                        }


                        }

                    else if (counter==1){
                        min=iterations.get(currentIter).flow;

                        //update the backward and forward edges connected to 't'
                        residual.updateGraph(MyView1.vertices.get(1).father,1, min);

                        Vertex v = MyView1.vertices.get(MyView1.vertices.get(1).father);
                        while (v.father != -1) {
                            //update the backward and forward edges
                            residual.updateGraph(v.father,v.number - 63,min);

                            v = MyView1.vertices.get(v.father);
                        }

                        iterations.add(new Node(residual, iterations.get(currentIter), min, maxFlow, iterations.get(currentIter).path,0));

                        str=iterations.get(currentIter).path;

                        currentIter++;

                        counter=0;

                    }
                }
                else{
                    next=iterations.get(currentIter).next;

                            residual=next.graph;

                    currentIter++;

                    min=next.flow;
                    str=next.path;
                    maxFlow=next.maxFlow;


                }
                return new Step(min,maxFlow, str,iterations.get(currentIter).newLen);

            }

            public Step previous(){



                if (currentIter>0){
                    Node prev=iterations.get(currentIter).prev;

                            residual=prev.graph;
                    currentIter--;
                    return new Step(iterations.get(currentIter).flow,iterations.get(currentIter).maxFlow, iterations.get(currentIter).path,iterations.get(currentIter).newLen);

                }
                else if (currentIter==0) {
                    return new Step(iterations.get(currentIter).flow, iterations.get(currentIter).maxFlow, iterations.get(currentIter).path, iterations.get(currentIter).newLen);
                }
                else return new Step(0, 0, "", 1);

            }




    }


