package edu.sasq.Chickenkiller.android;



import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class Game extends Activity {
    static TextView count;
    public int bullet;
	Odstevanje stej= new Odstevanje(120000,1000);
	
	
	
	@Override
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        bullet=5;
	        setContentView(R.layout.game);
	        stej.start();
	        count=(TextView) findViewById(R.id.txtCas);
	        
	        // Touch koordinate
	        
	        final TextView textView = (TextView)findViewById(R.id.textView1); 
	        // this is the view on which you will listen for touch events 
	        final View touchView = findViewById(R.id.view1); 
	        touchView.setOnTouchListener(new View.OnTouchListener() { 
	        @Override 
	        public boolean onTouch(View v, MotionEvent event) {
	        	if(bullet>0)
	        	bullet--;
	        	else
	        		
	        	{// Se ne poka�e...
	        		//Toast.makeText(Game.this, "Reload!!",Toast.LENGTH_SHORT);
	        		bullet=5;
	        	}
	        	textView.setText(""+bullet);
	        /*textView.setText("Touch coordinates : " + 
	        String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()));*/ 
	        return true; 
	        } 
	        }); 
	        
	 }
	


public class Odstevanje extends CountDownTimer{

	public Odstevanje(long millisInFuture, long countDownInterval) {
	super(millisInFuture, countDownInterval);
	}

	@Override
	public void onFinish() {
		finish();
	}

	@Override
	public void onTick(long millisUntilFinished) {
		long s=0;
		int min=0;
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
		
			
	Game.count.setText(min+":"+s);

	}

	}

}

