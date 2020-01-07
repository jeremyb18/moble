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
 * Chapter 5, 6 & 7 - Whack-A-Mole 
 * This class represents the code for the Game screen in the
 * "Whack-A-Mole" game.
 * @author Homeschool Programming, Inc.
 * @version 2.0
*/

package teencoder.androidprogramming;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;

//The main Game class for the Whack-A-Mole game
public class Game extends Activity implements OnClickListener 
{
	// Set up an array of integers to hold the Button IDs of the "moles"
	ArrayList<Integer> myButtonIDs = new ArrayList<Integer>();
		
	// The Handler will be used to run a timer in our game
	protected Handler taskHandler = new Handler();
		
	// The isComplete variable will tell us when time is up!
	protected Boolean isComplete = false;
		
	// We need to know which mole is the currently visible mole
	Button currentMole;
	
	// Use the current time as the start time for the game
	long startTime = System.currentTimeMillis();
		
	// Keep track of how many times the user has hit the mole
	int numWhacks = 0;
		
	// The following variables are used to configure the game.
	// Establish default game configuration settings here!
	String playerName = "Default";
	int difficultyLevel = 2;    // 1 = hard, 2 = medium, 3 = easy
	int numMoles = 8;           // any value between 3 and 8
	int duration = 20;			// any value up to 30 seconds
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
    }
    
     
    // Click method for all buttons in the game
    public void onClick(View v) 
    {
    	
	}
    	
    // This method is called when the game is completed
    public void gameOver()
    {
    	
    }
       
    // This method will choose a new button as the current mole
    // ** This method is provided complete as part of the activity starter. **
    public void setNewMole()
    {
       	// Create a random number generator
       	Random generator = new Random();

    	// Find a random number between 0 and numMoles - 1
       	int randomItem = generator.nextInt(numMoles);	 

        // Use the random number as an index into the array of button IDs
       	// The new mole button ID is the element in the array at our random index
       	int newButtonId = myButtonIDs.get(randomItem);
        	
       	// Make sure there is a current mole, then hide it on the screen
       	if (currentMole != null)
       		currentMole.setVisibility(View.INVISIBLE);
     	
       	// Get the new mole button using the new Button ID value
       	Button newMole = (Button)findViewById(newButtonId);
        	
       	// Set our new mole to visible on the screen
       	newMole.setVisibility(View.VISIBLE);
        	
       	// Return the new mole Button object
       	currentMole = newMole;
    }
    
    // This method will retrieve all mole button IDs and place them into
    // our array of integer Button IDs.
    // ** This method is provided complete as part of the activity starter. **
    public void initButtons()
    {
    	// A ViewGroup will allow us to grab all the controls in the current layout
		ViewGroup group = (ViewGroup)findViewById(R.id.GameLayout);
		View v;
		
		// Now we can loop through all the controls and find just the buttons
		for(int i = 0; i < group.getChildCount(); i++) 
		{
		    v = group.getChildAt(i);
		    if (v instanceof Button)	// if the current control is a button
		    {
		    	v.setOnClickListener(this); 	// set the onClickListener for the button
		    	
		    	// If the game is not over
		    	if (!isComplete)	
		    	{
		    		myButtonIDs.add(v.getId());			// Add the Button ID to the array
		    		v.setVisibility(View.INVISIBLE);	// Set the Button to invisible
		    	}
		    	
		    }
		}

    }
    
    // This method will create the timer that will allow us to switch current moles
    // ** This method is provided complete as part of the activity starter. **
    protected void setTimer( long time )
    {
        // get the time that we want our timer to last from the input parameter
    	final long elapse = time;
        
    	// Create a new "runnable" task - this will create a timer feature
    	Runnable t = new Runnable() 
    	{
            public void run()
            {
                onTimerTask();	// Change the current mole on the screen
                
                // If the game is not complete
                if( !isComplete )
                {
                    // create the new timer task to go off when the next mole should be shown
                	taskHandler.postDelayed( this, elapse );
                }
            }
        };
        
        // create the new timer task to go off when the next mole should be shown
    	taskHandler.postDelayed( t, elapse );
    }
     
    // This method will change the current mole whenever the timer goes off
 // ** This method is provided complete as part of the activity starter. **
    protected void onTimerTask()
    {
    	// Calculate our ending time based on the duration setting
    	long endTime = startTime + (duration * 1000);
    	    	
    	// If the ending time is greater than the current time, keep the game going
    	if (endTime > System.currentTimeMillis())
    	{
    	   	setNewMole();	// set a new mole on the screen
    	}
        else
        {
        	gameOver();		// if the ending time is less than the current time, the game is over
        	
        }
    }
    
}


