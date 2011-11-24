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

	public static long timeNow;
	public static long timeBefor;
	public static int mWidth;
	public static int mHeight;
	public static int tocke;
	public static float senzor;
	private ViewThread mThread;
	public static ArrayList<Element> mElements = new ArrayList<Element>();
	public static ArrayList<Element> poljeMetki = new ArrayList<Element>();
	public static Element tank;
	public static Element tankx;
	//private int mElementNumber = 0;

	private Paint mPaint = new Paint();

	public Panel(Context context) {
		super(context);
		getHolder().addCallback(this);
		timeBefor = 0;
		mThread = new ViewThread(this);
		mPaint.setColor(Color.WHITE);
		tankx = new Element(getResources());
		tocke = 0;
		tank = new Element(getResources(), (int) ((mWidth / 2) - tankx.slikaX),
				(int) (mHeight - tankx.slikaY - tankx.slikaY));
	}

	public static int dobiTocke() {
		return tocke;
	}

	public void doDraw(long elapsed, Canvas canvas) {
		try {
			tank.mSpeedX = senzor;
			timeNow = System.currentTimeMillis();
			if (timeNow - timeBefor > 700) {
				poljeMetki.add(new Element(getResources(), (int) tank.getX(),
						(int) tank.getY(), 0, -2));
				timeBefor = timeNow;
				Random x= new Random();
				int index= x.nextInt(mElements.size());
				mElements.get(index).mSpeedY=0.5;
				mElements.get(index).mSpeedX=-x.nextDouble();
				
				Random x2= new Random();
				int index2= x2.nextInt(mElements.size());
				mElements.get(index2).mSpeedY=-0.5;
				mElements.get(index2).mSpeedX=1+x2.nextDouble();
				/*
				boolean prvic=true;
				
				for (int i = 0; i < mElements.size() - 1; i += 2) {
					if(prvic)
					{
					Random randx = new Random();
					int rndx = randx.nextInt(2) + 1;
					Random randy = new Random();
					int rndy = randy.nextInt(3) + 1;
					mElements.get(i).mSpeedY = -rndy;
					mElements.get(i).mSpeedX = rndx;
					prvic=false;
					}
					else
					{
						prvic=true;
						Random randx = new Random();
						int rndx = randx.nextInt(2) + 1;
						Random randy = new Random();
						Random randxd = new Random();
						double rndxd= randxd.nextDouble();
						int rndy = randy.nextInt(3) + 1;
						mElements.get(i).mSpeedY = rndy-rndxd;
						mElements.get(i).mSpeedX = -rndx+rndxd;
					}
				}*/

			}
			if (Tutorial2D.pocasni) {
				Random rand = new Random();
				int rnd = rand.nextInt((int) (mHeight - (tankx.slikaY * 4))) + 40;
				mElements.add(new Element(getResources(), 0, (int) rnd, 10));
				mElements.add(new Element(getResources(), mWidth, (int) rnd, 10,-1.2,-0.6));
				Tutorial2D.pocasni = false;
			}
			canvas.drawColor(Color.BLACK);
			synchronized (mElements) {
				if (mElements.isEmpty() == false) {
					for (Element element : mElements) {
						element.doDraw(canvas);
					}
					if (poljeMetki.isEmpty() == false) {
						for (Element element2 : poljeMetki) {
							element2.doDraw(canvas);
						}
					}
					tank.doDraw(canvas);
				}
			}
			mPaint.setTextSize(15);
			canvas.drawText(Tutorial2D.getTimer(), 10, 20, mPaint);
			canvas.drawText("" + tocke, mWidth - 35, 20, mPaint);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// mWidth = width;
		// mHeight = height;
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
		//	mElementNumber = mElements.size();

			if (event.getAction() == event.ACTION_DOWN) {

			}
		}
		return super.onTouchEvent(event);
	}

	public void animate(long elapsedTime) {
		synchronized (mElements) {
			try {
				if (mElements.isEmpty() == false) {
					for (int i = 0; i < mElements.size(); i++) {
						Element a = mElements.get(i);
						a.animate(elapsedTime);
						if (a.getX() + a.slikaX * 2 <= 0
								|| a.getX() - a.slikaX >= Panel.mWidth) {
							mElements.remove(i);
						}
						colision(a, i);
					}
				}
				tank.animate(elapsedTime);
				if (poljeMetki.isEmpty() == false) {
					for (int i = 0; i < poljeMetki.size(); i++) {
						Element a = poljeMetki.get(i);
						a.animate(elapsedTime);
						if (a.mY <= 10)
							poljeMetki.remove(i);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void colision(Element x, int i) {
		for (int y = 0; y < poljeMetki.size(); y++) {
			Element a = poljeMetki.get(y);
			if ((Math.abs(x.getY() - a.getY()) < 50.0)
					&& (Math.abs(x.getX() - (a.getX() + a.slikaY)) < 20.0)) {

				tocke += mElements.get(i).getPoints();
				mElements.remove(i);
				poljeMetki.remove(y);
			}

		}

	}
}
