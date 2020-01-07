package com.example.pizza;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Order extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order);
		
		Intent startIntent = getIntent();
		Bundle bun = startIntent.getExtras();
		String Name = bun.getString("name");
		String Crust = bun.getString("Crust");
		String Toppings = bun.getString("Toppings");
		String Size = bun.getString("size");
		String Soda = bun.getString("soda");
		TextView tv1 = (TextView)findViewById(R.id.textView1);
		TextView tv2 = (TextView)findViewById(R.id.textView2);
		TextView tv3 = (TextView)findViewById(R.id.textView3);
		tv1.setText("Order for: " + Name);
		tv2.setText("Crust: " + Crust + "   |   Size: " + Size + "   |   Soda: " + Soda);
		tv3.setText("Toppings: " + Toppings);
	}
	
	
}
