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

public class MyView2 extends MyView1{

    public MyView2(Context context, AttributeSet attrs) {
        super(context, attrs);}

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p=new Paint();
        p.setTextSize(15);
        for (Vertex v : vertices) {
            if (v.print==0) {
                p.setColor(Color.rgb(128, 0, 255));
                if (v.number == 's' || v.number == 't') {
                    p.setColor(Color.RED);
                }
                canvas.drawText("h="+Integer.toString(v.h), v.point.x - 65, v.point.y -5, p);
                canvas.drawText("e="+Integer.toString(v.e), v.point.x - 65, v.point.y +15, p);
            }
        }


        }


}
