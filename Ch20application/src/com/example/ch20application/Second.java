package com.example.ch20application;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.Intent;
public class Second extends Activity implements OnClickListener {

		public void onCreate(Bundle savedInstantState) {
			super.onCreate(savedInstantState);
			setContentView(R.layout.second);
			Button myButton1 = (Button)findViewById(R.id.button1);
			myButton1.setOnClickListener(this);
			
			Intent startIntent = getIntent();
			Bundle bun = startIntent.getExtras();
			String value = bun.getString("userName");
			TextView tv = (TextView)findViewById(R.id.textView1);
			tv.setText("Hello" + value);
		}

		@Override
		public void onClick(View v) {
			int id = v.getId();
			if(id == R.id.button1)
			{
				Intent  change_to_main = new Intent(Second.this,Main.class);
				startActivity(change_to_main);
				
			}
			
		}
			
		
}
