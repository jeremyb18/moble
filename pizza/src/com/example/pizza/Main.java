package com.example.pizza;

import android.os.Bundle;


import android.app.Activity;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.Intent;

public class Main extends Activity implements OnClickListener {
	EditText name;
	RadioButton stuff;
	RadioButton pan;
	RadioButton thin;
	RadioButton Deep;
	CheckBox Pep;
	CheckBox Bac;
	CheckBox Sas;
	CheckBox Pine;
	CheckBox Ham;
	CheckBox Mus;
	CheckBox Oli;
	CheckBox Oni;
	Button order;
	Button size;
	String sizeIn = "medium";
	String sodaIn = "none";
	String[] soda = {"Coke", "Pepse", "Sprite", "Root Beer", "Mountain Dew", "None"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		name = (EditText)findViewById(R.id.editText1);
		stuff = (RadioButton)findViewById(R.id.radio0);
		pan = (RadioButton)findViewById(R.id.radio1);
		thin = (RadioButton)findViewById(R.id.radio2);
		Deep = (RadioButton)findViewById(R.id.radio3);
		Pep = (CheckBox)findViewById(R.id.checkBox1);
		Bac = (CheckBox)findViewById(R.id.checkBox2);
		Sas = (CheckBox)findViewById(R.id.checkBox3);
		Pine = (CheckBox)findViewById(R.id.checkBox4);
		Ham = (CheckBox)findViewById(R.id.checkBox5);
		Mus = (CheckBox)findViewById(R.id.checkBox6);
		Oli = (CheckBox)findViewById(R.id.checkBox7);
		Oni = (CheckBox)findViewById(R.id.checkBox8);
		order = (Button)findViewById(R.id.button1);
		size = (Button)findViewById(R.id.button2);
		size.setOnClickListener(this);
		order.setOnClickListener(this);
		ArrayAdapter<String> myAdapter = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item, soda);
		Spinner spn = (Spinner)findViewById(R.id.spinner1);
		
		spn.setAdapter(myAdapter);
		myAdapter.setDropDownViewResource         
       (android.R.layout.simple_spinner_dropdown_item);
		spn.setSelection(5);
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(id == R.id.button2)
		{
			
			startActivityForResult(0);
			
			
		}
		
		if(id == R.id.button1)
		{
			String Order_Name = name.getText().toString();
			String Crust = "";
		if (stuff.isChecked()){
		   
			Crust = "Stuffed";
		}else if (pan.isChecked()){
			
			Crust = "Pan";
		} else if (thin.isChecked()){
			
			Crust = "Thin";
		} else {
			Crust = "Deep Dish";
		}
		String Toppings = "" ;
		if (Pep.isChecked()){
		   Toppings = Toppings + " Peperoni ";
		}
		if (Bac.isChecked()){
			Toppings = Toppings + " Bacon ";
		}
		if (Sas.isChecked()){
			Toppings = Toppings + " Sausage ";
		}
		if (Pine.isChecked()){
			Toppings = Toppings + " Pineapple ";
		}
		if (Ham.isChecked()){
			Toppings = Toppings + " Ham ";
		}
		if (Mus.isChecked()){
			Toppings = Toppings + " Mushroom ";
		}
		if (Oli.isChecked()){
			Toppings = Toppings + " Olives ";
		}
		if (Oni.isChecked()){
			Toppings = Toppings + " Onions ";
		}
		Spinner spn = (Spinner)findViewById(R.id.spinner1);
		int pos = spn.getSelectedItemPosition();
		 sodaIn = spn.getItemAtPosition(pos).toString();
		 SeekBar sb = (SeekBar)findViewById(R.id.seekBar1);
		 int seekValue = sb.getProgress();
		 if( seekValue < 16) {
			 sb.setProgress(12);
		 }else if( seekValue < 21) {
			 sb.setProgress(21);
			 
		 }else if( seekValue < 32) {
			 sb.setProgress(32);
			 
		 }else if( seekValue < 48) {
			 sb.setProgress(48);
			 
		 }else if( seekValue <= 64) {
			 sb.setProgress(64);
			 
		 }
		 seekValue = sb.getProgress();
		Intent  change_to_second = new Intent(Main.this,Order.class);
		
		change_to_second.putExtra("name", Order_Name);
		change_to_second.putExtra("Crust", Crust);
		change_to_second.putExtra("Toppings", Toppings);
		change_to_second.putExtra("size", sizeIn);
		change_to_second.putExtra("soda", sodaIn + " "+ seekValue +  "oz");
		startActivity(change_to_second);
		}
		
		
	}
	public void startActivityForResult(int requestCode)
	{
		
		
		if(requestCode == 0) {
		Intent  change_to_size = new Intent(Main.this,ListSize.class);
		startActivityForResult(change_to_size,requestCode);
		}
		
		if(requestCode == 1) {
			Intent  change_to_soda = new Intent(Main.this,SodaList.class);
			startActivityForResult(change_to_soda,requestCode);
		}
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		Bundle bun = data.getExtras();
		
		
		String value1 = bun.getString("size");
		size.setText(value1);
		sizeIn = value1;
		
		
	}
}
