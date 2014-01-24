package com.ccvii.nachos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.MotionEvent;


public class GameBoardTierra extends View{
	
	private Paint p;
	private List<Point> starField = null;
	
	private int moveX = 0;
	private int moveY = 0;
	
	private boolean debug = true;
	
	int divisor = 0;
	
	private List<Disparo> disparos = new ArrayList<Disparo>();
	
	
	public int x = 0;
	public int y = 0;
	
	 private SparseArray<PointF> mActivePointers = new SparseArray<PointF>();
	
	private static final int NUM_OF_STARS = 25;
	
	synchronized public void resetStarField() {
		starField = null;
	}
	
	public GameBoardTierra(Context context, AttributeSet aSet) {
		super(context, aSet);
		//it's best not to create any new objects in the on draw
		//initialize them as class variables here
		p = new Paint();
		
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
		for (int i=0; i<NUM_OF_STARS; i++) {
			canvas.drawPoint(starField.get(i).x--, starField.get(i).y, p);

			if(starField.get(i).x<0)
			{
				starField.get(i).x  = this.getWidth();
			}
		}
		
		for(int e = 0; e< mActivePointers.size(); e++)
		{
			canvas.drawCircle(mActivePointers.get(e).x, mActivePointers.get(e).y, 30,p);
		}
		
		//
		p.setColor(Color.RED);
		p.setStrokeWidth(5);
		
		if(mActivePointers.size()==2)
		{
			canvas.drawLine(mActivePointers.get(0).x, mActivePointers.get(0).y, this.x, this.y, p);
			canvas.drawLine(mActivePointers.get(1).x, mActivePointers.get(1).y, this.x, this.y, p);
		}
		canvas.drawCircle(this.x, this.y, 30,p);
		//canvas.drawBitmap(new Bitmap(), matrix, paint);
		
		if(debug)
		{
			p.setTextSize(20);
			canvas.drawText("MoveX:"+this.moveX+",MoveY" + this.moveY, this.x, this.y, p);
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
	    float eventX = event.getX();
	    float eventY = event.getY();
	    
	    this.x = (int)eventX;
	    this.y = (int)eventY;
	    
	    
	 // get pointer index from the event object
	    int pointerIndex = event.getActionIndex();

	    // get pointer ID
	    int pointerId = event.getPointerId(pointerIndex);

	    // get masked (not specific to a pointer) action
	    int maskedAction = event.getActionMasked();

	    switch (maskedAction) {

	    case MotionEvent.ACTION_DOWN:
	    case MotionEvent.ACTION_POINTER_DOWN: {
	      // We have a new pointer. Lets add it to the list of pointers

	    	
	    	if(mActivePointers.size() <2)
	    	{
	    		PointF f = new PointF();
		      f.x = event.getX(pointerIndex);
		      f.y = event.getY(pointerIndex);
		      mActivePointers.put(pointerId, f);
	    	}
	    	else
	    	{
	    		
	    		//AgregarDisparo();
	    	}
	      break;
	    }
	    case MotionEvent.ACTION_MOVE: { // a pointer was moved
	      for (int size = event.getPointerCount(), i = 0; i < size; i++) {
	        PointF point = mActivePointers.get(event.getPointerId(i));
	        if (point != null) {
	          point.x = event.getX(i);
	          point.y = event.getY(i);
	        }
	      }
	      
	      if(mActivePointers.size()==2)
			{	
				this.moveY = (int)(mActivePointers.get(0).x -  mActivePointers.get(1).x)/this.divisor;
				this.moveX = (int) (mActivePointers.get(0).y -  mActivePointers.get(1).y)/this.divisor;	
			}
	      break;
	    }
	    case MotionEvent.ACTION_UP:
	    case MotionEvent.ACTION_POINTER_UP:
	    case MotionEvent.ACTION_CANCEL: {
	    	/*if(pointerId != 0)
	    			mActivePointers.remove(pointerId);
	    	else if(mActivePointers.size() ==1)	
	    		mActivePointers.remove(0);*/
	      break;
	    }
	    }

	    //CalculateMidBallPosition();
	    return true;
	  }
	
	
	
}