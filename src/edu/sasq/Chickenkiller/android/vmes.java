package edu.sasq.Chickenkiller.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class vmes extends Activity{

	EditText uredi;
	ApplicationExample app;
	@Override
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.vmes);
	        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			app = (ApplicationExample) this.getApplication();
	        uredi = (EditText) findViewById(R.id.editText1);
	        uredi.setText(app.ime_privzeto);
	 }
	
	public void btnbegin(View v)
	{
		if(uredi.getText().toString()==" ")
		{
			Toast.makeText(this, "Vnesi ime", Toast.LENGTH_SHORT);
		}
		else
		{
			Tutorial2D.player_name=uredi.getText().toString();
		Intent start= new Intent(this,Tutorial2D.class);
		this.startActivity(start);
		}
		finish();
	}
}
