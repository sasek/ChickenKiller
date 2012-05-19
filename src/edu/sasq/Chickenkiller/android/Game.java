package edu.sasq.Chickenkiller.android;



import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Game extends Activity {
    static TextView count;
    public int bullet;
//	Odstevanje stej= new Odstevanje(120000,1000);
	
	
	
	@Override
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        bullet=5;
	        setContentView(R.layout.game);
	//       stej.start();
	        //count=(TextView) findViewById(R.id.txtCas);
	}
}
	        
	        // Touch koordinate
	    /*    
	        final TextView textView = (TextView)findViewById(R.id.textView1); 
	        // this is the view on which you will listen for touch events 
	        final View touchView = findViewById(R.id.view1); 
	        final Context con = this.getApplicationContext();
	        
	        touchView.setOnTouchListener(new View.OnTouchListener() { 
	        @Override 
	        public boolean onTouch(View v, MotionEvent event) {
	        	if(event.getAction()==event.ACTION_DOWN)
	        	{/*
	        		Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gabi48);
	        		Canvas c = new Canvas(myBitmap);
					c.drawBitmap(myBitmap, 100, 100, null);
	        		DrawView ab= new DrawView(con);
	        		Canvas canvas = new Canvas();
	        		ab.draw(canvas);
	        	if(bullet>0)
	        	bullet--;
	        	
	        	else
	        		
	        		{
	        			bullet=5;
	        		}
	        	}
	        	textView.setText(""+bullet);
	        /*textView.setText("Touch coordinates : " + 
	        String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()));*/ 
	        /*	textView.setText("Touch coordinates : " + 
	        	        String.valueOf(event.getHistoricalX(0)) + "x" + String.valueOf(event.getHistoricalY(0)));
	        	return true; 
	        } 
	        }); 
	        
	 }
	*/
	        /*
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
		
			if(s>=10)
				Game.count.setText(min+":"+s);
			else
				Game.count.setText(min+":0"+s);

	}

	}

}*/

