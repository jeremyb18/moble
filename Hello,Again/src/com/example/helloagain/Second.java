package com.example.helloagain;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Second extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		
		Intent startIntent = getIntent();
		Bundle bun = startIntent.getExtras();
		String value = bun.getString("value");
		Long time = bun.getLong("value");
		int num = bun.getInt("number");
		TextView tv = (TextView)findViewById(R.id.textView1);
		if(num == 1)
		{
			tv.setText("Your device is running Android version: " + value);
		}
		if(num == 2)
		{
			tv.setText("The emulator has been running for:             " + time + " milliseconds");
		}
		
	}
	
}
