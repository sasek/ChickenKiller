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
import android.view.Window;

public class Tutorial2D extends Activity {
    /** Called when the activity is first created. */
	//private static final int TEST_LIST_ACTIVITY_ID = 2;  //Step 4.12
	public ApplicationExample app2;
	public static String player_name="";
	Panel  app;
	Context ab;
	public static boolean cancel;
	public static boolean hitri;
	public static boolean pocasni;
	public static boolean yspeed;
	public static  String timer =" ";
	public  Odstevanje stej= new Odstevanje(30000,1000);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new Panel(this));
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        stej.start();
        cancel=false;
        ab=this;
        app2 = (ApplicationExample) getApplication(); //Step 4.4
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
		rezultat a= new rezultat(app.dobiTocke(),player_name);
		app2.add(a);
		Intent moj2=new Intent(ab,StevecListActivity.class);
		ab.startActivity(moj2);
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
			if(cancel)
			{
				stej.onTick(0);
				stej.cancel();
			}
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

