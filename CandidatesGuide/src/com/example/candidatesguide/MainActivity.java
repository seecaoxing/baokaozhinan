package com.example.candidatesguide;

import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.Bmob;

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

		// new Handler().postDelayed(new Runnable() {
		// @Override
		// public void run() {
		// Intent intent = new Intent(MainActivity.this,
		// MenuActivity.class);
		// startActivity(intent);
		// MainActivity.this.finish();
		// }
		// }, 2000);

		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				startActivity(new Intent(MainActivity.this, MenuActivity.class));
				finish();

			}
		}, 2000);// 这里停留时间为3秒

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
