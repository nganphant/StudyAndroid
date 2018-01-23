package com.example.nguyenvanco.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by NGUYEN VAN CO on 12/28/2017.
 */

public class DrawRectangle extends View {
    public static int m_top;
    public static int m_left;
    public static int m_right;
    public static int m_bottom;
    public Rect m_rect;
    public Paint m_paint;
    private boolean is_insize_rect;
    private int m_old_x;
    private int m_old_y;
    public DrawRectangle(Context context) {
        super(context);
        m_top =100;
        m_left = 100;
        m_right = 200;
        m_bottom = 200;
      //  m_rect = new Rect(m_top,m_left,m_right,m_bottom);
        m_paint = new Paint();
        m_paint.setStyle(Paint.Style.STROKE);
        m_paint.setColor(Color.BLACK);
        m_paint.setStrokeWidth(6f);
    }
    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawRect(m_left,m_top,m_right,m_bottom,m_paint);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                m_old_x = (int)event.getX();
                m_old_y = (int)event.getY();

                //invalidate();
             //   System.out.println(" nvc x=: " + x + "y:=" +y );
                if(m_old_x > m_right || m_old_y> m_bottom || m_old_x< m_top  ||  m_old_y< m_left)
                {
                    is_insize_rect = false;
                 //   System.out.println(" nvc nam ngoai");
                }
                else
                {
                    is_insize_rect = true;
                //    System.out.println(" nvc nam trong");
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(!is_insize_rect)
                    return true;
                int detal_x = (int)event.getX() - m_old_x;
                int detal_y = (int)event.getY() - m_old_y;
                m_top = m_top + detal_y;
                m_left = m_left + detal_x ;
                m_bottom = m_bottom + detal_y;
                m_right = m_right + detal_x;

                m_old_y = (int)event.getY();
                m_old_x = (int)event.getX();

                /// m-top = 500 + 601 - 600 = 501
                // mtop = 501 + 602 - 601 = 502
               // m_top = (int)event.getY() - m_old_y;

                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        invalidate();
        return true;

    }

}
