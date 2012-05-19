package edu.sasq.Chickenkiller.android;

import edu.sasq.Chickenkiller.android.ApplicationExample;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Window;
import android.view.WindowManager;

public class Tutorial2D extends Activity
	{

		private SensorManager sSensorManager;
		
		public ApplicationExample app2;
		public static String player_name = "";
		Panel app;
		public static Context ab;
	//	public static boolean cancel;
	//	public static boolean arry;
		public static boolean hitri;
		public static boolean pocasni;
		public static boolean yspeed;
		public static boolean strelaj;
		public static boolean vibriraj;
		public boolean koncaj;
		public static Vibrator v;
		public static Thread nitkica;
		public static String timer = " ";
		private SensorEventListener sSensorListener = new SensorEventListener()
			{

				public void onSensorChanged(SensorEvent event)
					{
						float cy = event.values[1];
						float cx = event.values[2];
						
						if (cy > 15)
							Panel.sensorY = -3;
						else if (cy < -15)
							Panel.sensorY = 3;
						else
							Panel.sensorY = 0;
						if (cx <= -5 && cx > -20)
							Panel.senzor = 2;
						else if (cx >= 5 && cx < 20)
							Panel.senzor = -2;
						else if (cx >= 20 && cx <= 35)
							Panel.senzor = -3;
						else if (cx > 35 && cx <= 90)
							Panel.senzor = -4;
						else if (cx <= -20 && cx > -35)
							Panel.senzor = 3;
						else if (cx <= -35 && cx > -90)
							Panel.senzor = 4;
						else
							Panel.senzor = 0;

						if (vibriraj)
							{
								v.vibrate(200);
								vibriraj = false;
							}
					}

				public void onAccuracyChanged(Sensor sensor, int accuracy)
					{
					}
			};

		@Override
		public void onCreate(Bundle savedInstanceState)
			{
				super.onCreate(savedInstanceState);
				requestWindowFeature(Window.FEATURE_NO_TITLE);
				getWindow().addFlags(
						WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
				koncaj = false;
				this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				sSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
				ab = this;
				app2 = (ApplicationExample) getApplication();
				hitri = false;
				pocasni = false;
				strelaj = false;
				Panel.konec = false;
				vibriraj = false;
				v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
				setContentView(new Panel(this, app2));
			}

		public static String getTimer()
			{

				return timer;
			}

		@SuppressWarnings("deprecation")
		@Override
		protected void onResume()
			{
				super.onResume();
				sSensorManager.registerListener(
						sSensorListener,
						sSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION
								| Sensor.TYPE_ACCELEROMETER),
						SensorManager.SENSOR_DELAY_GAME);
			}


		protected void onStop()
			{
			super.onStop();
				sSensorManager.unregisterListener(sSensorListener);
				
			}


		protected void onDestroy()
			{
			super.onDestroy();
				sSensorManager.unregisterListener(sSensorListener);
				koncaj = true;
				
			}
		
		protected void onPause()
		{
			super.onPause();
			sSensorManager.unregisterListener(sSensorListener);
			
		}

	}
