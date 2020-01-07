package com.example.pizza;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListSize extends ListActivity{
	String[] size = {"Small", "Medium", "Large", "Family", "Extra large"};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		ArrayAdapter<String> myAdapter = new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1, size);
		setListAdapter(myAdapter);
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		// get selected value by position
		String selection = size[position];  

		// create new Intent to hold info
		Intent resultIntent = new Intent();

		// store result info in Intent  
		resultIntent.putExtra("size", selection); 

		setResult(RESULT_OK,resultIntent);   // send Intent back to Main

		finish();  // close the ListActivity
	}
}
