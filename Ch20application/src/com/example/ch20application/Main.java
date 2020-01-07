package com.example.ch20application;

import android.os.Bundle;
import android.app.Activity;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.Intent;
public class Main extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button myButton1 = (Button)findViewById(R.id.button1);
		myButton1.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(id == R.id.button1)
		{
			EditText et = (EditText)findViewById(R.id.editText1);
			String userName = et.getText().toString();
			Intent  change_to_second = new Intent(Main.this,Second.class);
			change_to_second.putExtra("userName", userName);
			startActivity(change_to_second);
			
		}
	}

}
