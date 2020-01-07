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
 * This class represents the code for the Main
 * screen for this application.
 * @author Homeschool Programming, Inc.
 * @version 2.1
*/

package teencoder.androidprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

// This is the main class file for the Weather application
public class Main extends Activity implements OnClickListener
{
    // Weather site API Key
    // To get your own API Key, ask your teacher for the key provided in the teacher's guide.
    final private static String APIKey = "f3acaa261cbd471fbe5161422192012";
    
    //**********************************************************
    // This method will be completed by the student in the Chapter 12 Activity
    //
    // This method will connect to the network and attempt to download the latest weather information
    public static ArrayList<String> downloadWeather(Context context, String location) 
    {
      
    	ArrayList<String> results = new ArrayList<String>();
    	int response = -1;
    	if(Main.isNetworkAvailable(context) != false) {
    		return results;
    	}
    	try {
    		
    	URL url = new URL(Main.getWeatherURL(location));
		HttpURLConnection httpConn =
                            (HttpURLConnection)url.openConnection();
		httpConn.setRequestMethod("GET");
		httpConn.connect();
		
		response = httpConn.getResponseCode();
		if (response == HttpURLConnection.HTTP_OK)
		{
			// get InputStream and wrap in handy readers
			InputStream in = httpConn.getInputStream();
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(isr);

			String line = "";
			
			
			do
			{
				line = br.readLine();
				if (line != null && (!line.startsWith("#")))
					results.add(line); // add line to larger string
			}
			while (line != null);
			br.close();     // always close your input stream!
			httpConn.disconnect();
			// put whatever we received in a multi-line EditText
			//EditText et = (EditText)findViewById(R.id.editText1);
			//et.setText(file);
			
		}	
			
    	} catch(Exception e) {
    		
    		
    	}
        return results;	// return what we found to calling program
    }
    //**********************************************************
    
    
    //**********************************************************
    // This method will be completed by the student in the Chapter 12 Activity
    //
    // This method will download a weather image from the specified URL and
    // return a Bitmap object.
    public static Bitmap downloadWeatherImage(Context context, String IconURL) 
    {
        // Create and initialize a Bitmap object
        // If we are unable to download the image, the Bitmap will be null
        Bitmap weatherBitmap = null;
        int response = -1;
    	if(Main.isNetworkAvailable(context) != false) {
    		return weatherBitmap;
    	}
    	try {
    		
        	URL url = new URL(Main.getWeatherURL(IconURL));
    		HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
    		httpConn.setRequestMethod("GET");
    		httpConn.connect();
    		
    		response = httpConn.getResponseCode();
    		if (response == HttpURLConnection.HTTP_OK)
    		{
    			// get InputStream and wrap in handy readers
    			InputStream in = httpConn.getInputStream();
				weatherBitmap = BitmapFactory.decodeStream(in);
    			httpConn.disconnect();
    		}	
    			
        	} catch(Exception e) {
        		
        		
        	}
        return weatherBitmap; // return the Bitmap object to the calling code
    }
    
    // This method is provided complete as part of the activity starter
    //
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       
        // Set the OnClickListener for the two buttons at the bottom of the screen
        Button weatherButton = (Button)findViewById(R.id.currentButton);
        Button configButton = (Button)findViewById(R.id.configButton);
        weatherButton.setOnClickListener(Main.this);
        configButton.setOnClickListener(this);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }
    
    // This method is provided complete as part of the activity starter
    //
    /** Called when the activity is first created. */
    public void onStart()
    {
    	updateScreen();
        super.onStart();
    }
    
    // This method is provided complete as part of the activity starter
    //
    // update the current weather display on the Main screen
    private void updateScreen()
    {
    	String location = Main.getLocation(this);
    	
        // Set the Screen title to the current location
        this.setTitle("Current location: " + location.replace("+", " "));
        
        // Populate the weather information
        ArrayList<String> results = Main.downloadWeather(this,location);
        
        // now process the result lines (should be 3 of them)
        
    	WeatherInfo currentInfo = WeatherInfo.getCurrentWeatherInfo(results);
    	WeatherInfo tomorrowInfo = WeatherInfo.getFutureWeatherInfo(results, 1);
    	WeatherInfo nextInfo = WeatherInfo.getFutureWeatherInfo(results, 2);

    	if (currentInfo != null) // if we successfully retrieved current info
        {
        	// populate "current" controls 
        	populateCurrent(currentInfo);  
        }
        if (tomorrowInfo != null)// if we successfully retrieved tomorrow's info
        {
        	// populate "tomorrow" controls 
        	populateTomorrow(tomorrowInfo); 
        }
        if (nextInfo != null)// if we successfully retrieved 2nd day info
        {
        	// populate "next day" controls
        	populateNext(nextInfo);   
        }
        
    }
    
    // This method is provided complete as part of the activity starter
    //
    // This method will check to see if there is network connectivity on the device
    // If connection is found, the method will return true
    // If there is no network connection, the method will return false
    private static boolean isNetworkAvailable(Context context) 
    {
        // Get a handle to the current Connectivity Service on the device
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
      
        // Get the current network information from the Connectivity Service
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        
        // if no network is available networkInfo will be null, otherwise check if we are connected
        if (networkInfo != null && networkInfo.isConnected()) 
        {
            return true;
        }
        return false;
    }
    
    // This method is provided complete as part of the activity starter
    //
    // This method will place the current weather into the relevant area on the screen
    public void populateCurrent(WeatherInfo info)
    {
   	
        // Get a handle to the relevant controls on the screen
        TextView currentCond = (TextView)findViewById(R.id.todayCond);
        TextView currentWind = (TextView)findViewById(R.id.currentWind);
        TextView currentTemp = (TextView)findViewById(R.id.currentTemp);
        ImageView currentImg = (ImageView)findViewById(R.id.todayImage);
  
        // Create a Bitmap object by downloading the image URL
        Bitmap currentBmp = Main.downloadWeatherImage(this,info.imageURL);
        
        // Make sure we have a valid image before assigning it to the ImageView control
        if(currentBmp != null)
          currentImg.setImageBitmap(currentBmp);
        
        double fahrenheit = info.temperature;
        
        // Set the current temperature in farenheit
        currentTemp.setText(fahrenheit + " F");
        
        // Set the windspeed and direction
        currentWind.setText(info.maxWindspeed + "mph/" + info.windDirection);
        
        // Set the current condition description
        currentCond.setText(info.description);
      
    }
    
    // This method is provided complete as part of the activity starter
    //
    // This method will place tomorrow's weather into the relevant area on the screen
    public void populateTomorrow(WeatherInfo info)
    {
    	
    	// Get a handle to the relevant controls on the screen
        TextView tomCond = (TextView)findViewById(R.id.tommorowCond);
        TextView tomWind = (TextView)findViewById(R.id.tommorowWind);
        TextView tomTemp = (TextView)findViewById(R.id.tomorrowTemp);
        ImageView tomImg = (ImageView)findViewById(R.id.tomorrowImage);
  
        // Create a Bitmap object by downloading the image URL
        Bitmap tomBmp = Main.downloadWeatherImage(this,info.imageURL);
        
        // Make sure we have a valid image before assigning it to the ImageView control
        if(tomBmp != null)
            tomImg.setImageBitmap(tomBmp);
        
        // Set the min/max temperature
        tomTemp.setText(info.minTemperature + " F/" + info.maxTemperature + " F");
      
        // Set the windspeed and direction
        tomWind.setText(info.maxWindspeed + "mph/" + info.windDirection);
        
        // Set the current condition description
        tomCond.setText(info.description);
    }
    
    // This method is provided complete as part of the activity starter
    //
    // This method will place the next day's weather into the relevant area on the screen
    public void populateNext(WeatherInfo info)
    {
    	
    	// Get a handle to the relevant controls on the screen
        TextView nextCond = (TextView)findViewById(R.id.nextCond);
        TextView nextWind = (TextView)findViewById(R.id.nextWind);
        TextView nextTemp = (TextView)findViewById(R.id.nextTemp);
        ImageView nextImg = (ImageView)findViewById(R.id.nextImage);
  
        // Create a Bitmap object by downloading the image URL
        Bitmap nextBmp = Main.downloadWeatherImage(this,info.imageURL);
        
        // Make sure we have a valid image before assigning it to the ImageView control
        if(nextBmp != null)
            nextImg.setImageBitmap(nextBmp);
        
        // Set the min/max temperature
        nextTemp.setText(info.minTemperature + " F/" + info.maxTemperature + " F");
        
        // Set the windspeed and direction
        nextWind.setText(info.maxWindspeed + "mph/" + info.windDirection);
        
        // Set the current condition description
        nextCond.setText(info.description);
    }

    // This method is provided complete as part of the activity starter
    //
    // This method will built the weather URL string to retrieve the current weather
    private static String getWeatherURL(String location)
    {
        // Create and initialize the String value
        String strWURL = null;
        
        // Build the URL string using the location and APIKey values
        strWURL = "http://api.worldweatheronline.com/premium/v1/weather.ashx?q=" + location + "&format=csv&num_of_days=2&tp=24&key=" + APIKey;

        // URL string cannot have spaces, so we will need to translate any spaces in our string to "%20" characters
        strWURL = strWURL.replace(" ", "%20");
        
        // Return the weather URL String
        return strWURL;
    }
    
    // This method is provided complete as part of the activity starter
    //
    // This method will get the location configured for the main application
    public static String getLocation(Context context)
    {
    	return getLocation(context,-1);	// use -1 to signal main app configuration
    }
    
    // This method is provided complete as part of the activity starter
    //
    // This method will get the location configured for the specified appWidgetId
    public static String getLocation(Context context, int appWidgetId)
    {
        // Get the SharedPreferences for this application
        String prefsName = getPreferencesName(appWidgetId);
        SharedPreferences prefs = context.getSharedPreferences(prefsName, MODE_PRIVATE);
        
        // Retrieve the location information from the preferences
        return prefs.getString("Location", "30004");
    }

    // This method is provided complete as part of the activity starter
    //
    // This method will return the name of the shared preference configuration
    // (either specific to an app widget >=0, or for the main screen (-1))
    public static String getPreferencesName(int appWidgetId)
    {
	    if (appWidgetId >= 0)
	        return "WeatherSettings" + appWidgetId;
	    
	    return "WeatherSettings";
    }

    // This method is provided complete as part of the activity starter
    //
    public void onClick(View v) 
    {
        // If the user clicked the "Get Current Weather" button
        if (v == findViewById(R.id.currentButton))
        {
            updateScreen(); // Refresh the current weather conditions
        }
        // If the user clicked the "Configure Location" button
        else if (v == findViewById(R.id.configButton))
        {
            // Change to the Configuration Activity with an Explicit Intent
            Intent myIntent = new Intent(Main.this, Configure.class); 
            startActivity(myIntent);
        }
    }

}