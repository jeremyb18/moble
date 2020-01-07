package teencoder.androidprogramming;



import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Snooze extends Activity implements OnClickListener{
	String alarmName;
	String alarmDesc;
	public void onCreate(Bundle savedInstanceState)
	   {
	     super.onCreate(savedInstanceState);
	     setContentView(R.layout.notify);
	     Button ButtonSnooze = (Button)findViewById(R.id.button1);
	     ButtonSnooze.setOnClickListener(this);
	     Intent myIntent = getIntent();
	     Bundle bun = myIntent.getExtras();
	     alarmName = bun.getString("name");
	    		 alarmDesc = bun.getString("desc");
	   }
	@Override
	public void onClick(View v) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(c.getTimeInMillis() + 600000);
		AlarmKeeper snoozeAlarm = new AlarmKeeper();
		snoozeAlarm.alarmName = alarmName;
		snoozeAlarm.alarmDesc = alarmDesc;
		snoozeAlarm.alarmDay    = c.get(Calendar.DAY_OF_MONTH);
		snoozeAlarm.alarmMonth  = c.get(Calendar.MONTH);
		snoozeAlarm.alarmYear   = c.get(Calendar.YEAR);
		snoozeAlarm.alarmHour   = c.get(Calendar.HOUR_OF_DAY);
		snoozeAlarm.alarmMinute = c.get(Calendar.MINUTE);
		snoozeAlarm.setAlarm(Snooze.this);
		finish();
	}
	
	
}
