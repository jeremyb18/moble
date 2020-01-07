package com.example.whack_a_mole;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Startpg extends Activity implements OnClickListener{
	Button play;
	Button options;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startpg);
		play = (Button)findViewById(R.id.button1);
		play.setOnClickListener(this);
		options = (Button)findViewById(R.id.button2);
		options.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(id == R.id.button1) {
			Intent Game = new Intent(this, Game.class);
			
			startActivity(Game);
			
		}
		if(id == R.id.button2) {
			Intent option = new Intent(this, Options.class);
			
			startActivity(option);
			
		}
		
	}
}
