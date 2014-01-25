package com.ccvii.nachos;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;


public class GameBoardTierra extends View{
	
	private Paint p;
	private List<Point> starField = null;
	private boolean debug = true;
	int divisor = 0;
	private List<Disparo> disparos = new ArrayList<Disparo>();
	public int x = 900;
	public int y = 550;
	
	private Bitmap bmp = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher);
	
	private SparseArray<PointF> mActivePointers = new SparseArray<PointF>();
	
	
	synchronized public void resetStarField() {
		starField = null;
	}
	
		
	
	public GameBoardTierra(Context context, AttributeSet aSet) {
		super(context, aSet);
		
		p = new Paint();
		this.x = 500;
		this.y = 200;
		this.divisor = 20;
		
	}
	
	private void initializeTiera(int maxX, int maxY) {
		starField = new ArrayList<Point>();
		
	}
	
	@Override
	synchronized public void onDraw(Canvas canvas) {
		
		//create a black canvas
		p.setColor(Color.BLACK);
		p.setAlpha(255);
	    p.setStrokeWidth(1);
		canvas.drawRect(0, 0, getWidth(), getHeight(), p);
		
		//initialize the starfield if needed
		if (starField==null) {
			//initializeStars(canvas.getWidth(), canvas.getHeight());
		}
		//draw the stars
		p.setColor(Color.CYAN);
		//p.setAlpha(starAlpha+=starFade);
		//fade them in and out
		/*if (starAlpha>=252 || starAlpha <=80) 
			starFade=starFade*-1;*/
		p.setStrokeWidth(5);
		
		
		canvas.drawBitmap(bmp,x,y,p);
		
		
		
		
		if(debug)
		{
			p.setTextSize(40);
			canvas.drawText("X:"+this.x+",Y" + this.y, this.x, this.y, p);
		}
		
		for(int e = 0; e< disparos.size(); e++)
		{
			canvas.drawCircle(disparos.get(e).x, disparos.get(e).y, 5,p);
			
			disparos.get(e).x -= disparos.get(e).moveX;
			disparos.get(e).y += disparos.get(e).moveY;
		}
		
		
	}
	
	@Override
	  public boolean onTouchEvent(MotionEvent event) {
	    
	    
	 // get pointer index from the event object
	    int pointerIndex = event.getActionIndex();

	    // get pointer ID
	    int pointerId = event.getPointerId(pointerIndex);

	    // get masked (not specific to a pointer) action
	    int maskedAction = event.getActionMasked();

	    switch (maskedAction) {

	    case MotionEvent.ACTION_DOWN:
	    case MotionEvent.ACTION_POINTER_DOWN: {

	    	Log.i(GameBoardTierra.class.toString() , x + " "+ y);
	    }
	    case MotionEvent.ACTION_MOVE: { // a pointer was moved
	    	
	    	
	    	
	     if(event.getX() < 200)
	    	 x -= 5;
	     else
	    	 x += 5;
	      
	     
	      break;
	    }
	    case MotionEvent.ACTION_UP:
	    case MotionEvent.ACTION_POINTER_UP:
	    case MotionEvent.ACTION_CANCEL: {
	   
	      break;
	    }
	    }

	    //CalculateMidBallPosition();
	    return true;
	  }
	
	
	
}
