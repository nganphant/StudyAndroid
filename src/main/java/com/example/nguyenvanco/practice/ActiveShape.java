package com.example.nguyenvanco.practice;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.ArrayList;

/**
 * Created by Ngan on 2018/01/11.
 */

public class ActiveShape extends Shape {
    public int ShapeIdx;
    private float radius =30;
    public float mrotate =0;
    public ArrayList<Rect> mArrayRect;
    @Override
    public void Ondraw(Canvas canvas) {
        canvas.save();
        canvas.rotate(mrotate,m_rect.centerX(),m_rect.centerY());
        canvas.drawRect(m_rect, m_paint);
        drawBoderRect(canvas);
        canvas.restore();
    }
    public void drawBoderRect(Canvas canvas)
    {
       /* canvas.drawCircle(m_rect.left,m_rect.top,radius,m_paint);
        canvas.drawCircle(m_rect.left,m_rect.top + (m_rect.bottom-m_rect.top)/2,radius,m_paint);
        canvas.drawCircle(m_rect.left,m_rect.bottom,radius,m_paint);
        canvas.drawCircle(m_rect.left + (m_rect.right-m_rect.left)/2,m_rect.bottom,radius,m_paint);
        canvas.drawCircle(m_rect.right,m_rect.bottom,radius,m_paint);
        canvas.drawCircle(m_rect.right ,m_rect.top + (m_rect.bottom-m_rect.top)/2,radius,m_paint);
        canvas.drawCircle(m_rect.right ,m_rect.top,radius,m_paint);
        canvas.drawCircle(m_rect.left + (m_rect.right-m_rect.left)/2,m_rect.top,radius,m_paint);
        */
        for(int i =0; i< mArrayRect.size();i++)
            canvas.drawCircle(mArrayRect.get(i).centerX(),mArrayRect.get(i).centerY(),radius,m_paint);
        canvas.drawLine(m_rect.left + (m_rect.right-m_rect.left)/2,m_rect.top,m_rect.left + (m_rect.right-m_rect.left)/2,m_rect.top-100,m_paint);
      //  canvas.drawCircle(m_rect.left + (m_rect.right-m_rect.left)/2,m_rect.top-100,radius,m_paint);
        RectF rectF = new RectF ((int)(m_rect.left + (m_rect.right-m_rect.left)/2 -radius),
                (int)(m_rect.top-100 -radius),
                (int)(m_rect.left + (m_rect.right-m_rect.left)/2 +radius),
                (int)(m_rect.top - 100 + radius) );
        canvas.drawArc(rectF,90,270,true,m_paint);

    }
    @Override
    protected void OnPropertyChanged(String property) {
        if (property != "m_rect") return;
        mArrayRect = new ArrayList<>();
        Rect m_rect1 = new Rect((int)(m_rect.left - radius),(int)(m_rect.top - radius),
                                (int)( m_rect.left + radius),(int)( m_rect.top+ radius));
        Rect m_rect2 = new Rect((int)(m_rect.left - radius),(int)(m_rect.top + (m_rect.bottom-m_rect.top)/2 - radius),
                                (int)( m_rect.left + radius),(int)(m_rect.top + (m_rect.bottom-m_rect.top)/2+ radius));
        Rect m_rect3 = new Rect((int)(m_rect.left - radius),(int)(m_rect.bottom - radius),
                                (int)( m_rect.left + radius),(int)( m_rect.bottom+ radius));
        Rect m_rect4 = new Rect((int)(m_rect.left + (m_rect.right-m_rect.left)/2 - radius),(int)(m_rect.bottom - radius),
                                (int)( m_rect.left + (m_rect.right-m_rect.left)/2 + radius),(int)( m_rect.bottom+ radius));
        Rect m_rect5 = new Rect((int)(m_rect.right - radius),(int)(m_rect.bottom - radius),
                                (int)( m_rect.right + radius),(int)( m_rect.bottom+ radius));
        Rect m_rect6 = new Rect((int)(m_rect.right - radius),(int)(m_rect.top + (m_rect.bottom-m_rect.top)/2 - radius),
                                (int)( m_rect.right + radius),(int)( m_rect.top + (m_rect.bottom-m_rect.top)/2+ radius));
        Rect m_rect7 = new Rect((int)(m_rect.right - radius),(int)(m_rect.top - radius),
                                (int)( m_rect.right + radius),(int)( m_rect.top+ radius));
        Rect m_rect8 = new Rect((int)(m_rect.left + (m_rect.right-m_rect.left)/2 - radius),(int)(m_rect.top - radius),
                                (int)(m_rect.left + (m_rect.right-m_rect.left)/2 + radius),(int)( m_rect.top+ radius));
        Rect m_rect9 = new Rect ((int)(m_rect.left + (m_rect.right-m_rect.left)/2 -radius),
                                (int)(m_rect.top-100 -radius),
                                (int)(m_rect.left + (m_rect.right-m_rect.left)/2 +radius),
                                (int)(m_rect.top - 100 + radius) );
        mArrayRect.add(m_rect1);
        mArrayRect.add(m_rect2);
        mArrayRect.add(m_rect3);
        mArrayRect.add(m_rect4);
        mArrayRect.add(m_rect5);
        mArrayRect.add(m_rect6);
        mArrayRect.add(m_rect7);
        mArrayRect.add(m_rect8);
        mArrayRect.add(m_rect9);
    }
    public int getSelectedIdx(Point point)
    {
        for (int i = 0 ; i< mArrayRect.size(); i++)
        {
            if(mArrayRect.get(i).contains(point.x,point.y)) {
                return i;
            }
        }
        return -1;
    }

}
