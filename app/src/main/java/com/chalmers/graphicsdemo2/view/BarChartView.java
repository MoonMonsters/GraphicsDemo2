package com.chalmers.graphicsdemo2.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Chalmers on 2016-08-31 12:49.
 * email:qxinhai@yeah.net
 */
public class BarChartView extends View {

    private Paint mPaintOrigin;

    private RectF[] mRectFs;
    private int data[];

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
    /** 间距 */
    int paddingWidth;

    private static final int colors[] = {Color.BLACK, Color.BLUE,Color.RED,Color.GREEN,
            Color.GRAY,Color.CYAN,Color.YELLOW};

    public BarChartView(Context context) {
        this(context,null);
    }

    public BarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        initData();
        drawOriginLine(canvas);

        drawBarChart(canvas);
    }

    private void initPaint(){
        mPaintOrigin = new Paint();
        mPaintOrigin.setColor(Color.BLACK);
        mPaintOrigin.setAntiAlias(true);
    }

    /**
     * 画坐标轴
     * @param canvas 画布对象
     */
    private void drawOriginLine(Canvas canvas) {

        float lines[] = {mOriginX,mOriginY, mLineUpX,mLineUpY,
                mOriginX,mOriginY,mLineRightX,mLineRightY};
        mPaintOrigin.setStrokeWidth(mStrokeWidth);
        mPaintOrigin.setColor(Color.BLACK);
        //画两条坐标轴
        canvas.drawLines(lines,mPaintOrigin);

        //画原点
        canvas.drawPoint(mOriginX,mOriginY,mPaintOrigin);
    }

    /**
     * 初始化数据
     */
    private void initData(){

        Log.i("TAG","initData");

        if(!isInit){
            return ;
        }
        int width = getWidth();
        int height = getHeight();

        paddingWidth = 50;

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

    private void drawBarChart(Canvas canvas){
        //得到每一个柱状图的宽度
        int paddingRight = mLineRight / data.length;

        //得到最大值
        int max = Integer.MIN_VALUE;
        for(int i : data){
            if(i > max){
                max = i;
            }
        }
        //得到竖坐标中，每一份的长度
        int paddingUp = mLineUp / max;
        //初始化
        mRectFs = new RectF[data.length];
        int currentRight = paddingWidth;
        for(int i=0; i<data.length; i++){
            int lineLength = data[i] * paddingUp;
            mRectFs[i] = new RectF(currentRight+mStrokeWidth*2,mLineUp-lineLength,currentRight+paddingRight,mLineUp+paddingWidth-mStrokeWidth*2);
            currentRight += paddingRight;
        }

        //画图
        for(int i=0; i<mRectFs.length; i++){
            mPaintOrigin.setStyle(Paint.Style.FILL_AND_STROKE);
            int color = i < colors.length?colors[i]:randomColor();
            mPaintOrigin.setColor(color);

            canvas.drawRect(mRectFs[i],mPaintOrigin);
        }
    }

    //设置数据
    public void setData(int data[]){
       this.data = data;

        invalidate();
    }

    private int randomColor(){

        return Color.argb((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255),
                (int)(Math.random()*255));
    }
}
