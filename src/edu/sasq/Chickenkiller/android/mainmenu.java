package edu.sasq.Chickenkiller.android;


import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import edu.sasq.Chickenkiller.android.MenuPreferences;
import edu.sasq.Chickenkiller.android.R;
import edu.sasq.Chickenkiller.android.StevecListActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class mainmenu extends Activity {
	ApplicationExample app;
	private static final int TEST_LIST_ACTIVITY_ID = 2;
    /** Called when the activity is first created. */
	private Menu mMenu;  //ni nujno
	public static final int GAME_ACTIVITY_ID = 1;
	private static final int EXIT_DIALOG = 0;
	public static int velikostX;
	public static int velikostY;
    @Override
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
   //     ImageView ab= (ImageView)findViewById(R.id.imageView1);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        app = (ApplicationExample) getApplication();
       /* String slika=vreme();
        if(slika.contains("Partly")&&slika.contains("Cloudy"))
        {
        	ab.setImageResource(R.drawable.partlycloudy);
        }
        if(slika.contains("Clear"))
        	ab.setImageResource(R.drawable.clear);
        if(slika.contains("Chance")&&slika.contains("Rain")||slika.contains("Rain"))
        	ab.setImageResource(R.drawable.chancerain);
        if(slika.contains("Chance")&&slika.contains("Snow")||slika.contains("Snow"))
        	ab.setImageResource(R.drawable.chancesnow);*/
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Panel.mHeight=dm.heightPixels;
        Panel.mWidth=dm.widthPixels;
        
    }
    public boolean onCreateOptionsMenu(Menu menu) {

        mMenu = menu; //ni nujno

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu, mMenu);

        return true;

      }
      
      @Override

      public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

        case R.id.dialogTest:
        	this.finish();
         // showDialog(EXIT_DIALOG);

          return true;

        case R.id.itemSettings:
      	  Intent i = new Intent();
      	  i.setClass(this, MenuPreferences.class);
      	  startActivityForResult(i, R.id.itemSettings);
      	  return true;
        case R.id.rezultati:
      	// prikaz rezultatov
  			Intent moj2=new Intent(this,StevecListActivity.class);
  			this.startActivityForResult(moj2, TEST_LIST_ACTIVITY_ID);
  			return true;  	  

        default:// Generic catch all for all the other menu resources

          if (!item.hasSubMenu()) {

           // Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT)

           // .show();

            return true;

          }

          break;

        }

        return false;
      }
    public void clickStart(View v)
    {

    	Intent igra=new Intent(this,vmes.class);

    	this.startActivity(igra);
  
    }
    public void clickQuit(View v)
    {
    	this.finish();
  
    	
    }
    public void clickScore(View v)
    {
    	Intent rezult=new Intent(this,StevecListActivity.class);
    	this.startActivity(rezult);
    }
    public void onResume()
    {
    	super.onResume();
    }
    public void onStart()
    {
    	super.onStart();
    }
    public void onPause()
    {
    	super.onPause();
    }
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){

        // See which child activity is calling us back.

	if(resultCode==-1)
	{
		finish();
		
	}
	
}
 // Inegracijsko
    private static final String SOAP_ACTION = "Kure";
    private static final String OPERATION_NAME = "slika";
    private static final String WSDL_TARGET_NAMESPACE = "http://vreme.edu.sasq";
    private static final String SOAP_ADDRESS = "http://164.8.118.248:8080/Domaca/services/Kure?wsdl";  

    public String vreme(){
    	

    SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE
    ,OPERATION_NAME);

    String izhod;
               
    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
    envelope.dotNet = false;
              
    envelope.setOutputSoapObject(request);
               
               
    HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
                       
     try     
     {              
            httpTransport.call(SOAP_ACTION, envelope);              
            final Object response = envelope.getResponse();
            izhod=response.toString();
            return izhod;
     }
     catch (final Exception exception)
     {
          exception.printStackTrace();
     }
    return null;

    }
    
}