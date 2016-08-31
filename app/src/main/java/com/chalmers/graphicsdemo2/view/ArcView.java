package com.chalmers.graphicsdemo2.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Chalmers on 2016-08-31 10:13.
 * email:qxinhai@yeah.net
 */
public class ArcView extends View {

    /** 绘制外圈圆 */
    private Paint mPaintOuter;
    /** 绘制内圈圆 */
    private Paint mPaintInner;
    /** 是否被初始化 */
    private boolean isInit = true;
    /** 矩形区域 */
    private RectF mRectF = null;

    private int mStrokeWidth = 20;

    private int mWidth;
    private int mHeight;
    private int mX;
    private int mY;
    private int mRadius;

    private int mDegree = 0;

    public ArcView(Context context) {
        this(context,null);
    }

    public ArcView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        initData();

        canvas.drawArc(mRectF,120,300,false,mPaintOuter);
        canvas.drawArc(mRectF,120,mDegree,false,mPaintInner);
    }

    private void initPaint(){
        mPaintOuter = new Paint();
        mPaintOuter.setColor(Color.GRAY);
        mPaintOuter.setAntiAlias(true);
        mPaintOuter.setStrokeWidth(mStrokeWidth);
        mPaintOuter.setStyle(Paint.Style.STROKE);

        mPaintInner = new Paint();
        mPaintInner.setColor(Color.BLUE);
        mPaintInner.setAntiAlias(true);
        mPaintInner.setStrokeWidth(mStrokeWidth);
        mPaintInner.setStyle(Paint.Style.STROKE);
    }

    private void initData(){

        if(!isInit){
            return ;
        }
        mWidth = getWidth();
        mHeight = getHeight();

        mX = mWidth / 2;
        mY = mHeight / 2;

        mRadius = mWidth<mHeight?mWidth/2:mHeight/2;

        mRectF = new RectF(20,50,mRadius*2-20,mRadius*2);

        isInit = false;
    }

    public void setDegree(int degree){
        this.mDegree = degree;

        invalidate();
    }
}
