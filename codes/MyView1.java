package com.example.noa.flowgraph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MyView1 extends MyView {
    static ResidualGraph g;
    static int print=0;

    MyView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        g=new ResidualGraph(size(),edges);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {


        if (MyView.multiple == 1) {
            vertices.get(0).print = 1;
            vertices.get(1).print = 1;
        }
        for (Vertex v : vertices) {
            if (v.print == 0) {
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

        for (NodeEdge node : g.graph) {
            while (node != null) {
                Edge e = node.e;
                if (e.length != 0) {
                    int x = vertices.get(e.source).point.x;
                    int y = vertices.get(e.source).point.y;
                    int x1 = vertices.get(e.destination).point.x;
                    int y1 = vertices.get(e.destination).point.y;

                    int x2, y2;
                    if (x > x1) x2 = x1;
                    else x2 = x;
                    if (y > y1) y2 = y1;
                    else y2 = y;

                    paint1.setColor(Color.rgb(0, 102, 102));
                    paint.setColor(Color.rgb(0, 102, 102));

                    if (((x <= x1) && ((x + 150) >= x1)) || ((x1 <= x) && ((x1 + 150) >= x))) {
                        if (y < y1) {
                            x = (int) (x + 30 * Math.cos(Math.toRadians(285)));
                            x1 = (int) (x1 + 30 * Math.cos(Math.toRadians(285)));
                            y = (int) (y - 30 * Math.sin(Math.toRadians(285)));
                            y1 = (int) (y1 + 30 * Math.sin(Math.toRadians(285)));
                            canvas.drawLine(x, y, x1, y1, paint);
                            if (x < x1) {
                                canvas.drawText(Integer.toString(e.length), x + 10 + Math.abs((x - x1) / 2), y + Math.abs((y - y1) / 2), paint1);
                                canvas.drawLine(x1 - 20, y1 - 14, x1, y1, paint);
                                canvas.drawLine(x1 + 10, y1 - 14, x1, y1, paint);
                            } else {
                                canvas.drawText(Integer.toString(e.length), x1 + 10 + Math.abs((x - x1) / 2), y + Math.abs((y - y1) / 2), paint1);
                                canvas.drawLine(x1 - 10, y1 - 14, x1, y1, paint);
                                canvas.drawLine(x1 + 20, y1 - 14, x1, y1, paint);
                            }
                        } else {
                            x = (int) (x + 30 * Math.cos(Math.toRadians(255)));
                            x1 = (int) (x1 + 30 * Math.cos(Math.toRadians(255)));
                            y = (int) (y + 30 * Math.sin(Math.toRadians(255)));
                            y1 = (int) (y1 - 30 * Math.sin(Math.toRadians(255)));
                            canvas.drawLine(x, y, x1, y1, paint);
                            if (x < x1) {

                                canvas.drawText(Integer.toString(e.length), x - 10 + Math.abs((x - x1) / 2), y1 + Math.abs((y - y1) / 2), paint1);
                                canvas.drawLine(x1 - 15, y1 + 14, x1, y1, paint);
                                canvas.drawLine(x1 + 15, y1 + 14, x1, y1, paint);
                            } else {
                                canvas.drawText(Integer.toString(e.length), x1 - 15 + Math.abs((x - x1) / 2), y1 + Math.abs((y - y1) / 2), paint1);
                                canvas.drawLine(x1 - 15, y1 + 14, x1, y1, paint);
                                canvas.drawLine(x1 + 15, y1 + 14, x1, y1, paint);
                            }
                        }
                    } else {

                        if ((x <= x1) && (y >= y1)) {
                            x = (int) (x + 30 * Math.cos(Math.toRadians(315)));
                            x1 = (int) (x1 - 30 * Math.cos(Math.toRadians(315)));
                            y = (int) (y + 30 * Math.sin(Math.toRadians(315)));
                            y1 = (int) (y1 + 30 * Math.sin(Math.toRadians(315)));
                            canvas.drawLine(x, y, x1, y1, paint);
                            canvas.drawText(Integer.toString(e.length), x + Math.abs((x - x1) / 2), y1 + Math.abs((y - y1) / 2 - 10), paint1);
                            canvas.drawLine(x1 - 14, y1 + 15, x1, y1, paint);
                            canvas.drawLine(x1 - 14, y1 - 15, x1, y1, paint);
                        }

                        if ((x > x1) && (y < y1)) {

                            x = (int) (x - 30 * Math.cos(Math.toRadians(15)));
                            x1 = (int) (x1 + 30 * Math.cos(Math.toRadians(15)));
                            y = (int) (y + 30 * Math.sin(Math.toRadians(15)));
                            y1 = (int) (y1 + 30 * Math.sin(Math.toRadians(15)));
                            canvas.drawLine(x, y, x1, y1, paint);
                            canvas.drawText(Integer.toString(e.length), x - Math.abs((x - x1) / 2), y1 - Math.abs((y - y1) / 2 + 20), paint1);
                            canvas.drawLine(x1 + 14, y1 - 25, x1, y1, paint);
                            canvas.drawLine(x1 + 14, y1 + 5, x1, y1, paint);
                        }
                        if ((x < x1) && (y < y1)) {
                            x = (int) (x + 30 * Math.cos(Math.toRadians(315)));
                            x1 = (int) (x1 - 30 * Math.cos(Math.toRadians(315)));
                            y = (int) (y + 30 * Math.sin(Math.toRadians(315)));
                            y1 = (int) (y1 + 30 * Math.sin(Math.toRadians(315)));
                            canvas.drawLine(x, y, x1, y1, paint);
                            canvas.drawText(Integer.toString(e.length), x + Math.abs((x - x1) / 2), y - 10 + Math.abs((y - y1) / 2), paint1);
                            canvas.drawLine(x1 - 10, y1 - 25, x1, y1, paint);
                            canvas.drawLine(x1 - 20, y1 + 5, x1, y1, paint);
                        }

                        if ((x > x1) && (y > y1)) {
                            x = (int) (x - 30 * Math.cos(Math.toRadians(15)));
                            x1 = (int) (x1 + 30 * Math.cos(Math.toRadians(15)));
                            y = (int) (y + 30 * Math.sin(Math.toRadians(15)));
                            y1 = (int) (y1 + 30 * Math.sin(Math.toRadians(15)));
                            canvas.drawLine(x, y, x1, y1, paint);
                            canvas.drawText(Integer.toString(e.length), x - Math.abs((x - x1) / 2), y + 20 - Math.abs((y - y1) / 2), paint1);
                            canvas.drawLine(x1 + 10, y1 + 25, x1, y1, paint);
                            canvas.drawLine(x1 + 20, y1 - 5, x1, y1, paint);
                        }
                    }

                }
            }

        }
    }
}