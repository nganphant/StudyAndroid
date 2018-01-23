package com.example.nguyenvanco.practice;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Size;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
//import com.example.

public class MainActivity extends AppCompatActivity {
    public static DrawShape drawShape;
    public static Activity m_activity;
    public int count =1;
    public LinearLayout btnListView;
    public UndoRedoManager manager = new UndoRedoManager();
    public boolean check = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_activity = this;
        isStoragePermissionGranted(m_activity);
        btnListView = (LinearLayout)findViewById(R.id.btnListView);
        createBtnShape();
        drawShape =  (DrawShape)findViewById(R.id.drawShape);
        drawShape.activity=this;

    }

    private void createBtnShape() {
        View.OnClickListener buttonListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE); // the results will be higher than using the activity context object or the getWindowManager() shortcut
                wm.getDefaultDisplay().getMetrics(displayMetrics);

                int shapeIdx = (int)v.getTag();
                Shape shape=null;
                int width = 400,
                        height =200;
                int maxCol = displayMetrics.widthPixels/width;
                int maxrow = displayMetrics.heightPixels/height;
                Point pos = new Point((drawShape.m_listShape.size()%maxCol)*width,
                        ((drawShape.m_listShape.size()/maxCol)%maxrow)*height);
                Rect rect = new Rect(
                        pos.x,
                        pos.y,pos.x+width,pos.y+height);

//                if (rect.right>screenWidth){
//                    rect.offset(-rect.left, height);
//                }

                Paint m_paint = new Paint();

                switch (shapeIdx){
                    case 0:
                        m_paint.setStyle(Paint.Style.FILL);

                        shape = new Rectangle(rect,m_paint);
                        break;
                    case 1:
                        m_paint.setStyle(Paint.Style.FILL);
                        m_paint.setColor(Color.BLUE);
                        rect.right=rect.left+(rect.bottom-rect.top);
                        shape = new Circle(rect,m_paint);
                        break;
                    case 2:
                        m_paint.setStyle(Paint.Style.FILL);
                        m_paint.setColor(Color.RED);
                        shape = new Triangle(rect,m_paint);
                        break;
                    case 3:
                        m_paint.setStyle(Paint.Style.FILL);
                        m_paint.setColor(Color.GREEN);
                        shape = new Rhombus(rect,m_paint);
                        break;
                    case 4:
                        m_paint.setStyle(Paint.Style.FILL);
                        // Set the text color
                        m_paint.setColor(Color.RED);
                        m_paint.setAntiAlias(true);
                        // Set the text size
                        m_paint.setTextSize(30);
                        m_paint.setTextAlign(Paint.Align.CENTER);
                        // Initialize a typeface object to draw text on canvas
                        Typeface typeface = Typeface.create(Typeface.SANS_SERIF,Typeface.NORMAL);

                        // Set the paint font
                        m_paint.setTypeface(typeface);

                        DrawText te = new DrawText();
                        te.SetPos(new Point(rect.left,rect.top));
                        te.Text="Text";
                        te.setPaint(m_paint);
                        shape = te;
                        break;
                    case 5:
                        check = true;
                        if (!manager.CanNotUndo())
                        {
                            manager.Undo();
                        }


                       break;
                    case 6:
                        check = true;
                        if (!manager.CannotRedo())
                        {
                            manager.Redo();
                        }

                     break;
                }

                if(!check) {
            //        drawShape.addShape(shape);
                    manager.PerformAction(new AddShape(shape, drawShape.m_listShape));

                }

                drawShape.invalidate();
                System.out.println("nvc--------drawShape.m_listShap:"+ drawShape.m_listShape.size());
                check = false;
            }
        };
        Button btn;

        for (int i=0;i<7;i++){
            btn = new Button(this);
            btn.setTag(i);
            btn.setOnClickListener(buttonListener);


            this.btnListView.addView(btn);

            switch (i){
                case 0:btn.setText("Rectangle");
                    break;
                case 1:btn.setText("Circle");
                    break;
                case 2:btn.setText("Triangle");
                    break;
                case 3:btn.setText("Hinh Thoi");
                    break;
                case 4:btn.setText("Text");
                    break;
                case 5:btn.setText("Undo");
                    break;
                case 6:btn.setText("Redo");
                    break;
                default:
                    break;
            }
        }
    }

    public static boolean isStoragePermissionGranted(Activity activity) {

        if (Build.VERSION.SDK_INT >= 23) {

            if (activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                System.out.println("nvc---aaaaaa");
                return true;

            } else {
                System.out.println("nvc---bbbb");
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

                return false;

            }
        } else {

            return true;

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(getApplicationContext(), "Please provide access to external storage", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }
}


