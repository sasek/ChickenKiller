package edu.sasq.Chickenkiller.android;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Element {
	public float mX;
	public float mY;
	private int points;
	public float slikaX;
	public float slikaY;
	public double mSpeedX;
	public double mSpeedY;
	public boolean killer;
	private Bitmap mBitmap;

	public Element(Resources res, int x, int y, int p) {
		mBitmap = BitmapFactory.decodeResource(res, R.drawable.finish);
		mX = x + (mBitmap.getWidth() / 2);
		mY = y + (mBitmap.getHeight() / 2);
		slikaX = mBitmap.getWidth() / 2;
		slikaY = mBitmap.getHeight() / 2;
		points = p;
		mSpeedX = 1;
		mSpeedY = 0;
		killer = false;
	}

	public Element(Resources res, int x, int y, int p, double sX, double sY) {
		mBitmap = BitmapFactory.decodeResource(res, R.drawable.finish);
		mX = x + (mBitmap.getWidth() / 2);
		mY = y + (mBitmap.getHeight() / 2);
		points = p;
		mSpeedX = sX;
		mSpeedY = sY;
		slikaX = mBitmap.getWidth() / 2;
		slikaY = mBitmap.getHeight() / 2;
		killer = false;
	}

	public Element(Resources res, int x, int y) {
		mBitmap = BitmapFactory.decodeResource(res, R.drawable.tank);
		mX = x + (mBitmap.getWidth() / 2);
		mY = y + (mBitmap.getHeight() / 2);
		slikaX = mBitmap.getWidth();
		slikaY = mBitmap.getHeight();

	}

	public Element(Resources res, int x, int y, boolean z) {
		if (z) {
			mBitmap = BitmapFactory.decodeResource(res, R.drawable.healthcrate);
		} else {
			mBitmap = BitmapFactory.decodeResource(res, R.drawable.ammo);
		}
		mX = x + (mBitmap.getWidth() / 2);
		mY = y + (mBitmap.getHeight() / 2);
		slikaX = mBitmap.getWidth();
		slikaY = mBitmap.getHeight();
		mSpeedY = 2.5;

	}

	public Element(Resources res, int x, int y, double sX, double sY) {
		mBitmap = BitmapFactory.decodeResource(res, R.drawable.metek);
		mX = x + (mBitmap.getWidth() / 2);
		mY = y + (mBitmap.getHeight() / 2);
		slikaX = mBitmap.getWidth() / 2;
		slikaY = mBitmap.getHeight() / 2;
		mSpeedX = sX;
		mSpeedY = sY;
	}

	public Element(Resources res) {
		mBitmap = BitmapFactory.decodeResource(res, R.drawable.tank);
		slikaX = mBitmap.getWidth();
		slikaY = mBitmap.getHeight();
	}

	public float getX() {
		return mX;
	}

	public float getY() {
		return mY;
	}

	public int getPoints() {
		return points;
	}

	public void doDraw(Canvas canvas) {
		canvas.drawBitmap(mBitmap, mX, mY, null);
	}

	/**
	 * @param elapsedTime
	 *            in ms.
	 */
	public void animate(long elapsedTime) {
		mX += mSpeedX * (elapsedTime / 20f);
		mY += mSpeedY * (elapsedTime / 20f);
	}
}
