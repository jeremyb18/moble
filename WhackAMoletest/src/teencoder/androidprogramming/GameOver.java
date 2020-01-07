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
 * Chapter 7 - Whack-A-Mole 
 * This class represents the code for the GameOver screen in the
 * "Whack-A-Mole" game.
 * @author Homeschool Programming, Inc.
 * @version 2.0
*/

package teencoder.androidprogramming;

import java.io.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.*;
import android.widget.*;

//The class for the GameOver screen
public class GameOver extends Activity implements OnClickListener
{
	// Use globals to define game options
	String playerName;
	int numWhacks;
	
	//**********************************************************
    // The student will complete this method for Chapter 7 Activity #2
	//
	// onCreate() should read data out of the Intent and populate
	// the controls on the screen with the game over message.
	// It will also register "this" as the button listeners and
	// save the new game over data to persistent storage.
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover);
    }
	
	//**********************************************************
    // The student will complete this method for Chapter 7 Activity #2
	//
    // save the player's score into the specified FileOutputStream
    private void writeToFOS(FileOutputStream fos)
    {
    
    }
    
	//**********************************************************
    // The student will complete this method for Chapter 7 Activity #2
	//
    // save the player's score into the specified FileOutputStream
	// This method will save the player's score into the HighScores.txt file in Internal Memory
	private void saveHighScoreInternalFile()
	{

	}
	
	//**********************************************************
    // The student will complete this method for Chapter 7 Activity #3
	//
	// This method will save the player's score into the HighScores.txt file on a SD card
	private void saveHighScoreSD()
	{

	}
	//**********************************************************
	
	//**********************************************************
    // This method is provided as part of the activity starter
	//
	// Click method for all buttons in the Game Over screen
	public void onClick(View v) 
	{
		// If the user clicked the "Play Again" button
		if (v.getId() == R.id.buttonPlay)
   		{
			// Start the Game screen with an Explicit Intent
			Intent myIntent = new Intent(this, Game.class); 
			startActivity(myIntent);
			finish();	// close this activity
   		}
		// If the user clicked the "High Scores" button
		else if(v.getId() == R.id.buttonScores)
		{
			// Start the Scores screen with an Explicit Intent
			Intent myIntent = new Intent(this, HighScores.class);
			startActivity(myIntent);
			finish();	// close this activity
		}
	}
}

