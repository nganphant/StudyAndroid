package com.example.nguyenvanco.practice;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by NGUYEN VAN CO on 1/4/2018.
 */

public class Image extends Shape {
    private String path_image;
    private Bitmap m_bitmap;
    public Image(Rect rect, Paint paint,String m_pathImage)
    {
        super(rect,paint);
        path_image = m_pathImage;
    }
    public Image(Rect rect, Paint paint,Bitmap bitmap)
    {
        super(rect,paint);
        m_bitmap = bitmap;
    }
    @Override
    public void Ondraw(Canvas canvas) {
       // Bitmap bitmap = BitmapFactory.decodeFile(path_image);
        canvas.drawBitmap(resize(m_bitmap,m_rect.width(),m_rect.height()),m_rect.left,m_rect.top,m_paint);

    }
    private static Bitmap resize(Bitmap image, int maxWidth, int maxHeight) {
        if (maxHeight > 0 && maxWidth > 0) {
            int width = image.getWidth();
            int height = image.getHeight();
            float ratioBitmap = (float) width / (float) height;
            float ratioMax = (float) maxWidth / (float) maxHeight;

            int finalWidth = maxWidth;
            int finalHeight = maxHeight;
            if (ratioMax > ratioBitmap) {
                finalWidth = (int) ((float)maxHeight * ratioBitmap);
            } else {
                finalHeight = (int) ((float)maxWidth / ratioBitmap);
            }
            image = Bitmap.createScaledBitmap(image, finalWidth, finalHeight, true);
            return image;
        } else {
            return image;
        }
    }
}
