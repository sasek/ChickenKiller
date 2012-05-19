package edu.sasq.Chickenkiller.android;

import java.util.ArrayList;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Element
{
	public float mX;
	public float mY;
	private int points;
	public float slikaX;
	public float slikaY;
	public double mSpeedX;
	public double mSpeedY;
	public boolean killer;

	private int positionBitmap = 0;
	private long timeToChangeBitmap;
	private boolean isAnimated;

	public ArrayList<Bitmap> AnimatedBitmaps = new ArrayList<Bitmap>();

	public Bitmap mBitmap;

	public Element(Resources res, int x, int y, int p)
	{
	
		mBitmap = BitmapFactory.decodeResource(res, R.drawable.finish);
		AnimatedBitmaps.add(mBitmap);
		AnimatedBitmaps.add(BitmapFactory
				.decodeResource(res, R.drawable.dontkill));
		AnimatedBitmaps.add(BitmapFactory
				.decodeResource(res, R.drawable.gabi48));
		mX = x + (mBitmap.getWidth() / 2);
		mY = y + (mBitmap.getHeight() / 2);
		slikaX = mBitmap.getWidth() / 2;
		slikaY = mBitmap.getHeight() / 2;
		points = p;
		mSpeedX = 1;
		mSpeedY = 0;
		killer = false;
		isAnimated = true;
		timeToChangeBitmap = 0;

	}

	public Element(Resources res, int x, int y, int p, double sX, double sY)
	{
		mBitmap = BitmapFactory.decodeResource(res, R.drawable.finish);
		AnimatedBitmaps.add(mBitmap);
		AnimatedBitmaps.add(BitmapFactory
				.decodeResource(res, R.drawable.dontkill));
		AnimatedBitmaps.add(BitmapFactory
				.decodeResource(res, R.drawable.gabi48));
		mX = x + (mBitmap.getWidth() / 2);
		mY = y + (mBitmap.getHeight() / 2);
		points = p;
		mSpeedX = sX;
		mSpeedY = sY;
		slikaX = mBitmap.getWidth() / 2;
		slikaY = mBitmap.getHeight() / 2;
		killer = false;
		isAnimated = true;
		timeToChangeBitmap = 0;
	}

	public Element(Resources res, int x, int y)
	{
		mBitmap = BitmapFactory.decodeResource(res, R.drawable.tank);
		mX = x + (mBitmap.getWidth() / 2);
		mY = y + (mBitmap.getHeight() / 2);
		slikaX = mBitmap.getWidth();
		slikaY = mBitmap.getHeight();
		isAnimated = false;

	}

	public Element(Resources res, int x, int y, boolean z)
	{
		if (z)
		{
			mBitmap = BitmapFactory.decodeResource(res, R.drawable.healthcrate);
		}
		else
		{
			mBitmap = BitmapFactory.decodeResource(res, R.drawable.ammo);
		}
		mX = x + (mBitmap.getWidth() / 2);
		mY = y + (mBitmap.getHeight() / 2);
		slikaX = mBitmap.getWidth();
		slikaY = mBitmap.getHeight();
		mSpeedY = 2.5;
		isAnimated = false;

	}

	public Element(Resources res, int x, int y, double sX, double sY)
	{
		mBitmap = BitmapFactory.decodeResource(res, R.drawable.metek);
		mX = x + (mBitmap.getWidth() / 2);
		mY = y + (mBitmap.getHeight() / 2);
		slikaX = mBitmap.getWidth() / 2;
		slikaY = mBitmap.getHeight() / 2;
		mSpeedX = sX;
		mSpeedY = sY;
		isAnimated = false;
	}

	public Element(Resources res)
	{
		mBitmap = BitmapFactory.decodeResource(res, R.drawable.tank);
		slikaX = mBitmap.getWidth();
		slikaY = mBitmap.getHeight();
		isAnimated = false;
	}

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

	public void doDraw(Canvas canvas)
	{
		canvas.drawBitmap(mBitmap, mX, mY, null);

	}
	public void animate(long elapsedTime)
	{
		if (this.isAnimated)
		{
			if(this.timeToChangeBitmap==0)
			{
				this.timeToChangeBitmap=System.currentTimeMillis();
			
				//TODO ko pride do vrha gremo nazaj 
			}
			else if(System.currentTimeMillis()-timeToChangeBitmap>200) // 200 ms
			{
				this.mBitmap=AnimatedBitmaps.get(positionBitmap);
				this.timeToChangeBitmap=System.currentTimeMillis();;
				this.positionBitmap++;
				if(this.positionBitmap==AnimatedBitmaps.size())
				{
					this.positionBitmap=0;
				}
					
			}
		}

		mX += mSpeedX * (elapsedTime / 20f);
		mY += mSpeedY * (elapsedTime / 20f);
	}
}
