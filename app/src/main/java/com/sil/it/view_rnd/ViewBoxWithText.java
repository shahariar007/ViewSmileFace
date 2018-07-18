package com.sil.it.view_rnd;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ViewBoxWithText extends View {
    private Paint paintFace = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paintEye = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paintMouth = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path mPath = new Path();
    private int faceColor = Color.YELLOW;
    private int mouthColor = Color.BLACK;
    private int eyeColor = Color.BLACK;
    private int borderColor = Color.BLACK;
    private float borderWidth = 0.4f;
    private int size = 320;
    private static final String TAG = ViewBoxWithText.class.getSimpleName();

    public ViewBoxWithText(Context context) {
        super(context);
        init();
    }

    public ViewBoxWithText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public ViewBoxWithText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ViewBoxWithText(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawFaceBackground(canvas);
        drawEye(canvas);
        drawMouth(canvas);
        //setBackgroundColor(Color.RED);

    }

    private void drawMouth(Canvas canvas) {
        mPath.moveTo(size * 0.22f, size * 0.70f);
        mPath.quadTo(size * 0.50f, size * 0.80f, size * 0.78f, size * 0.70f);
        mPath.quadTo(size * 0.50f, size * 1.05f, size * 0.22f, size * 0.70f);
        canvas.drawPath(mPath, paintMouth);
    }

    private void drawEye(Canvas canvas) {
        RectF leftEye = new RectF(size * 0.32f, size * 0.23f, size * 0.43f, size * 0.50f);
        canvas.drawOval(leftEye, paintEye);
        RectF rightEye = new RectF(size * 0.57f, size * 0.23f, size * 0.68f, size * 0.50f);
        canvas.drawOval(rightEye, paintEye);
    }

    private void drawFaceBackground(Canvas canvas) {
        paintFace.setStyle(Paint.Style.FILL);
        canvas.drawCircle(size / 2f, size / 2f, size / 2f, paintFace);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: ");
        super.onTouchEvent(event);
        int colorFrom = getResources().getColor(R.color.blue);
        int colorTo = getResources().getColor(R.color.red);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(2500); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                paintFace.setColor((int) animator.getAnimatedValue());
                invalidate();
            }

        });
        colorAnimation.start();
        return false;
    }

    public void init() {
        paintFace.setColor(faceColor);
        paintEye.setColor(Color.BLACK);
        paintEye.setStyle(Paint.Style.FILL);
        paintMouth.setColor(Color.BLACK);
        paintMouth.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(size, size);
    }
}
