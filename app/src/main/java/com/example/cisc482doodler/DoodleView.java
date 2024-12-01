package com.example.cisc482doodler;

import android.annotation.SuppressLint;
import android.view.View;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class DoodleView extends View {

    private Bitmap b;
    private Canvas c;
    private Paint p;
    private Matrix m;

    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        b = Bitmap.createBitmap(411*3, 763*3, Bitmap.Config.ARGB_8888);
        c = new Canvas(b);

        p = new Paint();
        p.setColor(Color.BLACK);
        p.setStrokeWidth(2);
        p.setAlpha(255);
        p.setStyle(Paint.Style.FILL);

        m = new Matrix();
    }

    @Override
    protected void onDraw(Canvas c) {
        super.onDraw(c);

        c.drawBitmap(b, m, null);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN || e.getAction() == MotionEvent.ACTION_MOVE) {
            float x = e.getX();
            float y = e.getY();

            c.drawCircle(x, y, p.getStrokeWidth(), p);

            invalidate();
            return true;
        }
        return false;
    }
}
