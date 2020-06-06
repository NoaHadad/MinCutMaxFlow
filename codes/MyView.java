package com.example.noa.flowgraph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MyView extends View {

    private final int paintColor = Color.BLACK;
    protected Paint drawPaint;
    Paint paint;
    Paint paint1;
    Canvas canvas;
    protected static int multiple=0;
    protected static int sources=0;
    protected static int targets=0;
    protected static List<Vertex> vertices= new ArrayList<Vertex>();;
    protected static List<Edge> edges=new ArrayList<Edge>();
    protected static List<Edge> edges1=new ArrayList<Edge>();

    public static int size(){
        if (vertices==null) return 0; return vertices.size();
    }

    public static List<Edge> getEdges() {
        return edges;
    }

    public static int getSources(){
        return sources;
    }

    public static int getTargets(){
        return targets;
    }

    public static void edgesClear(){

        edges.clear();
    }

    public static void addEdges(Edge e){

        edges.add(e);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setupPaint();
        Vertex.counter='A';
        vertices.add(new Vertex(new Point(70,285),'s'));
        vertices.add(new Vertex(new Point(1130,285),'t'));
        int y1=75;
        int y2=75;
        if (MyView.multiple==1){
            int p=550/MyView.sources;
            switch(MyView.sources) {
                case (1):
                    y1 += 210;
                    break;
                case (2):
                    y1 += 105;
                    break;
                case (3):
                    y1 += 30;
                    break;
                case (4):
                    y1 += 10;
                    break;
                case (5):
                    y2 += 7;
                    break;
            }
            switch(MyView.targets) {
                case (1):
                    y2 += 210;
                    break;
                case (2):
                    y2 += 105;
                    break;
                case (3):
                    y2 += 30;
                    break;
                case (4):
                    y2 += 10;
                    break;
                case (5):
                    y2 += 7;
                    break;
            }
            vertices.get(0).print=1;
            vertices.get(1).print=1;
            for (int i=0;i<MyView.sources;i++) {
                vertices.add(new Vertex(new Point(260, y1)));
                y1 += p;
            }
            p=500/MyView.targets;
            for (int i=0;i<MyView.targets;i++) {
                vertices.add(new Vertex(new Point(940, y2)));
                y2 += p;
            }}
        else{
            sources=0; targets=0;
        }


    }

    // Setup paint with color and stroke styles
    private void setupPaint() {
        drawPaint = new Paint();
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        paint1 = new Paint();
        paint1.setColor(Color.WHITE);
        paint1.setColor(Color.BLACK);
        paint1.setTextSize(18);
    }

    @Override
    protected void onDraw(Canvas canvas) {


        if (MyView.multiple==1){
            vertices.get(0).print=1;
            vertices.get(1).print=1;
        }
        for (Vertex v : vertices) {
            if (v.print==0) {
                drawPaint.setColor(Color.rgb(102, 0, 102));
                paint.setColor(Color.rgb(102, 0, 102));
                if (v.number == 's' || v.number == 't') {
                    paint.setColor(Color.rgb(51, 51, 204));
                    drawPaint.setColor(Color.rgb(51, 51, 204));
                }
                canvas.drawCircle(v.point.x, v.point.y, 30, drawPaint);
                canvas.drawText(Character.toString((char) v.number), v.point.x - 10, v.point.y + 10, paint);
            }
        }

        for (Edge e : edges) {

            int x=vertices.get(e.source).point.x;
            int y=vertices.get(e.source).point.y;
            int x1=vertices.get(e.destination).point.x;
            int y1=vertices.get(e.destination).point.y;

            int x2, y2;
            if (x>x1) x2=x1; else x2=x;
            if (y>y1) y2=y1; else y2=y;

            paint1.setColor(Color.rgb(0, 102, 102));
            paint.setColor(Color.rgb(0, 102, 102));

            if (((x<=x1) && ((x+150)>=x1)) || ((x1<=x) && ((x1+150)>=x))){
                if (y<y1) {
                    x = (int) (x + 30 * Math.cos(Math.toRadians(285)));
                    x1 = (int) (x1 + 30 * Math.cos(Math.toRadians(285)));
                    y = (int) (y - 30 * Math.sin(Math.toRadians(285)));
                    y1 = (int) (y1 + 30 * Math.sin(Math.toRadians(285)));
                    canvas.drawLine(x, y, x1, y1, paint);
                    if (x<x1) {
                        canvas.drawText(Integer.toString(e.length), x + 10 + Math.abs((x - x1) / 2), y + Math.abs((y - y1) / 2), paint1);
                        canvas.drawLine(x1-20, y1-14, x1, y1, paint);
                        canvas.drawLine(x1+10, y1-14, x1, y1, paint);
                    }
                    else {
                        canvas.drawText(Integer.toString(e.length), x1 + 10 + Math.abs((x - x1) / 2), y + Math.abs((y - y1) / 2), paint1);
                        canvas.drawLine(x1 - 10, y1 - 14, x1, y1, paint);
                        canvas.drawLine(x1 + 20, y1 - 14, x1, y1, paint);
                    }
                }
                else {
                    x = (int) (x + 30 * Math.cos(Math.toRadians(255)));
                    x1 = (int) (x1 + 30 * Math.cos(Math.toRadians(255)));
                    y = (int) (y + 30 * Math.sin(Math.toRadians(255)));
                    y1 = (int) (y1 - 30 * Math.sin(Math.toRadians(255)));
                    canvas.drawLine(x, y, x1, y1, paint);
                    if (x<x1) {

                        canvas.drawText(Integer.toString(e.length), x - 10 + Math.abs((x - x1) / 2), y1 + Math.abs((y - y1) / 2), paint1);
                        canvas.drawLine(x1 - 15, y1 + 14, x1, y1, paint);
                        canvas.drawLine(x1 + 15, y1 + 14, x1, y1, paint);
                    }
                    else {
                        canvas.drawText(Integer.toString(e.length), x1 - 15 + Math.abs((x - x1) / 2), y1 + Math.abs((y - y1) / 2), paint1);
                        canvas.drawLine(x1 - 15, y1 + 14, x1, y1, paint);
                        canvas.drawLine(x1 + 15, y1 + 14, x1, y1, paint);
                    }
                }
            }
            else {

                if ((x <= x1) && (y >= y1)) {
                    x = (int) (x + 30 * Math.cos(Math.toRadians(315)));
                    x1 = (int) (x1 - 30 * Math.cos(Math.toRadians(315)));
                    y = (int) (y + 30 * Math.sin(Math.toRadians(315)));
                    y1 = (int) (y1 + 30 * Math.sin(Math.toRadians(315)));
                    canvas.drawLine(x, y, x1, y1, paint);
                    canvas.drawText(Integer.toString(e.length), x + Math.abs((x - x1) / 2), y1 + Math.abs((y - y1) / 2 - 10), paint1);
                    canvas.drawLine(x1 - 14, y1 + 15, x1, y1, paint);
                    canvas.drawLine(x1 -14, y1 - 15, x1, y1, paint);
                }

                if ((x > x1) && (y < y1)) {

                    x = (int) (x - 30 * Math.cos(Math.toRadians(15)));
                    x1 = (int) (x1 + 30 * Math.cos(Math.toRadians(15)));
                    y = (int) (y + 30 * Math.sin(Math.toRadians(15)));
                    y1 = (int) (y1 + 30 * Math.sin(Math.toRadians(15)));
                    canvas.drawLine(x, y, x1, y1, paint);
                    canvas.drawText(Integer.toString(e.length), x - Math.abs((x - x1) / 2), y1 - Math.abs((y - y1) / 2 + 20), paint1);
                    canvas.drawLine(x1 + 14, y1 - 25, x1, y1, paint);
                    canvas.drawLine(x1 +14, y1 + 5, x1, y1, paint);
                }
                if ((x < x1) && (y < y1)) {
                    x = (int) (x + 30 * Math.cos(Math.toRadians(315)));
                    x1 = (int) (x1 - 30 * Math.cos(Math.toRadians(315)));
                    y = (int) (y + 30 * Math.sin(Math.toRadians(315)));
                    y1 = (int) (y1 + 30 * Math.sin(Math.toRadians(315)));
                    canvas.drawLine(x, y, x1, y1, paint);
                    canvas.drawText(Integer.toString(e.length), x + Math.abs((x - x1) / 2), y - 10 + Math.abs((y - y1) / 2), paint1);
                    canvas.drawLine(x1 - 10, y1 - 25, x1, y1, paint);
                    canvas.drawLine(x1 -20, y1 + 5, x1, y1, paint);
                }

                if ((x > x1) && (y > y1)) {
                    x = (int) (x - 30 * Math.cos(Math.toRadians(15)));
                    x1 = (int) (x1 + 30 * Math.cos(Math.toRadians(15)));
                    y = (int) (y + 30 * Math.sin(Math.toRadians(15)));
                    y1 = (int) (y1 + 30 * Math.sin(Math.toRadians(15)));
                    canvas.drawLine(x, y, x1, y1, paint);
                    canvas.drawText(Integer.toString(e.length), x - Math.abs((x - x1) / 2), y + 20 - Math.abs((y - y1) / 2), paint1);
                    canvas.drawLine(x1 + 10, y1 + 25, x1, y1, paint);
                    canvas.drawLine(x1 +20, y1 - 5, x1, y1, paint);
                }
            }

        }}



    public void addEdge(int s,int d, int l){
        if (s==52) s=0;
        if (s==53) s=1;
        if (d==53) d=1;
        if (d==52) d=0;
        edges1.add(new Edge(s,d,l,0+"/"+l));
        edges=edges1;

    }

    public void deleteEdge(){
        if (edges1.size()>0)
            edges1.remove(edges1.get(edges1.size()-1));
        edges=edges1;
    }

    public void deleteVertex(){
        ArrayList<Edge> a=new ArrayList<Edge>();
        if (vertices.size()>(sources+targets+2)){
            int v=vertices.get(vertices.size()-1).number;
            for (Edge e: edges1){
                if ((vertices.get(e.source).number==v) || (vertices.get(e.destination).number==v))
                    a.add(e);
            }
            for (Edge e: a){
                edges1.remove(e);
            }
            vertices.remove(vertices.get(vertices.size()-1));
            Vertex.counter-=1;

        }
        edges=edges1;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        boolean flag=true;
        for (Vertex v: vertices)
            if ((v.point.x>=touchX-50 && v.point.x<=touchX+50) && (v.point.y>=touchY-50 && v.point.y<=touchY+50))
                flag=false;
        if (flag)
            vertices.add(new Vertex(new Point(Math.round(touchX), Math.round(touchY))));
        // indicate view should be redrawn
        postInvalidate();
        return true;
    }
}
