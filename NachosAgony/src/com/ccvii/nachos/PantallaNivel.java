package com.ccvii.nachos;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.ccvii.nachos.util.SystemUiHider;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class PantallaNivel extends Activity implements OnClickListener{ 
	
	private Handler frame = new Handler();
	private static final int FRAME_RATE = 20;
	
	GameBoardEspacio pantallaEspacio = null;
	GameBoardTierra pantallaTierra = null;
	
	int nivel = 1;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_pantalla_nivel);

		Handler h = new Handler();
        //We can't initialize the graphics immediately because the layout manager
        //needs to run first, thus call back in a sec.
		
		LinearLayout contenedor = (LinearLayout)findViewById(R.id.fullscreen_content_controls);
		
		   if(nivel == 1)
	        {
	        	pantallaTierra = new GameBoardTierra(this, null);	
	        	pantallaTierra.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	        	
	        	contenedor.addView(pantallaTierra);
	        }
	        else
	        {
	        	pantallaEspacio= new GameBoardEspacio(this, null);
	        	pantallaEspacio.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	        	contenedor.addView(pantallaEspacio);
	        }
	        
		   
		   
        h.postDelayed(new Runnable() {
			@Override
			public void run() {
				initGfx();
			}
        }, 1000);
        
      
     
	
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		// Trigger the initial hide() shortly after the activity has been
		// created, to briefly hint to the user that UI controls
		// are available.
	}


	Handler mHideHandler = new Handler();
	

	 synchronized public void initGfx() {
		 
		  if(nivel == 1)
	        {
			  
	        }
		  else
		  {
	    	pantallaEspacio.resetStarField();
		  }
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
			
			 if(nivel == 1)
			 {
				 pantallaTierra.invalidate();
			 }else
			 {
				 pantallaEspacio.invalidate();
			 }
			frame.postDelayed(frameUpdate, FRAME_RATE);
		}
		
		   
	   };
}
