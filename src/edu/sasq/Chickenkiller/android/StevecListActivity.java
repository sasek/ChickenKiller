package edu.sasq.Chickenkiller.android;

import android.app.ListActivity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
//Step 4.6
public class StevecListActivity extends ListActivity/* implements OnItemClickListener  */{
	ApplicationExample app;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stevec_list_activity);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		app = (ApplicationExample) getApplication();
		setListAdapter(app.rezultati);
		//this.getListView().setOnItemClickListener(this);

	}
	public void izhod(View v)
	{
		finish();
	}
	/*
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
		Toast.makeText(this, "Pritisnili ste:"+app.rezultati.getItem(position).getPoints(), Toast.LENGTH_LONG).show();
		
	}*/
}
