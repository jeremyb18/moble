package com.example.list_box;

import android.os.Bundle;
import android.app.Activity;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.Intent;

public class Main extends Activity implements OnClickListener{
	String[] pets = {"Dog", "Fish", "Spider", "Snake", "Parrot", "Chicken"};
	String strSelected = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button myButton1 = (Button)findViewById(R.id.button1);
		myButton1.setOnClickListener(this);
		ArrayAdapter<String> myAdapter = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item, pets);
		Spinner spn = (Spinner)findViewById(R.id.spinner1);
		
		spn.setAdapter(myAdapter);
		myAdapter.setDropDownViewResource         
       (android.R.layout.simple_spinner_dropdown_item);
		spn.setSelection(2);
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
			Spinner spn = (Spinner)findViewById(R.id.spinner1);
			int pos = spn.getSelectedItemPosition();
			 strSelected = spn.getItemAtPosition(pos).toString();
			
			startActivityForResult(0);
			
		}
		
	}
	public void startActivityForResult(int requestCode)
	{
		
		
		
		Intent  change_to_second = new Intent(Main.this,petList.class);
		
		startActivityForResult(change_to_second,0);
		
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		Bundle bun = data.getExtras();
		String value = bun.getString("pet");
		TextView tv = (TextView)findViewById(R.id.textView1);
		tv.setText("Received name back: " + value + "  |  " + strSelected);
		
	}
}
