package com.example.candidatesguide;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);      
        
	new Handler().postDelayed(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity.this,MenuActivity.class);
			System.out.println("nihao");
			startActivity(intent);
			MainActivity.this.finish();
		}
	}, 2000);
/*	new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
			startActivity(new Intent(MainActivity.this,MenuActivity.class));
			finish();
				
			}
		},1000);//����ͣ��ʱ��Ϊ3��
		*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
