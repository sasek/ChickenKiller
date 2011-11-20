package edu.sasq.Chickenkiller.android;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Panel extends SurfaceView implements SurfaceHolder.Callback {

    public static float mWidth;
    public static float mHeight;
    public static int tocke;
    
    private ViewThread mThread;
    public static ArrayList<Element> mElements = new ArrayList<Element>();
    public static ArrayList<Element> poljeMetki = new ArrayList<Element>();
    public static Element tank;
    private int mElementNumber = 0;

    private Paint mPaint = new Paint();
    
    public Panel(Context context) {
        super(context);
        getHolder().addCallback(this);
        mThread = new ViewThread(this);
        mPaint.setColor(Color.WHITE);
        tocke=0;
        tank=new Element(getResources(),180,640);
    }
    public static int dobiTocke()
    {
    	return tocke;
    }
    public void doDraw(long elapsed, Canvas canvas) {
    /*	if(Tutorial2D.hitri)
    	{
    		
    		Random rand= new Random();
    		Random rand2= new Random();
			int rnd= rand.nextInt((int)500)+80;
			int rnd2= rand2.nextInt((int)500)+80;
		 mElements.add(new Element(getResources(),(int)mWidth,(int)rnd,30,-3,0));
		 mElements.add(new Element(getResources(),(int)mWidth,(int)rnd2-25,-3,0,true));
		 Tutorial2D.hitri=false;
    	}*/
    	if(Tutorial2D.pocasni)
    	{
    	Random rand= new Random();
		int rnd= rand.nextInt((int)500)+80;
	 mElements.add(new Element(getResources(),0,(int)rnd,10));
	 Tutorial2D.pocasni=false;
    	}
    	if(Tutorial2D.strelaj)
    	{
    		poljeMetki.add(new Element(getResources(),(int)tank.topX,(int)tank.topY,0.5,-3));
    	Tutorial2D.strelaj=false;
    	}
        canvas.drawColor(Color.BLACK);
        synchronized (mElements) {
        	if(mElements.isEmpty()==false)
        	{
            for (Element element : mElements) {
                element.doDraw(canvas);
            }
       //     }
       // }
       // synchronized (poljeMetki){
                if(poljeMetki.isEmpty()==false)
                {
            for(Element element2 : poljeMetki){
            	element2.doDraw(canvas);
            }
                }
            tank.doDraw(canvas);
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
    
    @SuppressWarnings("static-access")
	@Override
    public boolean onTouchEvent(MotionEvent event) {
        synchronized (mElements) {
        //	Random rand= new Random();
       // 	float rnd= (float)rand.nextInt((int) (mHeight-48));
          //  mElements.add(new Element(getResources(),0,(int)event.getY(),10));
           // mElements.add(new Element(getResources(),0,(int)rnd,20));
            mElementNumber = mElements.size();
          //  int i;
            //mElements.remove(0);
            if(event.getAction()==event.ACTION_DOWN)
        	{
            	poljeMetki.add(new Element(getResources(),(int)tank.topX,(int)tank.topY,0,-2));
         /*  for( i=0;i<mElements.size();i++) 
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
        		   
            }*/
        	}
        }
        return super.onTouchEvent(event);
    }
    
    public void animate(long elapsedTime) {
        synchronized (mElements) {
        	 for(int  i=0;i<mElements.size();i++)
        	{
        		 Element a = mElements.get(i);
                a.animate(elapsedTime);
                if(a.getX()+a.slikaX*2<=0 || a.getX()-a.slikaX>=Panel.mWidth)
                {
                	mElements.remove(i);
                }
                colision(a, i);
            }
       // }
       // synchronized (poljeMetki) {
        	 for(int  i=0;i<poljeMetki.size();i++)
        	{
        		 Element a = poljeMetki.get(i);
                a.animate(elapsedTime);
                if(a.mY<=10)
                	poljeMetki.remove(i);
        	}
        }
    }
    public void colision(Element x, int i)
    {
    	for(int y=0;y<poljeMetki.size();y++)
    	{
    		Element a=poljeMetki.get(y);
    		if((Math.abs(x.getY()-a.getY())<50.0)&&(Math.abs(x.getX()-(a.getX()+a.slikaY))<20.0))
    		{
    			mElements.remove(i);
    			tocke+=mElements.get(i).getPoints();
    			poljeMetki.remove(y);
    		}
    		
    	}
    	
    }
}
