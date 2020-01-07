package com.example.chapter27;
import android.net.*;
import android.os.Bundle;
import android.app.Activity;


import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.*;
import android.widget.*;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
public class Main extends Activity implements OnClickListener{
	Button webButton;
	Button dial;
	 NotificationManager nm;
	 Notification notify;
	 Toast myToast ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	//	Intent intent = getIntent();
	//	String action = intent.getAction();
		//if( action.contentEquals(Intent.ACTION_MAIN)){
			  myToast = Toast.makeText(getApplicationContext(),
                     "This is a Toast message!",
                     Toast.LENGTH_SHORT);
			 myToast.setGravity(android.view.Gravity.CENTER, 0, 0);
			 
			 
			 dial = (Button)findViewById(R.id.button1);
			 dial.setOnClickListener(this);
			 webButton = (Button)findViewById(R.id.button2);
			 webButton.setOnClickListener(this);
			 
			 Intent implicitIntent = new Intent(getApplicationContext(),
					 NotifyActivty.class);
			 PendingIntent pendIntent= PendingIntent.getActivity(getApplicationContext(),
                                         0, implicitIntent, 0);
			 
			 Notification.Builder builder = new Notification.Builder(
					 getApplicationContext());
					 builder.setSmallIcon(R.drawable.ic_launcher) ; 
					 builder.setTicker("Notify!");
					 builder.setContentTitle("You are Notified!");
					 builder.setContentText("This is the notification information");
					 builder.setContentIntent(pendIntent);
					 builder.setAutoCancel(false);

					 notify = builder.build();
					  nm = (NotificationManager)getSystemService(
                             Context.NOTIFICATION_SERVICE);
			 
			 
					
			 
		//}
	//	else if (action.equals(Intent.ACTION_VIEW))
	//	{
		   // we were launched by an implicit VIEW Intent
		//}
	//	else if (action.equals(Intent.ACTION_EDIT))
	//	{
		   // we were launched by an implicit EDIT Intent
	//	}
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(id == R.id.button1) {
			nm.notify(0, notify);
		}
		if(id == R.id.button2) {
			myToast.show();
		} 
		
	}

}
