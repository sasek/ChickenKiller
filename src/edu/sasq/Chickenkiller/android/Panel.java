package edu.sasq.Chickenkiller.android;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Panel extends SurfaceView implements SurfaceHolder.Callback {

    public static float mWidth;
    public static float mHeight;
    public static int tocke;
    
    private ViewThread mThread;
    public static ArrayList<Element> mElements = new ArrayList<Element>();
    private int mElementNumber = 0;

    private Paint mPaint = new Paint();
    
    public Panel(Context context) {
        super(context);
        getHolder().addCallback(this);
        mThread = new ViewThread(this);
        mPaint.setColor(Color.WHITE);
        tocke=0;
    }
    public static int dobiTocke()
    {
    	return tocke;
    }
    public void doDraw(long elapsed, Canvas canvas) {
    	if(Tutorial2D.hitri)
    	{
    		Random rand= new Random();
			int rnd= rand.nextInt((int)mHeight-80)+80;
		 mElements.add(new Element(getResources(),(int)mWidth,(int)rnd,30,-3,0));
		 mElements.add(new Element(getResources(),(int)mWidth,(int)rnd-25,-3,0,true));
		 Tutorial2D.hitri=false;
    	}
    	if(Tutorial2D.pocasni)
    	{
    	Random rand= new Random();
		int rnd= rand.nextInt((int)mHeight-80)+80;
	 mElements.add(new Element(getResources(),0,(int)rnd,10));
	 Tutorial2D.pocasni=false;
    	}
        canvas.drawColor(Color.BLACK);
        synchronized (mElements) {
            for (Element element : mElements) {
                element.doDraw(canvas);
            }
        }
        mPaint.setTextSize(15);
        canvas.drawText(Tutorial2D.getTimer(), 10, 20, mPaint);
        canvas.drawText(""+tocke, mWidth-35, 20, mPaint);
        //canvas.drawText(""+mWidth, mWidth-60, 10, mPaint);
        //canvas.drawText(""+mHeight, mWidth-90, 10, mPaint);
    }
    
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mWidth = width;
        mHeight = height;
    }
    
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!mThread.isAlive()) {
            mThread = new ViewThread(this);
            mThread.setRunning(true);
            mThread.start();
        }
    }
    
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mThread.isAlive()) {
            mThread.setRunning(false);
        }
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        synchronized (mElements) {
        	Random rand= new Random();
        	float rnd= (float)rand.nextInt((int) (mHeight-48));
          //  mElements.add(new Element(getResources(),0,(int)event.getY(),10));
           // mElements.add(new Element(getResources(),0,(int)rnd,20));
            mElementNumber = mElements.size();
            int i;
            //mElements.remove(0);
            if(event.getAction()==event.ACTION_DOWN)
        	{
           for( i=0;i<mElements.size();i++) 
           {
        	   Element a = mElements.get(i);
                   if( (Math.abs(event.getY()-(a.getY()+a.slikaY))<30.0)&&(Math.abs(event.getX()-(a.getX()+a.slikaY))<30.0))		
        	  {
                	  
                   //Log.d("Izbris v polju:", "y od eventa"+event.getY()+"y od slike"+a.getY());
        		   tocke+=mElements.get(i).getPoints(); 
        		   if(tocke<0)
        			   tocke=0;
        		   mElements.remove(i);
        	  }
        		   
            }
        	}
        }
        return super.onTouchEvent(event);
    }
    
    public void animate(long elapsedTime) {
        synchronized (mElements) {
            //for (Element element : mElements)
        	 for(int  i=0;i<mElements.size();i++)
        	{
        		 Element a = mElements.get(i);
                a.animate(elapsedTime);
                if(Tutorial2D.yspeed)
                {/*
                	if(a.mSpeedY<=1.5)
                		a.mSpeedY=-1.5;
                	else
                		a.mSpeedY=1.5;*/
               
                }
                //Tutorial2D.yspeed=false;
                if(a.getX()+a.slikaX*2<=0 || a.getX()-a.slikaX>=Panel.mWidth)
                {
                	mElements.remove(i);
                }
            }
        }
    }
}
