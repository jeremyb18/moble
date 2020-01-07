package com.example.whack_a_mole;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.content.*;

public class Options extends Activity implements OnClickListener{
	EditText name;
	RadioButton easy;
	RadioButton medium;
	RadioButton hard;
	Button play;
	int[] numberOfMoles = { 3, 4, 5, 6,7,8};
	
	
	
	Spinner spn;
	
	
	
	private void setUpSpinner() {
		
		
		spn = (Spinner)findViewById(R.id.number_moles);
		String[] numberOfMoles = { "3", "4", "5"," 6","7","8"};
		ArrayAdapter<String> myAdapter = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item, numberOfMoles);
		spn.setAdapter(myAdapter);
		myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
	}
	/*
	private void saveSettingIntent(int difficulty, String name, int numMoles, int duration, Intent myIntent) {
		
		myIntent.putExtra("name", name);
		myIntent.putExtra("difficulty", difficulty);
		myIntent.putExtra("number", numMoles);
		myIntent.putExtra("duration", duration);
		
		
	}
	*/
	
	private void saveSettingsInPrefs(int difficulty, String name, int numMoles, int duration) {
		SharedPreferences wackSetting = getSharedPreferences("MyPreferences", MODE_PRIVATE);
		
		SharedPreferences.Editor ed = wackSetting.edit();
		ed.putInt("diff", difficulty);
		ed.putString("name", name);
		ed.putInt("num", numMoles);
		ed.putInt("duration", duration);
		ed.commit();
	}
	private void loadSettings() {
		 SharedPreferences wackSetting = getSharedPreferences("MyPreferences", MODE_PRIVATE);
		 String Name = wackSetting.getString("name", "");
		 int number = wackSetting.getInt("num", 8);
		int diff = wackSetting.getInt("diff", 2);
		int duration = wackSetting.getInt("duration", 20);
		
		name = (EditText)findViewById(R.id.name);
		name.setText(Name);
		easy = (RadioButton)findViewById(R.id.easy);
		medium = (RadioButton)findViewById(R.id.medium);
		hard = (RadioButton)findViewById(R.id.hard);
		if(diff == 1) {
			hard.setChecked(true);
		}else if(diff == 2) {
			medium.setChecked(true);
		}else {
			easy.setChecked(true);
		}
		 SeekBar sb = (SeekBar)findViewById(R.id.duration);
		 sb.setProgress(duration);
		 Spinner spn = (Spinner)findViewById(R.id.number_moles);
		 spn.setSelection(number - 3);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.options);
		
		name = (EditText)findViewById(R.id.name);
		easy = (RadioButton)findViewById(R.id.easy);
		medium = (RadioButton)findViewById(R.id.medium);
		hard = (RadioButton)findViewById(R.id.hard);
		play = (Button)findViewById(R.id.play_game);
		play.setOnClickListener(this);
		setUpSpinner();
		loadSettings();
	}
	
	
	
	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(id != R.id.play_game)
		{
			return;
		}else {
			
			if(id == R.id.play_game)
			{
				
				String Name = name.getText().toString();
				int difficulty = 1;
				
			if (easy.isChecked()){
			   
				difficulty = 3;
			}else if (medium.isChecked()){
				
				difficulty = 2;
			} else {
				
				difficulty = 1;
			} 
			
			Spinner spn = (Spinner)findViewById(R.id.number_moles);
			int pos = spn.getSelectedItemPosition();
			int number = numberOfMoles[pos];
			 
			 SeekBar sb = (SeekBar)findViewById(R.id.duration);
			 int seekValue = sb.getProgress();
			Intent myIntent = new Intent(Options.this,Game.class);
			 
			// saveSettingIntent(difficulty, Name, number , seekValue , myIntent);
			 saveSettingsInPrefs(difficulty, Name, number, seekValue );
			 startActivity(myIntent);
		}
		
	
		
	}

}
	
	
}
