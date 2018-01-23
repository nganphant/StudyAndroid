package com.example.nguyenvanco.practice.DrawActionCls;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.nguyenvanco.practice.ActiveShape;
import com.example.nguyenvanco.practice.Shape;

import java.util.ArrayList;

/**
 * Created by Ngan on 2018/01/11.
 */

public class ActionMove implements IDrawAction {
    Point touchDownPoint;
    ActiveShape activeShape;
    ArrayList<Shape> m_listShape;
    public  ActionMove(ArrayList<Shape> listShape){
        m_listShape = listShape;

        activeShape=new ActiveShape();
        activeShape.ShapeIdx=-1;
        Paint pt=new Paint();
        pt.setStyle(Paint.Style.STROKE);
        pt.setStrokeWidth(4.0f);
        pt.setColor(Color.argb(255, 67,158,184));
        activeShape.setPaint( pt);
    }

    @Override
    public ActiveShape isActionActive(Point point, ActiveShape shape) {
        touchDownPoint = point;

        if(shape!=null && shape.IsContain(point)){
                activeShape.setRect(new Rect(shape.getRect()));
                activeShape.ShapeIdx = m_listShape.indexOf(shape);
            System.out.println("nvc:2222222 shape active:" + activeShape.ShapeIdx);
            activeShape.mrotate = shape.mrotate;
            return activeShape;
        }

        activeShape.ShapeIdx=-1;
        // lặp từ cuối lên đầu vì shape ở cuối sẽ vẽ trên cùng.
        for(int i = m_listShape.size() -1; i>-1 ; i--)
        {
         //   System.out.println("nvc------" +m_listShape.get(i).getRect());
            System.out.println("nvc:item truoc if i:" + i);
            if(m_listShape.get(i).IsContain(point))
            {
                System.out.println("nvc:item i:" + i);
                activeShape.setRect(new Rect(m_listShape.get(i).getRect()));
                activeShape.ShapeIdx=i;
                System.out.println("nvc:444 shape active:" + activeShape.ShapeIdx);
                activeShape.mrotate = m_listShape.get(i).m_rotate;
                return activeShape;
            }
        }
        return null;
    }

    @Override
    public void touchMove(Point point) {
        activeShape.Offset(point.x-  this.touchDownPoint.x, point.y -  this.touchDownPoint.y);
        this.touchDownPoint = point;
    }

    @Override
    public void touchUp(Point point, Shape shape) {
        shape.setRect(new Rect(activeShape.getRect()));
    }
}
