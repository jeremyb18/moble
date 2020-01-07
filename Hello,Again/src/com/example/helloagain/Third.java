package com.example.helloagain;

import android.os.Bundle;
import android.app.Activity;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.Intent;

public class Third extends Activity implements OnClickListener{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third);
		
		Button myButton1 = (Button)findViewById(R.id.button1);
		myButton1.setOnClickListener(this);
		Button myButton2 = (Button)findViewById(R.id.button2);
		myButton2.setOnClickListener(this);
		
		
		
		
	}
	
	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(id == R.id.button1)
		{
		String value = "Red";
		Intent resultIntent = new Intent();
		resultIntent.putExtra("name", value);
		setResult(RESULT_OK,resultIntent);
		}
		if(id == R.id.button2)
		{
			String value = "Blue";
			Intent resultIntent = new Intent();
			resultIntent.putExtra("name", value);
			setResult(RESULT_OK,resultIntent);
		}
		
	}

}
