package com.example.nguyenvanco.practice;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.nguyenvanco.practice.DrawActionCls.ActionMove;
import com.example.nguyenvanco.practice.DrawActionCls.ActionResize;
import com.example.nguyenvanco.practice.DrawActionCls.ActionRotate;
import com.example.nguyenvanco.practice.DrawActionCls.IDrawAction;

import java.util.ArrayList;

/**
 * Created by NGUYEN VAN CO on 12/29/2017.
 */

public class DrawShape extends RelativeLayout implements View.OnLongClickListener, View.OnTouchListener,ColorPickerDialog.OnColorChangedListener{
    public ArrayList<Shape> m_listShape;
    int editingShape=-1;
    public Activity activity;
    ArrayList<IDrawAction> lstDrawAction;
    ActiveShape activeShape;
    IDrawAction activeAction;
   // GestureDetector gestureDetector;
    RelativeLayout self;
    public DrawShape(Context context) {
        super(context);
        init();

    }
    public DrawShape(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawShape(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init();
    }
    public void addShape(Shape shape)
    {
        m_listShape.add(shape);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(int i = 0; i< m_listShape.size(); i++)
        {
            m_listShape.get(i).Ondraw(canvas);
        }

        //draw active shape here
        if (activeShape!=null)
            activeShape.Ondraw(canvas);
    }
    private void init()
    {
        m_listShape = new ArrayList<>() ;
        lstDrawAction = new ArrayList<>();
        lstDrawAction.add(new ActionResize());
       // lstDrawAction.add(new ActionRotate());
        lstDrawAction.add(new ActionMove(m_listShape));//cho no reference cai list
        this.setOnLongClickListener(this);
        this.setOnTouchListener(this);
        self=this;

    /*   gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                if (activeShape ==null|| !(m_listShape.get(activeShape.ShapeIdx) instanceof DrawText))return false;
                ((DrawText)m_listShape.get(activeShape.ShapeIdx)).showEditor(self) ;
                editingShape = activeShape.ShapeIdx;
                return true;
            }
        });
        */

    }
    private void hideEditor(int shapeIdx){
        if (shapeIdx < 0 || !(m_listShape.get(shapeIdx) instanceof DrawText))return;

        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        imm.hideSoftInputFromWindow(view  == null ? new View(activity).getWindowToken() : view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        ((DrawText)m_listShape.get(shapeIdx)).hideEditor() ;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
     //   gestureDetector.onTouchEvent(event);
        boolean defaultResult = v.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                activeAction = null;
                Point point = new Point((int)event.getX(),(int)event.getY());
                for (IDrawAction drawaction : lstDrawAction) {
                    activeShape = drawaction.isActionActive(point, activeShape);
                    if (activeShape!=null){
                        activeAction = drawaction;
                        break;
                    }
                }

                if (activeShape == null || activeShape.ShapeIdx!=editingShape){
                    hideEditor(editingShape);
                    editingShape = activeShape == null ? -1 : activeShape.ShapeIdx;
                }
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                if (activeAction!=null) {
                    activeAction.touchMove(new Point((int) event.getX(), (int) event.getY()));
                    //m_listShape.get(activeShape.ShapeIdx));
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:

                if (activeAction!=null) {
                    activeAction.touchUp(new Point((int) event.getX(), (int) event.getY()),
                            m_listShape.get(activeShape.ShapeIdx));
                    BringShapeToTop(activeShape.ShapeIdx);
                    invalidate();
                }
                break;
            default:
                return defaultResult;

        }

        return true;
    }
    @Override
    public boolean onLongClick(View v)
    {
        System.out.println("nvc-------onLongClick");
        if(activeShape ==null) return false;
        if ((m_listShape.get(activeShape.ShapeIdx) instanceof DrawText)) {
            ((DrawText) m_listShape.get(activeShape.ShapeIdx)).showEditor(self);
            editingShape = activeShape.ShapeIdx;
            System.out.println("nvc-------kkk");

        }
        else
        {
         //  new ColorPickerDialog(this.getContext(), this, m_listShape.get(activeShape.ShapeIdx).m_paint.getColor()).show();
            System.out.println("nvc: shape active:" + activeShape.ShapeIdx);
            CustomerDialog dialog = new CustomerDialog(activity);
            dialog.show();
           // invalidate();
        }
        return true;
    }
    @Override
    public void colorChanged(int color)
    {
        m_listShape.get(activeShape.ShapeIdx).m_paint.setColor(color);
        invalidate();
    }
    void BringShapeToTop(int i)
    {
        //already at the end
        if (i == m_listShape.size() - 1) return;

        Shape ShapeMove =m_listShape.get(i);
        m_listShape.remove(i);
        m_listShape.add(ShapeMove);
    }
    public  void RemoveShape()
    {
        System.out.println("nvc:3333333333 shape active:" + activeShape.ShapeIdx);
        m_listShape.remove(activeShape.ShapeIdx);
        invalidate();
    }
    public void clearAllShape()
    {
        m_listShape.clear();
        invalidate();

    }

}
