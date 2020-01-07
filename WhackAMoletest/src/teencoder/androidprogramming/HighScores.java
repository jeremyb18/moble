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
 * This class represents the code for the HighScores screen in the
 * "Whack-A-Mole" game.
 * @author Homeschool Programming, Inc.
 * @version 2.0
*/

package teencoder.androidprogramming;

import android.app.Activity;
import android.os.Bundle;
import java.io.*;
import java.util.*;
import android.widget.*;


//The class for the High Scores screen
public class HighScores extends Activity 

{
    public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
        setContentView(R.layout.highscores);
		
        // Load all the scores
        loadHighScoresInternalFile();	// Chapter 7 Activity #2
        //loadHighScoresSD();			// Chapter 7 Activity #3
	}

    
	//**********************************************************
    // The student will complete this method for Chapter 7 Activity #2
	//
	// This method will load the scores from the HighScores.txt internal file
	private void loadHighScoresInternalFile()
	{

	}
	
	//**********************************************************
    // The student will complete this method for Chapter 7 Activity #3
	//
	// This method will load the scores from the HighScores.txt file
	private void loadHighScoresSD()
	{

	}
	//**********************************************************


	//**********************************************************
    // This method is provided as part of the activity starter.
    //
	// this method will load the scores from the FileInputStream
	// sort them by score, and select the top 5 to put in the
	// high scores list on the screen.
	private void readScoresFIS(FileInputStream fis)
	{
		// create InputStreamReader 
	    InputStreamReader isr = new InputStreamReader(fis);

		// Get a handle to the two text fields on the screen
		TextView tvName = (TextView)findViewById(R.id.tvPlayerName);
		TextView tvScore = (TextView)findViewById(R.id.tvScore);
		
		// Figure out which character is an endline on the current device
		String endLine = System.getProperty("line.separator");
		
		LinkedList<String> playerNames = new LinkedList<String>();
		LinkedList<Integer> playerScores = new LinkedList<Integer>();
		
		try
		{
			// Use a BufferedReader to allow for easy reading of the file data
			BufferedReader buffreader = new BufferedReader(isr);
			
			// Read in the data, line-by-line
			String name = buffreader.readLine();
			
			// While we still have valid data
			while (name != null) 
			{
				// Read in the next line (which will contain the player's score)
				String strScore = buffreader.readLine();
				int score = Integer.parseInt(strScore);	// covert to int

				// place the name an score into the linked list in sorted order!
				ListIterator<Integer> scoreIter = playerScores.listIterator();
				ListIterator<String> playerIter = playerNames.listIterator();
				while (scoreIter.hasNext())
				{
					// get the next integer and also iterate to the next name
					Integer thisScore = scoreIter.next();
					playerIter.next();
					
					// if new score is larger than this one
					if (score >= thisScore)
					{
						break;	// stop looking, we know where to insert!
					}
				}
				// if there are any scores at all, we need to rewind both iterators
				// by one to insert in the correct spot
				if (playerScores.size() > 0)
				{
					scoreIter.previous();
					playerIter.previous();
				}
				
				// add this score and name into the linked lists in sorted order
				scoreIter.add(new Integer(score));
				playerIter.add(name);
				
				// Read in the next line in the file
				name = buffreader.readLine();
		    }
			
			// Close the BufferedReader object
			buffreader.close();
		}
		catch(Exception e)
		{
			// file handling error
		}
		
		// now, iterate again over the sorted list
		ListIterator<Integer> scoreIter = playerScores.listIterator();
		ListIterator<String> playerIter = playerNames.listIterator();
		
		// these strings will contain the sorted scores and corresponding names
		String sortedNames = "";
		String sortedScores = "";

		int numPresent = 0;	// count how many scores we're adding
		while (scoreIter.hasNext())
		{
			// get the score and corresponding name
			Integer score = scoreIter.next();
			String name = playerIter.next();
			
			// add score and name plus a newline to respective text fields
			sortedScores += score.toString() + endLine;
			sortedNames += name + endLine;

			// count scores and stop after top 5
			numPresent++;
			if (numPresent >= 5)
				break;
		}
		
		// Put the stored names in the name TextView
		tvName.setText(sortedNames);
		
		// Put the sorted scores in the score TextView
		tvScore.setText(sortedScores);
		
	}
	//**********************************************************
	

}	
	


