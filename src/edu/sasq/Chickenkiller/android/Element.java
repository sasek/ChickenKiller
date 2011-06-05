package edu.sasq.Chickenkiller.android;

import java.util.Random;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Element {
    private float mX;
    private float mY;
    private int points;
    public float slikaX;
    public float slikaY;
    
    public double mSpeedX;
    public double mSpeedY;
    public  boolean killer;
    private Bitmap mBitmap;
    
    public Element(Resources res, int x, int y, int p) {
        mBitmap = BitmapFactory.decodeResource(res, R.drawable.finish);
        mX = x - mBitmap.getWidth();
        mY = y - mBitmap.getHeight();
        slikaX=mBitmap.getWidth()/ 2;
        slikaY=mBitmap.getHeight()/ 2;
        points=p;
        mSpeedX = 1;
        mSpeedY = 0;
        killer=false;
    }
    public Element(Resources res, int x, int y, int p,double sX,double sY) {
        mBitmap = BitmapFactory.decodeResource(res, R.drawable.finish);
        mX = x + (mBitmap.getWidth() / 2);
        mY = y + (mBitmap.getHeight() / 2);
        points=p;
        mSpeedX = sX;
        mSpeedY = sY;
        slikaX=mBitmap.getWidth()/ 2;
        slikaY=mBitmap.getHeight()/ 2;
        killer=false;
    }
    public Element(Resources res, int x, int y, double sX,double sY, boolean kill) {
        mBitmap = BitmapFactory.decodeResource(res, R.drawable.dontkill);
        mX = x + (mBitmap.getWidth() / 2);
        mY = y + (mBitmap.getHeight() / 2);
        mSpeedX = sX;
        mSpeedY = sY;
        points=-35;
        killer=true;
        slikaX=mBitmap.getWidth()/ 2;
        slikaY=mBitmap.getHeight()/ 2;
    }
    /*
    public  Element(Resources res, int x, int y) {
        mBitmap = BitmapFactory.decodeResource(res, R.drawable.finish);
        mX = x - (mBitmap.getWidth() / 2);
        mY = y - (mBitmap.getHeight() / 2);
        mSpeedX = 0.5;
        mSpeedY = 0;
    }*/
    public float getX()
    {
    	return mX;
    }
    public float getY()
    {
    	return mY;
    }
    public int getPoints()
    {
    	return points;
    }
    public void doDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, mX, mY, null);
    }
    
    /**
     * @param elapsedTime in ms.
     */
    public void animate(long elapsedTime) {
        mX += mSpeedX * (elapsedTime / 20f);
        mY += mSpeedY * (elapsedTime / 20f);
       // checkBorders();
    }

    private void checkBorders() {
        if (mX <= 0) {
            mSpeedX = -mSpeedX;
            mX = 0;
        } else if (mX + mBitmap.getWidth() >= Panel.mWidth) {
            mSpeedX = -mSpeedX;
            mX = Panel.mWidth - mBitmap.getWidth();
        }
        if (mY <= 0) {
            mY = 0;
            mSpeedY = -mSpeedY;
        }
        if (mY + mBitmap.getHeight() >= Panel.mHeight) {
            mSpeedY = -mSpeedY;
            mY = Panel.mHeight - mBitmap.getHeight();
        }
    }
}
