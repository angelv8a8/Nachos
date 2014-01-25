package com.ccvii.nachos;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.Button;

//import com.ccvii.nachos.R;
//import com.ccvii.nachos.util.SystemUiHider;

public class FullscreenActivity extends Activity {
	

	

	private TableLayout menu = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		
		setContentView(R.layout.activity_fullscreen);
		
		menu = (TableLayout)View.inflate(this, R.layout.menu_principal, null);
		
		
		FrameLayout layout = (FrameLayout)findViewById(R.id.activity_fullscreen );
		
		
		menuActions acciones = new menuActions();
		Button btnIniciar= (Button)menu.findViewById(R.id.btn_iniciar);
		btnIniciar.setOnClickListener(acciones);
		
		layout.addView(menu);


	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		// Trigger the initial hide() shortly after the activity has been
		// created, to briefly hint to the user that UI controls
		// are available.
	}

	
	   private class menuActions implements OnClickListener
	   {

		@Override
		public void onClick(View v) {

				if(v.getId() == R.id.btn_iniciar)
				{
					Intent intent = new Intent(getApplicationContext(), PantallaNivel.class );
					startActivity(intent);
				}
			
		}
	   }

	
}
