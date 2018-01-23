package com.example.nguyenvanco.practice;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by NGUYEN VAN CO on 1/20/2018.
 */

public class CustomerDialog extends Dialog {
    public CustomerDialog(Context context)
    {
        super(context);
    }
    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogcustomer);
        Button btnClearAll = (Button)findViewById(R.id.btn_clearAll);
        btnClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.drawShape.clearAllShape();
                MainActivity.drawShape.invalidate();
                dismiss();
            }
        });
        Button btnDellShape = (Button)findViewById(R.id.btn_dellshape);
        btnDellShape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.drawShape.RemoveShape();
                MainActivity.drawShape.invalidate();
                dismiss();
            }
        });
        Button btn_changecolor = (Button)findViewById(R.id.btn_changecolor);
        btn_changecolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog a = new ColorPickerDialog(MainActivity.drawShape.getContext(),MainActivity.drawShape,MainActivity.drawShape.m_listShape.get(MainActivity.drawShape.activeShape.ShapeIdx).m_paint.getColor());
                a.show();
                dismiss();
            }
        });

    }


}
