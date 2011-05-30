package edu.sasq.Chickenkiller.android;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

public class mainmenu extends Activity {
    /** Called when the activity is first created. */
	public static final int GAME_ACTIVITY_ID = 1;
    @Override
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
    public void clickStart(View v)
    {
    	Intent igra=new Intent(this,Tutorial2D.class);
    	this.startActivity(igra);
    }
    public void clickQuit(View v)
    {
    	this.finish();
    	
    }
    public void clickScore(View v)
    {
    	
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
    
}