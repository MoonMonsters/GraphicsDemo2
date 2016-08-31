package com.chalmers.graphicsdemo2.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Chalmers on 2016-08-30 19:55.
 * email:qxinhai@yeah.net
 */
public class PieView extends View {

    private int degrees[] = null;
    private static final int colors[] = {Color.BLACK, Color.BLUE,Color.RED,Color.GREEN,
    Color.GRAY,Color.CYAN,Color.YELLOW};

    private int width;
    private int height;

    private int x;
    private int y;
    private int radius;

    private boolean isInit = true;
    private RectF rectF = null;

    private Paint mPaint;

    private int mStrokeWidth = 5;

    public PieView(Context context) {
        this(context,null);
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        initData();

        drawPie(canvas);
    }

    private void drawPie(Canvas canvas) {
        if(degrees != null && degrees.length != 0){
            int currentDegree = 0;
            for(int i=0; i<degrees.length; i++){
                int colorData = i>=colors.length?randomColor():colors[i];
                mPaint.setColor(colorData);

                canvas.drawArc(rectF,currentDegree,degrees[i],true,mPaint);
                currentDegree += degrees[i];
            }
        }
    }

    private void initData() {
        if(isInit){

            width = getWidth();
            height = getHeight();

            x = width / 2;
            y = height / 2;
            radius = width>height?width/2:height/2;

            rectF = new RectF(mStrokeWidth*2,mStrokeWidth*2,width-mStrokeWidth*2,width);

            isInit = false;
        }
    }

    private void initPaint(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);
    }

    public static int randomColor(){

        return Color.argb((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255),
        (int)(Math.random()*255));
    }

    private ArrayList<Integer> mDegreeList;
    public void setDegrees(int data[]){
        mDegreeList = new ArrayList<>();
        for(int i : data){
            mDegreeList.add(i);
        }

//        invalidate();
    }

    ViewThread mViewThread = null;
    public void start(){
        mViewThread = new ViewThread();
        mViewThread.start();

    }

    class ViewThread extends Thread{
        @Override
        public void run() {
            degrees = new int[mDegreeList.size()];
            for(int i=0; i<mDegreeList.size(); i++){
                degrees[i] = mDegreeList.get(i);

                postInvalidate();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
