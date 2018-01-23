package com.example.nguyenvanco.practice;


        import android.content.Context;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Point;
        import android.graphics.Rect;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.inputmethod.InputMethodManager;
        import android.widget.EditText;
        import android.widget.RelativeLayout;

/**
 * Created by Ngan on 2018/01/11.
 */

public class DrawText extends Shape {
    static EditText EDITOR;
    boolean isEditing = false;
    public String Text;
    boolean isDefaultSize = true;
    int texttHeight=0;
    @Override
    public void Ondraw(Canvas canvas) {
        if (isEditing)return;
        canvas.drawText(
                Text, // Text to draw
                m_rect.centerX(), // x
                m_rect.centerY()+Math.abs(texttHeight/2)/2, // y
                m_paint // Paint
        );
    }

    public void SetPos(Point pt){
        m_rect=new Rect(pt.x,pt.y,1,1);
    }
    public void showEditor(RelativeLayout parent){
        if (DrawText.EDITOR == null) {
            EditText edit = new EditText(parent.getContext());
            parent.addView(edit);
            DrawText.EDITOR = edit;
        }

        RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams(
                m_rect.width(), m_rect.height()
        );

        layout.leftMargin = m_rect.left;
        layout.topMargin = m_rect.top;

        DrawText.EDITOR.setLayoutParams(layout);
        DrawText.EDITOR.setTextColor(m_paint.getColor());
        DrawText.EDITOR.setVisibility(RelativeLayout.VISIBLE);
        DrawText.EDITOR.setText(this.Text);
        DrawText.EDITOR.setTypeface(m_paint.getTypeface());
        DrawText.EDITOR.setTextSize(m_paint.getTextSize()-10);
        DrawText.EDITOR.requestFocus();
        isEditing=true;
    }
    public void hideEditor(){
        if (isEditing){
            DrawText.EDITOR.setVisibility(View.INVISIBLE);
            isEditing = false;
            this.Text = DrawText.EDITOR.getText().toString().trim();
        }
    }
    @Override
    protected void OnPropertyChanged(String property) {
        if (!isDefaultSize)return;

        if (property=="m_rect"){
            isDefaultSize = false;
            return;
        }

        // calculate the y position of canvas to draw the text
        Rect rectangle = new Rect();

        m_paint.getTextBounds(
                Text, // text
                0, // start
                Text.length(), // end
                rectangle // bounds
        );
        texttHeight=rectangle.height();
//        float f=m_paint.measureText(Text);
        int margin = 30;
        m_rect=new Rect(m_rect.left-margin,m_rect.top-margin,rectangle.width()+m_rect.left+margin,
                rectangle.height()+m_rect.top+margin);
    }
}
