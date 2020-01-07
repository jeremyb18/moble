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
 * This class represents the code for the Alarm List 
 * screen for this application.
 * @author Homeschool Programming, Inc.
 * @version 2.0
*/

package teencoder.androidprogramming;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class AlarmList extends ListActivity
{
  // Create an array of all AlarmKeeper objects
  ArrayList<AlarmKeeper> myAlarmKeepers = new ArrayList<AlarmKeeper>();
  
  // Create an array of all Alarm names
  ArrayList<String> myAlarmDisplayStrings = new ArrayList<String>();
  
  // This method is provided complete in the starter activity
  /** Called when the activity is first created. */
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    
    // populate memory arrays from alarms saved to disk
    loadAlarms();

    // Re-populate the display list of alarms
    buildAlarmDisplayStrings();
    registerForContextMenu(getListView());
  }
  
  // This method is provided complete in the starter activity
  protected void onListItemClick(ListView l, View v, int position, long id)
  {
    if ((position >= 0) && (position < myAlarmKeepers.size()))
    {
       // If the user clicks on an existing item, create a String
       // out of the alarm information and pass it in a new Intent
       // to the AddAlarm screen
       AlarmKeeper alarm = myAlarmKeepers.get(position);
       Intent editIntent = new Intent(this, AddAlarm.class);
       editIntent.putExtra("alarm", alarm.buildString());
       editIntent.putExtra("index", position);
       startActivityForResult(editIntent, 0);
    }
  }
  
  // This method is provided complete in the starter activity
  public void buildAlarmDisplayStrings()
  {
    // Clear the strings array
    myAlarmDisplayStrings.clear();
    
    // If we do not have any alarms set
    if (myAlarmKeepers.isEmpty())
    {
      myAlarmDisplayStrings.add("No Alarms Set");
    }
    else
    {
      // Put each alarm name in the display string array
      for (AlarmKeeper alarm: myAlarmKeepers)
      {
        myAlarmDisplayStrings.add(alarm.alarmName);
      }
    }
    
    // Create an ArrayAdapter using our new display strings array
    ArrayAdapter<String> myAdapter = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, myAlarmDisplayStrings);
    
    // Set the ArrayAdapter to our list
    setListAdapter(myAdapter);    
  }
  
  // This method is provided complete in the activity starter
  protected void onActivityResult(int requestCode, int resultCode, Intent data)
  {
    // If there is Intent data returned to us
    if (data != null)
    {
      // this screen launched by response from AddAlarm screen
      
      // If the Intent has Extra data
      Bundle extras = data.getExtras();
      if (extras != null)
      {
        // Get the Alarm values and the index value
        String alarmString = extras.getString("alarm");
        int index = extras.getInt("index");

        // If we have valid alarm values
        if (alarmString != null)
        {
          // The alarm has changed, so we need a new AlarmKeeper object
          AlarmKeeper changedAlarm = new AlarmKeeper();
          
          // Copy the alarm values into our new AlarmKeeper
          changedAlarm.fromString(alarmString);
          if (index >= 0)
          {
            // update existing alarm in our memory arrays
            myAlarmKeepers.set(index, changedAlarm);  
            changedAlarm.cancelAlarm(this);
          }
          else
          {
            // add new alarm to our memory arrays
            index = myAlarmKeepers.size();  // this new alarm's index is the last slot in the array
            myAlarmKeepers.add(changedAlarm); 
          }
            
          changedAlarm.setAlarm(this);

          saveAlarms(); // save modified list to disk
        }
      }
    }

    // now, regardless of whether or not this screen was launched by the user
    // or as a result of a new or updated alarm edit, we need to re-populate the
    // display list
    buildAlarmDisplayStrings();
  }
  
  //**************************************************************
  // This method is completed by the student in Chapter 11 Activity #2
  
  // This method is called when the program needs to create an ActionBar menu
  public boolean onCreateOptionsMenu(Menu menu) 
  {
	  
	       // get the MenuInflater object from the Android system
	       MenuInflater mi = getMenuInflater();

	       // "inflate" the menu using our actinobar_menu layout XML
	       mi.inflate(R.menu.actionbar, menu);

	       return true;   // all done!
	    
	       
  }
  //**************************************************************
  
  
  //**************************************************************
  // This method is completed by the student in Chapter 11 Activity #2
  
  // This method is called when a user clicks on a menu item
  public boolean onOptionsItemSelected(MenuItem item) 
  {
	  
	       
	       if (item.getItemId() == R.id.actionAdd)
	       {
	         Intent explict = new Intent(AlarmList.this,AddAlarm.class);
	  explict.putExtra("alarm", "");
      explict.putExtra("index", -1);
		startActivityForResult(explict,0);
	         
	       }
	       
     return super.onOptionsItemSelected(item);
  }
  
	
  //**************************************************************
  
  
  //**************************************************************
  // This method is completed by the student in Chapter 11 Activity #3
  //
  // This method is called when the program needs to create a Context menu
  public void onCreateContextMenu(ContextMenu menu, View v, 
                                  ContextMenuInfo menuInfo) 
  {
    super.onCreateContextMenu(menu, v, menuInfo);
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.alarm_context, menu);
    
  }
  //**************************************************************
  
  
  //**************************************************************
  // This method is completed by the student in Chapter 11 Activity #3
    
  // This method is called when a user clicks on a context menu item
  public boolean onContextItemSelected(MenuItem item) 
  {
	  AdapterContextMenuInfo info =
              (AdapterContextMenuInfo) item.getMenuInfo();
	  
	  int index = info.position;
	  if(index >= 0  && index < myAlarmKeepers.size()) {
		  AlarmKeeper Alarm = myAlarmKeepers.get(index);
		  Alarm.cancelAlarm(this);
		  myAlarmKeepers.remove(index);
		  myAlarmDisplayStrings.remove(index);
		  saveAlarms();
		  buildAlarmDisplayStrings();
	  }
	  
    return super.onOptionsItemSelected(item);
  }
  //****************************************************************
  
  // This method is provided complete in the starter activity
  public void loadAlarms()
  {
    // All file operations must be placed in a try/catch block
    try 
    {
      // Open the HighScores.txt file and place it in an InputStreamReader object
      InputStreamReader isr = new InputStreamReader(openFileInput("alarms.txt"));
      
      // Place the contents in a BufferedReader object to make reading easier
      BufferedReader buffreader = new BufferedReader(isr);
      
      // Read in the data, line-by-line
      String line = buffreader.readLine();
      
      // While we still have valid data
      while (line != null) 
      {
        StringTokenizer st = new StringTokenizer(line, ",");
        
        AlarmKeeper newAlarm = new AlarmKeeper();
        newAlarm.alarmName = st.nextToken();
        newAlarm.alarmDay = Integer.parseInt(st.nextToken());
        newAlarm.alarmMonth = Integer.parseInt(st.nextToken());
        newAlarm.alarmYear = Integer.parseInt(st.nextToken());
        newAlarm.alarmHour = Integer.parseInt(st.nextToken());
        newAlarm.alarmMinute = Integer.parseInt(st.nextToken());
        newAlarm.alarmDesc = st.nextToken();
        newAlarm.setReqCode(Integer.parseInt(st.nextToken()));
        
        myAlarmKeepers.add(newAlarm);
        
        // Read in the next line in the file
        line = buffreader.readLine();
      }
      
      // Close the BufferedReader object
      buffreader.close();
    }
    catch (Exception e) 
    {
      // file error
    }
  }
  
  
  // This method is provided complete in the starter activity
  public void saveAlarms()
  {
    // File operations must be done in a try/catch block
    try 
    {
      // Open the file and assign it to an output stream writer
      OutputStreamWriter osr = new OutputStreamWriter(openFileOutput("alarms.txt",  MODE_PRIVATE));
      
      // Figure out which character is an endline on the current device
      String endLine = System.getProperty("line.separator");
      
      for (int i=0; i<myAlarmKeepers.size(); i++)
      {
        AlarmKeeper alarm = myAlarmKeepers.get(i);
        osr.write(alarm.alarmName + ",");
        osr.write(alarm.alarmDay+ ",");
        osr.write(alarm.alarmMonth + ",");
        osr.write(alarm.alarmYear + ",");
        osr.write(alarm.alarmHour + ",");
        osr.write(alarm.alarmMinute + ",");
        osr.write(alarm.alarmDesc + ",");
        osr.write(alarm.getReqCode() + ",");
        osr.write(endLine);
      }
      
      // Make sure all data has been sent out to the file
      osr.flush();
      
      // Close the file
      osr.close();
    } 
    catch (Exception e) 
    {
      // file error
    }
  }
}

