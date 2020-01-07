package com.example.whack_a_mole;



import java.io.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.*;
import android.widget.*;
public class GameOver extends Activity implements OnClickListener{
	String playerName;
	int numWhacks;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameover);
		//Creates an Intent with the bundle of extras
			Intent startIntent = getIntent();
			Bundle bun = startIntent.getExtras();
			playerName = bun.getString("name");
			numWhacks = bun.getInt("Score");
			TextView hits = (TextView)findViewById(R.id.tvHits);
			hits.setText("You Hit: " + numWhacks + " times" );
			TextView gameOver = (TextView)findViewById(R.id.tvGameOver);
			gameOver.setText("Game Over, " + playerName);
			Button Play = (Button)findViewById(R.id.buttonPlay);
			Play.setOnClickListener(this);
			Button Highscores = (Button)findViewById(R.id.buttonScores);
			Highscores.setOnClickListener(this);
			
			String state = Environment.getExternalStorageState();
			if (state.equals(Environment.MEDIA_MOUNTED))
			{
			saveHighScoreSD();
			}else {
				saveHighScoreInternalFile();
			}
	}
	
	// save the player's score into the specified FileOutputStream
	private void writeToFOS(FileOutputStream fos) {
		try
		{
		   // open file, overwriting any previous version
		   //FileOutputStream fos1 = openFileOutput("HighScores.txt", MODE_PRIVATE);
		   // create OutputStreamWriter around FileOutputStream       
		   OutputStreamWriter osw = new OutputStreamWriter(fos);

		   // create some String information and write it to the file
		   String strSeparator = System.getProperty("line.separator");
		  // String strInformationName =  playerName;
		  // String strInformationScore = "" + numWhacks;
		   playerName += strSeparator;
		   osw.write(playerName);
		   String score = numWhacks + strSeparator.toString();
		   osw.write(score);
		    // write String to the file
		   //osw.write(strInformationName + strSeparator);
		  // osw.write(strInformationScore + strSeparator);
		   
		   // flush and close the file through the OutputStreamWriter
		   osw.flush();
		   osw.close();
		}
		catch(Exception e)
		{
		   // handle error...
		}

	}
	
	// This method will save the player's score into the HighScores.text file in Internal Memory
	private void saveHighScoreInternalFile() {
			try {
				
				FileOutputStream fos = openFileOutput("HighScores.txt", MODE_APPEND);
				writeToFOS(fos);
			} catch (FileNotFoundException e) {
				
				//e.printStackTrace();
			}
	}
	// This method will save the player's score into the HighScores.txt file on a SD card
	private void saveHighScoreSD() {
		
		
		   // we can read and write to the SD card
			try {
			File privateLocation = getExternalFilesDir(null);
			File myFile = new File(privateLocation, "highScores.txt");
			FileOutputStream fos = new FileOutputStream(myFile,true);
			writeToFOS(fos);
			} catch (FileNotFoundException e) {
				
			}
		
	}
	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(id == R.id.buttonPlay) {
			Intent startover = new Intent(GameOver.this, Game.class);
			
			startActivity(startover);
			finish();
		}
		else if(id == R.id.buttonScores) {
			Intent goToHS = new Intent(GameOver.this, HighScores.class);
			
			startActivity(goToHS);
			finish();
			
		}
		
	}
}
