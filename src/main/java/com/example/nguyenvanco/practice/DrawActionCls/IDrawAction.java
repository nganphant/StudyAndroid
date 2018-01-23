package com.example.nguyenvanco.practice.DrawActionCls;

import android.graphics.Point;

import com.example.nguyenvanco.practice.ActiveShape;
import com.example.nguyenvanco.practice.Shape;

/**
 * Created by Ngan on 2018/01/11.
 */

public interface IDrawAction {
    ActiveShape isActionActive(Point point, ActiveShape shape);
    void touchMove(Point point);
    void touchUp(Point point, Shape shape);
}
