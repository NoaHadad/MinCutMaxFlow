package com.example.noa.flowgraph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;



    public class DrawView extends View {
        Paint paint = new Paint();
        int x;
        int y;
        int x1;
        int y1;

        public DrawView(Context context, int x, int y, int x1, int x2) {
            super(context);
            this.x=x;this.x1=x1;this.y=y;this.y1=y1;
            paint.setColor(Color.BLACK);
        }

        @Override
        public void onDraw(Canvas canvas) {
            canvas.drawLine(x, y, x1, y1, paint);

        }



    }