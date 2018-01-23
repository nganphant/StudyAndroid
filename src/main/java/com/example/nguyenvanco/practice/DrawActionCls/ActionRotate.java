package com.example.nguyenvanco.practice.DrawActionCls;

import android.graphics.Point;

import com.example.nguyenvanco.practice.ActiveShape;
import com.example.nguyenvanco.practice.Shape;

/**
 * Created by NGUYEN VAN CO on 1/13/2018.
 */

public class ActionRotate implements IDrawAction {
    int corner;
    Point touchDownPt;
    ActiveShape activeItem;
    @Override
    public ActiveShape isActionActive(Point point, ActiveShape shape) {
        System.out.println("nvc----isActionActive---shape:" + shape );
        if (shape==null)return null;
        corner = shape.getSelectedIdx(point);
        System.out.println("nvc----isActionActive---corner:" + corner );
        if (corner!=8)
            return null;

        activeItem = shape;
        touchDownPt = point;
        return activeItem;
    }

    @Override
    public void touchMove(Point point) {
        System.out.println("nvc----aaaaaa---corner:" + corner );
        if(corner != 8)
            return;
        Point target = new Point(activeItem.getRect().centerX(),activeItem.getRect().centerY());
        activeItem.mrotate = getAngle(target,point);
        System.out.println("nvc-------mrotate:" + activeItem.mrotate );

    }

    @Override
    public void touchUp(Point point, Shape shape) {

    }
    public float getAngle(Point target, Point touchmove) {
        float angle = (float) Math.toDegrees(Math.atan2(target.y - touchmove.y, target.x - touchmove.x));

        return angle;
    }
}
