package edu.sasq.Chickenkiller.android;


import java.util.Random;

import edu.sasq.Chickenkiller.android.ApplicationExample;
import edu.sasq.Chickenkiller.android.StevecListActivity;
import edu.sasq.Chickenkiller.android.rezultat;

import android.R.string;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.Window;

public class Tutorial2D extends Activity {
    /** Called when the activity is first created. */
	public ApplicationExample app2;
	public static String player_name="";
	Panel  app;
	Context ab;
	public static boolean cancel;
	public static boolean arry;
	public static boolean hitri;
	public static boolean pocasni;
	public static boolean yspeed;
	public static boolean strelaj;
	public static  String timer =" ";
	public static int velikostX;
	public static int velikostY;
	public final  Odstevanje stej= new Odstevanje(30000,1000);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new Panel(this));
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        stej.start();
        cancel=false;
        ab=this;
        arry=true;
        app2 = (ApplicationExample) getApplication(); //Step 4.4
        Panel.mHeight=dm.heightPixels;
        Panel.mWidth=dm.widthPixels;
        velikostX=dm.heightPixels;
        velikostY=dm.widthPixels;
       System.out.println("height :"+dm.heightPixels+" width"+dm.widthPixels);
        hitri=false;
        pocasni=false;
        strelaj=false;
        
    }
    public static  String getTimer()
    {
    	return timer;
    }

public class Odstevanje extends CountDownTimer{

	private boolean stop;
	public Odstevanje(long millisInFuture, long countDownInterval) {
	super(millisInFuture, countDownInterval);
	setStop(false);
	}

	@Override
	public void onFinish() {
		if(arry)
		{
		rezultat a= new rezultat(app.dobiTocke(),player_name);
		app2.add(a);
		app2.dobi(a);
		Intent moj2=new Intent(ab,StevecListActivity.class);
		ab.startActivity(moj2);
		finish();
		Panel.mElements.clear();
		Panel.poljeMetki.clear();
		}
		arry=false;
		
		
	}



	@Override
	public void onTick(long millisUntilFinished) {
		if(stop)
		{
			this.cancel();
		}
		long s=0;
		int min=0;			
		if(millisUntilFinished%2==0)
			strelaj=true;
		else
			strelaj=false;
		if(millisUntilFinished%2==0)
		{
			if(cancel)
			{
				stej.onTick(0);
				stej.cancel();
			}
			hitri=true;
			}
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

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public boolean isStop() {
		return stop;
	}

	}
}

