package com.example.ch20exe2;

import android.os.Bundle;
import android.app.Activity;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.Intent;

public class Main extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button myButton1 = (Button)findViewById(R.id.button1);
		myButton1.setOnClickListener(this);
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
			
			startActivityForResult(0);
			
			
		}
		
	}

	public void startActivityForResult(int requestCode)
	{
		
		EditText et = (EditText)findViewById(R.id.editText1);
		String value = et.getText().toString();
		Intent  change_to_second = new Intent(Main.this,Second.class);
		change_to_second.putExtra("value", value);
		startActivityForResult(change_to_second,0);
	}
	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		Bundle bun = data.getExtras();
		String value = bun.getString("name");
		TextView tv = (TextView)findViewById(R.id.textView1);
		tv.setText("Received name back: " + value );
		
	}
	
	
}
