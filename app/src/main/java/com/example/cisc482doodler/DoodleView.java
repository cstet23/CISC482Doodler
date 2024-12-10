package com.example.cisc482doodler;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.ColorInt;

public class DoodleView extends View {

    private Bitmap b;
    private Canvas c;
    private Paint p;
    private Matrix m;

    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        b = Bitmap.createBitmap(411*3, 635*3, Bitmap.Config.ARGB_8888);
        c = new Canvas(b);

        p = new Paint();
        p.setColor(Color.BLACK);
        p.setStrokeWidth(3);
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

    public @ColorInt int getColor() {
        return p.getColor();
    }

    public void setColor(@ColorInt int newColor) {
        p.setColor(newColor);
    }

    public void setSize(int newSize) {
        p.setStrokeWidth(newSize);
    }

    public int getOpacity() {
        return p.getAlpha();
    }
    public void setOpacity(int newOpacity) {
        p.setAlpha(newOpacity);
    }

    public void clearImage() {
        b.eraseColor(Color.TRANSPARENT);
        invalidate();
    }

    public void makeEpic(int vPos, int hPos) {
        //making something epic programmatically
        //initialization
        int initX, initY = 0, endX, endY;
        @ColorInt int color = 0xFFFCBA03;
        //draw yellow circle
        for(int row = 0; row < 11; row++, initY += 9) {
            switch(row) {
                case(0):
                case(10):
                    initX = 27; endX = 69; break;
                case(1):
                case(9):
                    initX = 18; endX = 78; break;
                case(2):
                case(8):
                    initX = 9; endX = 87; break;
                default: initX = 0; endX = 96; break;
            }
            endY = initY+9;
            for(int x=initX, y=initY; y<endY; x++) {
                b.setPixel(hPos+x, vPos+y, color);
                if(x == endX) {x = initX; y++;}
            }
        }

        //fill in the glasses/smile
        color = Color.BLACK;

        //top row of glasses
        initX = 0; endX = 96; initY = 27; endY = initY+9;
        for(int x=initX, y=initY; y<endY; x++) {
            b.setPixel(hPos+x, vPos+y, color);
            if(x == endX) {x = initX; y++;}
        }
        //middle row of glasses
        initY += 9; endY = initY+9;
        for(int x=initX, y=initY; y<endY; x++) {
            if(x > 8 && x < 44 || x > 53 && x < 89)
                b.setPixel(hPos+x, vPos+y, color);
            if(x == endX) {x = initX; y++;}
        }
        //bottom row of glasses
        initY += 9; endY = initY+9;
        for(int x=initX, y=initY; y<endY; x++) {
            if(x > 17 && x < 35 || x > 62 && x < 80)
                b.setPixel(hPos+x, vPos+y, color);
            if(x == endX) {x = initX; y++;}
        }
        //corners of smile
        initY += 18; endY = initY+9;
        for(int x=initX, y=initY; y<endY; x++) {
            if(x > 26 && x < 35 || x > 62 && x < 71)
                b.setPixel(hPos+x, vPos+y, color);
            if(x == endX) {x = initX; y++;}
        }
        //bottom of smile
        initY += 9; endY = initY+9;
        for(int x=initX, y=initY; y<endY; x++) {
            if(x > 35 && x < 62)
                b.setPixel(hPos+x, vPos+y, color);
            if(x == endX) {x = initX; y++;}
        }

        //refreshing the view
        invalidate();
    }
}
