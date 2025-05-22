package com.example.miniproject;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

public class GradientDrawable extends Drawable {
    private Paint paint;
    private int progress;

    public GradientDrawable(int color, int progress) {
        this.progress = progress;
        paint = new Paint();
        paint.setShader(new LinearGradient(0, 0, progress, 0,
                new int[]{color, Color.TRANSPARENT},
                null, Shader.TileMode.CLAMP));
    }

    @Override
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.drawRect(bounds, paint);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
