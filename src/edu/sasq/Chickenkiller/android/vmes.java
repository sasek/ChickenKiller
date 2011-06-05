package edu.sasq.Chickenkiller.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class vmes extends Activity{

	EditText uredi;
	@Override
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.vmes);
	        uredi = (EditText) findViewById(R.id.editText1);
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
