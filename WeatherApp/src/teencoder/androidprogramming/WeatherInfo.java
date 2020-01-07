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
 * This class represents a set of weather data
 * @author Homeschool Programming, Inc.
 * @version 2.0
*/
package teencoder.androidprogramming;

import java.util.ArrayList;


// This file is provide complete as part of the starter project
//
// This simple class defines one set of weather data 
public class WeatherInfo
{
	// public properties for easy use by anyone
	public double temperature = 0;
	public String windDirection = "";
	public double maxWindspeed = 0;
	public double minTemperature = 0;
	public double maxTemperature = 0;
	public String description = "";
	public String imageURL = "";
	
	// V1 API results will have 3 lines, one for each day (current, tomorrow, next)
	// http://api.worldweatheronline.com/free/v1/weather.ashx?q=Atlanta&format=csv&num_of_days=2&key=z9t3e3uvhe3wv8xw8uzz88hz
	// 
	// #First row will always contain the current weather condition. if for any reason we do not have current condition it will have 'Not Available'.
	// #The current weather condition data is laid in the following way:-
	// #observation_time,temp_C,weatherCode,weatherIconUrl,weatherDesc,windspeedMiles,windspeedKmph,winddirDegree,winddir16Point,precipMM,humidity,visibility,pressure,cloudcover
	// #
	// #The weather information is available in following format:-
	// #date,tempMaxC,tempMaxF,tempMinC,tempMinF,windspeedMiles,windspeedKmph,winddirDegree,winddir16Point,weatherCode,weatherIconUrl,weatherDesc,precipMM
	//	
	// 12:45 AM,28,113,http://cdn.worldweatheronline.net/images/wsymbols01_png_64/wsymbol_0008_clear_sky_night.png,Clear ,6,9,230,SW,0.0,51,11,1018,0
	// 2015-05-10,32,89,20,68,6,10,294,WNW,116,http://cdn.worldweatheronline.net/images/wsymbols01_png_64/wsymbol_0002_sunny_intervals.png,Partly Cloudy,0.5
	// 2015-05-11,32,89,20,68,7,11,253,WSW,386,http://cdn.worldweatheronline.net/images/wsymbols01_png_64/wsymbol_0016_thundery_showers.png,Patchy light rain in area with thunder,3.4


	// V2 API results will have multiple lines for each subsequent day (should set tp=24 also to eliminate hourly data!)
	// http://api.worldweatheronline.com/free/v2/weather.ashx?q=atlanta&format=csv&num_of_days=2&tp=24&key=fd16199a38732ab40bf67331ca0eb
	//
	// #The current weather condition data is laid in the following way:-
	// #observation_time,temp_C,temp_F,weatherCode,weatherIconUrl,weatherDesc,windspeedMiles,windspeedKmph,winddirDegree,winddir16Point,precipMM,humidity,visibilityKm,pressureMB,cloudcover
	// #
	// #The day information is available in following format:-
	// #date,maxtempC,maxtempF,mintempC,mintempF,sunrise,sunset,moonrise,moonset
	// #
	// #Hourly information follows below the day in the following way:-
	// #date,time,tempC,tempF,windspeedMiles,windspeedKmph,winddirdegree,winddir16point,weatherCode,weatherIconUrl,weatherDesc,precipMM,humidity,visibilityKm,pressureMB,cloudcover,HeatIndexC,HeatIndexF,DewPointC,DewPointF,WindChillC,WindChillF,WindGustMiles,WindGustKmph,FeelsLikeC,FeelsLikeF,chanceofrain,chanceofremdry,chanceofwindy,chanceofovercast,chanceofsunshine,chanceoffrost,chanceofhightemp,chanceoffog,chanceofsnow,chanceofthunder
	// #
	// 01:25 PM,17,63,113,http://cdn.worldweatheronline.net/images/wsymbols01_png_64/wsymbol_0001_sunny.png,Sunny,8,13,300,WNW,0.0,59,16,1023,0
	// 2015-05-13,31,88,16,61,06:38 AM,08:30 PM,03:33 AM,03:46 PM
	// 2015-05-13,24,31,88,7,11,10,N,113,http://cdn.worldweatheronline.net/images/wsymbols01_png_64/wsymbol_0001_sunny.png,Sunny,0.0,30,10,1023,11,29,84,10,51,30,86,5,8,29,84,0,0,0,0,99,0,100,0,0,0
	// 2015-05-14,28,83,17,62,06:37 AM,08:30 PM,04:13 AM,04:52 PM
	// 2015-05-14,24,28,83,10,16,114,ESE,113,http://cdn.worldweatheronline.net/images/wsymbols01_png_64/wsymbol_0001_sunny.png,Sunny,0.0,43,10,1025,2,27,81,14,57,27,81,11,18,27,81,0,0,0,0,100,0,100,0,0,0
		
			
	
	private static int determineVersion(ArrayList<String> results)
	{
		// V1 API has 14 fields in the first/current weather line
		// V2 API has 15 fields in the first/current weather line
		
		// sanity check
		if (results.size() == 0)
			return 0;	// invalid results
		
		// get the first line and break it into fields so we can count them
		String currentWeather = results.get(0);
		String[] tokens = currentWeather.split(",");
		
		// return 1 for V1 API or 2 for V2 API or 0 for other error
		if (tokens.length == 14)
			return 1;	// V1 API
		if (tokens.length == 15)
			return 2;	// V2 API
		
		return 0;	// unexpected format or invalid data
	}
	
    // This method is provided complete as part of the activity starter
    //
    // Create a WeatherInfo object from a comma-delimited line containing
	// the current weather information
    public static WeatherInfo getCurrentWeatherInfo(ArrayList<String> results)
    {
    	WeatherInfo info = null;	//assume failure
    	
    	// see if this is V1 or V2 API results
    	int apiVersion = determineVersion(results);	

    	if ( apiVersion == 1)	// V1 API parsing
    	{
    		// #The current weather condition data is laid in the following way:-
    		// #observation_time,temp_C,weatherCode,weatherIconUrl,weatherDesc,windspeedMiles,windspeedKmph,winddirDegree,winddir16Point,precipMM,humidity,visibility,pressure,cloudcover
    		
    		String currentWeather = results.get(0);	// get the current weather line
    		
    		// get list of tokens in the comma-delimited string
    		String[] tokens = currentWeather.split(",");

            if (tokens.length > 8)	// if input line has enough tokens
            {
        		info = new WeatherInfo();

    	        info.temperature = Double.parseDouble(tokens[1]) * 9.0 / 5.0 + 32.0;  // convert C to F
    	        info.windDirection = tokens[8];
    	        info.maxWindspeed = Double.parseDouble(tokens[5]);
    	        info.minTemperature = 0;
    	        info.maxTemperature = 0;
    	        info.description = tokens[4];
    	        info.imageURL = tokens[3];
            }
    		
    	}
    	else if (apiVersion == 2) // V2 API parsing
    	{
    		// #The current weather condition data is laid in the following way:-
    		// #observation_time,temp_C,temp_F,weatherCode,weatherIconUrl,weatherDesc,windspeedMiles,windspeedKmph,winddirDegree,winddir16Point,precipMM,humidity,visibilityKm,pressureMB,cloudcover

    		String currentWeather = results.get(0);	// get the current weather line
    		
    		// get list of tokens in the comma-delimited string
    		String[] tokens = currentWeather.split(",");

            if (tokens.length > 9)	// if input line has enough tokens
            {
        		info = new WeatherInfo();

    	        info.temperature = Double.parseDouble(tokens[2]);  // read F directly
    	        info.windDirection = tokens[9];
    	        info.maxWindspeed = Double.parseDouble(tokens[6]);
    	        info.minTemperature = 0;
    	        info.maxTemperature = 0;
    	        info.description = tokens[5];
    	        info.imageURL = tokens[4];
            }
    	}
       
        return info;	// will be null if unsuccessful
    }

    // This method is provided complete as part of the activity starter
    //
    // Create a WeatherInfo object from comma-delimited lines containing
	// future weather information
    public static WeatherInfo getFutureWeatherInfo(ArrayList<String> results, int day)
    {
    	WeatherInfo info = null;	//assume failure
    	
    	// see if this is V1 or V2 API results
    	int apiVersion = determineVersion(results);	

    	if ( apiVersion == 1)	// V1 API parsing
    	{
    		// #The weather information is available in following format:-
    		// #date,tempMaxC,tempMaxF,tempMinC,tempMinF,windspeedMiles,windspeedKmph,winddirDegree,winddir16Point,weatherCode,weatherIconUrl,weatherDesc,precipMM

    		String dayWeather = results.get(day);	// get Day N weather line
    		
    		// get list of tokens in the comma-delimited string
    		String[] tokens = dayWeather.split(",");

            if (tokens.length > 11)	// if input line has enough tokens
            {
                info = new WeatherInfo();

    	        info.temperature = 0;
    	        info.windDirection = tokens[8];
    	        info.maxWindspeed = Double.parseDouble(tokens[5]);
    	        info.minTemperature = Double.parseDouble(tokens[4]);
    	        info.maxTemperature = Double.parseDouble(tokens[2]);
    	        info.description = tokens[11];
    	        info.imageURL = tokens[10];
            }       
    	}
    	else if (apiVersion == 2) // V2 API parsing
    	{
    		// #The day information is available in following format:-
    		// #date,maxtempC,maxtempF,mintempC,mintempF,sunrise,sunset,moonrise,moonset
    		// #
    		// #Hourly information follows below the day in the following way:-
    		// #date,time,tempC,tempF,windspeedMiles,windspeedKmph,winddirdegree,winddir16point,weatherCode,weatherIconUrl,weatherDesc,precipMM,humidity,visibilityKm,pressureMB,cloudcover,HeatIndexC,HeatIndexF,DewPointC,DewPointF,WindChillC,WindChillF,WindGustMiles,WindGustKmph,FeelsLikeC,FeelsLikeF,chanceofrain,chanceofremdry,chanceofwindy,chanceofovercast,chanceofsunshine,chanceoffrost,chanceofhightemp,chanceoffog,chanceofsnow,chanceofthunder

    		// by setting tp=24 in the request URL, we are receiving just one summary "hourly" line per day. 
    		// So, we need to aggregate information from two lines to produce the forecast we want for future days.

    		// line 0 = current weather
    		// line 1 = day 1 forecast summary
    		// line 2 = day 1 forecast details
    		// line 3 = day 2 forecast summary
    		// line 4 = day 2 forecast details
    		
    		// ensure we have enough lines
    		if (results.size() >= 5)
    		{
    			// calculate row for this day
    			int dayIndex = (day * 2) - 1;
    			
    			// get the 2 lines of data
	    		String daySummary = results.get(dayIndex);	// get Day N weather summary
	    		String dayDetails = results.get(dayIndex + 1);	// get Day N weather details
	    		
	    		// get list of tokens in the comma-delimited strings
	    		String[] summaryTokens = daySummary.split(",");
	    		String[] detailTokens = dayDetails.split(",");
	
	    		// if input lines have enough tokens
	            if ((summaryTokens.length > 4) && (detailTokens.length > 10))	
	            {
	                info = new WeatherInfo();
	
	    	        info.temperature = 0;
	    	        info.windDirection = detailTokens[7];
	    	        info.maxWindspeed = Double.parseDouble(detailTokens[4]);
	    	        info.minTemperature = Double.parseDouble(summaryTokens[4]);
	    	        info.maxTemperature = Double.parseDouble(summaryTokens[2]);
	    	        info.description = detailTokens[10];
	    	        info.imageURL = detailTokens[9];
	            }
    		}
    		
    	}
       
        return info;	// will be null if unsuccessful
    }
    
}
    
