package com.example.noa.flowgraph;

import com.example.noa.flowgraph.Edge;
import com.example.noa.flowgraph.MyView;
import com.example.noa.flowgraph.MyView1;
import com.example.noa.flowgraph.Step;
import com.example.noa.flowgraph.Vertex;

import java.util.ArrayList;


public class PushRelabel extends Algorithm{


    ArrayList<Node2> iterations;
    static String maxFlow="";
    int firstRound;
    static int sending=0;
    static int receiving=0;
    static int flow=0;
    static int f=0;
    int eh[][];
    int flag1;
    static int net=0;
    boolean finalFlag;
    int lastRound;
    int flag3;
    int type;


    PushRelabel(){

        for (Vertex v: MyView.vertices){
            v.print=0;
        }

        type=0;
        lastRound=0;
        finalFlag=true;

        flag1=0;

        firstRound=0;

        eh=new int [MyView.vertices.size()][2];

        for (int i=0; i<MyView.vertices.size();i++) {
            eh[i][0] = 0;
            eh[i][1] = 0;
        }


        for (Vertex v: MyView.vertices){
            v.h=0;
            v.e=0;
        }



        MyView1.vertices.get(0).print=0;
        MyView1.vertices.get(1).print=0;


        MyView.vertices.get(0).h=MyView.vertices.size();
        eh[0][1]=MyView.vertices.size();

        iterations=new ArrayList<Node2>();

        //first iteration-the initial graph



    }

public void prev(){

        //if we do not in the first iteration

        if (currentIter>0){
            currentIter--;
            flow=iterations.get(currentIter).flow;
            receiving=iterations.get(currentIter).receiving;
            sending=iterations.get(currentIter).sending;
            net=iterations.get(currentIter).net;
            type = iterations.get(currentIter).type;

            for (Vertex v: MyView.vertices){
                if (v.number=='s'){
                    v.e=iterations.get(currentIter).eh[0][0];
                    v.h=iterations.get(currentIter).eh[0][1];
                }
                else if (v.number=='t'){
                    v.e=iterations.get(currentIter).eh[1][0];
                    v.h=iterations.get(currentIter).eh[1][1];
                }
                else{
                    v.e=iterations.get(currentIter).eh[v.number-63][0];
                    v.h=iterations.get(currentIter).eh[v.number-63][1];
                }
            }

            residual = iterations.get(currentIter).residual;

            for (int i=0;i<MyView.vertices.size();i++)
                for (int j=0;j<2;j++)
                    eh[i][j]=iterations.get(currentIter).eh[i][j];
        }
}

    public void round() {

        if ((currentIter + 1) < iterations.size()) {
            currentIter++;
            flow = iterations.get(currentIter).flow;
            receiving = iterations.get(currentIter).receiving;
            sending = iterations.get(currentIter).sending;
            net = iterations.get(currentIter).net;
            type = iterations.get(currentIter).type;

            for (Vertex v : MyView.vertices) {
                if (v.number == 's') {
                    v.e = iterations.get(currentIter).eh[0][0];
                    v.h = iterations.get(currentIter).eh[0][1];
                } else if (v.number == 't') {
                    v.e = iterations.get(currentIter).eh[1][0];
                    v.h = iterations.get(currentIter).eh[1][1];
                } else {
                    v.e = iterations.get(currentIter).eh[v.number - 63][0];
                    v.h = iterations.get(currentIter).eh[v.number - 63][1];
                }
            }

            for (int i = 0; i < MyView.vertices.size(); i++)
                for (int j = 0; j < 2; j++)
                    eh[i][j] = iterations.get(currentIter).eh[i][j];


                    residual = iterations.get(currentIter).residual;

            return;
        }


        if (finalFlag) {
            //if this is a new iteration

            //if it is a "push" round
            if (flag1 != 2) {
                if (firstRound == 0)
                    firstRound = pushFirstRound();
                else
                    push();
            }

            //if it is a "relabel" round
            else {
                relabel();
                flag1 -= 2;
            }

            if ((flag3==1) && finalFlag) {
                iterations.add(new Node2(MyView.vertices.size(),residual, eh, iterations.get(currentIter), flow, 0, 0, receiving, sending,flag1));
                type=flag1;
                net = 0;
                currentIter++;
            }

            //checking if there is no flow to push

            if (((-MyView.vertices.get(0).e) == MyView.vertices.get(1).e) && (flag3==1))
                finalFlag = false;


        }
        else {
            if (lastRound==0) {
                lastRound++;
                maxFlow = Integer.toString(-MyView.vertices.get(0).e);
                iterations.add(new Node2(MyView.vertices.size(),residual, eh, iterations.get(currentIter), flow, 0, 0, receiving, sending,4));
                type=4;
                net = 0;
                currentIter++;
            }
            else if  (lastRound==1) {
                lastRound++;
                maxFlow = Integer.toString(-MyView.vertices.get(0).e);
                iterations.add(new Node2(MyView.vertices.size(),residual, eh, iterations.get(currentIter), flow, 0, 1, receiving, sending,3));
                type=3;
                net = 1;
                currentIter++;
            }
        }


return;
    }




    public void relabel(){


        for (Vertex v: MyView.vertices){
            if ((v.e>0) && (v.number!='t') && (v.number!='s')) {
                int min = 1000000;
                for (int j = 0; j < MyView.vertices.size(); j++)
                    if (residual.findLen(v.number-63,j) > 0)
                        if (MyView.vertices.get(j).h < min)
                            min = MyView.vertices.get(j).h;
                if (min==1000000){}
                else{
                MyView.vertices.get(v.number-63).h = min + 1;
                eh[v.number-63][1]=min + 1;
                   }
            }
        }

    }

    public void push(){

        for (Vertex v: MyView.vertices){

                if ((v.e>0) && (v.number!='t') && (v.number!='s')) {
                    int i = v.number - 63;
                    for (int j = 0; j < MyView.vertices.size(); j++)
                        if ((residual.findLen(i,j) > 0) && (v.h == (MyView.vertices.get(j).h + 1))) {
                            if (flag1 == 0) {
                                sending = i + 63;
                                if (j == 1)
                                    receiving = 't';
                                else if (j == 0)
                                    receiving = 's';
                                else
                                    receiving = j + 63;

                                int min = Math.min(residual.findLen(i,j), v.e);
                                flow = min;
                                flag1++;
                                return;
                            } else {
                                int min = flow;
                                j= receiving;
                                if (j == 't')
                                    j = 1;
                                else if (j == 's')
                                    j = 0;
                                else
                                    j-=63;
                                i=sending;
                                i-=63;
                                residual.updateGraph(i,j,min);
                                MyView.vertices.get(j).e += min;
                                eh[j][0] += min;
                                MyView.vertices.get(i).e -= min;
                                eh[i][0] -= min;
                                flag1++;
                                return;
                            }
                        }
                }
    }}

    public  int pushFirstRound(){

        //first push
        int flag5=0;

        for (int i=1;i<MyView.vertices.size();i++)
            if (residual.findLen(0,i) > 0) {
                flag5++;
                if (flag5==2) return 0;
                if (flag1 == 0) {
                    sending = 's';
                    if (i == 1)
                        receiving = 't';
                    else
                        receiving = i + 63;
                    flow = residual.findLen(0,i);
                    if (currentIter==-1){
                        iterations.add(new Node2(MyView.vertices.size(),residual, eh, null, flow, 0, 0, receiving, sending,1));
                        type=1;
                        net=0;
                        currentIter++;
                        flag3=0;
                    }
                    flag1++;
                }
                else{
                    flag3=1;
                    flow = residual.findLen(0,i);
                    flag1++;
                    MyView.vertices.get(i).e += residual.findLen(0,i);
                    eh[i][0]+= residual.findLen(0,i);
                    MyView.vertices.get(0).e -= residual.findLen(0,i);
                    eh[0][0]-=residual.findLen(0,i);
                    residual.updateGraph(0,i,residual.findLen(0,i));

                }
            }

        //we pushed to the last vertex connected to 's'
        if ((flag5==1) && (flag1==2)) return 1;

        return 0;



    }
}
