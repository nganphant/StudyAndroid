package com.example.nguyenvanco.practice;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by NGUYEN VAN CO on 12/30/2017.
 */
    public class Circle extends Shape {
        public Circle(Rect rect, Paint paint){
            super(rect,paint);
        }
        @Override
        public void Ondraw(Canvas canvas) {
            canvas.drawCircle(m_rect.centerX(),m_rect.centerY(),
                    Math.min(m_rect.width(), m_rect.height())/2,m_paint);
        }
    }


