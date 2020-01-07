package com.example.helloagain;

import android.os.Bundle;
import android.provider.Settings.System;
import android.app.Activity;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.Intent;

public class Main extends Activity  implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button myButton1 = (Button)findViewById(R.id.button1);
		myButton1.setOnClickListener(this);
		Button myButton2 = (Button)findViewById(R.id.button2);
		myButton2.setOnClickListener(this);
		Button myButton3 = (Button)findViewById(R.id.button3);
		myButton3.setOnClickListener(this);
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
		if(id == R.id.button1)
		{
			
			
				String myVersion = android.os.Build.VERSION.RELEASE;
				Intent  change_to_second = new Intent(Main.this,Second.class);
				change_to_second.putExtra("number", 1);
				change_to_second.putExtra("value", myVersion);
				
			startActivity(change_to_second);
				
		}
		if(id == R.id.button2)
		{
			
			long Etime = android.os.SystemClock.uptimeMillis();
			Intent  change_to_second = new Intent(Main.this,Second.class);
			change_to_second.putExtra("number", 2);
			change_to_second.putExtra("value", Etime);
			
		startActivity(change_to_second);
				
		}if(id == R.id.button3)
		{
			
			startActivityForResult(0);
				
		}
		
		
		
		
	}
	
	public void startActivityForResult(int requestCode)
	{

		
		Intent  change_to_third = new Intent(Main.this,Third.class);
		
		startActivityForResult(change_to_third,0);
	}
	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		Bundle bun = data.getExtras();
		String value = bun.getString("name");
		TextView tv = (TextView)findViewById(R.id.textView1);
		tv.setText("Your color is: " + value );
		
	}
}
