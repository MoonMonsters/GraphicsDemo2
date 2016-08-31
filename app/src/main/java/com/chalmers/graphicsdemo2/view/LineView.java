package com.chalmers.graphicsdemo2.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Chalmers on 2016-08-30 20:50.
 * email:qxinhai@yeah.net
 */
public class LineView extends View {

    private int data[];
    private int cordinates[];

    /** 原点的x坐标 */
    private int mOriginX;
    /** 原点的y坐标 */
    private int mOriginY;
    /** 竖轴的x坐标 */
    private int mLineUpX;
    /** 竖轴的y坐标 */
    private int mLineUpY;
    /** 横轴的x坐标 */
    private int mLineRightX;
    /** 横轴的y坐标 */
    private int mLineRightY;
    /** 线条大小 */
    private int mStrokeWidth = 5;
    /** 是否已经被初始化过 */
    private boolean isInit = true;
    /** 竖坐标的长度 */
    private int mLineUp;
    /** 横坐标的长度 */
    private int mLineRight;

    private Paint mPaint;

    public LineView(Context context) {
        this(context,null);
    }

    public LineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        initData();
        /** 画坐标轴的x轴和y轴 */
        drawOriginLine(canvas);
        drawLines();

        int index = 0;
        while(true){

            int x = cordinates[index++];
            int y = cordinates[index++];
            mPaint.setColor(Color.RED);
            mPaint.setStrokeWidth(mStrokeWidth*5);
            canvas.drawPoint(x,y,mPaint);

            if(index >= cordinates.length){
                break;
            }

            int x2 = cordinates[index];
            int y2 = cordinates[index+1];
            mPaint.setStrokeWidth(mStrokeWidth);
            mPaint.setColor(Color.GREEN);
            canvas.drawLine(x,y,x2,y2,mPaint);
        }
    }

    /**
     * 画坐标轴
     * @param canvas 画布对象
     */
    private void drawOriginLine(Canvas canvas) {

        float lines[] = {mOriginX,mOriginY, mLineUpX,mLineUpY,
        mOriginX,mOriginY,mLineRightX,mLineRightY};
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setColor(Color.BLACK);
        //画两条坐标轴
        canvas.drawLines(lines,mPaint);

        //画原点
        canvas.drawPoint(mOriginX,mOriginY,mPaint);
    }

    /**
     * 初始化画笔
     */
    private void initPaint(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    /**
     * 初始化数据
     */
    private void initData(){

        if(!isInit){
            return ;
        }
        int width = getWidth();
        int height = getHeight();

        int paddingWidth = 50;

        mOriginX = paddingWidth;
        mOriginY = height - paddingWidth;

        mLineUpX = paddingWidth;
        mLineUpY = paddingWidth;

        mLineRightX = width - paddingWidth;
        mLineRightY = height - paddingWidth;

        mLineUp = mOriginY - mLineUpY;
        mLineRight = mLineRightX - mOriginX;

        isInit = false;
    }

    /**
     * 设置数据
     * @param data 出入的int数组
     */
    public void setData(int data[]){

        this.data = data;

        invalidate();
    }

    /**
     * 画折线图
     */
    private void drawLines(){
        //横向间隔，每个点之间的距离
        int paddingRight = mLineRight / data.length;
        //得到数据的最大值和最小值
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i=0; i<data.length; i++){
            if(data[i] > max){
                max = data[i];
            }
            if(data[i] < min){
                min = data[i];
            }
        }
        //把竖轴长度分成max-min份，得到每一份的长度
        float paddingUp = mLineUp * 1.0f / (max - min);

        cordinates = new int[data.length*2];
        int index = 0;
        int currentRight = 50;
        for(int i=0; i<data.length; i++){
            //得到x坐标
            int x = currentRight;
            //得到y坐标
            int y = (int)(mLineUp - ((data[i] - min) * paddingUp)) + 50;

            //存入坐标数组中
            cordinates[index++] = x;
            cordinates[index++] = y;

            //横坐标移动到下一个点
            currentRight += paddingRight;
        }
    }
}
