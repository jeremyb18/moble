package com.example.myown;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;





public class GameView extends SurfaceView implements SurfaceHolder.Callback{

	
	private MainThread thread;
	//private CharacterSprite characterSprite;
	private Bouncing_Ball bouncingball;
	private Bouncing_Ball bouncingball1;
	
	public GameView(Context context) {
	    super(context);
	    getHolder().addCallback(this);
	    thread = new MainThread(getHolder(), this);
	    setFocusable(true);
	    
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		thread.setRunning(true);
	    thread.start();
	    bouncingball = new Bouncing_Ball(100,100);
	    bouncingball1 = new Bouncing_Ball(500,500);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		 boolean retry = true;   
		    while (retry) {       
		        try {           
		            thread.setRunning(false);           
		            thread.join();              
		        } catch (InterruptedException e) {       
		            e.printStackTrace();   
		        }   
		        retry = false;
		    }
	}
	public void update() {
		bouncingball.update();
		bouncingball1.update();
	}
	@Override
	public void draw(Canvas canvas) {         
	    super.draw(canvas);       
	    if (canvas != null) {           
	         bouncingball.draw(canvas);    
	         bouncingball1.draw(canvas);    
	    }   
	}
}
