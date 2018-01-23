package com.example.nguyenvanco.practice;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by NGUYEN VAN CO on 12/29/2017.
 */

public class Shape {
    public Rect m_rect;
    public Paint m_paint;
    public float m_rotate;
    public Shape(Rect rect, Paint paint){
        setRect(rect);
        setPaint(paint);

    }
    public Shape(Rect rect ){
        setRect(rect);

    }
    public Shape()
    {

    }
    public void Ondraw(Canvas canvas)
    {

    }
    public boolean IsContain(Point point)
    {
        if(m_rect.contains(point.x,point.y))
            return true;
       return false;
    }
    public void Offset(int x, int y)
    {

        m_rect.offset(x,y);
        OnPropertyChanged("m_rect");
    }
    protected void OnPropertyChanged(String property){}
    public void setRect(Rect rect)
    {
        m_rect = rect;
        OnPropertyChanged("m_rect");

    }
    public Rect getRect()
    {
        return m_rect;
    }
    public void setPaint(Paint paint)
    {
        m_paint= paint;
        OnPropertyChanged("m_paint");
    }
    public Paint getPaint()
    {
        return m_paint;
    }
}
