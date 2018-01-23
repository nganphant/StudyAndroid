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

public class ActionResize implements IDrawAction {
    int corner;
    Point touchDownPt;
    ActiveShape activeItem;
    float starAngle;
    @Override
    public ActiveShape isActionActive(Point point, ActiveShape shape) {
        if (shape==null)return null;
        corner = shape.getSelectedIdx(point);
        if (corner==-1)
            return null;

        activeItem = shape;
        touchDownPt = point;
        if(corner ==8)
        {
            Point a = new Point(activeItem.getRect().centerX(),activeItem.getRect().centerY());
            starAngle= getAngle(a,point);


        }
        return activeItem;
    }
    @Override
    public void touchMove(Point point) {
      //  System.out.println("nvc-----------secletedIndex:" + secletedIndex);
       // System.out.println("nvc-------corner:" + corner );
           if(corner == -1)
               return;

               int detalX = point.x-  this.touchDownPt.x;
               int detalY = point.y-  this.touchDownPt.y;
                Rect r=activeItem.getRect();
                switch (corner)
               {
                   case 1:
                       r.left += detalX;
                       break;
                   case 3: r.bottom += detalY;
                       break;
                   case 5:
                       r.right += detalX;
                       break;
                   case 7:
                       r.top += detalY;
                   case 0:
                       r.left +=detalX;
                       r.top +=detalY;
                       break;
                   case 2:
                       r.left +=detalX;
                       r.bottom +=detalY;
                       break;
                   case 4:
                       r.right +=detalX;
                       r.bottom +=detalY;
                       break;
                   case 6:
                       r.right +=detalX;
                       r.bottom +=detalY;
                       break;
                   case 8:

                       Point a = new Point(activeItem.getRect().centerX(),activeItem.getRect().centerY());
                       activeItem.mrotate = getAngle(a,point) - starAngle;
                       System.out.println("nvc-------activeItem.mrotate :" + activeItem.mrotate  );
               }
               this.touchDownPt = point;
            activeItem.setRect(r);//gan cho chinh no

    }

    @Override
    public void touchUp(Point point, Shape shape) {
        if(corner!=8) {
            shape.setRect(new Rect(activeItem.getRect()));
        }
        else
        {
            shape.m_rotate = activeItem.m_rotate;
        }

    }
    public float getAngle(Point target, Point touchmove) {
        float angle = (float) Math.toDegrees(Math.atan2(target.y - touchmove.y, target.x - touchmove.x));

        return angle;
    }
}
