package com.example.nguyenvanco.practice;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.provider.ContactsContract;

/**
 * Created by NGUYEN VAN CO on 12/29/2017.
 */

public class Rectangle extends Shape {
    public Rectangle(Rect rect, Paint paint){
        super(rect,paint);
    }
    @Override
    public void Ondraw(Canvas canvas) {
        canvas.save();
        canvas.rotate(m_rotate,m_rect.centerX(),m_rect.centerY());
        canvas.drawRect(m_rect,m_paint);
        canvas.restore();
    }

}
