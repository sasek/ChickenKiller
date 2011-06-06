package edu.sasq.Chickenkiller.android;


import edu.sasq.Chickenkiller.android.MenuPreferences;
import edu.sasq.Chickenkiller.android.R;
import edu.sasq.Chickenkiller.android.StevecListActivity;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class mainmenu extends Activity {
	ApplicationExample app;
	private static final int TEST_LIST_ACTIVITY_ID = 2;
    /** Called when the activity is first created. */
	private Menu mMenu;  //ni nujno
	public static final int GAME_ACTIVITY_ID = 1;
	private static final int EXIT_DIALOG = 0;
    @Override
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        app = (ApplicationExample) getApplication();
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
    
}