package edu.sasq.Chickenkiller.android;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import edu.sasq.Chickenkiller.android.ApplicationExample;
import edu.sasq.Chickenkiller.android.StevecListActivity;
import edu.sasq.Chickenkiller.android.rezultat;

import android.app.Activity;
import android.app.WallpaperInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PowerManager;
import android.os.Vibrator;
import android.view.Window;
import android.view.WindowManager;

public class Tutorial2D extends Activity {
	/** Called when the activity is first created. */
	private SensorManager sSensorManager;
	public ApplicationExample app2;
	public static String player_name = "";
	Panel app;
	Context ab;
	public static boolean cancel;
	public static boolean arry;
	public static boolean hitri;
	public static boolean pocasni;
	public static boolean yspeed;
	public static boolean strelaj;
	public static boolean vibriraj;
	public boolean koncaj;
	public static Vibrator v;
	public static Thread nitkica;
	public static String timer = " ";
	private SensorEventListener sSensorListener = new SensorEventListener() {

		@Override
		public void onSensorChanged(SensorEvent event) {
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
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	};
	public Odstevanje stej = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		stej = new Odstevanje(30000, 1000);
		koncaj = false;
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		sSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		// stej.start();
		cancel = false;
		ab = this;
		arry = true;
		app2 = (ApplicationExample) getApplication();
		hitri = false;
		pocasni = false;
		strelaj = false;
		Panel.konec = false;
		 vibriraj=false;
		 v= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		setContentView(new Panel(this));
		nitkica = new Thread(new Runnable() {

			@Override
			public void run() {
				while (arry) {
					if (Panel.konec) {
						rezultat a = new rezultat(app.dobiTocke(), player_name);
						app2.add(a);
						app2.dobi(a);
						Intent moj2 = new Intent(ab, StevecListActivity.class);
						ab.startActivity(moj2);
						finish();
						Panel.mElements.clear();
						Panel.poljeMetki.clear();
						arry = false;
					}
					if(vibriraj)
					{
						v.vibrate(200);
						vibriraj=false;
					}
					if (koncaj) {
						arry = false;
						Panel.mElements.clear();
						Panel.poljeMetki.clear();
					}
				}
			}
		});
		nitkica.start();
	}

	public static String getTimer() {

		return timer;
	}

	public class Odstevanje extends CountDownTimer {

		private boolean stop;

		public Odstevanje(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			setStop(false);
		}

		@Override
		public void onFinish() {
			/*
			 * if(arry) { //tocke(app.dobiTocke());
			 * 
			 * @SuppressWarnings("static-access") rezultat a= new
			 * rezultat(app.dobiTocke(),player_name); app2.add(a); app2.dobi(a);
			 * Intent moj2=new Intent(ab,StevecListActivity.class);
			 * ab.startActivity(moj2); finish(); Panel.mElements.clear();
			 * Panel.poljeMetki.clear(); } arry=false;
			 */
		}

		@Override
		public void onTick(long millisUntilFinished) {
			if (stop) {
				this.cancel();
			}
			long s = 0;
			int min = 0;
			if (millisUntilFinished % 2 == 0)
				strelaj = true;
			else
				strelaj = false;
			if (millisUntilFinished % 2 == 0) {
				if (cancel) {
					stej.setStop(true);
				}
				hitri = true;
			} else {
				hitri = false;

			}
			if (millisUntilFinished % 2 == 0) {
				yspeed = true;
			} else
				yspeed = false;

			pocasni = true;
			if (millisUntilFinished / 60000 == 1) {
				min = 1;
				s = (millisUntilFinished / 1000) - 60;
			} else {
				s = millisUntilFinished / 1000;
				min = 0;
			}

			if (s >= 10)
				timer = min + ":" + s;
			else
				timer = min + ":0" + s;

		}

		public void setStop(boolean stop) {
			this.stop = stop;
			System.out.println(stop + "timer");
		}

		public boolean isStop() {
			return stop;
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		sSensorManager.registerListener(
				sSensorListener,
				sSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION
						| Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	protected void onStop() {
		sSensorManager.unregisterListener(sSensorListener);
		super.onStop();
	}

	@Override
	protected void onDestroy() {

		koncaj = true;
		super.onDestroy();
	}

	private static final String SOAP_ACTION = "Kure";
	private static final String OPERATION_NAME = "tocke";
	private static final String WSDL_TARGET_NAMESPACE = "http://vreme.edu.sasq";
	private static final String SOAP_ADDRESS = "http://164.8.118.248:8080/Domaca/services/Kure?wsdl";

	public int tocke(int x) {

		SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,
				OPERATION_NAME);

		int izhod;

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = false;

		request.addProperty("p", x);
		envelope.setOutputSoapObject(request);

		HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);

		try {
			httpTransport.call(SOAP_ACTION, envelope);
			final Object response = envelope.getResponse();
			izhod = Integer.parseInt(response.toString()); // response.toString();
			return izhod;
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
		return (Integer) null;

	}

}
