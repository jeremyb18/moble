package com.example.list_box;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class petList extends ListActivity {
	String[] pets = {"Dog", "Fish", "Spider", "Snake", "Parrot", "Chicken"};
	//String[] pets = {type_of_pets[-1 + (int)(6.0 * Math.random())], type_of_pets[-1 + (int)(6.0 * Math.random())]};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		ArrayAdapter<String> myAdapter = new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1, pets);
		setListAdapter(myAdapter);
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		// get selected value by position
		String selection = pets[position];  

		// create new Intent to hold info
		Intent resultIntent = new Intent();

		// store result info in Intent  
		resultIntent.putExtra("pet", selection); 

		setResult(RESULT_OK,resultIntent);   // send Intent back to Main

		finish();  // close the ListActivity
	}
}
