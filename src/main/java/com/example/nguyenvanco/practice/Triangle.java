package com.example.nguyenvanco.practice;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by NGUYEN VAN CO on 12/30/2017.
 */

public class Triangle extends Shape {
    public Triangle(Rect rect, Paint paint){
        super(rect,paint);
    }
    @Override
    public void Ondraw(Canvas canvas) {
        Point point1 = new Point(m_rect.left,m_rect.top);
        Point point2 = new Point(m_rect.right,m_rect.bottom);
        Point point3 = new Point(m_rect.left,m_rect.bottom);
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(point1.x,point1.y);
        path.lineTo(point2.x,point2.y);
        path.lineTo(point3.x,point3.y);
        path.lineTo(point1.x,point1.y);
        path.close();

        canvas.drawPath(path, m_paint);
       // canvas.drawRect(m_rect,m_paint);
    }
}
