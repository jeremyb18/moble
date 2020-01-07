package com.example.whack_a_mole;

import android.os.Bundle;
import java.util.Random;
import java.util.ArrayList;
import android.app.Activity;
import android.view.Menu;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;
public class Game extends Activity implements OnClickListener{
	// Set up an array of integers to hold the Button IDs of the Moles
	ArrayList<Integer> myButtonIDs = new ArrayList<Integer>();
	// The handler will be used to run a timer in our game
	protected Handler taskHandler = new Handler();
	// The isComplete variable will tell us when time is up
	protected Boolean isComplete = false;
	// We need to know which mole is the currently visible mole
	ImageButton currentMole;
	// Use the current time as the start time for the game
	Long startTime = System.currentTimeMillis();
	// Keep track of how many times the user has hit the mole
	int numWhacks = 0;
	// the following variables are used to configure the game.
	// Establish default game configuration settings here
	String playerName = "Default";
	int difficultyLevel = 2;   // 1 = hard, 2 = medium, 3 = easy
	int numMoles = 8;          // any value between 3 and 8;
	int duration = 20;         // any value up to 30 seconds        
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		//gets the sharedPreferences
		SharedPreferences wackSetting = getSharedPreferences("MyPreferences", MODE_PRIVATE);
		
		//sets the difficulty level
		difficultyLevel = wackSetting.getInt("diff", difficultyLevel);
		//sets the number of Moles 
		numMoles = wackSetting.getInt("num", numMoles);
		//sets the duration of the game
		duration = wackSetting.getInt("duration", duration);
		//sets the player's name
		playerName = wackSetting.getString("name", playerName);
		TextView name = (TextView)findViewById(R.id.Name);
		name.setText(playerName);
		// This will link the * buttons on the screen and call them Invisible
		initButtons();
		//This will randomly select one of the buttons and make it Visible
		setNewMole();
		//difficultyLevel is a class-level variable declared and initialized to 2 in the activity starter. 
		//This value represents the number of seconds each mole will last on the screen.
		//The setTimer() method takes a number of milliseconds, so multiplying by 1000 converts the number of seconds to milliseconds
		setTimer(difficultyLevel * 1000);
		
	}
	// Click method for all buttons in the game
	@Override
	public void onClick(View v) {
		
		if(isComplete) {
			return;
		}
			if( v == currentMole) {
				numWhacks++;
				TextView tv2 = (TextView)findViewById(R.id.textView1);
				tv2.setText("Number of Whacks: " + numWhacks );
				setNewMole();
			}
		
		
	}
	// This method is called when the game is completed
	public void gameOver() {
		// telling the program that the game is over
		isComplete = true;
		// changing the text view
		TextView tv = (TextView)findViewById(R.id.textView1);
		tv.setText("Game Over: " + numWhacks );
		Intent GameOver = new Intent(this, GameOver.class);
		GameOver.putExtra("name", playerName);
		GameOver.putExtra("Score", numWhacks);
		startActivity(GameOver);
		finish();
	}
	// this method will choose a new button as the current mole
	public void setNewMole() {
		//Create a random number generator
		Random generator = new Random();
		
		// Find a random number between 0 and numMoles - 1;
		int randomItem = generator.nextInt(numMoles);
		// Use the random number as an index into the array of button IDs
		// The new mole button ID is the element is the array at out random index
		int newButtonId = myButtonIDs.get(randomItem);
		
		// Make sure there is a current mole, then hide it on the screen
		if (currentMole != null){
			
			//currentMole.setVisibility(View.INVISIBLE);
			currentMole.setImageResource(R.drawable.empty_hole2);
		}
		// Get the new mole button using the new Button ID value
		ImageButton newMole = (ImageButton)findViewById(newButtonId);
		//Set our new mole to visible on the screen
		//newMole.setVisibility(View.VISIBLE);
		newMole.setImageResource(R.drawable.mole2);
		//Return the new mole Button object
		currentMole = newMole;
		
	}
	
	//This method will retrieve all mole button IDs and place them into 
	// our array of integer Button IDs.
	public void initButtons() {
		// A ViewGroup will allow us to grab all the controls in the current layout
		ViewGroup group = (ViewGroup)findViewById(R.id.GameLayout);
		View v;
		
		// Now we can loop through all the controls and find just the buttons
		for (int i = 0; i < group.getChildCount();i++) {
			v = group.getChildAt(i);
			if (v instanceof ImageButton) {
				// if the current control is a button
				v.setOnClickListener(this);
				if (!isComplete) {
					myButtonIDs.add(v.getId()); //add the Button ID to the array
					//v.setVisibility(View.INVISIBLE);// Set the Button to invisible
					((ImageButton) v).setImageResource(R.drawable.empty_hole2);
				}
			}
		}	
	}
	
	// This method will create the timer that will allow us to switch current moles 
	protected void setTimer( long time) {
		// get the time that we want our timer to last from the input parameter
		final long elapse = time;
		
		// Create a new "runnable " task - this will create a timer feature
		Runnable t = new Runnable() {
			public void run() {
				onTimerTask(); // Change the current mole on the screen
				// If the game is not complete
				if (!isComplete) {
				 // create the new timer task to go off when the next mole should be shown
				 taskHandler.postDelayed(this, elapse);
				 
			 	}
			}

		};
		taskHandler.postDelayed( t, elapse);
	}
	
	// This method will change the current mole whenever the timer goes off
	private void onTimerTask() {
		// Calculate our ending time based on the duration setting
		long endTime = startTime + (duration * 1000);
		// If the ending time is greater than the current time, keep the game going
		if ( endTime > System.currentTimeMillis()) {
			setNewMole();
		}else {
			gameOver();
		}
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

	

}
