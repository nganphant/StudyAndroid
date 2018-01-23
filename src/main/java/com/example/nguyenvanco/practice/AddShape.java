package com.example.nguyenvanco.practice;

import java.util.ArrayList;

/**
 * Created by NGUYEN VAN CO on 1/19/2018.
 */

public class AddShape implements ICommand{
    private Shape mshape;
    private ArrayList<Shape> marrayShape;
    public  AddShape(Shape shape, ArrayList<Shape> shapeArrayList)
    {
        mshape=shape;
        marrayShape = shapeArrayList;
    }

    @Override
    public void Redo() {
        marrayShape.add(mshape);
    }

    @Override
    public void Undo() {
        marrayShape.remove(marrayShape.size()-1);

    }
}
