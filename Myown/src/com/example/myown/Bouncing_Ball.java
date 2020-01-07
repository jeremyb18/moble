package com.example.myown;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Bouncing_Ball {
	
	private int xVelocity = 10;
	private int yVelocity = 5;
	private int gravity = 0;
	private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
	private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
	private static int size = 10;
	private int x;
	private int y;
	public Bouncing_Ball( int x1, int y1) {
		 x = x1;
	     y = y1;
	}
	 public void draw(Canvas canvas) {
		 //canvas.drawColor(Color.WHITE);           
	        Paint paint = new Paint();  
	       // Paint paint2 = new Paint(); 
	        paint.setColor(Color.rgb(250, 0, 0));
	       // paint2.setColor(Color.rgb(0, 0, 0));  
	        canvas.drawCircle(x, y, size, paint); 
	       // canvas.drawText(y + "y", 200, 200, paint2);
	      //  canvas.drawText(screenHeight - 200+ "n", 200, 220, paint2);
	      //  canvas.drawLine(0, screenHeight - 200 + size, screenWidth, screenHeight - 200 + size, paint2);
	    }
	 
	 public void update() {
		 yVelocity += gravity;
		    x += xVelocity;
		    y += yVelocity;
		    if ( (screenWidth - size) < x || size > x ) {
		        xVelocity = xVelocity * -1;
		    }
		    if ((screenHeight - size)  < y ||  size > y) {
		        yVelocity = yVelocity * -1;
		    }

		}
}
