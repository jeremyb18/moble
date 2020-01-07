package com.example.anonymous_classes;

import android.os.Bundle;
import android.app.*;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.*;
import android.view.View.*;
import android.widget.*;


public class MainActivity extends Activity {
	TextView count;
	int num = 0;
	
	RelativeLayout currentLayout ;	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		count = (TextView)findViewById(R.id.textView1);
		Button add = (Button)findViewById(R.id.button1);
		Button minus = (Button)findViewById(R.id.button2);
		add.setOnClickListener( 
				new OnClickListener() {
					
					public void onClick(View v) {
						num++;
						count.setText(num + "");
						
					}

					
				}
				);
		minus.setOnClickListener( new minus());
		Button Change = (Button)findViewById(R.id.button3);
		Change.setOnClickListener(
				new OnClickListener() {
					public void onClick(View v) {
						DialogFragment newFragment = new MyAlertDialog();
						newFragment.show(getFragmentManager(), "myDialog");
						
					}
					
					
				});
	}

	
	

	  public static class MyAlertDialog extends DialogFragment
	  {
		String[] Colors = {"Blue","Green", "Purple", "Red"};
	    public Dialog onCreateDialog(Bundle savedInstanceState)
	    {
	    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    	builder.setTitle("What Color do you like");
	    	builder.setCancelable(false);
	    	
	    	builder.setSingleChoiceItems(Colors,-1,
	    		     new DialogInterface.OnClickListener()  // anonymous inner class
	    		     {
	    		        public void onClick(DialogInterface dialog, int id)
	    		        {
	    		        	String color = Colors[id];
	    		        	MainActivity myActivity = (MainActivity)getActivity();
	    	    		    RelativeLayout  currentLayout = (RelativeLayout)myActivity.findViewById(R.id.layout);
	    		        	if(color == "Blue") {
	    		    
	    		        	currentLayout.setBackgroundColor(Color.BLUE);
	    		        	}
	    		        	if(color == "Green") {
	    		    		    
		    		        	currentLayout.setBackgroundColor(Color.GREEN);
		    		        	}
	    		        	if(color == "Purple") {
	    		    		    
		    		        	currentLayout.setBackgroundColor(Color.MAGENTA);
		    		        	}
	    		        	if(color == "Red") {
	    		    		    
		    		        	currentLayout.setBackgroundColor(Color.RED);
		    		        	}
	    		        }
	    		        
	    		     }
	    		);
	    	
	    	return builder.create();
	    }
	  
	  
	  
	  }
	
	class minus implements OnClickListener {
		public void onClick(View v) {
			num--;
			count.setText(num + "");
			
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
