package com.avoole.im.wiget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 */
public class PressTextView extends AppCompatTextView {

    public PressTextView(Context context) {
        super(context);
    }

    public PressTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PressTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchSetPressed(boolean pressed) {
        Drawable drawable = getBackground();
        if(drawable == null) return;
        if(pressed){
            if(drawable != null){
                drawable.setAlpha(127);
            }
        }else{
            drawable.setAlpha(255);
        }
    }
}
