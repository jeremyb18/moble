////////////////////////////////////////////////////////////////
//Copyright 2013, Homeschool Programming, Inc.
//
//This source code is for use by the students and teachers who 
//have purchased the corresponding TeenCoder or KidCoder product.
//It may not be transmitted to other parties for any reason
//without the written consent of Homeschool Programming, Inc.
//This source is provided as-is for educational purposes only.
//Homeschool Programming, Inc. makes no warranty and assumes
//no liability regarding the functionality of this program.
//
////////////////////////////////////////////////////////////////

/** TeenCoder: Android Programming
 * Chapter 10-11 - Reminder application
 * This class represents the code for the AlarmReceiver 
 * class for this application.
 * @author Homeschool Programming, Inc.
 * @version 2.0
*/

package teencoder.androidprogramming;



import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


// This class will handle the messages sent to this application when an
// alarm goes off
public class AlarmReceiver extends BroadcastReceiver 
{

  // This method is called when one of our alarms go off on the user's device
  public void onReceive(Context context, Intent intent) 
  {
    try 
    {  
       // Get the extras from the incoming Intent
       Bundle bundle = intent.getExtras();
       String name = bundle.getString("alarm_name");  // Retrieves the user-defined alarm name
       String desc = bundle.getString("alarm_desc");  // Retrieves the user-defined alarm description
       
       //***********************************************************************************************
       // The following code is used for Chapter 10 and then commented out in Chapter 11 Activity #1
       
       // Send a message to the user about the alarm
      // Toast t = Toast.makeText(context, name + "\n\n" + desc, Toast.LENGTH_LONG);
      // t.show();
       
       Intent explicitIntent = new Intent(context,
				 Snooze.class);
       explicitIntent.putExtra("name", name);
       explicitIntent.putExtra("desc", desc);
		 PendingIntent pendIntent= PendingIntent.getActivity(context,
                                   0, explicitIntent, 0);
		 
		 Notification.Builder builder = new Notification.Builder(context);
				 builder.setSmallIcon(R.drawable.ic_launcher) ; 
				 builder.setTicker("Reminder!");
				 builder.setContentTitle(name);
				 builder.setContentText(desc);
				 builder.setContentIntent(pendIntent);
				 builder.setAutoCancel(true);

				 Notification notify = builder.build();
				 NotificationManager nm = (NotificationManager)context.getSystemService(
                       Context.NOTIFICATION_SERVICE);
				  nm.notify(0, notify);
				  
    }
    catch (Exception e) 
    {
    }
  }



}
