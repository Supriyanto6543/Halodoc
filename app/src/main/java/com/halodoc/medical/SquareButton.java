package com.arkay.quoteapp.customeviews;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.arkay.quoteapp.R;

/**
 * Created by Alejandro on 06/06/14.
 */

/**
 * A Google Plus like, circular button for Android.
 * See https://github.com/Alexrs95/CircularButton
 */
public class SquareButton extends ImageView {

    /**
     * The dimension of the shadow is a 15% of the radius of the button
     */
    private static float SHADOW_CONSTANT = 0.0F;

    private Paint mButtonPaint;
    private float centerX;
    private float centerY;
    private float left=0;
    private float top=0;
    private float right = left+getResources().getDimension(R.dimen.square);
    private float bottom = top + getResources().getDimension(R.dimen.square);

    private int buttonColor = Color.WHITE;
    private int shadowColor = Color.GRAY;

    public SquareButton(Context context) {
        super(context);
        init(context, null);
    }

    public SquareButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SquareButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void init(Context context, AttributeSet attrs) {
        setScaleType(ScaleType.CENTER_INSIDE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        mButtonPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mButtonPaint.setStyle(Paint.Style.FILL);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SquareButton);
            buttonColor = a.getColor(R.styleable.SquareButton_buttonColor, buttonColor);
            shadowColor = a.getColor(R.styleable.SquareButton_shadowColor, shadowColor);
            a.recycle();
        }
        setButtonColor(buttonColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        RectF rectF = new RectF(left,top,right,bottom);
        canvas.drawRect(rectF, mButtonPaint);
        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        centerX = width / 2;
        centerY = height / 2;
    }


    public void setButtonColor(int color) {
        this.buttonColor = color;
        mButtonPaint.setColor(buttonColor);
        invalidate();
    }
}
