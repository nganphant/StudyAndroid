package com.example.nguyenvanco.practice;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by NGUYEN VAN CO on 1/2/2018.
 */

public class Rhombus extends Shape {
    public Rhombus(Rect rect, Paint paint){
        super(rect,paint);
    }

    @Override
    public void Ondraw(Canvas canvas) {
        canvas.drawPath(path, m_paint);
//        canvas.drawRect(m_rect, m_paint);
    }
    Path path;

    @Override
    protected void OnPropertyChanged(String property) {
        if (property!="m_rect") return;

        //LESSION: Thao tac ton kem xu ly thoi gian, neu co the, chi xu ly khi can
        //OnDraw se dc goi rat rat nhiu lan -> tinh toan trong day la NOT GOOD
        Point point1 = new Point(m_rect.left,m_rect.top + (m_rect.bottom-m_rect.top)/2);

        Point point2= new Point(m_rect.left + (m_rect.right-m_rect.left)/2,m_rect.bottom);

        // Point point2 = new Point(m_rect.left,m_rect.bottom);
        Point point3 = new Point(m_rect.right ,m_rect.top + (m_rect.bottom-m_rect.top)/2);
        Point point4 = new Point(m_rect.left + (m_rect.right-m_rect.left)/2,m_rect.top);

        path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(point1.x,point1.y);
        path.lineTo(point2.x,point2.y);
        path.lineTo(point3.x,point3.y);
        path.lineTo(point4.x,point4.y);
        path.moveTo(point1.x,point1.y);
        path.close();
    }

}
