package com.example.candidatesguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Find_ShowActivity extends Activity {
	private  String city_name,score,position;
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			
			if (msg.what==0x13) {
				
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find_show);
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		city_name = bundle.getString("city_name");
		score = bundle.getString("score");
		position = bundle.getString("position");
		System.out.println(city_name+score+position);
		
		
	}

}
