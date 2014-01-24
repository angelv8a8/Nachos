package com.ccvii.nachos;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TableLayout;

//import com.ccvii.nachos.R;
//import com.ccvii.nachos.util.SystemUiHider;

public class FullscreenActivity extends Activity implements OnClickListener{
	

	
	private Handler frame = new Handler();
	private static final int FRAME_RATE = 20;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/*Handler h = new Handler();
        //We can't initialize the graphics immediately because the layout manager
        //needs to run first, thus call back in a sec.
        h.postDelayed(new Runnable() {
			@Override
			public void run() {
				initGfx();
			}
        }, 1000);
		*/
		
		setContentView(R.layout.activity_fullscreen);
		
		TableLayout menu = (TableLayout)View.inflate(this, R.layout.menu_principal, null);
		
		
		FrameLayout layout = (FrameLayout)findViewById(R.id.activity_fullscreen );
		
		layout.addView(menu);


	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		// Trigger the initial hide() shortly after the activity has been
		// created, to briefly hint to the user that UI controls
		// are available.
	}

	

	 synchronized public void initGfx() {
	    	((GameBoardEspacio)findViewById(R.id.the_canvas)).resetStarField();
	    	//It's a good idea to remove any existing callbacks to keep
	    	//them from inadvertently stacking up.
	    	frame.removeCallbacks(frameUpdate);
	    	frame.postDelayed(frameUpdate, FRAME_RATE);
	    }

	
	 @Override
	   synchronized public void onClick(View v) {
		   initGfx();
	   }
	   
	   
	   
	  private Runnable frameUpdate = new Runnable() {
		  
		  

		@Override
		synchronized public void run() {
			frame.removeCallbacks(frameUpdate);
			//make any updates to on screen objects here
			//then invoke the on draw by invalidating the canvas
			//((GameBoardEspacio)findViewById(R.id.the_canvas)).invalidate();
			frame.postDelayed(frameUpdate, FRAME_RATE);
		}
		
		   
	   };
	   
	   private class menuActions implements OnClickListener
	   {

		@Override
		public void onClick(View v) {

				if(v.getId() == R.id.btn_iniciar)
				{
					
				}
			
		}
	   }
	   }
	
	
}
