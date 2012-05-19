package edu.sasq.Chickenkiller.android;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Panel extends SurfaceView implements SurfaceHolder.Callback
{

	public Context Context2;
	public static long timeNow;
	public static long timeNow2;
	public static long timeBefor;
	public static long timeBefor2;
	public long metkiOn;
	public static int mWidth;
	public static int mHeight;
	public static int tocke;
	public static int sensorY;
	public int healthBar;
	public static float senzor;
	private ViewThread mThread;
	public static ArrayList<Element> mElements = new ArrayList<Element>();
	public static ArrayList<Element> poljeMetki = new ArrayList<Element>();
	public static Element tank;
	public static Element tankx;
	public boolean bonusMetki;
	public Element health;
	public Element ammo;
	public Bitmap slika;
	public static boolean konec = false;
	public ApplicationExample app2;

	private Paint mPaint = new Paint();
	public Paint barvaLifa = new Paint();

	public Panel(Context context, Application app3)
	{
		super(context);
		app2 = (ApplicationExample) app3;
		Context2 = context;
		getHolder().addCallback(this);
		slika = BitmapFactory.decodeResource(getResources(), R.drawable.snow2);
		bonusMetki = false;
		healthBar = 200;
		timeBefor = 0;
		timeBefor2 = 0;
		mThread = new ViewThread(this);
		mPaint.setColor(Color.BLACK);
		mPaint.setTextSize(20);
		barvaLifa.setColor(Color.GREEN);
		barvaLifa.setStrokeWidth(10);
		tankx = new Element(getResources());
		tocke = 0;
		tank = new Element(getResources(), (int) ((mWidth / 2) - tankx.slikaX),
				(int) (mHeight - tankx.slikaY - tankx.slikaY));
	}

	public static int dobiTocke()
	{
		return tocke;
	}

	public void doDraw(long elapsed, Canvas canvas)
	{
		try
		{
			tank.mSpeedX = senzor;
			tank.mSpeedY = sensorY;
			timeNow = System.currentTimeMillis();
			timeNow2 = System.currentTimeMillis();
			if (timeNow - timeBefor > 500)
			{
				if (mWidth >= 480)
					poljeMetki.add(new Element(getResources(), (int) tank
							.getX() + 12, (int) tank.getY(), 0, -4.5));
				if (bonusMetki)
				{
					if (metkiOn + 5000 >= System.currentTimeMillis())
					{
						poljeMetki.add(new Element(getResources(), (int) tank
								.getX() + 12, (int) tank.getY(), -2, -4.5));
						poljeMetki.add(new Element(getResources(), (int) tank
								.getX() + 12, (int) tank.getY(), 2, -4.5));
					}
					else
						bonusMetki = false;

				}
				if (mWidth < 480)
					poljeMetki.add(new Element(getResources(), (int) tank
							.getX(), (int) tank.getY(), 0, -4.5));
				if (bonusMetki)
				{
					if (metkiOn + 5000 <= System.currentTimeMillis())
					{
						poljeMetki.add(new Element(getResources(), (int) tank
								.getX() + 12, (int) tank.getY(), -2, -4.5));
						poljeMetki.add(new Element(getResources(), (int) tank
								.getX() + 12, (int) tank.getY(), 2, -4.5));
					}
				}
				timeBefor = timeNow;
				Random x = new Random();
				int index = x.nextInt(mElements.size());
				mElements.get(index).mSpeedY = 2.5;
				mElements.get(index).mSpeedX = -1 - x.nextDouble();

				Random x2 = new Random();
				int index2 = x2.nextInt(mElements.size());
				mElements.get(index2).mSpeedY = -2.1;
				mElements.get(index2).mSpeedX = 2 + x2.nextDouble();

			}
			if (timeNow2 - timeBefor2 > 1200)
			{
				timeBefor2 = timeNow2;
				Random rand = new Random();
				int rnd = rand.nextInt((int) (mHeight - (tankx.slikaY * 4))) + 40;
				mElements.add(new Element(getResources(), 0, (int) rnd, 10));
				mElements.add(new Element(getResources(), mWidth, (int) rnd,
						10, -2.2, -0.6));
				mElements.add(new Element(getResources(), (int) rnd, 0 - 10,
						10, -0.2, 4));
			}
			canvas.drawBitmap(slika, 0, 0, null);
			synchronized (mElements)
			{
				if (health != null)
				{
					health.doDraw(canvas);
				}
				if (ammo != null)
				{
					ammo.doDraw(canvas);
				}
				if (mElements.isEmpty() == false)
				{
					tank.doDraw(canvas);
					for (Element element : mElements)
					{
						element.doDraw(canvas);
					}
					if (poljeMetki.isEmpty() == false)
					{
						for (Element element2 : poljeMetki)
						{
							element2.doDraw(canvas);
						}
					}

				}
			}
			mPaint.setTextSize(25);
			canvas.drawText(Tutorial2D.getTimer(), 10, 210, mPaint);
			if (healthBar > 150)
				barvaLifa.setColor(Color.GREEN);
			if (healthBar <= 150 && healthBar >= 100)
				barvaLifa.setColor(Color.rgb(255, 204, 153));
			if (healthBar <= 50)
				barvaLifa.setColor(Color.RED);
			if (healthBar != 0 && healthBar > 0)
			{
				canvas.drawLine(10, 8, healthBar, 8, barvaLifa);
			}
			if (healthBar == 0 || healthBar < 0)
			{
				konec = true;
			}
			Paint barvaOkvira = new Paint();
			barvaOkvira.setColor(Color.BLACK);
			barvaOkvira.setStrokeWidth(2);
			canvas.drawLine(9, 3, 201, 3, barvaOkvira);
			canvas.drawLine(9, 12, 201, 12, barvaOkvira);
			canvas.drawLine(9, 3, 9, 12, barvaOkvira);
			canvas.drawLine(201, 3, 201, 12, barvaOkvira);
			canvas.drawText("" + tocke, mWidth - 65, 20, mPaint);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height)
	{

	}

	public void surfaceCreated(SurfaceHolder holder)
	{
		if (!mThread.isAlive())
		{
			mThread = new ViewThread(this);
			mThread.setRunning(true);
			mThread.start();
		}
		finish();
	}

	public void surfaceDestroyed(SurfaceHolder holder)
	{
		if (mThread.isAlive())
		{
			mThread.setRunning(false);
		}
		
	}

	public void animate(long elapsedTime)
	{
		synchronized (mElements)
		{
			try
			{
				if (health != null)
				{
					health.animate(elapsedTime);
				}
				if (ammo != null)
				{
					ammo.animate(elapsedTime);
				}
				if (mElements.isEmpty() == false)
				{
					for (int i = 0; i < mElements.size(); i++)
					{
						Element a = mElements.get(i);
						a.animate(elapsedTime);
						if (a.getX() + a.slikaX * 2 <= 0
								|| a.getX() - a.slikaX >= mWidth
								|| a.getY() + a.slikaY * 2 <= 0
								|| a.getY() - a.slikaY >= mHeight)
						{
							mElements.remove(i);
						}
						colision(a, i);
					}
				}
				tank.animate(elapsedTime);
				if (poljeMetki.isEmpty() == false)
				{
					for (int i = 0; i < poljeMetki.size(); i++)
					{
						Element a = poljeMetki.get(i);
						a.animate(elapsedTime);
						if (a.mY <= 10 || a.mX < 0 - 10 || a.mX > mWidth + 10)
							poljeMetki.remove(i);
					}
				}
				if (tank.getX() > mWidth - (tank.slikaX))
					tank.mX = mWidth - (tank.slikaX);
				if (tank.getX() < 0)
					tank.mX = 0;

				if (tank.getY() > mHeight - (tank.slikaY + tank.slikaY / 2))
					tank.mY = mHeight - (tank.slikaY + tank.slikaY / 2);
				if (tank.getY() < 0)
					tank.mY = 0;
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

	}

	public void colision(Element x, int i)
	{

		try
		{
			if ((Math.abs(x.getY() - tank.getY() - 10) < 70.0)
					&& (Math.abs(x.getX() - (tank.getX() + 10)) < 40.0))

			{
				mElements.remove(i);
				tocke += mElements.get(i).getPoints();
				healthBar -= 50;
				Tutorial2D.vibriraj = true;
			}
			if (health != null)
			{
				if ((Math.abs(health.getY() - tank.getY() - 10) < 70.0)
						&& (Math.abs(health.getX() - (tank.getX() + 10)) < 40.0))
				{
					if (healthBar < 200)
					{
						healthBar += 50;
					}
					health = null;

				}
			}
			if (ammo != null)
			{
				if ((Math.abs(ammo.getY() + ammo.slikaY - tank.getY() - 10) < 70.0)
						&& (Math.abs(ammo.getX() + ammo.slikaX
								- (tank.getX() + 10)) < 40.0))
				{
					bonusMetki = true;
					ammo = null;
					metkiOn = System.currentTimeMillis();

				}
			}
			for (int y = 0; y < poljeMetki.size(); y++)
			{
				Element a = poljeMetki.get(y);
				if ((Math.abs(x.getY() - a.getY()) < 50.0)
						&& (Math.abs(x.getX() - (a.getX())) < 20.0))
				{
					Random m = new Random();
					int z = m.nextInt(100) + 1;
					if (z % 20 == 0)
					{
						if (health == null)
							health = new Element(getResources(),
									(int) (mElements.get(i).mX + mElements
											.get(i).slikaX / 2),
									(int) (mElements.get(i).mY + mElements
											.get(i).slikaY / 2), true);
					}
					Random m1 = new Random();
					int z1 = m1.nextInt(50) + 1;
					if (z1 % 15 == 0)
					{
						if (ammo == null)
							ammo = new Element(getResources(),
									(int) (mElements.get(i).mX + mElements
											.get(i).slikaX / 2),
									(int) (mElements.get(i).mY + mElements
											.get(i).slikaY / 2), false);
					}
					tocke += mElements.get(i).getPoints();

					mElements.remove(i);
					poljeMetki.remove(y);
				}

			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public  boolean finish()
	{
		if (Panel.konec)
		{
			rezultat a = new rezultat(dobiTocke(), "test");
			app2.add(a);
			app2.dobi(a);
			Intent moj2 = new Intent(Context2, StevecListActivity.class);
			Context2.startActivity(moj2);
			((Activity) Context2).finish();
			Panel.mElements.clear();
			Panel.poljeMetki.clear();
			Panel.konec = false;
			return false;
		}
		return true;
	}
}
