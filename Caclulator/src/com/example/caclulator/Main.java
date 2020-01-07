package com.example.caclulator;

import android.os.Bundle;



import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.*;
import android.widget.*;
public class Main extends Activity implements OnClickListener{
	Button button0;
	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Button button5;
	Button button6;
	Button button7;
	Button button8;
	Button button9;
	Button buttonD;
	Button buttonX;
	Button buttonS;
	Button buttonA;
	Button buttonP;
	Button buttonE;
	TextView numholder;
	double num1 = 0;
	double num2 = 0;
	String show = "";
	String operator = "";
	boolean bequal = false;
	int period = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		button0 = (Button)findViewById(R.id.button0);
		button0.setOnClickListener(this);
		button1 = (Button)findViewById(R.id.button1);
		button1.setOnClickListener(this);
		button2 = (Button)findViewById(R.id.button2);
		button2.setOnClickListener(this);
		button3 = (Button)findViewById(R.id.button3);
		button3.setOnClickListener(this);
		button4 = (Button)findViewById(R.id.button4);
		button4.setOnClickListener(this);
		button5 = (Button)findViewById(R.id.button5);
		button5.setOnClickListener(this);
		button6 = (Button)findViewById(R.id.button6);
		button6.setOnClickListener(this);
		button7 = (Button)findViewById(R.id.button7);
		button7.setOnClickListener(this);
		button8 = (Button)findViewById(R.id.button8);
		button8.setOnClickListener(this);
		button9 = (Button)findViewById(R.id.button9);
		button9.setOnClickListener(this);
		buttonD = (Button)findViewById(R.id.buttonDivide);
		buttonD.setOnClickListener(this);
		buttonX = (Button)findViewById(R.id.buttonX);
		buttonX.setOnClickListener(this);
		buttonS = (Button)findViewById(R.id.buttonM);
		buttonS.setOnClickListener(this);
		buttonA = (Button)findViewById(R.id.buttonPlus);
		buttonA.setOnClickListener(this);
		buttonP = (Button)findViewById(R.id.buttonPeriod);
		buttonP.setOnClickListener(this);
		buttonE = (Button)findViewById(R.id.buttonEqual);
		buttonE.setOnClickListener(this);
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
		
		numholder = (TextView)findViewById(R.id.textView1);
		if(bequal == false && period == 0) {
		if(id == R.id.button0)
		{

			num1 = num1*10;
			show = show + 0;
			
		}
		if(id == R.id.button1)
		{
			num1 = num1*10;
			num1 = num1+1;
			show = show + 1;
			
		}
		if(id == R.id.button2)
		{
			num1 = num1*10;
			num1 = num1+2;
			show = show + 2;
			
		}
		if(id == R.id.button3)
		{
			num1 = num1*10;
			num1 = num1+3;
			show = show + 3;
			
		}
		if(id == R.id.button4)
		{
			num1 = num1*10;
			num1 = num1+4;
			show = show + 4;
			
		}
		if(id == R.id.button5)
		{
			num1 = num1*10;
			num1 = num1+5;
			show = show + 5;
			
		}
		if(id == R.id.button6)
		{
			num1 = num1*10;
			num1 = num1+6;
			show = show + 6;
			
		}
		if(id == R.id.button7)
		{
			num1 = num1*10;
			num1 = num1+7;
			show = show + 7;
			
		}
		if(id == R.id.button8)
		{
			num1 = num1*10;
			num1 = num1+8;
			show = show + 8;
			
		}
		if(id == R.id.button9)
		{
			num1 = num1*10;
			num1 = num1+9;
			show = show + 9;
			
		}
		if(id == R.id.buttonPeriod) {
			show = show + ".";
			period = 1;
		}
		}
		
		
		if(bequal == false && period > 0) {
			if(id == R.id.button0)
			{

				period++;
				show = show + 0;
				
			}
			if(id == R.id.button1)
			{
				num1 = num1+1/(Math.pow(10,period));
				period++;
				show = show + 1;
				
			}
			if(id == R.id.button2)
			{
				num1 = num1+2/(Math.pow(10,period));
				period++;
				show = show + 2;
				
			}
			if(id == R.id.button3)
			{
				num1 = num1+3/(Math.pow(10,period));
				period++;
				show = show + 3;
				
			}
			if(id == R.id.button4)
			{
				num1 = num1+4/(Math.pow(10,period));
				period++;
				show = show + 4;
				
			}
			if(id == R.id.button5)
			{
				num1 = num1+5/(Math.pow(10,period));
				period++;
				
				show = show + 5;
				
			}
			if(id == R.id.button6)
			{
				num1 = num1+6/(Math.pow(10,period));
				period++;
				
				show = show + 6;
				
			}
			if(id == R.id.button7)
			{
				num1 = num1+7/(Math.pow(10,period));
				period++;
				show = show + 7;
				
			}
			if(id == R.id.button8)
			{
				num1 = num1+8/(Math.pow(10,period));
				period++;
				show = show + 8;
				
			}
			if(id == R.id.button9)
			{
				num1 = num1+9/(Math.pow(10,period));
				period++;

				show = show + 9;
				
			}
		}
		if(id == R.id.buttonDivide)
		{
			if(operator != "") {
				show = "" + Calculate(num2, operator, num1);
				num1 = Calculate(num2, operator, num1);
				num2 = 0;
				operator = "";
			}

			num2 = num1;
			num1 = 0;
			operator = "/";
			bequal = false;
			show = show + "/";
			
		}
		if(id == R.id.buttonX)
		{
			if(operator != "") {
				show = "" + Calculate(num2, operator, num1);
				num1 = Calculate(num2, operator, num1);
				num2 = 0;
				operator = "";
			}
			num2 = num1;
			num1 = 0;
			bequal = false;
			period = 0;
			operator = "X";
			show = show + "X";
			
		}
		if(id == R.id.buttonM)
		{
			if(operator != "") {
				show = "" + Calculate(num2, operator, num1);
				num1 = Calculate(num2, operator, num1);
				num2 = 0;
				
				operator = "";
			}
			num2 = num1;
			num1 = 0;
			bequal = false;
			period = 0;
			operator = "-";
			show = show + "-";
			
		}
		if(id == R.id.buttonPlus)
		{
			if(operator != "") {
				show = "" + Calculate(num2, operator, num1);
				num1 = Calculate(num2, operator, num1);
				num2 = 0;
				operator = "";
			}
			num2 = num1;
			num1 = 0;
			bequal = false;
			period = 0;
			operator = "+";
			show = show + "+";
			
		}
		
		if(id == R.id.buttonEqual)
		{
			show = "" + Calculate(num2, operator, num1);
			num1 = Calculate(num2, operator, num1);
			num2 = 0;
			bequal = true;
			period = 0;
			operator = "";
		}
		//numholder.setText(""+num1);
		//numholder.setText(num2 + operator + num1);
		numholder.setText(show);
	}
	public double Calculate(double num2,String operator,double num1) {
		if(operator == "/") {
			if(num1 == 0) {
				//show = "undefined";
				return  0;
			}else {
				//show = "" + num2/num1;
				
				return  num2/num1;
			}
			
		}else
		if(operator == "X") {
			//show = "" + num2 * num1;
			return num2*num1;
		}else
		if(operator == "-") {
			//show = "" + (num2 - num1);
			return num2-num1;
		}else
		if(operator == "+") {
			//show = "" + num2 + num1;
			return num2 + num1;
		}else {
			return num1;
		}
		
	}
}
	