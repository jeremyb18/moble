package com.example.ch20exe2;




import android.os.Bundle;
import android.app.Activity;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.Intent;
public class Second extends Activity implements OnClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		Button myButton1 = (Button)findViewById(R.id.button1);
		myButton1.setOnClickListener(this);
		
		
		Intent startIntent = getIntent();
		Bundle bun = startIntent.getExtras();
		String value = bun.getString("value");
		TextView tv = (TextView)findViewById(R.id.textView1);
		tv.setText("Hello " + value);
		
		Intent resultIntent = new Intent();
		resultIntent.putExtra("name", value);
		setResult(RESULT_OK,resultIntent);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(id == R.id.button1)
		{
			
			Intent  change_to_first = new Intent(Second.this,Main.class);
			startActivity(change_to_first);
			
		}
		
	}
		
	}

	
	
	

