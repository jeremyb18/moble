package com.example.sms_test;

import android.os.Bundle;
import android.text.Editable;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.telephony.*;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsMessage;
public class Main extends Activity implements OnClickListener{
	Button sendSms;
	String SentText = "";
	String RecievedText = "";
	SmsManager sms = SmsManager.getDefault();
	BroadcastReceiver mySentReceiver = null;
	BroadcastReceiver mySmsReceiver = null;
	BroadcastReceiver myDeliveryReceiver = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		sendSms = (Button)findViewById(R.id.button1);
		sendSms.setOnClickListener(this);
		
		mySentReceiver =
                new BroadcastReceiver()     // anonymous inner class
{
					public void onReceive(Context context, Intent intent){
						if (getResultCode() == Activity.RESULT_OK) // if message sent
						{
							Toast t = Toast.makeText(context, "Message Sent!",Toast.LENGTH_LONG);
							t.show();
						}
						else  // else some send error occurred
						{
							Toast t = Toast.makeText(context,"Message Error!",Toast.LENGTH_LONG);
							t.show();
						}
				}


		};
		
		registerReceiver(mySentReceiver, new IntentFilter("MY_SMS_SENT"));
		
		myDeliveryReceiver =
                new BroadcastReceiver()   // anonymous inner class
  {
    public void onReceive(Context context, Intent intent)
    {
      if (getResultCode() == Activity.RESULT_OK)
      {
        Toast t = Toast.makeText(context, "Msg Delivered!",
                                 Toast.LENGTH_LONG);
        t.show();
      }
      else
      {
        Toast t = Toast.makeText(context,"Msg Error!",
                                 Toast.LENGTH_LONG);
        t.show();
      }
  }
};
		

		registerReceiver(myDeliveryReceiver, new IntentFilter("MY_SMS_DELIVERED"));

		
		
		mySmsReceiver =
		         new BroadcastReceiver()   // anonymous inner class
		         {
		           public void onReceive(Context context, Intent intent)
		           {
		             Bundle bundle = intent.getExtras();
		           
		             if (bundle == null){return;}// error - no Bundle present
		            
		             Object[] pdus = (Object[]) bundle.get("pdus");
		             
		             if (pdus == null) {return;} // error - no PDU values present
		             
		             for (int i=0; i<pdus.length; i++)  // for each message
		             {
		                // convert from PDU to SmsMessage
		              SmsMessage message = SmsMessage.createFromPdu((byte[]) pdus[i]);
		              	TextView Recieved = (TextView)findViewById(R.id.textView2);
		                // get from address and message text
		                String from = message.getOriginatingAddress();
		                String text = message.getMessageBody().toString();  
		                // format a nice display string and show with a Toast
		                String display = from + " sent you: " + text;
		                Toast t = Toast.makeText(context,display,Toast.LENGTH_LONG);
		                t.show();
		                RecievedText = RecievedText + text + "\n";
		                Recieved.setText(RecievedText);
		              }
		             
		            }
		          };
		          
	 registerReceiver(mySmsReceiver,new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
	
	// Button send = (Button)findViewById(R.id.button1);
	
	//send.setOnClickListener(new OnClickListener() {
	//	
		//public void onClick(View v) {
			
			
		//}
		
	//});
	
	
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
		if(id == R.id.button1) {
			EditText PhoneNumber = (EditText)findViewById(R.id.editText1);
			EditText TextToSend = (EditText)findViewById(R.id.editText2);
			TextView Sent = (TextView)findViewById(R.id.textView3);
			
			String address = PhoneNumber.getText().toString();
			String message = TextToSend.getText().toString();
			
			
			PendingIntent msgSentIntent = PendingIntent.getBroadcast(this, 0,
	                new Intent("MY_SMS_SENT"), 0);
			PendingIntent msgDeliveredIntent =
                    PendingIntent.getBroadcast(this, 0,
                    new Intent("MY_SMS_DELIVERED"), 0);
			
			
			SentText = SentText  + message + "\n"  ;
			
			sms.sendTextMessage(address, null, message,
					msgSentIntent, msgDeliveredIntent);
			Sent.setText(SentText);
			
		}
		
		
	}


	public void onDestroy()
	{
	   super.onDestroy();  // always call the base class version
	   if (mySentReceiver!= null)     // just to be safe
	   {
	      // unregister BroadcastReceiver
	      unregisterReceiver(mySentReceiver); 
	   }
	   if (mySmsReceiver != null)
	   {
	     unregisterReceiver(mySmsReceiver);
	   }
	   if (myDeliveryReceiver != null) 
	   {
		   unregisterReceiver(myDeliveryReceiver); 
	   }
	}

}



