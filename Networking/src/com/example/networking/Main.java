package com.example.networking;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.net.*;
import java.io.*;
import android.net.*;
import android.content.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		
		//Uri myURI = Uri.parse("http://www.CompuScholar.com"); 
		//Intent uriIntent = new Intent(Intent.ACTION_VIEW, myURI );
		//startActivity(uriIntent);
		/*
		try
		{
			if (isNetworkAvailable())  // only try if network is available
			{
				// make URL and open the HttpUrlConnection
				URL url = new URL("http://www.google.com");
				HttpURLConnection httpConn =
		                            (HttpURLConnection)url.openConnection();
				httpConn.setRequestMethod("GET");

				// connect to the web server and get response code
				httpConn.connect();
				int response = httpConn.getResponseCode();
				if (response == HttpURLConnection.HTTP_OK)
				{
					// get InputStream and wrap in handy readers
					InputStream in = httpConn.getInputStream();
					InputStreamReader isr = new InputStreamReader(in);
					BufferedReader br = new BufferedReader(isr);

					String line = "";
					String file = "";

					// read lines of text until nothing left on stream
					do
					{
						line = br.readLine();
						if (line != null)
							file += line;  // add line to larger string
					}
					while (line != null);
					br.close();     // always close your input stream!

					// put whatever we received in a multi-line EditText
					EditText et = (EditText)findViewById(R.id.editText1);
					et.setText(file);
				}else {Toast t = Toast.makeText(getApplicationContext(),
						   "did not work :(",Toast.LENGTH_LONG);
				   t.show();}
				httpConn.disconnect();  // always disconnect when finished!
		  }
		}
		catch(Exception e)
		{
		   // handle errors here
			//EditText et = (EditText)findViewById(R.id.editText1);
			//et.setText(e.toString());
		   Toast t = Toast.makeText(getApplicationContext(),
		   e.toString(),Toast.LENGTH_LONG);
		   t.show();
		}
		
		*/
		try
		{
			if (isNetworkAvailable())  // only try if network is available
			{
				// make URL and open the HttpUrlConnection
				URL url = new URL("http://www.CompuScholar.com/images/teencoder_android.png");
				HttpURLConnection httpConn =
		                            (HttpURLConnection)url.openConnection();
				httpConn.setRequestMethod("GET");

				// connect to the web server and get response code
				httpConn.connect();
				int response = httpConn.getResponseCode();
					if (response == HttpURLConnection.HTTP_OK)
					{
						// get InputStream and wrap in handy readers
						InputStream in = httpConn.getInputStream();
						Bitmap imageBmp = BitmapFactory.decodeStream(in);
						
						
						in.close();
						
						
						ImageView iv = (ImageView)findViewById(R.id.imageView1);   
						iv.setImageBitmap(imageBmp);
						
					}else {Toast t = Toast.makeText(getApplicationContext(),
							   "did not work :(",Toast.LENGTH_LONG);
					   t.show();}
		    httpConn.disconnect(); 
		    // always disconnect when finished!
		  }
		}
		catch(Exception e)
		{
		   // handle errors here
		   Toast t = Toast.makeText(getApplicationContext(),
		   e.toString(),Toast.LENGTH_LONG);
		   t.show();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public boolean isNetworkAvailable()
	{
		// get a reference to the ConnectivityManager
	    ConnectivityManager cm = (ConnectivityManager)
	                getSystemService(Context.CONNECTIVITY_SERVICE);
	   
	    // get the current NetworkInfo object
	    NetworkInfo networkInfo = cm.getActiveNetworkInfo();
	   
	    // if we have a valid NetworkInfo and we are connected
	    if (networkInfo != null && networkInfo.isConnected())
	    {
	        return true;    // yes this device is connected
	    }
	    
	    return false; // not connected for some reason
	    
	}
}
