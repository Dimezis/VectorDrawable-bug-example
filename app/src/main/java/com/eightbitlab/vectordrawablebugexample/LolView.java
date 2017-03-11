package com.eightbitlab.vectordrawablebugexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class LolView extends FrameLayout {

    private Canvas lolCanvas;
    private View root;

    {
        setWillNotDraw(false);
        //bitmap size doesn't matter
        Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        lolCanvas = new Canvas(bitmap);
    }

    public void setRoot(@NonNull View root) {
        this.root = root;
    }

    public LolView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        lolCanvas.save();
        //scale is a main trigger here, VectorDrawable will be downscaled just as much as this canvas
        lolCanvas.scale(1f / 8, 1f / 8);
        root.draw(lolCanvas);
        lolCanvas.restore();
        invalidate();
    }
}
