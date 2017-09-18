package com.example.candidatesguide;

import com.example.classinfo.SchoolInfo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Introduction_ShowActivity extends Activity {
	/**
	 * 从IntroductionActivity中的listview获得点击事件 传过来一个SchoolInfo对象
	 * 在这个页面显示这个对象的学校描述信息
	 */

	private TextView school_msg_tView;
	private SchoolInfo sInfo_show = new SchoolInfo();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.introduction_show);
		school_msg_tView = (TextView) findViewById(R.id.school_msg_show);
		sInfo_show = (SchoolInfo) getIntent().getSerializableExtra("sInfo");

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				school_msg_tView.setText(sInfo_show.getSchool_introduction()
						.toString());
			}
		}).start();
	}
}
