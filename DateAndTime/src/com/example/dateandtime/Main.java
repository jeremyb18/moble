package com.example.dateandtime;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;



import android.os.Bundle;
import android.app.*;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.*;
import android.view.View.*;
import android.widget.*;


import android.view.Menu;
import android.widget.DatePicker;
import android.widget.TextView;

public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button date = (Button)findViewById(R.id.button1);
		date.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DialogFragment newFragment = new MyDatePicker();
				newFragment.show(getFragmentManager(), "myDialog");
				
			}
		});
		Button time = (Button)findViewById(R.id.button2);
		time.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DialogFragment newFragment = new MyTimePicker();
				newFragment.show(getFragmentManager(), "myDialog");
				
			}
		});
	}
	
	public static class MyDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
		
		public Dialog onCreateDialog(Bundle savedInstanceState){
			// Use the current date as the default date in the picker
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);

			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this,
              year, month, day);
			}

		 public void onDateSet(DatePicker view, int year, int monthOfYear,
                 int dayOfMonth)
  {
     Main myActivity = (Main)getActivity();
     TextView myView = (TextView)myActivity.findViewById(R.id.textView1);
     myView.setText(year +"/"+ Integer.toString(monthOfYear + 1)
     +"/"+ dayOfMonth);
  }
}  // end of MyDatePicker
		
	public static class MyTimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
		public Dialog onCreateDialog(Bundle savedInstanceState){
			// Use the current time as the default values for the picker
			Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);

		
				return new TimePickerDialog(getActivity(), this, hour, minute,false);     
		}
		public void onTimeSet(TimePicker view, int hourOfDay, int minute)
		   {
		      Main myActivity = (Main)getActivity();
		      TextView myView
		                  = (TextView)myActivity.findViewById(R.id.textView2);
		      if(hourOfDay < 13) {
		      myView.setText(hourOfDay + ":" + minute + " am");
		      }else {
		    	  myView.setText(hourOfDay - 12 + ":" + minute + " pm");
		      }
		   }
		}
		
			@Override
		public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}




			

}
