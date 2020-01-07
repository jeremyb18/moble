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
 * Chapter 12 & 13 - Weather App
 * This class represents the code for the Configure 
 * screen for this application.
 * @author Homeschool Programming, Inc.
 * @version 2.0
*/

package teencoder.androidprogramming;


import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


// This is the class file for the Configure Activity
public class Configure extends Activity implements OnClickListener
{
    
    // Create and initialize the Widget ID (-1 means no widget)
    int widgetID = -1;                        
    
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configure);
        
        //*********************************************************************************
        // The following code block is added for Chapter 13 Activity #2
        
        //*********************************************************************************
                
        // Load the current location settings (if any)
        loadSettings();
        
        // Set the OnClickListener for the "Save Settings" button
        Button btnConfig = (Button) findViewById(R.id.btnConfig);
        btnConfig.setOnClickListener(this);

        // Set the current application title  to the current location information
        String location = Main.getLocation(this,widgetID);
        this.setTitle("Current Location: " + location.replace("+"," "));
        
    }
  
    // This method will connect to the Shared Preferences and will populate the settings with the user's preferences
    public void loadSettings()
    {
        // Get handles to the relevant controls on the screen
        RadioButton rbCity = (RadioButton) findViewById(R.id.rbCity);
        RadioButton rbZip = (RadioButton) findViewById(R.id.rbZip);
        EditText etCity = (EditText) findViewById(R.id.etCity);
        EditText etState = (EditText) findViewById(R.id.etState);
        EditText etZip = (EditText) findViewById(R.id.etZip);
        
        
        // Load the Shared Preferences for the application or widget
        String prefsName = Main.getPreferencesName(widgetID);
        SharedPreferences prefs = getSharedPreferences(prefsName, MODE_PRIVATE);
        
        // Check the value in the "LocType" preference. 
        // LocType = "Zip" means the user wants to retrieve weather with a zip code
        // LocType = "City" means the user wants to retrieve weather with a city/state value
        
        // Set the correct radio button based on the user preference
        if (prefs.getString("LocType", "Zip") == "Zip")
            rbZip.setChecked(true);
        else
            rbCity.setChecked(true);

        // Set the rest of the settings based on user preferences
        etZip.setText(prefs.getString("Zip", "30334"));
        etCity.setText(prefs.getString("City", "Atlanta"));
        etState.setText(prefs.getString("State", "GA"));
    }
  
    public void saveSettings()
    {
        // Get a handle to all the settings controls on the screen
        RadioButton rbCity = (RadioButton) findViewById(R.id.rbCity);
        RadioButton rbZip = (RadioButton) findViewById(R.id.rbZip);
        EditText etCity = (EditText) findViewById(R.id.etCity);
        EditText etState = (EditText) findViewById(R.id.etState);
        EditText etZip = (EditText) findViewById(R.id.etZip);
        
        // Load the Shared Preferences for the application or widget
        String prefsName = Main.getPreferencesName(widgetID);
        SharedPreferences prefs = getSharedPreferences(prefsName, MODE_PRIVATE);
        
        // Open an editor to make changes to our Shared Preferences
        SharedPreferences.Editor editor = prefs.edit();
    
        String location = null;
        
        // If the user has marked the City radio button
        if (rbCity.isChecked())
        {
            // Build the location data from the City and State fields on the screen
            // The string must be in the form: City, State
            // In addition, the City and State strings cannot have spaces, so we must replace any spaces with "+" characters
            String strCity = etCity.getText().toString();
            String strState = etState.getText().toString();
            location = strCity.replace(" ", "+") + "," + strState.replace(" ", "+");
  
            // Save the Location and LocType information into the preferences
            editor.putString("Location", location);
            editor.putString("LocType", "City");
        }
        
        // If the user has marked the Zip radio button
        else if (rbZip.isChecked())
        {
            // The location string just needs to contain the zip code text
            location = etZip.getText().toString();
            
            // Save the Location and LocType information into the preferences
            editor.putString("Location", location);
            editor.putString("LocType", "Zip");
        }
        
        // Save the City, State, and Zip information into the preferences
        editor.putString("City", etCity.getText().toString());
        editor.putString("State", etState.getText().toString());
        editor.putString("Zip", etZip.getText().toString());
  
        // Commit the changes to memory
        editor.commit();
       
    }
  
    public void onClick(View v) 
    {
        saveSettings();
    
        //*********************************************************************************
        // The following code block is added for Chapter 13 Activity #2
      
        
        //*********************************************************************************
      
        finish(); // close this activity and return to the calling activity
    }
}