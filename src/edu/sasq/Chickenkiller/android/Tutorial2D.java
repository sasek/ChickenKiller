package edu.sasq.Chickenkiller.android;


import java.util.Random;

import android.R.string;
import android.app.Activity;
import android.app.Application;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;

public class Tutorial2D extends Activity {
    /** Called when the activity is first created. */
	Panel  app;
	public static boolean hitri;
	public static boolean pocasni;
	public static boolean yspeed;
	public static  String timer =" ";
	Odstevanje stej= new Odstevanje(120000,1000);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new Panel(this));
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        stej.start();
        Panel.mHeight=100;
        Panel.mWidth=40;
        hitri=false;
        pocasni=false;
        
    }
    public static  String getTimer()
    {
    	return timer;
    }

public class Odstevanje extends CountDownTimer{

	public Odstevanje(long millisInFuture, long countDownInterval) {
	super(millisInFuture, countDownInterval);
	}

	@Override
	public void onFinish() {
		finish();
	}

	@Override
	public void onTick(long millisUntilFinished) {
		long s=0;
		int min=0;
		int visina= (int)Panel.mHeight;
		int sirina= (int)Panel.mWidth;
		if(millisUntilFinished%2==0)
		{
			hitri=true;
			
			/*
			Random rand= new Random();
			int rnd= rand.nextInt(visina-80)+80;
		 Panel.mElements.add(new Element(getResources(),(int)sirina,(int)rnd,30,-3,0));
		
		*/}
		else
		{
			hitri=false;
			
		}
		if(millisUntilFinished%2==0)
		{
			yspeed=true;
		}
		else
			yspeed=false;
		
		pocasni=true;
		/*	
			Random rand= new Random();
			int rnd= rand.nextInt(visina-80)+80;
		 Panel.mElements.add(new Element(getResources(),0,(int)rnd,10));
		*/
		if(millisUntilFinished/60000==1)
		{
		min=1;
			s=(millisUntilFinished/1000)-60;
		}
		else
		{
			s=millisUntilFinished/1000;
			min=0;
		}
		
			if(s>=10)
			timer=min+":"+s;
			else
			timer=min+":0"+s;

	}

	}
}

